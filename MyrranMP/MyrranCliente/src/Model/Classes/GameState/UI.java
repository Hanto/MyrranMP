package Model.Classes.GameState;// Created by Hanto on 06/05/2014.

import Model.Classes.AbstractModel;
import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.BarraAcciones;
import Model.Classes.UIO.Keybinds;
import Model.Classes.UIO.PlayerEstado;
import Model.Classes.UIO.PlayerIO;
import Model.DAO.DAO;
import Model.DTO.UIDTO;
import com.badlogic.gdx.utils.Array;


public class UI extends AbstractModel
{
    public Keybinds keybinds;
    public Array<BarraAcciones>listaDeBarraAcciones = new Array<>();

    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);


    public UI (Player player)
    {   keybinds = new Keybinds(player, playerEstado); }

    public void añadirBarraAcciones(int tamaño)
    {
        BarraAcciones barraAcciones = new BarraAcciones(keybinds, tamaño);
        listaDeBarraAcciones.add(barraAcciones);

        Object añadirBarraAccionesDTO = new UIDTO.AñadirBarraAccionesDTO(barraAcciones);
        notificarActualizacion("añadirBarraAcciones", null, añadirBarraAccionesDTO);
    }

    public void añadirAccionesEnBarra(int numBarra)
    {
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
}
