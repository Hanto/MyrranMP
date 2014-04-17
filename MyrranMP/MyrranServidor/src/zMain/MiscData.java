package zMain;// Created by Hanto on 07/04/2014.

import Model.DAO.Terreno.TerrenoDAOFactory;

public class MiscData
{
    //DAOFactory
    public static final TerrenoDAOFactory terrenoDAO = TerrenoDAOFactory.LOCAL;

    //Network:
    public static final int NETWORK_Puerto_Servidor = 54555;
    public static final int NETWORK_Client_Timeout = 5000*100;
    public static final float NETWORK_Update_Time = 50f;

    //LibGDX:
    public static final int GDX_Window_Horizontal_Resolution = 1600;
    public static final int GDX_Window_Vertical_Resolution = 900;

    //RSC:
    public final static String RECURSOS_Atlas_Carpeta_Imagenes_Origen = "images/";
    public final static String RECURSOS_Atlas_Carpeta_Imagenes_Destino = "atlas/";
    public final static String RECURSOS_Atlas_Atlas_Extension = "atlas";

    //Actor RSC:
    public final static String ATLAS_PlayerSprites_LOC = "Player Sprites/";
    public final static String ATLAS_Armaduras_LOC = "Armaduras/";

    public final static int PIXIE_Player_numFilas = 7;
    public final static int PIXIE_Player_numColumnas = 6;

    //Model.Geo:
    public final static int MAPA_Max_Capas_Terreno = 3;
    public final static int MAPA_Max_X = 1000;
    public final static int MAPA_Max_Y = 1000;
}