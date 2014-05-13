package View.UI;// Created by Hanto on 06/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.UIO.ConjuntoBarraAcciones.BarraAcciones;
import Model.DTO.BarraAccionesDTO;
import Recursos.DAO.RSC;
import View.Graficos.Caja;
import View.Graficos.Texto;
import View.Vista;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static View.UI.ConjuntoBarraAccionesView.Icono;

public class BarraAccionesView extends Table implements PropertyChangeListener
{
    private BarraAcciones barraModel;  //MODEL
    private Vista vista;
    private Controlador controlador;
    private ConjuntoBarraAccionesView conjuntoBarraAccionesView;

    private Array<Array<Icono>> barraIconos = new Array<>();

    private Caja caja = new Caja();
    private int redimensionarX;
    private int redimensionarY;

    public float getEsquinaSupIzdaX()                       { return this.getX(); }
    public float getEsquinaSupIzdaY()                       { return this.getY() + this.getHeight(); }

    public BarraAccionesView(BarraAcciones barraAcciones, ConjuntoBarraAccionesView conjuntoBarraAccionesView, Vista view, Controlador controller)
    {
        this.barraModel = barraAcciones;
        this.vista = view;
        this.controlador = controller;
        this.conjuntoBarraAccionesView = conjuntoBarraAccionesView;

        barraAcciones.añadirObservador(this);

        this.setWidth(barraModel.getNumColumnas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));
        this.setHeight(barraModel.getNumFilas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));

        this.bottom().left();
        this.setPosition(500,0);

        for (int y=0; y< barraModel.getNumFilas(); y++)
        {   añadirFila(); }
        recrearTabla();

        vista.stageUI.addActor(this);
    }

    private Icono crearIcono (int posX, int posY)
    {
        final Icono icono = conjuntoBarraAccionesView.crearIcono(barraModel, posX, posY);

        icono.apariencia.addListener(new InputListener()
        {
            @Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {   if (conjuntoBarraAccionesView.getRebindearSkills()) icono.apariencia.getStage().setKeyboardFocus(icono.apariencia); }

            //Hacemos que deje de recibir eventos de teclado, puesto que el teclado ha salido
            @Override public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {   if (conjuntoBarraAccionesView.getRebindearSkills()) icono.apariencia.getStage().setKeyboardFocus(null); }

            //Capturamos que tecla aprieta el player para rebindearla
            @Override public boolean keyDown (InputEvent event, int keycode)
            {   //Solo rebindeamos los skills, si esta activado el boton de rebindear
                if (conjuntoBarraAccionesView.getRebindearSkills())
                {   controlador.barraAccionRebindear(barraModel, icono.posX, icono.posY, keycode); }
                return true;
            }
        });

        return icono;
    }

    private void recrearTabla ()
    {
        float altura = this.getHeight();

        this.setWidth(barraModel.getNumColumnas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));
        this.setHeight(barraModel.getNumFilas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));

        if (altura != this.getHeight())
        {   this.setPosition(this.getX(), this.getY()-this.getHeight()+altura); }

        this.clear();

        for (int y=0; y< barraIconos.size; y++)
        {
            for (int x = 0; x < barraIconos.get(y).size; x++)
            {
                Icono icono = barraIconos.get(y).get(x);
                this.add(icono.apariencia).left().height(MiscData.BARRASPELLS_Alto_Casilla + 2).width(MiscData.BARRASPELLS_Ancho_Casilla + 2);
            }
            this.row();
        }

        final Image moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        this.addActor(moverBarra);
        moverBarra.setPosition(-moverBarra.getWidth()-2,this.getHeight()-moverBarra.getHeight());
        moverBarra.addListener(new DragListener()
        {
            @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
            {
                int newX = (int) (getX() -moverBarra.getWidth()/2 + x);
                int newY = (int) (getY() -moverBarra.getHeight()/2 + y);
                int alto = (int) getHeight();
                int ancho = (int) getWidth();

                if (newX - moverBarra.getWidth() < 0) newX = 0 + (int)moverBarra.getWidth();
                if (newY < 0) newY = 0;
                if (newX + ancho > MiscData.GDX_Window_Horizontal_Resolution) newX = MiscData.GDX_Window_Horizontal_Resolution - ancho;
                if (newY + alto > MiscData.GDX_Window_Vertical_Resolution) newY = MiscData.GDX_Window_Vertical_Resolution - alto;

                BarraAccionesView.this.setPosition( newX, newY);
            }
        });

        final Image redimensionarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        this.addActor(redimensionarBarra);
        redimensionarBarra.setPosition(this.getWidth(),-redimensionarBarra.getHeight());
        redimensionarBarra.addListener(new DragListener()
        {
            @Override public void touchUp (InputEvent event, float x, float y, int pointer, int button)
            {
                BarraAccionesView.this.getStage().getRoot().removeActor(caja);

                int X = (int)(event.getStageX()-redimensionarX);
                int Y = (int)(event.getStageY()-redimensionarY);

                int columnasAdicionales = Math.round((float)Math.abs(X)/(float)(MiscData.BARRASPELLS_Ancho_Casilla+2));
                int filasAdicionales = Math.round((float)Math.abs(Y)/(float)(MiscData.BARRASPELLS_Alto_Casilla+2));

                if (X >0)
                {
                    for (int i=0; i<columnasAdicionales; i++)
                    {   controlador.barraAñadirColumna(barraModel); recrearTabla(); }
                }
                else
                {
                    for (int i=0; i<columnasAdicionales; i++)
                    {   controlador.barraEliminarColumna(barraModel); recrearTabla(); }
                }

                if (Y<0)
                {
                    for (int i=0; i<filasAdicionales; i++)
                    {   controlador.barraAñadirFila(barraModel); recrearTabla(); }
                }
                else
                {
                    for (int i=0; i<filasAdicionales; i++)
                    {   controlador.barraEliminarFila(barraModel); recrearTabla(); }
                }
            }

            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                caja.setCaja (getEsquinaSupIzdaX(), getEsquinaSupIzdaY(),
                              getWidth(), -getHeight());

                BarraAccionesView.this.getStage().addActor(caja);

                redimensionarX = (int)event.getStageX();
                redimensionarY = (int)event.getStageY();
                return true;
            }

            @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
            {
                float newX = redimensionarBarra.getX() -redimensionarBarra.getWidth()/2 + x;
                float newY = redimensionarBarra.getY() -redimensionarBarra.getHeight()/2 + y;

                redimensionarBarra.setPosition(newX, newY);

                caja.setTamaño(newX, newY - getHeight() + redimensionarBarra.getHeight());
            }
        });

    }

    private void añadirFila()
    {
        int y = barraIconos.size;
        Array<Icono>array = new Array<>();

        for (int x = 0; x< barraModel.getNumColumnas(); x++)
        {
            final Icono icono = crearIcono(x, y);
            array.add(icono);
        }

        barraIconos.add(array);
    }

    private void añadirColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            int x = barraIconos.get(y).size;
            Icono icono = crearIcono(x, y);
            barraIconos.get(y).add(icono);
        }
    }

    private void eliminarFila ()
    {
        Array<Icono> array = barraIconos.peek();
        for (int i=0; i< array.size; i++)
        {   conjuntoBarraAccionesView.eliminarIcono(array.get(i)); }
        barraIconos.removeIndex(barraIconos.size - 1);
    }

    private void eliminarColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            Icono icono = barraIconos.get(y).pop();
            conjuntoBarraAccionesView.eliminarIcono(icono);
        }
    }


    private void setApariencia(Icono icono)
    {
        conjuntoBarraAccionesView.setApariencia(barraModel.getAccion(icono.posX, icono.posY), icono.apariencia);
        if (barraModel.getKeybind(icono.posX, icono.posY) != null)
        {
            Texto.printTexto(String.valueOf(barraModel.getKeybind(icono.posX, icono.posY)), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                             Color.ORANGE, Color.BLACK, 0, 20, Align.left, Align.bottom, 2, icono.apariencia);
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof BarraAccionesDTO.EliminarAccionDTO)
        {
            int posX = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posY;
            setApariencia(barraIconos.get(posY).get(posX));
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.SetAccionDTO)
        {
            int posX = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posY;
            setApariencia(barraIconos.get(posY).get(posX));
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
