package View.UI.BarraAccionesView;// Created by Hanto on 13/05/2014.

import Data.MiscData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class BarraMoverListener extends DragListener
{
    private BarraAccionesView barraView;
    private Actor actor;

    public BarraMoverListener (Actor actor, BarraAccionesView barraView)
    {
        this.actor = actor;
        this.barraView = barraView;
    }

    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        float newX = barraView.getX() - actor.getWidth()/2 + x;
        float newY = barraView.getY() - actor.getHeight()/2 + y;
        float alto = barraView.getHeight();
        float ancho = barraView.getWidth();

        if (newX - actor.getWidth() < 0) newX = 0 + actor.getWidth();
        if (newY < 0) newY = 0;
        if (newX + ancho > MiscData.GDX_Window_Horizontal_Resolution) newX = MiscData.GDX_Window_Horizontal_Resolution - ancho;
        if (newY + alto > MiscData.GDX_Window_Vertical_Resolution) newY = MiscData.GDX_Window_Vertical_Resolution - alto;

        barraView.setPosition(newX, newY);
    }
}
