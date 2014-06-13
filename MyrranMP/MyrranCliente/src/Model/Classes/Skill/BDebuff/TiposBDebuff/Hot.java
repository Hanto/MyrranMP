package Model.Classes.Skill.BDebuff.TiposBDebuff;// Created by Hanto on 10/06/2014.

import Core.SkillStat;
import Data.TipoBDebuffsData;
import Interfaces.BDebuff.AuraI;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Model.Classes.Skill.BDebuff.TipoBDebuff;

public class Hot extends TipoBDebuff
{
    public static final int STAT_Heal = 1;

    @Override public void inicializarSkillStats()
    {
        setID(TipoBDebuffsData.HOT_ID);
        setNombre(TipoBDebuffsData.HOT_Nombre);
        setDescripcion(TipoBDebuffsData.HOT_Descripcion);
        setIsDebuff(TipoBDebuffsData.HOT_isDebuff);
        setStacksMaximos(TipoBDebuffsData.HOT_Stacks_Maximos);

        skillStats = new SkillStat[2];
        skillStats[STAT_Duracion]   = new SkillStat (TipoBDebuffsData.HOT_Duracion_String, TipoBDebuffsData.HOT_Duracion_Valor);  //DURACION
        skillStats[STAT_Heal]       = new SkillStat (TipoBDebuffsData.HOT_Daño_String, TipoBDebuffsData.HOT_Daño_Valor);          //Heal
    }

    @Override public void actualizarAura(AuraI aura)
    {
        float HPsPorTick = aura.getDebuff().skillStat()[STAT_Heal].getValorBase() * aura.getStacks();

        if (aura.getTarget() instanceof Vulnerable)
        {   ((Vulnerable)aura.getTarget()).modificarHPs((int)+HPsPorTick); }
    }
}
