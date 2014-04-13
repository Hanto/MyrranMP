package View.Actores;// Created by Hanto on 10/04/2014.

import Controller.Controlador;
import DTO.NetDTO;
import DTO.PlayerDTO;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PlayerModel;
import View.Graficos.PixiePC;
import View.Vista;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerView extends Group implements PropertyChangeListener
{
    public PlayerModel playerModel;
    public Vista vista;
    public Controlador controlador;
    //Datos derivados:
    public MundoModel mundoModel;

    public int connectionID;
    public PixiePC actor;

    public PlayerView (PlayerModel playerModel, Vista vista, Controlador controlador)
    {
        this.playerModel = playerModel;
        this.vista = vista;
        this.controlador = controlador;
        mundoModel = vista.mundoModel;

        connectionID = playerModel.getConnectionID();
        this.setPosition(playerModel.getX(), playerModel.getY());

        playerModel.eliminarObservador(vista);
        playerModel.añadirObservador(this);

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
        if (Math.abs(this.getX()-x) >= 1.0f || Math.abs(this.getY()-y) >= 1.0f)
        {
            super.setPosition(x, y);
            NetDTO.MoverPC moverPlayer = new NetDTO.MoverPC(playerModel.getConnectionID(), getX(), getY());
            controlador.enviarAServidor(moverPlayer);
        }
    }

    public void setAnimacion (int numAnimacion)
    {
        actor.setAnimacion(numAnimacion, false);
        NetDTO.CambiarAnimacionPC cambiarAnimacionPC = new NetDTO.CambiarAnimacionPC(playerModel.getConnectionID(), numAnimacion);
        controlador.enviarAServidor(cambiarAnimacionPC);
        System.out.println("PlayerModel ID["+cambiarAnimacionPC.connectionID + "]Animacion["+cambiarAnimacionPC.numAnimacion+"]");
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof PlayerDTO.MoverPlayer)
        {
            float x = ((PlayerDTO.MoverPlayer) evt.getNewValue()).x;
            float y = ((PlayerDTO.MoverPlayer) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof PlayerDTO.CambiarAnimacionPlayer)
        {
            int numAnimacion = ((PlayerDTO.CambiarAnimacionPlayer) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }
    }
}