package Datos.SkillRecursos.DB;// Created by Hanto on 30/04/2014.

import Datos.SkillRecursos.SkillRecursosDAO;
import Datos.SkillRecursos.SkillRecursosLocal;

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
