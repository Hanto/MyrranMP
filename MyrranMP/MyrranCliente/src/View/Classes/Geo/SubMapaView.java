package View.Classes.Geo;// Created by Hanto on 15/04/2014.

import Data.MiscData;
import Model.Classes.Geo.MapaSeamless;
import Model.DTO.TerrenoDTO;
import Recursos.DAO.TerrenoRecursos.DB.TerrenoRecursosLocalDB;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class SubMapaView extends TiledMap
{
    private MapaSeamless mapa;
    private OrthogonalTiledMapRenderer mapRenderer;

    //mapTile, cuadrante que se renderiza
    private int mapTileX;
    private int mapTileY;
    //tile origen y final que lo componen
    private int tileOrigenX;
    private int tileFinalX;
    private int tileOrigenY;
    private int tileFinalY;
    //numero de Tiles de ancho y alto
    private int tamañoX;
    private int tamañoY;

    //GET:
    public int getMapTileX()                        { return mapTileX; }
    public int getMapTileY()                        { return mapTileY; }
    //SET:
    public void setView(OrthographicCamera camara)  { mapRenderer.setView(camara); }


    public SubMapaView(MapaSeamless mapaModel, int tamañoX, int tamañoY)
    {
        this.mapa = mapaModel;
        this.mapRenderer = new OrthogonalTiledMapRenderer(this);
        this.tamañoX = tamañoX;
        this.tamañoY = tamañoY;
    }

    public void crearTiledMap ()  { crearTiledMap(mapTileX, mapTileY);}
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
                (tamañoX *2, tamañoY *2, MiscData.TILESIZE/2, MiscData.TILESIZE/2);

            for (int x = this.tileOrigenX; x < tileFinalX; x++)
            {
                for (int y = this.tileOrigenY; y < tileFinalY; y++)
                {
                    if (x < 0 || y < 0 || x >= MiscData.MAPA_Max_TilesX || y >= MiscData.MAPA_Max_TilesY) { break; }
                    else if (mapa.getTerrenoID(x, y, numCapa) >= 0)
                    {
                        adyacencias = calcularAdyacencias(x,y,numCapa);
                        terrenoView = new TerrenoView(adyacencias);

                        tileNO = new StaticTiledMapTile(terrenoView.cuadranteNO);
                        tileNE = new StaticTiledMapTile(terrenoView.cuadranteNE);
                        tileSO = new StaticTiledMapTile(terrenoView.cuadranteSO);
                        tileSE = new StaticTiledMapTile(terrenoView.cuadranteSE);

                        cell = new Cell();
                        cell.setTile(tileNO);
                        suelo.setCell((x- tileOrigenX)*2, (y- tileOrigenY)*2+1, cell);

                        cell = new Cell();
                        cell.setTile(tileNE);
                        suelo.setCell((x- tileOrigenX)*2+1, (y- tileOrigenY)*2+1, cell);

                        cell = new Cell();
                        cell.setTile(tileSO);
                        suelo.setCell((x- tileOrigenX)*2, (y- tileOrigenY)*2, cell);

                        cell = new Cell();
                        cell.setTile(tileSE);
                        suelo.setCell((x- tileOrigenX)*2+1, (y- tileOrigenY)*2, cell);
                    }
                }
            }
            getLayers().add(suelo);
        }
        addGrid();
    }

    public void ajustarCoordenadas(int mapTileOrigenX, int mapTileOrigenY)
    {
        this.mapTileX = mapTileOrigenX; this.mapTileY = mapTileOrigenY;

        this.tileOrigenX = mapTileOrigenX * tamañoX;
        this.tileOrigenY = mapTileOrigenY * tamañoY;
        this.tileFinalX = mapTileOrigenX * tamañoX + tamañoX;
        this.tileFinalY = mapTileOrigenY * tamañoY + tamañoY;
    }

    private void borrarTodosLosLayers ()
    {
        while (getLayers().getCount()>0)
            getLayers().remove(0);
    }

    private TerrenoDTO.Adyacencias calcularAdyacencias (int X, int Y, int capa)
    {
        TerrenoDTO.Adyacencias ad = new TerrenoDTO.Adyacencias();

        ad.iDTerreno = mapa.getTerrenoID(X,Y,capa);

        if      (Y+1 >= MiscData.MAPA_Max_TilesY)           { ad.NOarriba = false; ad.NEarriba = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X,Y+1,capa)))              { ad.NOarriba = true; ad.NEarriba = true; }

        if      (Y-1 < 0)                                   { ad.SOabajo = false; ad.SEabajo = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X,Y-1,capa)))              { ad.SOabajo = true; ad.SEabajo = true; }

        if      (X-1 < 0)                                   { ad.NOizquierda = false; ad.SOizquierda = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y,capa)))              { ad.NOizquierda = true; ad.SOizquierda = true; }

        if      (X+1 >= MiscData.MAPA_Max_TilesX)           { ad.NEderecha = false; ad.SEderecha = false; }
        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y,capa)))              { ad.NEderecha = true; ad.SEderecha = true; }

        if      (X+1 >= MiscData.MAPA_Max_TilesX ||
                 Y+1 >= MiscData.MAPA_Max_TilesY)           { ad.NEdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y+1,capa)))            { ad.NEdiagonal = true; }

        if      (X-1<0 || Y-1<0)                            { ad.SOdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y-1,capa)))            { ad.SOdiagonal = true; }

        if      (X-1 <0 || Y+1 >= MiscData.MAPA_Max_TilesY) { ad.NOdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X-1,Y+1,capa)))            { ad.NOdiagonal = true; }

        if      (X+1 >= MiscData.MAPA_Max_TilesX || Y-1<0)  { ad.SEdiagonal = false; }

        else if (mapa.getTerreno(X,Y,capa) ==
                (mapa.getTerreno(X+1,Y-1,capa)))            { ad.SEdiagonal = true; }


        return ad;
    }

    private void addGrid ()
    {
        TiledMapTileLayer layerGrid = new TiledMapTileLayer
            (tamañoX, tamañoY, MiscData.TILESIZE, MiscData.TILESIZE);

        StaticTiledMapTile grid = new StaticTiledMapTile(TerrenoRecursosLocalDB.get().grid);

        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        for (int x = 0; x < tamañoX; x++)
        {
            for (int y = 0; y < tamañoY; y++)
            {
                cell.setTile(grid);
                layerGrid.setCell(x, y, cell);
            }
        }
        layerGrid.setName("LayerGrid");
        getLayers().add(layerGrid);

    }

    private void borrarTile (int tileX, int tileY, int numCapa)
    {
        TiledMapTileLayer suelo = (TiledMapTileLayer)getLayers().get(numCapa);
        suelo.setCell((tileX - tileOrigenX)*2,  (tileY-tileOrigenY)*2+1, null);
        suelo.setCell((tileX - tileOrigenX)*2+1,(tileY-tileOrigenY)*2+1, null);
        suelo.setCell((tileX - tileOrigenX)*2,  (tileY-tileOrigenY)*2, null);
        suelo.setCell((tileX - tileOrigenX)*2+1,(tileY-tileOrigenY)*2, null);
    }


    public void crearTile (int tileX, int tileY, int numCapa)
    {
        if (tileX<0 || tileY<0 || tileX>= MiscData.MAPA_Max_TilesX || tileY>= MiscData.MAPA_Max_TilesY) { return; }

        if (mapa.getTerrenoID(tileX, tileY, numCapa) >= 0)
        {
            TerrenoDTO.Adyacencias adyacencias = calcularAdyacencias(tileX,tileY,numCapa);
            TerrenoView terrenoView = new TerrenoView(adyacencias);

            StaticTiledMapTile tileNO = new StaticTiledMapTile(terrenoView.cuadranteNO);
            StaticTiledMapTile tileNE = new StaticTiledMapTile(terrenoView.cuadranteNE);
            StaticTiledMapTile tileSO = new StaticTiledMapTile(terrenoView.cuadranteSO);
            StaticTiledMapTile tileSE = new StaticTiledMapTile(terrenoView.cuadranteSE);

            TiledMapTileLayer suelo = (TiledMapTileLayer)getLayers().get(numCapa);
            Cell cell;

            cell = new Cell();
            cell.setTile(tileNO);
            suelo.setCell((tileX- tileOrigenX)*2, (tileY- tileOrigenY)*2+1, cell);

            cell = new Cell();
            cell.setTile(tileNE);
            suelo.setCell((tileX- tileOrigenX)*2+1, (tileY- tileOrigenY)*2+1, cell);

            cell = new Cell();
            cell.setTile(tileSO);
            suelo.setCell((tileX- tileOrigenX)*2, (tileY- tileOrigenY)*2, cell);

            cell = new Cell();
            cell.setTile(tileSE);
            suelo.setCell((tileX- tileOrigenX)*2+1, (tileY- tileOrigenY)*2, cell);
        }
        else borrarTile(tileX, tileY, numCapa);
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
