package DAO.BDebuff;// Created by Hanto on 16/06/2014.

import Interfaces.BDebuff.BDebuffI;

import java.util.Map;

public class BDebuffLocal implements BDebuffDAO
{
    private Map<String, BDebuffI> listaDeBDebuffs;

    public BDebuffLocal(Map<String, BDebuffI> listaDeBDebuffs)
    {   this.listaDeBDebuffs = listaDeBDebuffs; }




    @Override public boolean añadirBDebuff(BDebuffI debuff)
    {
        if (listaDeBDebuffs.containsKey(debuff.getID()))
        {   System.out.println("ERROR: ya existe un Buff/Debuff con este ID["+debuff.getID()+"]");  return false; }
        else {  listaDeBDebuffs.put(debuff.getID(), debuff); return true; }
    }

    @Override public void salvarBDebuff(BDebuffI debuff)
    {
        if (listaDeBDebuffs.containsKey(debuff.getID()))
        {   listaDeBDebuffs.put(debuff.getID(), debuff); }
    }

    @Override public void eliminarBDebuff(String debuffID)
    {
        if (listaDeBDebuffs.containsKey(debuffID))
        {   listaDeBDebuffs.remove(debuffID); }
    }

    @Override public BDebuffI getBDebuff(String debuffID)
    {   return listaDeBDebuffs.get(debuffID); }
}
