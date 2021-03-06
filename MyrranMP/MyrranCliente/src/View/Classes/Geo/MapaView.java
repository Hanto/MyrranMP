package View.Classes.Geo;// Created by Hanto on 16/04/2014.

import DTO.NetDTO;
import Data.MiscData;
import Model.Classes.Geo.Mapa;
import View.GameState.MundoView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MapaView implements PropertyChangeListener
{
    private MundoView mundoView;
    private Mapa mapaModel;

    private OrthographicCamera camara;

    private SubMapaView[] listaSubMapas;

    private float xActual;                          //Coordenadas del centro de vision
    private float yActual;

    private int mapTileBordeE;                      //MapTile que hay justo a la derecha del limite de vision
    private int mapTileBordeO;                      //MapTile que hay justo a la izquierda del limite de vision
    private int mapTileBordeN;                      //MapTile que hay justo arriba del limite de vision
    private int mapTileBordeS;                      //MapTile que hay justo abajo del limtie de vision

    private int tamañoX;                            //numero de subMapas en el ejeX
    private int tamañoY;                            //numero de subMapas en el ejeY

    private int numTilesX;                          //numero de Tiles de ancho de cada submapa
    private int numTilesY;                          //numero de Tiles de alto de cada submapa

    public MapaView(Mapa mapaModel, MundoView mundoView, float posInicialX, float posInicialY, int tamañoX, int tamañoY)
    {
        this.mapaModel = mapaModel;
        this.mundoView = mundoView;
        this.tamañoX = tamañoX;
        this.tamañoY = tamañoY;
        this.mapaModel.añadirObservador(this);

        this.numTilesX = (int)Math.ceil((double)MiscData.MAPTILE_Horizontal_Resolution /(double)(tamañoX -1)/(double)MiscData.TILESIZE);
        this.numTilesY = (int)Math.ceil((double)MiscData.MAPTILE_Vertical_Resolution /(double)(tamañoY -1)/(double)MiscData.TILESIZE);

        camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        listaSubMapas = new SubMapaView[tamañoX * tamañoY];
        for (int i=0; i<listaSubMapas.length;i++)
        {   listaSubMapas[i] = new SubMapaView(this.mapaModel, numTilesX, numTilesY); }

        setPosition(posInicialX, posInicialY);
    }

    public void setPosition(float posX, float posY)
    {
        int mapTileInicialX = (int)(posX / (numTilesX *MiscData.TILESIZE));
        int mapTileInicialY = (int)(posY / (numTilesY *MiscData.TILESIZE));

        int xInicial, xFinal, yInicial;

        //tamañoX Impar:
        if (tamañoX %2 > 0)
        {   xInicial = -(tamañoX -1)/2;
            xFinal = (tamañoX -1)/2;
        }
        //tamañoX Par:
        else
        {   xInicial = -tamañoX /2;
            xFinal = tamañoX /2-1;
        }
        //TamañoY Par/Impar:
        if (tamañoY%2 >0) yInicial = (tamañoY -1)/2;
        else  yInicial = tamañoY /2-1;


        int x = xInicial; int y = yInicial;

        synchronized (listaSubMapas)
        {
            for (SubMapaView subMapaView : listaSubMapas)
            {
                subMapaView.crearTiledMap(x + mapTileInicialX, y + mapTileInicialY);
                x++; if (x > xFinal) { x = xInicial; y--; }
            }
        }
    }

    public void setView (SubMapaView subMapaView)
    {
        camara.zoom = mundoView.getCamara().zoom;
        camara.position.x = mundoView.getCamara().position.x - subMapaView.getMapTileX() * numTilesX *MiscData.TILESIZE;
        camara.position.y = mundoView.getCamara().position.y - subMapaView.getMapTileY() * numTilesY *MiscData.TILESIZE;
        camara.update();

        subMapaView.setView(camara);
    }

    public void render()
    {
        xActual = (mundoView.getCamara().position.x );
        yActual = (mundoView.getCamara().position.y );

        if (Math.abs(listaSubMapas[0].getMapTileX() -  (xActual /  (numTilesX *MiscData.TILESIZE))) > tamañoX ||
            Math.abs(listaSubMapas[0].getMapTileY() -  (yActual /  (numTilesY *MiscData.TILESIZE))) > tamañoY )
            setPosition(xActual, yActual);

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
        mapTileBordeE = (int)(xActual + MiscData.MAPTILE_Horizontal_Resolution /2) /  (numTilesX *MiscData.TILESIZE);
        mapTileBordeO = (int)(xActual - MiscData.MAPTILE_Horizontal_Resolution /2) /  (numTilesX *MiscData.TILESIZE);
        mapTileBordeN = (int)(yActual + MiscData.MAPTILE_Vertical_Resolution /2) /    (numTilesY *MiscData.TILESIZE);
        mapTileBordeS = (int)(yActual - MiscData.MAPTILE_Vertical_Resolution /2) /    (numTilesY *MiscData.TILESIZE);

        if (mapTileBordeE >= (subMapaView.getMapTileX() + tamañoX))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX() + tamañoX,  subMapaView.getMapTileY()); }

        if (mapTileBordeO <= (subMapaView.getMapTileX() - tamañoX))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX() - tamañoX,  subMapaView.getMapTileY()); }

        if (mapTileBordeN >= (subMapaView.getMapTileY() + tamañoY))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()         ,   subMapaView.getMapTileY() + tamañoY); }

        if (mapTileBordeS <= (subMapaView.getMapTileY() - tamañoY))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()         ,   subMapaView.getMapTileY() - tamañoY); }
    }

    public void crearTile(int celdaX, int celdaY, int numCapa)
    {
        int mapTileX = celdaX/ numTilesX;
        int mapTileY = celdaY/ numTilesY;

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
        if (evt.getNewValue() instanceof NetDTO.SetTerreno)
        {
            int celdaX = ((NetDTO.SetTerreno) evt.getNewValue()).celdaX;
            int celdaY = ((NetDTO.SetTerreno) evt.getNewValue()).celdaY;
            int numCapa = ((NetDTO.SetTerreno) evt.getNewValue()).numCapa;

            synchronized (listaSubMapas)
            {   setTerreno(celdaX, celdaY, numCapa); }
        }
    }
}
