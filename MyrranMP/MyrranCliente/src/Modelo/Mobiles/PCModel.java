package Modelo.Mobiles;// Created by Hanto on 08/04/2014.

import Modelo.AbstractModel;
import DTO.MobDTO;

public class PCModel extends AbstractModel
{
    protected Integer connectionID;
    //Posicion:
    protected Float x;                      //Coordenadas X:
    protected Float y;                      //Coordenadas Y:
    protected int numAnimacion = 5;



    public int getConnectionID()            { return connectionID; }
    public float getX()                     { return x; }
    public float getY()                     { return y; }


    public PCModel(int connectionID)
    {
        this.connectionID = connectionID;
    }

    public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new MobDTO.MoverPC(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion (int numAnimacion)
    {
        this.numAnimacion = numAnimacion;
        Object AnimacionDTO = new MobDTO.CambiarAnimacionPlayer(numAnimacion);
        notificarActualizacion("setAnimacion", null, AnimacionDTO);
        System.out.println("model animacion a ["+numAnimacion+"]");
    }

    public void eliminar()
    {
        Object eliminarPC = new MobDTO.EliminarPC(this);
        notificarActualizacion("eliminarPC", null, eliminarPC);
    }
}
