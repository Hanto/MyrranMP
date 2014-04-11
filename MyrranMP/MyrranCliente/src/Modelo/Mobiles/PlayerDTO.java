package Modelo.Mobiles;// Created by Hanto on 11/04/2014.

public class PlayerDTO
{
    public static class MundoAñadirPlayer
    {
        public PlayerModel player;
        public MundoAñadirPlayer (PlayerModel player)
        {   this.player = player; }
    }

    public static class PlayerPosition
    {
        public PlayerModel player;
        public float x;
        public float y;
        public PlayerPosition(PlayerModel player, float x, float y)
        {   this.player = player; this.x = x; this.y = y; }
    }

    public static class PlayerAnimacion
    {
        public int numAnimacion;
        public PlayerAnimacion (int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }
}
