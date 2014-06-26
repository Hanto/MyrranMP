package Model.Classes.Skill.BDebuff;

import Model.Classes.Skill.BDebuff.TiposBDebuff.Bomba;
import Model.Classes.Skill.BDebuff.TiposBDebuff.Hot;

public enum TipoBDebuffFactory
{
    HOT("Hot")
    {
        @Override
        public TipoBDebuff nuevo()
        {   return new Hot(); }
    },
    BOMBA("Bomba")
    {
        @Override
        public TipoBDebuff nuevo()
        {   return new Bomba(); }
    };


    public abstract TipoBDebuff nuevo();

    private TipoBDebuffFactory(String nombre) {}
}