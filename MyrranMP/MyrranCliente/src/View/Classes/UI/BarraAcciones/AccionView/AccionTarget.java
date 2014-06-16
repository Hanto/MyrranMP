package View.Classes.UI.BarraAcciones.AccionView;// Created by Hanto on 13/05/2014.

import Interfaces.UI.BarraAcciones.ControladorBarraAccionI;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class AccionTarget extends Target
{
    private AccionView accionView;
    private DragAndDrop dad;
    private ControladorBarraAccionI controlador;

    public AccionTarget(AccionView accionView, DragAndDrop dad, ControladorBarraAccionI controlador)
    {
        super(accionView.getApariencia());
        this.accionView = accionView;
        this.dad = dad;
        this.controlador = controlador;
    }




    @Override public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
    {   return true; }

    @Override public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload)
    {   super.reset(source, payload); }

    @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
    {
        AccionView origen = ((AccionView) payload.getObject());
        controlador.barraAccionMoverAccion(origen.barra, origen.posX, origen.posY, accionView.barra, accionView.posX, accionView.posY);
    }
}
