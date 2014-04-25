package Data;

import Data.Spell.SkillStat;

public class GameDataDTO
{
    public enum TipoSpell { EDITARTERRENO }

    public static class TerrenoDTO
    {
        public int id;
        public String nombre;
        public boolean isSolido;
        public String nombreTextura;
        public TerrenoDTO(int id, String nombre, boolean isSolido, String nombreTextura)
        {   this.id = id; this.nombre = nombre; this.isSolido = isSolido; this.nombreTextura = nombreTextura; }
    }

    public static class SpellDTO
    {
        public TipoSpell tipoSpell;
        public String id;
        public String nombre;
        public String descripcion;
        public SkillStat [] skillStats;
        public SpellDTO(int numSkillStats)
        {
            skillStats = new SkillStat[numSkillStats];
            for (int i=0; i< skillStats.length; i++)
            {   skillStats[i] = new SkillStat(); }
        }
    }
}
