package View.Classes.UI.BarraAcciones;// Created by Hanto on 08/05/2014.

import Interfaces.UI.BarraAcciones.ControladorBarraAccionI;
import Data.Misc.MiscData;
import Model.Classes.UI.BarraAcciones.BarraAcciones;
import Recursos.DAO.RSC;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

public class ConjuntoBarraAccionesView
{
    private ControladorBarraAccionI controlador;
    private Stage stage;

    private Array<BarraAccionesView> listaBarraAccionesView = new Array<>();
    private DragAndDrop dadAcciones = new DragAndDrop();
    private boolean rebindearSkills = false;

    public boolean getRebindearSkills()             { return rebindearSkills; }
    public DragAndDrop getDadAcciones()             { return dadAcciones; }

    public ConjuntoBarraAccionesView(ControladorBarraAccionI controller, Stage stage)
    {
        controlador = controller;
        this.stage = stage;
        dadAcciones.setDragTime(0);
        crearBotonesRebind();
    }

    public void añadirBarraAccionesView(BarraAcciones barracciones)
    {
        BarraAccionesView barraAccionesView = new BarraAccionesView(barracciones, this, stage, controlador);
        listaBarraAccionesView.add(barraAccionesView);
    }

    public void eliminarBarraAccionesView(BarraAccionesView barraAccionesView)
    {   listaBarraAccionesView.removeValue(barraAccionesView, true); }

    private void crearBotonesRebind()
    {
        final Image rebindButtonOff = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonOFF));
        stage.addActor(rebindButtonOff);
        rebindButtonOff.setPosition(32,0);
        rebindButtonOff.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   //Switch para activar y desactivar el rebindeo de Skills
                rebindearSkills = false;
                rebindButtonOff.toBack();
                return true;
            }
        });

        final Image rebindButtonOn = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        stage.addActor(rebindButtonOn);
        rebindButtonOn.setPosition(32,0);
        rebindButtonOn.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   //Switch para activar y desactivar el rebindeo de Skills
                rebindearSkills = true;
                rebindButtonOn.toBack();
                return true;
            }
        });

        Image añadirBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        stage.addActor(añadirBarra);
        añadirBarra.setPosition(32+18,0);
        añadirBarra.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   controlador.añadirBarraAcciones(2, 5);
                return true;
            }
        });
    }
}
