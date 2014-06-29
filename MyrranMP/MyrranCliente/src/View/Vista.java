package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import DB.RSC;
import Model.GameState.Mundo;
import Model.GameState.UI;
import View.GameState.MundoView;
import View.GameState.UIView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Vista
{
    public Controlador controlador;

    public Mundo mundo;
    public UI ui;

    private MundoView mundoView;
    private UIView uiView;

    private SpriteBatch batch;

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

        mundo.act(delta);

        mundoView.getCamara().position.x = mundoView.getPlayerView().getCenterX();
        mundoView.getCamara().position.y = mundoView.getPlayerView().getCenterY();
        mundoView.getCamara().update();

        batch.setProjectionMatrix(mundoView.getCamara().combined);

        batch.begin();
        batch.end();

        mundoView.act(delta);
        mundoView.draw();

        uiView.act(delta);
        uiView.draw();

        uiView.setTextoFPS(Integer.toString(Gdx.graphics.getFramesPerSecond()) + "fps");

        Table.drawDebug(uiView);
    }

    public void resize (int anchura, int altura)
    {
        mundoView.getCamara().setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mundoView.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiView.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void dispose ()
    {
        mundoView.dispose();
        uiView.dispose();
        batch.dispose();
        RSC.atlasRecursosDAO.getAtlasRecursosDAO().dispose();
        RSC.fuenteRecursosDAO.getFuentesRecursosDAO().dispose();
    }

    public void aplicarZoom(int incrementoZoom)
    {   mundoView.aplicarZoom(incrementoZoom); }
}
