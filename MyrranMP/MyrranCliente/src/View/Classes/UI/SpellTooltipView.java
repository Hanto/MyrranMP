package View.Classes.UI;// Created by Hanto on 19/06/2014.

import Core.SkillStat;
import DB.RSC;
import Data.MiscData;
import Interfaces.BDebuff.BDebuffI;
import Interfaces.Spell.SpellI;
import View.Classes.Graficos.Texto;
import View.Classes.UI.Ventana.VentanaMoverListener;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import java.util.Iterator;

public class SpellTooltipView extends Group
{
    private SpellI spell;

    private Image background;
    private Image icono;
    private Table tabla;

    private final int PAD = 8;
    private final int ANCHO_Descripcion = 80;

    public SpellTooltipView(SpellI spell)
    {
        this.spell = spell;

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
                {   getParent().removeActor(SpellTooltipView.this);}
                return true;
            }
        });
    }

    public void recrearTabla()
    {
        tabla.clear();
        Texto texto;
        BitmapFont fuente = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres);
        //NOMBRE SPELL::
        texto = new Texto(spell.getNombre(), fuente, Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
        tabla.add(texto).left().padRight(4).padLeft(4);
        tabla.row();

        this.cabecera(tabla);
        this.printSkillStats(tabla, spell.skillStats());

        Iterator<BDebuffI> debuffIIterator = spell.getDebuffsQueAplica();
        while (debuffIIterator.hasNext())
        {
            BDebuffI debuff = debuffIIterator.next();
            //NOMBRE DEBUFF:
            texto = new Texto(debuff.getNombre()+":", fuente, Color.GREEN, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).left().padRight(4).padLeft(4);
            tabla.row();

            this.printSkillStats(tabla, debuff.skillStats());
        }

        this.setBounds(0, 0, tabla.getMinWidth(), tabla.getMinHeight());
        background.setBounds(0,-getHeight(), getWidth(), getHeight());
        //tabla.debug();
    }


    private void cabecera(Table tabla)
    {
        Texto texto;
        BitmapFont fuenteMini = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente("10");

        texto = new Texto("Nombre", fuenteMini, Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).left().padRight(4).padLeft(4);

        texto = new Texto("Valor", fuenteMini, Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("c", fuenteMini, Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("bono", fuenteMini, Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        texto = new Texto("m", fuenteMini, Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
        tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);

        tabla.row();
    }

    private void printSkillStats(Table tabla, SkillStat[] skillStats)
    {
        Texto texto;
        BitmapFont fuente = RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres);

        for (SkillStat skillStat: skillStats)
        {   //NOMBRE:
            texto = new Texto(skillStat.getNombre(), fuente, Color.WHITE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).left().width(ANCHO_Descripcion > texto.getWidth() ? ANCHO_Descripcion : texto.getWidth()).padRight(4).padLeft(4);
            //VALOR:
            texto = new Texto(Float.toString(skillStat.getValorBase()), fuente, Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight() - PAD).right().padRight(4);
            //COSTE TALENTO:
            texto = new Texto(skillStat.getisMejorable() ? Integer.toString(skillStat.getCosteTalento()) : "-", fuente, Color.YELLOW, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight()-PAD).right().padRight(4);
            //BONO TALENTO:
            texto = new Texto(skillStat.getisMejorable() ? Float.toString(skillStat.getBonoTalento()) : "-", fuente, Color.YELLOW, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight()-PAD).right().padRight(4);
            //NUMTALENTOS MAXIMOS:
            texto = new Texto(skillStat.getisMejorable() ? Integer.toString(skillStat.getTalentoMaximo()): "-", fuente, Color.YELLOW, Color.BLACK, 0, 0, Align.left, Align.bottom, 1);
            tabla.add(texto).height(texto.getHeight()-PAD).right().padRight(4);

            tabla.row();
        }
    }
}
