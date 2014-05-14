package View.UI;// Created by Hanto on 08/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.UIO.ConjuntoBarraAcciones.BarraAcciones;
import Recursos.DAO.RSC;
import View.UI.ConjuntoBarraAccionView.BarraAccionesView.BarraAccionesView;
import View.Vista;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

public class ConjuntoBarraAccionesView
{
    private Controlador controlador;
    private Vista vista;

    private Array<BarraAccionesView> listaBarraAccionesView = new Array<>();
    private DragAndDrop dadAcciones = new DragAndDrop();
    private boolean rebindearSkills = false;

    public boolean getRebindearSkills()             { return rebindearSkills; }
    public DragAndDrop getDadAcciones()             { return dadAcciones; }

    public ConjuntoBarraAccionesView(Controlador controller, Vista view)
    {
        controlador = controller;
        vista = view;
        dadAcciones.setDragTime(0);
        crearBotonesRebind();
    }

    public void añadirBarraAccionesView(BarraAcciones barracciones)
    {
        BarraAccionesView barraAccionesView = new BarraAccionesView(barracciones, this, vista.stageUI, controlador);
        listaBarraAccionesView.add(barraAccionesView);
    }

    private void crearBotonesRebind()
    {
        final Image rebindButtonOff = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonOFF));
        vista.stageUI.addActor(rebindButtonOff);
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
        vista.stageUI.addActor(rebindButtonOn);
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
    }
}
