package Modelo.Mobiles;// Created by Hanto on 08/04/2014.

import DTOs.ActorDTO;
import Modelo.DTO.ClientDTO;
import Models.AbstractModel;
import Models.PCModel;

public class PC extends AbstractModel implements PCModel
{
    protected int connectionID;
    //Posicion:
    protected float x;                      //Coordenadas X:
    protected float y;                      //Coordenadas Y:

    protected int numAnimacion = 5;

    @Override public int getConnectionID()  { return connectionID; }
    @Override public float getX()           { return x; }
    @Override public float getY()           { return y; }


    public PC (int connectionID)
    {
        this.connectionID = connectionID;
    }

    public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new ActorDTO.MoverPC(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion (int numAnimacion)
    {
        this.numAnimacion = numAnimacion;
        Object AnimacionDTO = new ClientDTO.CambiarAnimacionPlayer(numAnimacion);
        notificarActualizacion("setAnimacion", null, AnimacionDTO);
        System.out.println("model animacion a ["+numAnimacion+"]");
    }

    public void eliminar()
    {
        Object eliminarPC = new ActorDTO.EliminarPC(this);
        notificarActualizacion("eliminarPC", null, eliminarPC);
    }
}
