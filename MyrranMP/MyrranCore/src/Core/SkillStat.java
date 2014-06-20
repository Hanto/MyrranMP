package Core;
//* @author Ivan Delgado Huerta
public class SkillStat
{
    private String nombre;                           //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private float valorBase;                         //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isMejorable = false;            //Indica si es un SkillStat mejorable por Talentos
    private int talentoMaximo;                       //numero de Talentos maximos que se pueden gastar en este SkillStat
    private int costeTalento;                        //coste por mejorar cada punto de talento
    private float bonoTalento;                         //Valor con el que mejora el valorBase por punto de talento


    //GET:
    public String getNombre()                       { return nombre; }
    public float getValorBase()                     { return valorBase; }
    public boolean getisMejorable()                 { return isMejorable; }
    public int getTalentoMaximo()                   { return talentoMaximo; }
    public int getCosteTalento()                    { return costeTalento; }
    public float getBonoTalento()                   { return bonoTalento; }



    //SET:
    public void setNombre (String nombre)           { this.nombre = nombre; }
    public void setValorBase (float valorBase)      { this.valorBase = valorBase; }
    public void setIsMejorable(boolean b)           { this.isMejorable = b; }
    public void setTalentoMaximo (int talentoMaximo){ this.talentoMaximo = talentoMaximo; }
    public void setCosteTalento (int costeTalento)  { this.costeTalento = costeTalento; }
    public void setBonoTalento (float bonoTalento)  { this.bonoTalento = bonoTalento; }

    //CONSTRUCTOR:
    public SkillStat() {}

    public SkillStat(String nombre, float valor)
    {   //constructor:
        this.nombre = nombre;
        valorBase = valor;
        isMejorable = false;
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
    }

    public void setValor (float valor)
    {   valorBase = valor; }

    public void setStat (String nombre, float valor)
    {
        this.nombre = nombre;
        valorBase = valor;
    }

    public void setTalentos (int max, int coste, float bono)
    {
        talentoMaximo = max;
        costeTalento = coste;
        bonoTalento = bono;
        isMejorable = true;
    }
}
    
    