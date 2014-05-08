package Model.Classes.GameState;// Created by Hanto on 06/05/2014.

import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.EntornoAcciones.EntornoAcciones;
import Model.Classes.UIO.Keybinds;
import Model.Classes.UIO.PlayerEstado;
import Model.Classes.UIO.PlayerIO;


public class UI
{
    public Keybinds keybinds;
    public EntornoAcciones entornoAcciones;
    //public Array<BarraAcciones>listaDeBarraAcciones = new Array<>();

    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);


    public UI (Player player)

    {   keybinds = new Keybinds(player, playerEstado);
        entornoAcciones = new EntornoAcciones(keybinds);
    }

    public void añadirBarraAcciones(int tamaño)
    {   entornoAcciones.añadirBarraAcciones(tamaño); }

    public void moverAccion (int numBarraOrigen, int posicionOrigen, int numBarraDestino, int posicionDestino)
    {   entornoAcciones.moverAccion (numBarraOrigen, posicionOrigen, numBarraDestino, posicionDestino); }
    public void setKeyCode (int numBarra, int posicion, int keycode)
    {   entornoAcciones.setKeycode(numBarra, posicion, keycode);}

    //Provisional:
    public void añadirAccionesEnBarra(int numBarra)
    {   entornoAcciones.añadirAccionesEnBarra(numBarra); }

}
