package Model.GameState;// Created by Hanto on 06/05/2014.

import Controller.Controlador;
import Interfaces.UI.BarraAcciones.BarraAccionesI;
import Interfaces.UI.BarraAcciones.ListaAccionesI;
import Model.Classes.Acciones.AccionFactory;
import Model.Classes.Input.InputManager;
import Model.Classes.Mobiles.Player;
import Model.Classes.UI.ConjuntoBarraAcciones;
import Model.Classes.UI.BarraTerrenos;
import Model.Classes.Input.PlayerEstado;
import Model.Classes.Input.PlayerIO;


public class UI
{
    protected InputManager inputManager;
    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);

    public ConjuntoBarraAcciones conjuntoBarraAcciones;
    public BarraTerrenos barraTerrenos;

    public InputManager getInputManager()       { return inputManager; }


    public UI (Player player, Controlador controlador)
    {
        inputManager = new InputManager(player, playerEstado, controlador);
        conjuntoBarraAcciones = new ConjuntoBarraAcciones(player, inputManager);
        barraTerrenos  = new BarraTerrenos(player);



        inputManager.añadirAccion(AccionFactory.accionComando.IRNORTE.nuevo());
        inputManager.añadirAccion(AccionFactory.accionComando.IRSUR.nuevo());
        inputManager.añadirAccion(AccionFactory.accionComando.IRESTE.nuevo());
        inputManager.añadirAccion(AccionFactory.accionComando.IROESTE.nuevo());
        inputManager.añadirAccion(AccionFactory.accionSpell.SELECCIONARSPELL.nuevo("Terraformar"));
        inputManager.añadirAccion(AccionFactory.accionSpell.SELECCIONARSPELL.nuevo("Heal"));
    }


    public void añadirBarraAcciones(int filas, int columnas)
    {   conjuntoBarraAcciones.añadirBarraAcciones(filas, columnas); }
    public void eliminarBarraAcciones(BarraAccionesI barra)
    {   conjuntoBarraAcciones.eliminarBarraAccion(barra); }

    public void moverAccion (ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino)
    {   conjuntoBarraAcciones.moverAccion(barraOrigen, posXOrigen, posYOrigen, barraDestino, posXDestino, posYDestino);}
    public void setKeyCode (BarraAccionesI barra, int posX, int posY, int keycode)
    {   conjuntoBarraAcciones.setKeycode(barra, posX, posY, keycode); }

    public void barraTerrenosMoverTerreno(int posOrigen, int posDestino)
    {   barraTerrenos.moverTerreno(posOrigen, posDestino); }
}
