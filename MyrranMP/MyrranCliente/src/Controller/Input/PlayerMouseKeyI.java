package Controller.Input;
// @author Ivan Delgado Huerta

import Controller.Controlador;
import com.badlogic.gdx.InputProcessor;

public class PlayerMouseKeyI implements InputProcessor
{
    //private PlayerEstado playerE;
    //private PlayerIO playerI;
    private Controlador controlador;

    //CONSTRUCTOR:
    public PlayerMouseKeyI(Controlador controlador)
    {
        //playerE = playerEstado;
        //playerI = playerE.getPlayerI();
        this.controlador = controlador;
    }

    @Override public boolean keyDown(int keycode)
    {
        controlador.procesarKeyDown(keycode);
        //if (keycode == playerI.teclaArriba)     playerI.irArriba = true;
        //if (keycode == playerI.teclaAbajo)      playerI.irAbajo = true;
        //if (keycode == playerI.teclaIzquierda)  playerI.irIzquierda = true;
        //if (keycode == playerI.teclaDerecha)    playerI.irDerecha = true;
        //playerE.procesarInput();
        //controlador.aplicarInputAPlayer();
        return false;
    }
    
    @Override public boolean keyUp(int keycode)                                             
    {
        controlador.procesarKeyUp(keycode);
        //if (keycode == playerI.teclaArriba)     playerI.irArriba = false;
        //if (keycode == playerI.teclaAbajo)      playerI.irAbajo = false;
        //if (keycode == playerI.teclaIzquierda)  playerI.irIzquierda = false;
        //if (keycode == playerI.teclaDerecha)    playerI.irDerecha = false;
        //playerE.procesarInput();
        //controlador.aplicarInputAPlayer();
        return false;
    }
    
    @Override public boolean keyTyped(char character)                                       { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        controlador.procesarTouchDown(screenX, screenY, pointer, button);
        /*playerI.screenX = screenX;
        playerI.screenY = screenY;
        playerI.startCastear = true;
        playerE.procesarInput();
        controlador.aplicarInputAPlayer();*/
        return true;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        controlador.procesarTouchUp(screenX, screenY, pointer, button);
        /*playerI.screenX = screenX;
        playerI.screenY = screenY;
        playerI.stopCastear = true;
        playerE.procesarInput();
        controlador.aplicarInputAPlayer();*/
        return true;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        controlador.procesarTouchDragged(screenX, screenY, pointer);
        /*playerI.screenX = screenX;
        playerI.screenY = screenY;
        playerI.startCastear = true;
        playerE.procesarInput();
        controlador.aplicarInputAPlayer();*/
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY)                           { return false; }
    @Override public boolean scrolled(int amount)
    {
        if (amount>0)   { controlador.aplicarZoom(1); }
        if (amount <0)  { controlador.aplicarZoom(-1); }
        return false;
    }
}