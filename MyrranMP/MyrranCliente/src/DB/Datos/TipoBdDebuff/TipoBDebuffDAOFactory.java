package DB.Datos.TipoBdDebuff;

import DAO.TipoBDebuff.TipoBDebuffDAO;
import DAO.TipoBDebuff.TipoBDebuffLocal;

public enum TipoBDebuffDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public TipoBDebuffDAO getTipoBDebuffDAO()
        {   return new TipoBDebuffLocal(TipoBDebuffLocalDB.get().listaDeTipoBDebuffs); }
    };

    public abstract TipoBDebuffDAO getTipoBDebuffDAO();

    private TipoBDebuffDAOFactory(String nombre) {}

}