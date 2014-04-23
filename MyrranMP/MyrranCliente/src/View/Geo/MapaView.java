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

    private SubMapaView[] listaSubMapas;
    private int tamaño;

    private int xActual;
    private int yActual;

    private int bordeE;
    private int bordeO;
    private int bordeN;
    private int bordeS;

    public MapaView(Mapa mapaModel, float posInicialX, float posInicialY, int size, Vista vista)
    {
        this.mapaModel = mapaModel;
        this.vista = vista;
        this.mapaModel.añadirObservador(this);

        camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (size <=0) size = 1;
        tamaño = 3*(int)Math.pow(2,size-1);

        listaSubMapas = new SubMapaView[tamaño*tamaño];
        for (int i=0; i<listaSubMapas.length;i++)
        {   listaSubMapas[i] = new SubMapaView(this.mapaModel); }

        setPosition(posInicialX, posInicialY);
    }

    public void setPosition(float posX, float posY)
    {
        int mapTileInicialX = (int)(posX / (MiscData.MAPAVIEW_Max_TilesX*MiscData.TILESIZE));
        int mapTileInicialY = (int)(posY / (MiscData.MAPAVIEW_Max_TilesY*MiscData.TILESIZE));

        int xInicial, xFinal, yInicial;

        if (tamaño == 3)
        {   xInicial = -1;
            xFinal = 1;
            yInicial = 1;
        }
        else
        {   xInicial = -tamaño/2;
            xFinal = tamaño/2-1;
            yInicial = tamaño/2-1;
        }

        int x = xInicial; int y = yInicial;

        for (int i=0; i<listaSubMapas.length;i++)
        {
            listaSubMapas[i].crearTiledMap(x +mapTileInicialX, y +mapTileInicialY);
            x++;
            if (x > xFinal) {x = xInicial; y--;}
        }
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
        xActual = (int)((vista.camara.position.x ));
        yActual = (int)((vista.camara.position.y ));

        for (SubMapaView subMapaView: listaSubMapas)
        {
            mapaVistaLoader(subMapaView);
            setView(subMapaView);
            subMapaView.render();
        }
    }

    public void dispose()
    {
        for (SubMapaView subMapaView: listaSubMapas)
        {   subMapaView.dispose(); }

    }

    public void mapaVistaLoader(SubMapaView subMapaView)
    {
        bordeE = (xActual+Gdx.graphics.getWidth()/2) / (MiscData.MAPAVIEW_Max_TilesX*MiscData.TILESIZE);
        bordeO = (xActual-Gdx.graphics.getWidth()/2) / (MiscData.MAPAVIEW_Max_TilesX*MiscData.TILESIZE);
        bordeN = (yActual+Gdx.graphics.getHeight()/2) / (MiscData.MAPAVIEW_Max_TilesY*MiscData.TILESIZE);
        bordeS = (yActual-Gdx.graphics.getHeight()/2) / (MiscData.MAPAVIEW_Max_TilesY*MiscData.TILESIZE);

        if (bordeE >= (subMapaView.getMapTileX() + tamaño))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX() + tamaño,    subMapaView.getMapTileY()); }

        if (bordeO <= (subMapaView.getMapTileX() - tamaño))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX() - tamaño,    subMapaView.getMapTileY()); }

        if (bordeN >= (subMapaView.getMapTileY() + tamaño))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()         ,    subMapaView.getMapTileY() + tamaño); }

        if (bordeS <= (subMapaView.getMapTileY() - tamaño))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()         ,    subMapaView.getMapTileY() - tamaño); }
    }

    public void crearTile(int celdaX, int celdaY, int numCapa)
    {
        int mapTileX = celdaX/MiscData.MAPAVIEW_Max_TilesX;
        int mapTileY = celdaY/MiscData.MAPAVIEW_Max_TilesY;

        for (SubMapaView subMapaView: listaSubMapas)
        {
            if (subMapaView.getMapTileX() == mapTileX && subMapaView.getMapTileY() == mapTileY)
            {   subMapaView.crearTile(celdaX, celdaY, numCapa); }
        }
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
