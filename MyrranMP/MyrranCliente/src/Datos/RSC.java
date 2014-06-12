package Datos;// Created by Ladrim on 24/04/2014.

import Datos.AccionRecursos.DB.AccionRecursosDAOFactory;
import Datos.AtlasRecursos.DB.AtlasRecursosDAOFactory;
import Datos.FuentesRecursos.DB.FuentesRecursosDAOFactory;
import Datos.MiscRecursos.DB.MiscRecursosDAOFactory;
import Datos.PixiePCRecursos.DB.PixiePCRecursosDAOFactory;
import Datos.SkillRecursos.DB.SkillRecursosDAOFactory;
import Datos.TerrenoRecursos.DB.TerrenoRecursosDAOFactory;

public class RSC
{
    public static final TerrenoRecursosDAOFactory terrenoRecursosDAO = TerrenoRecursosDAOFactory.LOCAL;
    public static final SkillRecursosDAOFactory skillRecursosDAO = SkillRecursosDAOFactory.LOCAL;
    public static final PixiePCRecursosDAOFactory pixiePCRecursosDAO = PixiePCRecursosDAOFactory.LOCAL;
    public static final FuentesRecursosDAOFactory fuenteRecursosDAO = FuentesRecursosDAOFactory.LOCAL;
    public static final MiscRecursosDAOFactory miscRecusosDAO = MiscRecursosDAOFactory.LOCAL;
    public static final AccionRecursosDAOFactory accionRecursosDAO = AccionRecursosDAOFactory.LOCAL;
    public static final AtlasRecursosDAOFactory atlasRecursosDAO = AtlasRecursosDAOFactory.LOCAL;
}
