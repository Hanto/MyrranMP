package View.Classes.UI.BarraTerrenosView;// Created by Hanto on 14/05/2014.

import Data.MiscData;
import Recursos.DAO.RSC;
import Recursos.DAO.TerrenoRecursos.TerrenoRecursosDAO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class IconoTerreno
{
    protected Group apariencia = new Group();
    protected int posX;
    protected int terrenoID;

    public Group getApariencia()                { return apariencia; }

    public IconoTerreno(int terrenoID)
    {
        this.terrenoID = terrenoID;
        actualizarApariencia(apariencia);
    }

    private void actualizarApariencia(Group group)
    {
        group.clearChildren();

        TerrenoRecursosDAO terrenoDAO = RSC.terrenoRecursosDAO.getTerrenoRecursosDAO();

        TextureRegion textura = new TextureRegion(terrenoDAO.getTerrenoRecurso(terrenoID).getTextura(), 0, MiscData.TILESIZE*1, MiscData.TILESIZE*2, MiscData.TILESIZE*2);
        Image image = new Image(textura);

        group.addActor(image);
        group.setBounds(0,0,image.getWidth(), image.getHeight());
    }
}
