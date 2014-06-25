package View.Classes.UI;// Created by Hanto on 19/06/2014.

import Core.SkillStat;
import DB.RSC;
import DTO.NetDTO;
import Data.MiscData;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.CasterConTalentos;
import Interfaces.Spell.Skill;
import Interfaces.Spell.SpellI;
import View.Classes.Graficos.Texto;
import View.Classes.UI.Ventana.VentanaMoverListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;

public class SpellTooltip extends Group implements PropertyChangeListener
{
    //Model:
    private SpellI spell;
    private CasterConTalentos caster;

    //View:
    private Image background;
    private Image icono;
    private Table tabla;

    private final int PAD = 8;
    private final int ANCHO_Descripcion = 80;

    public SpellTooltip(SpellI spell, CasterConTalentos caster)
    {
        this.spell = spell;
        this.caster = caster;

        background = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_Textura_Casillero));
        background.setColor(0, 0, 0, 0.15f);
        icono = new Image(RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(spell.getID()).getIcono());
        icono.setPosition(0 - icono.getWidth(), getHeight() - icono.getHeight());
        icono.addListener(new VentanaMoverListener(icono, this));
        tabla = new Table().top().left();
        tabla.setPosition(0, 8);

        this.addActor(background);
        this.addActor(icono);
        this.addActor(tabla);

        this.recrearTabla();

        icono.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (button == Input.Buttons.RIGHT)
                {   eliminar(); }
                return true;
            }
        });

        caster.añadirObservador(this);
    }

    public void eliminar()
    {
        caster.eliminarObservador(this);
        if (this.getStage() != null) this.getStage().getRoot().removeActor(this);
    }

    public void recrearTabla()
    {
        tabla.clear();
        Texto texto;
        BitmapFont fuente = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres);
        //NOMBRE SPELL::
        texto = new Texto(spell.getNombre(), fuente, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).left().padRight(4).padLeft(4);
        tabla.row();

        this.cabecera(tabla);
        this.printSkillStats(tabla, spell.getSkillStats(), spell);

        Iterator<BDebuffI> debuffIIterator = spell.getDebuffsQueAplica();
        while (debuffIIterator.hasNext())
        {
            BDebuffI debuff = debuffIIterator.next();
            //NOMBRE DEBUFF:
            texto = new Texto(debuff.getNombre()+":", fuente, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).left().padRight(4).padLeft(4);
            tabla.row();

            this.printSkillStats(tabla, debuff.getSkillStats(), debuff);
        }

        this.setSize(tabla.getMinWidth(), tabla.getMinHeight());
        background.setBounds(0,-getHeight(), getWidth(), getHeight());
        //tabla.debug();

    }


    private void cabecera(Table tabla)
    {
        Texto texto;
        BitmapFont fuenteMini = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("10");

        texto = new Texto("Nombre", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).left().padRight(4).padLeft(4);

        texto = new Texto("Base", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("Grados", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).center().padRight(4);

        texto = new Texto("Total", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("niv", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("c", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("bono", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("mx", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        tabla.row();
    }


    private void printSkillStats(Table tabla, Iterator<SkillStat> iterator, Skill skill)
    {
        Texto texto;
        BitmapFont fuente = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres);
        BitmapFont mini = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("10");
        SkillStat skillStat;
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(symbols);

        while (iterator.hasNext())
        {
            skillStat = iterator.next();
            //NOMBRE:
            texto = new Texto(skillStat.getNombre(), fuente, Color.WHITE, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).left().width(ANCHO_Descripcion > texto.getWidth() ? ANCHO_Descripcion : texto.getWidth()).bottom().padRight(4).padLeft(4);
            //VALOR BASE:
            texto = new Texto(df.format(skillStat.getValorBase()), fuente, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom().padRight(4);
            //CASILLERO
            int numTalentos = caster.getSkillTalentos(skill.getID(), skillStat.getID());
            tabla.add(crearCasillero(skill.getID(), skillStat.getID())).left().top().padRight(4);

            //VALOR CON TALENTOS: (redondeamos a 2 decimales maximo)
            texto = new Texto(df.format(skill.getTalentedSkillStat(caster, skillStat.getID())), fuente, Color.GREEN, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight()-PAD).right().bottom().padRight(4);
            //NUMTALENTOS:
            texto = new Texto(Integer.toString(numTalentos), mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom().padRight(4);
            //COSTE TALENTO:
            texto = new Texto(skillStat.getisMejorable() ? Integer.toString(skillStat.getCosteTalento()) : "-", mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom().padRight(4).bottom();
            //BONO TALENTO:
            texto = new Texto(skillStat.getisMejorable() ? Float.toString(skillStat.getBonoTalento()) : "-", mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom().padRight(4).bottom();
            //NUMTALENTOS MAXIMOS:
            texto = new Texto(skillStat.getisMejorable() ? Integer.toString(skillStat.getTalentoMaximo()): "-", mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom().padRight(4).bottom();

            tabla.row();
        }
    }

    private Group crearCasillero(final String skillID, final int skillStatID)
    {
        Group group = new Group();

        final int numTalentos = caster.getSkillTalentos(skillID, skillStatID);

        Image fondo = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_SPELLTOOLTIP_TalentoFondo));
        group.addActor(fondo);
        group.setWidth(fondo.getWidth());
        group.setHeight(fondo.getHeight());

        Image frente = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_SPELLTOOLTIP_Talento));
        frente.setSize((fondo.getWidth() / 25) * (numTalentos > 25 ? 25 : numTalentos), fondo.getHeight());
        frente.setColor(255/255f, 180/255f, 0/255f, 0.75f);
        group.addActor(frente);

        if (numTalentos > 25)
        {
            Image frente2 = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_SPELLTOOLTIP_Talento));
            frente2.setSize((fondo.getWidth()/25)*(numTalentos > 50? 25 : numTalentos-25), fondo.getHeight());
            frente2.setColor(255, 0, 0, 0.45f);
            group.addActor(frente2);
        }

        Actor quitarTalento = new Actor();
        quitarTalento.setBounds(0, 0, fondo.getWidth()/2, fondo.getHeight());
        group.addActor(quitarTalento);

        Actor añadirTalento = new Actor();
        añadirTalento.setBounds(fondo.getWidth()/2, 0, fondo.getWidth()/2, fondo.getHeight());
        group.addActor(añadirTalento);

        quitarTalento.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                caster.setSkillTalento(skillID, skillStatID, numTalentos-1);
                return true;
            }
        });

        añadirTalento.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                caster.setSkillTalento(skillID, skillStatID, numTalentos+1);
                return true;
            }
        });

        return group;
    }

    private void modificarSkillTalento(String skillID)
    {
        synchronized (this.getStage())
        {
            if (spell.getID().equals(skillID))  { recrearTabla(); return; }
            Iterator<BDebuffI> debuffIIterator = spell.getDebuffsQueAplica();
            while (debuffIIterator.hasNext())
            {   if (debuffIIterator.next().getID().equals(skillID)) { recrearTabla(); return; }}
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof NetDTO.ModificarSkillTalentoPPC)
        {
            String skillID = ((NetDTO.ModificarSkillTalentoPPC) evt.getNewValue()).skillID;
            modificarSkillTalento(skillID);
        }
    }
}
