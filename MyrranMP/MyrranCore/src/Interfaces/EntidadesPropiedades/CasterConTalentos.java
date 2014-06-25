package Interfaces.EntidadesPropiedades;// Created by Hanto on 23/06/2014.

public interface CasterConTalentos extends Caster
{
    public void añadirSpellTalentos(String spellID);
    public int getSkillTalentos(String spelloDebuffID, int statID);
    public void setSkillTalento(String spelloDebuffID, int statID, int talento);
}
