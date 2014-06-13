package DB.Datos.Accion;

import DAO.Accion.AccionDAO;
import DAO.Accion.AccionLocal;

public enum AccionDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public AccionDAO getAccionDAO()
        {   return new AccionLocal(AccionLocalDB.get().listaDeAcciones); }
    };

    public abstract AccionDAO getAccionDAO();
    private AccionDAOFactory(String nombre) {}
}