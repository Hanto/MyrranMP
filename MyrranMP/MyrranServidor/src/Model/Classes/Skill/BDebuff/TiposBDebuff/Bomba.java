package Model.Classes.Skill.BDebuff.TiposBDebuff;// Created by Hanto on 26/06/2014.

import Data.MiscData;
import Interfaces.BDebuff.AuraI;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Model.Classes.Skill.BDebuff.TipoBDebuff;

public class Bomba extends TipoBDebuff
{
    public final int STAT_Daño = 1;

    @Override public void inicializarSkillStats()
    {
        setID(this.getClass().getSimpleName().toUpperCase());
        setNumSkillStats(2);
    }

    @Override public void actualizarTick(AuraI aura)
    {
        if (aura.getTicksAplicados() == (int)aura.getDuracionMax()/ MiscData.BDEBUFF_DuracionTick)
        {
            float daño = aura.getDebuff().getValorTotal(aura.getCaster(), STAT_Daño) * aura.getStacks();

            if(aura.getTarget() instanceof Vulnerable)
            {((Vulnerable)aura.getTarget()).modificarHPs((int) -daño); }
        }
    }
}
