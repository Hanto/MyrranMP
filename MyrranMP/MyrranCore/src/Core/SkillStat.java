package Core;
//* @author Ivan Delgado Huerta
public class SkillStat
{
    private String nombre;                           //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private float valorBase;                         //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isMejorable = false;            //Indica si es un SkillStat mejorable por Talentos
    private int talentoMaximo;                       //numero de Talentos maximos que se pueden gastar en este SkillStat
    private int costeTalento;                        //coste por mejorar cada punto de talento
    private int bonoTalento;                         //Valor con el que mejora el valorBase por punto de talento
    private boolean hayNombre = false;
    private boolean hayValorBase = false;
    private boolean hayIsMejorable = false;
    private boolean hayTalentoMaximo = false;
    private boolean hayCosteTalento = false;
    private boolean hayBonoTalento = false;

    //GET:
    public String getNombre()                       { return nombre; }
    public float getValorBase()                     { return valorBase; }
    public boolean getisMejorable()                 { return isMejorable; }
    public int getTalentoMaximo()                   { return talentoMaximo; }
    public int getCosteTalento()                    { return costeTalento; }
    public int getBonoTalento()                     { return bonoTalento; }

    public boolean getHayNombre()                   { return hayNombre; }
    public boolean getHayValorBase()                { return hayValorBase; }
    public boolean getHayIsMejorable()              { return hayIsMejorable; }
    public boolean getHayTalentoMaximo()            { return hayTalentoMaximo; }
    public boolean getHayCosteTalento()             { return hayCosteTalento; }
    public boolean getHayBonoTalento()              { return hayBonoTalento; }

    //SET:
    public void setNombre (String nombre)           { this.nombre = nombre; }
    public void setValorBase (float valorBase)      { this.valorBase = valorBase; }
    public void setIsMejorable(boolean b)           { this.isMejorable = b; }
    public void setTalentoMaximo (int talentoMaximo){ this.talentoMaximo = talentoMaximo; }
    public void setCosteTalento (int costeTalento)  { this.costeTalento = costeTalento; }
    public void setBonoTalento (int bonoTalento)    { this.bonoTalento = bonoTalento; }

    //CONSTRUCTOR:
    public SkillStat()
    {

    }

    public SkillStat(String nombre, float valor)
    {   //constructor:
        this.nombre = nombre;
        valorBase = valor;
        isMejorable = false;

        hayNombre = true;
        hayValorBase = true;
        hayIsMejorable = true;
    }
    
    //CONSTRUCTOR: (constructor Copia)
    public SkillStat(SkillStat skillStat)
    {
        this.nombre = skillStat.nombre;
        this.valorBase = skillStat.valorBase;
        this.talentoMaximo = skillStat.talentoMaximo;
        this.costeTalento = skillStat.costeTalento;
        this.bonoTalento = skillStat.bonoTalento;
        this.isMejorable = skillStat.isMejorable;

        hayNombre = true;
        hayValorBase = true;
        hayTalentoMaximo = true;
        hayCosteTalento = true;
        hayBonoTalento = true;
        hayIsMejorable = true;
    }

    public void setValor (float valor)
    {
        valorBase = valor;

        hayValorBase = true;
    }

    public void setStat (String nombre, float valor)
    {
        this.nombre = nombre;
        valorBase = valor;

        hayNombre = true;
        hayValorBase = true;
    }

    public void setTalentos (int max, int coste, int bono)
    {
        talentoMaximo = max;
        costeTalento = coste;
        bonoTalento = bono;
        isMejorable = true;

        hayTalentoMaximo = true;
        hayCosteTalento = true;
        hayBonoTalento = true;
        hayIsMejorable = true;
    }
}
    
    