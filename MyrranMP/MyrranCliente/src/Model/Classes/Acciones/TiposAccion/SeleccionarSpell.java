package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Controller.Controlador;
import Data.Spell.SpellsData;
import Model.Classes.Acciones.Accion;
import Model.Classes.Mobiles.Player;
import Model.Classes.Skill.Spell.Spell;
import Model.Classes.UI.Input.PlayerEstado;

public class SeleccionarSpell extends Accion
{
    public SeleccionarSpell(Spell spell)
    {
        iD = spell.getID();
        parametros = spell.getID();
    }

    @Override public void accionKeyDown(Player player, PlayerEstado playerE, Controlador controlador)
    {
        if (iD.equals(SpellsData.TERRAFORMAR_ID)) {/* player.setParametrosSpell(new ParametrosEditarTerreno());*/ controlador.mostrarBarraTerrenos();}

        player.setSpellIDSeleccionado((String)parametros);
    }

    @Override public void accionKeyUp(Player player, PlayerEstado playerE, Controlador controlador)
    {
        if (iD.equals(SpellsData.TERRAFORMAR_ID)) controlador.ocultarBarraTerrenos();
    }
}
