package View.Classes.UI.SpellTooltip;// Created by Hanto on 27/06/2014.

import DB.RSC;
import Data.MiscData;
import Interfaces.UI.BarraAcciones.ControladorBarraAccionI;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class CasilleroTalentos extends Actor
{
    private TextureRegion fondo;
    private TextureRegion frente;

    private int ancho25;
    private int ancho50;
    private int numTalentos = 0;

    private ControladorBarraAccionI controlador;


    public CasilleroTalentos(final ControladorBarraAccionI controladorI, final String skillID, final int skillStatID, final int numTalentos)
    {
        this.controlador = controladorI;

        fondo = RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_SPELLTOOLTIP_TalentoFondo);
        frente = RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_SPELLTOOLTIP_Talento);

        int ancho = fondo.getRegionWidth();
        int alto = fondo.getRegionHeight();

        this.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (button == Input.Buttons.LEFT)
                {
                    if (x < getWidth() / 2)                             controlador.decrementarSkillTalento(skillID, skillStatID);
                    else if (x > getWidth() / 2)                        controlador.aumentarSkillTalento(skillID, skillStatID);
                }
                if (button == Input.Buttons.RIGHT)
                {
                    if (CasilleroTalentos.this.numTalentos <= 25)       controlador.setSkillTalento(skillID, skillStatID, (int)(x/3));
                    else if (CasilleroTalentos.this.numTalentos > 25)   controlador.setSkillTalento(skillID, skillStatID, (int)(x/3) + 25);
                }
                return true;
            }
        });

        this.setWidth(ancho);
        this.setHeight(alto);

        setNumTalentos(numTalentos);
    }

    public void setNumTalentos(int numTalentos)
    {
        this.numTalentos = numTalentos;

        ancho25 = ((int)getWidth()/25) * (numTalentos > 25 ? 25 : numTalentos);
        ancho50 = ((int)getWidth()/25) * (numTalentos > 50 ? 25 : numTalentos - 25);
    }

    public void draw (Batch batch, float alpha)
    {
        batch.setColor(getColor());
        batch.draw(fondo, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        batch.setColor(255 / 255f, 180 / 255f, 0 / 255f, 0.75f);
        batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), ancho25, getHeight(), getScaleX(), getScaleY(), getRotation());

        if (numTalentos > 25)
        {
            batch.setColor(255/255f, 0/255f, 0/255f, 0.55f);
            batch.draw(frente, getX(), getY(), getOriginX(), getOriginY(), ancho50, getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
