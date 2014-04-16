package View.Geo;// Created by Hanto on 16/04/2014.

import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zMain.MiscData;

public class MapaVistas
{
    private Vista vista;

    private OrthographicCamera camara;

    private SubMapaVista subMapaVista;
    private SubMapaVista subMapaVistaE;
    private SubMapaVista subMapaVistaO;
    private SubMapaVista subMapaVistaNO;
    private SubMapaVista subMapaVistaN;
    private SubMapaVista subMapaVistaNE;
    private SubMapaVista subMapaVistaSO;
    private SubMapaVista subMapaVistaS;
    private SubMapaVista subMapaVistaSE;

    private float x;
    private float y;

    public MapaVistas(Vista vista)
    {
        this.vista = vista;

        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        subMapaVista = new SubMapaVista(vista,0,0);
        subMapaVistaE = new SubMapaVista(vista, MiscData.MAPAVIEW_Max_X,0);
        subMapaVistaO = new SubMapaVista(vista, -MiscData.MAPAVIEW_Max_X, 0);

        subMapaVistaNO = new SubMapaVista(vista,-MiscData.MAPAVIEW_Max_X, MiscData.MAPAVIEW_MAX_Y);
        subMapaVistaN = new SubMapaVista(vista, 0,MiscData.MAPAVIEW_MAX_Y);
        subMapaVistaNE = new SubMapaVista(vista, MiscData.MAPAVIEW_Max_X, MiscData.MAPAVIEW_MAX_Y);

        subMapaVistaSO = new SubMapaVista(vista,-MiscData.MAPAVIEW_Max_X, -MiscData.MAPAVIEW_MAX_Y);
        subMapaVistaS = new SubMapaVista(vista, 0,-MiscData.MAPAVIEW_MAX_Y);
        subMapaVistaSE = new SubMapaVista(vista, MiscData.MAPAVIEW_Max_X, -MiscData.MAPAVIEW_MAX_Y);
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

        mapaVistaLoader(subMapaVista);
        mapaVistaLoader(subMapaVistaE);
        mapaVistaLoader(subMapaVistaO);
        mapaVistaLoader(subMapaVistaNO);
        mapaVistaLoader(subMapaVistaN);
        mapaVistaLoader(subMapaVistaNE);
        mapaVistaLoader(subMapaVistaSO);
        mapaVistaLoader(subMapaVistaS);
        mapaVistaLoader(subMapaVistaSE);

        setView(subMapaVista);
        setView(subMapaVistaE);
        setView(subMapaVistaO);
        setView(subMapaVistaNO);
        setView(subMapaVistaN);
        setView(subMapaVistaNE);
        setView(subMapaVistaSO);
        setView(subMapaVistaS);
        setView(subMapaVistaSE);

        subMapaVista.render();
        subMapaVistaO.render();
        subMapaVistaE.render();
        subMapaVistaNO.render();
        subMapaVistaN.render();
        subMapaVistaNE.render();
        subMapaVistaSO.render();
        subMapaVistaS.render();
        subMapaVistaSE.render();
    }

    public void dispose()
    {
        subMapaVista.dispose();
        subMapaVistaE.dispose();
        subMapaVistaO.dispose();
        subMapaVistaNO.dispose();
        subMapaVistaN.dispose();
        subMapaVistaNE.dispose();
        subMapaVistaSO.dispose();
        subMapaVistaS.dispose();
        subMapaVistaSE.dispose();
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
