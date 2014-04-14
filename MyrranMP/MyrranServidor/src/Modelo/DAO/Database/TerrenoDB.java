package Modelo.DAO.Database;// Created by Hanto on 14/04/2014.

import Modelo.DAO.TerrenoDAO;
import Modelo.Geo.Terreno;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class TerrenoDB implements TerrenoDAO
{
    private Map<Integer, Terreno> listaDeterrenos = new HashMap<>();



    public TerrenoDB ()
    {   cargarTerrenoDB(); }

    @Override public Terreno getTerreno(int terrenoID)
    {   return listaDeterrenos.get(terrenoID); }

    @Override public void añadirTerreno(Terreno terreno)
    {
        int iDMenor;
        for (iDMenor=0; iDMenor<listaDeterrenos.size(); iDMenor++)
        {   if (!listaDeterrenos.containsKey(iDMenor)) break; }

        terreno.setId(iDMenor);
        listaDeterrenos.put(terreno.getID(), terreno);
        salvarTerrenoDB();
    }

    @Override public void salvarTerreno(Terreno terreno)
    {
        listaDeterrenos.put(terreno.getID(), terreno);
        salvarTerrenoDB();
    }

    @Override public void eliminarTerreno(int terrenoID)
    {
        listaDeterrenos.remove(terrenoID);
        salvarTerrenoDB();
    }

    private void salvarTerrenoDB ()
    {
        Kryo kryo = new Kryo();
        try
        {
            Output output = new Output(new FileOutputStream("Terrenos.bin"));
            kryo.writeObject(output, this.listaDeterrenos);
            output.close();
        }
        catch (Exception e) { System.out.println("No se ha podido salvar Terrenos.Bin"); }
    }

    private void cargarTerrenoDB()
    {
        Kryo kryo = new Kryo();

        try
        {
            Input input = new Input(new FileInputStream("Terrenos.bin"));
            listaDeterrenos = kryo.readObject(input, this.listaDeterrenos.getClass());
            input.close();
        }
        catch (Exception e)
        {
            System.out.println("No se ha podido cargar Terrenos.Bin. "+e);
        }
    }

}
