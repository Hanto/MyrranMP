package DAO.Accion;// Created by Hanto on 06/05/2014.

import DAO.Accion.AccionDAO;
import Interfaces.UI.Acciones.AccionI;

import java.util.Map;

public class AccionLocal implements AccionDAO
{
    private Map<String, AccionI>listaDeAcciones;

    public AccionLocal(Map<String, AccionI>listaDeAcciones)
    {   this.listaDeAcciones = listaDeAcciones; }



    @Override public boolean salvarAccion(AccionI accion)
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

    @Override public AccionI getAccion(String accionID)
    {   return listaDeAcciones.get(accionID);}
}
