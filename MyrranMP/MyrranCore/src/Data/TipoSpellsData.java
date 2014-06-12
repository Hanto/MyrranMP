package Data;
// @author Ivan Delgado Huerta
public class TipoSpellsData 
{
    //Iconos:
    public static final String ICONO_Editar = "Editar";
    public static final String ICONO_Fireball = "FireBall";
    public static final String ICONO_Frostbolt = "FrostBolt";
    public static final String ICONO_Muro = "Muros";

    //Casteos:
    public static final String CASTEO_Fireball = "Fireball01";
    public static final String CASTEO_FrostBolt = "FrostBolt01";

    //Proyectiles:
    public static final String PROYECTIL_Fireball = "Fireball02";
    public static final String PROYECTIL_Frostbolt = "FrostBolt02";

    //TIPOSSPELL:

    //EDITAR TERRENO:
    public static final String  EDITARTERRENO_ID = "EditarTErreno";
    public static final String  EDITARTERRENO_Nombre = "EditarTerreno";
    public static final String  EDITARTERRENO_Descripcion = "Edita el Terreno";
    public static final String  EDITARTERRENO_CastingTime_String = "Casting Time";
    public static final float   EDITARTERRENO_CastingTime_Valor = 0.01f;
    public static final String  EDITARTERRENO_Icono = ICONO_Editar;

    //BOLT: (el casting time siempre debe ir primero)
    public static final String  BOLT_Icono = "FireBall";
    public static final String  BOLT_CastingTime_String = "Casting Time";
    public static final float   BOLT_CastingTime_Valor = 0.5f;
    public static final String  BOLT_Daño_String = "Daño";
    public static final float   BOLT_Daño_Valor = 100f;
    public static final String  BOLT_Velocidad_String = "Velocidad";
    public static final float   BOLT_Velocidad_Valor = 550f;
    public static final String  BOLT_Duracion_String = "Duracion";
    public static final float   BOLT_Duracion_Valor = 2.0f;
    public static final float   BOLT_Duracion_Animaciones = 0.15f;
    public static final String  BOLT_Pixie_Casteo_001 = "Fireball01";
    public static final String  BOLT_Pixie_Casteo_002 = "FrostBolt01";
    public static final String  BOLT_Pixie_Proyectil_001 = "Fireball02";
    public static final String  BOLT_Pixie_Proyectil_002 = "FrostBolt02";

    //EDITAR MURO:
    public static final String  EDITARMURO_Icono = "Muro";
    public static final String  EDITARMURO_CastingTime_String = "Casting Time";
    public static final float   EDITARMURO_CastingTime_Valor = 0.005f;
    
    //HEAL:
    public static final String  HEAL_Icono = "FireBall";
    public static final String  HEAL_CastingTime_String = "Casting Time";
    public static final float   HEAL_CastingTime_Valor = 1.0f;
    public static final String  HEAL_Curacion_String = "Curacion";
    public static final float   HEAL_Curacion_Valor = 500f;
}
