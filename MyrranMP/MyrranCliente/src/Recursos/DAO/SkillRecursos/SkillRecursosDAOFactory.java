package Recursos.DAO.SkillRecursos;// Created by Hanto on 30/04/2014.

import Recursos.DAO.SkillRecursos.DB.SkillRecursosLocal;

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
