package View.UI;// Created by Hanto on 08/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.Acciones.Accion;
import Model.Classes.UIO.EntornoAcciones.BarraAcciones;
import Model.Classes.UIO.EntornoAcciones.ListaAccionesI;
import Recursos.DAO.RSC;
import View.Vista;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

public class ConjuntoBarraAccionesView
{
    public Controlador controlador;
    public Vista vista;

    public Array<BarraAccionesView> listaBarraAccionesView = new Array<>();
    public DragAndDrop dadAcciones = new DragAndDrop();

    public static class Icono
    {
        public ListaAccionesI barra;
        public int posX;
        public int posY;
        public Group apariencia;
        public Source source;
        public Target target;

        public Icono (ListaAccionesI barra, int posX, int posY, Group group)
        {   this.barra = barra; this.posX = posX; this.posY = posY; this.apariencia = group; }
    }




    public ConjuntoBarraAccionesView(Controlador controller, Vista view)
    {
        controlador = controller;
        vista = view;
        dadAcciones.setDragTime(0);
    }

    public void añadirBarraAccionesView(BarraAcciones barracciones)
    {
        BarraAccionesView barraAccionesView = new BarraAccionesView(barracciones, this, vista, controlador);
        listaBarraAccionesView.add(barraAccionesView);
    }

    public Icono crearIcono (final ListaAccionesI barra, int posX, int posY)
    {
        final Icono icono = new Icono(barra, posX, posY, getApariencia(barra.getAccion(posX,posY)));

        icono.source = new Source(icono.apariencia)
        {
            @Override public DragAndDrop.Payload dragStart(InputEvent inputEvent, float v, float v2, int i)
            {
                if (barra.getAccion(icono.posX, icono.posY) != null)
                {
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    Group dragActor = getApariencia(barra.getAccion(icono.posX, icono.posY));
                    dadAcciones.setDragActorPosition(-dragActor.getWidth() / 2, dragActor.getHeight() / 2);
                    payload.setDragActor(dragActor);
                    payload.setObject(icono);
                    return payload;
                }
                return null;
            }
        };

        icono.target = new Target(icono.apariencia)
        {
            @Override public boolean drag(Source source, DragAndDrop.Payload payload, float v, float v2, int i)
            {   return true; }

            @Override public void reset(Source source, DragAndDrop.Payload payload)
            {   super.reset(source, payload); }

            @Override public void drop(Source source, DragAndDrop.Payload payload, float v, float v2, int i)
            {
                Icono origen = ((Icono) payload.getObject());
                controlador.barraAccionMoverAccion(origen.barra, origen.posX, origen.posY, icono.barra, icono.posX, icono.posY);
            }
        };

        dadAcciones.addSource(icono.source);
        dadAcciones.addTarget(icono.target);

        return icono;
    }

    public void eliminarIcono (Icono icono)
    {
        dadAcciones.removeSource(icono.source);
        dadAcciones.removeTarget(icono.target);
    }

    public void setApariencia(Accion accion,  Group group)
    {
        group.clearChildren();

        if (accion == null)
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
            Image casillaIcono = new Image (RSC.accionRecursosDAO.getAccionRecursosDAO().getAccionRecurso(accion.getID()).getTextura());
            casillaIcono.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaIcono);
            group.setWidth(casillaIcono.getWidth());
            group.setHeight(casillaIcono.getHeight());
        }
    }

    public Group getApariencia (Accion accion)
    {
        Group group = new Group();
        setApariencia(accion, group);
        return group;
    }
}
