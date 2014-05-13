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
    public void añadirAccionesEnBarra(int numBarra)
    {
        listaDeBarraAcciones.get(numBarra).setKeycode(0, 0, 8);
        listaDeBarraAcciones.get(numBarra).setKeycode(1, 0, 9);
        listaDeBarraAcciones.get(numBarra).setKeycode(2, 0, 10);
        listaDeBarraAcciones.get(numBarra).setKeycode(3, 0, 11);
        listaDeBarraAcciones.get(numBarra).setKeycode(4, 0, 12);
        listaDeBarraAcciones.get(numBarra).setKeycode(5, 0, 13);
        listaDeBarraAcciones.get(numBarra).setKeycode(6, 0, 14);
        listaDeBarraAcciones.get(numBarra).setKeycode(7, 0, 15);
        listaDeBarraAcciones.get(numBarra).setKeycode(8, 0, 16);



        listaDeBarraAcciones.get(numBarra).setKeycode(1,    1,  51);
        listaDeBarraAcciones.get(numBarra).setKeycode(1,    2,  47);
        listaDeBarraAcciones.get(numBarra).setKeycode(2,    2,  32);
        listaDeBarraAcciones.get(numBarra).setKeycode(0,    2,  29);

        listaDeBarraAcciones.get(numBarra).setAccion(0,     0,  DAO.accionDAOFactory.getAccionDAO().getAccion("Terraformar"));
        listaDeBarraAcciones.get(numBarra).setAccion(1,     1,  DAO.accionDAOFactory.getAccionDAO().getAccion("IrNorte"));
        listaDeBarraAcciones.get(numBarra).setAccion(1,     2,  DAO.accionDAOFactory.getAccionDAO().getAccion("IrSur"));
        listaDeBarraAcciones.get(numBarra).setAccion(2,     2,  DAO.accionDAOFactory.getAccionDAO().getAccion("IrEste"));
        listaDeBarraAcciones.get(numBarra).setAccion(0,     2,  DAO.accionDAOFactory.getAccionDAO().getAccion("IrOeste"));

        listaDeBarraAcciones.get(numBarra).añadirFila();
        listaDeBarraAcciones.get(numBarra).añadirFila();
        listaDeBarraAcciones.get(numBarra).añadirColumna();
        listaDeBarraAcciones.get(numBarra).añadirColumna();
    }


    public  void setKeycode (int numBarra, int posX, int posY, int keycode)
    {
        for (Map.Entry<Integer,BarraAcciones> entry : listaDeBarraAcciones.entrySet())
        { entry.getValue().eliminarKeycode(keycode);}

        listaDeBarraAcciones.get(numBarra).setKeycode(posX, posY, keycode);
    }
    public void setAccion (int numBarra, int posX, int posY, Accion accion)
    {   listaDeBarraAcciones.get(numBarra).setAccion(posX, posY, accion); }

    public void removeAccion (int numBarra, int posX, int posY)
    {   listaDeBarraAcciones.get(numBarra).eliminarAccion(posX, posY); }

    public void moverAccion (int numBarraOrigen, int posXOrigen, int posYOrigen, int numBarraDestino, int posXDestino, int posYDestino)
    {   Accion accionOrigen = listaDeBarraAcciones.get(numBarraOrigen).getAccion(posXOrigen, posYOrigen);
        Accion accionDestino = listaDeBarraAcciones.get(numBarraDestino).getAccion(posXDestino, posYDestino);

        if (accionDestino == null) removeAccion(numBarraOrigen, posXOrigen, posYOrigen);
        else setAccion(numBarraOrigen, posXOrigen, posYOrigen, accionDestino);

        if (accionOrigen == null) removeAccion(numBarraDestino, posXDestino, posYDestino);
        else setAccion(numBarraDestino, posXDestino, posYDestino, accionOrigen);
    }



    public void añadirColumna (int numBarra)
    {   listaDeBarraAcciones.get(numBarra).añadirColumna(); }
    public void eliminarColuna (int numBarra)
    {   listaDeBarraAcciones.get(numBarra).eliminarColumna(); }
    public void añadirFila (int numBarra)
    {   listaDeBarraAcciones.get(numBarra).añadirFila(); }
    public void eliminarFila (int numBarra)
    {   listaDeBarraAcciones.get(numBarra).eliminarFila(); }
}
