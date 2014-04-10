package Controller.Input;
// @author Ivan Delgado Huerta

import Modelo.Mobiles.PlayerModel;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class PlayerInput implements InputProcessor
{
    private ControladorCliente controlador;

    //CONSTRUCTOR:
    public PlayerInput(ControladorCliente controlador)
    {   this.controlador = controlador; }

    @Override
    public boolean keyDown(int keycode) 
    {
        PlayerModel player = controlador.getMundo().getPlayer();
        switch (keycode)
        {

            case Keys.W:    controlador.moverPlayer(player.getX(), player.getY()+3); break;
            case Keys.S:    controlador.moverPlayer(player.getX(), player.getY()-3); break;
            case Keys.A:    controlador.moverPlayer(player.getX()-3, player.getY()); break;
            case Keys.D:    controlador.moverPlayer(player.getX()+3, player.getY()); break;
        }
        return false;
    }
    
    @Override public boolean keyUp(int keycode)                                             
    { 
        switch (keycode)
        {
            //case Keys.W:    {Mundo.get().player.irArriba = false; break; }
            //case Keys.S:    {Mundo.get().player.irAbajo = false; break;}
            //case Keys.A:    {Mundo.get().player.irIzquierda = false; break;}
            //case Keys.D:    {Mundo.get().player.irDerecha = false; break;}
        }
        return false;
    }
    
    @Override public boolean keyTyped(char character)                                       { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)   
    {

        return true;
    }
    
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return true;
    }
    
    @Override public boolean touchDragged(int screenX, int screenY, int pointer)
    {

        return false;
    }
    @Override public boolean mouseMoved(int screenX, int screenY)
    {

        return false;
    }
    @Override public boolean scrolled(int amount)                                           
    { 

        return false; 
    }
}