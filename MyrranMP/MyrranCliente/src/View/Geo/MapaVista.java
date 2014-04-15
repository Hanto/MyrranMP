package View.Geo;// Created by Hanto on 15/04/2014.

import Model.DTO.TerrenoDTO;
import Model.Geo.MapaModel;
import View.Vista;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import zMain.MiscData;

public class MapaVista extends TiledMap
{
    private Vista vista;
    private MapaModel mapa;

    public MapaVista(Vista vista)
    {
        this.vista = vista;
        this.mapa = vista.mundoModel.mapa;
        crearTiledMap();
    }

    public void crearTiledMap()
    {
        Cell cell;
        StaticTiledMapTile tileNO, tileNE, tileSO, tileSE;
        TerrenoDTO.Adyacencias adyacencias;
        TerrenoView terrenoView;

        for (int numCapa = 0; numCapa< MiscData.MAPA_Max_Capas_Terreno; numCapa++)
        {
            TiledMapTileLayer suelo = new TiledMapTileLayer(MiscData.MAPA_Max_X*2, MiscData.MAPA_Max_Y *2, MiscData.TILESIZE/2, MiscData.TILESIZE/2);
            for (int x = 0; x < MiscData.MAPA_Max_X; x++)
            {
                for (int y = 0; y < MiscData.MAPA_Max_Y; y++)
                {
                    if (mapa.getTerreno(x, y, numCapa) != null)
                    {
                        adyacencias = calcularAdyacencias(x,y,numCapa);
                        terrenoView = new TerrenoView(adyacencias);

                        tileNO = new StaticTiledMapTile(terrenoView.cuadranteNO);
                        tileNE = new StaticTiledMapTile(terrenoView.cuadranteNE);
                        tileSO = new StaticTiledMapTile(terrenoView.cuadranteSO);
                        tileSE = new StaticTiledMapTile(terrenoView.cuadranteSE);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileNO);
                        suelo.setCell(x*2, y*2+1, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileNE);
                        suelo.setCell(x*2+1, y*2+1, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileSO);
                        suelo.setCell(x*2, y*2, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileSE);
                        suelo.setCell(x*2+1, y*2, cell);
                    }
                }
            }
            getLayers().add(suelo);
        }
    }

    private TerrenoDTO.Adyacencias calcularAdyacencias (int X, int Y, int capa)
    {
        TerrenoDTO.Adyacencias ad = new TerrenoDTO.Adyacencias();

        if      (Y+1 >= MiscData.MAPA_Max_Y)                { ad.NOarriba = false; ad.NEarriba = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X,Y+1,capa)))              { ad.NOarriba = true; ad.NEarriba = true; }

        if      (Y-1 < 0)                                   { ad.SOabajo = false; ad.SEabajo = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X,Y-1,capa)))              { ad.SOabajo = true; ad.SEabajo = true; }

        if      (X-1 < 0)                                   { ad.NOizquierda = false; ad.SOizquierda = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y,capa)))              { ad.NOizquierda = true; ad.SOizquierda = true; }

        if      (X+1 >= MiscData.MAPA_Max_X)                { ad.NEderecha = false; ad.SEderecha = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y,capa)))              { ad.NEderecha = true; ad.SEderecha = true; }

        if      (X+1 >= MiscData.MAPA_Max_X ||
                 Y+1 >= MiscData.MAPA_Max_Y)                { ad.NEdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y+1,capa)))            { ad.NEdiagonal = true; }

        if      (X-1<0 || Y-1<0)                            { ad.SOdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y-1,capa)))            { ad.SOdiagonal = true; }

        if      (X-1 <0 || Y+1 >= MiscData.MAPA_Max_Y)      { ad.NOdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y+1,capa)))            { ad.NOdiagonal = true; }

        if      (X+1 >= MiscData.MAPA_Max_X || Y-1<0)       { ad.SEdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y-1,capa)))            { ad.SEdiagonal = true; }

        ad.iDTerreno = mapa.getTerreno(X,Y,capa).getNombre();
        return ad;
    }
}
