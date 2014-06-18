package Model.Classes.Skill.BDebuff.TiposBDebuff;// Created by Hanto on 10/06/2014.

import Core.SkillStat;
import Interfaces.BDebuff.AuraI;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Model.Classes.Skill.BDebuff.TipoBDebuff;

public class Hot extends TipoBDebuff
{
    public static final int STAT_Heal = 1;

    @Override public void inicializarSkillStats()
    {
        setID(this.getClass().getSimpleName().toUpperCase());
        skillStats = new SkillStat[2];
    }

    @Override public void actualizarTick(AuraI aura)
    {
        float HPsPorTick = aura.getDebuff().skillStats()[STAT_Heal].getValorBase() * aura.getStacks();

        if (aura.getTarget() instanceof Vulnerable)
        {   ((Vulnerable)aura.getTarget()).modificarHPs((int)+HPsPorTick); }
    }
}
