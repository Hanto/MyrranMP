package View.Classes.UI.ConjuntoBarraAccionView.IconoAccion;// Created by Hanto on 13/05/2014.

import Controller.Interfaces.ControladorBarraAccionI;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class AccionTarget extends Target
{
    private IconoAccion iconoAccion;
    private DragAndDrop dad;
    private ControladorBarraAccionI controlador;

    public AccionTarget(IconoAccion iconoAccion, DragAndDrop dad, ControladorBarraAccionI controlador)
    {
        super(iconoAccion.getApariencia());
        this.iconoAccion = iconoAccion;
        this.dad = dad;
        this.controlador = controlador;
    }




    @Override public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
    {   return true; }

    @Override public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload)
    {   super.reset(source, payload); }

    @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
    {
        IconoAccion origen = ((IconoAccion) payload.getObject());
        controlador.barraAccionMoverAccion(origen.barra, origen.posX, origen.posY, iconoAccion.barra, iconoAccion.posX, iconoAccion.posY);
    }
}
