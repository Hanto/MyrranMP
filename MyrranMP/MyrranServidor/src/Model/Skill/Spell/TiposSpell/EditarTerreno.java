package Model.Skill.Spell.TiposSpell;
// @author Ivan Delgado Huerta

import Data.Spell.TipoSpellsData;
import Interfaces.Caster;
import Model.Skill.SkillStat;
import Model.Skill.Spell.Spell;
import Model.Skill.Spell.TipoSpell;

public class EditarTerreno extends TipoSpell
{
    public EditarTerreno()                         { }
    
    @Override public void inicializarSkillStats() 
    {
        skillStats = new SkillStat[1]; SkillStat stat;
        stat = new SkillStat  (TipoSpellsData.EDITARTERRENO_CastingTime_String, TipoSpellsData.EDITARTERRENO_CastingTime_Valor); skillStats[STAT_Cast]=stat;//CAST
    }

    @Override public void ejecutarCasteo(Spell skill, Caster caster, float targetX, float targetY)
    {
        /*Vector2 destino = new Vector2(targetX, targetY);
        destino = convertirCoordenadasANumeroDeTile(destino);
        
        int x = (int)destino.x;
        int y = (int)destino.y;
/*
        int numCapa = caster.getCapaTerrenoSeleccionada();
        String iDTerreno = Mundo.get().player.getTerrenoSeleccionado();
*/
        caster.getMapa().setTerreno((int)targetX, (int)targetY, 0, 1);
    }
}