package View;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import Model.DTO.MundoDTO;
import Model.Mobiles.MundoModel;
import Model.Mobiles.PcModel;
import View.Mobiles.PcView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Vista implements PropertyChangeListener
{
    public Controlador controlador;
    public MundoModel mundoModel;

    public List<PcView> listaPcViews = new ArrayList<>();

    //Constructor:
    public Vista (Controlador controlador, MundoModel mundoModel)
    {
        mundoModel.añadirObservador(this);
        this.controlador = controlador;
        this.mundoModel = mundoModel;
    }

    public void netUpdate()
    {
        for (PcView pcView: listaPcViews)
            pcView.netUpdate();
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof MundoDTO.AñadirPC)
        {
            PcModel pcModel = ((MundoDTO.AñadirPC) evt.getNewValue()).pcModel;
            PcView pcView = new PcView(pcModel, this);

            for ( PcView gente: listaPcViews)
            {   gente.quienMeVe(); }
        }
    }
}
