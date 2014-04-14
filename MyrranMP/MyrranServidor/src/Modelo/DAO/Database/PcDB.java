package Modelo.DAO.Database;// Created by Hanto on 13/04/2014.

import Modelo.DAO.PcDAO;
import Modelo.Mobiles.PcModel;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PcDB implements PcDAO
{
    public ArrayList<PcModel> listaPCDB = new ArrayList<PcModel>();


    public PcDB()
    {   cargarListaPCDB();}

    @Override public void añadirPC(PcModel pc)      { }
    @Override public PcModel getPC(int iD)          { return null; }
    @Override public void salvarPC()                { }
    @Override public void eliminarPC(PcModel pc)    { }

    public void salvarListaPCDB()
    {
        Kryo kryo = new Kryo();
        try
        {
            kryo.register(PcDB.class);
            kryo.register(ArrayList.class);
            Output output = new Output(new FileOutputStream("Players.bin"));
            kryo.writeObject(output, PcDB.class);

            output.close();
        }
        catch (Exception e) { System.out.println("No se ha podido salvar Players.Bin"); }
    }

    public void cargarListaPCDB()
    {
        Kryo kryo = new Kryo();

        try
        {
            kryo.register(PcDB.class);
            kryo.register(ArrayList.class);
            Input input = new Input(new FileInputStream("Players.bin"));
            this.listaPCDB = kryo.readObject(input, PcDB.class).listaPCDB;

            input.close();
        }
        catch (Exception e) { System.out.println("No se ha podido cargar Players.Bin"); }
    }
}
