package Model.Classes.UI;// Created by Hanto on 06/05/2014.

import Model.Classes.Acciones.Accion;
import Controller.Input.Binds.Keybinds;
import Model.Classes.AbstractModel;

public class BarraAcciones extends AbstractModel
{
    public Casilla[] barraSpells;
    public Keybinds keybinds;

    public static class Casilla
    {
        public Accion accion;
        public String keybind;
        public int keycode;
    }

    public BarraAcciones(int filas, int columnas, Keybinds keybinds)
    {
        this.keybinds = keybinds;
        barraSpells = new Casilla[filas*columnas];

        for (int i=0; i< barraSpells.length; i++)
        {
            Casilla casilla = new Casilla();

            if (i<9) { casilla.keybind = String.valueOf(i+1); casilla.keycode = i+8; }
            casilla.accion = null;

            barraSpells[i] = casilla;
        }
    }

    public void setAccion(int posicion, Accion accion)
    {
        barraSpells[posicion].accion = accion;
        keybinds.listaDeBinds.put(barraSpells[posicion].keycode, accion.getID());
    }

    public void removeAccion (int posicion)
    {
        barraSpells[posicion].accion = null;
        keybinds.listaDeBinds.remove(barraSpells[posicion].keycode);
    }

    public void moverAccion(int posicionOrigen, int posicionDestino)
    {
        Accion accionOrigen = barraSpells[posicionOrigen].accion;
        Accion accionDestino = barraSpells[posicionDestino].accion;

        if (accionDestino == null) removeAccion(posicionOrigen);
        else keybinds.listaDeBinds.put(barraSpells[posicionOrigen].keycode, accionDestino.getID());

        if (accionOrigen == null) removeAccion(posicionDestino);
        else  keybinds.listaDeBinds.put(barraSpells[posicionDestino].keycode, accionOrigen.getID());

        barraSpells[posicionOrigen].accion = accionDestino;
        barraSpells[posicionDestino].accion = accionOrigen;
    }
}
