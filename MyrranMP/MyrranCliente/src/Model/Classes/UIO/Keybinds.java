package Model.Classes.UIO;// Created by Hanto on 05/05/2014.

import Model.Classes.Mobiles.Player;
import Model.DAO.DAO;

import java.util.HashMap;
import java.util.Map;

public class Keybinds
{
    private Player player;
    private PlayerEstado playerE;

    public Map<Integer, String> listaDeBinds = new HashMap<>();


    public Keybinds (Player player, PlayerEstado playerE)
    {   this.player = player; this.playerE = playerE;  }

    public void keyDown(int keycode)
    {
        if (listaDeBinds.containsKey(keycode))
        {
            String idAccion = listaDeBinds.get(keycode);
            DAO.accionDAOFactory.getAccionDAO().getAccion(idAccion).accionKeyDown(player, playerE);
        }
    }

    public void keyUp (int keycode)
    {
        if (listaDeBinds.containsKey(keycode))
        {
            String idAccion = listaDeBinds.get(keycode);
            DAO.accionDAOFactory.getAccionDAO().getAccion(idAccion).accionKeyUp(player, playerE);
        }
    }

    public void touchDown(int screenX, int screenY, int pointer, int button)
    {
        playerE.getPlayerI().screenX = screenX;
        playerE.getPlayerI().screenY = screenY;
        playerE.getPlayerI().startCastear = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    public void touchUp(int screenX, int screenY, int pointer, int button)
    {
        playerE.getPlayerI().screenX = screenX;
        playerE.getPlayerI().screenY = screenY;
        playerE.getPlayerI().stopCastear = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    public void touchDragged(int screenX, int screenY, int pointer)
    {
        playerE.getPlayerI().screenX = screenX;
        playerE.getPlayerI().screenY = screenY;
        playerE.getPlayerI().startCastear = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }
}
