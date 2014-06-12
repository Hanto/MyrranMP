package Model.Classes.Skill.Spell;// Created by Ladrim on 20/04/2014.

import Model.Classes.Skill.Spell.TiposSpell.EditarTerreno;

public enum TipoSpellFactory
{
    EDITARTERRENO("EditarTerreno")
    {
        @Override
        public TipoSpell nuevoTipoSpell()
        {   return new EditarTerreno(); }
    };

    public abstract TipoSpell nuevoTipoSpell();

    private TipoSpellFactory(String nombre) { }
}