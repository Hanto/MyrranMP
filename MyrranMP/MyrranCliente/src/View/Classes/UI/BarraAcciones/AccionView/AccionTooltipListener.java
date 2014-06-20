package View.Classes.UI.BarraAcciones.AccionView;// Created by Hanto on 19/06/2014.

import DB.DAO;
import Interfaces.Spell.SpellI;
import Interfaces.UI.Acciones.AccionI;
import Model.Classes.Acciones.TiposAccion.SeleccionarSpell;
import View.Classes.UI.SpellTooltipView;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class AccionTooltipListener extends InputListener
{
    private AccionIcono accionIcono;
    private SpellTooltipView tooltip;

    public AccionTooltipListener(AccionIcono accionIcono)
    {   this.accionIcono = accionIcono; }

    @Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
    {
        AccionI accion = accionIcono.getAccion();

        if (tooltip != null)
        {   accionIcono.getApariencia().removeActor(tooltip); }
        if (accion instanceof SeleccionarSpell)
        {
            System.out.println("Entra " + fromActor);
            SpellI spell = DAO.spellDAOFactory.getSpellDAO().getSpell(accion.getID());
            tooltip = new SpellTooltipView(spell);
            tooltip.setPosition(0, tooltip.getHeight() + accionIcono.getApariencia().getHeight()+8);
            accionIcono.getApariencia().addActor(tooltip);
        }
    }

    @Override public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
    {   accionIcono.getApariencia().removeActor(tooltip); System.out.println(" Sale "+fromActor);}

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        if (button == Input.Buttons.RIGHT && tooltip != null)
        {
            event.getStage().addActor(tooltip);
            Vector2 clickPos = getPosicionClick(event, x, y);
            tooltip.setPosition(clickPos.x +16, clickPos.y +16);
        }
        return true;
    }

    private Vector2 getPosicionClick(InputEvent event, float x, float y)
    {
        //Calculamos la posicion del raton, con programacion eslovaca:
        Vector2 clickPos = new Vector2(x, y);
        event.getListenerActor().localToStageCoordinates(clickPos);
        return clickPos;
    }
}
