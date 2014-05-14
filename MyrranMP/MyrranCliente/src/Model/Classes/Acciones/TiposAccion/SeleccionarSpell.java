package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Controller.Controlador;
import Model.Classes.Acciones.Accion;
import Model.Classes.Mobiles.Player;
import Model.Classes.Skill.Spell.Spell;
import Model.Classes.UIO.PlayerEstado;

public class SeleccionarSpell extends Accion
{
    public SeleccionarSpell(Spell spell)
    {
        iD = spell.getID();
        parametros = spell.getID();
    }

    @Override public void accionKeyDown(Player player, PlayerEstado playerE, Controlador controlador)
    {   player.setSpellIDSeleccionado((String)parametros); }

    @Override public void accionKeyUp(Player player, PlayerEstado playerE, Controlador controlador)
    { }
}
