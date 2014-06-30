package View.Classes.UI.SpellTooltip;// Created by Hanto on 27/06/2014.

import Interfaces.Skill.SkillPersonalizadoI;
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

    private Texto nombreSkill;
    private SkillStatsView[] skillStat;

    public SkillStatsView getSkillStat(int statID)                              { return skillStat[statID]; }
    public Texto getNombreSkill()                                               { return nombreSkill; }
    public Texto getNombre(int statID)                                          { return skillStat[statID].nombre; }
    public Texto getValorBase(int statID)                                       { return skillStat[statID].valorBase; }
    public CasilleroTalentos getCasilleroTalentos(int statID)                   { return skillStat[statID].casillero; }
    public Texto getValorTotal(int statID)                                      { return skillStat[statID].valorTotal; }
    public Texto getTalentos(int statID)                                        { return skillStat[statID].talentos; }
    public Texto getCosteTalento(int statID)                                    { return skillStat[statID].costeTalento; }
    public Texto getBonoTalento(int statID)                                     { return skillStat[statID].bonoTalento; }
    public Texto getMaxTalentos(int statID)                                     { return skillStat[statID].maxTalentos; }

    public void setNombreSkill(Texto nombre)                                    { nombreSkill = nombre; }
    public void setNombre(int statID, Texto nombre)                             { skillStat[statID].nombre = nombre; }
    public void setValorBase(int statID, Texto valor)                           { skillStat[statID].valorBase = valor; }
    public void setCasilleroTalentos(int statID, CasilleroTalentos casillero)   { skillStat[statID].casillero = casillero; }
    public void setValorTotal(int statID, Texto valorTotal)                     { skillStat[statID].valorTotal = valorTotal; }
    public void setTalentos(int statID, Texto talentos)                         { skillStat[statID].talentos = talentos; }
    public void setCosteTalento(int statID, Texto costeTalento)                 { skillStat[statID].costeTalento = costeTalento; }
    public void setBonoTalentos(int statID, Texto bonoTalento)                  { skillStat[statID].bonoTalento = bonoTalento; }
    public void setMaxTalentos(int statID, Texto maxTalentos)                   { skillStat[statID].maxTalentos = maxTalentos; }

    public SkillView (SkillPersonalizadoI skill)
    {
        skillStat = new SkillStatsView[skill.getNumSkillStats()];
        for (int i=0; i<skill.getNumSkillStats(); i++) skillStat[i] = new SkillStatsView();
    }
}
