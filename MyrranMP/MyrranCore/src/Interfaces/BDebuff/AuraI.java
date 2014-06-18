package Interfaces.BDebuff;// Created by Hanto on 09/06/2014.

import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.EntidadesPropiedades.Debuffeable;

public interface AuraI
{    //SET:
    public byte getStacks();
    public byte getTicksAplicados();
    public float getDuracion();
    public float getDuracionMax();
    public Caster getCaster();
    public Debuffeable getTarget();
    public BDebuffI getDebuff();

    //GET:
    public void setStacks(byte b);
    public void setTicksAplicados(byte b);
    public void setDuracion(float f);
    public void setDuracionMax(float f);
    public void setCaster(Caster caster);
    public void setTarget(Debuffeable target);
    public void setDebuff(BDebuffI debuff);

    //METODOS:
    public void actualizarAura (float delta);
}
