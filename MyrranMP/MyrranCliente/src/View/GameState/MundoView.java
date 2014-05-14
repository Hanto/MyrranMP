package View.GameState;// Created by Hanto on 14/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.Mobiles.PC;
import Model.Classes.Mobiles.Player;
import Model.DTO.MundoDTO;
import Model.GameState.Mundo;
import View.Classes.Geo.MapaView;
import View.Classes.Mobiles.PCView;
import View.Classes.Mobiles.PlayerView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MundoView extends Stage implements PropertyChangeListener
{
    protected Controlador controlador;

    protected PlayerView playerView;
    protected MapaView mapaView;
    protected Array<PCView> listaPCViews = new Array<>();

    public OrthographicCamera camara;
    private int nivelDeZoom = 0;

    public PlayerView getPlayerView()                   { return playerView; }
    public MapaView getMapaView()                       { return mapaView; }

    public void eliminarPCView (PCView pcView)
    {   listaPCViews.removeValue(pcView, true); }

    public MundoView(Controlador controlador, Player player, Mundo mundo)
    {
        this.controlador = controlador;

        playerView = new PlayerView(player, this, controlador);
        mapaView = new MapaView(mundo.mapa, this, playerView.getX(), playerView.getY(), MiscData.MAPAVIEW_TamañoX, MiscData.MAPAVIEW_TamañoY);
        camara = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        getViewport().setCamera(camara);

        controlador.addInputProcessor(this);
        mundo.añadirObservador(this);
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
            PC pc = ((MundoDTO.AñadirPC) evt.getNewValue()).pc;

            pc.eliminarObservador(this);
            PCView pcView = new PCView(pc, this, controlador);
            listaPCViews.add(pcView);
        }
    }
}
