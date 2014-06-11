package DTO;

import Comun.SkillStat;

public class GameDataDTO
{
    public enum TipoSpell { EDITARTERRENO }

    public static class TerrenoRSCDTO
    {
        public String iDTerreno;
        public String nombreTextura;
        public TerrenoRSCDTO (String nombreTextura)
        {   this.iDTerreno = nombreTextura; this.nombreTextura = nombreTextura; }
    }

    public static class TerrenoDTO
    {
        public short id;
        public String nombre;
        public boolean isSolido;
        public String nombreTextura;
        public TerrenoDTO(int id, String nombre, boolean isSolido, String nombreTextura)
        {   this.id = (short)id; this.nombre = nombre; this.isSolido = isSolido; this.nombreTextura = nombreTextura; }
    }

    public static class SpellRSCIconosDTO
    {
        public String iDIcono;
        public String nombreTextura;
        public SpellRSCIconosDTO(String nombreTextura)
        {   this.iDIcono = nombreTextura; this.nombreTextura = nombreTextura; }
    }

    public static class SpellRSCCasteoDTO
    {
        public String iDCasteo;
        public String nombreTextura;
        public SpellRSCCasteoDTO(String nombreTextura)
        {   this.iDCasteo = nombreTextura; this.nombreTextura = nombreTextura; }
    }

    public static class SpellRSCProyectilDTO
    {
        public String iDCasteo;
        public String nombreTextura;
        public SpellRSCProyectilDTO(String nombreTextura)
        {   this.iDCasteo = nombreTextura; this.nombreTextura = nombreTextura; }
    }

    public static class SpellDTO
    {
        public TipoSpell tipoSpell;
        public String id;
        public String nombre;
        public String descripcion;
        public SkillStat [] skillStats;
        public String iDIcono;
        public String[] listaIDAnimaciones;
        public SpellDTO(int numSkillStats, int numAnimaciones)
        {
            skillStats = new SkillStat[numSkillStats];
            for (int i=0; i< skillStats.length; i++)
            {   skillStats[i] = new SkillStat(); }
            listaIDAnimaciones = new String[numAnimaciones];
        }
    }

    public static class PixiePCRazaDTO
    {
        public String iDRaza;
        public PixiePCRazaDTO(String iDRaza)
        {   this.iDRaza = iDRaza; }
    }

    public static class PixiePCCuerpoDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCCuerpoDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCYelmoDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCYelmoDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCCabezaDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCCabezaDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCBotasDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCBotasDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCGuantesDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCGuantesDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCHombrerasDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCHombrerasDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCPantalonesDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCPantalonesDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCPetoDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCPetoDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCCapasFrontalesDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCCapasFrontalesDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }

    public static class PixiePCCapasTraserasDTO
    {
        public String iDRaza;
        public String nombreTextura;
        public PixiePCCapasTraserasDTO(String iDRaza, String nombreTextura)
        {   this.iDRaza = iDRaza; this.nombreTextura = nombreTextura; }
    }
}
