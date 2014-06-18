package View.Classes.Mobiles;

import DB.RSC;
import DB.Recursos.MiscRecursos.DAO.MiscRecursosDAO;
import DTO.NetDTO;
import Data.MiscData;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Interfaces.Model.ModelI;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// @author Ivan Delgado Huerta
public class NameplateView extends Actor implements PropertyChangeListener
{
    //Model:
    protected Vulnerable vulnerable;
    protected Caster caster;
    protected ModelI model;

    protected boolean isCaster = false;
    protected boolean isVulnerable = false;

    //NAMEPLATES
    protected Sprite barraVidaTotal;                //Imagen que contiene el nameplateTotal de la vida del Player
    protected Sprite barraVidaActual;               //Imagen que contiene el MapaView de la vida del nameplateTotal del Player
    protected Sprite barraCasteoTotal;
    protected Sprite barraCasteoActual;

    public NameplateView(Vulnerable vulnerable)
    {
        this.model = vulnerable;
        this.vulnerable = vulnerable;
        this.isVulnerable = true;

        if (vulnerable instanceof Caster)
        {
            this.caster = (Caster)vulnerable;
            this.isCaster = true;
        }
        crearActor();
    }

    public NameplateView(Caster caster)
    {
        this.model = caster;
        this.caster = caster;
        this.isCaster = true;

        if (caster instanceof Vulnerable)
        {
            this.vulnerable = (Vulnerable)caster;
            this.isVulnerable = true;
        }
        crearActor();
    }

    public void crearActor()
    {
        model.añadirObservador(this);

        MiscRecursosDAO miscRecursosDAO = RSC.miscRecusosDAO.getMiscRecursosDAO();

        int alto = RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_NAMEPLATE_Nameplate).getRegionHeight();
        int ancho = RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_NAMEPLATE_Nameplate).getRegionWidth();

        if (isVulnerable)
        {
            barraVidaTotal = new Sprite(miscRecursosDAO.cargarTextura(MiscData.RECURSO_NAMEPLATE_Nameplate));
            barraVidaActual = new Sprite(miscRecursosDAO.cargarTextura(MiscData.RECURSO_NAMEPLATE_Nameplate_Fondo));
            barraVidaTotal.setColor(Color.GREEN);
            barraVidaTotal.setPosition(0, alto-1);
            barraVidaActual.setPosition(ancho-1, alto-1);
        }
        if (isCaster)
        {
            barraCasteoTotal = new Sprite(miscRecursosDAO.cargarTextura(MiscData.RECURSO_NAMEPLATE_Nameplate));
            barraCasteoActual = new Sprite(miscRecursosDAO.cargarTextura(MiscData.RECURSO_NAMEPLATE_Nameplate_Fondo));
            barraCasteoTotal.setColor(Color.ORANGE);
            barraCasteoActual.setPosition(ancho-1, 0);
        }

        this.setWidth(ancho);
        this.setHeight(alto);
        this.setBounds(0, 0, ancho, alto);
    }

    public void eliminar()
    {   model.eliminarObservador(this); }

    private void setHPsPercent ()
    {
        float HPsPercent = vulnerable.getActualHPs()/vulnerable.getMaxHPs();

        float tamaño = (1 - HPsPercent) * this.getWidth();
        if (tamaño != barraVidaActual.getWidth()) barraVidaActual.setSize(-(int) tamaño, this.getHeight());
    }

    private void setCastingTimePercent (float castingTimePercent)
    {
        float tamaño = (1-castingTimePercent)*this.getWidth();
        if (tamaño != barraCasteoActual.getWidth()) barraCasteoActual.setSize(-(int)tamaño, this.getHeight());
    }



    @Override public void act(float delta)
    {
        float alto = this.getHeight();
        float ancho = this.getWidth();
        
        super.act(delta);

        if (isVulnerable)
        {
            barraVidaTotal.setPosition(this.getX(), this.getY() + alto - 1);
            barraVidaActual.setPosition(this.getX() + ancho - 1, this.getY() + alto - 1);
        }
        if (isCaster)
        {
            barraCasteoTotal.setPosition(this.getX(), this.getY());
            barraCasteoActual.setPosition(this.getX() + ancho - 1, this.getY());
        }
    }
    
    @Override public void draw (Batch batch, float alpha)
    {
        if (isVulnerable)
        {
            barraVidaTotal.draw(batch, alpha);
            barraVidaActual.draw(batch, alpha);
        }
        if (isCaster)
        {
            barraCasteoTotal.draw(batch, alpha);
            barraCasteoActual.draw(batch, alpha);
        }
    }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof NetDTO.ModificarHPsPPC)
        { setHPsPercent();}

        if (evt.getNewValue() instanceof NetDTO.CastingTimePercent)
        { setCastingTimePercent(((NetDTO.CastingTimePercent) evt.getNewValue()).castingTimePercent); }
    }
}
