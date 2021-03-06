package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import DB.DAO;
import Data.MiscData;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesTipos.MobPlayer;
import Interfaces.Input.PlayerEstadoI;
import Interfaces.Spell.SpellI;
import Interfaces.UI.ControladorUI;
import Model.Classes.Acciones.Accion;

public class SeleccionarSpell extends Accion
{
    public SeleccionarSpell(SpellI spell)
    {
        iD = spell.getID();
        parametros = spell.getTipoSpell().getID();
    }

    public SeleccionarSpell(String idSpell)
    {
        SpellI spell = DAO.spellDAOFactory.getSpellDAO().getSpell(idSpell);
        iD = spell.getID();
        parametros = spell.getTipoSpell().getID();
    }

    @Override public void accionKeyDown(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {
        if (parametros.equals(MiscData.BARRATERRENOS_EditarTerrenoID)) { controlador.mostrarBarraTerrenos(); /*player.setParametrosSpell(new ParametrosEditarTerreno());*/}

        if (player instanceof Caster)
            ((Caster)player).setSpellIDSeleccionado(iD);
    }

    @Override public void accionKeyUp(MobPlayer player, PlayerEstadoI playerE, ControladorUI controlador)
    {
        if (parametros.equals(MiscData.BARRATERRENOS_EditarTerrenoID)) controlador.ocultarBarraTerrenos();
    }
}
