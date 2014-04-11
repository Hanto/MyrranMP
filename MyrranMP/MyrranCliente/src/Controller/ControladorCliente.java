package Controller;// Created by Hanto on 10/04/2014.

import Controller.Input.PlayerIO;
import Controller.Network.NetClient;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface ControladorCliente extends ControladorI
{
    public NetClient getNetIO();

    public void añadirPlayer (int connectionID);
    public void eliminarPlayer ();
    public void moverPlayer (float x, float y);

    public void aplicarPlayerIO();

    public PlayerIO getPlayerIO();
    public void addInputProcessor(Stage stage);
}
