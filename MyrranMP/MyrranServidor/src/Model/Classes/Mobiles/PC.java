package Model.Classes.Mobiles;// Created by Hanto on 07/04/2014.


import Data.MiscData;
import Interfaces.Caster;
import Interfaces.MapaI;
import Interfaces.MobPC;
import Interfaces.Vulnerable;
import Model.AbstractModel;
import Model.DAO.DAO;
import Model.DTO.PcDTO;
import Model.Classes.Geo.Mapa;
import Model.Classes.Skill.Spell.Spell;

public class PC extends AbstractModel implements MobPC, Caster, Vulnerable
{
    protected Integer connectionID;                             //ID de la conexion con el servidor
    protected MapaI mapaI;                                      //mapaI al que pertecene el Player

    protected Float x=0.0f;                                     //Coordenadas X:
    protected Float y=0.0f;                                     //Coordenadas Y:
    protected Float oldPosX;                                    //Coordenadas X, de la ultima posicion X segura
    protected Float oldPosY;                                    //Coordenadas Y, de la ultima posicion Y segura

    protected Integer numAnimacion = 5;

    protected Float velocidadMod=1.0f;                          //Modificadores de Velocidad: debido a Snares, a Sprints, Roots
    protected Float velocidadMax;                               //Velocidad Maxima:
    protected Float velocidad;                                  //Velocidad Actual:
    protected Double direccion;                                 //Direccion Actual en Radianes

    protected String nombre = "Hanto";
    protected Integer nivel = 1;

    protected Float actualHPs=2000f;
    protected Float maxHPs=2000f;

    protected Boolean castear = false;
    protected Integer targetX = 0;
    protected Integer targetY = 0;
    protected Float actualCastingTime = 0.0f;
    protected Float totalCastingTime = 0.0f;

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
    @Override public float getVelocidad()                       { return velocidad; }
    @Override public double getDireccion()                      { return direccion; }
    @Override public void setVelocidaMod(float velocidadMod)    { this.velocidadMod = velocidadMod; }
    @Override public void setVelocidadMax(float velocidadMax)   { this.velocidadMax = velocidadMax; }
    @Override public void setVelocidad(float velocidad)         { this.velocidad = velocidad; }
    @Override public void setDireccion(double direccion)        { this.direccion = direccion; }

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
