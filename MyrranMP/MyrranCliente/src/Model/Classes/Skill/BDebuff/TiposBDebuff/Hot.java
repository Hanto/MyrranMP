package Model.Classes.Skill.BDebuff.TiposBDebuff;// Created by Hanto on 10/06/2014.

import Core.SkillStat;
import Data.TipoAurasData;
import Interfaces.BDebuff.AuraI;
import Interfaces.EntidadesPropiedades.Vulnerable;
import Model.Classes.Skill.BDebuff.TipoBDebuff;

public class Hot extends TipoBDebuff
{
    public static final int STAT_Heal = 1;

    @Override public void inicializarSkillStats()
    {
        setID(TipoAurasData.HOT_ID);
        setNombre(TipoAurasData.HOT_Nombre);
        setDescripcion(TipoAurasData.HOT_Descripcion);
        setIsDebuff(TipoAurasData.HOT_isDebuff);
        setStacksMaximos(TipoAurasData.HOT_Stacks_Maximos);

        skillStats = new SkillStat[2];
        skillStats[STAT_Duracion]   = new SkillStat (TipoAurasData.HOT_Duracion_String, TipoAurasData.HOT_Duracion_Valor);  //DURACION
        skillStats[STAT_Heal]       = new SkillStat (TipoAurasData.HOT_Daño_String, TipoAurasData.HOT_Daño_Valor);          //Heal
    }

    @Override public void actualizarAura(AuraI aura)
    {
        float HPsPorTick = aura.getDebuff().skillStat()[STAT_Heal].getValorBase() * aura.getStacks();

        if (aura.getTarget() instanceof Vulnerable)
        {   ((Vulnerable)aura.getTarget()).modificarHPs((int)+HPsPorTick); }
    }
}
