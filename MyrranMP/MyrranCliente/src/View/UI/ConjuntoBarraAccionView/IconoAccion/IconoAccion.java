package View.UI.ConjuntoBarraAccionView.IconoAccion;// Created by Hanto on 13/05/2014.

import Controller.Interfaces.ControladorBarraAccionI;
import Data.MiscData;
import Model.Classes.Acciones.Accion;
import Model.Classes.UIO.ConjuntoBarraAcciones.ListaAccionesI;
import Recursos.DAO.RSC;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class IconoAccion
{
    public ListaAccionesI barra;
    public int posX;
    public int posY;

    private Group apariencia = new Group();
    public AccionSource source;
    public AccionTarget target;

    public IconoAccion(ListaAccionesI barra, int posX, int posY)
    {
        this.barra = barra;
        this.posX = posX;
        this.posY = posY;
        actualizarApariencia();
    }



    public Group getApariencia()            { return apariencia; }
    public Group getDragActor()
    {
        Group group = new Group();
        actualizarApariencia(group);
        return group;
    }
    public void actualizarApariencia()
    {   actualizarApariencia(apariencia); }

    private void actualizarApariencia(Group group)
    {
        Accion accion = barra.getAccion(posX, posY);

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

    public void addSource(DragAndDrop dad)
    {
        source = new AccionSource(this, dad);
        dad.addSource(source);
    }

    public void addTarget(DragAndDrop dad, ControladorBarraAccionI controlador)
    {
        target = new AccionTarget(this, dad, controlador);
        dad.addTarget(target);
    }

    public void addDragAndDrop(DragAndDrop dad, ControladorBarraAccionI controlador)
    {
        addSource(dad);
        addTarget(dad, controlador);
    }

    public void eliminarIcono (DragAndDrop dad)
    {
        if (source != null) dad.removeSource(source);
        if (target != null) dad.removeTarget(target);
    }
}
