package Interfaces.EntidadesPropiedades;// Created by Hanto on 23/06/2014.

public interface CasterConTalentos extends Caster
{
    public int getSpellTalentos(String idSpell, int numStat);
    public int getBDebuffTalentos(String BDebuff, int numStat);
}
