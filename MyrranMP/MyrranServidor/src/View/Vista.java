package View;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import Model.DTO.MundoDTO;
import Model.Classes.Mobiles.Mundo;
import Model.Classes.Mobiles.PC;
import View.Mobiles.PcView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Vista implements PropertyChangeListener
{
    public Controlador controlador;
    public Mundo mundo;

    public List<PcView> listaPcViews = new ArrayList<>();

    //Constructor:
    public Vista (Controlador controlador, Mundo mundo)
    {
        mundo.añadirObservador(this);
        this.controlador = controlador;
        this.mundo = mundo;
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
            PC PC = ((MundoDTO.AñadirPC) evt.getNewValue()).PC;
            PcView pcView = new PcView(PC, this);

            for ( PcView gente: listaPcViews)
            {   gente.quienMeVe(); }
        }
    }
}
