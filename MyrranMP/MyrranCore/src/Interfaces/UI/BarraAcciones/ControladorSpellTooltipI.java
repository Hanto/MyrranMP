package Interfaces.UI.BarraAcciones;// Created by Hanto on 27/06/2014.

public interface ControladorSpellTooltipI
{
    public void decrementarSkillTalento(String skillID, int statID);
    public void aumentarSkillTalento(String skillID, int statID);
    public void setSkillTalento(String skillID, int statID, int valor);
}
