package View.Geo;// Created by Hanto on 15/04/2014.

import Data.MiscData;
import Model.DTO.TerrenoDTO;
import Model.Geo.Mapa;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class SubMapaView extends TiledMap
{
    private Mapa mapa;
    private OrthogonalTiledMapRenderer mapRenderer;

    //mapTile, cuadrante que se renderiza
    private int mapTileX;
    private int mapTileY;
    //tile origen y final que lo componen
    private int tileOrigenX;
    private int tileFinalX;
    private int tileOrigenY;
    private int tileFinalY;

    //GET:
    public int getMapTileX()                               { return mapTileX; }
    public int getMapTileY()                               { return mapTileY; }
    //SET:
    public void setView(OrthographicCamera camara)  { mapRenderer.setView(camara); }


    public SubMapaView(Mapa mapaModel, int mapTileOrigenX, int mapTileOrigenY)
    {
        this.mapa = mapaModel;
        this.mapRenderer = new OrthogonalTiledMapRenderer(this);
        crearTiledMap(mapTileOrigenX, mapTileOrigenY);
    }

    public void ajustarCoordenadas(int mapTileOrigenX, int mapTileOrigenY)
    {
        this.mapTileX = mapTileOrigenX; this.mapTileY = mapTileOrigenY;

        this.tileOrigenX = mapTileOrigenX * MiscData.MAPAVIEW_Max_TilesX;
        this.tileOrigenY = mapTileOrigenY * MiscData.MAPAVIEW_Max_TilesY;
        this.tileFinalX = mapTileOrigenX * MiscData.MAPAVIEW_Max_TilesX + MiscData.MAPAVIEW_Max_TilesX;
        this.tileFinalY = mapTileOrigenY * MiscData.MAPAVIEW_Max_TilesY + MiscData.MAPAVIEW_Max_TilesY;
    }

    private void borrarTodosLosLayers ()
    {
        while (getLayers().getCount()>0)
            getLayers().remove(0);
    }

    public void crearTiledMap(int mapTileOrigenX, int mapTileOrigenY)
    {
        ajustarCoordenadas(mapTileOrigenX, mapTileOrigenY);
        borrarTodosLosLayers();

        Cell cell;
        StaticTiledMapTile tileNO, tileNE, tileSO, tileSE;
        TerrenoDTO.Adyacencias adyacencias;
        TerrenoView terrenoView;

        for (int numCapa = 0; numCapa< MiscData.MAPA_Max_Capas_Terreno; numCapa++)
        {
            TiledMapTileLayer suelo = new TiledMapTileLayer
                (MiscData.MAPAVIEW_Max_TilesX *2, MiscData.MAPAVIEW_Max_TilesY *2, MiscData.TILESIZE/2, MiscData.TILESIZE/2);

            for (int x = this.tileOrigenX; x < tileFinalX; x++)
            {
                for (int y = this.tileOrigenY; y < tileFinalY; y++)
                {
                    if (x < 0 || y < 0 || x > MiscData.MAPA_Max_TilesX || y > MiscData.MAPA_Max_TilesY) { break; }
                    else if (mapa.getTerrenoID(x, y, numCapa) >= 0)
                    {
                        adyacencias = calcularAdyacencias(x,y,numCapa);
                        terrenoView = new TerrenoView(adyacencias);

                        tileNO = new StaticTiledMapTile(terrenoView.cuadranteNO);
                        tileNE = new StaticTiledMapTile(terrenoView.cuadranteNE);
                        tileSO = new StaticTiledMapTile(terrenoView.cuadranteSO);
                        tileSE = new StaticTiledMapTile(terrenoView.cuadranteSE);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileNO);
                        suelo.setCell((x- tileOrigenX)*2, (y- tileOrigenY)*2+1, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileNE);
                        suelo.setCell((x- tileOrigenX)*2+1, (y- tileOrigenY)*2+1, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileSO);
                        suelo.setCell((x- tileOrigenX)*2, (y- tileOrigenY)*2, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileSE);
                        suelo.setCell((x- tileOrigenX)*2+1, (y- tileOrigenY)*2, cell);
                    }
                }
            }
            getLayers().add(suelo);
        }
        addGrid();
    }

    private TerrenoDTO.Adyacencias calcularAdyacencias (int X, int Y, int capa)
    {
        TerrenoDTO.Adyacencias ad = new TerrenoDTO.Adyacencias();

        ad.iDTerreno = mapa.getTerrenoID(X,Y,capa);

        if      (Y+1 >= MiscData.MAPA_Max_TilesY)                { ad.NOarriba = false; ad.NEarriba = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X,Y+1,capa)))              { ad.NOarriba = true; ad.NEarriba = true; }

        if      (Y-1 < 0)                                   { ad.SOabajo = false; ad.SEabajo = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X,Y-1,capa)))              { ad.SOabajo = true; ad.SEabajo = true; }

        if      (X-1 < 0)                                   { ad.NOizquierda = false; ad.SOizquierda = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y,capa)))              { ad.NOizquierda = true; ad.SOizquierda = true; }

        if      (X+1 >= MiscData.MAPA_Max_TilesX)                { ad.NEderecha = false; ad.SEderecha = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y,capa)))              { ad.NEderecha = true; ad.SEderecha = true; }

        if      (X+1 >= MiscData.MAPA_Max_TilesX ||
                 Y+1 >= MiscData.MAPA_Max_TilesY)                { ad.NEdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y+1,capa)))            { ad.NEdiagonal = true; }

        if      (X-1<0 || Y-1<0)                            { ad.SOdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y-1,capa)))            { ad.SOdiagonal = true; }

        if      (X-1 <0 || Y+1 >= MiscData.MAPA_Max_TilesY)      { ad.NOdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y+1,capa)))            { ad.NOdiagonal = true; }

        if      (X+1 >= MiscData.MAPA_Max_TilesX || Y-1<0)       { ad.SEdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y-1,capa)))            { ad.SEdiagonal = true; }


        return ad;
    }

    private void addGrid ()
    {
        TiledMapTileLayer layerGrid = new TiledMapTileLayer
            (MiscData.MAPAVIEW_Max_TilesX, MiscData.MAPAVIEW_Max_TilesY, MiscData.TILESIZE, MiscData.TILESIZE);

        StaticTiledMapTile grid = new StaticTiledMapTile(GeoRecursos.get().grid);

        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        for (int x = 0; x < MiscData.MAPAVIEW_Max_TilesX; x++)
        {
            for (int y = 0; y < MiscData.MAPAVIEW_Max_TilesY; y++)
            {
                cell.setTile(grid);
                layerGrid.setCell(x, y, cell);
            }
        }
        layerGrid.setName("LayerGrid");
        getLayers().add(layerGrid);

    }


    @Override public void dispose()
    {
        mapRenderer.dispose();
        super.dispose();
    }

    public void render()
    {
        mapRenderer.render();
    }
}
