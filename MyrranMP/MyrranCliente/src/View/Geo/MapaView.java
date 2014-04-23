package View.Geo;// Created by Hanto on 16/04/2014.

import Data.MiscData;
import Model.DTO.MapaDTO;
import Model.Geo.Mapa;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MapaView implements PropertyChangeListener
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
        mapaModel.añadirObservador(this);

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

        mapaO.crearTiledMap(mapTileX - 1, mapTileY);
        mapa.crearTiledMap(mapTileX, mapTileY);
        mapaE.crearTiledMap(mapTileX + 1, mapTileY);

        mapaNO.crearTiledMap(mapTileX - 1, mapTileY + 1);
        mapaN.crearTiledMap(mapTileX, mapTileY + 1);
        mapaNE.crearTiledMap(mapTileX + 1, mapTileY + 1);

        mapaSO.crearTiledMap(mapTileX - 1, mapTileY - 1);
        mapaS.crearTiledMap(mapTileX, mapTileY - 1);
        mapaSE.crearTiledMap(mapTileX + 1, mapTileY - 1);
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

    public void crearTile(int celdaX, int celdaY, int numCapa)
    {
        int mapTileX = celdaX/MiscData.MAPAVIEW_Max_TilesX;
        int mapTileY = celdaY/MiscData.MAPAVIEW_Max_TilesY;

        if (mapa.getMapTileX() == mapTileX && mapa.getMapTileY() == mapTileY) mapa.crearTile(celdaX, celdaY, numCapa);
        if (mapaE.getMapTileX() == mapTileX && mapaE.getMapTileY() == mapTileY) mapaE.crearTile(celdaX, celdaY, numCapa);
        if (mapaO.getMapTileX() == mapTileX && mapaO.getMapTileY() == mapTileY) mapaO.crearTile(celdaX, celdaY, numCapa);
        if (mapaN.getMapTileX() == mapTileX && mapaN.getMapTileY() == mapTileY) mapaN.crearTile(celdaX, celdaY, numCapa);
        if (mapaNE.getMapTileX() == mapTileX && mapaNE.getMapTileY() == mapTileY) mapaNE.crearTile(celdaX, celdaY, numCapa);
        if (mapaNO.getMapTileX() == mapTileX && mapaNO.getMapTileY() == mapTileY) mapaNO.crearTile(celdaX, celdaY, numCapa);
        if (mapaS.getMapTileX() == mapTileX && mapaS.getMapTileY() == mapTileY) mapaS.crearTile(celdaX, celdaY, numCapa);
        if (mapaSE.getMapTileX() == mapTileX && mapaSE.getMapTileY() == mapTileY) mapaSE.crearTile(celdaX, celdaY, numCapa);
        if (mapaSO.getMapTileX() == mapTileX && mapaSO.getMapTileY() == mapTileY) mapaSO.crearTile(celdaX, celdaY, numCapa);
    }

    public void setTerreno(int x, int y, int numCapa)
    {
        crearTile(x - 1, y - 1, numCapa);
        crearTile(x - 1, y + 0, numCapa);
        crearTile(x - 1, y + 1, numCapa);
        crearTile(x + 0, y - 1, numCapa);
        crearTile(x + 0, y + 0, numCapa);
        crearTile(x + 0, y + 1, numCapa);
        crearTile(x + 1, y - 1, numCapa);
        crearTile(x + 1, y + 0, numCapa);
        crearTile(x + 1, y + 1, numCapa);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof MapaDTO.SetTerreno)
        {
            int celdaX = ((MapaDTO.SetTerreno) evt.getNewValue()).celdaX;
            int celdaY = ((MapaDTO.SetTerreno) evt.getNewValue()).celdaY;
            int numCapa = ((MapaDTO.SetTerreno) evt.getNewValue()).numCapa;
            setTerreno(celdaX, celdaY, numCapa);
        }
    }
}
