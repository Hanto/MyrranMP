package Core.Skills;// Created by Hanto on 29/06/2014.

public class SkillMod
{
    private int id;
    private int numTalentos = 0;
    private float bonosPorObjetos = 0;

    public int getID()                                      { return id; }
    public int getNumTalentos()                             { return numTalentos; }
    public float getBonosPorObjetos()                       { return bonosPorObjetos; }

    public void setID(int id)                               { this.id = id; }
    public void setNumTalentos(int numTalentos)             { this.numTalentos = numTalentos; }
    public void setBonosPorObjetos(float bonosPorObjetos)   { this.bonosPorObjetos = bonosPorObjetos; }

    public SkillMod(int id)
    {   this.id = id;}
}
