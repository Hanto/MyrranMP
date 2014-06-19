package View.Classes.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Data.MiscData;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Model.Classes.Mobiles.Player;
import Model.DTO.PlayerDTO;
import DB.RSC;
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
    public MundoView mundoView;
    public Controlador controlador;

    public Integer nivel;
    public String spellIDSeleccionado;

    public PixiePC actor;
    public NameplateView nameplateView;
    public Texto nombre;
    public PointLight luz;

    public float getCenterX()               { return (this.getX()+this.getWidth()/2); }
    public float getCenterY()               { return (this.getY()+this.getHeight()/2); }

    public PlayerView (Player player, MundoView mundoView, Controlador controlador)
    {
        this.player = player;
        this.mundoView = mundoView;
        this.controlador = controlador;

        this.setPosition(player.getX(), player.getY());

        player.añadirObservador(this);

        crearActor();
    }

    public void crearActor ()
    {
        mundoView.addActor(this);

        actor = new PixiePC("Golem");
        actor.setAnimacion(5, false);
        this.addActor(actor);
        this.setWidth(actor.getWidth());
        this.setHeight(actor.getHeight());

        nameplateView = new NameplateView((Vulnerable)player);
        nameplateView.setPosition(this.getWidth()/2 - nameplateView.getWidth() / 2, getHeight());
        this.addActor(nameplateView);

        nombre = new Texto("Player", RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres), Color.WHITE, Color.BLACK, actor.getWidth()/2, 0, Align.center, Align.bottom, 1);
        nombre.setPosition(0, actor.getHeight()+8);
        this.addActor(nombre);

        luz = new PointLight(mundoView.getRayHandler(), 300, new Color(0.3f,0.3f,0.3f,1.0f), 350, 0, 0);
    }

    public void setNombre (String nuevoNombre)
    {   nombre.setTexto(nuevoNombre); }

    public void setPosition (int x, int y)
    {
        if (Math.abs(this.getX()-x) >= 1 || Math.abs(this.getY()-y) >= 1)
        {
            super.setPosition(x, y);
            luz.setPosition(x,y);
            NetDTO.PosicionPPC moverPlayer = new NetDTO.PosicionPPC(player.getConnectionID(), getX(), getY());
            controlador.enviarAServidor(moverPlayer);
        }
    }

    //Muy importante, la posX en pantalla que sea en Pixels siempre, o sea sin decimales, o el sprite parpadea
    @Override public void setPosition (float x, float y)
    {   setPosition((int)x, (int)y);}

    public void setAnimacion (int numAnimacion)
    {
        actor.setAnimacion(numAnimacion, false);
        NetDTO.AnimacionPPC animacionPPC = new NetDTO.AnimacionPPC(player.getConnectionID(), numAnimacion);
        controlador.enviarAServidor(animacionPPC);
        //System.out.println("Player ID["+cambiarAnimacionPC.connectionID + "]Animacion["+cambiarAnimacionPC.numAnimacion+"]");
    }

    public void setCastear (boolean castear, int targetX, int targetY)
    {
        Vector2 targetMundo = convertirCoordenadasPantallaAMundo(targetX, targetY);
        NetDTO.CastearPPC castearNDTO = new NetDTO.CastearPPC(castear, (int)targetMundo.x, (int)targetMundo.y);
        controlador.enviarAServidor(castearNDTO);
        //System.out.println("Casteando "+castear+" en: ["+targetX+"]["+targetY+"]");
    }

    public void modificarHPs(NetDTO.ModificarHPsPPC HPs)
    {
        synchronized (mundoView)
        {
            Texto texto = new Texto(Integer.toString((int) HPs.HPs), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                    Color.GREEN, Color.BLACK, 0, 0, Align.center, Align.bottom, 1);
            texto.setPosition(this.getWidth() / 2 + (float) Math.random() * 30 - 15, this.getHeight() + 15);
            texto.scrollingCombatText(this, 2f);
        }
    }

    public Vector2 convertirCoordenadasPantallaAMundo (int screenX, int screenY)
    {
        Vector3 destino = new Vector3(screenX, screenY, 0);
        mundoView.getCamera().unproject(destino);
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
        if (evt.getNewValue() instanceof NetDTO.PosicionPPC)
        {
            float x = ((NetDTO.PosicionPPC) evt.getNewValue()).x;
            float y = ((NetDTO.PosicionPPC) evt.getNewValue()).y;
            setPosition(x, y);
        }

        if (evt.getNewValue() instanceof NetDTO.AnimacionPPC)
        {
            int numAnimacion = ((NetDTO.AnimacionPPC) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }

        if (evt.getNewValue() instanceof PlayerDTO.NombrePlayer)
        {
            String nuevoNombre = ((PlayerDTO.NombrePlayer) evt.getNewValue()).nombre;
            setNombre(nuevoNombre);
        }

        if (evt.getNewValue() instanceof PlayerDTO.NivelPlayer)
        {   nivel = ((PlayerDTO.NivelPlayer) evt.getNewValue()).nivel; }

        if (evt.getNewValue() instanceof NetDTO.CastearPPC)
        {
            boolean castear = ((NetDTO.CastearPPC) evt.getNewValue()).castear;
            int targetX = ((NetDTO.CastearPPC) evt.getNewValue()).targetX;
            int targetY = ((NetDTO.CastearPPC) evt.getNewValue()).targetY;
            setCastear(castear, targetX, targetY);
        }

        if (evt.getNewValue() instanceof NetDTO.ModificarHPsPPC)
        {   modificarHPs((NetDTO.ModificarHPsPPC)evt.getNewValue()); }

        if (evt.getNewValue() instanceof PlayerDTO.SetSpellIDSeleccionado)
        {
            String spellID = ((PlayerDTO.SetSpellIDSeleccionado) evt.getNewValue()).spellID;
            setSpellIDSeleccionado(spellID);
        }

        if (evt.getNewValue() instanceof PlayerDTO.SetParametrosSpell)
        {   setParametrosSpell(); }
    }
}