package Controller;// Created by Hanto on 10/04/2014.

import Controller.Network.NetClient;
import com.badlogic.gdx.scenes.scene2d.Stage;

public interface ControladorCliente extends ControladorI
{
    public NetClient getNetIO();

    public void añadirPlayer (int connectionID);
    public void eliminarPlayer ();
    public void moverPlayer (float x, float y);

    public void aplicarInputAPlayer();
    public void addInputProcessor(Stage stage);
}
