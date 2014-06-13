package DB.Recursos.PixiePCRecursos.DTO;// Created by Hanto on 13/06/2014.

import DB.RSC;
import Data.MiscData;
import View.Classes.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PixieRecursos
{
    public static class PixieCuerpo extends Pixie
    {
        public PixieCuerpo(String textura)
        {
            super(new TextureRegion(RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas().findRegion(MiscData.ATLAS_PlayerSprites_LOC + textura)),
                  MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);

            añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
            añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
            añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
            añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
            añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
            añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
            añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
            añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
            añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
            añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
            añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
            añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
            añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
            añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
            animaciones().get(4).ininterrumpible = true;
        }
    }

    public static class PixieArmadura extends Pixie
    {
        public PixieArmadura(String textura)
        {
            super(new TextureRegion(RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas().findRegion(MiscData.ATLAS_Armaduras_LOC + textura)),
                    MiscData.PIXIE_Player_numFilas, MiscData.PIXIE_Player_numColumnas);

            añadirAnimacion("izquierda",    new int []{0,1,2},      0.15f, true);
            añadirAnimacion("derecha",      new int []{3,4,5},      0.15f, true);
            añadirAnimacion("arriba",       new int []{6,7,8},      0.15f, true);
            añadirAnimacion("abajo",        new int []{9,10,11},    0.15f, true);
            añadirAnimacion("castear",      new int []{12,13,14},   0.15f, false);
            añadirAnimacion("quieto",       new int []{15,16,17},   0.80f, true);
            añadirAnimacion("dispararO",    new int []{18,19,20},   0.15f, true);
            añadirAnimacion("dispararE",    new int []{21,22,23},   0.15f, true);
            añadirAnimacion("dispararSO",   new int []{24,25,26},   0.15f, true);
            añadirAnimacion("dispararSE",   new int []{27,28,29},   0.15f, true);
            añadirAnimacion("dispararS",    new int []{30,31,32},   0.15f, true);
            añadirAnimacion("dispararN",    new int []{33,34,35},   0.15f, true);
            añadirAnimacion("dispararNO",   new int []{36,37,38},   0.15f, true);
            añadirAnimacion("dispararNE",   new int []{39,40,41},   0.15f, true);
            animaciones().get(4).ininterrumpible = true;
        }
    }
}
