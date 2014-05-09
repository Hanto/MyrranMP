package View.UI;// Created by Hanto on 08/05/2014.

import Controller.Controlador;
import Model.Classes.UIO.EntornoAcciones.BarraAcciones;
import View.Vista;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

public class EntornoAccionesView
{
    public Controlador controlador;
    public Vista vista;

    public DragAndDrop dadAcciones = new DragAndDrop();
    public Array<BarraAccionesView> listaBarraAccionesView = new Array<>();

    public EntornoAccionesView (Controlador controller, Vista view)
    {
        controlador = controller;
        vista = view;
        dadAcciones.setDragTime(0);
    }

    public void añadirBarraAccionesView(BarraAcciones barracciones)
    {
        BarraAccionesView barraAccionesView = new BarraAccionesView(barracciones, dadAcciones, vista, controlador);
        listaBarraAccionesView.add(barraAccionesView);
    }
}
