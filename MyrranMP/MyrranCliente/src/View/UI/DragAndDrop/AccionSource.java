package View.UI.DragAndDrop;// Created by Hanto on 13/05/2014.

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;

public class AccionSource extends Source
{
    private IconoAccion iconoAccion;
    private DragAndDrop dad;

    public AccionSource(IconoAccion iconoAccion, DragAndDrop dad)
    {
        super(iconoAccion.getApariencia());
        this.iconoAccion = iconoAccion;
        this.dad = dad;
    }



    @Override public Payload dragStart(InputEvent event, float x, float y, int pointer)
    {
        if (iconoAccion.barra.getAccion(iconoAccion.posX, iconoAccion.posY) != null)
        {
            Payload payload = new DragAndDrop.Payload();
            Group dragActor = iconoAccion.getDragActor();
            dad.setDragActorPosition(-dragActor.getWidth() / 2, dragActor.getHeight() / 2);
            payload.setDragActor(dragActor);
            payload.setObject(iconoAccion);
            return payload;
        }
        return null;
    }
}
