package Model.Classes.UIO;// Created by Hanto on 06/05/2014.

import Model.Classes.AbstractModel;
import Model.Classes.Acciones.Accion;
import com.badlogic.gdx.utils.Array;

public class BarraAcciones extends AbstractModel
{
    private Array<Casilla> barraSpells;
    private Keybinds keybinds;

    public static class Casilla
    {
        public Accion accion;
        public String keybind;
        public int keycode;
    }

    //CONSTRUCTOR:
    public BarraAcciones(Keybinds keybinds, int tamaño)
    {
        this.keybinds = keybinds;
        barraSpells = new Array<>(tamaño);

        for (int i=0; i< tamaño; i++)
        {
            Casilla casilla = new Casilla();

            if (i<9) { casilla.keybind = String.valueOf(i+1); casilla.keycode = i+8; }
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

    public void setKeybind (int posicion, String keybind)
    {   barraSpells.get(posicion).keybind = keybind; }

    public void setKeycode (int posicion, int keycode)
    {   barraSpells.get(posicion).keycode = keycode; }

    public void setAccion(int posicion, Accion accion)
    {
        barraSpells.get(posicion).accion = accion;
        keybinds.listaDeBinds.put(barraSpells.get(posicion).keycode, accion.getID());
    }

    public void removeAccion (int posicion)
    {
        barraSpells.get(posicion).accion = null;
        keybinds.listaDeBinds.remove(barraSpells.get(posicion).keycode);
    }

    public void moverAccion(int posicionOrigen, int posicionDestino)
    {
        Accion accionOrigen = barraSpells.get(posicionOrigen).accion;
        Accion accionDestino = barraSpells.get(posicionDestino).accion;

        if (accionDestino == null) removeAccion(posicionOrigen);
        else keybinds.listaDeBinds.put(barraSpells.get(posicionOrigen).keycode, accionDestino.getID());

        if (accionOrigen == null) removeAccion(posicionDestino);
        else  keybinds.listaDeBinds.put(barraSpells.get(posicionDestino).keycode, accionOrigen.getID());

        barraSpells.get(posicionOrigen).accion = accionDestino;
        barraSpells.get(posicionDestino).accion = accionOrigen;
    }
}
