package zMain;// Created by Hanto on 11/04/2014.

import Core.SkillStat;
import DAO.Accion.AccionDAO;
import DAO.DAO;
import DB.RSC;
import DB.Recursos.AccionRecursos.DAO.AccionRecursosDAO;
import DB.Recursos.AccionRecursos.DTO.AccionRecursos;
import Model.Classes.UI.Acciones.Accion;
import Model.Classes.UI.Acciones.TiposAccion.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;

public class LoadGameData
{
    private TextureAtlas atlas = RSC.atlasRecursosDAO.getAtlasRecursosDAO().getAtlas();

    public List<SpellDTO> listaDeSpells = new ArrayList<>();

    public enum TipoSpell { EDITARTERRENO }
    public static class SpellDTO
    {
        public TipoSpell tipoSpell;
        public String id;
        public String nombre;
        public String descripcion;
        public SkillStat[] skillStats;
        public String iDIcono;
        public String[] listaIDAnimaciones;
        public SpellDTO(int numSkillStats, int numAnimaciones)
        {
            skillStats = new SkillStat[numSkillStats];
            for (int i=0; i< skillStats.length; i++)
            {   skillStats[i] = new SkillStat(); }
            listaIDAnimaciones = new String[numAnimaciones];
        }
    }
    public LoadGameData()
    {

        //SPELL: Editar Terreno:
        SpellDTO spellDTO = new SpellDTO(1,0);
        spellDTO.tipoSpell = TipoSpell.EDITARTERRENO;
        spellDTO.id = "Terraformar";
        spellDTO.nombre = "Terra";
        spellDTO.descripcion = "Terraforma el terreno";
        spellDTO.skillStats[0].setValor(0.01f);
        spellDTO.iDIcono = "Editar";
        listaDeSpells.add(spellDTO);
    }

    public void cargarTodo ()
    {

        cargarTexturasAcciones();
        cargarAcciones();
    }






    public void cargarTexturasAcciones()
    {
        AccionRecursosDAO accionRecursosDAO = RSC.accionRecursosDAO.getAccionRecursosDAO();
        accionRecursosDAO.salvarTextura("IrNorte",  "IrNorte",  atlas);
        accionRecursosDAO.salvarTextura("IrSur",    "IrSur",    atlas);
        accionRecursosDAO.salvarTextura("IrEste",   "IrEste",   atlas);
        accionRecursosDAO.salvarTextura("IrOeste",  "IrOeste",  atlas);
    }


    public void cargarAcciones()
    {
        cargarAccion(new IrNorte());
        cargarAccion(new IrSur());
        cargarAccion(new IrOeste());
        cargarAccion(new IrEste());

        AccionDAO accionDAO = DAO.accionDAOFactory.getAccionDAO();
        AccionRecursosDAO accionRecursosDAO = RSC.accionRecursosDAO.getAccionRecursosDAO();

        AccionRecursos accionRecurso;
        Accion accion;

        for (SpellDTO spellDTO : listaDeSpells)
        {
            accion = new SeleccionarSpell(DB.DAO.spellDAOFactory.getSpellDAO().getSpell(spellDTO.id));
            accionDAO.salvarAccion(accion);

            accionRecurso = new AccionRecursos(accion.getID(), RSC.skillRecursosDAO.getSpellRecursosDAO().getSpellRecursos(spellDTO.id).getIcono());
            accionRecursosDAO.salvarAccionRecurso(accionRecurso);
        }
    }

    public void cargarAccion(Accion accion)
    {
        AccionDAO accionDAO = DAO.accionDAOFactory.getAccionDAO();
        AccionRecursosDAO accionRecursosDAO = RSC.accionRecursosDAO.getAccionRecursosDAO();

        accionDAO.salvarAccion(accion);
        accionRecursosDAO.salvarAccionRecurso(new AccionRecursos(accion.getID(), accionRecursosDAO.getTextura(accion.getID())));
    }
}
