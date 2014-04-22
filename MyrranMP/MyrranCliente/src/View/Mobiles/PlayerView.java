package View.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Controlador;
import Model.DTO.NetDTO;
import Model.DTO.PlayerDTO;
import Model.Mobiles.Mundo;
import Model.Mobiles.Player;
import View.Graficos.PixiePC;
import View.Graficos.Texto;
import View.Vista;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerView extends Group implements PropertyChangeListener
{
    public Player player;
    public Vista vista;
    public Controlador controlador;
    //Datos derivados:
    public Mundo mundo;

    public Texto nombre;
    public Integer nivel;

    public PixiePC actor;

    public PlayerView (Player player, Vista vista, Controlador controlador)
    {
        this.player = player;
        this.vista = vista;
        this.controlador = controlador;
        mundo = vista.mundo;

        this.setPosition(player.getX(), player.getY());

        player.eliminarObservador(vista);
        player.añadirObservador(this);

        crearActor();
    }

    public void crearActor ()
    {
        actor = new PixiePC("Golem");
        this.addActor(actor);
        this.setWidth(actor.getWidth());
        this.setHeight(actor.getHeight());
        System.out.println("añadiendo Actor:");
        vista.stageMundo.addActor(this);
        System.out.println("numero Mobiles: "+vista.stageMundo.getActors().size);
        actor.setAnimacion(5, false);
    }

    public void setNombre (String nuevoNombre)
    {
        this.removeActor(nombre);
        nombre = new Texto(nuevoNombre, MobilesRecursos.get().font14, Color.WHITE, Color.BLACK, actor.getWidth()/2, 0, Align.center, Align.bottom, 1);
        nombre.setPosition(0, actor.getHeight()+12);
        this.addActor(nombre);
    }

    @Override public void setPosition (float x, float y)
    {
        if (Math.abs(this.getX()-x) >= 1.0f || Math.abs(this.getY()-y) >= 1.0f)
        {
            super.setPosition(x, y);
            NetDTO.MoverPC moverPlayer = new NetDTO.MoverPC(player.getConnectionID(), getX(), getY());
            controlador.enviarAServidor(moverPlayer);
        }
    }

    public void setAnimacion (int numAnimacion)
    {
        actor.setAnimacion(numAnimacion, false);
        NetDTO.CambiarAnimacionPC cambiarAnimacionPC = new NetDTO.CambiarAnimacionPC(player.getConnectionID(), numAnimacion);
        controlador.enviarAServidor(cambiarAnimacionPC);
        System.out.println("Player ID["+cambiarAnimacionPC.connectionID + "]Animacion["+cambiarAnimacionPC.numAnimacion+"]");
    }

    public void setCastear (boolean castear, int targetX, int targetY)
    {
        Vector2 targetMundo = vista.convertirCoordenadasPantallaAMundo(targetX, targetY);
        NetDTO.CastearPC castearNDTO = new NetDTO.CastearPC(castear, (int)targetMundo.x, (int)targetMundo.y);
        controlador.enviarAServidor(castearNDTO);
        System.out.println("Casteando "+castear+" en: ["+targetX+"]["+targetY+"]");
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

        if (evt.getNewValue() instanceof PlayerDTO.Castear)
        {
            boolean castear = ((PlayerDTO.Castear) evt.getNewValue()).castear;
            int targetX = ((PlayerDTO.Castear) evt.getNewValue()).screenX;
            int targetY = ((PlayerDTO.Castear) evt.getNewValue()).screenY;
            setCastear(castear, targetX, targetY);
        }
    }
}