package Model.Classes.Skill.Spell.TiposSpell;// Created by Hanto on 17/06/2014.

import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Interfaces.Spell.SpellI;
import Model.Classes.Skill.Spell.TipoSpell;

public class Heal extends TipoSpell
{
    @Override public void inicializarSkillStats()
    {
        setID(this.getClass().getSimpleName().toUpperCase());
        setNumSkillStats(2);
    }

    @Override public void ejecutarCasteo(SpellI spell, Caster caster, int targetX, int targetY)
    {
        int STAT_Curacion = 1;
        float curacion = spell.getValorTotal(caster, STAT_Curacion);

        if (caster instanceof Vulnerable)
        {   ((Vulnerable)caster).modificarHPs(curacion); }

        if (caster instanceof Debuffeable)
        {   spell.aplicarDebuffs(caster, (Debuffeable)caster);}
    }
}
