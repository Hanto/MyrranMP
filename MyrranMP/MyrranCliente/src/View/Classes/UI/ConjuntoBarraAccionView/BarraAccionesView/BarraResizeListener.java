package View.Classes.UI.ConjuntoBarraAccionView.BarraAccionesView;// Created by Hanto on 13/05/2014.

import Controller.Interfaces.ControladorLisTaRedimensionableI;
import Data.MiscData;
import Model.Classes.UIO.ConjuntoBarraAcciones.ListaRedimensionableI;
import View.Classes.Graficos.Caja;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class BarraResizeListener extends DragListener
{
    private ControladorLisTaRedimensionableI controlador;
    private ListaRedimensionableI barraModel;
    private Actor dragActor;
    private Actor actor;

    private float redimensionarX;
    private float redimensionarY;
    private Caja caja = new Caja();

    public BarraResizeListener(Actor dragActor, Actor barraAccionesView, ListaRedimensionableI barraModel, ControladorLisTaRedimensionableI controlador)
    {
        this.controlador = controlador;
        this.barraModel = barraModel;
        this.actor = barraAccionesView;
        this.dragActor = dragActor;
    }


    @Override public void touchUp (InputEvent event, float x, float y, int pointer, int button)
    {
        dragActor.getStage().getRoot().removeActor(caja);

        int X = (int)(event.getStageX()-redimensionarX);
        int Y = (int)(event.getStageY()-redimensionarY);

        int columnasAdicionales = Math.round((float)Math.abs(X)/(float)(MiscData.BARRASPELLS_Ancho_Casilla+2));
        int filasAdicionales = Math.round((float)Math.abs(Y)/(float)(MiscData.BARRASPELLS_Alto_Casilla+2));

        if (X >0)
        {
            for (int i=0; i<columnasAdicionales; i++)
            {   controlador.barraAñadirColumna(barraModel); }
        }
        else
        {
            for (int i=0; i<columnasAdicionales; i++)
            {   controlador.barraEliminarColumna(barraModel); }
        }

        if (Y<0)
        {
            for (int i=0; i<filasAdicionales; i++)
            {   controlador.barraAñadirFila(barraModel); }
        }
        else
        {
            for (int i=0; i<filasAdicionales; i++)
            {   controlador.barraEliminarFila(barraModel); }
        }
    }

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        caja.setCaja(actor.getX(), actor.getY() + actor.getHeight(),
                     actor.getWidth(), -actor.getHeight());

        dragActor.getStage().addActor(caja);

        redimensionarX = (int)event.getStageX();
        redimensionarY = (int)event.getStageY();
        return true;
    }

    @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
    {
        float newX = dragActor.getX() - dragActor.getWidth()/2 + x;
        float newY = dragActor.getY() - dragActor.getHeight()/2 + y;

        dragActor.setPosition(newX, newY);

        caja.setTamaño(newX, newY - actor.getHeight() + dragActor.getHeight());
    }
}
