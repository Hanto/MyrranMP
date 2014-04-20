package Model.Skill.Spell;// Created by Ladrim on 20/04/2014.

import Model.Skill.Spell.TiposSpell.EditarTerreno;

public enum TipoSpellFactory
{
    EDITARTERRENO("Editar Terreno")
    {
        @Override
        public TipoSpell nuevo()
        {   return new EditarTerreno(); }
    };

    public abstract TipoSpell nuevo();

    private String nombre;
    private TipoSpellFactory(String nombre) { this.nombre = nombre; }
}