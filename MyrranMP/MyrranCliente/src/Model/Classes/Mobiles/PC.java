package Model.Classes.Mobiles;// Created by Hanto on 08/04/2014.

import DTO.NetDTO;
import Interfaces.AbstractModel;
import Model.DTO.PlayerDTO;

public class PC extends AbstractModel
{
    protected Integer connectionID;
    //Posicion:
    protected Float x;                      //Coordenadas X:
    protected Float y;                      //Coordenadas Y:
    protected int numAnimacion = 5;


    public int getConnectionID()            { return connectionID; }
    public int getNumAnimacion()            { return numAnimacion; }
    public float getX()                     { return x; }
    public float getY()                     { return y; }


    public PC(int connectionID)
    {
        this.connectionID = connectionID;
    }

    public void setPosition (float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new NetDTO.CambiarPosicionPC(connectionID, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion (int numAnimacion)
    {
        this.numAnimacion = numAnimacion;
        Object AnimacionDTO = new PlayerDTO.AnimacionPlayer(numAnimacion);
        notificarActualizacion("setTipoAnimacion", null, AnimacionDTO);
        System.out.println("model animacion a ["+numAnimacion+"]");
    }

    public void eliminar()
    {
        Object eliminarPC = new NetDTO.EliminarPC(connectionID);
        notificarActualizacion("eliminarPC", null, eliminarPC);
    }
}
