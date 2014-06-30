package Controller;// Created by Hanto on 07/04/2014.

import Model.GameState.Mundo;
import View.Vista;

public class Controlador
{
    protected Servidor servidor = new Servidor(this);                  //Input principal de la simulacion
    protected Updater updater;

    protected Mundo mundo;
    protected Vista vista;

    public Controlador (Mundo mundo)
    {
        this.mundo = mundo;
        vista = new Vista(this, mundo);
        updater = new Updater(this, mundo);
    }

    public void enviarACliente(int connectionID, Object obj)
    {   servidor.enviarACliente(connectionID, obj); }

    public void añadirPC (int connectionID)
    {   mundo.añadirPC(connectionID);

        mundo.getPC(connectionID).añadirSkillsPersonalizados("Terraformar");
        mundo.getPC(connectionID).añadirSkillsPersonalizados("Heal");
        mundo.getPC(connectionID).setNumTalentosSkillPersonalizado("Heal", 0, 10);
        mundo.getPC(connectionID).setNumTalentosSkillPersonalizado("Heal", 1, 17);
        mundo.getPC(connectionID).setNumTalentosSkillPersonalizado("Hot", 1, 10);
    }

    public void eliminarPC (int connectionID)
    {   mundo.eliminarPC(connectionID); }

    public void moverPC (int connectionID, float x, float y)
    {   mundo.getPC(connectionID).setPosition(x, y);}

    public void cambiarAnimacionPC(int connectionID, int numAnimacion)
    {   mundo.getPC(connectionID).setNumAnimacion(numAnimacion); }

    public void castear(int connectionID, boolean castear, int targetX, int targetY)
    {   mundo.getPC(connectionID).setCastear(castear, targetX, targetY); }

    public void cambiarSpellSeleccionado(int connectionID, String spellID, Object parametrosSpell)
    {   mundo.getPC(connectionID).setSpellIDSeleccionado(spellID);
        mundo.getPC(connectionID).setParametrosSpell(parametrosSpell);
    }

    public void cambiarParametrosSpell(int connectionID, Object parametrosSpell)
    {   mundo.getPC(connectionID).setParametrosSpell(parametrosSpell); }

    public void modificarSkillTalentoPC(int connectionID, String skillID, int statID, int valor)
    {   mundo.getPC(connectionID).setNumTalentosSkillPersonalizado(skillID, statID, valor);}



    public void netUpdater ()
    {   vista.netUpdate(); }
}
