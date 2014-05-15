package View.Classes.UI.BarraAcciones.BarraAccionesView;// Created by Hanto on 06/05/2014.

import Controller.Interfaces.ControladorBarraAccionI;
import Data.MiscData;
import Model.Classes.UI.ConjuntoBarraAcciones.BarraAcciones;
import Model.DTO.BarraAccionesDTO;
import Recursos.DAO.RSC;
import View.Classes.Graficos.Texto;
import View.Classes.UI.BarraAcciones.AccionIcono.AccionIcono;
import View.Classes.UI.Comun.MoverActorListener;
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

    private Image moverBarra;
    private Image redimensionarBarra;

    private Array<Array<AccionIcono>> barraIconos = new Array<>();

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

        moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        moverBarra.addListener(new MoverActorListener(moverBarra, this));
        this.addActor(moverBarra);

        redimensionarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        redimensionarBarra.addListener(new BAccionesResizeListener(redimensionarBarra, this, barraModel, controlador));
        this.addActor(redimensionarBarra);

        for (int y=0; y< barraModel.getNumFilas(); y++)
        {   añadirFila(); }

        this.stage.addActor(this);
    }

    private AccionIcono crearIcono (int posX, int posY)
    {
        final AccionIcono icono = new AccionIcono(barraModel, posX, posY);
        icono.addDragAndDrop(dad, controlador);
        icono.getApariencia().addListener(new BAccionesRebindListener(icono, conjuntoBarraAccionesView, controlador));

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
                AccionIcono icono = barraIconos.get(y).get(x);
                this.add(icono.getApariencia()).left().height(MiscData.BARRASPELLS_Alto_Casilla + 2).width(MiscData.BARRASPELLS_Ancho_Casilla + 2);
            }
            this.row();
        }

        moverBarra.setPosition(-moverBarra.getWidth()-2,this.getHeight()-moverBarra.getHeight());
        this.addActor(moverBarra);
        redimensionarBarra.setPosition(this.getWidth(),-redimensionarBarra.getHeight());
        this.addActor(redimensionarBarra);
    }

    private void añadirFila()
    {
        int y = barraIconos.size;
        Array<AccionIcono>array = new Array<>();

        for (int x = 0; x< barraModel.getNumColumnas(); x++)
        {
            AccionIcono icono = crearIcono(x, y);
            array.add(icono);
        }

        barraIconos.add(array);
        recrearTabla();
    }

    private void añadirColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            int x = barraIconos.get(y).size;
            AccionIcono icono = crearIcono(x, y);
            barraIconos.get(y).add(icono);
        }
        recrearTabla();
    }

    private void eliminarFila ()
    {
        Array<AccionIcono> array = barraIconos.peek();
        for (int i=0; i< array.size; i++)
        {   array.get(i).eliminarIcono(dad); }
        barraIconos.removeIndex(barraIconos.size - 1);
        recrearTabla();
    }

    private void eliminarColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            AccionIcono icono = barraIconos.get(y).pop();
            icono.eliminarIcono(dad);
        }
        recrearTabla();
    }


    private void actualizarApariencia(AccionIcono icono)
    {
        icono.actualizarApariencia();
        if (barraModel.getKeybind(icono.getPosX(), icono.getPosY()) != null)
        {
            Texto.printTexto(String.valueOf(barraModel.getKeybind(icono.getPosX(), icono.getPosY())), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
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
