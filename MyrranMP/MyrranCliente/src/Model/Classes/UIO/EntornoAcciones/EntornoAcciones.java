package Model.Classes.UIO.EntornoAcciones;// Created by Hanto on 08/05/2014.

import Model.Classes.AbstractModel;
import Model.Classes.Acciones.Accion;
import Model.Classes.UIO.Keybinds;
import Model.DAO.DAO;
import Model.DTO.UIDTO;

import java.util.HashMap;
import java.util.Map;

public class EntornoAcciones extends AbstractModel
{
    protected Keybinds keybinds;
    protected Map<Integer, BarraAcciones> listaDeBarraAcciones = new HashMap<>();

    public EntornoAcciones(Keybinds keybinds)
    {   this.keybinds = keybinds; }

    public void añadirBarraAcciones (int tamaño)
    {
        int iDMenor;
        for (iDMenor =0; iDMenor < listaDeBarraAcciones.size(); iDMenor++)
        {   if (!listaDeBarraAcciones.containsKey(iDMenor)) break; }


        BarraAcciones barraAcciones = new BarraAcciones(keybinds, tamaño, iDMenor);
        listaDeBarraAcciones.put(barraAcciones.getID(), barraAcciones);

        Object añadirBarraAccionesDTO = new UIDTO.AñadirBarraAccionesDTO(barraAcciones);
        notificarActualizacion("añadirBarraAcciones", null, añadirBarraAccionesDTO);
    }

    //Provisional:
    public void añadirAccionesEnBarra(int numBarra)
    {
        listaDeBarraAcciones.get(numBarra).setKeycode(0, 8);
        listaDeBarraAcciones.get(numBarra).setKeycode(1, 9);
        listaDeBarraAcciones.get(numBarra).setKeycode(2, 10);
        listaDeBarraAcciones.get(numBarra).setKeycode(3, 11);
        listaDeBarraAcciones.get(numBarra).setKeycode(4, 12);
        listaDeBarraAcciones.get(numBarra).setKeycode(5, 13);
        listaDeBarraAcciones.get(numBarra).setKeycode(6, 14);
        listaDeBarraAcciones.get(numBarra).setKeycode(7, 15);
        listaDeBarraAcciones.get(numBarra).setKeycode(8, 16);



        listaDeBarraAcciones.get(numBarra).setKeycode(11, 51);
        listaDeBarraAcciones.get(numBarra).setKeycode(21, 47);
        listaDeBarraAcciones.get(numBarra).setKeycode(22, 32);
        listaDeBarraAcciones.get(numBarra).setKeycode(20, 29);

        listaDeBarraAcciones.get(numBarra).setAccion(0, DAO.accionDAOFactory.getAccionDAO().getAccion("Terraformar"));
        listaDeBarraAcciones.get(numBarra).setAccion(11, DAO.accionDAOFactory.getAccionDAO().getAccion("IrNorte"));
        listaDeBarraAcciones.get(numBarra).setAccion(21, DAO.accionDAOFactory.getAccionDAO().getAccion("IrSur"));
        listaDeBarraAcciones.get(numBarra).setAccion(22, DAO.accionDAOFactory.getAccionDAO().getAccion("IrEste"));
        listaDeBarraAcciones.get(numBarra).setAccion(20, DAO.accionDAOFactory.getAccionDAO().getAccion("IrOeste"));
    }


    public  void setKeycode (int numBarra, int posicion, int keycode)
    {
        for (Map.Entry<Integer,BarraAcciones> entry : listaDeBarraAcciones.entrySet())
        { entry.getValue().removeKeycode(keycode);}

        listaDeBarraAcciones.get(numBarra).setKeycode(posicion, keycode);
    }

    public void setAccion (int numBarra, int posicion, Accion accion)
    {   listaDeBarraAcciones.get(numBarra).setAccion(posicion, accion); }

    public void removeAccion (int numBarra, int posicion)
    {   listaDeBarraAcciones.get(numBarra).removeAccion(posicion); }

    public void moverAccion (int numBarraOrigen, int posicionOrigen, int numBarraDestino, int posicionDestino)
    {   Accion accionOrigen = listaDeBarraAcciones.get(numBarraOrigen).getAccion(posicionOrigen);
        Accion accionDestino = listaDeBarraAcciones.get(numBarraDestino).getAccion(posicionDestino);

        if (accionDestino == null) removeAccion(numBarraOrigen, posicionOrigen);
        else setAccion(numBarraOrigen, posicionOrigen, accionDestino);

        if (accionOrigen == null) removeAccion(numBarraDestino, posicionDestino);
        else setAccion(numBarraDestino, posicionDestino, accionOrigen);
    }
}
