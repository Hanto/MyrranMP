package Model.Classes.UI.BarraTerrenos;// Created by Hanto on 14/05/2014.

import DAO.Terreno.TerrenoDAO;
import Interfaces.EntidadesPropiedades.Caster;
import Interfaces.Geo.TerrenoI;
import Interfaces.Model.AbstractModel;
import Model.DTO.BarraTerrenosDTO;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import static DTO.ParametrosSpellDTO.ParametrosEditarTerreno;

public class BarraTerrenos extends AbstractModel
{
    private Array<Short>barraTerrenos = new Array<>();
    private Caster caster;

    private short parametroTerrenoID = 0;
    private int parametroNumCapa = 0;

    public short getTerrenoID (int posX)                { return barraTerrenos.get(posX); }
    public int getTamaño()                              { return barraTerrenos.size; }

    public BarraTerrenos (Caster player)
    {
        crearBarraTerrenos();
        this.caster = player;
    }

    public void crearBarraTerrenos()
    {
        barraTerrenos.clear();

        TerrenoDAO terrenoDAO = DB.DAO.terrenoDAOFactory.getTerrenoDAO();

        Iterator<TerrenoI> iterator = terrenoDAO.getIterator();
        while (iterator.hasNext())
        {   barraTerrenos.add(iterator.next().getID()); }

        Object crearBarraTerrenos = new BarraTerrenosDTO.CrearBarraTerreno();
        notificarActualizacion("crearBarraTerrenos", null, crearBarraTerrenos);
    }

    public void moverTerreno(int posOrigen, int posDestino)
    {
        short origen = barraTerrenos.get(posOrigen);
        setTerreno(posOrigen, getTerrenoID(posDestino));
        setTerreno(posDestino, origen);
    }

    public void setTerreno (int posX, short terrenoID)
    {
        barraTerrenos.set(posX, terrenoID);

        Object setTerrenoDTO = new BarraTerrenosDTO.SetTerreno(posX);
        notificarActualizacion("setTerreno", null, setTerrenoDTO);
    }

    public void setParametroTerrenoID(short terrenoID)
    {
        //if (caster.getParametrosSpell() instanceof ParametrosEditarTerreno)
        {
            //ParametrosEditarTerreno editarTerreno = (ParametrosEditarTerreno)caster.getParametrosSpell();
            ParametrosEditarTerreno editarTerreno = new ParametrosEditarTerreno(parametroNumCapa, terrenoID);
            parametroTerrenoID = terrenoID;
            //editarTerreno.terrenoIDSeleccionado = terrenoID;
            caster.setParametrosSpell(editarTerreno);
        }
    }

    public void setParametroNumCapa(int numCapa)
    {
        //if (caster.getParametrosSpell() instanceof ParametrosEditarTerreno)
        {
            //ParametrosEditarTerreno editarTerreno = (ParametrosEditarTerreno)caster.getParametrosSpell();
            ParametrosEditarTerreno editarTerreno = new ParametrosEditarTerreno(numCapa, parametroTerrenoID);
            parametroNumCapa = numCapa;
            //editarTerreno.capaTerrenoSeleccionada = numCapa;
            caster.setParametrosSpell(editarTerreno);
        }
    }
}
