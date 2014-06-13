package Model.Classes.UI.Input;// Created by Hanto on 05/05/2014.

import Controller.Controlador;
import Model.Classes.Mobiles.Player;

import java.util.HashMap;
import java.util.Map;

public class Keybinds
{
    private Player player;
    private PlayerEstado playerE;
    private Controlador controlador;

    public Map<Integer, String> listaDeBinds = new HashMap<>();


    public Keybinds (Player player, PlayerEstado playerE, Controlador controlador)
    {
        this.player = player;
        this.playerE = playerE;
        this.controlador = controlador;
    }

    public void eliminarKeybind (int keycode)
    {   listaDeBinds.remove(keycode); }

    public void salvarKeybind (int keycode, String iDAccion)
    {   listaDeBinds.put(keycode, iDAccion); }


    public void keyDown(int keycode)
    {
        if (listaDeBinds.containsKey(keycode))
        {
            String idAccion = listaDeBinds.get(keycode);
            DB.DAO.accionDAOFactory.getAccionDAO().getAccion(idAccion).accionKeyDown(player, playerE, controlador);
        }
    }

    public void keyUp (int keycode)
    {
        if (listaDeBinds.containsKey(keycode))
        {
            String idAccion = listaDeBinds.get(keycode);
            DB.DAO.accionDAOFactory.getAccionDAO().getAccion(idAccion).accionKeyUp(player, playerE, controlador);
        }
    }

    public void touchDown(int screenX, int screenY, int pointer, int button)
    {
        playerE.getPlayerI().setScreenX(screenX);
        playerE.getPlayerI().setScreenY(screenY);
        playerE.getPlayerI().setStartCastear(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    public void touchUp(int screenX, int screenY, int pointer, int button)
    {
        playerE.getPlayerI().setScreenX(screenX);
        playerE.getPlayerI().setScreenY(screenY);
        playerE.getPlayerI().setStopCastear(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    public void touchDragged(int screenX, int screenY, int pointer)
    {
        playerE.getPlayerI().setScreenX(screenX);
        playerE.getPlayerI().setScreenY(screenY);
        playerE.getPlayerI().setStartCastear(true);
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }
}
