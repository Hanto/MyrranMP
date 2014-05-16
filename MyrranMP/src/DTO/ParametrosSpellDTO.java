package DTO;// Created by Hanto on 16/05/2014.

public class ParametrosSpellDTO
{
    public static class ParametrosEditarTerreno
    {
        public int capaTerrenoSeleccionada = 0;
        public int terrenoIDSeleccionado = 0;
        public ParametrosEditarTerreno() {}
        public ParametrosEditarTerreno(int capaTerrenoSeleccionada, int terrenoIDSeleccionado)
        {   this.capaTerrenoSeleccionada = capaTerrenoSeleccionada; this.terrenoIDSeleccionado = terrenoIDSeleccionado; }
    }
}
