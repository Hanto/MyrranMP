package View.Classes.UI.Comun;// Created by Hanto on 13/05/2014.

import Data.MiscData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class VentanaMoverListener extends DragListener
{
    private Actor actor;
    private Actor dragActor;

    public VentanaMoverListener(Actor dragActor, Actor actor)
    {
        this.dragActor = dragActor;
        this.actor = actor;
    }

    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        float newX = actor.getX() - dragActor.getWidth()/2 + x;
        float newY = actor.getY() - dragActor.getHeight()/2 + y;
        float alto = actor.getHeight();
        float ancho = actor.getWidth();

        if (newX - dragActor.getWidth() < 0) newX = 0 + dragActor.getWidth();
        if (newY < 0) newY = 0;
        if (newX + ancho > MiscData.GDX_Horizontal_Resolution) newX = MiscData.GDX_Horizontal_Resolution - ancho;
        if (newY + alto > MiscData.GDX_Vertical_Resolution) newY = MiscData.GDX_Vertical_Resolution - alto;

        actor.setPosition((int)newX, (int)newY);
    }
}
