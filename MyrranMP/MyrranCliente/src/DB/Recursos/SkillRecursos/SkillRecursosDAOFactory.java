package DB.Recursos.SkillRecursos;// Created by Hanto on 30/04/2014.

import DB.Recursos.SkillRecursos.DAO.SkillRecursosDAO;
import DB.Recursos.SkillRecursos.DAO.SkillRecursosLocal;

public enum SkillRecursosDAOFactory
{
    LOCAL("LOCAL")
    {
        @Override
        public SkillRecursosDAO getSpellRecursosDAO()
        {   return new SkillRecursosLocal(); }
    };

    public abstract SkillRecursosDAO getSpellRecursosDAO();
    private SkillRecursosDAOFactory(String nombre) {}
}
