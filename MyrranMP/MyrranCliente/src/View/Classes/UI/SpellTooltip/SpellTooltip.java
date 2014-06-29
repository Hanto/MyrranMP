package View.Classes.UI.SpellTooltip;// Created by Hanto on 19/06/2014.

import Core.SkillStat;
import DB.RSC;
import DTO.NetDTO;
import Data.MiscData;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.EntidadesPropiedades.CasterConTalentos;
import Interfaces.Spell.SkillI;
import Interfaces.Spell.SpellI;
import Interfaces.UI.BarraAcciones.ControladorSpellTooltipI;
import View.Classes.Graficos.Texto;
import View.Classes.UI.Ventana.VentanaMoverListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SpellTooltip extends Group implements PropertyChangeListener
{
    //Model:
    private SpellI spell;
    private CasterConTalentos caster;
    private ControladorSpellTooltipI controlador;

    //View:
    private Image background;
    private Image icono;
    private Table tabla;
    private Map<String, SkillView>listaSkills = new HashMap<>();

    private final int PAD = 8;
    private final int ANCHO_Descripcion = 80;
    private int textoSobresalePorArriba;


    public SpellTooltip(SpellI spell, CasterConTalentos caster, ControladorSpellTooltipI controlador)
    {
        this.spell = spell;
        this.caster = caster;
        this.controlador = controlador;

        background = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura("Casillero2"));
        background.setColor(1f,1f,1f,0.55f);
        icono = new Image(RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(spell.getID()).getIcono());
        icono.addListener(new VentanaMoverListener(icono, this));
        tabla = new Table().bottom().left();

        this.addActor(background);
        this.addActor(icono);
        this.addActor(tabla);

        icono.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   if (button == Input.Buttons.RIGHT)  dispose();  return true; }
        });

        this.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   SpellTooltip.this.toFront(); return false; }
        });

        recrearTabla();

        caster.añadirObservador(this);
    }

    public void dispose()
    {
        caster.eliminarObservador(this);
        if (this.getStage() != null) this.getStage().getRoot().removeActor(this);
    }

    public void recrearTabla()
    {
        tabla.clear();
        tabla.padLeft(4).padRight(4).padBottom(4);
        tabla.defaults().padRight(2).padLeft(2);

        Texto texto;
        BitmapFont fuenteNombre = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("20");
        BitmapFont fuente = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("10");
        SkillView skillView = new SkillView(spell.getNumSkillStats());

        //SPELL::
        texto = new Texto(spell.getNombre(), fuenteNombre, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        skillView.setNombreSkill(texto);
        textoSobresalePorArriba = (int)(texto.getHeight()/2+2);
        texto.setTouchable(Touchable.disabled);
        tabla.add(texto).left().padBottom(2);
        tabla.row();

        cabecera(tabla);
        printSkillStats(tabla, skillView, spell);


        //DEBUFFS:
        Iterator<BDebuffI> debuffIIterator = spell.getDebuffsQueAplica();
        while (debuffIIterator.hasNext())
        {
            BDebuffI debuff = debuffIIterator.next();
            skillView = new SkillView(debuff.getNumSkillStats());
            texto = new Texto(debuff.getNombre()+":", fuente, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setNombreSkill(texto);
            tabla.add(texto).height(texto.getHeight() - PAD).left();
            tabla.row();

            printSkillStats(tabla, skillView, debuff);
        }

        this.setSize(tabla.getMinWidth(), tabla.getMinHeight() - textoSobresalePorArriba);
        background.setBounds(0, 0, getWidth(), getHeight());
        icono.setPosition(0 - icono.getWidth(), getHeight() - icono.getHeight());
        //tabla.debug();
    }


    private void cabecera(Table tabla)
    {
        Texto texto;
        BitmapFont fuenteMini = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("10");

        texto = new Texto("Nombre", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).left().width(ANCHO_Descripcion > texto.getWidth() ? ANCHO_Descripcion : texto.getWidth());

        texto = new Texto("Base", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right();

        texto = new Texto("Grados", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).center();

        texto = new Texto("Total", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right();

        texto = new Texto("niv", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right();

        texto = new Texto("c", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right();

        texto = new Texto("bono", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right();

        texto = new Texto("mx", fuenteMini, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right();

        tabla.row();
    }


    private void printSkillStats(Table tabla, SkillView skillView, SkillI skill)
    {
        BitmapFont fuente = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres);
        BitmapFont mini = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("11");
        Texto texto;

        Iterator<SkillStat> iterator = skill.getSkillStats();
        SkillStat skillStat;
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();

        symbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(symbols);

        while (iterator.hasNext())
        {
            skillStat = iterator.next();

            //NOMBRE:
            texto = new Texto(skillStat.getNombre(), mini,
                    Color.WHITE, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setNombre(skillStat.getID(), texto);
            tabla.add(texto).height(texto.getHeight() - PAD).left().bottom();

            //VALOR BASE:
            texto = new Texto(df.format(skillStat.getValorBase()), fuente,
                    Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setValorBase(skillStat.getID(), texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //CASILLERO
            int numTalentos = caster.getSkillTalentos(skill.getID(), skillStat.getID());
            skillView.setCasilleroTalentos(skillStat.getID(), new CasilleroTalentos(controlador, skill.getID(), skillStat.getID(), numTalentos));
            tabla.add(skillView.getCasilleroTalentos(skillStat.getID())).left().top();

            //VALOR CON TALENTOS: (redondeamos a 2 decimales maximo)
            texto = new Texto(df.format(skill.getTalentedSkillStat(caster, skillStat.getID())), fuente,
                    Color.GREEN, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setValorTotal(skillStat.getID(), texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //NUMTALENTOS:
            texto = new Texto(skillStat.getisMejorable() ? Integer.toString(numTalentos) : "-", mini,
                    Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setTalentos(skillStat.getID(), texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //COSTE TALENTO:
            texto = new Texto(skillStat.getisMejorable() ? Integer.toString(skillStat.getCosteTalento()) : "-", mini,
                    Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setCosteTalento(skillStat.getID(), texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //BONO TALENTO:
            texto = new Texto(skillStat.getisMejorable() ? Float.toString(skillStat.getBonoTalento()) : "-", mini,
                    Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setBonoTalentos(skillStat.getID(), texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //NUMTALENTOS MAXIMOS:
            texto = new Texto(skillStat.getisMejorable() ? Integer.toString(skillStat.getTalentoMaximo()): "-", mini,
                    Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setMaxTalentos(skillStat.getID(), texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            tabla.row();
        }

        listaSkills.put(skill.getID(), skillView);
    }

    private void modificarSkillTalento(String skillID, int statID, int valor)
    {
        SkillView skillView = listaSkills.get(skillID);
        if (skillView != null)
        {
            DecimalFormat df = new DecimalFormat("0.00");
            DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();

            symbols.setDecimalSeparator('.');
            df.setDecimalFormatSymbols(symbols);

            skillView.getTalentos(statID).setTexto(Integer.toString(valor));
            skillView.getCasilleroTalentos(statID).setNumTalentos(valor);

            skillView.getValorTotal(statID).setTexto(df.format(spell.getSkill(skillID).getTalentedSkillStat(caster, statID)));
            //Invalidate es lo que hace que la tabla recalcule las dimensiones de cada celda
            tabla.invalidate();

            this.setSize(tabla.getMinWidth(), tabla.getMinHeight() - textoSobresalePorArriba);
            background.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof NetDTO.ModificarSkillTalentoPPC)
        {
            String skillID = ((NetDTO.ModificarSkillTalentoPPC) evt.getNewValue()).skillID;
            int statID = ((NetDTO.ModificarSkillTalentoPPC) evt.getNewValue()).statID;
            int valor = ((NetDTO.ModificarSkillTalentoPPC) evt.getNewValue()).valor;
            modificarSkillTalento(skillID, statID, valor);
        }
    }
}
