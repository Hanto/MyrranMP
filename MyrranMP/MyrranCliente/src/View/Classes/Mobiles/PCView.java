package View.Classes.Mobiles;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import DB.RSC;
import DTO.NetDTO;
import Data.MiscData;
import Model.Classes.Mobiles.PC;
import View.Classes.Graficos.PixiePC;
import View.Classes.Graficos.Texto;
import View.GameState.MundoView;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

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
        this.setWidth(actor.getWidth());
        this.setHeight(actor.getHeight());
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
        this.addAction(Actions.moveTo(x, y, MiscData.SERVIDOR_Delta_Time, Interpolation.linear));
        //setPosition(x,y);
    }

    public void modificarHPs(NetDTO.ModificarHPsPPC HPs)
    {
        Texto texto = new Texto(Integer.toString((int)HPs.HPs), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                Color.RED, Color.BLACK, 0, 0, Align.center, Align.bottom, 1);
        texto.setPosition(this.getWidth()/2+(float)Math.random()*30-15, this.getHeight()+15);
        texto.scrollingCombatText(this, 2f);
    }

    public void setAnimacion (int numAnimacion)
    {   actor.setAnimacion(numAnimacion, false); }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof NetDTO.PosicionPPC)
        {
            float x = ((NetDTO.PosicionPPC) evt.getNewValue()).x;
            float y = ((NetDTO.PosicionPPC) evt.getNewValue()).y;
            mover((int)x, (int)y);
        }

        if (evt.getNewValue() instanceof NetDTO.ModificarHPsPPC)
        {   modificarHPs((NetDTO.ModificarHPsPPC)evt.getNewValue()); }

        if (evt.getNewValue() instanceof NetDTO.AnimacionPPC)
        {
            int numAnimacion = ((NetDTO.AnimacionPPC) evt.getNewValue()).numAnimacion;
            setAnimacion(numAnimacion);
        }

        if (evt.getNewValue() instanceof NetDTO.EliminarPPC)
        {   eliminar(); }
    }
}
