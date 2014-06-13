package View.Classes.Geo;// Created by Hanto on 15/04/2014.

import Data.MiscData;
import Model.DTO.TerrenoDTO;
import DB.RSC;
import DB.Recursos.TerrenoRecursos.DTO.TerrenoRecursos;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrenoView
{   //Textura BASE:
    private TerrenoRecursos terreno;

    public TextureRegion cuadranteNO;
    public TextureRegion cuadranteNE;
    public TextureRegion cuadranteSE;
    public TextureRegion cuadranteSO;

    TerrenoDTO.Adyacencias ad;

    public TerrenoView(TerrenoDTO.Adyacencias adyacencias)
    {
        this.ad = adyacencias;
        generarTextura();
    }

    public void generarTextura()
    {
        terreno = RSC.terrenoRecursosDAO.getTerrenoRecursosDAO().getTerrenoRecurso(ad.iDTerreno);
        if (terreno != null)
        {
            generarTexturaNO(terreno.getTextura());
            generarTexturaNE(terreno.getTextura());
            generarTexturaSO(terreno.getTextura());
            generarTexturaSE(terreno.getTextura());
        }
    }

    private void generarTexturaNO(TextureRegion terreno)
    {
        int cuadrante = MiscData.TILESIZE/2;

        if (  ad.NOizquierda &&  ad.NOdiagonal &&  ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*2, cuadrante*4, cuadrante, cuadrante); }
        if (  ad.NOizquierda &&  ad.NOdiagonal && !ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*2, cuadrante*2, cuadrante, cuadrante); }
        if (  ad.NOizquierda && !ad.NOdiagonal &&  ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*2, cuadrante*0, cuadrante, cuadrante); }
        if (  ad.NOizquierda && !ad.NOdiagonal && !ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*2, cuadrante*2, cuadrante, cuadrante); }
        if ( !ad.NOizquierda &&  ad.NOdiagonal &&  ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*0, cuadrante*4, cuadrante, cuadrante); }
        if ( !ad.NOizquierda &&  ad.NOdiagonal && !ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*0, cuadrante*2, cuadrante, cuadrante); }
        if ( !ad.NOizquierda && !ad.NOdiagonal &&  ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*0, cuadrante*4, cuadrante, cuadrante); }
        if ( !ad.NOizquierda && !ad.NOdiagonal && !ad.NOarriba ) { cuadranteNO = new TextureRegion(terreno, cuadrante*0, cuadrante*2, cuadrante, cuadrante); }
    }

    private void generarTexturaNE(TextureRegion terreno)
    {
        int cuadrante = MiscData.TILESIZE/2;

        if (  ad.NEderecha &&  ad.NEdiagonal &&  ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*1, cuadrante*4, cuadrante, cuadrante); }
        if (  ad.NEderecha &&  ad.NEdiagonal && !ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*1, cuadrante*2, cuadrante, cuadrante); }
        if (  ad.NEderecha && !ad.NEdiagonal &&  ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*3, cuadrante*0, cuadrante, cuadrante); }
        if (  ad.NEderecha && !ad.NEdiagonal && !ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*1, cuadrante*2, cuadrante, cuadrante); }
        if ( !ad.NEderecha &&  ad.NEdiagonal &&  ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*3, cuadrante*4, cuadrante, cuadrante); }
        if ( !ad.NEderecha &&  ad.NEdiagonal && !ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*3, cuadrante*2, cuadrante, cuadrante); }
        if ( !ad.NEderecha && !ad.NEdiagonal &&  ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*3, cuadrante*4, cuadrante, cuadrante); }
        if ( !ad.NEderecha && !ad.NEdiagonal && !ad.NEarriba )   { cuadranteNE = new TextureRegion(terreno, cuadrante*3, cuadrante*2, cuadrante, cuadrante); }
    }

    private void generarTexturaSO(TextureRegion terreno)
    {
        int cuadrante = MiscData.TILESIZE/2;

        if (  ad.SOizquierda &&  ad.SOdiagonal &&  ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*2, cuadrante*3, cuadrante, cuadrante); }
        if (  ad.SOizquierda &&  ad.SOdiagonal && !ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*2, cuadrante*5, cuadrante, cuadrante); }
        if (  ad.SOizquierda && !ad.SOdiagonal &&  ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*2, cuadrante*1, cuadrante, cuadrante); }
        if (  ad.SOizquierda && !ad.SOdiagonal && !ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*2, cuadrante*5, cuadrante, cuadrante); }
        if ( !ad.SOizquierda &&  ad.SOdiagonal &&  ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*0, cuadrante*3, cuadrante, cuadrante); }
        if ( !ad.SOizquierda &&  ad.SOdiagonal && !ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*0, cuadrante*5, cuadrante, cuadrante); }
        if ( !ad.SOizquierda && !ad.SOdiagonal &&  ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*0, cuadrante*3, cuadrante, cuadrante); }
        if ( !ad.SOizquierda && !ad.SOdiagonal && !ad.SOabajo )  { cuadranteSO = new TextureRegion(terreno, cuadrante*0, cuadrante*5, cuadrante, cuadrante); }
    }

    private void generarTexturaSE(TextureRegion terreno)
    {
        int cuadrante = MiscData.TILESIZE/2;

        if (  ad.SEderecha &&  ad.SEdiagonal &&  ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*1, cuadrante*3, cuadrante, cuadrante); }
        if (  ad.SEderecha &&  ad.SEdiagonal && !ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*1, cuadrante*5, cuadrante, cuadrante); }
        if (  ad.SEderecha && !ad.SEdiagonal &&  ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*3, cuadrante*1, cuadrante, cuadrante); }
        if (  ad.SEderecha && !ad.SEdiagonal && !ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*1, cuadrante*5, cuadrante, cuadrante); }
        if ( !ad.SEderecha &&  ad.SEdiagonal &&  ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*3, cuadrante*3, cuadrante, cuadrante); }
        if ( !ad.SEderecha &&  ad.SEdiagonal && !ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*3, cuadrante*5, cuadrante, cuadrante); }
        if ( !ad.SEderecha && !ad.SEdiagonal &&  ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*3, cuadrante*3, cuadrante, cuadrante); }
        if ( !ad.SEderecha && !ad.SEdiagonal && !ad.SEabajo )    { cuadranteSE = new TextureRegion(terreno, cuadrante*3, cuadrante*5, cuadrante, cuadrante); }
    }
}
