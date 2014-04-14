package Modelo.DTO;

import Modelo.Mobiles.PlayerModel;

/**
 * Created by Ivan Delgado Huerta on 13/04/2014.
 */
public class PlayerDTO
{
    public static class PositionPlayer
    {
        public PlayerModel playerModel;
        public float x;
        public float y;
        public PositionPlayer(PlayerModel playerModel, float x, float y)
        {   this.playerModel = playerModel; this.x = x; this.y = y; }
    }

    public static class AnimacionPlayer
    {
        public int numAnimacion;
        public AnimacionPlayer(int numAnimacion)
        {   this.numAnimacion = numAnimacion; }
    }

    public static class NombrePlayer
    {
        public String nombre;
        public NombrePlayer(String nombre)
        {   this.nombre = nombre; }
    }

    public static class NivelPlayer
    {
        public int nivel;
        public NivelPlayer(int nivel)
        {   this.nivel = nivel; }
    }

    public static class ActualHPsPlayer
    {
        public float actualHPsPlayer;
        public ActualHPsPlayer(float hps)
        {   actualHPsPlayer = hps; }
    }

    public static class MaxHPs
    {
        public float maxHPs;
        public MaxHPs(float mHPs)
        {   maxHPs = mHPs; }
    }
}
