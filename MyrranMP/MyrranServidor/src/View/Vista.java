package View;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import DTO.MobDTO;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PCModel;
import View.Actores.PCView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Vista implements PropertyChangeListener
{
    public Controlador controlador;
    public MundoModel mundoModel;

    public ArrayList<PCView>listaPCViews = new ArrayList<>();

    //Constructor:
    public Vista (Controlador controlador, MundoModel mundoModel)
    {
        mundoModel.añadirObservador(this);
        this.controlador = controlador;
        this.mundoModel = mundoModel;
    }

    public void netUpdate()
    {
        for (PCView pcView: listaPCViews)
            pcView.netUpdate();
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof MobDTO.MundoAñadirPC)
        {
            PCModel pcModel = ((MobDTO.MundoAñadirPC) evt.getNewValue()).pcModel;
            PCView pcView = new PCView(pcModel, this);

            for ( PCView gente: listaPCViews)
            {   gente.quienMeVe(); }
        }
    }
}
