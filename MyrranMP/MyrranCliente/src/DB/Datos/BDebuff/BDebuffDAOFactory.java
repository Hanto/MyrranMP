package DB.Datos.BDebuff;

import DAO.BDebuff.BDebuffDAO;
import DAO.BDebuff.BDebuffLocal;

public enum BDebuffDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override public BDebuffDAO getBDebuffDAODAO()
        {   return new BDebuffLocal(BDebuffLocalDB.get().listaDeBDebuffs); }
    };


    public abstract BDebuffDAO getBDebuffDAODAO();

    private BDebuffDAOFactory(String nombre) {}

}