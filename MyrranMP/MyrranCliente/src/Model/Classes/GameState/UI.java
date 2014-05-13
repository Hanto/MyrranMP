package Model.Classes.GameState;// Created by Hanto on 06/05/2014.

import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.ConjuntoBarraAcciones.ConjuntoBarraAcciones;
import Model.Classes.UIO.ConjuntoBarraAcciones.ListaAccionesBI;
import Model.Classes.UIO.ConjuntoBarraAcciones.ListaAccionesI;
import Model.Classes.UIO.Keybinds;
import Model.Classes.UIO.PlayerEstado;
import Model.Classes.UIO.PlayerIO;


public class UI
{
    public Keybinds keybinds;
    public ConjuntoBarraAcciones conjuntoBarraAcciones;

    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);


    public UI (Player player)
    {
        keybinds = new Keybinds(player, playerEstado);
        conjuntoBarraAcciones = new ConjuntoBarraAcciones(keybinds);
    }

    public void añadirBarraAcciones(int filas, int columnas)
    {   conjuntoBarraAcciones.añadirBarraAcciones(filas, columnas); }


    public void moverAccion (ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino)
    {   conjuntoBarraAcciones.moverAccion(barraOrigen, posXOrigen, posYOrigen, barraDestino, posXDestino, posYDestino);}
    public void setKeyCode (ListaAccionesBI barra, int posX, int posY, int keycode)
    {   conjuntoBarraAcciones.setKeycode(barra, posX, posY, keycode); }


    //Provisional:
    public void añadirAccionesEnBarra(int numBarra)
    {   conjuntoBarraAcciones.añadirAccionesEnBarra(conjuntoBarraAcciones.getBarraAcciones(numBarra)); }

}
