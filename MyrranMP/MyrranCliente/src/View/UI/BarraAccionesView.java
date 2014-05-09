package View.UI;// Created by Hanto on 06/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.UIO.EntornoAcciones.BarraAcciones;
import Model.DTO.BarraAccionesDTO;
import Recursos.DAO.RSC;
import View.Graficos.Texto;
import View.Vista;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;

public class BarraAccionesView extends Table implements PropertyChangeListener
{
    private BarraAcciones barraModel;  //MODEL
    private Vista vista;
    private Controlador controlador;

    //private Map<Integer,Icono> barraIconos = new HashMap<>();

    private Array<Array<Icono>> barraIconos2 = new Array<>();

    private DragAndDrop dad;
    private boolean rebindearSkills = false;

    public static class Icono
    {
        public int numBarra;
        public int posX;
        public int posY;
        public Group apariencia;
        public Source source;
        public Target target;

        public Icono(int numBarra, int posX, int posY, Group group)
        {   this.numBarra= numBarra; this.posX = posX; this.posY = posY; this.apariencia = group; }
    }

    public BarraAccionesView(BarraAcciones barraAcciones, DragAndDrop dadAcciones, Vista view, Controlador controller)
    {
        this.barraModel = barraAcciones;
        this.vista = view;
        this.controlador = controller;
        this.dad = dadAcciones;

        barraAcciones.añadirObservador(this);

        this.setWidth(barraModel.getNumColumnas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));
        this.setHeight(barraModel.getNumFilas()*(MiscData.BARRASPELLS_Ancho_Casilla+2));

        this.bottom().left();;


        for (int y=0; y< barraModel.getNumFilas(); y++)
        {

            Array<Icono> array = new Array<>();
            for (int x = 0; x< barraModel.getNumColumnas(); x++)
            {
                final Icono icono = new Icono(barraModel.getID(), x, y, getApariencia(x, y, true));
                array.add(icono);
                this.add(icono.apariencia).left().height(MiscData.BARRASPELLS_Alto_Casilla + 2).width(MiscData.BARRASPELLS_Ancho_Casilla + 2);

                icono.source = new Source(icono.apariencia)
                {
                    @Override public Payload dragStart(InputEvent inputEvent, float v, float v2, int i)
                    {
                        if (barraModel.getAccion(icono.posX, icono.posY) != null)
                        {
                            Payload payload = new Payload();
                            Group dragActor = getApariencia(icono.posX,icono.posY, false);
                            dad.setDragActorPosition(-dragActor.getWidth() / 2, dragActor.getHeight() / 2);
                            payload.setDragActor(dragActor);
                            payload.setObject(icono);
                            return payload;
                        }
                        return null;
                    }
                };

                icono.target = new Target(icono.apariencia)
                {
                    @Override public boolean drag(Source source, Payload payload, float v, float v2, int i)
                    {   return true; }

                    @Override public void reset(Source source, Payload payload)
                    {   super.reset(source, payload); }

                    @Override public void drop(Source source, Payload payload, float v, float v2, int i)
                    {
                        Icono origen = ((Icono) payload.getObject());
                        controlador.barraAccionmoverAccion(origen.numBarra, origen.posX, origen.posY, icono.numBarra, icono.posX, icono.posY);
                    }
                };

                icono.apariencia.addListener(new InputListener()
                {
                    @Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
                    {   if (rebindearSkills) icono.apariencia.getStage().setKeyboardFocus(icono.apariencia); }

                    //Hacemos que deje de recibir eventos de teclado, puesto que el teclado ha salido
                    @Override public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
                    {   if (rebindearSkills) icono.apariencia.getStage().setKeyboardFocus(null); }

                    //Capturamos que tecla aprieta el player para rebindearla
                    @Override public boolean keyDown (InputEvent event, int keycode)
                    {   //Solo rebindeamos los skills, si esta activado el boton de rebindear
                        if (rebindearSkills)
                        {   controlador.barraAccionRebindear(icono.numBarra, icono.posX, icono.posY, keycode); }
                        return true;
                    }
                });

                dad.addSource(icono.source);
                dad.addTarget(icono.target);
            }
            barraIconos2.add(array);
            this.row();
        }


        final Image rebindButtonOff = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonOFF));
        this.addActor(rebindButtonOff);
        rebindButtonOff.setPosition(-rebindButtonOff.getWidth()-2,0);
        rebindButtonOff.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   //Switch para activar y desactivar el rebindeo de Skills
                rebindearSkills = false;
                BarraAccionesView.this.getStage().setKeyboardFocus(null);
                rebindButtonOff.toBack();
                return true;
            }
        });

        final Image rebindButtonOn = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        this.addActor(rebindButtonOn);
        rebindButtonOn.setPosition(-rebindButtonOn.getWidth()-2,0);
        rebindButtonOn.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   //Switch para activar y desactivar el rebindeo de Skills
                rebindearSkills = true;
                rebindButtonOn.toBack();
                return true;
            }
        });

        final Image moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        this.addActor(moverBarra);
        moverBarra.setPosition(-moverBarra.getWidth()-2,this.getHeight()-moverBarra.getHeight());
        moverBarra.addListener(new DragListener()
        {
            @Override public void touchDragged (InputEvent event, float x, float y, int pointer)
            {
                int newX = (int)(BarraAccionesView.this.getX() -moverBarra.getWidth()/2 + x);
                int newY = (int)(BarraAccionesView.this.getY() -moverBarra.getHeight()/2 + y);
                int alto = (int)BarraAccionesView.this.getHeight();
                int ancho = (int)BarraAccionesView.this.getWidth();

                if (newX - moverBarra.getWidth() < 0) newX = 0 + (int)moverBarra.getWidth();
                if (newY < 0) newY = 0;
                if (newX + ancho > MiscData.GDX_Window_Horizontal_Resolution) newX = MiscData.GDX_Window_Horizontal_Resolution - ancho;
                if (newY + alto > MiscData.GDX_Window_Vertical_Resolution) newY = MiscData.GDX_Window_Vertical_Resolution - alto;

                BarraAccionesView.this.setPosition( newX, newY);
            }
        });

        this.setPosition(500,0);
        vista.stageUI.addActor(this);
    }

    public void eliminarIcono (int posX, int posY)
    {/*
        dad.removeSource(barraIconos.get(posicion).source);
        dad.removeTarget(barraIconos.get(posicion).target);

        this.removeActor(barraIconos.get(posicion).apariencia);
        barraIconos.remove(posicion);*/

    }


    public void setApariencia(int posX, int posY,  Group group, boolean getKeybind)
    {
        group.clearChildren();

        if (barraModel.getAccion(posX, posY) == null)
        {
            Image casillaVacia = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_Textura_Casillero));
            casillaVacia.setColor(0, 0, 0, 0.06f);
            casillaVacia.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaVacia);
            group.setWidth(casillaVacia.getWidth());
            group.setHeight(casillaVacia.getHeight());
        }
        else
        {
            Image casillaIcono = new Image (RSC.accionRecursosDAO.getAccionRecursosDAO().getAccionRecurso(barraModel.getAccion(posX, posY).getID()).getTextura());
            casillaIcono.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaIcono);
            group.setWidth(casillaIcono.getWidth());
            group.setHeight(casillaIcono.getHeight());
        }
        if (barraModel.getKeybind(posX, posY) != null && getKeybind)
        {
            Texto.printTexto(String.valueOf(barraModel.getKeybind(posX, posY)), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                    Color.ORANGE, Color.BLACK, 0, 20, Align.left, Align.bottom, 2, group);
        }
    }

    public void setApariencia(Icono icono, boolean getKeybind)
    {   setApariencia(icono.posX, icono.posY, icono.apariencia, getKeybind); }

    public Group getApariencia (int posX, int posY, boolean getkeybind)
    {
        Group group = new Group();
        setApariencia(posX, posY, group, getkeybind);
        return group;
    }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof BarraAccionesDTO.EliminarAccionDTO)
        {
            int posX = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posY;
            setApariencia(barraIconos2.get(posY).get(posX), true);
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.SetAccionDTO)
        {
            int posX = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posY;
            setApariencia(barraIconos2.get(posY).get(posX), true);
        }

        if  (evt.getNewValue() instanceof BarraAccionesDTO.EliminarCasillaDTO)
        {
            int posX = ((BarraAccionesDTO.EliminarCasillaDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.EliminarCasillaDTO) evt.getNewValue()).posY;
            eliminarIcono(posX, posY);
        }
    }
}
