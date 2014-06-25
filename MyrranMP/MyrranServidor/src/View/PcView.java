package View;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Data.MiscData;
import Interfaces.EntidadesTipos.MobPC;
import Model.Classes.Mobiles.PC;
import Model.GameState.Mundo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PcView implements PropertyChangeListener
{
    //Model:
    public PC PC;
    public Vista vista;
    public Mundo mundo;
    public Controlador controlador;

    //Datos:
    private List<MobPC> listaPCsCercanos = new ArrayList<>();
    public boolean positionChanged = false;
    public float x;
    public float y;

    public MapaView mapaView;


    //Constructor:
    public PcView(PC PC, Vista vista)
    {
        this.PC = PC;
        this.vista = vista;
        this.controlador = vista.controlador;
        mundo = vista.mundo;

        x = PC.getX();
        y = PC.getY();

        mundo.getMapa().añadirObservador(this);
        PC.añadirObservador(this);

        NetDTO.ActualizarPPC actualizarPPC = new NetDTO.ActualizarPPC(PC);
        actualizarPlayer(actualizarPPC);

        quienMeVe();

        mapaView = new MapaView(PC, mundo, controlador);
    }

    public void netUpdate()
    {
        if (isVisible())
        {
            if (positionChanged)
            {
                NetDTO.PosicionPPC posicionPPC = new NetDTO.PosicionPPC(PC.getConnectionID(), x, y);
                actualizarPlayersCercanos(posicionPPC);
            }
            positionChanged = false;
        }
    }
    private void actualizarPlayersCercanos (Object obj)
    {
        for (MobPC PCCercanos : listaPCsCercanos)
            controlador.enviarACliente(PCCercanos.getConnectionID(), obj);
    }
    private void actualizarPlayer (Object obj)
    {   controlador.enviarACliente(PC.getConnectionID(), obj); }

    private boolean isVisible()
    {   return !listaPCsCercanos.isEmpty(); }


    private void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        this.quienMeVe();

        if (isVisible()) positionChanged = true;
        else positionChanged = false;

        mapaView.comprobarVistaMapa();
    }

    private void quienMeVe()
    {
        for (PcView pcCercanos : vista.listaPcViews)
        {
            MobPC PCCercano = pcCercanos.PC;

            if (PCCercano.getConnectionID() != PC.getConnectionID())
            {
                if (Math.abs(PCCercano.getX()- PC.getX()) <=  MiscData.SERVIDOR_DistanciaVisionMobs*MiscData.MAPTILE_Horizontal_Resolution /2 &&
                    Math.abs(PCCercano.getY()- PC.getY()) <=  MiscData.SERVIDOR_DistanciaVisionMobs*MiscData.MAPTILE_Vertical_Resolution /2     )
                {
                    añadirPCVisible(pcCercanos);
                    pcCercanos.añadirPCVisible(this);
                }
                else
                {
                    eliminarPCVisible(pcCercanos);
                    pcCercanos.eliminarPCVisible(this);
                }
            }
        }
    }

    private void añadirPCVisible (PcView pcview)
    {
        if (!listaPCsCercanos.contains(pcview.PC))
        {
            listaPCsCercanos.add(pcview.PC);
            NetDTO.ActualizarPPC añadirPC = new NetDTO.ActualizarPPC(pcview.PC);
            actualizarPlayer(añadirPC);
        }
    }

    private void eliminarPCVisible (PcView pcView)
    {
        if (listaPCsCercanos.contains(pcView.PC))
        {
            listaPCsCercanos.remove(pcView.PC);
            NetDTO.EliminarPPC eliminarPPC = new NetDTO.EliminarPPC(pcView.PC);
            actualizarPlayer(eliminarPPC);
        }
    }




    private void setAnimacion(NetDTO.AnimacionPPC animacion)
    {   actualizarPlayersCercanos(animacion); }
    private void modificarSkillTalento(NetDTO.ModificarSkillTalentoPPC skillTalento)
    {   actualizarPlayer(skillTalento); }
    private void añadirSkillTalento(NetDTO.AñadirSpellTalentosPPC spell)
    {   actualizarPlayer(spell); }
    private void modificarHPs(NetDTO.ModificarHPsPPC HPs)
    {   actualizarPlayersCercanos(HPs);
        actualizarPlayer(HPs);
    }
    private void eliminar(NetDTO.EliminarPPC eliminarPPC)
    {
        mundo.getMapa().eliminarObservador(this);
        PC.eliminarObservador(this);
        vista.listaPcViews.remove(this);
        actualizarPlayersCercanos(eliminarPPC);
    }



    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        //MOBILES:
        if (evt.getNewValue() instanceof NetDTO.PosicionPPC)
        {
            x = ((NetDTO.PosicionPPC) evt.getNewValue()).x;
            y = ((NetDTO.PosicionPPC) evt.getNewValue()).y;
            setPosition(x, y);
        }
        if (evt.getNewValue() instanceof NetDTO.ModificarHPsPPC)
        {   modificarHPs((NetDTO.ModificarHPsPPC)evt.getNewValue()); }

        if (evt.getNewValue() instanceof NetDTO.EliminarPPC)
        {   eliminar((NetDTO.EliminarPPC)evt.getNewValue()); }

        if (evt.getNewValue() instanceof NetDTO.AñadirSpellTalentosPPC)
        {   añadirSkillTalento((NetDTO.AñadirSpellTalentosPPC)evt.getNewValue());}

        if (evt.getNewValue() instanceof NetDTO.ModificarSkillTalentoPPC)
        {   modificarSkillTalento((NetDTO.ModificarSkillTalentoPPC)evt.getNewValue()); }

        if (isVisible())
        {
            if (evt.getNewValue() instanceof NetDTO.AnimacionPPC)
            {   setAnimacion((NetDTO.AnimacionPPC)evt.getNewValue()); }
        }

        //TERRENOS:
        if (evt.getNewValue() instanceof NetDTO.SetTerreno)
        {
            int celdaX = ((NetDTO.SetTerreno) evt.getNewValue()).celdaX;
            int celdaY = ((NetDTO.SetTerreno) evt.getNewValue()).celdaY;
            int numCapa = ((NetDTO.SetTerreno) evt.getNewValue()).numCapa;
            short iDTerreno = ((NetDTO.SetTerreno) evt.getNewValue()).iDTerreno;
            mapaView.cambioTerreno(celdaX, celdaY, numCapa, iDTerreno);
        }
    }
}
