package Model.Classes.Skill.Spell;// Created by Hanto on 26/03/2014.

import Interfaces.Caster;

public interface TipoSpellInterface
{
    public void inicializarSkillStats();
    public void ejecutarCasteo(Spell spell, Caster caster, int targetX, int targetY);
}
