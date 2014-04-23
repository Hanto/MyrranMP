package View.Geo;// Created by Ladrim on 22/04/2014.

import Data.MiscData;
import Model.Geo.Mapa;
import View.Vista;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ParaMapaView
{
    private Vista vista;
    private Mapa mapaModel;

    private OrthographicCamera camara;
    private int mapTileActualX;
    private int mapTileActualY;

    private SubMapaView[][] superMapa;
    private int tamaño;

    int numTilesX;
    int numTilesY;

    public ParaMapaView(Mapa mapaModel, float posInicialX, float posInicialY, Vista vista, int size)
    {
        this.mapaModel = mapaModel;
        this.vista = vista;
        this.camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.tamaño = (int)(3*Math.pow(2, size-1));

        superMapa = new SubMapaView[this.tamaño][this.tamaño];
        for (int i=0; i<tamaño; i++)
        {   for (int j=0; j<tamaño; j++)
            {   superMapa[i][j] = new SubMapaView(this.mapaModel); }

        }
        setPosition(posInicialX, posInicialY);

        for (int i=0; i<tamaño; i++)
        {   for (int j=0; j<tamaño; j++)
            {   System.out.print("["+superMapa[j][i].getMapTileX()+" "+superMapa[j][i].getMapTileY()+"] "); }
            System.out.println(" ");
        }
    }

    public void setPosition(float nuevaPosicionX, float nuevaPosicionY)
    {
        numTilesX = Math.round(MiscData.GDX_Window_Horizontal_Resolution/MiscData.TILESIZE/tamaño);
        numTilesY = Math.round(MiscData.GDX_Window_Vertical_Resolution/MiscData.TILESIZE/tamaño);

        int mapTileX = (int)(nuevaPosicionX / (numTilesX*MiscData.TILESIZE));
        int mapTileY = (int)(nuevaPosicionY / (numTilesY*MiscData.TILESIZE));

        int xMenor,xMayor, yMayor, yMenor;

        if (tamaño >1)
        {
            xMenor = -tamaño / 2;
            xMayor =  tamaño / 2 -1;
            yMayor =  tamaño / 2 -1;
            yMenor =  -tamaño / 2;
        }
        else { xMenor = -1; xMayor = 1; yMayor = 1; yMenor = 1;}

        int x = 0; int y = 0;

        for (int j=yMayor; j>=yMenor;j--)
        {   for (int i=xMenor; i<=xMayor; i++)
            {
                superMapa[x][y].crearTiledMap(i+mapTileX,j+mapTileY);
                System.out.println("["+i+" "+j+"] ");
                System.out.println(x+"-"+y);
                if (x<tamaño-1) { x++; }
                else x = 0;
            }
            if (y < tamaño-1) { y++; }
            else y = 0;
        }
    }

    public void setView (SubMapaView subMapaView)
    {
        camara.zoom = vista.camara.zoom;
        camara.position.x = vista.camara.position.x - subMapaView.getMapTileX() *numTilesX *MiscData.TILESIZE;
        camara.position.y = vista.camara.position.y - subMapaView.getMapTileY() *numTilesY *MiscData.TILESIZE;
        camara.update();
        subMapaView.setView(camara);
    }

    public void render()
    {

        mapTileActualX = (int)((vista.camara.position.x + 24) / (numTilesX *MiscData.TILESIZE));
        mapTileActualY = (int)((vista.camara.position.y + 24) / (numTilesY *MiscData.TILESIZE));

        for (int i=0; i<tamaño; i++)
        {   for (int j=0; j<tamaño; j++)
            {
                mapaVistaLoader(superMapa[j][i]);
                setView(superMapa[j][i]);
                superMapa[j][i].render();
            }
        }

    }

    public void mapaVistaLoader(SubMapaView subMapaView)
    {
        if (mapTileActualX > (subMapaView.getMapTileX() + tamaño/2))
        {   subMapaView.crearTiledMap(mapTileActualX + tamaño/2-1,    subMapaView.getMapTileY()    ); }

        if (mapTileActualX <= (subMapaView.getMapTileX() - (tamaño/2)))
        {   subMapaView.crearTiledMap(mapTileActualX - tamaño/2,    subMapaView.getMapTileY()    ); }

        if (mapTileActualY > (subMapaView.getMapTileY() + tamaño/2 ))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()    ,    mapTileActualY + tamaño/2-1); }

        if (mapTileActualY <= (subMapaView.getMapTileY() - (tamaño/2)))
        {   subMapaView.crearTiledMap(subMapaView.getMapTileX()    ,    mapTileActualY - tamaño/2); }
    }
}
