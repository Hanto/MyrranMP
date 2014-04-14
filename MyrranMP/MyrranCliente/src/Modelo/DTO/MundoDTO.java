package Modelo.DTO;// Created by Hanto on 14/04/2014.

import Modelo.Mobiles.PCModel;
import Modelo.Mobiles.PlayerModel;

public class MundoDTO
{
    public static class AñadirPlayer
    {
        public PlayerModel playerModel;
        public AñadirPlayer(PlayerModel playerModel)
        {   this.playerModel = playerModel; }
    }

    public static class AñadirPC
    {
        public PCModel pcModel;
        public float x;
        public float y;
        public AñadirPC(PCModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }
}
