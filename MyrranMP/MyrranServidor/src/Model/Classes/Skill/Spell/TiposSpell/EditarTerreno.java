package Model.Classes.Skill.Spell.TiposSpell;
// @author Ivan Delgado Huerta

import Data.MiscData;
import Data.Spell.TipoSpellsData;
import Interfaces.Caster;
import Model.Classes.Skill.SkillStat;
import Model.Classes.Skill.Spell.Spell;
import Model.Classes.Skill.Spell.TipoSpell;

public class EditarTerreno extends TipoSpell
{
    @Override public void inicializarSkillStats() 
    {
        skillStats = new SkillStat[1]; SkillStat stat;
        stat = new SkillStat  (TipoSpellsData.EDITARTERRENO_CastingTime_String, TipoSpellsData.EDITARTERRENO_CastingTime_Valor); skillStats[STAT_Cast]=stat;//CAST
    }

    @Override public void ejecutarCasteo(Spell skill, Caster caster, int targetX, int targetY)
    {
        int tileX = (targetX / MiscData.TILESIZE);
        int tileY = (targetY / MiscData.TILESIZE);

        int numCapa = caster.getCapaTerrenoSeleccionada();
        int iDTerreno = caster.getTerrenoIDSeleccionado();

        caster.getMapa().setTerreno(tileX, tileY, numCapa, iDTerreno);
    }
}
