package Interfaces;// Created by Hanto on 17/04/2014.

public interface Caster
{
    //GET:
    public MapaI getMapa();

    public boolean isCasteando();
    public float getActualCastingTime();
    public float getTotalCastingTime();
    public int getSpellIDSeleccionado();

    //SET:
    public void setCastear(boolean castear, int targetX, int targetY);
    public void setTotalCastingTime(float castingTime);
    public void setSpellIDSeleccionado(int spellID);
}
