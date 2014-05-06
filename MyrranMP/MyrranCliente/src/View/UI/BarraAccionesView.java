package View.UI;// Created by Hanto on 06/05/2014.

import Model.Classes.Acciones.IconoInterface;
import Data.MiscData;
import Model.Classes.UI.BarraAcciones;
import Recursos.DAO.RSC;
import View.Graficos.Texto;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class BarraAccionesView extends Table
{
    private BarraAcciones barraModel;  //MODEL

    private DragAndDrop dad;

    public static class Icono
    {
        public int numIcono;
        public Group apariencia;

        public Icono(int numIcono, Group group)
        {   this.numIcono = numIcono; this.apariencia = group; }
    }

    public BarraAccionesView(BarraAcciones barraAcciones)
    {
        this.barraModel = barraAcciones;
        this.bottom().left();;
        dad = new DragAndDrop();

        dad.setDragTime(0);

        for (int i=0 ; i< barraModel.barraSpells.length; i++)
        {
            final Icono icono = new Icono(i, getApariencia(i));

            this.add(icono.apariencia).left().height(MiscData.BARRASPELLS_Alto_Casilla + 2).width(MiscData.BARRASPELLS_Ancho_Casilla + 2);
            if ((i+1)%10 == 0) this.row();

            dad.addSource(new DragAndDrop.Source(icono.apariencia)
            {
                @Override public DragAndDrop.Payload dragStart(InputEvent inputEvent, float v, float v2, int ii)
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

                @Override public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload)
                {   super.reset(source, payload); }

                @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int ii)
                {
                    Icono origen = ((Icono)payload.getObject());

                    barraModel.moverAccion(icono.numIcono, origen.numIcono);

                    setApariencia(icono);
                    setApariencia(origen);
                }
            });
        }
    }


    public void setApariencia(int i, Group group)
    {
        group.clearChildren();

        if (barraModel.barraSpells[i].accion == null)
        {
            Image casillaVacia = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_Textura_Casillero));
            casillaVacia.setColor(0, 0, 0, 0.1f);
            casillaVacia.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaVacia);
        }
        else
        {
            Image casillaIcono = new Image (((IconoInterface) barraModel.barraSpells[i].accion).getImagen());
            casillaIcono.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaIcono);
        }
        if (barraModel.barraSpells[i].keybind != null)
        {
            Texto.printTexto(String.valueOf(barraModel.barraSpells[i].keybind), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                    Color.ORANGE, Color.BLACK, 0, 20, Align.left, Align.bottom, 2, group);
        }
    }

    public void setApariencia(Icono icono)
    {   setApariencia(icono.numIcono, icono.apariencia); }

    public Group getApariencia (int i)
    {
        Group group = new Group();
        setApariencia(i, group);
        return group;
    }
}
