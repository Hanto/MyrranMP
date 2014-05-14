package View.Classes.UI.BarraTerrenosView;// Created by Hanto on 14/05/2014.

import View.Classes.Graficos.Caja;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class BarraTerrenosResizeListener extends DragListener
{
    private Actor dragActor;
    private Actor actor;
    private BarraTerrenosView barra;

    private float redimensionarX;
    private float redimensionarY;
    private Caja caja = new Caja();

    public BarraTerrenosResizeListener(Actor dragActor, Actor actor, BarraTerrenosView barraTerrenos)
    {
        this.dragActor = dragActor;
        this.actor = actor;
        this.barra = barraTerrenos;
    }

    @Override public void touchUp (InputEvent event, float x, float y, int pointer, int button)
    {
        dragActor.getStage().getRoot().removeActor(caja);

        int X = (int)(event.getStageX()-redimensionarX);
        int Y = (int)(event.getStageY()-redimensionarY);

        int columnasAdicionales = Math.round((float)Math.abs(X)/(float)(48));
        int filasAdicionales = Math.round((float)Math.abs(Y)/(float)(48));

        if (X >0)   { barra.numColumnas += columnasAdicionales; }
        else        { barra.numColumnas -= columnasAdicionales; }

        if (Y<0)    { barra.numFilas += filasAdicionales; }
        else        { barra.numFilas -= filasAdicionales; }

        barra.crearTabla();
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
