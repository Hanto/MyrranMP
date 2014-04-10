package Modelo.Mobiles;// Created by Hanto on 09/04/2014.

public class DTO
{
    public static class MundoAñadirPC
    {
        public PCModel pc;
        public float x;
        public float y;
        public MundoAñadirPC(PCModel pc, float x, float y)
        {   this.pc = pc; this.x = x; this.y = y; }
    }

    /*public static class MundoEliminarPC
    {
        public PCModel pc;
        public MundoEliminarPC(PCModel pc)
        {   this.pc = pc; }
    }*/

    public static class MundoAñadirPlayer
    {
        public PlayerModel player;
        public MundoAñadirPlayer (PlayerModel player)
        {   this.player = player; }
    }

    /*public static class MundoEliminarPlayer
    {
        public PlayerModel player;
        public MundoEliminarPlayer (PlayerModel player)
        {   this.player = player; }
    }*/

    public static class PCPosition
    {
        public PCModel pc;
        public float x;
        public float y;
        public PCPosition(PCModel pc, float x, float y)
        {   this.pc = pc; this.x = x; this.y = y; }
    }

    public static class PCEliminar
    {
        public PCModel pc;
        public PCEliminar(PCModel pc)
        {   this.pc = pc; }
    }

    public static class PlayerPosition
    {
        public PlayerModel player;
        public float x;
        public float y;
        public PlayerPosition(PlayerModel player, float x, float y)
        {   this.player = player; this.x = x; this.y = y; }
    }

    public static class ProyectilPosition
    {
        public ProyectilModel proyectil;
        public float x;
        public float y;
        public ProyectilPosition(ProyectilModel proyectil, float x, float y)
        {   this.proyectil = proyectil; this.x = x; this.y = y; }
    }
}
