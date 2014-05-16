package View.Classes.UI.BarraAcciones.BarraAccionesView;// Created by Hanto on 13/05/2014.

import Controller.Interfaces.ControladorBarraAccionI;
import Model.Classes.UI.ConjuntoBarraAcciones.BarraAccionesI;
import View.Classes.UI.BarraAcciones.AccionIcono.AccionIcono;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class BAccionesRebindListener extends InputListener
{
    private ConjuntoBarraAccionesView conjuntoBarraAccionesView;
    private AccionIcono icono;
    private ControladorBarraAccionI controlador;


    public BAccionesRebindListener(AccionIcono icono, ConjuntoBarraAccionesView conjuntoBarraAccionesView, ControladorBarraAccionI controlador)
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
        if (conjuntoBarraAccionesView.getRebindearSkills() && icono.getBarra() instanceof BarraAccionesI)
        {   controlador.barraAccionRebindear((BarraAccionesI)icono.getBarra(), icono.getPosX(), icono.getPosY(), keycode); }
        return true;
    }

    @Override public boolean keyUp (InputEvent event, int keycode)
    {   return true; }
}
