package View.Actores;// Created by Hanto on 10/04/2014.

import Controller.ControladorCliente;
import DTOs.NetDTO;
import Modelo.DTO.ClientDTO;
import Modelo.Models.MundoModelC;
import Modelo.Models.PlayerModel;
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
        actor.setAnimacion(5, false);
    }

    @Override public void setPosition (float x, float y)
    {
        if (Math.abs(this.getX()-x) >= 1f || Math.abs(this.getY()-y) >= 1f)
        {
            super.setPosition(x, y);
            NetDTO.MoverPC moverPlayer = new NetDTO.MoverPC(player.getConnectionID(), getX(), getY());
            controlador.getNetIO().enviarAServidor(moverPlayer);
        }
    }

    public void setAnimacion (int numAnimacion)
    {
        actor.setAnimacion(numAnimacion, false);
        NetDTO.CambiarAnimacionPC cambiarAnimacionPC = new NetDTO.CambiarAnimacionPC(player.getConnectionID(), numAnimacion);
        controlador.getNetIO().enviarAServidor(cambiarAnimacionPC);
        System.out.println("Player ID["+cambiarAnimacionPC.connectionID + "]Animacion["+cambiarAnimacionPC.numAnimacion+"]");
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof ClientDTO.MoverPlayer)
        {
            float x = ((ClientDTO.MoverPlayer) evt.getNewValue()).x;
            float y = ((ClientDTO.MoverPlayer) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof ClientDTO.CambiarAnimacionPlayer)
        {
            int numAnimacion = ((ClientDTO.CambiarAnimacionPlayer) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }
    }
}