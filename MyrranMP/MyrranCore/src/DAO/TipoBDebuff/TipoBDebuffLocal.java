package DAO.TipoBDebuff;// Created by Hanto on 10/06/2014.

import Interfaces.BDebuff.TipoBDebuffI;

import java.util.Map;

public class TipoBDebuffLocal implements TipoBDebuffDAO
{
    private Map<String, TipoBDebuffI> listaDeTipoBDebuffs;

    public TipoBDebuffLocal(Map<String, TipoBDebuffI> listaDeTipoBDebuffs)
    {   this.listaDeTipoBDebuffs = listaDeTipoBDebuffs; }



    @Override public boolean añadirTipoBDebuff(TipoBDebuffI debuff)
    {
        if (listaDeTipoBDebuffs.containsKey(debuff.getID()))
        {   System.out.println("ERROR: ya existe un Tipo Buff/Debuff con este ID["+debuff.getID()+"]");  return false; }
        else {  listaDeTipoBDebuffs.put(debuff.getID(), debuff); return true; }
    }

    @Override public void salvarTipoBDebuff(TipoBDebuffI debuff)
    {
        if (listaDeTipoBDebuffs.containsKey(debuff.getID()))
        {   listaDeTipoBDebuffs.put(debuff.getID(), debuff); }
    }

    @Override public void eliminarTipoBDebuff(String debuffID)
    {
        if (listaDeTipoBDebuffs.containsKey(debuffID))
        {   listaDeTipoBDebuffs.remove(debuffID); }
    }

    @Override public TipoBDebuffI getTipoBDebuff(String debuffID)
    {   return listaDeTipoBDebuffs.get(debuffID); }
}
