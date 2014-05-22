package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.Mobiles.Player;
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
    public Player player;
    public UI ui;
    public Mundo mundo;
    //public Mapa mapa;

    private MundoView mundoView;
    private UIView uiView;

    private SpriteBatch batch;
    private ShapeRenderer shape = new ShapeRenderer();

    public UIView getUiView()       { return uiView; }
    public MundoView getMundoView() { return mundoView; }

    public Vista (Controlador controlador, Player player, UI ui, Mundo mundo)
    {
        this.controlador = controlador;
        this.player = player;
        this.ui = ui;
        this.mundo = mundo;
        //this.mapa = mundo.mapa;

        batch = new SpriteBatch();

        mundoView = new MundoView(controlador, player, mundo);
        uiView = new UIView(controlador, ui);
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.actualizar(delta);

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
        shape.setColor(Color.RED);
        shape.begin(ShapeRenderer.ShapeType.Line);

        shape.rect( mundoView.getPlayerView().getCenterX()-MiscData.GDX_Window_Horizontal_Resolution/2-1,
                    mundoView.getPlayerView().getCenterY()-MiscData.GDX_Window_Vertical_Resolution/2-1,
                    MiscData.GDX_Window_Horizontal_Resolution+2,
                    MiscData.GDX_Window_Vertical_Resolution+2);

        shape.setColor(Color.YELLOW);
        shape.rect( mundoView.getPlayerView().getCenterX()-MiscData.GDX_Window_Horizontal_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs/2,
                    mundoView.getPlayerView().getCenterY()-MiscData.GDX_Window_Vertical_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs/2,
                    MiscData.GDX_Window_Horizontal_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs,
                    MiscData.GDX_Window_Vertical_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs);

        shape.setColor(Color.GRAY);
        shape.rect( 0, 0, MiscData.MAPA_Max_TilesX*MiscData.TILESIZE,MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);

        shape.setColor(Color.CYAN);
        for (int i=1; i<=MiscData.MAPA_Max_TilesX/MiscData.MAPAMODEL_NumTilesX; i++)
        {   shape.line(i*MiscData.MAPAMODEL_NumTilesX*MiscData.TILESIZE, 0, i*MiscData.MAPAMODEL_NumTilesX*MiscData.TILESIZE, MiscData.MAPA_Max_TilesY*MiscData.TILESIZE); }
        for (int i=1; i<=MiscData.MAPA_Max_TilesY/MiscData.MAPAMODEL_NumTilesY; i++)
        {   shape.line(0, i*MiscData.MAPAMODEL_NumTilesY*MiscData.TILESIZE, MiscData.MAPA_Max_TilesX*MiscData.TILESIZE, i*MiscData.MAPAMODEL_NumTilesY*MiscData.TILESIZE); }


        shape.setColor(Color.DARK_GRAY);
        for (int i=0; i<=MiscData.MAPA_Max_TilesX/MiscData.MAPAMODEL_NumTilesX; i++)
        {
            shape.line(i*MiscData.MAPAMODEL_NumTilesX*MiscData.TILESIZE+400, 0, i*MiscData.MAPAMODEL_NumTilesX*MiscData.TILESIZE+400, MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);
            shape.line(i*MiscData.MAPAMODEL_NumTilesX*MiscData.TILESIZE+1600-400, 0, i*MiscData.MAPAMODEL_NumTilesX*MiscData.TILESIZE+1600-400, MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);
        }

        for (int i=0; i<=MiscData.MAPA_Max_TilesY/MiscData.MAPAMODEL_NumTilesY; i++)
        {
            shape.line(0, 225+i*MiscData.MAPAMODEL_NumTilesY*MiscData.TILESIZE, MiscData.MAPA_Max_TilesX*MiscData.TILESIZE, 225+i*MiscData.MAPAMODEL_NumTilesY*MiscData.TILESIZE);
            shape.line(0, 900-225+i*MiscData.MAPAMODEL_NumTilesY*MiscData.TILESIZE, MiscData.MAPA_Max_TilesX*MiscData.TILESIZE, 900-225+i*MiscData.MAPAMODEL_NumTilesY*MiscData.TILESIZE);

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
