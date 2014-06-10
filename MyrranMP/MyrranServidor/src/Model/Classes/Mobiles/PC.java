package Model.Classes.Mobiles;// Created by Hanto on 07/04/2014.


import DTO.NetDTO;
import Data.MiscData;
import Interfaces.Entidades.Caster;
import Interfaces.Geo.MapaI;
import Interfaces.Entidades.MobPC;
import Interfaces.Entidades.Vulnerable;
import Interfaces.AbstractModel;
import Model.Classes.Geo.Mapa;
import Model.Classes.Skill.Spell.Spell;
import Model.DAO.DAO;

public class PC extends AbstractModel implements MobPC, Caster, Vulnerable
{
    protected int connectionID;                                 //ID de la conexion con el servidor
    protected MapaI mapaI;                                      //mapaI al que pertecene el Player

    protected float x=0.0f;                                     //Coordenadas X:
    protected float y=0.0f;                                     //Coordenadas Y:
    protected float oldPosX;                                    //Coordenadas X, de la ultima posicion X segura
    protected float oldPosY;                                    //Coordenadas Y, de la ultima posicion Y segura

    protected int numAnimacion = 5;

    protected float velocidadMod=1.0f;                          //Modificadores de Velocidad: debido a Snares, a Sprints, Roots
    protected float velocidadMax;                               //Velocidad Maxima:
    protected double direccion;                                 //Direccion Actual en Radianes

    protected String nombre = "Hanto";
    protected int nivel = 1;

    protected float actualHPs=2000f;
    protected float maxHPs=2000f;

    protected boolean castear = false;
    protected int targetX = 0;
    protected int targetY = 0;
    protected float actualCastingTime = 0.0f;
    protected float totalCastingTime = 0.0f;

    protected String spellIDSeleccionado = null;
    protected Object parametrosSpell;

    protected String iDraza;                                    //id de la raza;


    //GET:
    @Override public int getConnectionID ()                     { return connectionID; }
    @Override public String getNombre()                         { return nombre; }
    @Override public int getNivel()                             { return nivel; }

    //VULNERABLE:
    @Override public float getActualHPs()                       { return actualHPs; }
    @Override public float getMaxHPs()                          { return maxHPs; }
    @Override public void setActualHPs(float HPs)               { actualHPs = HPs; }
    @Override public void setMaxHPs(float HPs)                  { maxHPs = HPs; }

    //CASTER:
    @Override public MapaI getMapa()                            { return mapaI; }
    @Override public boolean isCasteando()                      { if (actualCastingTime >0) return true; else return false; }
    @Override public float getActualCastingTime()               { return actualCastingTime; }
    @Override public float getTotalCastingTime()                { return totalCastingTime; }
    @Override public String getSpellIDSeleccionado()            { return spellIDSeleccionado; }
    @Override public Object getParametrosSpell()                { return parametrosSpell; }
    @Override public void setParametrosSpell(Object parametros) { parametrosSpell = parametros; }
    @Override public void setTotalCastingTime(float castingTime){ actualCastingTime = 0.01f; totalCastingTime = castingTime;}
    @Override public void setSpellIDSeleccionado(String spellID){ spellIDSeleccionado = spellID; }
    @Override public void setCastear (boolean castear, int targetX, int targetY)
    {
        this.castear = castear;
        this.targetX = targetX;
        this.targetY = targetY;
        if (castear) castear();
    }

    //MOB:
    @Override public int getNumAnimacion()                      { return numAnimacion; }
    @Override public float getX()                               { return x; }
    @Override public float getY()                               { return y; }
    @Override public float getVelocidadMod()                    { return velocidadMod; }
    @Override public float getVelocidadMax()                    { return velocidadMax; }
    @Override public double getDireccion()                      { return direccion; }
    @Override public void setVelocidaMod(float velocidadMod)    { this.velocidadMod = velocidadMod; }
    @Override public void setVelocidadMax(float velocidadMax)   { this.velocidadMax = velocidadMax; }
    @Override public void setDireccion(double direccion)        { this.direccion = direccion; }

    public float getCenterX()                                   { return x+24; }
    public float getCenterY()                                   { return y+24;}

    public PC(int connectionID, Mapa mapa)
    {
        this.connectionID = connectionID;
        this.mapaI = mapa;
    }

    public void setPosition(float x, float y)
    {
        this.x = x; this.y = y;
        Object posicionDTO = new NetDTO.CambiarPosicionPC(this);
        notificarActualizacion("setPosition", null, posicionDTO);
    }

    public void setAnimacion(int numAnimacion)
    {
        if (this.numAnimacion != numAnimacion)
        {
            this.numAnimacion = numAnimacion;
            Object animacionDTO = new NetDTO.CambiarAnimacionPC(this);
            notificarActualizacion("setAnimacion", null, animacionDTO);
        }
    }

    public void eliminar()
    {
        Object eliminarDTO = new NetDTO.EliminarPC(this);
        notificarActualizacion("eliminar", null, eliminarDTO);
    }

    private void actualizarCastingTime(float delta)
    {
        if (isCasteando())
        {
            actualCastingTime += delta;
            if (actualCastingTime >= totalCastingTime)
            {
                actualCastingTime = 0f;
                totalCastingTime = 0f;
            }
            //NOTIFICAR ACTUALIZACION CASTING TIME
        }
    }

    private void castear()
    {
        if (!isCasteando())
        {
            Spell spell = DAO.spellDAOFactory.getSpellDAO().getSpell(spellIDSeleccionado);
            if (spell != null)
            {
                spell.castear(this, targetX, targetY);
                //actualCastingTime += 0.01f;
            }
        }
    }

    public void actualizar()
    {
        actualizarCastingTime(MiscData.SERVIDOR_Delta_Time);
        if (castear) castear();
    }
}
