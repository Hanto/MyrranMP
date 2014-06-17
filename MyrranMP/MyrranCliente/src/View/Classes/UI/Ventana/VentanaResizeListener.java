package View.Classes.UI.Ventana;// Created by Hanto on 14/05/2014.

import View.Classes.Graficos.Caja;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class VentanaResizeListener extends DragListener
{
    private Actor dragActor;
    private Actor actor;
    private Ventana ventana;

    private float origenX;
    private float origenY;
    private Caja caja = new Caja();

    private float oldX;
    private float oldY;

    private int numFilas;
    private int numColumnas;

    private int columnasAdicionales;
    private int filasAdicionales;

    public VentanaResizeListener(Actor dragActor, Actor actor, Ventana ventana)
    {
        this.dragActor = dragActor;
        this.actor = actor;
        this.ventana = ventana;
    }

    @Override public void touchUp (InputEvent event, float x, float y, int pointer, int button)
    {
        dragActor.setPosition(oldX, oldY);
        caja.remove();

        float X = (event.getStageX()- origenX);
        float Y = (event.getStageY()- origenY);

        columnasAdicionales = Math.round((float)(X)/(float)(ventana.getAnchoElemento()));
        filasAdicionales = - Math.round((float)(Y)/(float)(ventana.getAltoElemento()));

        if (numFilas + filasAdicionales <=0)        { filasAdicionales = 1-numFilas; }
        if (numColumnas + columnasAdicionales <=0)  { columnasAdicionales = 1-numColumnas; }

        ventana.eventoVentanaResize(columnasAdicionales, filasAdicionales);
    }

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        numFilas = (int)(actor.getHeight()/ventana.getAltoElemento());
        numColumnas = (int)(actor.getWidth()/ventana.getAnchoElemento());

        oldX = dragActor.getX();
        oldY = dragActor.getY();

        caja.setCaja(actor.getX(), actor.getY() + actor.getHeight(),
                actor.getWidth(), -actor.getHeight());

        dragActor.getStage().addActor(caja);

        origenX = (int)event.getStageX();
        origenY = (int)event.getStageY();
        return true;
    }

    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        if (actor.getStage() == null) { return; }

        float newX = dragActor.getX() - dragActor.getWidth()/2 + x;
        float newY = dragActor.getY() - dragActor.getHeight()/2 + y;

        dragActor.setPosition(newX, newY);

        caja.setTamaño(newX, newY - actor.getHeight() + dragActor.getHeight());
    }
}
