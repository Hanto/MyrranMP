package View;// Created by Hanto on 08/04/2014.

import Controller.Input.ControladorCliente;
import Modelo.Mobiles.DTO;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PCModel;
import Modelo.Mobiles.PlayerModel;
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
    public MundoModel mundo;

    public PlayerView playerView;
    public Array<PCView> listaPCViews = new Array<>();

    public Stage stageMundo;
    public Stage stageUI;
    public SpriteBatch batch;
    public OrthographicCamera camara;


    public Vista (ControladorCliente controlador, MundoModel mundo)
    {
        this.controlador = controlador;
        this.mundo = mundo;

        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        stageMundo = new Stage();
        stageUI = new Stage();

        stageMundo.getViewport().setCamera(camara);
        mundo.añadirObservador(this);
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        if (evt.getNewValue() instanceof  DTO.MundoAñadirPlayer)
        {
            PlayerModel player = ((DTO.MundoAñadirPlayer) evt.getNewValue()).player;
            playerView = new PlayerView(player, this, controlador);
        }

        if (evt.getNewValue() instanceof DTO.MundoAñadirPC)
        {
            PCModel pc = ((DTO.MundoAñadirPC) evt.getNewValue()).pc;
            PCView pcView = new PCView(pc, this, controlador);
        }
    }
}
