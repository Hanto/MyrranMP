package Model.DTO;

/**
 * Created by Ivan Delgado Huerta on 13/04/2014.
 */
public class PlayerDTO
{
    public static class NombrePlayer
    {
        public String nombre;
        public NombrePlayer() {}
        public NombrePlayer(String nombre)
        {   this.nombre = nombre; }
    }

    public static class NivelPlayer
    {
        public int nivel;
        public NivelPlayer() {}
        public NivelPlayer(int nivel)
        {   this.nivel = nivel; }
    }

    public static class MaxHPs
    {
        public float maxHPs;
        public MaxHPs () {}
        public MaxHPs(float mHPs)
        {   maxHPs = mHPs; }
    }

    public static class SetSpellIDSeleccionado
    {
        public String spellID;
        public SetSpellIDSeleccionado() {}
        public SetSpellIDSeleccionado(String spellID)
        {   this.spellID = spellID; }
    }

    public static class SetParametrosSpell
    {   public SetParametrosSpell() {} }
}
