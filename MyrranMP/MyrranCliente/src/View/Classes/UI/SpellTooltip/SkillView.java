package View.Classes.UI.SpellTooltip;// Created by Hanto on 27/06/2014.

import View.Classes.Graficos.Texto;

public class SkillView
{
    private static class SkillStatsView
    {
        public Texto nombreSkillStat;
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

    public SkillStatsView getSkillStat(int statID)      { return skillStat[statID]; }

    public SkillView(int numSkillStats)
    {
        skillStat = new SkillStatsView[numSkillStats];
        for (int i=0; i<numSkillStats; i++) skillStat[i] = new SkillStatsView();
    }
}
