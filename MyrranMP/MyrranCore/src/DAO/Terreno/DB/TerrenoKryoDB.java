package DAO.Terreno.DB;// Created by Hanto on 15/04/2014.

import Interfaces.Geo.TerrenoI;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class TerrenoKryoDB
{
    private static class Singleton      { private static final TerrenoKryoDB get = new TerrenoKryoDB(); }
    public static TerrenoKryoDB get()   { return Singleton.get; }

    public Map<Short, TerrenoI> listaDeTerrenos = new HashMap<>();

    public TerrenoKryoDB()
    {
        cargarDatos();
    }

    public void salvarDatos()
    {
        Kryo kryo = new Kryo();
        try
        {
            Output output = new Output(new FileOutputStream("Terrenos.bin"));
            kryo.writeObject(output, this.listaDeTerrenos);
            output.close();
        }
        catch (Exception e) { System.out.println("No se ha podido salvar Terrenos.Bin"); }
    }

    public void cargarDatos()
    {
        Kryo kryo = new Kryo();

        try
        {
            Input input = new Input(new FileInputStream("Terrenos.bin"));
            listaDeTerrenos = kryo.readObject(input, this.listaDeTerrenos.getClass());
            input.close();
            System.out.println("Terrenos Cargados");
        }
        catch (Exception e)
        {
            System.out.println("No se ha podido cargar Terrenos.Bin. "+e);
        }
    }
}
