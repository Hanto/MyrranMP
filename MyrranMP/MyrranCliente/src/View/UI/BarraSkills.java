package View.UI;// Created by Hanto on 02/05/2014.

import Data.MiscData;
import Model.Classes.Skill.Spell.Spell;
import Recursos.DAO.RSC;
import View.Graficos.Texto;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;


public class BarraSkills extends Group
{
    private Table tablaSpells;
    private Casilla[] barraSpells;
    private DragAndDrop dad;

    private int numFilas;
    private int numColumnas;
    private boolean rebindearSkills = false;

    public static class Casilla
    {
        public String spellID;
        public String keybind;
        public int keycode;
        public Group apariencia = new Group();
    }

    public BarraSkills(int filas, int columnas)
    {
        tablaSpells = new Table().bottom().left();
        barraSpells = new Casilla[filas*columnas];
        dad = new DragAndDrop();

        dad.setDragTime(0);
        numFilas = filas;
        numColumnas = columnas;


        for (int i=0; i< barraSpells.length; i++)
        {
            final Casilla casilla = new Casilla();

            casilla.spellID = "";
            if (i<9) { casilla.keybind = String.valueOf(i+1); casilla.keycode = i+8; }

            setApariencia(casilla);

            tablaSpells.add(casilla.apariencia).left().height(MiscData.BARRASPELLS_Alto_Casilla + 2).width(MiscData.BARRASPELLS_Ancho_Casilla+2);
            if ((i+1)%numColumnas == 0) tablaSpells.row();

            dad.addSource (new DragAndDrop.Source(casilla.apariencia)
            {
                 @Override public DragAndDrop.Payload dragStart(InputEvent inputEvent, float v, float v2, int i)
                 {
                     if (casilla.spellID.length() > 0)
                     {
                         DragAndDrop.Payload payload = new DragAndDrop.Payload();
                         payload.setDragActor(getApariencia(casilla));
                         payload.setObject(casilla);
                         return payload;
                     }
                     else return null;
                 }
            });

            dad.addTarget(new DragAndDrop.Target(casilla.apariencia)
            {
                @Override public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
                {   return true; }

                @Override public void reset (DragAndDrop.Source source, DragAndDrop.Payload payload)
                {   super.reset(source, payload); }

                @Override public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float v, float v2, int i)
                {
                    Casilla origen = ((Casilla)payload.getObject());
                    String destinoSpellID = casilla.spellID;

                    casilla.spellID = origen.spellID;
                    origen.spellID = destinoSpellID;

                    setApariencia(casilla);
                    setApariencia(origen);
                }
            });
            this.barraSpells[i] = casilla;
        }

        this.addActor(tablaSpells);
        this.setWidth(numColumnas*(MiscData.BARRASPELLS_Ancho_Casilla+2));
        this.setHeight(numFilas*(MiscData.BARRASPELLS_Alto_Casilla+2));
    }

    public void setApariencia (Casilla casilla, Group group)
    {
        group.clearChildren();

        if (casilla.spellID.length() <=0)
        {
            Image casillaVacia = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_Textura_Casillero));
            casillaVacia.setColor(0, 0, 0, 0.1f);
            casillaVacia.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaVacia);
        }
        else
        {
            Image casillaIcono = new Image(RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(casilla.spellID).getIcono());
            casillaIcono.setBounds(0,0,MiscData.BARRASPELLS_Ancho_Casilla, MiscData.BARRASPELLS_Alto_Casilla);
            group.addActor(casillaIcono);
        }

        if (casilla.keybind != null)
        {
            Texto.printTexto(String.valueOf(casilla.keybind), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                             Color.ORANGE, Color.BLACK, 0, 20, Align.left, Align.bottom, 2, group);
        }
    }

    public void setApariencia (Casilla casilla)
    {   setApariencia (casilla, casilla.apariencia); }

    public Group getApariencia (Casilla casilla)
    {
        Group group = new Group();
        setApariencia(casilla, group);
        return group;
    }

    public void setSkill (int slot, Spell spell)
    {
        barraSpells[slot].spellID = spell.getID();
        setApariencia(barraSpells[slot]);
    }
}
