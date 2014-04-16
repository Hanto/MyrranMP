package View.Mobiles;// Created by Hanto on 11/04/2014.

import View.Graficos.Pixie;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import zMain.MiscData;

import java.util.HashMap;
import java.util.Map;

public class MobilesRecursos
 {
     private static class Singleton         { private static final MobilesRecursos get = new MobilesRecursos(); }
     public static MobilesRecursos get()    { return Singleton.get; }

     private TextureAtlas atlas;

     public HashMap<String, EquipoPC>listaDePCRazas = new HashMap<>();
     public static class EquipoPC
     {
         public Map<String,Pixie> listaDeCuerpos = new HashMap<>();
         public Map<String,Pixie>listaDeCabezas = new HashMap<>();
         public Map<String,Pixie>listaDeYelmos = new HashMap<>();
         public Map<String,Pixie>listaDePetos = new HashMap<>();
         public Map<String,Pixie>listaDePantalones = new HashMap<>();
         public Map<String,Pixie>listaDeGuantes = new HashMap<>();
         public Map<String,Pixie>listaDeBotas = new HashMap<>();
         public Map<String,Pixie>listaDeHombreras = new HashMap<>();
         public Map<String,Pixie>listaDeCapasTraseras = new HashMap<>();
         public Map<String,Pixie>listaDeCapasFrontales = new HashMap<>();
     }

     public TextureRegion nameplateTotal;
     public TextureRegion nameplateActual;
     public BitmapFont font14;

     public void setAtlas(TextureAtlas atlas)
     {
         this.atlas = atlas;
         font14 = new BitmapFont(Gdx.files.internal(MiscData.ATLAS_Fuentes_LOC +"14.fnt"), false);
     }

     public void añadirRazaPC(String iDRaza)
     {
         EquipoPC equipoPC = new EquipoPC();
         listaDePCRazas.put(iDRaza, equipoPC);
     }

     public void salvarCuerpoPC(String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_PlayerSprites_LOC + iDCuerpo));
         Pixie pixieCuerpo = new Pixie( texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieCuerpo.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieCuerpo.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieCuerpo.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieCuerpo.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieCuerpo.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieCuerpo.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieCuerpo.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieCuerpo.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeCuerpos.put(iDCuerpo, pixieCuerpo);
     }

     public void salvarCabezaPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_PlayerSprites_LOC+ iDCuerpo));
         Pixie pixieCuerpo = new Pixie( texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieCuerpo.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieCuerpo.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieCuerpo.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieCuerpo.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieCuerpo.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieCuerpo.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieCuerpo.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieCuerpo.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieCuerpo.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeCabezas.put(iDCuerpo, pixieCuerpo);
     }

     public void salvarYelmoPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie( texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeYelmos.put(iDCuerpo, pixieArmadura);
     }

     public void salvarHombrerasPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie( texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeHombreras.put(iDCuerpo, pixieArmadura);
     }

     public void salvarPetoPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie(texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDePetos.put(iDCuerpo, pixieArmadura);
     }

     public void salvarPantalonesPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie(texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDePantalones.put(iDCuerpo, pixieArmadura);
     }

     public void salvarGuantesPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie(texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeGuantes.put(iDCuerpo, pixieArmadura);
     }

     public void salvarBotasPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie(texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeBotas.put(iDCuerpo, pixieArmadura);
     }

     public void salvarCapasTraserasPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie(texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeCapasTraseras.put(iDCuerpo, pixieArmadura);
     }

     public void salvarCapasFrontalesPC (String iDRaza, String iDCuerpo)
     {
         if ( atlas == null) { System.out.println("ERROR Atlas no especificado"); return; }
         TextureRegion texture = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Armaduras_LOC+ iDCuerpo));
         Pixie pixieArmadura = new Pixie(texture, MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);
         pixieArmadura.añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
         pixieArmadura.añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
         pixieArmadura.añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
         pixieArmadura.añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
         pixieArmadura.añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
         pixieArmadura.añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
         pixieArmadura.añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
         pixieArmadura.añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
         pixieArmadura.animaciones().get(4).ininterrumpible = true;
         listaDePCRazas.get(iDRaza).listaDeCapasFrontales.put(iDCuerpo, pixieArmadura);
     }
 }
