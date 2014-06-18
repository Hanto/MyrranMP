package DB.Datos.BDebuff;

import DAO.BDebuff.BDebuffDAO;
import DAO.BDebuff.BDebuffLocal;

public enum BDebuffDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override public BDebuffDAO getBDebuffDAO()
        {   return new BDebuffLocal(BDebuffLocalDB.get().listaDeBDebuffs); }
    };


    public abstract BDebuffDAO getBDebuffDAO();

    private BDebuffDAOFactory(String nombre) {}

}