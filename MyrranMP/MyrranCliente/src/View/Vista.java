package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Model.GameState.Mundo;
import Model.GameState.UI;
import View.GameState.MundoView;
import View.GameState.UIView;
import com.badlogic.gdx.Gdx;
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

        mundoView.dibujarVision(shape);
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
