package Controller;// Created by Hanto on 08/04/2014.

import DTO.NetDTO;
import Interfaces.UI.BarraAcciones.BarraAccionesI;
import Interfaces.UI.BarraAcciones.ListaAccionesI;
import Interfaces.UI.ControladorUI;
import Model.GameState.Mundo;
import Model.GameState.UI;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

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

        inputMultiplexer.addProcessor(vista.getMundoView());
        inputMultiplexer.addProcessor(vista.getUiView());
        inputMultiplexer.addProcessor(ui.getInputManager());
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
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(1, 0, "Heal", 9);

        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(1, 1, "IrNorte", 51);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(1, 2, "IrSur", 47);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(2, 2, "IrEste", 32);
        ui.conjuntoBarraAcciones.getBarraAcciones(0).setAccion(0, 2, "IrOeste", 29);

        moverPPC(mundo.getPlayer().getConnectionID(), 21000, 20000);
    }

    //LibGDX:
    public void render (float delta)                                                    { vista.render(delta); }
    public void dispose()                                                               { vista.dispose(); }
    public void resize(int anchura, int altura)                                         { vista.resize(anchura, altura);}

    //Kryo:
    public void enviarAServidor(Object obj)                                             { cliente.enviarAServidor(obj); }

    //Entidades:
    public void añadirPlayer(int connectionID)
    {   mundo.getPlayer().setConnectionID(connectionID); }

    public void modificarHPsPPC(int connectionID, float HPs)
    {
        if (connectionID == mundo.getPlayer().getConnectionID()) mundo.getPlayer().modificarHPs(HPs);
        else mundo.getPC(connectionID).modificarHPs(HPs);
    }
    public void moverPPC(int connectionID, float x, float y)
    {
        if (connectionID == mundo.getPlayer().getConnectionID()) mundo.getPlayer().setPosition(x, y);
        else mundo.getPC(connectionID).setPosition(x, y);
    }
    public void cambiarAnimacionPPC(int connectionID, int numAnimacion)
    {
        if (connectionID == mundo.getPlayer().getConnectionID()) mundo.getPlayer().setNumAnimacion(numAnimacion);
        else mundo.getPC(connectionID).setNumAnimacion(numAnimacion);
    }
    public void eliminarPPC(int connectionID)
    {
        if (connectionID == mundo.getPlayer().getConnectionID()) {}
        else mundo.eliminarPC(connectionID);
    }
    public void actualizarPPC(NetDTO.ActualizarPPC updatePlayer)
    {
        if (updatePlayer.connectionID == mundo.getPlayer().getConnectionID())
        {
            mundo.getPlayer().setNombre(updatePlayer.nombre);
            mundo.getPlayer().setNivel(updatePlayer.nivel);
            mundo.getPlayer().setActualHPs(updatePlayer.actualHPs);
            mundo.getPlayer().setMaxHPs(updatePlayer.maxHPs);
            mundo.getPlayer().setPosition(updatePlayer.x, updatePlayer.y);
            mundo.getPlayer().setNumAnimacion(updatePlayer.numAnimacion);
        }
        else
        {
            if (mundo.getPC(updatePlayer.connectionID) == null)
            {   mundo.añadirPC(updatePlayer.connectionID, updatePlayer.x, updatePlayer.y); }

            mundo.getPC(updatePlayer.connectionID).setNombre(updatePlayer.nombre);
            mundo.getPC(updatePlayer.connectionID).setNivel(updatePlayer.nivel);
            mundo.getPC(updatePlayer.connectionID).setActualHPs(updatePlayer.actualHPs);
            mundo.getPC(updatePlayer.connectionID).setMaxHPs(updatePlayer.maxHPs);
            mundo.getPC(updatePlayer.connectionID).setPosition(updatePlayer.x, updatePlayer.y);
            mundo.getPC(updatePlayer.connectionID).setNumAnimacion(updatePlayer.numAnimacion);
        }
    }

    public void actualizarMapTilesCargados (NetDTO.MapTilesAdyacentesEnCliente ady)     { mundo.mapTilesCargados = ady.mapaAdyacencias; }
    public void actualizarMapa(NetDTO.ActualizarMapa mapaServidor)                      { mundo.actualizarMapa(mapaServidor); }
    public void setTerreno(int celdaX, int celdaY, int numCapa, short iDTerreno)        { mundo.getMapa().setTerreno(celdaX, celdaY, numCapa, iDTerreno); }
    public void aplicarZoom(int incrementoZoom)                                         { vista.aplicarZoom(incrementoZoom); }

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
