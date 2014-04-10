package Controller;// Created by Hanto on 08/04/2014.

import Controller.Input.ControladorCliente;
import Controller.Input.PlayerInput;
import Controller.Network.NetClient;
import Modelo.Mobiles.MundoModel;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

//CLIENTE:
public class Controlador implements ControladorCliente
{
    public NetClient cliente;
    protected MundoModel mundo;
    protected Vista vista;

    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public Controlador (MundoModel mundo)
    {
        this.mundo = mundo;
        vista = new Vista(this, mundo);
        inputMultiplexer.addProcessor(new PlayerInput(this));
        Gdx.input.setInputProcessor(inputMultiplexer);
        cliente = new Cliente(this);

        añadirPlayer(cliente.getID());
    }

    public void render (float delta)                                    { vista.render(delta); }
    public void dispose()                                               { vista.dispose(); }

    @Override public MundoModel getMundo()                              { return mundo; }
    @Override public NetClient getNetIO()                               { return cliente; }

    @Override public void añadirPlayer(int connectionID)                { mundo.añadirPlayer(connectionID);}
    @Override public void eliminarPlayer()                              { mundo.eliminarPlayer();}
    @Override public void moverPlayer(float x, float y)                 { mundo.moverPlayer(x, y);}

    @Override public void añadirPC(int connectionID, float x, float y)  { mundo.añadirPC(connectionID, x, y); }
    @Override public void eliminarPC(int connectionID)                  { mundo.eliminarPC(connectionID); }
    @Override public void moverPC(int connectionID, float x, float y)   { mundo.moverPC(connectionID, x, y); }

    @Override public void netUpdater()                                  { }


}
