package Model.DTO;

import Model.Mobiles.Player;

/**
 * Created by Ivan Delgado Huerta on 13/04/2014.
 */
public class PlayerDTO
{
    public static class PositionPlayer
    {
        public Player player;
        public float x;
        public float y;
        public PositionPlayer(Player player, float x, float y)
        {   this.player = player; this.x = x; this.y = y; }
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
