package Controller;// Created by Hanto on 08/04/2014.

import Controller.Input.PlayerEstado;
import Controller.Input.PlayerGestures;
import Controller.Input.PlayerIO;
import Controller.Input.PlayerMouseKey;
import Model.DTO.NetDTO;
import Model.Mobiles.MundoModel;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;

//CLIENTE:
public class Controlador
{
    protected Cliente cliente;
    protected MundoModel mundoModel;
    protected Vista vista;

    //Input:
    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);
    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public Controlador (MundoModel mundoModel)
    {
        this.mundoModel = mundoModel;
        vista = new Vista(this, mundoModel);

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

    public void añadirPlayer(int connectionID)                          { mundoModel.añadirPlayer(connectionID);}
    public void actualizarPlayer(NetDTO.ActualizarPlayer updatePlayer)  { mundoModel.actualizarPlayer (updatePlayer); }
    public void eliminarPlayer()                                        { mundoModel.eliminarPlayer();}
    public void moverPlayer(float x, float y)                           { mundoModel.moverPlayer(x, y);}
    public void aplicarInputAPlayer()                                   { mundoModel.getPlayer().setInput(playerOutput); }

    public void añadirPC(int connectionID, float x, float y)            { mundoModel.añadirPC(connectionID, x, y); }
    public void eliminarPC(int connectionID)                            { mundoModel.eliminarPC(connectionID); }
    public void moverPC(int connectionID, float x, float y)             { mundoModel.moverPC(connectionID, x, y); }
    public void cambiarAnimacionPC(int connectionID, int numAnimacion)  { mundoModel.cambiarAnimacionPC(connectionID, numAnimacion); }

    public void addInputProcessor(Stage stage)                          { inputMultiplexer.addProcessor(stage); }
}
