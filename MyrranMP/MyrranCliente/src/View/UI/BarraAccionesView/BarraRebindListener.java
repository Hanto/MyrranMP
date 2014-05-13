package View.UI.BarraAccionesView;// Created by Hanto on 13/05/2014.

import Controller.Controlador;
import Model.Classes.UIO.ConjuntoBarraAcciones.ListaAccionesBI;
import View.UI.ConjuntoBarraAccionesView;
import View.UI.DragAndDrop.IconoAccion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class BarraRebindListener extends InputListener
{
    private ConjuntoBarraAccionesView conjuntoBarraAccionesView;
    private IconoAccion icono;
    private Controlador controlador;


    public BarraRebindListener(IconoAccion icono, ConjuntoBarraAccionesView conjuntoBarraAccionesView, Controlador controlador)
    {
        this.conjuntoBarraAccionesView = conjuntoBarraAccionesView;
        this.icono = icono;
        this.controlador = controlador;
    }


    @Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
    {
        if (conjuntoBarraAccionesView.getRebindearSkills())
           icono.getApariencia().getStage().setKeyboardFocus(icono.getApariencia());
    }

    //Hacemos que deje de recibir eventos de teclado, puesto que el teclado ha salido
    @Override public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
    {
        if (conjuntoBarraAccionesView.getRebindearSkills())
            icono.getApariencia().getStage().setKeyboardFocus(null);
    }

    //Capturamos que tecla aprieta el player para rebindearla
    @Override public boolean keyDown(InputEvent event, int keycode)
    {   //Solo rebindeamos los skills, si esta activado el boton de rebindear
        if (conjuntoBarraAccionesView.getRebindearSkills() && icono.barra instanceof  ListaAccionesBI)
        {   controlador.barraAccionRebindear((ListaAccionesBI)icono.barra, icono.posX, icono.posY, keycode); }
        return true;
    }
}
