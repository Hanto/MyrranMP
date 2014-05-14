package View.Classes.UI.ConjuntoBarraAccionView.BarraAccionesView;// Created by Hanto on 06/05/2014.

import Controller.Interfaces.ControladorBarraAccionI;
import Data.MiscData;
import Model.Classes.UIO.ConjuntoBarraAcciones.BarraAcciones;
import Model.DTO.BarraAccionesDTO;
import Recursos.DAO.RSC;
import View.Classes.Graficos.Texto;
import View.Classes.UI.ConjuntoBarraAccionView.IconoAccion.IconoAccion;
import View.Classes.UI.ConjuntoBarraAccionesView;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BarraAccionesView extends Table implements PropertyChangeListener
{
    private BarraAcciones barraModel;
    private Stage stage;
    private DragAndDrop dad;
    private ConjuntoBarraAccionesView conjuntoBarraAccionesView;
    private ControladorBarraAccionI controlador;

    private Array<Array<IconoAccion>> barraIconos = new Array<>();

    public float getEsquinaSupIzdaX()                       { return this.getX(); }
    public float getEsquinaSupIzdaY()                       { return this.getY() + this.getHeight(); }

    public BarraAccionesView(BarraAcciones barraAcciones, ConjuntoBarraAccionesView conjuntoBarraAccionesView, Stage stage, ControladorBarraAccionI controller)
    {
        this.barraModel = barraAcciones;
        this.stage = stage;
        this.controlador = controller;
        this.conjuntoBarraAccionesView = conjuntoBarraAccionesView;
        this.dad = conjuntoBarraAccionesView.getDadAcciones();

        barraAcciones.añadirObservador(this);

        this.setWidth(barraModel.getNumColumnas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));
        this.setHeight(barraModel.getNumFilas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));

        this.bottom().left();
        this.setPosition(500,0);

        for (int y=0; y< barraModel.getNumFilas(); y++)
        {   añadirFila(); }
        recrearTabla();

        this.stage.addActor(this);
    }

    private IconoAccion crearIcono (int posX, int posY)
    {
        final IconoAccion icono = new IconoAccion(barraModel, posX, posY);
        icono.addDragAndDrop(dad, controlador);
        icono.getApariencia().addListener(new BarraRebindListener(icono, conjuntoBarraAccionesView, controlador));

        return icono;
    }

    public void recrearTabla ()
    {
        float esquinaSupIzda = getEsquinaSupIzdaY();

        this.setWidth(barraModel.getNumColumnas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));
        this.setHeight(barraModel.getNumFilas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));

        this.setY(esquinaSupIzda-getHeight());

        this.clear();

        for (int y=0; y< barraIconos.size; y++)
        {
            for (int x = 0; x < barraIconos.get(y).size; x++)
            {
                IconoAccion icono = barraIconos.get(y).get(x);
                this.add(icono.getApariencia()).left().height(MiscData.BARRASPELLS_Alto_Casilla + 2).width(MiscData.BARRASPELLS_Ancho_Casilla + 2);
            }
            this.row();
        }

        final Image moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        this.addActor(moverBarra);
        moverBarra.setPosition(-moverBarra.getWidth()-2,this.getHeight()-moverBarra.getHeight());
        moverBarra.addListener(new BarraMoverListener(moverBarra, this));

        final Image redimensionarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        this.addActor(redimensionarBarra);
        redimensionarBarra.setPosition(this.getWidth(),-redimensionarBarra.getHeight());
        redimensionarBarra.addListener(new BarraResizeListener(redimensionarBarra, this, barraModel, controlador));
    }

    private void añadirFila()
    {
        int y = barraIconos.size;
        Array<IconoAccion>array = new Array<>();

        for (int x = 0; x< barraModel.getNumColumnas(); x++)
        {
            IconoAccion icono = crearIcono(x, y);
            array.add(icono);
        }

        barraIconos.add(array);
    }

    private void añadirColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            int x = barraIconos.get(y).size;
            IconoAccion icono = crearIcono(x, y);
            barraIconos.get(y).add(icono);
        }
    }

    private void eliminarFila ()
    {
        Array<IconoAccion> array = barraIconos.peek();
        for (int i=0; i< array.size; i++)
        {   array.get(i).eliminarIcono(dad); }
        barraIconos.removeIndex(barraIconos.size - 1);
    }

    private void eliminarColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            IconoAccion icono = barraIconos.get(y).pop();
            icono.eliminarIcono(dad);
        }
    }


    private void actualizarApariencia(IconoAccion icono)
    {
        icono.actualizarApariencia();
        if (barraModel.getKeybind(icono.posX, icono.posY) != null)
        {
            Texto.printTexto(String.valueOf(barraModel.getKeybind(icono.posX, icono.posY)), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                             Color.ORANGE, Color.BLACK, 0, 20, Align.left, Align.bottom, 2, icono.getApariencia());
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof BarraAccionesDTO.EliminarAccionDTO)
        {
            int posX = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posY;
            actualizarApariencia(barraIconos.get(posY).get(posX));
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.SetAccionDTO)
        {
            int posX = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posY;
            actualizarApariencia(barraIconos.get(posY).get(posX));
        }

        if  (evt.getNewValue() instanceof BarraAccionesDTO.EliminarFilaDTO)
        {   eliminarFila(); }

        if (evt.getNewValue() instanceof BarraAccionesDTO.AñadirFilaDTO)
        {   añadirFila(); }

        if (evt.getNewValue() instanceof BarraAccionesDTO.EliminarColumnaDTO)
        {   eliminarColumna(); }

        if (evt.getNewValue() instanceof BarraAccionesDTO.AñadirColumnaDTO)
        {   añadirColumna(); }
    }
}
