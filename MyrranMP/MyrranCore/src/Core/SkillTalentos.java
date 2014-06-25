package Core;// Created by Hanto on 25/06/2014.

import Interfaces.BDebuff.BDebuffI;
import Interfaces.Spell.SpellI;

public class SkillTalentos
{
    private String id;
    private boolean isSpell = false;
    private int[] talentos;

    public void setID(String id)                    { this.id = id; }
    public void setTalento(int statID, int valor)   { talentos[statID] = valor; }
    public String getId()                           { return id; }
    public boolean getIsSpell()                     { return isSpell; }
    public int getTalento(int statID)               { return talentos[statID]; }

    public SkillTalentos(SpellI spell)
    {
        id = spell.getID();
        isSpell = true;
        talentos = new int[spell.getNumSkillStats()];
        for (int i = 0; i < talentos.length; i++) talentos[i] = 0;
    }

    public SkillTalentos(BDebuffI debuff)
    {
        id = debuff.getID();
        isSpell = false;
        talentos = new int[debuff.getNumSkillStats()];
        for (int i = 0; i < talentos.length; i++) talentos[i] = 0;
    }
}
