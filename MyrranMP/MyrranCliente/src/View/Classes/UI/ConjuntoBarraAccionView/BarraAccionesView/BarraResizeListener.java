package View.Classes.UI.ConjuntoBarraAccionView.BarraAccionesView;// Created by Hanto on 13/05/2014.

import Controller.Interfaces.ControladorBarraAccionI;
import Data.MiscData;
import Model.Classes.UIO.ConjuntoBarraAcciones.BarraAcciones;
import View.Classes.Graficos.Caja;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class BarraResizeListener extends DragListener
{
    private ControladorBarraAccionI controlador;
    private BarraAcciones barraModel;
    private BarraAccionesView barraView;
    private Actor actor;

    private float redimensionarX;
    private float redimensionarY;
    private Caja caja = new Caja();

    public BarraResizeListener(Actor actor, BarraAccionesView barraAccionesView, BarraAcciones barraModel, ControladorBarraAccionI controlador)
    {
        this.controlador = controlador;
        this.barraModel = barraModel;
        this.barraView = barraAccionesView;
        this.actor = actor;
    }


    @Override public void touchUp (InputEvent event, float x, float y, int pointer, int button)
    {
        actor.getStage().getRoot().removeActor(caja);

        int X = (int)(event.getStageX()-redimensionarX);
        int Y = (int)(event.getStageY()-redimensionarY);

        int columnasAdicionales = Math.round((float)Math.abs(X)/(float)(MiscData.BARRASPELLS_Ancho_Casilla+2));
        int filasAdicionales = Math.round((float)Math.abs(Y)/(float)(MiscData.BARRASPELLS_Alto_Casilla+2));

        if (X >0)
        {
            for (int i=0; i<columnasAdicionales; i++)
            {   controlador.barraAñadirColumna(barraModel); }
            barraView.recrearTabla();
        }
        else
        {
            for (int i=0; i<columnasAdicionales; i++)
            {   controlador.barraEliminarColumna(barraModel); }
            barraView.recrearTabla();
        }

        if (Y<0)
        {
            for (int i=0; i<filasAdicionales; i++)
            {   controlador.barraAñadirFila(barraModel); }
            barraView.recrearTabla();
        }
        else
        {
            for (int i=0; i<filasAdicionales; i++)
            {   controlador.barraEliminarFila(barraModel); }
            barraView.recrearTabla();
        }
    }

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        caja.setCaja (barraView.getEsquinaSupIzdaX(), barraView.getEsquinaSupIzdaY(),
                      barraView.getWidth(), -barraView.getHeight());

        actor.getStage().addActor(caja);

        redimensionarX = (int)event.getStageX();
        redimensionarY = (int)event.getStageY();
        return true;
    }

    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        float newX = actor.getX() -actor.getWidth()/2 + x;
        float newY = actor.getY() -actor.getHeight()/2 + y;

        actor.setPosition(newX, newY);

        caja.setTamaño(newX, newY - barraView.getHeight() + actor.getHeight());
    }
}
