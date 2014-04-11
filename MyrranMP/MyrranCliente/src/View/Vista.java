package View;// Created by Hanto on 08/04/2014.

import Controller.ControladorCliente;
import Modelo.Mobiles.*;
import View.Actores.PCView;
import View.Actores.PlayerView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Vista implements PropertyChangeListener
{
    public ControladorCliente controlador;
    public MundoModelC mundo;

    public PlayerView playerView;
    public Array<PCView> listaPCViews = new Array<>();

    public Stage stageMundo;
    public Stage stageUI;
    public SpriteBatch batch;
    public OrthographicCamera camara;


    public Vista (ControladorCliente controlador, MundoModelC mundo)
    {
        this.controlador = controlador;
        this.mundo = mundo;

        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        stageMundo = new Stage();
        stageUI = new Stage();

        controlador.addInputProcessor(stageUI);
        controlador.addInputProcessor(stageMundo);

        stageMundo.getViewport().setCamera(camara);
        mundo.añadirObservador(this);
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mundo.getPlayer().actualizar(delta);

        //camara.position.x = player.getX();
        //camara.position.y = player.getY();
        camara.update();

        //stageMundo.ordenarPorProfundidad();

        batch.setProjectionMatrix(camara.combined);
        //rayHandler.setCombinedMatrix(camara.combined);
        //mapRenderer.setView(camara);

        //mapRenderer.render();
        batch.begin();
        batch.end();
        stageMundo.act(delta);
        stageMundo.draw();
        //rayHandler.updateAndRender();
        stageUI.act(delta);
        stageUI.draw();
    }

    public void dispose ()
    {
        stageMundo.dispose();
        stageUI.dispose();
        batch.dispose();
    }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof  PlayerDTO.MundoAñadirPlayer)
        {
            PlayerModel player = ((PlayerDTO.MundoAñadirPlayer) evt.getNewValue()).player;
            playerView = new PlayerView(player, this, controlador);
        }

        if (evt.getNewValue() instanceof DTO.MundoAñadirPC)
        {
            PCModel pc = ((DTO.MundoAñadirPC) evt.getNewValue()).pc;
            PCView pcView = new PCView(pc, this, controlador);
        }
    }
}
