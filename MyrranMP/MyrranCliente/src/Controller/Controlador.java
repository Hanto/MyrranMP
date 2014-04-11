package Controller;// Created by Hanto on 08/04/2014.

import Controller.Input.PlayerEstado;
import Controller.Input.PlayerGestures;
import Controller.Input.PlayerIO;
import Controller.Input.PlayerMouseKey;
import Controller.Network.NetClient;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.MundoModelC;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

//CLIENTE:
public class Controlador implements ControladorCliente
{
    protected NetClient cliente;
    protected MundoModelC mundo;
    protected Vista vista;

    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();

    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);

    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public Controlador (MundoModelC mundo)
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

    @Override public MundoModel getMundo()                              { return mundo; }
    @Override public NetClient getNetIO()                               { return cliente; }
    @Override public PlayerIO getPlayerIO()                             { return playerOutput; }

    @Override public void añadirPlayer(int connectionID)                { mundo.añadirPlayer(connectionID);}
    @Override public void eliminarPlayer()                              { mundo.eliminarPlayer();}
    @Override public void moverPlayer(float x, float y)                 { mundo.moverPlayer(x, y);}
    @Override public void aplicarPlayerIO()                             { mundo.getPlayer().setInput(playerOutput); }

    @Override public void añadirPC(int connectionID, float x, float y)  { mundo.añadirPC(connectionID, x, y); }
    @Override public void eliminarPC(int connectionID)                  { mundo.eliminarPC(connectionID); }
    @Override public void moverPC(int connectionID, float x, float y)   { mundo.moverPC(connectionID, x, y); }

    @Override public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mundo.cambiarAnimacionPC(connectionID, numAnimacion); }

    @Override public void addInputProcessor(Stage stage)                { inputMultiplexer.addProcessor(stage); }
    @Override public void netUpdater()                                  { }


}
