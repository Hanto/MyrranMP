package View.Classes.UI.BarraAcciones.AccionIcono;// Created by Hanto on 13/05/2014.

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
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class AccionIcono extends Actor implements Icono
{
    //Model:
    protected ListaAccionesI barra;
    protected ControladorBarraAccionI controlador;
    protected int posX;
    protected int posY;

    //View:
    protected TextureRegion casillaIcono;
    protected Texto keybind;
    protected Actor tooltip;
    protected IconoSource source;
    protected AccionTarget target;

    //GET:
    public int getPosX()                            { return posX; }
    public int getPosY()                            { return posY; }
    public ListaAccionesI getBarra()                { return barra; }
    public AccionI getAccion()                      { return barra.getAccion(posX, posY); }
    public ControladorBarraAccionI getControlador() { return controlador; }
    @Override public Actor getApariencia()          { return this; }
    @Override public Actor getDragActor()           { return new Image(casillaIcono);}

    //SET:
    public void setTooltip(Actor tooltip)           { this.tooltip = tooltip; }
    public void setTexto(String texto)              { this.keybind.setTexto(texto); }

    //Constructor:
    public AccionIcono(ListaAccionesI barra, ControladorBarraAccionI controlador, int posX, int posY)
    {
        this.barra = barra;
        this.controlador = controlador;
        this.posX = posX;
        this.posY = posY;

        keybind = new Texto("", RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres), Color.ORANGE, Color.BLACK, Align.left, Align.top, 2);
        keybind.setTouchable(Touchable.disabled);

        this.addCaptureListener(new AccionTooltipListener(this));
        this.setBounds(0, 0, MiscData.ICONO_Accion_Ancho, MiscData.ICONO_Accion_Alto);
        this.actualizarApariencia();
    }

    public void actualizarApariencia()
    {
        AccionI accion = getAccion();

        if (accion == null)
        {   casillaIcono = RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_Textura_Casillero); }
        else
        {
            if (accion instanceof SeleccionarSpell) { casillaIcono = RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(accion.getID()).getIcono(); }
            else { casillaIcono = RSC.accionRecursosDAO.getAccionRecursosDAO().getAccionRecurso(accion.getID()).getTextura(); }
        }
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

    @Override public void draw (Batch batch, float alpha)
    {   //Posicion Elementos:
        keybind.setPosition(getX(), getY() + 32 -4);
        if (tooltip != null) { tooltip.setPosition(getX(), getY() + 32 +8); }

        //Dibujado Elementos:
        batch.setColor(this.getColor());
        batch.draw(casillaIcono, getX(), getY());
        keybind.draw(batch, alpha);
        if (tooltip != null)
            tooltip.draw(batch, alpha);
    }
}