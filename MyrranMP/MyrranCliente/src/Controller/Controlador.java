package Controller;// Created by Hanto on 08/04/2014.

import Controller.Input.PlayerGestures;
import Controller.Input.PlayerMouseKeys;
import Interfaces.UI.Input.ControladorUI;
import DTO.NetDTO;
import Interfaces.UI.BarraAcciones.BarraAccionesI;
import Interfaces.UI.BarraAcciones.ListaAccionesI;
import Model.GameState.Mundo;
import Model.GameState.UI;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

//CLIENTE:
public class Controlador implements ControladorUI
{
    protected Cliente cliente;

    protected Mundo mundo;
    protected UI ui;
    protected Vista vista;

    //Input:
    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public Controlador (Mundo mundo)
    {
        this.mundo = mundo;

        ui = new UI(mundo.getPlayer(), this);
        vista = new Vista(this, ui, mundo);

        inputMultiplexer.addProcessor(new GestureDetector(new PlayerGestures()));
        inputMultiplexer.addProcessor(new PlayerMouseKeys(this));
        Gdx.input.setInputProcessor(inputMultiplexer);

        cliente = new Cliente(this);

        añadirPlayer(cliente.getID());


        ui.añadirBarraAcciones(3, 9);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(1, 0, 9);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(2, 0, 10);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(3, 0, 11);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(4, 0, 12);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(5, 0, 13);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(6, 0, 14);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(7, 0, 15);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setKeycode(8, 0, 16);

        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(0, 0, "Terraformar", 8);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(1, 1, "IrNorte", 51);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(1, 2, "IrSur", 47);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(2, 2, "IrEste", 32);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(0, 2, "IrOeste", 29);


        moverPlayer(21000,22400);
    }

    public void render (float delta)                                                    { vista.render(delta); }
    public void dispose()                                                               { vista.dispose(); }
    public void resize(int anchura, int altura)                                         { vista.resize(anchura, altura);}

    public void enviarAServidor(Object o)                                               { cliente.enviarAServidor(o); }
    public void añadirPlayer(int connectionID)                                          { mundo.getPlayer().setConnectionID(connectionID); }

    public void actualizarPlayer(NetDTO.ActualizarPlayer updatePlayer)
    {
        mundo.getPlayer().setNombre(updatePlayer.nombre);
        mundo.getPlayer().setNivel(updatePlayer.nivel);
        mundo.getPlayer().setActualHPs(updatePlayer.actualHPs);
        mundo.getPlayer().setMaxHPs(updatePlayer.maxHPs);
        //player.setPosition(updatePlayer.x, updatePlayer.y);
    }
    public void añadirPC(int connectionID, float x, float y, int numAnimacion)
    {   mundo.añadirPC(connectionID, x, y);
        mundo.getPC(connectionID).setAnimacion(numAnimacion);
    }
    public void moverPlayer(float x, float y)                                           { mundo.getPlayer().setPosition(x, y); }
    public void eliminarPC(int connectionID)                                            { mundo.eliminarPC(connectionID); }
    public void moverPC(int connectionID, float x, float y)                             { mundo.getPC(connectionID).setPosition(x, y); }
    public void cambiarAnimacionPC(int connectionID, int numAnimacion)                  { mundo.getPC(connectionID).setAnimacion(numAnimacion); }

    public void actualizarMapTilesCargados (NetDTO.MapTilesAdyacentesEnCliente ady)     { mundo.mapTilesCargados = ady.mapaAdyacencias; }
    public void actualizarMapa(NetDTO.ActualizarMapa mapaServidor)                      { mundo.actualizarMapa(mapaServidor); }
    public void setTerreno(int celdaX, int celdaY, int numCapa, short iDTerreno)        { mundo.getMapa().setTerreno(celdaX, celdaY, numCapa, iDTerreno); }
    public void aplicarZoom(int incrementoZoom)                                         { vista.aplicarZoom(incrementoZoom); }

    public void addInputProcessor(Stage stage)                                          { inputMultiplexer.addProcessor(stage); }
    public void procesarKeyDown(int keycode)                                            { ui.getKeybinds().keyDown(keycode); }
    public void procesarKeyUp(int keycode)                                              { ui.getKeybinds().keyUp(keycode); }
    public void procesarTouchDown(int screenX, int screenY, int pointer, int button)    { ui.getKeybinds().touchDown(screenX, screenY, pointer, button); }
    public void procesarTouchUp(int screenX, int screenY, int pointer, int button)      { ui.getKeybinds().touchUp(screenX, screenY, pointer, button); }
    public void procesarTouchDragged(int screenX, int screenY, int pointer)             { ui.getKeybinds().touchDragged(screenX, screenY, pointer); }

    //BarraTerrenos:
    @Override public void mostrarBarraTerrenos()                                        { vista.getUiView().mostrarBarraTerreno(); }
    @Override public void ocultarBarraTerrenos()                                        { vista.getUiView().ocultarBarraTerreno(); }
    @Override public void barraTerrenosMoverTerreno(int posOrigen, int posDestino)      { ui.barraTerrenosMoverTerreno(posOrigen, posDestino); }

    //BarrasAccion:
    @Override public void añadirBarraAcciones(int filas, int columnas)                  { ui.añadirBarraAcciones(filas, columnas); }
    @Override public void eliminarBarraAcciones(BarraAccionesI barraAcciones)           { ui.eliminarBarraAcciones(barraAcciones); }
    @Override public void barraAñadirFila (BarraAccionesI barra, int numFilas)          { barra.añadirFila(numFilas); }
    @Override public void barraAñadirColumna(BarraAccionesI barra, int numColumnas)     { barra.añadirColumna(numColumnas); }
    @Override public void barraEliminarFila (BarraAccionesI barra, int numFilas)        { barra.eliminarFila(numFilas); }
    @Override public void barraEliminarColumna (BarraAccionesI barra, int numColumnas)  { barra.eliminarColumna(numColumnas); }
    @Override public void barraAccionMoverAccion(ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino,int posXDestino, int posYDestino)
    {   ui.moverAccion(barraOrigen, posXOrigen, posYOrigen, barraDestino, posXDestino, posYDestino);}
    @Override public void barraAccionRebindear(BarraAccionesI barra, int posX, int posY, int keycode)
    {   ui.setKeyCode(barra, posX, posY, keycode);}
}
