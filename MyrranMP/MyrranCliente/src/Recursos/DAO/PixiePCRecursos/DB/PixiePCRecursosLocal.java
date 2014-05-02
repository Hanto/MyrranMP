package Recursos.DAO.PixiePCRecursos.DB;// Created by Hanto on 01/05/2014.

import Data.MiscData;
import Recursos.DAO.PixiePCRecursos.PixiePCRecursosDAO;
import View.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class PixiePCRecursosLocal implements PixiePCRecursosDAO
{
    public HashMap<String, PixiePCRecursosLocalDB.EquipoPC> listaDePCRazas = PixiePCRecursosLocalDB.get().listaDePCRazas;



    @Override public void salvarRazaPC(String iDRaza)
    {
        PixiePCRecursosLocalDB.EquipoPC equipoPC = new PixiePCRecursosLocalDB.EquipoPC();
        listaDePCRazas.put(iDRaza, equipoPC);
    }

    @Override public void salvarCuerpoPC(String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarCabezaPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarYelmoPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarHombrerasPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarPetoPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarPantalonesPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarGuantesPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarBotasPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarCapasTraserasPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public void salvarCapasFrontalesPC (String iDRaza, String iDCuerpo, TextureAtlas atlas)
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

    @Override public Pixie getCuerpoPC (String iDRaza, String iDCuerpo)
    {   return listaDePCRazas.get(iDRaza).listaDeCuerpos.get(iDCuerpo); }

    @Override public Pixie getCabezaPC(String iDRaza, String iDCabeza)
    {   return listaDePCRazas.get(iDRaza).listaDeCabezas.get(iDCabeza); }

    @Override public Pixie getYelmoPC(String iDRaza, String iDYelmo)
    {   return listaDePCRazas.get(iDRaza).listaDeYelmos.get(iDYelmo); }

    @Override public Pixie getHombrerasPC(String iDRaza, String iDHombreras)
    {   return listaDePCRazas.get(iDRaza).listaDeHombreras.get(iDHombreras); }

    @Override public Pixie getPetoPC(String iDRaza, String iDPeto)
    {   return listaDePCRazas.get(iDRaza).listaDePetos.get(iDPeto); }

    @Override public Pixie getPantalonesPC(String iDRaza, String iDPantalones)
    {   return listaDePCRazas.get(iDRaza).listaDePantalones.get(iDPantalones); }

    @Override public Pixie getGuantesPC(String iDRaza, String iDGuantes)
    {   return listaDePCRazas.get(iDRaza).listaDeGuantes.get(iDGuantes); }

    @Override public Pixie getBotasPC(String iDRaza, String iDGuantes)
    {   return listaDePCRazas.get(iDRaza).listaDeBotas.get(iDGuantes); }

    @Override public Pixie getCapaTraseraPC(String iDRaza, String iDCapaTrasera)
    {   return listaDePCRazas.get(iDRaza).listaDeCapasTraseras.get(iDCapaTrasera); }

    @Override public Pixie getCapaFrontalPC(String iDRaza, String iDCapaFrontal)
    {   return listaDePCRazas.get(iDRaza).listaDeCapasFrontales.get(iDCapaFrontal); }
}
