package Modelo.DTO;// Created by Hanto on 11/04/2014.

import Modelo.Mobiles.PlayerModel;

public class ClienteDTO
{
    public static class MundoAñadirPlayer
    {
        public PlayerModel player;
        public MundoAñadirPlayer (PlayerModel player)
        {   this.player = player; }
    }

    public static class MoverPlayer
    {
        public PlayerModel player;
        public float x;
        public float y;
        public MoverPlayer(PlayerModel player, float x, float y)
        {   this.player = player; this.x = x; this.y = y; }
    }

    public static class CambiarAnimacionPlayer
    {
        public int numAnimacion;
        public CambiarAnimacionPlayer(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }
}
