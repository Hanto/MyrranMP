package View;// Created by Hanto on 16/04/2014.

import View.Geo.MapaVista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import zMain.MiscData;

public class Fondo
{
    private Vista vista;

    private OrthographicCamera camara;

    private MapaVista mapaVista;
    private MapaVista mapaVistaE;
    private MapaVista mapaVistaO;
    private MapaVista mapaVistaNO;
    private MapaVista mapaVistaN;
    private MapaVista mapaVistaNE;
    private MapaVista mapaVistaSO;
    private MapaVista mapaVistaS;
    private MapaVista mapaVistaSE;

    private float x;
    private float y;

    public Fondo(Vista vista)
    {
        this.vista = vista;

        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mapaVista = new MapaVista(vista,0,0);
        mapaVistaE = new MapaVista(vista, MiscData.MAPAVIEW_Max_X,0);
        mapaVistaO = new MapaVista(vista, -MiscData.MAPAVIEW_Max_X, 0);

        mapaVistaNO = new MapaVista(vista,-MiscData.MAPAVIEW_Max_X, MiscData.MAPAVIEW_MAX_Y);
        mapaVistaN = new MapaVista(vista, 0,MiscData.MAPAVIEW_MAX_Y);
        mapaVistaNE = new MapaVista(vista, MiscData.MAPAVIEW_Max_X, MiscData.MAPAVIEW_MAX_Y);

        mapaVistaSO = new MapaVista(vista,-MiscData.MAPAVIEW_Max_X, -MiscData.MAPAVIEW_MAX_Y);
        mapaVistaS = new MapaVista(vista, 0,-MiscData.MAPAVIEW_MAX_Y);
        mapaVistaSE = new MapaVista(vista, MiscData.MAPAVIEW_Max_X, -MiscData.MAPAVIEW_MAX_Y);
    }

    public void setView (MapaVista mapavista)
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

        mapaVistaLoader(mapaVista);
        mapaVistaLoader(mapaVistaE);
        mapaVistaLoader(mapaVistaO);
        mapaVistaLoader(mapaVistaNO);
        mapaVistaLoader(mapaVistaN);
        mapaVistaLoader(mapaVistaNE);
        mapaVistaLoader(mapaVistaSO);
        mapaVistaLoader(mapaVistaS);
        mapaVistaLoader(mapaVistaSE);

        setView(mapaVista);
        setView(mapaVistaE);
        setView(mapaVistaO);
        setView(mapaVistaNO);
        setView(mapaVistaN);
        setView(mapaVistaNE);
        setView(mapaVistaSO);
        setView(mapaVistaS);
        setView(mapaVistaSE);

        mapaVista.render();
        mapaVistaO.render();
        mapaVistaE.render();
        mapaVistaNO.render();
        mapaVistaN.render();
        mapaVistaNE.render();
        mapaVistaSO.render();
        mapaVistaS.render();
        mapaVistaSE.render();
    }

    public void dispose()
    {
        mapaVista.dispose();
        mapaVistaE.dispose();
        mapaVistaO.dispose();
        mapaVistaNO.dispose();
        mapaVistaN.dispose();
        mapaVistaNE.dispose();
        mapaVistaSO.dispose();
        mapaVistaS.dispose();
        mapaVistaSE.dispose();
    }

    public void mapaVistaLoader(MapaVista mapaVista)
    {

        if (x > (mapaVista.getX() + 2*MiscData.MAPAVIEW_Max_X) * MiscData.TILESIZE )
        {
            int newOrigenX = mapaVista.getX() + MiscData.MAPAVIEW_Max_X*3;
            mapaVista.ajustarCoordenadas(newOrigenX, mapaVista.getY());
            mapaVista.crearTiledMap(newOrigenX, mapaVista.getY());
        }
        if (x < (mapaVista.getX() - MiscData.MAPAVIEW_Max_X) * MiscData.TILESIZE)
        {
            int newOrigenX = mapaVista.getX() - MiscData.MAPAVIEW_Max_X*3;
            mapaVista.ajustarCoordenadas(newOrigenX, mapaVista.getY());
            mapaVista.crearTiledMap(newOrigenX, mapaVista.getY());

        }
        if (y > (mapaVista.getY() + 2*MiscData.MAPAVIEW_MAX_Y) * MiscData.TILESIZE )
        {
            int newOrigenY = mapaVista.getY() + MiscData.MAPAVIEW_MAX_Y*3;
            mapaVista.ajustarCoordenadas(mapaVista.getX(), newOrigenY);
            mapaVista.crearTiledMap(mapaVista.getX(), newOrigenY);
        }
        if (y < (mapaVista.getY() - MiscData.MAPAVIEW_MAX_Y) * MiscData.TILESIZE)
        {
            int newOrigenY = mapaVista.getY() - MiscData.MAPAVIEW_MAX_Y*3;
            mapaVista.ajustarCoordenadas(mapaVista.getX(), newOrigenY);
            mapaVista.crearTiledMap(mapaVista.getX(), newOrigenY);
        }
    }
}
