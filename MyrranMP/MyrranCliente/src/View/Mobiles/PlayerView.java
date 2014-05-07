package View.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.GameState.Mundo;
import Model.Classes.Mobiles.Player;
import Model.DTO.NetDTO;
import Model.DTO.PlayerDTO;
import Recursos.DAO.RSC;
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

    public float getCenterX()               { return (this.getX()+this.getWidth()/2); }
    public float getCenterY()               { return (this.getY()+this.getHeight()/2); }

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
        nombre = new Texto(nuevoNombre, RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres), Color.WHITE, Color.BLACK, actor.getWidth()/2, 0, Align.center, Align.bottom, 1);
        nombre.setPosition(0, actor.getHeight()+12);
        this.addActor(nombre);
    }

    public void setPosition (int x, int y)
    {
        if (Math.abs(this.getX()-x) >= 1 || Math.abs(this.getY()-y) >= 1)
        {
            super.setPosition(x, y);
            NetDTO.MoverPC moverPlayer = new NetDTO.MoverPC(player.getConnectionID(), getX(), getY());
            controlador.enviarAServidor(moverPlayer);
        }
    }

    //Muy importante, la posicion en pantalla que sea en Pixels siempre, o sea sin decimales, o el sprite parpadea
    @Override public void setPosition (float x, float y)
    {   setPosition((int)x, (int)y);}

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