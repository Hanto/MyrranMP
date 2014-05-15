package View.Classes.UI.Comun;// Created by Hanto on 14/05/2014.

import View.Classes.Graficos.Caja;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class VentanaResizeListener extends DragListener
{
    private Actor dragActor;
    private Actor actor;
    private Ventana ventana;

    private float redimensionarX;
    private float redimensionarY;
    private Caja caja = new Caja();

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
        dragActor.getStage().getRoot().removeActor(caja);

        float X = (event.getStageX()-redimensionarX);
        float Y = (event.getStageY()-redimensionarY);

        columnasAdicionales = Math.round((float)(X)/(float)(ventana.getAnchoElemento()));
        filasAdicionales = - Math.round((float)(Y)/(float)(ventana.getAltoElemento()));

        ventana.eventoVentanaResize(columnasAdicionales, filasAdicionales);
    }

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        caja.setCaja(actor.getX(), actor.getY() + actor.getHeight(),
                     actor.getWidth(), -actor.getHeight());

        dragActor.getStage().addActor(caja);

        redimensionarX = (int)event.getStageX();
        redimensionarY = (int)event.getStageY();
        return true;
    }

    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        float newX = dragActor.getX() - dragActor.getWidth()/2 + x;
        float newY = dragActor.getY() - dragActor.getHeight()/2 + y;

        dragActor.setPosition(newX, newY);

        caja.setTamaño(newX, newY - actor.getHeight() + dragActor.getHeight());
    }
}
