package View.Geo;// Created by Hanto on 15/04/2014.

import Model.DTO.TerrenoDTO;
import Model.Geo.MapaModel;
import View.Vista;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import zMain.MiscData;

public class SubMapaVista extends TiledMap
{
    private Vista vista;
    private MapaModel mapa;
    private OrthogonalTiledMapRenderer mapRenderer;

    private int x;
    private int y;

    private int origenX;
    private int origenY;
    private int finalX;
    private int finalY;

    //GET:
    public int getX()                               { return x; }
    public int getY()                               { return y; }
    //SET:
    public void setView(OrthographicCamera camara)  { mapRenderer.setView(camara); }


    public SubMapaVista(Vista vista, int OrigenX, int OrigenY)
    {
        this.vista = vista;
        this.mapa = vista.mundoModel.mapa;
        this.mapRenderer = new OrthogonalTiledMapRenderer(this);
        crearTiledMap(OrigenX, OrigenY);
    }

    public void ajustarCoordenadas(int origenX, int origenY)
    {
        this.x = origenX; this.y = origenY;

        if (origenX < 0) this.origenX = 0; else this.origenX = origenX;
        if (origenY < 0) this.origenY = 0; else this.origenY = origenY;

        finalX = origenX + MiscData.MAPAVIEW_Max_X;
        if (finalX > MiscData.MAPA_Max_X) finalX = MiscData.MAPA_Max_X;
        finalY = origenY + MiscData.MAPAVIEW_Max_Y;
        if (finalY > MiscData.MAPA_Max_Y) finalY = MiscData.MAPA_Max_Y;
    }

    private void borrarTodosLosLayers ()
    {
        while (getLayers().getCount()>0)
            getLayers().remove(0);
    }

    public void crearTiledMap(int OrigenX, int OrigenY)
    {
        ajustarCoordenadas(OrigenX, OrigenY);
        borrarTodosLosLayers();

        Cell cell;
        StaticTiledMapTile tileNO, tileNE, tileSO, tileSE;
        TerrenoDTO.Adyacencias adyacencias;
        TerrenoView terrenoView;

        for (int numCapa = 0; numCapa< MiscData.MAPA_Max_Capas_Terreno; numCapa++)
        {
            TiledMapTileLayer suelo =
                new TiledMapTileLayer(MiscData.MAPAVIEW_Max_X*2, MiscData.MAPAVIEW_Max_Y *2, MiscData.TILESIZE/2, MiscData.TILESIZE/2);

            for (int x = origenX; x < finalX; x++)
            {
                for (int y = origenY; y < finalY; y++)
                {
                    if (mapa.getTerrenoID(x, y, numCapa) >= 0)
                    {
                        adyacencias = calcularAdyacencias(x,y,numCapa);
                        terrenoView = new TerrenoView(adyacencias);

                        tileNO = new StaticTiledMapTile(terrenoView.cuadranteNO);
                        tileNE = new StaticTiledMapTile(terrenoView.cuadranteNE);
                        tileSO = new StaticTiledMapTile(terrenoView.cuadranteSO);
                        tileSE = new StaticTiledMapTile(terrenoView.cuadranteSE);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileNO);
                        suelo.setCell((x-origenX)*2, (y-origenY)*2+1, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileNE);
                        suelo.setCell((x-origenX)*2+1, (y-origenY)*2+1, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileSO);
                        suelo.setCell((x-origenX)*2, (y-origenY)*2, cell);

                        cell = new TiledMapTileLayer.Cell();
                        cell.setTile(tileSE);
                        suelo.setCell((x-origenX)*2+1, (y-origenY)*2, cell);
                    }
                }
            }
            getLayers().add(suelo);
        }
    }

    private TerrenoDTO.Adyacencias calcularAdyacencias (int X, int Y, int capa)
    {
        TerrenoDTO.Adyacencias ad = new TerrenoDTO.Adyacencias();

        ad.iDTerreno = mapa.getTerrenoID(X,Y,capa);

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


        return ad;
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
