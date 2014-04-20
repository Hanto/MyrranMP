package Model.Skill.Spell.TiposSpell;
// @author Ivan Delgado Huerta

import Data.Spell.TipoSpellsData;
import Model.Skill.SkillStat;
import Model.Skill.Spell.Spell;

public class EditarTerreno extends Spell
{
    @Override public void inicializarSkillStats()
    {
        skillStats = new SkillStat[1]; SkillStat stat;
        stat = new SkillStat  (TipoSpellsData.EDITARTERRENO_CastingTime_String, TipoSpellsData.EDITARTERRENO_CastingTime_Valor); skillStats[STAT_Cast]=stat;//CAST
    }
}
