package View.Actores;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import DTO.MobDTO;
import Modelo.Mobiles.MundoModel;
import Modelo.Mobiles.PCModel;
import View.Graficos.PixiePC;
import View.Vista;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import zMain.MiscData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PCView extends Group implements PropertyChangeListener
{
    public PCModel pcModel;
    public Vista vista;
    public Controlador controlador;
    public MundoModel mundoModel;

    public int connectionID;
    public PixiePC actor;

    public PCView (PCModel pcModel, Vista vista, Controlador controlador)
    {
        this.pcModel = pcModel;
        this.vista = vista;
        this.controlador = controlador;
        this.mundoModel = vista.mundoModel;

        connectionID = pcModel.getConnectionID();
        this.setPosition(pcModel.getX(), pcModel.getY());

        vista.listaPCViews.add(this);
        pcModel.eliminarObservador(vista);
        pcModel.añadirObservador(this);

        crearActor();
    }

    public void crearActor ()
    {
        actor = new PixiePC("Golem");
        this.addActor(actor);
        vista.stageMundo.addActor(this);
        actor.setAnimacion(5, false);
    }

    public void eliminar()
    {
        pcModel.eliminarObservador(this);
        vista.stageMundo.getRoot().removeActor(this);
        vista.listaPCViews.removeValue(this, true);
    }

    public void mover(float x, float y)
    {
        //TODO hay que hacerlo por setPosition y en cambio mover el model interpoladamente
        this.addAction(Actions.moveTo(x, y, MiscData.NETWORK_Update_Time / 1000f, Interpolation.linear));
        //setPosition(x,y);
    }

    public void setAnimacion (int numAnimacion)
    {   actor.setAnimacion(numAnimacion, false); }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof MobDTO.MoverPC)
        {
            float x = ((MobDTO.MoverPC) evt.getNewValue()).x;
            float y = ((MobDTO.MoverPC) evt.getNewValue()).y;
            mover(x, y);
        }

        if (evt.getNewValue() instanceof MobDTO.CambiarAnimacionPlayer)
        {
            int numAnimacion = ((MobDTO.CambiarAnimacionPlayer) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }

        if (evt.getNewValue() instanceof MobDTO.EliminarPC)
        {   eliminar(); }
    }
}
