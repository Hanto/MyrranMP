package View.Classes.UI.SpellTooltip;// Created by Hanto on 27/06/2014.

import View.Classes.Graficos.Texto;

public class SkillView
{
    private static class SkillStatsView
    {
        public Texto nombre;
        public Texto valorBase;
        public CasilleroTalentos casillero;
        public Texto valorTotal;
        public Texto talentos;
        public Texto costeTalento;
        public Texto bonoTalento;
        public Texto maxTalentos;
    }

    public Texto nombreSkill;
    private SkillStatsView[] skillStat;
    public SkillStatsView getSkillStat(int statID)                              { return skillStat[statID]; }

    public Texto getNombre(int statID)                                          { return skillStat[statID].nombre; }
    public Texto getTalentos(int statID)                                        { return skillStat[statID].talentos; }
    public CasilleroTalentos getCasilleroTalentos(int statID)                   { return skillStat[statID].casillero; }
    public Texto getValorTotal(int statID)                                      { return skillStat[statID].valorTotal; }

    public void setNombre(int statID, Texto nombre)                             { skillStat[statID].nombre = nombre; }
    public void setValorBase(int statID, Texto valor)                           { skillStat[statID].valorBase = valor; }
    public void setCasilleroTalentos(int statID, CasilleroTalentos casillero)   { skillStat[statID].casillero = casillero; }
    public void setValorTotal(int statID, Texto valorTotal)                     { skillStat[statID].valorTotal = valorTotal; }
    public void setTalentos(int statID, Texto talentos)                         { skillStat[statID].talentos = talentos; }
    public void setCosteTalento(int statID, Texto costeTalento)                 { skillStat[statID].costeTalento = costeTalento; }
    public void setBonoTalentos(int statID, Texto bonoTalento)                  { skillStat[statID].bonoTalento = bonoTalento; }
    public void setMaxTalentos(int statID, Texto maxTalentos)                   { skillStat[statID].maxTalentos = maxTalentos; }

    public SkillView(int numSkillStats)
    {
        skillStat = new SkillStatsView[numSkillStats];
        for (int i=0; i<numSkillStats; i++) skillStat[i] = new SkillStatsView();
    }
}
