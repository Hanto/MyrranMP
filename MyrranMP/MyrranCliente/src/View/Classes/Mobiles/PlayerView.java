package View.Classes.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Data.MiscData;
import Model.Classes.Mobiles.Player;
import Model.DTO.PlayerDTO;
import Datos.RSC;
import View.Classes.Graficos.Nameplate;
import View.Classes.Graficos.PixiePC;
import View.Classes.Graficos.Texto;
import View.GameState.MundoView;
import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerView extends Group implements PropertyChangeListener
{
    public Player player;
    public MundoView vista;
    public Controlador controlador;

    public Integer nivel;
    public String spellIDSeleccionado;

    public Texto nombre;
    public PixiePC actor;
    public Nameplate nameplate;
    public PointLight luz;

    public float getCenterX()               { return (this.getX()+this.getWidth()/2); }
    public float getCenterY()               { return (this.getY()+this.getHeight()/2); }

    public PlayerView (Player player, MundoView vista, Controlador controlador)
    {
        this.player = player;
        this.vista = vista;
        this.controlador = controlador;


        this.setPosition(player.getX(), player.getY());

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
        vista.addActor(this);
        System.out.println("numero Mobiles: "+vista.getActors().size);
        actor.setAnimacion(5, false);
        nameplate = new Nameplate();
        this.addActor(nameplate);
        nameplate.setPosition(getCenterX()-nameplate.getWidth()/2, getHeight());
        luz = new PointLight(vista.getRayHandler(), 300, new Color(0.3f,0.3f,0.3f,1.0f), 350, 0, 0);
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
            luz.setPosition(x,y);
            NetDTO.CambiarPosicionPC moverPlayer = new NetDTO.CambiarPosicionPC(player.getConnectionID(), getX(), getY());
            controlador.enviarAServidor(moverPlayer);
        }
    }

    //Muy importante, la posX en pantalla que sea en Pixels siempre, o sea sin decimales, o el sprite parpadea
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
        Vector2 targetMundo = convertirCoordenadasPantallaAMundo(targetX, targetY);
        NetDTO.CastearPC castearNDTO = new NetDTO.CastearPC(castear, (int)targetMundo.x, (int)targetMundo.y);
        controlador.enviarAServidor(castearNDTO);
        System.out.println("Casteando "+castear+" en: ["+targetX+"]["+targetY+"]");
    }

    public Vector2 convertirCoordenadasPantallaAMundo (int screenX, int screenY)
    {
        Vector3 destino = new Vector3(screenX, screenY, 0);
        vista.getCamera().unproject(destino);
        return new Vector2(destino.x, destino.y);
    }

    public void setSpellIDSeleccionado(String spellID)
    {
        if (spellIDSeleccionado != spellID)
        {
            spellIDSeleccionado = spellID;
            NetDTO.SetSpellIDSeleccionado setSpellIDSeleccionado = new NetDTO.SetSpellIDSeleccionado(spellID, player.getParametrosSpell());
            controlador.enviarAServidor(setSpellIDSeleccionado);
        }
    }

    public void setParametrosSpell()
    {
        NetDTO.SetParametrosSpell setSpellIDSeleccionado = new NetDTO.SetParametrosSpell(player.getParametrosSpell());
        controlador.enviarAServidor(setSpellIDSeleccionado);
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

        if (evt.getNewValue() instanceof PlayerDTO.SetSpellIDSeleccionado)
        {
            String spellID = ((PlayerDTO.SetSpellIDSeleccionado) evt.getNewValue()).spellID;
            setSpellIDSeleccionado(spellID);
        }

        if (evt.getNewValue() instanceof PlayerDTO.SetParametrosSpell)
        {   setParametrosSpell(); }
    }
}