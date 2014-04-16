package View.Geo;// Created by Hanto on 16/04/2014.

import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zMain.MiscData;

public class MapaVista
{
    private Vista vista;

    private OrthographicCamera camara;

    private SubMapaVista mapa;
    private SubMapaVista mapaE;
    private SubMapaVista mapaO;
    private SubMapaVista mapaNO;
    private SubMapaVista mapaN;
    private SubMapaVista mapaNE;
    private SubMapaVista mapaSO;
    private SubMapaVista mapaS;
    private SubMapaVista mapaSE;

    private float x;
    private float y;

    public MapaVista(Vista vista)
    {
        this.vista = vista;

        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mapa = new SubMapaVista(vista,0,0);
        mapaE = new SubMapaVista(vista, MiscData.MAPAVIEW_Max_X,0);
        mapaO = new SubMapaVista(vista, -MiscData.MAPAVIEW_Max_X, 0);

        mapaNO = new SubMapaVista(vista,-MiscData.MAPAVIEW_Max_X, MiscData.MAPAVIEW_MAX_Y);
        mapaN = new SubMapaVista(vista, 0,MiscData.MAPAVIEW_MAX_Y);
        mapaNE = new SubMapaVista(vista, MiscData.MAPAVIEW_Max_X, MiscData.MAPAVIEW_MAX_Y);

        mapaSO = new SubMapaVista(vista,-MiscData.MAPAVIEW_Max_X, -MiscData.MAPAVIEW_MAX_Y);
        mapaS = new SubMapaVista(vista, 0,-MiscData.MAPAVIEW_MAX_Y);
        mapaSE = new SubMapaVista(vista, MiscData.MAPAVIEW_Max_X, -MiscData.MAPAVIEW_MAX_Y);
    }

    public void setView (SubMapaVista mapavista)
    {
        camara.position.x = vista.camara.position.x - mapavista.getX() *MiscData.TILESIZE;
        camara.position.y = vista.camara.position.y - mapavista.getY() *MiscData.TILESIZE;
        camara.update();
        mapavista.setView(camara);
    }

    public void render()
    {
        x = vista.camara.position.x;
        y = vista.camara.position.y;

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

    public void mapaVistaLoader(SubMapaVista subMapaVista)
    {

        if (x > (subMapaVista.getX() + 2*MiscData.MAPAVIEW_Max_X) * MiscData.TILESIZE )
        {
            int newOrigenX = subMapaVista.getX() + MiscData.MAPAVIEW_Max_X*3;
            subMapaVista.ajustarCoordenadas(newOrigenX, subMapaVista.getY());
            subMapaVista.crearTiledMap(newOrigenX, subMapaVista.getY());
        }
        if (x < (subMapaVista.getX() - MiscData.MAPAVIEW_Max_X) * MiscData.TILESIZE)
        {
            int newOrigenX = subMapaVista.getX() - MiscData.MAPAVIEW_Max_X*3;
            subMapaVista.ajustarCoordenadas(newOrigenX, subMapaVista.getY());
            subMapaVista.crearTiledMap(newOrigenX, subMapaVista.getY());

        }
        if (y > (subMapaVista.getY() + 2*MiscData.MAPAVIEW_MAX_Y) * MiscData.TILESIZE )
        {
            int newOrigenY = subMapaVista.getY() + MiscData.MAPAVIEW_MAX_Y*3;
            subMapaVista.ajustarCoordenadas(subMapaVista.getX(), newOrigenY);
            subMapaVista.crearTiledMap(subMapaVista.getX(), newOrigenY);
        }
        if (y < (subMapaVista.getY() - MiscData.MAPAVIEW_MAX_Y) * MiscData.TILESIZE)
        {
            int newOrigenY = subMapaVista.getY() - MiscData.MAPAVIEW_MAX_Y*3;
            subMapaVista.ajustarCoordenadas(subMapaVista.getX(), newOrigenY);
            subMapaVista.crearTiledMap(subMapaVista.getX(), newOrigenY);
        }
    }
}
