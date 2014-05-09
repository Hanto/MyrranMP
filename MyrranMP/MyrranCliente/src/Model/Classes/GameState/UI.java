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

    public void añadirBarraAcciones(int filas, int columnas)
    {   entornoAcciones.añadirBarraAcciones(filas, columnas); }

    public void moverAccion (int numBarraOrigen, int posXOrigen, int posYOrigen, int numBarraDestino, int posXDestino, int posYDestino)
    {   entornoAcciones.moverAccion (numBarraOrigen, posXOrigen, posYOrigen, numBarraDestino, posXDestino, posYDestino); }
    public void setKeyCode (int numBarra, int posX, int posY, int keycode)
    {   entornoAcciones.setKeycode(numBarra, posX, posY, keycode);}

    public void añadirColumna (int numBarra)
    {   entornoAcciones.añadirColumna(numBarra);}
    public void eliminarColumna (int numBarra)
    {   entornoAcciones.eliminarColuna(numBarra);}
    public void añadirFila (int numBarra)
    {   entornoAcciones.añadirFila(numBarra); }
    public void eliminarFila (int numBarra)
    {   entornoAcciones.eliminarFila(numBarra); }


    //Provisional:
    public void añadirAccionesEnBarra(int numBarra)
    {   entornoAcciones.añadirAccionesEnBarra(numBarra); }

}
