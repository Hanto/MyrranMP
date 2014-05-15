package Model.Classes.UI.BarraTerrenos;// Created by Hanto on 14/05/2014.

import Model.Classes.AbstractModel;
import Model.Classes.Geo.Terreno;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DTO.BarraTerrenosDTO;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class BarraTerrenos extends AbstractModel
{
    private Array<Integer>barraTerrenos = new Array<>();


    public int getTerrenoID (int posX)                  { return barraTerrenos.get(posX); }
    public int getTamaño()                              { return barraTerrenos.size; }

    public BarraTerrenos ()
    {
        crearBarraTerrenos();
    }

    public void crearBarraTerrenos()
    {
        barraTerrenos.clear();

        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();

        Iterator<Terreno> iterator = terrenoDAO.getIterator();
        while (iterator.hasNext())
        {   barraTerrenos.add(iterator.next().getID()); }
    }

    public void moverTerreno(int posOrigen, int posDestino)
    {
        int origen = barraTerrenos.get(posOrigen);
        setTerreno(posOrigen, getTerrenoID(posDestino));
        setTerreno(posDestino, origen);
    }

    public void setTerreno (int posX, int terrenoID)
    {
        barraTerrenos.set(posX, terrenoID);
        Object setTerrenoDTO = new BarraTerrenosDTO.SetTerrenoDTO(posX);
        notificarActualizacion("setTerreno", null, setTerrenoDTO);
    }
}
