package Controller.Input.Binds;// Created by Hanto on 05/05/2014.

import Controller.Input.PlayerEstado;
import Data.Spell.SpellsData;
import Model.Classes.Acciones.Accion;
import Model.Classes.Acciones.TiposAccion.*;
import Model.Classes.Mobiles.Player;
import Model.DAO.DAO;

import java.util.HashMap;
import java.util.Map;

public class Keybinds
{
    private Player player;
    private PlayerEstado playerE;

    public Map<Integer, String> listaDeBinds = new HashMap<>();
    public Map<String, Accion> listaDeAcciones = new HashMap<>();



    public Keybinds (/*Player player,*/ PlayerEstado playerE)
    {   this.player = player; this.playerE = playerE;

        Accion spell = new SeleccionarSpell(DAO.spellDAOFactory.getSpellDAO().getSpell(SpellsData.TERRAFORMAR_ID));

        listaDeAcciones.put(spell.getID(), spell);
    }

    public void keyDown(int keycode)
    {
        if (listaDeBinds.containsKey(keycode))
            listaDeAcciones.get(listaDeBinds.get(keycode)).ejecutarAccion(player, playerE);
    }
}
