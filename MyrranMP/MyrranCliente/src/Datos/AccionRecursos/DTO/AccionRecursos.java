package Datos.AccionRecursos.DTO;// Created by Hanto on 07/05/2014.

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AccionRecursos
{
    private String id;
    private TextureRegion textura;

    public String getID()                           { return id; }
    public TextureRegion getTextura()               { return textura; }

    public void setID(String id)                    { this.id = id; }
    public void setTextura (TextureRegion textura)  { this.textura = textura; }

    public AccionRecursos(String ID, TextureRegion textura)
    {   this.id = ID; this.textura = textura; }
}
