package Modelo.Mobiles;// Created by Hanto on 07/04/2014.


import Modelo.AbstractModel;
import DTO.MobDTO;

public class PCModel extends AbstractModel
{
    protected int connectionID;                 //ID de la conexion con el servidor
    //Posicion:
    protected Float x=0.0f;                     //Coordenadas X:
    protected Float y=0.0f;                     //Coordenadas Y:
    protected Float oldPosX;                    //Coordenadas X, de la ultima posicion X segura
    protected Float oldPosY;                    //Coordenadas Y, de la ultima posicion Y segura

    protected Integer numAnimacion = 5;

    //Velocidad y Direccion:
    protected Float velocidadMod=1.0f;          //Modificadores de Velocidad: debido a Snares, a Sprints, Roots
    protected Float velocidadMax;               //Velocidad Maxima:
    protected Float velocidad;                  //Velocidad Actual:
    protected Double direccion;                 //Direccion Actual en Radianes

    protected String nombre;
    protected Integer nivel;
    protected Integer actualHPs=2000;
    protected Integer maxHPs=2000;

    protected String spellSeleccionado = "";
    protected String terrenoSeleccionado = "";
    protected Integer capaTerrenoSeleccionada = 0;

    protected Boolean isCasteando = false;
    protected Float actualCastingTime = 0.0f;
    protected Float totalCastingTime = 0.0f;

    protected String iDraza;                         //id de la raza;

    protected Boolean accionCastear = false;
    protected Boolean accionDisparar = false;
    protected Boolean accionIrNorte = false;
    protected Boolean accionIrSur = false;
    protected Boolean accionIrEste = false;
    protected Boolean accionIrOeste = false;


    //GET:
    public Integer getConnectionID ()           { return connectionID; }
    public Float getX()                         { return x; }
    public Float getY()                         { return y; }


    public PCModel(int connectionID)
    {
        this.connectionID = connectionID;
    }

    public void setPosition(float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new MobDTO.MoverPC(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion(int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object animacionDTO = new MobDTO.CambiarAnimacionPC(numAnimacion);
            notificarActualizacion("setAnimacion", null, animacionDTO);
        }
    }

    public void eliminar()
    {
        Object eliminarDTO = new MobDTO.EliminarPC(this);
        notificarActualizacion("eliminar", null, eliminarDTO);
    }
}
