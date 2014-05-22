package Controller;// Created by Hanto on 08/04/2014.

import Controller.Input.PlayerGestures;
import Controller.Input.PlayerMouseKeyI;
import Controller.Interfaces.ControladorBarraAccionI;
import Controller.Interfaces.ControladorBarraTerrenosI;
import DTO.NetDTO;
import Model.Classes.Geo.Mapa;
import Model.Classes.Mobiles.Player;
import Model.Classes.UI.BarraAcciones.BarraAccionesI;
import Model.Classes.UI.BarraAcciones.ListaAccionesI;
import Model.GameState.Mundo;
import Model.GameState.UI;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

//CLIENTE:
public class Controlador implements ControladorBarraAccionI, ControladorBarraTerrenosI
{
    protected Cliente cliente;
    protected Player player;
    protected UI ui;
    protected Mundo mundo;
    protected Vista vista;

    //Input:
    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public Controlador (Player player, Mundo mundo)
    {
        this.player = player;
        this.mundo = mundo;

        mundo.mapa = new Mapa(player);

        ui = new UI(player, this);

        vista = new Vista(this, player, ui, mundo);

        inputMultiplexer.addProcessor(new GestureDetector(new PlayerGestures()));
        inputMultiplexer.addProcessor(new PlayerMouseKeyI(this));
        Gdx.input.setInputProcessor(inputMultiplexer);

        cliente = new Cliente(this);

        añadirPlayer(cliente.getID());

        añadirBarraAcciones(3, 10);
        ui.añadirAccionesEnBarra(0);

        //moverPlayer(21000,22600);
    }

    public void render (float delta)                                    { vista.render(delta); }
    public void dispose()                                               { vista.dispose(); }
    public void resize(int anchura, int altura)                         { vista.resize(anchura, altura);}

    public void enviarAServidor(Object o)                               { cliente.enviarAServidor(o); }
    public int  getConnID()                                             { return cliente.getID(); }
    public void añadirPlayer(int connectionID)                          { player.setConnectionID(connectionID); }

    public void actualizarPlayer(NetDTO.ActualizarPlayer updatePlayer)
    {
        player.setNombre(updatePlayer.nombre);
        player.setNivel(updatePlayer.nivel);
        player.setActualHPs(updatePlayer.actualHPs);
        player.setMaxHPs(updatePlayer.maxHPs);
        //player.setPosition(updatePlayer.x, updatePlayer.y);
    }
    public void añadirPC(int connectionID, float x, float y, int numAnimacion)
    {   mundo.añadirPC(connectionID, x, y);
        mundo.getPC(connectionID).setAnimacion(numAnimacion);
    }
    public void moverPlayer(float x, float y)                                           { player.setPosition(x, y); }
    public void eliminarPC(int connectionID)                                            { mundo.eliminarPC(connectionID); }
    public void moverPC(int connectionID, float x, float y)                             { mundo.getPC(connectionID).setPosition(x, y); }
    public void cambiarAnimacionPC(int connectionID, int numAnimacion)                  { mundo.getPC(connectionID).setAnimacion(numAnimacion); }

    public void actualizarMapa(NetDTO.ActualizarMapa mapaServidor)                      { mundo.actualizarMapa(mapaServidor); }
    public void setTerreno(int celdaX, int celdaY, int numCapa, int iDTerreno)          { mundo.mapa.setTerreno(celdaX, celdaY, numCapa, iDTerreno); }
    public void aplicarZoom(int incrementoZoom)                                         { vista.aplicarZoom(incrementoZoom); }
    public void addInputProcessor(Stage stage)                                          { inputMultiplexer.addProcessor(stage); }
    public void procesarKeyDown(int keycode)                                            { ui.keybinds.keyDown(keycode); }
    public void procesarKeyUp(int keycode)                                              { ui.keybinds.keyUp(keycode); }
    public void procesarTouchDown(int screenX, int screenY, int pointer, int button)    { ui.keybinds.touchDown(screenX, screenY, pointer, button); }
    public void procesarTouchUp(int screenX, int screenY, int pointer, int button)      { ui.keybinds.touchUp(screenX, screenY, pointer, button); }
    public void procesarTouchDragged(int screenX, int screenY, int pointer)             { ui.keybinds.touchDragged(screenX, screenY, pointer); }

    //BarrasAccion:
    @Override public void añadirBarraAcciones(int filas, int columnas)                          { ui.añadirBarraAcciones(filas, columnas); }
    @Override public void eliminarBarraAcciones(BarraAccionesI barraAcciones)                   { ui.eliminarBarraAcciones(barraAcciones); }
    @Override public void barraAñadirFila (BarraAccionesI barra, int numFilas)                  { barra.añadirFila(numFilas); }
    @Override public void barraAñadirColumna(BarraAccionesI barra, int numColumnas)             { barra.añadirColumna(numColumnas); }
    @Override public void barraEliminarFila (BarraAccionesI barra, int numFilas)                { barra.eliminarFila(numFilas); }
    @Override public void barraEliminarColumna (BarraAccionesI barra, int numColumnas)          { barra.eliminarColumna(numColumnas); }
    @Override public void barraAccionMoverAccion(ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino)
    {   ui.moverAccion(barraOrigen, posXOrigen, posYOrigen, barraDestino, posXDestino, posYDestino);}
    @Override public void barraAccionRebindear(BarraAccionesI barra, int posX, int posY, int keycode)
    {   ui.setKeyCode(barra, posX, posY, keycode);}

    //BarraTerrenos:
    @Override public void mostrarBarraTerrenos()                                                { vista.getUiView().mostrarBarraTerreno(); }
    @Override public void ocultarBarraTerrenos()                                                { vista.getUiView().ocultarBarraTerreno(); }
    @Override public void barraTerrenosMoverTerreno(int posOrigen, int posDestino)              { ui.barraTerrenosMoverTerreno(posOrigen, posDestino); }
}
