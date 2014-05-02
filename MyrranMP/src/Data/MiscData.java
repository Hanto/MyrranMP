package Data;// Created by Hanto on 07/04/2014.

public class MiscData
{
    //General
    public static final int TILESIZE = 24;

    //Network:
    public static final int NETWORK_Puerto_Servidor = 54555;
    public static final int NETWORK_Client_Timeout = 5000*100;
    public static final int NETWORK_Update_Time = 50;
    public static final float SERVIDOR_Delta_Time = NETWORK_Update_Time/1000f;
    public static final float SERVIDOR_DistanciaVisionMobs = 1.2f;

    //LibGDX:
    public static final int GDX_Window_Horizontal_Resolution = 1600;
    public static final int GDX_Window_Vertical_Resolution = 900;

    //Recursos:
    public final static String RECURSOS_Atlas_Carpeta_Imagenes_Origen = "Images/";
    public final static String RECURSOS_Atlas_Carpeta_Imagenes_Destino = "Atlas/";
    public final static String RECURSOS_Atlas_Atlas_Extension = "atlas";


    //Mobiles Recursos:
    public final static String ATLAS_PlayerSprites_LOC = "Player Sprites/";
    public final static String ATLAS_Armaduras_LOC = "Armaduras/";
    public final static String ATLAS_Terrenos_LOC = "Terrenos/";
    public final static String ATLAS_Fuentes_LOC = "Fonts/";
    public final static String ATLAS_Spell_Iconos_Loc = "Spell Icons/";
    public final static String ATLAS_Spell_Animations_LOC = "Spell Animations/";

    //Pixie:
    public final static int PIXIE_Player_numFilas = 7;
    public final static int PIXIE_Player_numColumnas = 6;

    //Mapa:
    public final static int MAPA_Max_Capas_Terreno = 3;
    public final static int MAPA_Max_TilesX = 1000;
    public final static int MAPA_Max_TilesY = 1000;

    public final static int MAPAVIEW_TamañoX = 3;//18;
    public final static int MAPAVIEW_TamañoY = 3;//20;

    //Fuentes
    public final static String FUENTE_Nombres = "14";

}
