package Model.Classes.Skill.BDebuff.TiposBDebuff;// Created by Hanto on 26/06/2014.

import Model.Classes.Skill.BDebuff.TipoBDebuff;

public class Bomba extends TipoBDebuff
{
    @Override public void inicializarSkillStats()
    {
        setID(this.getClass().getSimpleName().toUpperCase());
        setNumSkillStats(2);
    }

}
