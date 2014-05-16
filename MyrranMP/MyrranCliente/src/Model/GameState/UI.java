package Model.GameState;// Created by Hanto on 06/05/2014.

import Controller.Controlador;
import Model.Classes.Mobiles.Player;
import Model.Classes.UI.BarraTerrenos.BarraTerrenos;
import Model.Classes.UI.ConjuntoBarraAcciones.BarraAccionesI;
import Model.Classes.UI.ConjuntoBarraAcciones.ConjuntoBarraAcciones;
import Model.Classes.UI.ConjuntoBarraAcciones.ListaAccionesI;
import Model.Classes.UI.Input.Keybinds;
import Model.Classes.UI.Input.PlayerEstado;
import Model.Classes.UI.Input.PlayerIO;


public class UI
{
    public Keybinds keybinds;
    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);

    public ConjuntoBarraAcciones conjuntoBarraAcciones;
    public BarraTerrenos barraTerrenos = new BarraTerrenos();



    public UI (Player player, Controlador controlador)
    {
        keybinds = new Keybinds(player, playerEstado, controlador);
        conjuntoBarraAcciones = new ConjuntoBarraAcciones(keybinds);
    }

    public void añadirBarraAcciones(int filas, int columnas)
    {   conjuntoBarraAcciones.añadirBarraAcciones(filas, columnas); }
    public void eliminarBarraAcciones(BarraAccionesI barra)
    {   conjuntoBarraAcciones.eliminarBarraAccion(barra); }

    public void moverAccion (ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino)
    {   conjuntoBarraAcciones.moverAccion(barraOrigen, posXOrigen, posYOrigen, barraDestino, posXDestino, posYDestino);}
    public void setKeyCode (BarraAccionesI barra, int posX, int posY, int keycode)
    {   conjuntoBarraAcciones.setKeycode(barra, posX, posY, keycode); }

    public void moverTerreno(int posOrigen, int posDestino)
    {   barraTerrenos.moverTerreno(posOrigen, posDestino); }

    //Provisional:
    public void añadirAccionesEnBarra(int numBarra)
    {   conjuntoBarraAcciones.añadirAccionesEnBarra(conjuntoBarraAcciones.getBarraAcciones(numBarra)); }

}
