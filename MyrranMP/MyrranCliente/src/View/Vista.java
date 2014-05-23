package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.GameState.Mundo;
import Model.GameState.UI;
import View.GameState.MundoView;
import View.GameState.UIView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Vista
{
    public Controlador controlador;

    public Mundo mundo;
    public UI ui;

    private MundoView mundoView;
    private UIView uiView;

    private SpriteBatch batch;
    private ShapeRenderer shape = new ShapeRenderer();

    public UIView getUiView()       { return uiView; }
    public MundoView getMundoView() { return mundoView; }

    public Vista (Controlador controlador, UI ui, Mundo mundo)
    {
        this.controlador = controlador;
        this.ui = ui;
        this.mundo = mundo;

        batch = new SpriteBatch();

        mundoView = new MundoView(controlador, mundo.getPlayer(), mundo);
        uiView = new UIView(controlador, ui);
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mundo.getPlayer().actualizar(delta);

        mundoView.camara.position.x = mundoView.getPlayerView().getCenterX();
        mundoView.camara.position.y = mundoView.getPlayerView().getCenterY();
        mundoView.camara.update();

        batch.setProjectionMatrix(mundoView.camara.combined);
        //rayHandler.setCombinedMatrix(camara.combined);

        mundoView.getMapaView().render();

        batch.begin();
        batch.end();

        mundoView.act(delta);
        mundoView.draw();

        dibujarVision();
        //rayHandler.updateAndRender();
        uiView.act(delta);
        uiView.draw();

        uiView.setTextoFPS(Integer.toString(Gdx.graphics.getFramesPerSecond()) + "fps");
    }

    public void resize (int anchura, int altura)
    {
        mundoView.camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mundoView.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiView.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void dibujarVision()
    {
        shape.setProjectionMatrix(mundoView.camara.combined);
        shape.begin(ShapeRenderer.ShapeType.Line);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //Dibujar Resolucion
        shape.setColor(new Color(1,1,1, 0.5f));
        shape.rect( (int)(mundoView.getPlayerView().getCenterX()-MiscData.GDX_Horizontal_Resolution /2-1),
                    (int)(mundoView.getPlayerView().getCenterY()-MiscData.GDX_Vertical_Resolution /2-1),
                    MiscData.GDX_Horizontal_Resolution +2,
                    MiscData.GDX_Vertical_Resolution +2);

        //Dibujar alcance Vista Virtual:
        shape.setColor(Color.RED);
        shape.rect( (int)(mundoView.getPlayerView().getCenterX()-MiscData.MAPTILE_Horizontal_Resolution /2-1),
                    (int)(mundoView.getPlayerView().getCenterY()-MiscData.MAPTILE_Vertical_Resolution /2-1),
                    MiscData.MAPTILE_Horizontal_Resolution +2,
                    MiscData.MAPTILE_Vertical_Resolution +2);


        //Dibujar alcance Mobs:
        shape.setColor(Color.GREEN);
        shape.rect( (int)(mundoView.getPlayerView().getCenterX()-MiscData.MAPTILE_Horizontal_Resolution *MiscData.SERVIDOR_DistanciaVisionMobs/2),
                    (int)(mundoView.getPlayerView().getCenterY()-MiscData.MAPTILE_Vertical_Resolution *MiscData.SERVIDOR_DistanciaVisionMobs/2),
                    MiscData.MAPTILE_Horizontal_Resolution *MiscData.SERVIDOR_DistanciaVisionMobs,
                    MiscData.MAPTILE_Vertical_Resolution *MiscData.SERVIDOR_DistanciaVisionMobs);

        //Dibujar Limites Mapa:
        shape.setColor(Color.WHITE);
        shape.rect( 0, 0, MiscData.MAPA_Max_TilesX*MiscData.TILESIZE,MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);

        //Dibujar limites Celda:
        shape.setColor(Color.GRAY);
        for (int i=1; i<=MiscData.MAPA_Max_TilesX/MiscData.MAPTILE_NumTilesX; i++)
        {
            shape.line(i*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE, 0,
                       i*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE, MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);
        }
        for (int i=1; i<=MiscData.MAPA_Max_TilesY/MiscData.MAPTILE_NumTilesY; i++)
        {
            shape.line(0, i*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE,
                       MiscData.MAPA_Max_TilesX*MiscData.TILESIZE, i*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE);
        }

        //Dibujar Subsectores Celda:
        shape.setColor(Color.DARK_GRAY);
        for (int i=0; i<=MiscData.MAPA_Max_TilesX/MiscData.MAPTILE_NumTilesX; i++)
        {
            shape.line(i*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE+MiscData.MAPTILE_posHorNeg, 0,
                       i*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE+MiscData.MAPTILE_posHorNeg, MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);
            shape.line(i*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE+MiscData.MAPTILE_posHorPos, 0,
                       i*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE+MiscData.MAPTILE_posHorPos, MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);
        }

        for (int i=0; i<=MiscData.MAPA_Max_TilesY/MiscData.MAPTILE_NumTilesY; i++)
        {
            shape.line(0, MiscData.MAPTILE_posVerNeg+i*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE,
                       MiscData.MAPA_Max_TilesX*MiscData.TILESIZE,  MiscData.MAPTILE_posVerNeg+i*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE);
            shape.line(0, MiscData.MAPTILE_posVerPos+i*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE,
                       MiscData.MAPA_Max_TilesX*MiscData.TILESIZE,  MiscData.MAPTILE_posVerPos+i*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE);

        }

        //Dibuar MapTiles Adyacentes Cargados:
        for (int y=0; y<3; y++)
        {
            for (int x=0; x<3; x++)
            {
                if (mundo.mapTilesCargados[x][y] == true)
                {
                    int mapTileX = x-1 + mundo.getMapa().mapTileCentroX;
                    int mapTileY = 1-y + mundo.getMapa().mapTileCentroY;

                    shape.setColor(Color.ORANGE);
                    shape.rect( mapTileX*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE, mapTileY*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE,
                                MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE,MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE);
                }
            }
        }


        shape.end();
    }

    public void dispose ()
    {
        mundoView.dispose();
        uiView.dispose();
        batch.dispose();
        mundoView.getMapaView().dispose();
        shape.dispose();
    }

    public void aplicarZoom(int incrementoZoom)
    {   mundoView.aplicarZoom(incrementoZoom); }
}
