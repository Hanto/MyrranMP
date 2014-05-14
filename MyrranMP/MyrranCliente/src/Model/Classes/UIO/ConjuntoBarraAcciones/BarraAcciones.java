package Model.Classes.UIO.ConjuntoBarraAcciones;// Created by Hanto on 06/05/2014.

import Data.MiscData;
import Model.Classes.AbstractModel;
import Model.Classes.Acciones.Accion;
import Model.Classes.UIO.Input.Keybinds;
import Model.DTO.BarraAccionesDTO;
import com.badlogic.gdx.utils.Array;

public class BarraAcciones extends AbstractModel implements ListaAccionesBI
{
    private int iD;
    private Array<Array<Casilla>> barraAcciones = new Array<>();

    private Keybinds keybinds;

    public static class Casilla
    {
        public Accion accion = null;
        public String keybind;
        public int keycode;
    }

    public int getID()          { return iD; }
    public void setID(int i)    { iD = i; }
    public int getNumFilas()    { return barraAcciones.size; }
    public int getNumColumnas() { return barraAcciones.first().size; }

    //CONSTRUCTOR:
    public BarraAcciones(Keybinds keybinds, int id, int numFilas, int numColumnas)
    {
        this.keybinds = keybinds;
        this.iD = id;

        for (int i=0; i<numFilas; i++)
        {
            Array<Casilla> array = new Array<>();
            for (int j=0; j<numColumnas; j++)
            {
                Casilla casilla = new Casilla();
                casilla.accion = null;

                array.add(casilla);
            }
            barraAcciones.add(array);
        }
    }





    @Override public String getKeybind (int posX, int posY)
    {   return barraAcciones.get(posY).get(posX).keybind; }

    public int getKeycode (int posX, int posY)
    {   return barraAcciones.get(posY).get(posX).keycode; }


    public void setKeybind (int posX, int posY, int keycode)
    {   barraAcciones.get(posY).get(posX).keybind = MiscData.keycodeNames.get(keycode); }

    public void setBind (int keycode, Accion accion)
    {   keybinds.salvarKeybind(keycode, accion.getID()); }

    public void eliminarBind (int keycode)
    {   keybinds.eliminarKeybind(keycode); }

    @Override public Accion getAccion(int posX, int posY)
    {   return barraAcciones.get(posY).get(posX).accion; }

    @Override public void setKeycode (int posX, int posY, int keycode)
    {
        eliminarKeycode(keycode);

        keybinds.eliminarKeybind(getKeycode(posX, posY));
        barraAcciones.get(posY).get(posX).keycode = keycode;
        setKeybind(posX, posY, keycode);
        if (getAccion(posX, posY) != null) setBind(keycode, getAccion(posX, posY));

        Object setKeycode = new BarraAccionesDTO.SetAccionDTO(posX, posY);
        notificarActualizacion("barraAccionRebindear", null, setKeycode);
    }

    @Override public void eliminarKeycode(int keycode)
    {
        for (int y=0; y< barraAcciones.size; y++)
        {
            for (int x=0; x< barraAcciones.get(y).size; x++)
            {
                if (barraAcciones.get(y).get(x).keycode == keycode)
                {
                    barraAcciones.get(y).get(x).keycode = 0;
                    barraAcciones.get(y).get(x).keybind = "";

                    Object setKeycode = new BarraAccionesDTO.SetAccionDTO(x,y);
                    notificarActualizacion("eliminarKeycode", null, setKeycode);
                }
            }
        }
    }



    @Override public void setAccion(int posX, int posY, Accion accion)
    {
        barraAcciones.get(posY).get(posX).accion = accion;
        setBind(getKeycode(posX, posY), accion);

        Object setAccionDTO = new BarraAccionesDTO.SetAccionDTO(posX, posY);
        notificarActualizacion("setAccion", null, setAccionDTO);
    }

    @Override public void eliminarAccion(int posX, int posY)
    {
        barraAcciones.get(posY).get(posX).accion = null;
        eliminarBind(getKeycode(posX, posY));

        Object removeAccionDTO = new BarraAccionesDTO.EliminarAccionDTO(posX, posY);
        notificarActualizacion("eliminarAccion", null, removeAccionDTO);
    }

    @Override public void eliminarFila()
    {
        if (barraAcciones.size <= 1) return;

        Array<Casilla> array = barraAcciones.peek();
        for (int i=0; i<array.size; i++)
        {   keybinds.eliminarKeybind(array.get(i).keycode); }

        barraAcciones.removeIndex(barraAcciones.size-1);

        Object eliminarFilaDTO = new BarraAccionesDTO.EliminarFilaDTO();
        notificarActualizacion("eliminarFila", null, eliminarFilaDTO);
    }

    @Override public void añadirFila()
    {
        Array<Casilla> array = new Array<>();
        for (int i=0; i<barraAcciones.first().size; i++)
        {
            Casilla casilla = new Casilla();
            casilla.accion = null;
            array.add(casilla);
        }
        barraAcciones.add(array);

        Object añadirFilaDTO = new BarraAccionesDTO.AñadirFilaDTO();
        notificarActualizacion("añadirFila", null, añadirFilaDTO);
    }

    @Override public void eliminarColumna()
    {
        if (barraAcciones.first().size <= 1) return;

        for (int y=0; y<barraAcciones.size; y++)
        {   keybinds.eliminarKeybind(barraAcciones.get(y).pop().keycode);}

        Object eliminarColumnaDTO = new BarraAccionesDTO.EliminarColumnaDTO();
        notificarActualizacion("eliminarColumna", null, eliminarColumnaDTO);
    }

    @Override public void añadirColumna()
    {
        for (int y=0; y<barraAcciones.size; y++)
        {
            Casilla casilla = new Casilla();
            barraAcciones.get(y).add(casilla);
        }

        Object añadirColumnaDTO = new BarraAccionesDTO.AñadirColumnaDTO();
        notificarActualizacion("añadirColumna", null, añadirColumnaDTO);
    }
}
