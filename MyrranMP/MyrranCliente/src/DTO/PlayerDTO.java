package DTO;

import Modelo.Mobiles.PlayerModel;

/**
 * Created by Ivan Delgado Huerta on 13/04/2014.
 */
public class PlayerDTO
{
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
