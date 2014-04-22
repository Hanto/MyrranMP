package View.Geo;// Created by Hanto on 16/04/2014.

import Data.MiscData;
import Model.Geo.Mapa;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MapaView
{
    private Vista vista;
    private Mapa mapaModel;

    private OrthographicCamera camara;

    private SubMapaView mapaE;
    private SubMapaView mapa;
    private SubMapaView mapaO;
    private SubMapaView mapaNO;
    private SubMapaView mapaN;
    private SubMapaView mapaNE;
    private SubMapaView mapaSO;
    private SubMapaView mapaS;
    private SubMapaView mapaSE;

    private int mapTileActualX;
    private int mapTileActualY;

    public MapaView(Mapa mapaModel, float posInicialX, float posInicialY, Vista vista)
    {
        this.mapaModel = mapaModel;
        this.vista = vista;
        camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mapaO = new SubMapaView(this.mapaModel);
        mapa = new SubMapaView(this.mapaModel);
        mapaE = new SubMapaView(this.mapaModel);

        mapaNO = new SubMapaView(this.mapaModel);
        mapaN = new SubMapaView(this.mapaModel);
        mapaNE = new SubMapaView(this.mapaModel);

        mapaSO = new SubMapaView(this.mapaModel);
        mapaS = new SubMapaView(this.mapaModel);
        mapaSE = new SubMapaView(this.mapaModel);

        setPosition(posInicialX, posInicialY);
    }

    public void setPosition(float x, float y)
    {
        int mapTileX = (int)(x / (MiscData.MAPAVIEW_Max_TilesX*MiscData.TILESIZE));
        int mapTileY = (int)(y / (MiscData.MAPAVIEW_Max_TilesY*MiscData.TILESIZE));

        mapaO.crearTiledMap(mapTileX-1,    mapTileY);
        mapa.crearTiledMap (mapTileX  ,    mapTileY);
        mapaE.crearTiledMap(mapTileX+1,    mapTileY);

        mapaNO.crearTiledMap(mapTileX-1,   mapTileY+1);
        mapaN.crearTiledMap (mapTileX  ,   mapTileY+1);
        mapaNE.crearTiledMap(mapTileX+1,   mapTileY+1);

        mapaSO.crearTiledMap(mapTileX-1,   mapTileY-1);
        mapaS.crearTiledMap (mapTileX  ,   mapTileY-1);
        mapaSE.crearTiledMap(mapTileX+1,   mapTileY-1);
    }

    public void setView (SubMapaView subMapaView)
    {
        camara.zoom = vista.camara.zoom;
        camara.position.x = vista.camara.position.x - subMapaView.getMapTileX() *MiscData.MAPAVIEW_Max_TilesX*MiscData.TILESIZE;
        camara.position.y = vista.camara.position.y - subMapaView.getMapTileY() *MiscData.MAPAVIEW_Max_TilesY*MiscData.TILESIZE;
        camara.update();
        subMapaView.setView(camara);
    }

    public void render()
    {
        //TODO mitad ancho personaje
        //TODO mitad alto personaje
        mapTileActualX = (int)((vista.camara.position.x + 24) / (MiscData.MAPAVIEW_Max_TilesX*MiscData.TILESIZE));
        mapTileActualY = (int)((vista.camara.position.y + 24) / (MiscData.MAPAVIEW_Max_TilesY*MiscData.TILESIZE));

        mapaVistaLoader(mapa);
        mapaVistaLoader(mapaE);
        mapaVistaLoader(mapaO);
        mapaVistaLoader(mapaNO);
        mapaVistaLoader(mapaN);
        mapaVistaLoader(mapaNE);
        mapaVistaLoader(mapaSO);
        mapaVistaLoader(mapaS);
        mapaVistaLoader(mapaSE);

        setView(mapa);
        setView(mapaE);
        setView(mapaO);
        setView(mapaNO);
        setView(mapaN);
        setView(mapaNE);
        setView(mapaSO);
        setView(mapaS);
        setView(mapaSE);

        mapa.render();
        mapaO.render();
        mapaE.render();
        mapaNO.render();
        mapaN.render();
        mapaNE.render();
        mapaSO.render();
        mapaS.render();
        mapaSE.render();
    }

    public void dispose()
    {
        mapa.dispose();
        mapaE.dispose();
        mapaO.dispose();
        mapaNO.dispose();
        mapaN.dispose();
        mapaNE.dispose();
        mapaSO.dispose();
        mapaS.dispose();
        mapaSE.dispose();
    }

    public void mapaVistaLoader(SubMapaView subMapaView)
    {
        if (mapTileActualX > (subMapaView.getMapTileX() + 1))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX() + 3,    subMapaView.getMapTileY()    ); }

        if (mapTileActualX < (subMapaView.getMapTileX() - 1))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX() - 3,    subMapaView.getMapTileY()    ); }

        if (mapTileActualY > (subMapaView.getMapTileY() + 1 ))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()    ,    subMapaView.getMapTileY() + 3); }

        if (mapTileActualY < (subMapaView.getMapTileY() - 1))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()    ,    subMapaView.getMapTileY() - 3); }
    }
}
