package Model.Classes.UI.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Data.Spell.SpellsData;
import Interfaces.Entidades.Caster;
import Interfaces.Entidades.MobPlayer;
import Interfaces.Spell.SpellI;
import Interfaces.UI.ControladorUI;
import Interfaces.UI.PlayerEstadoI;
import Model.Classes.UI.Acciones.Accion;

public class SeleccionarSpell extends Accion
{
    public SeleccionarSpell(SpellI spell)
    {
        iD = spell.getID();
        parametros = spell.getID();
    }

    @Override public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {
        if (iD.equals(SpellsData.TERRAFORMAR_ID)) {/* player.setParametrosSpell(new ParametrosEditarTerreno());*/ controlador.mostrarBarraTerrenos();}

        if (player instanceof Caster)
            ((Caster)player).setSpellIDSeleccionado((String)parametros);
    }

    @Override public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {
        if (iD.equals(SpellsData.TERRAFORMAR_ID)) controlador.ocultarBarraTerrenos();
    }
}
