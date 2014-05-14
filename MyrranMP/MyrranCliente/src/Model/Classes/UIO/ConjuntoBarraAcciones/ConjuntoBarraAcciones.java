package Model.Classes.UIO.ConjuntoBarraAcciones;// Created by Hanto on 08/05/2014.

import Model.Classes.AbstractModel;
import Model.Classes.Acciones.Accion;
import Model.Classes.UIO.Input.Keybinds;
import Model.DAO.DAO;
import Model.DTO.UIDTO;

import java.util.HashMap;
import java.util.Map;

public class ConjuntoBarraAcciones extends AbstractModel
{
    protected Keybinds keybinds;
    protected Map<Integer, BarraAcciones> listaDeBarraAcciones = new HashMap<>();

    public BarraAcciones getBarraAcciones (int iD)              { return listaDeBarraAcciones.get(iD); }

    public ConjuntoBarraAcciones(Keybinds keybinds)
    {   this.keybinds = keybinds; }

    public void añadirBarraAcciones (int filas, int columnas)
    {
        int iDMenor;
        for (iDMenor =0; iDMenor < listaDeBarraAcciones.size(); iDMenor++)
        {   if (!listaDeBarraAcciones.containsKey(iDMenor)) break; }


        BarraAcciones barraAcciones = new BarraAcciones(keybinds, iDMenor, filas, columnas);
        listaDeBarraAcciones.put(barraAcciones.getID(), barraAcciones);

        Object añadirBarraAccionesDTO = new UIDTO.AñadirBarraAccionesDTO(barraAcciones);
        notificarActualizacion("añadirBarraAcciones", null, añadirBarraAccionesDTO);
    }

    //Provisional:
    public void añadirAccionesEnBarra(ListaAccionesBI barra)
    {
        barra.setKeycode(0, 0, 8);
        barra.setKeycode(1, 0, 9);
        barra.setKeycode(2, 0, 10);
        barra.setKeycode(3, 0, 11);
        barra.setKeycode(4, 0, 12);
        barra.setKeycode(5, 0, 13);
        barra.setKeycode(6, 0, 14);
        barra.setKeycode(7, 0, 15);
        barra.setKeycode(8, 0, 16);

        barra.setKeycode(1, 1, 51);
        barra.setKeycode(1, 2, 47);
        barra.setKeycode(2, 2, 32);
        barra.setKeycode(0, 2, 29);

        barra.setAccion(0, 0, DAO.accionDAOFactory.getAccionDAO().getAccion("Terraformar"));
        barra.setAccion(1, 1, DAO.accionDAOFactory.getAccionDAO().getAccion("IrNorte"));
        barra.setAccion(1, 2, DAO.accionDAOFactory.getAccionDAO().getAccion("IrSur"));
        barra.setAccion(2, 2, DAO.accionDAOFactory.getAccionDAO().getAccion("IrEste"));
        barra.setAccion(0, 2, DAO.accionDAOFactory.getAccionDAO().getAccion("IrOeste"));
    }

    public void setKeycode (ListaAccionesBI barra, int posX, int posY, int keycode)
    {
        for (Map.Entry<Integer,BarraAcciones> entry : listaDeBarraAcciones.entrySet())
        {   entry.getValue().eliminarKeycode(keycode); }

        barra.setKeycode(posX, posY, keycode);
    }

    public void setAccion (ListaAccionesBI barra, int posX, int posY, Accion accion)
    {   barra.setAccion(posX, posY, accion); }

    public void eliminarAccion (ListaAccionesBI barra, int posX, int posY)
    {   barra.eliminarAccion (posX, posY); }

    public void moverAccion (ListaAccionesI barraOrigen, int posXOrigen, int posYOrigen, ListaAccionesI barraDestino, int posXDestino, int posYDestino)
    {
        Accion accionOrigen = barraOrigen.getAccion(posXOrigen, posYOrigen);
        Accion accionDestino = barraDestino.getAccion(posXDestino, posYDestino);

        if (barraOrigen instanceof ListaAccionesBI)
        {
            if (accionDestino == null) ((ListaAccionesBI)barraOrigen).eliminarAccion(posXOrigen, posYOrigen);
            else ((ListaAccionesBI)barraOrigen).setAccion(posXOrigen, posYOrigen, accionDestino);
        }

        if (barraDestino instanceof ListaAccionesBI)
        {
            if (accionOrigen == null) ((ListaAccionesBI)barraDestino).eliminarAccion(posXDestino, posYDestino);
            else ((ListaAccionesBI)barraDestino).setAccion(posXDestino, posYDestino, accionOrigen);
        }
    }
}
