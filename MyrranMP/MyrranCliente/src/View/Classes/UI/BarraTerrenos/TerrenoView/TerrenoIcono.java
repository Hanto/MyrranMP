package View.Classes.UI.BarraTerrenos.TerrenoView;// Created by Hanto on 14/05/2014.

import Interfaces.UI.BarraTerrenos.ControladorBarraTerrenosI;
import Data.MiscData;
import Model.Classes.UI.BarraTerrenos;
import DB.RSC;
import DB.Recursos.TerrenoRecursos.DAO.TerrenoRecursosDAO;
import View.Classes.UI.Icono.Icono;
import View.Classes.UI.Icono.IconoSource;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class TerrenoIcono implements Icono
{
    protected BarraTerrenos barraTerrenos;
    protected int posX;

    protected Group apariencia = new Group();

    public int getPosX()                        { return posX; }

    //Constructor:
    public TerrenoIcono(BarraTerrenos barraTerrenos, int posX)
    {
        this.barraTerrenos = barraTerrenos;
        this.posX = posX;
        actualizarApariencia();
        crearListener();
    }


    @Override public Group getApariencia()          { return apariencia; }
    public void actualizarApariencia()    { actualizarApariencia(apariencia); }

    private void actualizarApariencia(Group group)
    {
        group.clearChildren();

        TerrenoRecursosDAO terrenoDAO = RSC.terrenoRecursosDAO.getTerrenoRecursosDAO();

        int terrenoID = barraTerrenos.getTerrenoID(posX);
        TextureRegion textura = new TextureRegion(terrenoDAO.getTerrenoRecurso(terrenoID).getTextura(), 0, MiscData.TILESIZE*1, MiscData.TILESIZE*2, MiscData.TILESIZE*2);
        Image image = new Image(textura);

        group.addActor(image);
        group.setWidth(image.getWidth());
        group.setHeight(image.getHeight());
    }


    @Override public Group getDragActor()
    {
        Group group = new Group();
        actualizarApariencia(group);
        return group;
    }

    @Override public boolean tieneDatos()
    {
        if (barraTerrenos.getTerrenoID(posX) >= 0) return true;
        else return false;
    }

    public void crearListener()
    {
        apariencia.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   barraTerrenos.setParametroTerrenoID(barraTerrenos.getTerrenoID(posX));
                return true;
            }
        });
    }

    public void addSource(DragAndDrop dad)
    {   dad.addSource(new IconoSource(this, dad)); }

    public void addTarget(DragAndDrop dad, ControladorBarraTerrenosI controlador)
    {   dad.addTarget(new TerrenoTarget(this, controlador));}

    public void addDragAndDrop (DragAndDrop dad, ControladorBarraTerrenosI controlador)
    {
        addSource(dad);
        addTarget(dad, controlador);
    }

}
