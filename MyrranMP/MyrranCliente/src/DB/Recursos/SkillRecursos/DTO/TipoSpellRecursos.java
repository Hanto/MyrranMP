package DB.Recursos.SkillRecursos.DTO;// Created by Hanto on 30/04/2014.

import View.Classes.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class TipoSpellRecursos
{
    private String id;
    private TextureRegion icono;
    private Array<Pixie>[] tiposAnimacion;

    public String getID()                                                       { return id; }
    public TextureRegion getIcono()                                             { return icono; }
    public Pixie getAnimacion(int tipoAnimacion, int numAnimacion)              { return tiposAnimacion[tipoAnimacion].get(numAnimacion); }


    public void setID(String id)                                                { this.id = id; }
    public void setIcono(TextureRegion icono)                                   { this.icono = icono; }
    public void setAnimacion(int tipoAnimacion, int numAnimacion, Pixie pixie)  { tiposAnimacion[tipoAnimacion].set(numAnimacion, pixie); }

    public TipoSpellRecursos(String iDTipoSpell, int numTiposAnimacion)
    {
        id = iDTipoSpell;

        tiposAnimacion = new Array[numTiposAnimacion];
        for (int i=0;i<tiposAnimacion.length;i++)
        {   tiposAnimacion[i] = new Array<>();}
    }
}
