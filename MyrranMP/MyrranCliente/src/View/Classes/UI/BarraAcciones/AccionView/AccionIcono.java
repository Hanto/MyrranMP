package View.Classes.UI.BarraAcciones.AccionView;// Created by Hanto on 13/05/2014.

import DB.RSC;
import Data.MiscData;
import Interfaces.UI.Acciones.AccionI;
import Interfaces.UI.BarraAcciones.ControladorBarraAccionI;
import Interfaces.UI.BarraAcciones.ListaAccionesI;
import Model.Classes.Acciones.TiposAccion.SeleccionarSpell;
import View.Classes.Graficos.Texto;
import View.Classes.UI.Icono.Icono;
import View.Classes.UI.Icono.IconoSource;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class AccionIcono extends Group implements Icono
{
    //Model:
    protected ListaAccionesI barra;
    protected int posX;
    protected int posY;

    //View:
    protected Image casillaIcono;
    protected Texto keybind;
    protected IconoSource source;
    protected AccionTarget target;

    public int getPosX()                { return posX; }
    public int getPosY()                { return posY; }
    public ListaAccionesI getBarra()    { return barra; }

    //Constructor:
    public AccionIcono(ListaAccionesI barra, int posX, int posY)
    {
        this.barra = barra;
        this.posX = posX;
        this.posY = posY;

        keybind = new Texto("", RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres), Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.top, 2);
        keybind.setPosition(0, 32+4);
        keybind.setTouchable(Touchable.disabled);
        this.addActor(keybind);

        this.addCaptureListener(new AccionTooltipListener(this));
        this.actualizarApariencia();
    }

    public void actualizarApariencia()
    {
        AccionI accion = getAccion();
        this.clearChildren();

        if (accion == null)
        {
            casillaIcono = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_Textura_Casillero));
            casillaIcono.setColor(0, 0, 0, 0.06f);
            casillaIcono.setBounds(0, 0, MiscData.ICONO_Accion_Ancho, MiscData.ICONO_Accion_Alto);
        }
        else
        {
            if (accion instanceof SeleccionarSpell) { casillaIcono = new Image(RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(accion.getID()).getIcono()); }
            else { casillaIcono = new Image(RSC.accionRecursosDAO.getAccionRecursosDAO().getAccionRecurso(accion.getID()).getTextura()); }
        }

        this.addActor(casillaIcono);
        this.setWidth(casillaIcono.getWidth());
        this.setHeight(casillaIcono.getHeight());
        this.addActor(keybind);

        casillaIcono.setTouchable(Touchable.disabled);
    }
    public AccionI getAccion()                      { return barra.getAccion(posX, posY); }
    public void setTexto(String texto)              { keybind.setTexto(texto); }
    @Override public Group getApariencia()          { return this; }
    @Override public Group getDragActor()
    {
        Group group = new Group();
        group.addActor(casillaIcono);
        group.setWidth(casillaIcono.getWidth());
        group.setHeight(casillaIcono.getHeight());
        return group;
    }

    @Override public boolean tieneDatos()
    {
        if (getAccion() != null) return true;
        else return false;
    }

    public void addDragAndDrop(DragAndDrop dad, ControladorBarraAccionI controlador)
    {
        source = new IconoSource(this, dad);
        dad.addSource(source);

        target = new AccionTarget(this, dad, controlador);
        dad.addTarget(target);
    }

    public void eliminarIcono (DragAndDrop dad)
    {
        if (source != null) dad.removeSource(source);
        if (target != null) dad.removeTarget(target);
    }
}