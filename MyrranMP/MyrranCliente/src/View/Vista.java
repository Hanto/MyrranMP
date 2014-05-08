package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.GameState.Mundo;
import Model.Classes.GameState.UI;
import Model.Classes.Geo.Mapa;
import Model.Classes.Mobiles.PC;
import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.EntornoAcciones.BarraAcciones;
import Model.DTO.MundoDTO;
import Model.DTO.UIDTO;
import Recursos.DAO.RSC;
import View.Geo.MapaView;
import View.Graficos.Texto;
import View.Mobiles.PCView;
import View.Mobiles.PlayerView;
import View.UI.BarraAccionesView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Vista implements PropertyChangeListener
{
    public Controlador controlador;
    public Player player;
    public UI ui;
    public Mundo mundo;

    public Mapa mapa;

    public PlayerView playerView;
    public Array<PCView> listaPCViews = new Array<>();
    public Array<BarraAccionesView> listaBarraAccionesView = new Array<>();

    public DragAndDrop accionesDAD = new DragAndDrop();

    public MapaView mapaView;

    public Stage stageMundo;
    public Stage stageUI;
    public OrthographicCamera camara;
    public SpriteBatch batch;

    private Texto fps;
    private int nivelDeZoom = 0;

    private ShapeRenderer shape = new ShapeRenderer();

    public Vista (Controlador controlador, Player player, UI ui, Mundo mundo)
    {
        this.controlador = controlador;
        this.player = player;
        this.ui = ui;
        this.mundo = mundo;
        this.mapa = mundo.mapa;

        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        stageMundo = new Stage();
        stageUI = new Stage();
        playerView = new PlayerView(this.player, this, controlador);

        mapaView = new MapaView(mapa, playerView.getX(), playerView.getY(), MiscData.MAPAVIEW_TamañoX, MiscData.MAPAVIEW_TamañoY, this);


        controlador.addInputProcessor(stageUI);
        controlador.addInputProcessor(stageMundo);

        stageMundo.getViewport().setCamera(camara);
        mundo.añadirObservador(this);
        ui.entornoAcciones.añadirObservador(this);

        fps = new Texto("fps", RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                        Color.WHITE, Color.BLACK, 0, 0, Align.left, Align.bottom, 2);

        accionesDAD.setDragTime(0);
        stageUI.addActor(fps);
    }

    public void render (float delta)
    {
        Gdx.gl.glClearColor(0/2.55f, 0/2.55f, 0/2.55f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.actualizar(delta);

        camara.position.x = playerView.getCenterX();
        camara.position.y = playerView.getCenterY();
        camara.update();

        //stageMundo.ordenarPorProfundidad();
        batch.setProjectionMatrix(camara.combined);
        //rayHandler.setCombinedMatrix(camara.combined);

        mapaView.render();

        batch.begin();
        batch.end();

        stageMundo.act(delta);
        stageMundo.draw();

        dibujarVision();
        //rayHandler.updateAndRender();
        stageUI.act(delta);
        stageUI.draw();

        fps.setTexto(Integer.toString(Gdx.graphics.getFramesPerSecond())+"fps");


    }

    public void resize (int anchura, int altura)
    {
        camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stageMundo.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //stageUI.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void dibujarVision()
    {
        shape.setProjectionMatrix(camara.combined);
        shape.setColor(Color.RED);
        shape.begin(ShapeRenderer.ShapeType.Line);

        shape.rect( playerView.getCenterX()-MiscData.GDX_Window_Horizontal_Resolution/2-1,
                    playerView.getCenterY()-MiscData.GDX_Window_Vertical_Resolution/2-1,
                    MiscData.GDX_Window_Horizontal_Resolution+2,
                    MiscData.GDX_Window_Vertical_Resolution+2);

        shape.setColor(Color.YELLOW);
        shape.rect( playerView.getCenterX()-MiscData.GDX_Window_Horizontal_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs/2,
                    playerView.getCenterY()-MiscData.GDX_Window_Vertical_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs/2,
                    MiscData.GDX_Window_Horizontal_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs,
                    MiscData.GDX_Window_Vertical_Resolution*MiscData.SERVIDOR_DistanciaVisionMobs);

        shape.setColor(Color.GRAY);
        shape.rect( 0, 0, MiscData.MAPA_Max_TilesX*MiscData.TILESIZE,MiscData.MAPA_Max_TilesY*MiscData.TILESIZE);

        shape.end();
    }

    public void dispose ()
    {
        stageMundo.dispose();
        stageUI.dispose();
        batch.dispose();
        mapaView.dispose();
        shape.dispose();
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

    public Vector2 convertirCoordenadasPantallaAMundo (int screenX, int screenY)
    {
        Vector3 destino = new Vector3(screenX, screenY, 0);
        camara.unproject(destino);
        return new Vector2(destino.x, destino.y);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof MundoDTO.AñadirPC)
        {
            PC pc = ((MundoDTO.AñadirPC) evt.getNewValue()).pc;
            PCView pcView = new PCView(pc, this, controlador);
        }

        if (evt.getNewValue() instanceof UIDTO.AñadirBarraAccionesDTO)
        {
            BarraAcciones barraAcciones = ((UIDTO.AñadirBarraAccionesDTO) evt.getNewValue()).barraAcciones;
            BarraAccionesView barraAccionesView = new BarraAccionesView(barraAcciones, this, controlador);
        }
    }
}
