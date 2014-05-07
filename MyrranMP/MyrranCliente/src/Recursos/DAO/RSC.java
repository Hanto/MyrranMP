package Recursos.DAO;// Created by Ladrim on 24/04/2014.

import Recursos.DAO.AccionRecursos.AccionRecursosDAOFactory;
import Recursos.DAO.FuentesRecursos.FuentesRecursosDAOFactory;
import Recursos.DAO.MiscRecursos.MiscRecursosDAOFactory;
import Recursos.DAO.PixiePCRecursos.PixiePCRecursosDAOFactory;
import Recursos.DAO.SkillRecursos.SkillRecursosDAOFactory;
import Recursos.DAO.TerrenoRecursos.TerrenoRecursosDAOFactory;

public class RSC
{
    public static final TerrenoRecursosDAOFactory terrenoRecursosDAO = TerrenoRecursosDAOFactory.LOCAL;
    public static final SkillRecursosDAOFactory skillRecursosDAO = SkillRecursosDAOFactory.LOCAL;
    public static final PixiePCRecursosDAOFactory pixiePCRecursosDAO = PixiePCRecursosDAOFactory.LOCAL;
    public static final FuentesRecursosDAOFactory fuenteRecursosDAO = FuentesRecursosDAOFactory.LOCAL;
    public static final MiscRecursosDAOFactory miscRecusosDAO = MiscRecursosDAOFactory.LOCAL;
    public static final AccionRecursosDAOFactory accionRecursosDAO = AccionRecursosDAOFactory.LOCAL;
}
