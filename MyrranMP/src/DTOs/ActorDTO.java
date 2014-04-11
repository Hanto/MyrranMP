package DTOs;// Created by Hanto on 09/04/2014.

import Models.PCModel;
import Models.ProyectilModel;

public class ActorDTO
{
    public static class MundoAñadirPC
    {
        public PCModel pc;
        public float x;
        public float y;
        public MundoAñadirPC(PCModel pc, float x, float y)
        {   this.pc = pc; this.x = x; this.y = y; }
    }

    public static class MoverPC
    {
        public PCModel pc;
        public float x;
        public float y;
        public MoverPC(PCModel pc, float x, float y)
        {   this.pc = pc; this.x = x; this.y = y; }
    }

    public static class CambiarAnimacionPC
    {
        public int numAnimacion;
        public CambiarAnimacionPC(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public PCModel pc;
        public EliminarPC(PCModel pc)
        {   this.pc = pc; }
    }

    public static class MoverProyectil
    {
        public ProyectilModel proyectil;
        public float x;
        public float y;
        public MoverProyectil(ProyectilModel proyectil, float x, float y)
        {   this.proyectil = proyectil; this.x = x; this.y = y; }
    }
}
