package Model.Classes.Skill.Spell.TiposSpell;
// @author Ivan Delgado Huerta

import Core.SkillStat;
import Data.MiscData;
import Data.TipoSpellsData;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.Spell.SpellI;
import Model.Classes.Skill.Spell.TipoSpell;

import static DTO.ParametrosSpellDTO.ParametrosEditarTerreno;

public class EditarTerreno extends TipoSpell
{
    @Override public void inicializarSkillStats() 
    {
        setID(TipoSpellsData.EDITARTERRENO_ID);
        setNombre(TipoSpellsData.EDITARTERRENO_Nombre);
        setDescripcion(TipoSpellsData.EDITARTERRENO_Descripcion);

        skillStats = new SkillStat[1];
        skillStats[STAT_Cast] = new SkillStat  (TipoSpellsData.EDITARTERRENO_CastingTime_String, TipoSpellsData.EDITARTERRENO_CastingTime_Valor); //CAST
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
