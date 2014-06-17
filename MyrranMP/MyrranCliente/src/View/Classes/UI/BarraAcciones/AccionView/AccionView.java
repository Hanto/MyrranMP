package View.Classes.UI.BarraAcciones.AccionView;// Created by Hanto on 13/05/2014.

import Data.MiscData;
import Interfaces.UI.Acciones.AccionI;
import Interfaces.UI.BarraAcciones.ControladorBarraAccionI;
import Interfaces.UI.BarraAcciones.ListaAccionesI;
import DB.RSC;
import Model.Classes.Acciones.TiposAccion.SeleccionarSpell;
import View.Classes.UI.Icono.Icono;
import View.Classes.UI.Icono.IconoSource;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class AccionView implements Icono
{
    //Model:
    protected ListaAccionesI barra;
    protected int posX;
    protected int posY;

    //View:
    protected Group apariencia = new Group();
    protected IconoSource source;
    protected AccionTarget target;

    public int getPosX()                { return posX; }
    public int getPosY()                { return posY; }
    public ListaAccionesI getBarra()    { return barra; }

    //Constructor:
    public AccionView(ListaAccionesI barra, int posX, int posY)
    {
        this.barra = barra;
        this.posX = posX;
        this.posY = posY;
        actualizarApariencia();
    }


    @Override public Group getApariencia()          { return apariencia; }
    public void actualizarApariencia()    { actualizarApariencia(apariencia); }

    private void actualizarApariencia(Group group)
    {
        AccionI accion = barra.getAccion(posX, posY);

        group.clearChildren();
        if (accion == null)
        {
            Image casillaVacia = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_Textura_Casillero));
            casillaVacia.setColor(0, 0, 0, 0.06f);
            casillaVacia.setBounds(0,0,MiscData.ICONO_Accion_Ancho, MiscData.ICONO_Accion_Alto);
            group.addActor(casillaVacia);
            group.setWidth(casillaVacia.getWidth());
            group.setHeight(casillaVacia.getHeight());
        }
        else
        {
            Image casillaIcono;
            if (accion instanceof SeleccionarSpell)
            {   casillaIcono = new Image (RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(accion.getID()).getIcono()); }
            else
            {   casillaIcono = new Image (RSC.accionRecursosDAO.getAccionRecursosDAO().getAccionRecurso(accion.getID()).getTextura()); }

            casillaIcono.setBounds(0,0,MiscData.ICONO_Accion_Ancho, MiscData.ICONO_Accion_Alto);
            group.addActor(casillaIcono);
            group.setWidth(casillaIcono.getWidth());
            group.setHeight(casillaIcono.getHeight());
        }
    }


    @Override public Group getDragActor()
    {
        Group group = new Group();
        actualizarApariencia(group);
        return group;
    }

    @Override public boolean tieneDatos()
    {
        if (barra.getAccion(posX, posY) != null) return true;
        else return false;
    }

    public void addSource(DragAndDrop dad)
    {
        source = new IconoSource(this, dad);
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
