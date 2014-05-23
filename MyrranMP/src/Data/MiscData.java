package Data;// Created by Hanto on 07/04/2014.

import java.util.HashMap;

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
    public final static String ATLAS_Acciones_LOC = "Acciones/";

    //Pixie:
    public final static int PIXIE_Player_numFilas = 7;
    public final static int PIXIE_Player_numColumnas = 6;

    //Mapa:
    public final static int MAPA_Max_Capas_Terreno = 3;
    public final static int MAPA_Max_TilesX = 5000;
    public final static int MAPA_Max_TilesY = 5000;

    public final static int MAPAVIEW_TamañoX = 2;//18;
    public final static int MAPAVIEW_TamañoY = 2;//20;

    public final static int MAPAMODEL_NumTilesX = (int)Math.ceil((double)MiscData.GDX_Window_Horizontal_Resolution/(double)MiscData.TILESIZE);
    public final static int MAPAMODEL_NumTilesY = (int)Math.ceil((double)MiscData.GDX_Window_Vertical_Resolution/(double)MiscData.TILESIZE);

    //Fuentes
    public final static String FUENTE_Nombres = "14";

    //Barra Spells:
    public final static int BARRASPELLS_Ancho_Casilla = 32+2;
    public final static int BARRASPELLS_Alto_Casilla = 32+2;
    public final static String BARRASPELLS_Textura_Casillero = "Casillero";
    public final static String BARRASPELLS_RebindButtonON = "RebindOn";
    public final static String BARRASPELLS_RebindButtonOFF ="RebindOff";
    public final static String BARRATERRENOS_Borrar_Terreno = "Borrar";

    //Icono Accion
    public final static int ICONO_Accion_Ancho = 32;
    public final static int ICONO_Accion_Alto = 32;

    //KEYCODES:
    public static final HashMap<Integer, String> keycodeNames = new HashMap<>();
    static  {   keycodeNames.put(7, "0");
                keycodeNames.put(8, "1");
                keycodeNames.put(9, "2");
                keycodeNames.put(10, "3");
                keycodeNames.put(11, "4");
                keycodeNames.put(12, "5");
                keycodeNames.put(13, "6");
                keycodeNames.put(14, "7");
                keycodeNames.put(15, "8");
                keycodeNames.put(16, "9");
                keycodeNames.put(29, "A");
                keycodeNames.put(57, "ALT_LEFT");
                keycodeNames.put(58, "ALT_RIGHT");
                keycodeNames.put(75, "APOSTROPHE");
                keycodeNames.put(77, "@");
                keycodeNames.put(30, "B");
                keycodeNames.put(4, "BACK");
                keycodeNames.put(73, "\\");
                keycodeNames.put(31, "C");
                keycodeNames.put(5, "CALL");
                keycodeNames.put(27, "CAMERA");
                keycodeNames.put(28, "CLEAR");
                keycodeNames.put(55, ",");
                keycodeNames.put(32, "D");
                keycodeNames.put(67, "BACKSPACE");
                keycodeNames.put(112, "FORWARD_DEL");
                keycodeNames.put(23, "CENTER");
                keycodeNames.put(20, "DOWN");
                keycodeNames.put(21, "LEFT");
                keycodeNames.put(22, "RIGHT");
                keycodeNames.put(19, "UP");
                keycodeNames.put(33, "E");
                keycodeNames.put(6, "ENDCALL");
                keycodeNames.put(66, "ENTER");
                keycodeNames.put(65, "ENVELOPE");
                keycodeNames.put(70, "=");
                keycodeNames.put(64, "EXPLORER");
                keycodeNames.put(34, "F");
                keycodeNames.put(80, "FOCUS");
                keycodeNames.put(35, "G");
                keycodeNames.put(68, "Ñ");
                keycodeNames.put(36, "H");
                keycodeNames.put(79, "HEADSETHOOK");
                keycodeNames.put(3, "HOME");
                keycodeNames.put(37, "I");
                keycodeNames.put(38, "J");
                keycodeNames.put(39, "K");
                keycodeNames.put(40, "L");
                keycodeNames.put(71, "[");
                keycodeNames.put(41, "M");
                keycodeNames.put(90, "FAST_FORWARD");
                keycodeNames.put(87, "NEXT");
                keycodeNames.put(85, "PLAY_PAUSE");
                keycodeNames.put(88, "PREVIOUS");
                keycodeNames.put(89, "REWIND");
                keycodeNames.put(86, "STOP");
                keycodeNames.put(82, "MENU");
                keycodeNames.put(69, "MINUS");
                keycodeNames.put(91, "MUTE");
                keycodeNames.put(42, "N");
                keycodeNames.put(83, "NOTIFICATION");
                keycodeNames.put(78, "NUM");
                keycodeNames.put(43, "O");
                keycodeNames.put(44, "P");
                keycodeNames.put(56, ".");
                keycodeNames.put(81, "+");
                keycodeNames.put(18, "#");
                keycodeNames.put(26, "POWER");
                keycodeNames.put(45, "Q");
                keycodeNames.put(46, "R");
                keycodeNames.put(72, "]");
                keycodeNames.put(47, "S");
                keycodeNames.put(84, "SEARCH");
                keycodeNames.put(74, ";");
                keycodeNames.put(59, "SHIFT_LEFT");
                keycodeNames.put(60, "SHIFT_RIGHT");
                keycodeNames.put(76, "/");
                keycodeNames.put(1, "SOFT_LEFT");
                keycodeNames.put(2, "SOFT_RIGHT");
                keycodeNames.put(62, "SPACE");
                keycodeNames.put(17, "STAR");
                keycodeNames.put(63, "SYM");
                keycodeNames.put(48, "T");
                keycodeNames.put(61, "TAB");
                keycodeNames.put(49, "U");
                keycodeNames.put(0, "UNKNOWN");
                keycodeNames.put(50, "V");
                keycodeNames.put(25, "VOLUME_DOWN");
                keycodeNames.put(24, "VOLUME_UP");
                keycodeNames.put(51, "W");
                keycodeNames.put(52, "X");
                keycodeNames.put(53, "Y");
                keycodeNames.put(54, "Z");
                keycodeNames.put(129, "CONTROL_LEFT");
                keycodeNames.put(130, "CONTROL_RIGHT");
                keycodeNames.put(131, "ESCAPE");
                keycodeNames.put(132, "END");
                keycodeNames.put(133, "INSERT");
                keycodeNames.put(92, "PAGE_UP");
                keycodeNames.put(93, "PAGE_DOWN");
                keycodeNames.put(94, "PICTSYMBOLS");
                keycodeNames.put(95, "SWITCH_CHARSET");
                keycodeNames.put(255, "BUTTON_CIRCLE");
                keycodeNames.put(96, "BUTTON_A");
                keycodeNames.put(97, "BUTTON_B");
                keycodeNames.put(98, "BUTTON_C");
                keycodeNames.put(99, "BUTTON_X");
                keycodeNames.put(100, "BUTTON_Y");
                keycodeNames.put(101, "BUTTON_Z");
                keycodeNames.put(102, "BUTTON_L1");
                keycodeNames.put(103, "BUTTON_R1");
                keycodeNames.put(104, "BUTTON_L2");
                keycodeNames.put(105, "BUTTON_R2");
                keycodeNames.put(106, "BUTTON_THUMBL");
                keycodeNames.put(107, "BUTTON_THUMBR");
                keycodeNames.put(108, "BUTTON_START");
                keycodeNames.put(109, "BUTTON_SELECT");
                keycodeNames.put(110, "BUTTON_MODE");
                keycodeNames.put(144, "N0");
                keycodeNames.put(145, "N1");
                keycodeNames.put(146, "N2");
                keycodeNames.put(147, "N3");
                keycodeNames.put(148, "N4");
                keycodeNames.put(149, "N5");
                keycodeNames.put(150, "N6");
                keycodeNames.put(151, "N7");
                keycodeNames.put(152, "N8");
                keycodeNames.put(153, "N9");
    }

}
