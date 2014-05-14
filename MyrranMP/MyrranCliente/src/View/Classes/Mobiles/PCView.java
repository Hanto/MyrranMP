package View.Classes.Mobiles;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.Mobiles.PC;
import Model.DTO.PcDTO;
import Model.DTO.PlayerDTO;
import View.Classes.Graficos.PixiePC;
import View.ViewState.MundoView;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PCView extends Group implements PropertyChangeListener
{
    public PC pc;
    public MundoView mundoView;
    public Controlador controlador;

    public int connectionID;
    public PixiePC actor;

    public PCView (PC pc, MundoView vista, Controlador controlador)
    {
        this.pc = pc;
        this.mundoView = vista;
        this.controlador = controlador;

        connectionID = pc.getConnectionID();
        this.setPosition(pc.getX(), pc.getY());

        pc.añadirObservador(this);

        crearActor();
    }

    public void crearActor ()
    {
        actor = new PixiePC("Golem");
        this.addActor(actor);
        mundoView.addActor(this);
        actor.setAnimacion(5, false);
    }

    public void eliminar()
    {
        pc.eliminarObservador(this);
        mundoView.getRoot().removeActor(this);
        mundoView.eliminarPCView(this);
    }

    public void mover(int x, int y)
    {
        //TODO hay que hacerlo por setPosition y en cambio mover el model interpoladamente, el destino sin decimales
        this.addAction(Actions.moveTo(x, y, MiscData.NETWORK_Update_Time / 1000f, Interpolation.linear));
        //setPosition(x,y);
    }

    public void setAnimacion (int numAnimacion)
    {   actor.setAnimacion(numAnimacion, false); }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof PcDTO.PositionPC)
        {
            float x = ((PcDTO.PositionPC) evt.getNewValue()).x;
            float y = ((PcDTO.PositionPC) evt.getNewValue()).y;
            mover((int)x, (int)y);
        }

        if (evt.getNewValue() instanceof PlayerDTO.AnimacionPlayer)
        {
            int numAnimacion = ((PlayerDTO.AnimacionPlayer) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }

        if (evt.getNewValue() instanceof PcDTO.EliminarPC)
        {   eliminar(); }
    }
}
