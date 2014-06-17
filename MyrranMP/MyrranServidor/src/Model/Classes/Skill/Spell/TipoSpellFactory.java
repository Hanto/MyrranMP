package Model.Classes.Skill.Spell;// Created by Ladrim on 20/04/2014.

import Model.Classes.Skill.Spell.TiposSpell.EditarTerreno;
import Model.Classes.Skill.Spell.TiposSpell.Heal;

public enum TipoSpellFactory
{
    EDITARTERRENO("EditarTerreno")
    {
        @Override public TipoSpell nuevo()
        {   return new EditarTerreno(); }
    },
    HEAL("Heal")
    {
        @Override public TipoSpell nuevo()
        {   return new Heal(); }
    };

    public abstract TipoSpell nuevo();

    private TipoSpellFactory(String nombre) { }
}