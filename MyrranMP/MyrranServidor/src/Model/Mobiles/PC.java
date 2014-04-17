package Model.Mobiles;// Created by Hanto on 07/04/2014.


import Interfaces.Caster;
import Model.AbstractModel;
import Model.DTO.PcDTO;
import Model.Geo.Mapa;
import Interfaces.MapaI;

public class PC extends AbstractModel implements Caster
{
    protected int connectionID;                                 //ID de la conexion con el servidor
    protected MapaI mapaI;                                        //mapaI al que pertecene el Player
    //Posicion:
    protected Float x=0.0f;                                     //Coordenadas X:
    protected Float y=0.0f;                                     //Coordenadas Y:
    protected Float oldPosX;                                    //Coordenadas X, de la ultima posicion X segura
    protected Float oldPosY;                                    //Coordenadas Y, de la ultima posicion Y segura

    protected Integer numAnimacion = 5;

    //Velocidad y Direccion:
    protected Float velocidadMod=1.0f;                          //Modificadores de Velocidad: debido a Snares, a Sprints, Roots
    protected Float velocidadMax;                               //Velocidad Maxima:
    protected Float velocidad;                                  //Velocidad Actual:
    protected Double direccion;                                 //Direccion Actual en Radianes

    protected String nombre = "Hanto";
    protected Integer nivel = 1;
    protected Float actualHPs=2000f;
    protected Float maxHPs=2000f;

    protected String spellSeleccionado = "";
    protected String terrenoSeleccionado = "";
    protected Integer capaTerrenoSeleccionada = 0;

    protected Boolean isCasteando = false;
    protected Float actualCastingTime = 0.0f;
    protected Float totalCastingTime = 0.0f;

    protected String iDraza;                                    //id de la raza;

    //GET:
    public Integer getConnectionID ()                           { return connectionID; }
    public Float getX()                                         { return x; }
    public Float getY()                                         { return y; }
    public String getNombre()                                   { return nombre; }
    public Integer getNivel()                                   { return nivel; }
    public Float getActualHPs()                                 { return actualHPs; }
    public Float getMaxHPs()                                    { return maxHPs; }

    //CASTER:                                                   (getters y setters de los tiempos de casteo)
    @Override public MapaI getMapa()                             { return mapaI; }
    @Override public boolean isCasteando()                      { return isCasteando; }
    @Override public void setIsCasteando(boolean b)             { isCasteando = b; }
    @Override public float getActualCastingTime()               { return actualCastingTime; }
    @Override public float getTotalCastingTime()                { return totalCastingTime; }
    @Override public void setActualCastingTime(float f)         { actualCastingTime = f;}
    @Override public void setTotalCastingTime(float castingTime){ actualCastingTime = 0f; totalCastingTime = castingTime;}


    public PC(int connectionID, Mapa mapa)
    {
        this.connectionID = connectionID;
        this.mapaI = mapa;
    }

    public void setPosition(float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new PcDTO.PositionPC(x, y);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion(int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object animacionDTO = new PcDTO.AnimacionPC(numAnimacion);
            notificarActualizacion("setAnimacion", null, animacionDTO);
        }
    }

    public void eliminar()
    {
        Object eliminarDTO = new PcDTO.EliminarPC(this);
        notificarActualizacion("eliminar", null, eliminarDTO);
    }
}
