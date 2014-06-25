package Model.Classes.Skill.Spell.TiposSpell;
// @author Ivan Delgado Huerta

import Data.MiscData;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.Spell.SpellI;
import Model.Classes.Skill.Spell.TipoSpell;

import static DTO.ParametrosSpellDTO.ParametrosEditarTerreno;

public class EditarTerreno extends TipoSpell
{
    @Override public void inicializarSkillStats() 
    {
        setID(this.getClass().getSimpleName().toUpperCase());
        setNumSkillStats(1);
    }

    @Override public void ejecutarCasteo(SpellI skill, Caster caster, int targetX, int targetY)
    {
        int tileX = (targetX / MiscData.TILESIZE);
        int tileY = (targetY / MiscData.TILESIZE);

        int numCapa = 0;
        short iDTerreno = 0;

        if (caster.getParametrosSpell() instanceof ParametrosEditarTerreno)
        {
            numCapa = ((ParametrosEditarTerreno) caster.getParametrosSpell()).capaTerrenoSeleccionada;
            iDTerreno = ((ParametrosEditarTerreno) caster.getParametrosSpell()).terrenoIDSeleccionado;
        }

        caster.getMapa().setTerreno(tileX, tileY, numCapa, iDTerreno);
    }
}
