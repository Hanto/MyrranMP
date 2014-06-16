package View.Classes.UI.BarraTerrenos.TerrenoView;// Created by Hanto on 14/05/2014.

import Interfaces.UI.BarraTerrenos.ControladorBarraTerrenosI;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class TerrenoTarget extends Target
{
    private TerrenoView terrenoView;
    private ControladorBarraTerrenosI controlador;

    public TerrenoTarget(TerrenoView terrenoView, ControladorBarraTerrenosI controlador)
    {
        super(terrenoView.getApariencia());
        this.terrenoView = terrenoView;
        this.controlador = controlador;
    }

    @Override public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer)
    {   return true; }

    @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer)
    {
        TerrenoView origen = (TerrenoView)payload.getObject();
        controlador.barraTerrenosMoverTerreno(origen.getPosX(), terrenoView.getPosX());
    }
}
