package Interfaces;// Created by Hanto on 17/04/2014.

public interface Caster
{
    //GET:
    public MapaI getMapa();

    public boolean isCasteando();
    public float getActualCastingTime();
    public float getTotalCastingTime();
    //SET:
    public void setIsCasteando(boolean isCasteando);
    public void setActualCastingTime(float actualCastime);
    public void setTotalCastingTime(float castingTime);
}
