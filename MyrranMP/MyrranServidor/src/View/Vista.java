package View;// Created by Hanto on 07/04/2014.

import Controller.ControladorServidor;
import DTOs.ActorDTO;
import DTOs.ActorDTO.MundoAñadirPC;
import Models.MundoModel;
import Models.PCModel;
import View.Actores.PCView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Vista implements PropertyChangeListener
{
    public ControladorServidor controlador;
    public MundoModel mundo;

    public ArrayList<PCView>listaPCViews = new ArrayList<>();

    //Constructor:
    public Vista (ControladorServidor controlador, MundoModel mundo)
    {
        mundo.añadirObservador(this);
        this.controlador = controlador; this.mundo = mundo;
    }

    public void netUpdate()
    {
        for (PCView pcView: listaPCViews)
            pcView.netUpdate();
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof ActorDTO.MundoAñadirPC)
        {
            PCModel pc = ((MundoAñadirPC) evt.getNewValue()).pc;
            PCView pcView = new PCView(pc, this);

            for ( PCView gente: listaPCViews)
            {   gente.quienMeVe(); }
        }
    }
}
