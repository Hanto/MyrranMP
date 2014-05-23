package Model.Classes.Geo;// Created by Hanto on 14/04/2014.

//Clase que define los tipos de terreno
public class Terreno
{
    private short id;                               //ID del tipo de Terreno para poder cargar su template
    private String nombre;                          //Nombre del Terreno ("Jungla", "Camino", "Cesped"...
    private boolean isSolido = false;               //Flag que controla si el terreno es solido o no, y por tanto atravesable por los objetos que pueblan el mundo

    public Terreno() {}
    public Terreno(short id, String nombre, Boolean isSolido)
    {   this.id = id; this.nombre = nombre; this.isSolido = isSolido; }

    //SET:
    public void setId (short i)                     { id = i ; }
    public void setNombre (String s)                { nombre = s; }
    public void setIsSolido (boolean b)             { isSolido = b; }

    //GET:
    public short getID()                            { return id; }
    public String getNombre()                       { return nombre; }
    public boolean getIsSolido()                    { return isSolido; }
}
