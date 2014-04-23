package Controller;// Created by Hanto on 08/04/2014.

import Controller.Input.PlayerEstado;
import Controller.Input.PlayerGestures;
import Controller.Input.PlayerIO;
import Controller.Input.PlayerMouseKey;
import Model.DTO.NetDTO;
import Model.Mobiles.Mundo;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

//CLIENTE:
public class Controlador
{
    protected Cliente cliente;
    protected Mundo mundo;
    protected Vista vista;

    //Input:
    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);
    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public Controlador (Mundo mundo)
    {
        this.mundo = mundo;
        vista = new Vista(this, mundo);

        inputMultiplexer.addProcessor(new GestureDetector(new PlayerGestures()));
        inputMultiplexer.addProcessor(new PlayerMouseKey(playerEstado, this));
        Gdx.input.setInputProcessor(inputMultiplexer);

        cliente = new Cliente(this);

        añadirPlayer(cliente.getID());
    }

    public void render (float delta)                                    { vista.render(delta); }
    public void dispose()                                               { vista.dispose(); }

    public void enviarAServidor(Object o)                               { cliente.enviarAServidor(o); }
    public int  getConnID()                                             { return cliente.getID(); }

    public void añadirPlayer(int connectionID)                          { mundo.añadirPlayer(connectionID);}
    public void actualizarPlayer(NetDTO.ActualizarPlayer updatePlayer)  { mundo.actualizarPlayer (updatePlayer); }
    public void eliminarPlayer()                                        { mundo.eliminarPlayer();}
    public void aplicarInputAPlayer()
    {   mundo.getPlayer().setInput(playerOutput); }

    public void moverPlayer(float x, float y)
    {   mundo.getPlayer().setPosition(x, y); }

    public void añadirPC(int connectionID, float x, float y, int numAnimacion)
    {   mundo.añadirPC(connectionID, x, y);
        mundo.getPC(connectionID).setAnimacion(numAnimacion);
    }
    public void eliminarPC(int connectionID)
    { mundo.eliminarPC(connectionID); }

    public void moverPC(int connectionID, float x, float y)
    {   mundo.getPC(connectionID).setPosition(x, y); }

    public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mundo.getPC(connectionID).setAnimacion(numAnimacion); }

    public void setTerreno(int celdaX, int celdaY, int numCapa, int iDTerreno)
    {   mundo.mapa.setTerreno(celdaX, celdaY, numCapa, iDTerreno); }

    public void aplicarZoom(int incrementoZoom)                         { vista.aplicarZoom(incrementoZoom); }
    public void addInputProcessor(Stage stage)                          { inputMultiplexer.addProcessor(stage); }
}
