package Recursos.DAO.FuentesRecursos.DB;// Created by Hanto on 02/05/2014.

import Data.MiscData;
import Recursos.DAO.FuentesRecursos.FuentesRecursosDAO;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Map;

public class FuentesRecursosLocal implements FuentesRecursosDAO
{
    private Map<String, BitmapFont>listaDeFuentes = FuentesRecursosLocalDB.get().listaDeFuentes;

    @Override public void salvarFuente(String nombreFuente, String nombreTextura, TextureAtlas atlas)
    {
        BitmapFont fuente = new BitmapFont(Gdx.files.internal(MiscData.ATLAS_Fuentes_LOC +nombreTextura), false);
        listaDeFuentes.put(nombreFuente, fuente);
    }

    @Override
    public BitmapFont getFuente(String nombreFuente)
    {   return listaDeFuentes.get(nombreFuente); }
}
