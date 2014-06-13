package Model.Classes.Acciones;// Created by Hanto on 13/06/2014.

import Interfaces.Spell.SpellI;
import Model.Classes.Acciones.TiposAccion.*;

public enum AccionFactory
{
    IRESTE("IrEste")
    {
        @Override public Accion nuevo()                 { return new IrEste(); }
        @Override public Accion nuevo(Object parametro) { return new IrEste(); }
    },
    IRNORTE("IrNorte")
    {
        @Override public Accion nuevo()                 { return new IrNorte(); }
        @Override public Accion nuevo(Object parametro) { return new IrNorte(); }

    },
    IROESTE("IrOeste")
    {
        @Override public Accion nuevo()                 { return new IrOeste(); }
        @Override public Accion nuevo(Object parametro) { return new IrOeste(); }
    },
    IRSUR("IrSur")
    {
        @Override public Accion nuevo()                 { return new IrSur(); }
        @Override public Accion nuevo(Object parametro) { return new IrSur(); }
    },
    SELECCIONARSPELL("SeleccionarSpell")
    {
        @Override public Accion nuevo()                 { return null; }
        @Override public Accion nuevo(Object parametro) { return new SeleccionarSpell((SpellI)parametro); }
    };

    public abstract Accion nuevo(Object parametro);
    public abstract Accion nuevo();

    private AccionFactory(String nombre) { }
}
