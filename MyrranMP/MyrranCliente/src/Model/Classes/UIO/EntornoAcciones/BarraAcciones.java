package Model.Classes.UIO.EntornoAcciones;// Created by Hanto on 06/05/2014.

import Data.MiscData;
import Model.Classes.AbstractModel;
import Model.Classes.Acciones.Accion;
import Model.Classes.UIO.Keybinds;
import Model.DTO.BarraAccionesDTO;
import com.badlogic.gdx.utils.Array;

public class BarraAcciones extends AbstractModel
{
    private int iD;
    private Array<Casilla> barraSpells;
    private Keybinds keybinds;

    public static class Casilla
    {
        public Accion accion;
        public String keybind;
        public int keycode;
    }

    public int getID()          { return iD; }
    public void setID(int i)    { iD = i; }

    //CONSTRUCTOR:
    public BarraAcciones(Keybinds keybinds, int tamaño, int id)
    {
        this.keybinds = keybinds;
        barraSpells = new Array<>(tamaño);
        this.iD = id;

        for (int i=0; i< tamaño; i++)
        {
            Casilla casilla = new Casilla();
            casilla.accion = null;

            barraSpells.add(casilla);
        }
    }


    public int getTamaño()
    {   return barraSpells.size; }

    public Accion getAccion(int posicion)
    {   return barraSpells.get(posicion).accion; }

    public String getKeybind (int posicion)
    {   return barraSpells.get(posicion).keybind; }

    public void setKeycode (int posicion, int keycode)
    {
        for (int i=0; i<barraSpells.size; i++)
        {
            if (barraSpells.get(i).keycode == keycode)
            {
                barraSpells.get(i).keycode = 0;
                barraSpells.get(i).keybind = "";
                Object setKeycode = new BarraAccionesDTO.SetAccionDTO(i);
                notificarActualizacion("setKeycode", null, setKeycode);
            }
        }

        keybinds.listaDeBinds.remove(barraSpells.get(posicion).keycode);
        barraSpells.get(posicion).keycode = keycode;
        barraSpells.get(posicion).keybind = MiscData.keycodeNames.get(keycode);
        if (barraSpells.get(posicion).accion != null) keybinds.listaDeBinds.put(keycode, barraSpells.get(posicion).accion.getID());

        Object setKeycode = new BarraAccionesDTO.SetAccionDTO(posicion);
        notificarActualizacion("setKeycode", null, setKeycode);
    }

    public void removeKeycode (int keycode)
    {
        for (int i=0; i<barraSpells.size; i++)
        {
            if (barraSpells.get(i).keycode == keycode)
            {
                barraSpells.get(i).keycode = 0;
                barraSpells.get(i).keybind = "";
                Object setKeycode = new BarraAccionesDTO.SetAccionDTO(i);
                notificarActualizacion("setKeycode", null, setKeycode);
            }
        }
    }



    public void setAccion(int posicion, Accion accion)
    {
        barraSpells.get(posicion).accion = accion;
        keybinds.listaDeBinds.put(barraSpells.get(posicion).keycode, accion.getID());

        Object setAccionDTO = new BarraAccionesDTO.SetAccionDTO(posicion);
        notificarActualizacion("setAccion", null, setAccionDTO);
    }

    public void removeAccion (int posicion)
    {
        barraSpells.get(posicion).accion = null;
        keybinds.listaDeBinds.remove(barraSpells.get(posicion).keycode);

        Object removeAccionDTO = new BarraAccionesDTO.RemoveAccionDTO(posicion);
        notificarActualizacion("removeAccion", null, removeAccionDTO);
    }

    public void moverAccion(int posicionOrigen, int posicionDestino)
    {
        Accion accionOrigen = barraSpells.get(posicionOrigen).accion;
        Accion accionDestino = barraSpells.get(posicionDestino).accion;

        if (accionDestino == null) removeAccion(posicionOrigen);
        else setAccion(posicionOrigen, accionDestino);

        if (accionOrigen == null) removeAccion(posicionDestino);
        else setAccion(posicionDestino, accionOrigen);
    }
}
