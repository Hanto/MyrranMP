package View;// Created by Hanto on 07/04/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Model.Classes.Mobiles.Mundo;
import Model.Classes.Mobiles.PC;
import View.Classes.Mobiles.PcView;

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
        if (evt.getNewValue() instanceof NetDTO.AñadirPC)
        {
            PC pc = mundo.getPC(((NetDTO.AñadirPC) evt.getNewValue()).connectionID);

            pc.eliminarObservador(this);
            PcView pcView = new PcView(pc, this);
            listaPcViews.add(pcView);

            //for ( PcView gente: listaPcViews)
            //{   gente.quienMeVe(); }
        }
    }
}
