package Modelo.Geo;// Created by Hanto on 14/04/2014.

import java.io.Serializable;

//Clase que define los tipos de terreno
public class Terreno implements Serializable
{
    private int id;                              //ID del tipo de Terreno para poder cargar su template
    private String nombre;                          //Nombre del Terreno ("Jungla", "Camino", "Cesped"...
    private Boolean isSolido = false;               //Flag que controla si el terreno es solido o no, y por tanto atravesable por los objetos que pueblan el mundo

    //SET:
    public void setId (int i)                       { id = i ; }
    public void setNombre (String s)                { nombre = s; }
    public void setIsSolido (boolean b)             { isSolido = b; }

    //GET:
    public int getID()                              { return id; }
    public String getNombre()                       { return nombre; }
    public boolean getIsSolido()                    { return isSolido; }
}
