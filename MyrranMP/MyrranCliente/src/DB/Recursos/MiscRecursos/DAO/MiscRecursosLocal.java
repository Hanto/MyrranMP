package DB.Recursos.MiscRecursos.DAO;// Created by Hanto on 02/05/2014.

import DB.Recursos.MiscRecursos.MiscRecursosLocalDB;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class MiscRecursosLocal implements MiscRecursosDAO
{
    private Map<String, TextureRegion> listaDeTexturasMisc = MiscRecursosLocalDB.get().listaDeTexturasMisc;



    @Override public void salvarTextura(String nombreRecurso, String rutaYnombreTextura, TextureAtlas atlas)
    {   TextureRegion textura = new TextureRegion(atlas.findRegion(rutaYnombreTextura));
        listaDeTexturasMisc.put(nombreRecurso, textura);
    }

    @Override public TextureRegion cargarTextura(String nombreRecurso)
    {
        if (!listaDeTexturasMisc.containsKey(nombreRecurso))
        {   System.out.println("ERROR: Recurso Misc " + nombreRecurso + " no existe."); }
        return listaDeTexturasMisc.get(nombreRecurso);
    }
}
