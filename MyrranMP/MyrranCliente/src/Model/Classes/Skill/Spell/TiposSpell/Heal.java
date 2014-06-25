package Model.Classes.Skill.Spell.TiposSpell;// Created by Hanto on 17/06/2014.

import Model.Classes.Skill.Spell.TipoSpell;

public class Heal extends TipoSpell
{
    @Override public void inicializarSkillStats()
    {
        setID(this.getClass().getSimpleName().toUpperCase());
        setNumSkillStats(2);
    }
}
