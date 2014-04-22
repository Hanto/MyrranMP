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

    private float x;
    private float y;

    public MapaView(Mapa mapaModel, float posInicialX, float posInicialY, Vista vista)
    {
        this.mapaModel = mapaModel;
        this.vista = vista;

        int inicialX = (int)(posInicialX/MiscData.TILESIZE - MiscData.MAPAVIEW_Max_X/2);
        int inicialY = (int)(posInicialY/MiscData.TILESIZE + MiscData.MAPAVIEW_Max_Y/2);

        camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mapaO = new SubMapaView(this.mapaModel, inicialX-MiscData.MAPAVIEW_Max_X,   inicialY);
        mapa = new SubMapaView(this.mapaModel,  inicialX,                           inicialY);
        mapaE = new SubMapaView(this.mapaModel, inicialX+MiscData.MAPAVIEW_Max_X,   inicialY);

        mapaNO = new SubMapaView(this.mapaModel,inicialX-MiscData.MAPAVIEW_Max_X,   inicialY+MiscData.MAPAVIEW_Max_Y);
        mapaN = new SubMapaView(this.mapaModel, inicialX,                           inicialY+MiscData.MAPAVIEW_Max_Y);
        mapaNE = new SubMapaView(this.mapaModel,inicialX+MiscData.MAPAVIEW_Max_X,   inicialY+MiscData.MAPAVIEW_Max_Y);

        mapaSO = new SubMapaView(this.mapaModel,inicialX-MiscData.MAPAVIEW_Max_X,   inicialY-MiscData.MAPAVIEW_Max_Y);
        mapaS = new SubMapaView(this.mapaModel, inicialX,                           inicialY-MiscData.MAPAVIEW_Max_Y);
        mapaSE = new SubMapaView(this.mapaModel,inicialX+MiscData.MAPAVIEW_Max_X,   inicialY-MiscData.MAPAVIEW_Max_Y);
    }

    public void setView (SubMapaView subMapaView)
    {
        camara.zoom = vista.camara.zoom;
        camara.position.x = vista.camara.position.x - subMapaView.getX() *MiscData.TILESIZE;
        camara.position.y = vista.camara.position.y - subMapaView.getY() *MiscData.TILESIZE;
        camara.update();
        subMapaView.setView(camara);
    }

    public void render()
    {
        x = vista.camara.position.x + 24; //TODO mitad ancho personaje
        y = vista.camara.position.y + 24; //TODO mitad alto personaje

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

        if (x > (subMapaView.getX() + 2*MiscData.MAPAVIEW_Max_X) * MiscData.TILESIZE )
        {
            int newOrigenX = subMapaView.getX() + MiscData.MAPAVIEW_Max_X*3;
            subMapaView.ajustarCoordenadas(newOrigenX, subMapaView.getY());
            subMapaView.crearTiledMap(newOrigenX, subMapaView.getY());
        }
        if (x < (subMapaView.getX() - MiscData.MAPAVIEW_Max_X) * MiscData.TILESIZE)
        {
            int newOrigenX = subMapaView.getX() - MiscData.MAPAVIEW_Max_X*3;
            subMapaView.ajustarCoordenadas(newOrigenX, subMapaView.getY());
            subMapaView.crearTiledMap(newOrigenX, subMapaView.getY());

        }
        if (y > (subMapaView.getY() + 2*MiscData.MAPAVIEW_Max_Y) * MiscData.TILESIZE )
        {
            int newOrigenY = subMapaView.getY() + MiscData.MAPAVIEW_Max_Y *3;
            subMapaView.ajustarCoordenadas(subMapaView.getX(), newOrigenY);
            subMapaView.crearTiledMap(subMapaView.getX(), newOrigenY);
        }
        if (y < (subMapaView.getY() - MiscData.MAPAVIEW_Max_Y) * MiscData.TILESIZE)
        {
            int newOrigenY = subMapaView.getY() - MiscData.MAPAVIEW_Max_Y *3;
            subMapaView.ajustarCoordenadas(subMapaView.getX(), newOrigenY);
            subMapaView.crearTiledMap(subMapaView.getX(), newOrigenY);
        }
    }
}
