package Model.Classes.Skill.Spell;// Created by Ladrim on 20/04/2014.

import Model.Classes.Skill.Spell.TiposSpell.EditarTerreno;

public enum TipoSpellFactory
{
    EDITARTERRENO("Editar Terreno")
    {
        @Override
        public TipoSpell nuevoTipoSpell()
        {   return new EditarTerreno(); }
    };

    public abstract TipoSpell nuevoTipoSpell();

    private String nombre;
    private TipoSpellFactory(String nombre) { this.nombre = nombre; }
}
