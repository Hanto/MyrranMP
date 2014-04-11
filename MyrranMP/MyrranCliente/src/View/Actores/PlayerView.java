package View.Actores;// Created by Hanto on 10/04/2014.

import Controller.Input.ControladorCliente;
import Controller.Network.Net;
import Modelo.Mobiles.DTO;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PlayerModel;
import View.Graficos.PixiePC;
import View.Vista;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerView extends Group implements PropertyChangeListener
{
    public PlayerModel player;
    public Vista vista;
    public ControladorCliente controlador;
    //Datos derivados:
    public MundoModel mundo;

    public int connectionID;
    public PixiePC actor;

    public PlayerView (PlayerModel player, Vista vista, ControladorCliente controlador)
    {
        this.player = player;
        this.vista = vista;
        this.controlador = controlador;
        mundo = vista.mundo;

        connectionID = player.getConnectionID();
        this.setPosition(player.getX(), player.getY());

        player.eliminarObservador(vista);
        player.añadirObservador(this);

        crearActor();
    }

    public void crearActor ()
    {
        actor = new PixiePC("Golem");
        this.addActor(actor);
        System.out.println("añadiendo Actor:");
        vista.stageMundo.addActor(this);
        System.out.println("numero Actores: "+vista.stageMundo.getActors().size);
    }

    @Override public void setPosition (float x, float y)
    {
        super.setPosition(x, y);
        Net.MoverPC moverPlayer = new Net.MoverPC(player.getConnectionID(), getX(), getY());
        controlador.getNetIO().enviarAServidor(moverPlayer);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof DTO.PlayerPosition)
        {
            float x = ((DTO.PlayerPosition) evt.getNewValue()).x;
            float y = ((DTO.PlayerPosition) evt.getNewValue()).y;
            setPosition(x, y);
        }
    }
}