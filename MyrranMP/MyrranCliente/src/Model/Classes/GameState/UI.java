package Model.Classes.GameState;// Created by Hanto on 06/05/2014.

import Model.Classes.UIO.PlayerEstado;
import Model.Classes.UIO.PlayerIO;
import Model.Classes.Mobiles.Player;
import Model.Classes.UIO.BarraAcciones;
import Model.Classes.UIO.Keybinds;
import Model.DAO.DAO;
import com.badlogic.gdx.utils.Array;

public class UI
{
    public Keybinds keybinds;
    public Array<BarraAcciones>listaDeBarraAcciones = new Array<>();

    protected PlayerIO playerInput = new PlayerIO();
    protected PlayerIO playerOutput = new PlayerIO();
    protected PlayerEstado playerEstado = new PlayerEstado(playerInput, playerOutput);


    public UI (Player player)
    {
        keybinds = new Keybinds(player, playerEstado);
        listaDeBarraAcciones.add(new BarraAcciones(keybinds, 30));

        listaDeBarraAcciones.first().setKeybind(11, "W");
        listaDeBarraAcciones.first().setKeycode(11, 51);

        listaDeBarraAcciones.first().setKeybind(21, "S");
        listaDeBarraAcciones.first().setKeycode(21, 47);

        listaDeBarraAcciones.first().setKeybind(22, "D");
        listaDeBarraAcciones.first().setKeycode(22, 32);

        listaDeBarraAcciones.first().setKeybind(20, "A");
        listaDeBarraAcciones.first().setKeycode(20, 29);

        listaDeBarraAcciones.first().setAccion(0, DAO.accionDAOFactory.getAccionDAO().getAccion("Terraformar"));
        listaDeBarraAcciones.first().setAccion(11, DAO.accionDAOFactory.getAccionDAO().getAccion("IrNorte"));
        listaDeBarraAcciones.first().setAccion(21, DAO.accionDAOFactory.getAccionDAO().getAccion("IrSur"));
        listaDeBarraAcciones.first().setAccion(22, DAO.accionDAOFactory.getAccionDAO().getAccion("IrEste"));
        listaDeBarraAcciones.first().setAccion(20, DAO.accionDAOFactory.getAccionDAO().getAccion("IrOeste"));


    }
}
