package DAO.TipoBDebuff.DB;// Created by Hanto on 10/06/2014.

import DAO.TipoBDebuff.TipoBDebuffDAO;
import Interfaces.BDebuff.TipoBDebuffI;

import java.util.Map;

public class TipoBDebuffLocal implements TipoBDebuffDAO
{
    private Map<String, TipoBDebuffI>listaDeBDebuffs = TipoBDebuffLocalDB.get().listaDeBDebuffs;

    @Override public boolean añadirTipoBDebuff(TipoBDebuffI debuff)
    {
        if (listaDeBDebuffs.containsKey(debuff.getID()))
        {   System.out.println("ERROR: ya existe un Buff/Debuff con este ID["+debuff.getID()+"]");  return false; }
        else {  listaDeBDebuffs.put(debuff.getID(), debuff); return true; }
    }

    @Override public void salvarTipoBDebuff(TipoBDebuffI debuff)
    {
        if (listaDeBDebuffs.containsKey(debuff.getID()))
        {   listaDeBDebuffs.put(debuff.getID(), debuff); }
    }

    @Override public void eliminarTipoBDebuff(String debuffID)
    {
        if (listaDeBDebuffs.containsKey(debuffID))
        {   listaDeBDebuffs.remove(debuffID); }
    }

    @Override public TipoBDebuffI getTipoBDebuff(String debuffID)
    {   return listaDeBDebuffs.get(debuffID); }
}
