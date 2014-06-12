package View.GameState;// Created by Hanto on 14/05/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Data.MiscData;
import Model.Classes.Mobiles.PC;
import Model.Classes.Mobiles.Player;
import Model.GameState.Mundo;
import View.Classes.Geo.MapaView;
import View.Classes.Mobiles.PCView;
import View.Classes.Mobiles.PlayerView;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MundoView extends Stage implements PropertyChangeListener
{
    protected Controlador controlador;
    protected Mundo mundo;

    protected PlayerView playerView;
    protected MapaView mapaView;
    protected Array<PCView> listaPCViews = new Array<>();

    protected RayHandler rayHandler;
    protected ShapeRenderer shape = new ShapeRenderer();
    protected int nivelDeZoom = 0;
    protected OrthographicCamera camara;

    public PlayerView getPlayerView()                   { return playerView; }
    public MapaView getMapaView()                       { return mapaView; }
    public OrthographicCamera getCamara()               { return camara; }
    public RayHandler getRayHandler()                   { return rayHandler; }

    public void eliminarPCView (PCView pcView)
    {   listaPCViews.removeValue(pcView, true); }

    public MundoView(Controlador controlador, Player player, Mundo mundo)
    {
        this.controlador = controlador;
        this.mundo = mundo;

        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(mundo.getWorld());
        rayHandler.setAmbientLight(0.4f, 0.4f, 0.4f, 1.0f);
        mapaView = new MapaView(mundo.getMapa(), this, player.getX(), player.getY(), MiscData.MAPAVIEW_TamañoX, MiscData.MAPAVIEW_TamañoY);
        playerView = new PlayerView(player, this, controlador);
        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getViewport().setCamera(camara);

        controlador.addInputProcessor(this);
        mundo.añadirObservador(this);
    }

    @Override public void draw ()
    {
        //dibujamos el fondo:
        mapaView.render();

        //dibujamos los sprites:
        super.draw();

        //aplicamos las luces:
        rayHandler.setCombinedMatrix(camara.combined);
        rayHandler.updateAndRender();

        //dibujamos las lineas de debug:
        dibujarVision();
    }

    @Override public void dispose ()
    {
        super.dispose();
        mapaView.dispose();
        rayHandler.dispose();
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

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof NetDTO.AñadirPC)
        {
            PC pc = mundo.getPC(((NetDTO.AñadirPC) evt.getNewValue()).connectionID);

            pc.eliminarObservador(this);
            PCView pcView = new PCView(pc, this, controlador);
            listaPCViews.add(pcView);
        }
    }


    public void dibujarVision()
    {
        shape.setProjectionMatrix(camara.combined);
        shape.begin(ShapeRenderer.ShapeType.Line);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //Dibujar Resolucion
        shape.setColor(new Color(1,1,1, 0.5f));
        shape.rect((int)(getPlayerView().getCenterX()-MiscData.GDX_Horizontal_Resolution /2-1),
                   (int)(getPlayerView().getCenterY()-MiscData.GDX_Vertical_Resolution /2-1),
                   MiscData.GDX_Horizontal_Resolution +2,
                   MiscData.GDX_Vertical_Resolution +2);

        //Dibujar alcance Vista Virtual:
        shape.setColor(Color.RED);
        shape.rect((int)(getPlayerView().getCenterX()-MiscData.MAPTILE_Horizontal_Resolution /2-1),
                   (int)(getPlayerView().getCenterY()-MiscData.MAPTILE_Vertical_Resolution /2-1),
                   MiscData.MAPTILE_Horizontal_Resolution +2,
                   MiscData.MAPTILE_Vertical_Resolution +2);


        //Dibujar alcance Mobs:
        shape.setColor(Color.GREEN);
        shape.rect((int)(getPlayerView().getCenterX()-MiscData.MAPTILE_Horizontal_Resolution *MiscData.SERVIDOR_DistanciaVisionMobs/2),
                   (int)(getPlayerView().getCenterY()-MiscData.MAPTILE_Vertical_Resolution *MiscData.SERVIDOR_DistanciaVisionMobs/2),
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
        shape.setColor(Color.ORANGE);
        for (int y=0; y<3; y++)
        {
            for (int x=0; x<3; x++)
            {
                if (mundo.mapTilesCargados[x][y] == true)
                {
                    int mapTileX = x-1 + mundo.getMapa().mapTileCentroX;
                    int mapTileY = 1-y + mundo.getMapa().mapTileCentroY;

                    shape.rect( mapTileX*MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE, mapTileY*MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE,
                            MiscData.MAPTILE_NumTilesX *MiscData.TILESIZE,MiscData.MAPTILE_NumTilesY *MiscData.TILESIZE);
                }
            }
        }
        shape.end();
    }
}
