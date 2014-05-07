package View.UI;// Created by Hanto on 06/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.UIO.BarraAcciones;
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

public class BarraAccionesView extends Table implements PropertyChangeListener
{
    private BarraAcciones barraModel;  //MODEL
    private Vista vista;
    private Controlador controlador;

    private Array<Icono> barraIconos = new Array<>();
    private DragAndDrop dad;
    private boolean rebindearSkills = false;
    private int numFilas = 3;

    public static class Icono
    {
        public int numIcono;
        public Group apariencia;

        public Icono(int numIcono, Group group)
        {   this.numIcono = numIcono; this.apariencia = group; }
    }

    public BarraAccionesView(final BarraAcciones barraAcciones, Vista vista, Controlador controlador)
    {
        this.barraModel = barraAcciones;
        this.vista = vista;
        this.controlador = controlador;

        vista.listaBarraAccionesView.add(this);
        barraAcciones.añadirObservador(this);

        this.setWidth(10*(MiscData.BARRASPELLS_Ancho_Casilla+2));
        this.setHeight(numFilas*(MiscData.BARRASPELLS_Ancho_Casilla+2));

        this.bottom().left();;
        dad = new DragAndDrop();

        dad.setDragTime(0);

        for (int i=0 ; i< barraModel.getTamaño(); i++)
        {
            final Icono icono = new Icono(i, getApariencia(i));
            barraIconos.add(icono);

            this.add(icono.apariencia).left().height(MiscData.BARRASPELLS_Alto_Casilla + 2).width(MiscData.BARRASPELLS_Ancho_Casilla + 2);
            if ((i+1)%10 == 0) this.row();


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
                    {   barraModel.setKeycode(icono.numIcono, keycode); }
                    return true;
                }
            });


            dad.addSource(new DragAndDrop.Source(icono.apariencia)
            {
                @Override public DragAndDrop.Payload dragStart(InputEvent inputEvent, float v, float v2, int i)
                {
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setDragActor(getApariencia(icono.numIcono));
                    payload.setObject(icono);
                    return payload;
                }
            });

            dad.addTarget(new DragAndDrop.Target(icono.apariencia)
            {
                @Override public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
                {   return true; }

                @Override public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload)
                {   super.reset(source, payload); }

                @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
                {
                    Icono origen = ((Icono) payload.getObject());

                    barraModel.moverAccion(icono.numIcono, origen.numIcono);
                }
            });
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
                int newX = (int)(BarraAccionesView.this.getX() + x);
                int newY = (int)(BarraAccionesView.this.getY() + y);
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


    public void setApariencia(int posicion, Group group)
    {
        group.clearChildren();

        if (barraModel.getAccion(posicion) == null)
        {
            Image casillaVacia = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_Textura_Casillero));
            casillaVacia.setColor(0, 0, 0, 0.1f);
            casillaVacia.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaVacia);
        }
        else
        {
            Image casillaIcono = new Image (RSC.accionRecursosDAO.getAccionRecursosDAO().getAccionRecurso(barraModel.getAccion(posicion).getID()).getTextura());
            casillaIcono.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaIcono);
        }
        if (barraModel.getKeybind(posicion) != null)
        {
            Texto.printTexto(String.valueOf(barraModel.getKeybind(posicion)), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                    Color.ORANGE, Color.BLACK, 0, 20, Align.left, Align.bottom, 2, group);
        }
    }

    public void setApariencia(Icono icono)
    {   setApariencia(icono.numIcono, icono.apariencia); }

    public Group getApariencia (int posicion)
    {
        Group group = new Group();
        setApariencia(posicion, group);
        return group;
    }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof BarraAccionesDTO.RemoveAccionDTO)
        {
            int posicion = ((BarraAccionesDTO.RemoveAccionDTO) evt.getNewValue()).posicion;
            setApariencia(barraIconos.get(posicion));
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.SetAccionDTO)
        {
            int posicion = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posicion;
            setApariencia(barraIconos.get(posicion));
        }
    }
}
