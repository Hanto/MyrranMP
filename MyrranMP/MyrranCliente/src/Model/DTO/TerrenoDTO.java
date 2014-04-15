package Model.DTO;// Created by Hanto on 15/04/2014.

public class TerrenoDTO
{
    public static class Adyacencias
    {
        public String iDTerreno;

        public boolean NOizquierda = false;
        public boolean NOdiagonal = false;
        public boolean NOarriba = false;

        public boolean NEarriba = false;
        public boolean NEdiagonal = false;
        public boolean NEderecha = false;

        public boolean SEderecha = false;
        public boolean SEdiagonal = false;
        public boolean SEabajo = false;

        public boolean SOabajo = false;
        public boolean SOdiagonal = false;
        public boolean SOizquierda = false;
        public Adyacencias() {}
    }
}
