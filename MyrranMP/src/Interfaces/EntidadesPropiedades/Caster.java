package Interfaces.EntidadesPropiedades;// Created by Hanto on 17/04/2014.

import Interfaces.Geo.MapaI;

public interface Caster
{
    //GET:
    public MapaI getMapa();

    public boolean isCasteando();
    public float getActualCastingTime();
    public float getTotalCastingTime();
    public String getSpellIDSeleccionado();
    public Object getParametrosSpell();

    //SET:
    public void setCastear(boolean castear, int targetX, int targetY);
    public void setTotalCastingTime(float castingTime);
    public void setSpellIDSeleccionado(String spellID);
    public void setParametrosSpell (Object parametrosDTO);
}
