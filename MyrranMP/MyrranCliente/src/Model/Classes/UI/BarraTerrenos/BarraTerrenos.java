package Model.Classes.UI.BarraTerrenos;// Created by Hanto on 14/05/2014.

import Interfaces.Caster;
import Model.Classes.AbstractModel;
import Model.Classes.Geo.Terreno;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;
import Model.DTO.BarraTerrenosDTO;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import static DTO.ParametrosSpellDTO.ParametrosEditarTerreno;

public class BarraTerrenos extends AbstractModel
{
    private Array<Integer>barraTerrenos = new Array<>();
    private Caster player;

    private int parametroTerrenoID = 0;
    private int parametroNumCapa = 0;

    public int getTerrenoID (int posX)                  { return barraTerrenos.get(posX); }
    public int getTamaño()                              { return barraTerrenos.size; }

    public BarraTerrenos (Caster player)
    {
        crearBarraTerrenos();
        this.player = player;
    }

    public void crearBarraTerrenos()
    {
        barraTerrenos.clear();

        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();

        Iterator<Terreno> iterator = terrenoDAO.getIterator();
        while (iterator.hasNext())
        {   barraTerrenos.add(iterator.next().getID()); }

        Object crearBarraTerrenos = new BarraTerrenosDTO.CrearBarraTerreno();
        notificarActualizacion("crearBarraTerrenos", null, crearBarraTerrenos);
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

        Object setTerrenoDTO = new BarraTerrenosDTO.SetTerreno(posX);
        notificarActualizacion("setTerreno", null, setTerrenoDTO);
    }

    public void setParametroTerrenoID(int terrenoID)
    {
        //if (player.getParametrosSpell() instanceof ParametrosEditarTerreno)
        {
            //ParametrosEditarTerreno editarTerreno = (ParametrosEditarTerreno)player.getParametrosSpell();
            ParametrosEditarTerreno editarTerreno = new ParametrosEditarTerreno(parametroNumCapa, terrenoID);
            parametroTerrenoID = terrenoID;
            //editarTerreno.terrenoIDSeleccionado = terrenoID;
            player.setParametrosSpell(editarTerreno);
        }
    }

    public void setParametroNumCapa(int numCapa)
    {
        //if (player.getParametrosSpell() instanceof ParametrosEditarTerreno)
        {
            //ParametrosEditarTerreno editarTerreno = (ParametrosEditarTerreno)player.getParametrosSpell();
            ParametrosEditarTerreno editarTerreno = new ParametrosEditarTerreno(numCapa, parametroTerrenoID);
            parametroNumCapa = numCapa;
            //editarTerreno.capaTerrenoSeleccionada = numCapa;
            player.setParametrosSpell(editarTerreno);
        }
    }
}
