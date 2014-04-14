package View.Actores;// Created by Hanto on 10/04/2014.

import Controller.Controlador;
import Modelo.DTO.NetDTO;
import Modelo.DTO.PlayerDTO;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PlayerModel;
import View.Graficos.PixiePC;
import View.Graficos.Texto;
import View.Vista;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerView extends Group implements PropertyChangeListener
{
    public PlayerModel playerModel;
    public Vista vista;
    public Controlador controlador;
    //Datos derivados:
    public MundoModel mundoModel;

    public Integer connectionID;
    public Texto nombre;
    public Integer nivel;

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

    public void setNombre (String nuevoNombre)
    {
        this.removeActor(nombre);
        nombre = new Texto(nuevoNombre, ActorRecursos.get().font14, Color.WHITE, Color.BLACK, actor.getWidth()/2, 0, Align.center, Align.bottom, 1);
        nombre.setPosition(0, actor.getHeight()+12);
        this.addActor(nombre);
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
        if (evt.getNewValue() instanceof PlayerDTO.PositionPlayer)
        {
            float x = ((PlayerDTO.PositionPlayer) evt.getNewValue()).x;
            float y = ((PlayerDTO.PositionPlayer) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof PlayerDTO.AnimacionPlayer)
        {
            int numAnimacion = ((PlayerDTO.AnimacionPlayer) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }

        if (evt.getNewValue() instanceof PlayerDTO.NombrePlayer)
        {   String nuevoNombre = ((PlayerDTO.NombrePlayer) evt.getNewValue()).nombre;
            setNombre(nuevoNombre);
        }

        if (evt.getNewValue() instanceof PlayerDTO.NivelPlayer)
        {   nivel = ((PlayerDTO.NivelPlayer) evt.getNewValue()).nivel; }
    }
}