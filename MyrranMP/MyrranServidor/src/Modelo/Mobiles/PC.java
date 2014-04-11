package Modelo.Mobiles;// Created by Hanto on 07/04/2014.

public class PC extends AbstractModel implements PCModel
{
    protected int connectionID;                 //ID de la conexion con el servidor
    //Posicion:
    protected float x=0;                        //Coordenadas X:
    protected float y=0;                        //Coordenadas Y:
    protected float oldPosX;                    //Coordenadas X, de la ultima posicion X segura
    protected float oldPosY;                    //Coordenadas Y, de la ultima posicion Y segura

    protected int numAnimacion = 5;

    //Velocidad y Direccion:
    protected double velocidadMod=1;            //Modificadores de Velocidad: debido a Snares, a Sprints, Roots
    protected double velocidadMax;              //Velocidad Maxima:
    protected double velocidad;                 //Velocidad Actual:
    protected double direccion;                 //Direccion Actual en Radianes

    protected String nombre;
    protected int nivel;
    protected int actualHPs=2000;
    protected int maxHPs=2000;

    protected String spellSeleccionado = "";
    protected String terrenoSeleccionado = "";
    protected int capaTerrenoSeleccionada = 0;

    protected boolean isCasteando = false;
    protected float actualCastingTime = 0;
    protected float totalCastingTime = 0;

    protected int raza;                         //id de la raza;

    protected boolean accionCastear = false;
    protected boolean accionDisparar = false;
    protected boolean accionIrNorte = false;
    protected boolean accionIrSur = false;
    protected boolean accionIrEste = false;
    protected boolean accionIrOeste = false;


    //GET:
    @Override public int getConnectionID ()     { return connectionID; }
    @Override public float getX()               { return x; }
    @Override public float getY()               { return y; }


    public PC(int connectionID)
    {
        this.connectionID = connectionID;
    }

    public void setPosition(float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new DTO.PCPosition(this, x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion(int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object animacionDTO = new DTO.PCAnimacion(numAnimacion);
            notificarActualizacion("setAnimacion", null, animacionDTO);
        }
    }

    public void eliminar()
    {
        Object eliminarDTO = new DTO.PCEliminar(this);
        notificarActualizacion("eliminar", null, eliminarDTO);
    }
}
