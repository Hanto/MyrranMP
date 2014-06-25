package Model.Classes.Skill.Spell.TiposSpell;
// @author Ivan Delgado Huerta

import Model.Classes.Skill.Spell.TipoSpell;

public class EditarTerreno extends TipoSpell
{
    @Override public void inicializarSkillStats()
    {
        setID(this.getClass().getSimpleName().toUpperCase());
        setNumSkillStats(1);
    }
}
