package Recursos.DAO.AccionRecursos.DB;// Created by Hanto on 07/05/2014.

import Data.Misc.MiscData;
import Recursos.Classes.AccionRecursos;
import Recursos.DAO.AccionRecursos.AccionRecursosDAO;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public class AccionRecursosLocal implements AccionRecursosDAO
{
    private Map<String, TextureRegion> listaDeTexturasAccion = AccionRecursosLocalDB.get().listaDeTexturasAcciones;
    private Map<String, AccionRecursos> listaDeAccionRecursos = AccionRecursosLocalDB.get().listaDeAccionRecursos;



    @Override public void salvarTextura(String nombreTextura, TextureRegion textura)
    {   listaDeTexturasAccion.put(nombreTextura, textura); }

    @Override public void salvarTextura(String nombreTextura, String nombreTexturaEnAtlas, TextureAtlas atlas)
    {
        TextureRegion textura = new TextureRegion(atlas.findRegion(MiscData.ATLAS_Acciones_LOC+nombreTexturaEnAtlas));
        listaDeTexturasAccion.put(nombreTextura, textura);
    }

    @Override public TextureRegion getTextura(String nombreTextura)
    {   return listaDeTexturasAccion.get(nombreTextura); }

    @Override public void salvarAccionRecurso(AccionRecursos accionRecurso)
    {   listaDeAccionRecursos.put(accionRecurso.getID(), accionRecurso); }

    @Override public AccionRecursos getAccionRecurso(String iDAccionRecurso)
    {   return listaDeAccionRecursos.get(iDAccionRecurso); }
}
