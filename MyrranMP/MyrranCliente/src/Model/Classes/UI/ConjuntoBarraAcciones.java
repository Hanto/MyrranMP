package Model.Classes.UI;// Created by Hanto on 08/05/2014.

import Interfaces.Model.AbstractModel;
import Interfaces.UI.Acciones.AccionI;
import Interfaces.UI.BarraAcciones.BarraAccionesI;
import Interfaces.UI.BarraAcciones.ListaAccionesI;
import Model.Classes.Acciones.Accion;
import Model.Classes.Input.InputManager;
import Model.Classes.UI.BarraAcciones;
import Model.DTO.BarraAccionesDTO;

import java.util.HashMap;
import java.util.Map;

public class ConjuntoBarraAcciones extends AbstractModel
{
    protected InputManager inputManager;
    protected Map<Integer, BarraAcciones> listaDeBarraAcciones = new HashMap<>();

    public ConjuntoBarraAcciones(InputManager inputManager)
    {   this.inputManager = inputManager; }




    public BarraAcciones getBarraAcciones (int iD)
    {   return listaDeBarraAcciones.get(iD); }

    public void añadirBarraAcciones (int filas, int columnas)
    {
        int iDMenor;
        for (iDMenor =0; iDMenor < listaDeBarraAcciones.size(); iDMenor++)
        {   if (!listaDeBarraAcciones.containsKey(iDMenor)) break; }


        BarraAcciones barraAcciones = new BarraAcciones(inputManager, iDMenor, filas, columnas);
        listaDeBarraAcciones.put(barraAcciones.getID(), barraAcciones);

        Object añadirBarraAccionesDTO = new BarraAccionesDTO.AñadirBarraAcciones(barraAcciones);
        notificarActualizacion("añadirBarraAcciones", null, añadirBarraAccionesDTO);
    }

    public void eliminarBarraAccion(BarraAccionesI barraAccion)
    {   listaDeBarraAcciones.remove(barraAccion.getID());
        barraAccion.eliminar();
    }


    public void setKeycode (BarraAccionesI barra, int posX, int posY, int keycode)
    {
        for (Map.Entry<Integer,BarraAcciones> entry : listaDeBarraAcciones.entrySet())
        {   entry.getValue().eliminarKeycode(keycode); }

        barra.setKeycode(posX, posY, keycode);
    }


    public void  setAccion (BarraAccionesI barra, int posX, int posY, String idAccion, int keycode)
    {
        setAccion(barra, posX, posY, idAccion);
        setKeycode(barra, posX, posY, keycode);
    }

    public void setAccion (BarraAccionesI barra, int posX, int posY, String idAccion)
    {
        AccionI accion = inputManager.getAccion(idAccion);
        barra.setAccion(posX, posY, accion);
    }

    public void setAccion (BarraAccionesI barra, int posX, int posY, Accion accion)
    {   barra.setAccion(posX, posY, accion); }

    public void eliminarAccion (BarraAccionesI barra, int posX, int posY)
    {   barra.eliminarAccion (posX, posY); }

    public void moverAccion (ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino, int posXDestino, int posYDestino)
    {
        AccionI accionOrigen = barraOrigen.getAccion(posXOrigen, posYOrigen);
        AccionI accionDestino = barraDestino.getAccion(posXDestino, posYDestino);

        if (barraOrigen instanceof BarraAccionesI)
        {
            if (accionDestino == null) ((BarraAccionesI)barraOrigen).eliminarAccion(posXOrigen, posYOrigen);
            else ((BarraAccionesI)barraOrigen).setAccion(posXOrigen, posYOrigen, accionDestino);
        }

        if (barraDestino instanceof BarraAccionesI)
        {
            if (accionOrigen == null) ((BarraAccionesI)barraDestino).eliminarAccion(posXDestino, posYDestino);
            else ((BarraAccionesI)barraDestino).setAccion(posXDestino, posYDestino, accionOrigen);
        }
    }
}
