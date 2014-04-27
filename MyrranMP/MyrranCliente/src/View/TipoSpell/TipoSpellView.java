package View.TipoSpell;// Created by Ladrim on 26/04/2014.


import View.Graficos.Pixie;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class TipoSpellView
{
    public Map<String, TextureRegion> listaDeIconosSpell = new HashMap<>();
    public Map<String, Pixie> listaDeEfectosDeCasteo = new HashMap<>();
    public Map<String, Pixie> listaDeProyectiles = new HashMap<>();



    public Map<String, RecursosTipoSpell>listaTipoSpellRSC = new HashMap<>();

    public static class RecursosTipoSpell
    {
        public TextureRegion icono;
        public RecursosEfectos[] tiposAnimacion;

        public RecursosTipoSpell(int numEfectos)
        {   tiposAnimacion = new RecursosEfectos[numEfectos]; }
    }

    public static class RecursosEfectos
    {
        public Array<Pixie> listaDeRecursosPorTipoAnimacion = new Array<>();
    }

    public void añadirTipoSpell (String iDTipoSpell, int numTiposAnimacion)
    {
        RecursosTipoSpell recursosTipoSpell = new RecursosTipoSpell(numTiposAnimacion);
        listaTipoSpellRSC.put(iDTipoSpell, recursosTipoSpell);

        listaTipoSpellRSC.get("EditarMuro").tiposAnimacion[0].listaDeRecursosPorTipoAnimacion.get(0);
    }
}
