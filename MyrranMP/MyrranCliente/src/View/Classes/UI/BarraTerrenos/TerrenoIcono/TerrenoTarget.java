package View.Classes.UI.BarraTerrenos.TerrenoIcono;// Created by Hanto on 14/05/2014.

import Interfaces.UI.BarraTerrenos.ControladorBarraTerrenosI;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class TerrenoTarget extends Target
{
    private TerrenoIcono terrenoIcono;
    private ControladorBarraTerrenosI controlador;

    public TerrenoTarget(TerrenoIcono terrenoIcono, ControladorBarraTerrenosI controlador)
    {
        super(terrenoIcono.getApariencia());
        this.terrenoIcono = terrenoIcono;
        this.controlador = controlador;
    }

    @Override public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer)
    {   return true; }

    @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer)
    {
        TerrenoIcono origen = (TerrenoIcono)payload.getObject();
        controlador.barraTerrenosMoverTerreno(origen.getPosX(), terrenoIcono.getPosX());
    }
}
