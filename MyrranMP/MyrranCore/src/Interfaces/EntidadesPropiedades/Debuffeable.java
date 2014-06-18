package Interfaces.EntidadesPropiedades;// Created by Hanto on 04/06/2014.

import Interfaces.BDebuff.AuraI;
import Interfaces.Model.ModelI;

import java.util.Iterator;

public interface Debuffeable extends ModelI
{
    public void añadirAura(AuraI aura);
    public void eliminarAura(AuraI aura);
    public Iterator<AuraI> getAuras();
}
