package Model.DAO.Accion;

import Model.DAO.Accion.DB.AccionLocal;

public enum AccionDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public AccionDAO getAccionDAO()
        {   return new AccionLocal(); }
    };

    public abstract AccionDAO getAccionDAO();
    private AccionDAOFactory(String nombre) {}
}