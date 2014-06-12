package Datos.TerrenoRecursos.DTO;// Created by Hanto on 30/04/2014.

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TerrenoRecursos
{
    private Integer id;
    private TextureRegion textura;

    public int getID()                              { return id; }
    public TextureRegion getTextura()               { return textura; }

    public void setID(int id)                       { this.id = id; }
    public void setTextura (TextureRegion textura)  { this.textura = textura; }

    public TerrenoRecursos(int ID, TextureRegion textura)
    {   this.id = ID; this.textura = textura; }
}
