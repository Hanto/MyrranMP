package Model.Classes.UI.BarraAcciones;// Created by Hanto on 06/05/2014.

import Data.MiscData;
import Interfaces.Model.AbstractModel;
import Interfaces.UI.Acciones.AccionI;
import Interfaces.UI.BarraAcciones.BarraAccionesI;
import Model.Classes.Input.Keybinds;
import Model.DTO.BarraAccionesDTO;
import com.badlogic.gdx.utils.Array;

public class BarraAcciones extends AbstractModel implements BarraAccionesI
{
    private int iD;
    private Array<Array<Casilla>> barraAcciones = new Array<>();

    private Keybinds keybinds;

    public static class Casilla
    {
        public AccionI accion = null;
        public String keybind;
        public int keycode;
    }

    @Override public int getID(){ return iD; }
    public void setID(int i)    { iD = i; }
    public int getNumFilas()    { return barraAcciones.size; }
    public int getNumColumnas() { return (barraAcciones.size == 0) ? 0 : barraAcciones.first().size; }

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

    public void setBind (int keycode, AccionI accion)
    {   keybinds.salvarKeybind(keycode, accion.getID()); }

    public void eliminarBind (int keycode)
    {   keybinds.eliminarKeybind(keycode); }

    @Override public AccionI getAccion(int posX, int posY)
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


    public void setAccion(int posX, int posY, String idAccion, int keycode)
    {
        setAccion(posX, posY, idAccion);
        setKeycode(posX, posY, keycode);
    }

    public void setAccion(int posX, int posY, String idAccion)
    {
        AccionI accion = keybinds.getAccion(idAccion);
        setAccion(posX, posY, accion);
    }

    @Override public void setAccion(int posX, int posY, AccionI accion)
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



    @Override public void eliminar()
    {   eliminarFila(barraAcciones.size); }

    @Override public void eliminarFila(int numFilas)
    {
        for (int i=0; i<numFilas;i++)
            eliminarFila();

        Object eliminarFilaDTO = new BarraAccionesDTO.EliminarFilaDTO(numFilas);
        notificarActualizacion("eliminarFila", null, eliminarFilaDTO);
    }

    @Override public void añadirFila(int numFilas)
    {
        for (int i=0; i<numFilas;i++)
            añadirFila();

        Object añadirFilaDTO = new BarraAccionesDTO.AñadirFilaDTO(numFilas);
        notificarActualizacion("añadirFila", null, añadirFilaDTO);
    }

    @Override public void eliminarColumna(int numColumnas)
    {
        for (int i=0; i<numColumnas;i++)
            eliminarColumna();

        Object eliminarColumnaDTO = new BarraAccionesDTO.EliminarColumnaDTO(numColumnas);
        notificarActualizacion("eliminarColumna", null, eliminarColumnaDTO);
    }

    @Override public void añadirColumna(int numColumnas)
    {
        for (int i=0; i<numColumnas;i++)
            añadirColumna();

        Object añadirColumnaDTO = new BarraAccionesDTO.AñadirColumnaDTO(numColumnas);
        notificarActualizacion("añadirColumna", null, añadirColumnaDTO);
    }

    private void eliminarFila()
    {
        Array<Casilla> array = barraAcciones.peek();
        for (int i=0; i<array.size; i++)
        {   keybinds.eliminarKeybind(array.get(i).keycode); }

        barraAcciones.removeIndex(barraAcciones.size-1);
    }

    private void añadirFila()
    {
        Array<Casilla> array = new Array<>();
        for (int i=0; i<barraAcciones.first().size; i++)
        {
            Casilla casilla = new Casilla();
            casilla.accion = null;
            array.add(casilla);
        }
        barraAcciones.add(array);
    }

    private void eliminarColumna()
    {
        for (int y=0; y<barraAcciones.size; y++)
        {   keybinds.eliminarKeybind(barraAcciones.get(y).pop().keycode);}
    }

    private void añadirColumna()
    {
        for (int y=0; y<barraAcciones.size; y++)
        {
            Casilla casilla = new Casilla();
            barraAcciones.get(y).add(casilla);
        }
    }

}
