package Modelo.Mobiles;// Created by Hanto on 10/04/2014.

public class Player extends AbstractModel implements PlayerModel
{
    protected int connectionID;
    //Posicion:
    protected float x;                      //Coordenadas X:
    protected float y;                      //Coordenadas Y:



    @Override public int getConnectionID()  { return connectionID; }
    @Override public float getX()           { return x; }
    @Override public float getY()           { return y; }


    public Player (int connectionID)
    {
        this.connectionID = connectionID;
    }

    public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new DTO.PlayerPosition(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }
}
