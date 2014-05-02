package Model.Classes.Geo;// Created by Hanto on 14/04/2014.

import java.io.Serializable;

//Clase que define los tipos de terreno
public class Terreno implements Serializable
{
    private Integer id;                             //ID del tipo de SetTerreno para poder cargar su template
    private String nombre;                          //Nombre del SetTerreno ("Jungla", "Camino", "Cesped"...
    private Boolean isSolido = false;               //Flag que controla si el terreno es solido o no, y por tanto atravesable por los objetos que pueblan el mapaI

    public Terreno() {}
    public Terreno(int id, String nombre, Boolean isSolido)
    {   this.id = id; this.nombre = nombre; this.isSolido = isSolido; }

    //SET:
    public void setId (Integer i)                   { id = i ; }
    public void setNombre (String s)                { nombre = s; }
    public void setIsSolido (Boolean b)             { isSolido = b; }

    //GET:
    public Integer getID()                          { return id; }
    public String getNombre()                       { return nombre; }
    public Boolean getIsSolido()                    { return isSolido; }
}
