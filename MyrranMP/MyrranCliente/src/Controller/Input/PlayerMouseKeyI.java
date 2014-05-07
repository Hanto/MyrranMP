package Controller.Input;
// @author Ivan Delgado Huerta

import Controller.Controlador;
import com.badlogic.gdx.InputProcessor;

public class PlayerMouseKeyI implements InputProcessor
{
    private Controlador controlador;

    //CONSTRUCTOR:
    public PlayerMouseKeyI(Controlador controlador)
    {   this.controlador = controlador; }

    @Override public boolean keyDown(int keycode)
    {
        controlador.procesarKeyDown(keycode);
        return false;
    }
    
    @Override public boolean keyUp(int keycode)                                             
    {
        controlador.procesarKeyUp(keycode);
        return false;
    }
    
    @Override public boolean keyTyped(char character)
    {   return false; }

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        controlador.procesarTouchDown(screenX, screenY, pointer, button);
        return true;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        controlador.procesarTouchUp(screenX, screenY, pointer, button);
        return true;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        controlador.procesarTouchDragged(screenX, screenY, pointer);
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY)
    {   return false; }

    @Override public boolean scrolled(int amount)
    {
        if (amount>0)   { controlador.aplicarZoom(1); }
        if (amount <0)  { controlador.aplicarZoom(-1); }
        return false;
    }
}