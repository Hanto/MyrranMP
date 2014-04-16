package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Model.DTO.MundoDTO;
import Model.Mobiles.MundoModel;
import Model.Mobiles.PCModel;
import View.Geo.MapaVista;
import View.Graficos.Texto;
import View.Mobiles.MobilesRecursos;
import View.Mobiles.PCView;
import View.Mobiles.PlayerView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Vista implements PropertyChangeListener
{
    public Controlador controlador;
    public MundoModel mundoModel;

    public PlayerView playerView;
    public Array<PCView> listaPCViews = new Array<>();

    public OrthographicCamera camara;
    public Stage stageMundo;
    public Stage stageUI;
    public SpriteBatch batch;

    public MapaVista mapaVista;

    private Texto fps;
    private int nivelDeZoom = 0;

    public Vista (Controlador controlador, MundoModel mundoModel)
    {
        this.controlador = controlador;
        this.mundoModel = mundoModel;

        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        stageMundo = new Stage();
        stageUI = new Stage();
        playerView = new PlayerView(mundoModel.player, this, controlador);

        mapaVista = new MapaVista(this);

        controlador.addInputProcessor(stageUI);
        controlador.addInputProcessor(stageMundo);

        stageMundo.getViewport().setCamera(camara);
        mundoModel.añadirObservador(this);

        fps = new Texto("fps", MobilesRecursos.get().font14, Color.WHITE, Color.BLACK, 0, 0, Align.left, Align.bottom, 2);
        stageUI.addActor(fps);
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mundoModel.getPlayer().actualizar(delta);

        camara.position.x = playerView.getX();
        camara.position.y = playerView.getY();
        camara.update();

        //stageMundo.ordenarPorProfundidad();
        batch.setProjectionMatrix(camara.combined);
        //rayHandler.setCombinedMatrix(camara.combined);

        mapaVista.render();

        batch.begin();
        batch.end();
        stageMundo.act(delta);
        stageMundo.draw();
        //rayHandler.updateAndRender();
        stageUI.act(delta);
        stageUI.draw();

        fps.setTexto(Integer.toString(Gdx.graphics.getFramesPerSecond())+"fps");
    }

    public void dispose ()
    {
        stageMundo.dispose();
        stageUI.dispose();
        batch.dispose();
        mapaVista.dispose();
    }

    public void aplicarZoom(int incrementoZoom)
    {
        nivelDeZoom += incrementoZoom;

        float zoom = 1f;
        if (nivelDeZoom < 0) zoom = 1f/(Math.abs(nivelDeZoom)+1f);
        if (nivelDeZoom ==0) zoom = 1f;
        if (nivelDeZoom > 0) zoom = 1f+ nivelDeZoom *0.2f;
        camara.zoom = zoom;
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof MundoDTO.AñadirPC)
        {
            PCModel pcModel = ((MundoDTO.AñadirPC) evt.getNewValue()).pcModel;
            PCView pcView = new PCView(pcModel, this, controlador);
        }
    }
}
