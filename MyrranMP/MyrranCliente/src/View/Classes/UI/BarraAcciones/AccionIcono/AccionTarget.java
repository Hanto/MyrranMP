package View.Classes.UI.BarraAcciones.AccionIcono;// Created by Hanto on 13/05/2014.

import Interfaces.UI.BarraAcciones.ControladorBarraAccionI;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class AccionTarget extends Target
{
    private AccionIcono accionIcono;
    private DragAndDrop dad;
    private ControladorBarraAccionI controlador;

    public AccionTarget(AccionIcono accionIcono, DragAndDrop dad, ControladorBarraAccionI controlador)
    {
        super(accionIcono.getApariencia());
        this.accionIcono = accionIcono;
        this.dad = dad;
        this.controlador = controlador;
    }




    @Override public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
    {   return true; }

    @Override public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload)
    {   super.reset(source, payload); }

    @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
    {
        AccionIcono origen = ((AccionIcono) payload.getObject());
        controlador.barraAccionMoverAccion(origen.barra, origen.posX, origen.posY, accionIcono.barra, accionIcono.posX, accionIcono.posY);
    }
}
