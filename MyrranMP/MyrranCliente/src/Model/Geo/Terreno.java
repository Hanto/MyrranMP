package Model.Geo;// Created by Hanto on 14/04/2014.

//Clase que define los tipos de terreno
public class Terreno
{
    private Integer id;                             //ID del tipo de Terreno para poder cargar su template
    private String nombre;                          //Nombre del Terreno ("Jungla", "Camino", "Cesped"...
    private Boolean isSolido = false;               //Flag que controla si el terreno es solido o no, y por tanto atravesable por los objetos que pueblan el mundo

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
