package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Model.Classes.Acciones.Accion;
import Model.Classes.Acciones.IconoInterface;
import Controller.Input.PlayerEstado;
import Model.Classes.Mobiles.Player;
import Model.Classes.Skill.Spell.Spell;
import Recursos.DAO.RSC;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SeleccionarSpell extends Accion implements IconoInterface
{
    public SeleccionarSpell(Spell spell)
    {
        iD = spell.getID();
        parametros = spell.getID();
    }

    @Override public void ejecutarAccion(Player player, PlayerEstado playerE)
    {   player.setSpellIDSeleccionado((String)parametros); }

    @Override public TextureRegion getImagen()
    {   return RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos((String)parametros).getIcono(); }

    @Override public String getID()
    {   return (String)parametros; }
}
