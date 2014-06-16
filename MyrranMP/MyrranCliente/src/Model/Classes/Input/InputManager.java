package Model.Classes.Input;// Created by Hanto on 05/05/2014.

import Controller.Controlador;
import Interfaces.UI.Acciones.AccionI;
import Model.Classes.Mobiles.Player;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;

public class InputManager implements InputProcessor, GestureDetector.GestureListener
{
    //Model:
    private Player player;
    private PlayerEstado playerE;
    private Controlador controlador;

    //Datos:
    private Map<Integer, String> listaDeBinds = new HashMap<>();
    private Map<String, AccionI> listaDeAcciones = new HashMap<>();

    //Constructor:
    public InputManager(Player player, PlayerEstado playerE, Controlador controlador)
    {
        this.player = player;
        this.playerE = playerE;
        this.controlador = controlador;
    }



    public void eliminarKeybind (int keycode)
    {   listaDeBinds.remove(keycode); }

    public void salvarKeybind (int keycode, String iDAccion)
    {   listaDeBinds.put(keycode, iDAccion); }

    public void añadirAccion (AccionI accion)
    {   listaDeAcciones.put(accion.getID(), accion); }

    public void eliminarAccion (AccionI accion)
    {   listaDeAcciones.remove(accion.getID()); }

    public AccionI getAccion (String idAccion)
    {   return listaDeAcciones.get(idAccion); }


    //INPUT PROCESSOR:
    @Override public boolean keyDown(int keycode)
    {
        if (listaDeBinds.containsKey(keycode))
        {
            String idAccion = listaDeBinds.get(keycode);
            AccionI accion = listaDeAcciones.get(idAccion);
            if (accion != null) accion.accionKeyDown(player, playerE, controlador);
        }
        return false;
    }

    @Override public boolean keyUp (int keycode)
    {
        if (listaDeBinds.containsKey(keycode))
        {
            String idAccion = listaDeBinds.get(keycode);
            AccionI accion = listaDeAcciones.get(idAccion);
            if (accion != null) accion.accionKeyUp(player, playerE, controlador);
        }
        return false;
    }


    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        playerE.getPlayerI().setScreenX(screenX);
        playerE.getPlayerI().setScreenY(screenY);
        playerE.getPlayerI().setStartCastear(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
        return true;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        playerE.getPlayerI().setScreenX(screenX);
        playerE.getPlayerI().setScreenY(screenY);
        playerE.getPlayerI().setStopCastear(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
        return true;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        playerE.getPlayerI().setScreenX(screenX);
        playerE.getPlayerI().setScreenY(screenY);
        playerE.getPlayerI().setStartCastear(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
        return false;
    }

    @Override public boolean scrolled(int amount)
    {
        if (amount > 0)  { controlador.aplicarZoom(1); }
        if (amount < 0)  { controlador.aplicarZoom(-1); }
        return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY)
    {   return false; }

    @Override public boolean keyTyped(char character)
    {   return false; }

    //GESTURE LISTENER:
    @Override public boolean touchDown(float x, float y, int pointer, int button) { return false; }
    @Override public boolean tap(float x, float y, int count, int button) { return false; }
    @Override public boolean longPress(float x, float y) { return false; }
    @Override public boolean fling(float velocityX, float velocityY, int button) { return false; }
    @Override public boolean pan(float x, float y, float deltaX, float deltaY) { return false; }
    @Override public boolean panStop(float arg0, float arg1, int arg2, int arg3) { return false; }
    @Override public boolean zoom(float initialDistance, float distance) { return false; }
    @Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) { return false; }
}
