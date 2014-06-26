package View.Classes.UI.Ventana;// Created by Hanto on 13/05/2014.

import Data.MiscData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class VentanaMoverListener extends DragListener
{
    private Actor actor;
    private Actor dragActor;

    private float anchoDragActor;
    private float altoDragActor;

    private Vector2 posicionInicial = new Vector2();
    private Vector2 posicionFinal = new Vector2();

    public VentanaMoverListener(Actor dragActor, Actor actor)
    {
        this.dragActor = dragActor;
        this.actor = actor;

        this.anchoDragActor = dragActor.getWidth();
        this.altoDragActor = dragActor.getHeight();
    }


    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        posicionFinal.set(x, y);

        float scrollX = posicionFinal.x - posicionInicial.x;
        float scrollY = posicionFinal.y - posicionInicial.y;

        float dragActorX = dragActor.getParent().getX() + dragActor.getX();
        float dragACtorY = dragActor.getParent().getY() + dragActor.getY();

        if (dragActorX + scrollX < 0)                                                           { actor.setX(getActorX(0)); }
        else if (dragActorX + anchoDragActor + scrollX > MiscData.GDX_Horizontal_Resolution)    { actor.setX(getActorX(MiscData.GDX_Horizontal_Resolution-anchoDragActor));}
        else                                                                                    { actor.setX(actor.getX() + scrollX); }

        if (dragACtorY + scrollY < 0)                                                           { actor.setY(getActorY(0)); }
        else if (dragACtorY + altoDragActor + scrollY > MiscData.GDX_Vertical_Resolution)       { actor.setY(getActorY(MiscData.GDX_Vertical_Resolution-altoDragActor)); }
        else                                                                                    { actor.setY(actor.getY() + scrollY); }

        posicionFinal.set(posicionInicial);
    }

    @Override public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
    {
        actor.toFront();
        posicionInicial.set(x,y);
        return true;
    }

    private float getActorX(float posX)                 { return posX - dragActor.getX(); }
    private float getActorY(float posY)                 { return posY - dragActor.getY(); }
}
