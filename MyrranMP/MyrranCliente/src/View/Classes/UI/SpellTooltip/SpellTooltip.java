package View.Classes.UI.SpellTooltip;// Created by Hanto on 19/06/2014.

import DB.RSC;
import DTO.NetDTO;
import Data.MiscData;
import Interfaces.EntidadesPropiedades.CasterConTalentos;
import Interfaces.Skill.SkillPersonalizadoI;
import Interfaces.Spell.SpellPersonalizadoI;
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
    private SpellPersonalizadoI spell;
    private CasterConTalentos caster;
    private ControladorSpellTooltipI controlador;

    //View:
    private Image background;
    private Image icono;
    private Table tabla;
    private Texto talentosTotales;
    private Map<String, SkillView>listaSkills = new HashMap<>();

    private final int PAD = 8;
    private final int ANCHO_Descripcion = 80;
    private int textoSobresalePorArriba;


    public SpellTooltip(String spellID, CasterConTalentos caster, ControladorSpellTooltipI controlador)
    {
        this.caster = caster;
        this.controlador = controlador;
        this.spell = caster.getSpellPersonalizado(spellID);

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
        BitmapFont fuenteTotalTalentos = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("14");
        BitmapFont fuente = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("10");
        SkillView skillView;

        //SPELL::
        SkillPersonalizadoI spellP = spell.getCustomSpell();
        texto = new Texto(spellP.getNombre(), fuenteNombre, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
        skillView = new SkillView(spellP);
        skillView.setNombreSkill(texto);
        tabla.add(texto).left().padBottom(2);
        tabla.row();

        textoSobresalePorArriba = (int)(texto.getHeight()/2+2);
        texto.setTouchable(Touchable.disabled);

        cabecera(tabla);
        printSkillStats(tabla, skillView, spellP);


        //DEBUFFS:
        Iterator<SkillPersonalizadoI> debuffIIterator = spell.getIteratorCustomDebuffs();
        while (debuffIIterator.hasNext())
        {
            SkillPersonalizadoI debuff = debuffIIterator.next();
            texto = new Texto(debuff.getNombre()+":", fuente, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
            skillView = new SkillView(debuff);
            skillView.setNombreSkill(texto);
            tabla.add(texto).height(texto.getHeight() - PAD).left();
            tabla.row();

            printSkillStats(tabla, skillView, debuff);
        }

        //TALENTOS TOTALES:
        talentosTotales = new Texto("Rank: "+Integer.toString(spell.getCosteTotalTalentos()), fuenteTotalTalentos, new Color(170/255f, 70/255f, 255/255f, 1f), Color.BLACK, Align.right, Align.center, 1);
        this.addActor(talentosTotales);

        ajustarDimensiones();
        //tabla.debug();
    }

    private void ajustarDimensiones()
    {
        this.setSize(tabla.getMinWidth(), tabla.getMinHeight() - textoSobresalePorArriba);

        icono.setPosition(0 - icono.getWidth(), getHeight() - icono.getHeight());
        background.setBounds(0, 0, getWidth(), getHeight());
        talentosTotales.setPosition(getWidth()-6, getHeight());
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


    private void printSkillStats(Table tabla, SkillView skillView, final SkillPersonalizadoI skill)
    {
        BitmapFont normal = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres);
        BitmapFont mini = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("11");
        Texto texto;

        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();

        symbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(symbols);

        for (int statID=0; statID< skill.getNumSkillStats(); statID++)
        {
            //NOMBRE:
            texto = new Texto(skill.getNombre(statID),
                    mini, Color.WHITE, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setNombre(statID, texto);
            tabla.add(texto).height(texto.getHeight() - PAD).left().bottom();

            //VALOR BASE:
            texto = new Texto(df.format(skill.getValorBase(statID)),
                    normal, Color.ORANGE, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setValorBase(statID, texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //CASILLERO
            skillView.setCasilleroTalentos(statID, new CasilleroTalentos(controlador, skill.getID(), statID, skill.getNumTalentos(statID)));
            tabla.add(skillView.getCasilleroTalentos(statID)).left().top();

            //VALOR TOTAL: (redondeamos a 2 decimales maximo)
            texto = new Texto(df.format(skill.getValorTotal(statID)),
                    normal, Color.GREEN, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setValorTotal(statID, texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //NUMTALENTOS:
            texto = new Texto(skill.getIsMejorable(statID) ? Integer.toString(skill.getNumTalentos(statID)) : "-",
                    mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setTalentos(statID, texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //COSTE TALENTO:
            texto = new Texto(skill.getIsMejorable(statID) ? Integer.toString(skill.getCosteTalento(statID)) : "-",
                    mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setCosteTalento(statID, texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //BONO TALENTO:
            texto = new Texto(skill.getIsMejorable(statID) ? Float.toString(skill.getBonoTalento(statID)) : "-",
                    mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setBonoTalentos(statID, texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            //NUMTALENTOS MAXIMOS:
            texto = new Texto(skill.getIsMejorable(statID) ? Integer.toString(skill.getTalentoMaximo(statID)): "-",
                    mini, Color.YELLOW, Color.BLACK, Align.left, Align.bottom, 1);
            skillView.setMaxTalentos(statID, texto);
            tabla.add(texto).height(texto.getHeight() - PAD).right().bottom();

            tabla.row();
        }

        listaSkills.put(skill.getID(), skillView);
    }

    private void modificarNumTalentos(String skillID, int statID, int valor)
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
            skillView.getValorTotal(statID).setTexto(df.format(spell.getSkillPersonalizado(skillID).getValorTotal(statID)));
            talentosTotales.setTexto("Rank: "+Integer.toString(spell.getCosteTotalTalentos()));

            //Invalidate es lo que hace que la tabla recalcule las dimensiones de cada celda
            tabla.invalidate();

            ajustarDimensiones();
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof NetDTO.ModificarNumTalentosSkillPersonalizadoPPC)
        {
            String skillID = ((NetDTO.ModificarNumTalentosSkillPersonalizadoPPC) evt.getNewValue()).skillID;
            int statID = ((NetDTO.ModificarNumTalentosSkillPersonalizadoPPC) evt.getNewValue()).statID;
            int valor = ((NetDTO.ModificarNumTalentosSkillPersonalizadoPPC) evt.getNewValue()).valor;
            modificarNumTalentos(skillID, statID, valor);
        }
    }
}
