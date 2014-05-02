package Recursos.DAO.MiscRecursos.DB;// Created by Hanto on 02/05/2014.

import Recursos.DAO.MiscRecursos.MiscRecursosDAO;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class MiscRecursosLocal implements MiscRecursosDAO
{
    private Map<String, TextureRegion>listaDeTexturas = MiscRecursosLocalDB.get().listaDeTexturas;



    @Override public void salvarTextura(String nombreRecurso, String rutaYnombreTextura, TextureAtlas atlas)
    {   TextureRegion textura = new TextureRegion(atlas.findRegion(rutaYnombreTextura));
        listaDeTexturas.put(nombreRecurso, textura);
    }

    @Override public TextureRegion cargarTextura(String nombreRecurso)
    {   return listaDeTexturas.get(nombreRecurso); }
}
