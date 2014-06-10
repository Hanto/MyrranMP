package Model.Classes.Geo;// Created by Hanto on 14/04/2014.

import Interfaces.Geo.TerrenoI;

//Clase que define los tipos de terreno
public class Terreno implements TerrenoI
{
    private short id;                               //ID del tipo de Terreno para poder cargar su template
    private String nombre;                          //Nombre del Terreno ("Jungla", "Camino", "Cesped"...
    private boolean isSolido = false;               //Flag que controla si el terreno es solido o no, y por tanto atravesable por los objetos que pueblan el mundo

    public Terreno() {}
    public Terreno(short id, String nombre, Boolean isSolido)
    {   this.id = id; this.nombre = nombre; this.isSolido = isSolido; }

    //SET:
    @Override public void setId (short i)           { id = i ; }
    @Override public void setNombre (String s)      { nombre = s; }
    @Override public void setIsSolido (boolean b)   { isSolido = b; }

    //GET:
    @Override public short getID()                  { return id; }
    @Override public String getNombre()             { return nombre; }
    @Override public boolean getIsSolido()          { return isSolido; }
}
