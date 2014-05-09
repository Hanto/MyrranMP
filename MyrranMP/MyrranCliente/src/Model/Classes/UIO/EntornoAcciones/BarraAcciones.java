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
    //private Map<Integer,Casilla> barraAcciones = new HashMap<>();

    private Array<Array<Casilla>> barraAcciones = new Array<>();

    private Keybinds keybinds;

    public static class Casilla
    {
        public Accion accion;
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

        /*for (int i=0; i< numFilas; i++)
        {
            for (int j=0; j< numColumnas; j++)
            {
                Casilla casilla = new Casilla();
                casilla.accion = null;

                barraAcciones.put(i*100+j,casilla);
            }
        }*/

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



    public Accion getAccion(int posX, int posY)
    {   return barraAcciones.get(posY).get(posX).accion; }

    public String getKeybind (int posX, int posY)
    {   return barraAcciones.get(posY).get(posX).keybind; }

    public int getKeycode (int posX, int posY)
    {   return barraAcciones.get(posY).get(posX).keycode; }


    public void setKeybind (int posX, int posY, int keycode)
    {   barraAcciones.get(posY).get(posX).keybind = MiscData.keycodeNames.get(keycode); }

    public void setBind (int keycode, Accion accion)
    {   keybinds.listaDeBinds.put(keycode, accion.getID()); }

    public void eliminarBind (int keycode)
    {   keybinds.listaDeBinds.remove(keycode); }

    public void setKeycode (int posX, int posY, int keycode)
    {
        eliminarKeycode(keycode);

        keybinds.listaDeBinds.remove(getKeycode(posX, posY));
        barraAcciones.get(posY).get(posX).keycode = keycode;
        setKeybind(posX, posY, keycode);
        if (getAccion(posX, posY) != null) setBind(keycode, getAccion(posX, posY));

        Object setKeycode = new BarraAccionesDTO.SetAccionDTO(posX, posY);
        notificarActualizacion("barraAccionRebindear", null, setKeycode);
    }

    public void eliminarKeycode(int keycode)
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



    public void setAccion(int posX, int posY, Accion accion)
    {
        barraAcciones.get(posY).get(posX).accion = accion;
        setBind(getKeycode(posX, posY), accion);

        Object setAccionDTO = new BarraAccionesDTO.SetAccionDTO(posX, posY);
        notificarActualizacion("setAccion", null, setAccionDTO);
    }

    public void eliminarAccion(int posX, int posY)
    {
        barraAcciones.get(posY).get(posX).accion = null;
        eliminarBind(getKeycode(posX, posY));

        Object removeAccionDTO = new BarraAccionesDTO.EliminarAccionDTO(posX, posY);
        notificarActualizacion("eliminarAccion", null, removeAccionDTO);
    }


/*
    public void eliminarFila()
    {
        int posX = 0;
        for (Iterator<Casilla> iterator = barraAcciones.iterator(); iterator.hasNext();)
        {
            Casilla casilla = iterator.next();
            if (posX+numCasillas >= barraAcciones.size)
            {
                keybinds.listaDeBinds.remove(casilla.keycode);
                iterator.remove();

                Object eliminarCasillaDTO = new BarraAccionesDTO.EliminarCasillaDTO(posX);
                notificarActualizacion("eliminarCasillas", null, eliminarCasillaDTO);
            }
            posX++;
        }
    }*/
}
