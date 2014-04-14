package Controller;// Created by Hanto on 07/04/2014.

import Modelo.DAO.DAOFactory.PcDBFactory;
import Modelo.DAO.DAOFactory.TerrenoDBFactory;
import Modelo.DAO.PcDAO;
import Modelo.DAO.TerrenoDAO;
import Modelo.Mobiles.MundoModel;
import View.Vista;

public class Controlador
{
    protected Servidor servidor = new Servidor(this);                  //Input principal de la simulacion
    protected NetUpdater netUpdater;

    protected PcDAO pcDAO = PcDBFactory.LOCAL.newInstance();
    protected TerrenoDAO terrenoDAO = TerrenoDBFactory.LOCAL.newInstance();

    protected MundoModel mundoModel;
    protected Vista vista;

    public Controlador (MundoModel mundoModel)
    {
        this.mundoModel = mundoModel;
        vista = new Vista(this, mundoModel);
        netUpdater = new NetUpdater(this);
    }

    public void enviarACliente(int connectionID, Object obj)            { servidor.enviarACliente(connectionID, obj); }

    public void añadirPC (int connectionID, float x, float y)           { mundoModel.añadirPC(connectionID, x, y); }
    public void moverPC (int connectionID, float x, float y)            { mundoModel.moverPC(connectionID, x, y); }
    public void eliminarPC (int connectionID)                           { mundoModel.eliminarPC(connectionID); }
    public void cambiarAnimacionPC(int connectionID, int numAnimacion)  { mundoModel.cambiarAnimacionPC(connectionID, numAnimacion);}

    public void netUpdater ()                                           { vista.netUpdate(); }
}
