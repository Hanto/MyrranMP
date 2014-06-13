package Data;// Created by Hanto on 10/06/2014.


public class TipoBDebuffsData
{
    //HOT:
    public static final String  HOT_ID = "Dot";
    public static final String  HOT_Nombre = "Dot";
    public static final String  HOT_Descripcion = "Spell que durante un tiempo determinado afecta periodicamente al objetivo con la perdida o recuperacion de HPs";
    public static final boolean HOT_isDebuff = true;
    public static final byte    HOT_Stacks_Maximos = 3;
    public static final String  HOT_Duracion_String = "Duracion";
    public static final float   HOT_Duracion_Valor = 10* MiscData.BDEBUFF_DuracionTick;  //Duracion de 10 Ticks (10 Segundos)
    public static final String  HOT_Daño_String = "Daño por Tick";
    public static final float   HOT_Daño_Valor = 10f;
    public static final String  HOT_Icono = "Dot";
}
