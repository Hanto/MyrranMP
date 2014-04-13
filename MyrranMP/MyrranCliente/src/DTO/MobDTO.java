package DTO;// Created by Hanto on 09/04/2014.

import Modelo.Mobiles.PCModel;
import Modelo.Mobiles.PlayerModel;

public class MobDTO
{
    public static class MundoAñadirPC
    {
        public PCModel pcModel;
        public float x;
        public float y;
        public MundoAñadirPC(PCModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }

    public static class MoverPC
    {
        public PCModel pcModel;
        public float x;
        public float y;
        public MoverPC(PCModel pcModel, float x, float y)
        {   this.pcModel = pcModel; this.x = x; this.y = y; }
    }

    public static class CambiarAnimacionPC
    {
        public int numAnimacion;
        public CambiarAnimacionPC(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }

    public static class EliminarPC
    {
        public PCModel pcModel;
        public EliminarPC(PCModel pcModel)
        {   this.pcModel = pcModel; }
    }

    public static class MundoAñadirPlayer
    {
        public PlayerModel playerModel;
        public MundoAñadirPlayer (PlayerModel playerModel)
        {   this.playerModel = playerModel; }
    }

    public static class MoverPlayer
    {
        public PlayerModel playerModel;
        public float x;
        public float y;
        public MoverPlayer(PlayerModel playerModel, float x, float y)
        {   this.playerModel = playerModel; this.x = x; this.y = y; }
    }

    public static class CambiarAnimacionPlayer
    {
        public int numAnimacion;
        public CambiarAnimacionPlayer(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }
}
