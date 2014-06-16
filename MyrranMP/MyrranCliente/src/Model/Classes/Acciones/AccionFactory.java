package Model.Classes.Acciones;// Created by Hanto on 13/06/2014.

import Interfaces.Spell.SpellI;
import Model.Classes.Acciones.TiposAccion.*;

public class AccionFactory
{
    public enum accionComando
    {

        IRESTE("IrEste")
        {
            @Override public Accion nuevo()
            {   return new IrEste(); }
        },
        IRNORTE("IrNorte")
        {
            @Override public Accion nuevo()
            {   return new IrNorte(); }

        },
        IROESTE("IrOeste")
        {
            @Override public Accion nuevo()
            {   return new IrOeste(); }
        },
        IRSUR("IrSur")
        {
            @Override public Accion nuevo()
            {   return new IrSur(); }
        };

        public abstract Accion nuevo();
        private accionComando(String nombre){}
    }

    public enum accionSpell
    {
        SELECCIONARSPELL("SeleccionarSpell")
        {   @Override public Accion nuevo(SpellI spell)
            {   return new SeleccionarSpell(spell); }

            @Override public Accion nuevo(String idSpell)
            {   return new SeleccionarSpell(idSpell); }
        };

        public abstract Accion nuevo(SpellI spell);
        public abstract Accion nuevo(String idSpell);
        private accionSpell(String nombre) {}
    }
}
