package Model.DAO.Accion.DB;// Created by Hanto on 06/05/2014.

import Model.Classes.Acciones.Accion;
import Model.DAO.Accion.AccionDAO;

import java.util.Map;

public class AccionLocal implements AccionDAO
{
    private Map<String, Accion>listaDeAcciones = AccionLocalDB.get().listaDeAcciones;


    @Override public boolean salvarAccion(Accion accion)
    {
        if (listaDeAcciones.containsKey(accion.getID()))
        {   System.out.println("ERROR: ya existe una accion con este ID["+accion.getID()+"]"); return false; }
        else
        {   listaDeAcciones.put(accion.getID(), accion); return true; }
    }

    @Override public void eliminarAccion(String accionID)
    {
        if (listaDeAcciones.containsKey(accionID))
            listaDeAcciones.remove(accionID);
    }

    @Override public Accion getAccion(String accionID)
    {   return listaDeAcciones.get(accionID);}
}
