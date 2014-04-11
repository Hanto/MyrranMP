package View.Actores;// Created by Hanto on 10/04/2014.

import Controller.ControladorCliente;
import Controller.Network.DTO;
import Modelo.Mobiles.MundoModelC;
import Modelo.Mobiles.PlayerDTO;
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
    public MundoModelC mundo;

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
        if (Math.abs(this.getX()-x) >= 1f || Math.abs(this.getY()-y) >= 1f)
        {
            super.setPosition(x, y);
            DTO.MoverPC moverPlayer = new DTO.MoverPC(player.getConnectionID(), getX(), getY());
            controlador.getNetIO().enviarAServidor(moverPlayer);
        }
    }

    public void setAnimacion (int numAnimacion)
    {
        actor.setAnimacion(numAnimacion, false);
        DTO.CambiarAnimacionPC cambiarAnimacionPC = new DTO.CambiarAnimacionPC(player.getConnectionID(), numAnimacion);
        controlador.getNetIO().enviarAServidor(cambiarAnimacionPC);
        System.out.println("Player ID["+cambiarAnimacionPC.connectionID + "]Animacion["+cambiarAnimacionPC.numAnimacion+"]");
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof PlayerDTO.PlayerPosition)
        {
            float x = ((PlayerDTO.PlayerPosition) evt.getNewValue()).x;
            float y = ((PlayerDTO.PlayerPosition) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof PlayerDTO.PlayerAnimacion)
        {
            int numAnimacion = ((PlayerDTO.PlayerAnimacion) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }
    }
}