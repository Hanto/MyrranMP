package Model.Classes.Skill.BDebuff;

import Model.Classes.Skill.BDebuff.TiposBDebuff.Hot;

public enum TipoBDebuffFactory
{
    HOT("Hot")
    {
        @Override
        public TipoBDebuff nuevo()
        {   return new Hot(); }
    };


    public abstract TipoBDebuff nuevo();

    private TipoBDebuffFactory(String nombre) {}
}