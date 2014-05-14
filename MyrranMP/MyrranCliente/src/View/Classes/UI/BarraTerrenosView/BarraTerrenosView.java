package View.Classes.UI.BarraTerrenosView;// Created by Hanto on 14/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.Geo.Terreno;
import Model.DAO.DAO;
import Model.DAO.Terreno.TerrenoDAO;
import Recursos.DAO.RSC;
import View.Classes.UI.ConjuntoBarraAccionView.BarraAccionesView.BarraMoverListener;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class BarraTerrenosView extends Group
{
    protected Controlador controlador;
    protected Stage stage;

    protected Table tablaTerrenos = new Table().top().left();
    protected ScrollPane scrollPane;
    protected Array<IconoTerreno> barraIconos = new Array<>();

    protected Image moverBarra;
    protected Image redimensionarBarra;

    protected int numFilas = 4;
    protected int numColumnas =2;

    public BarraTerrenosView (Controlador controlador, Stage stage)
    {
        this.controlador = controlador;
        this.stage = stage;

        scrollPane = new ScrollPane(tablaTerrenos);
        this.addActor(scrollPane);

        moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        moverBarra.addListener(new BarraMoverListener(moverBarra, this));
        this.addActor(moverBarra);

        redimensionarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        redimensionarBarra.addListener(new BarraTerrenosResizeListener(redimensionarBarra, this, this));
        this.addActor(redimensionarBarra);

        scrollPane.addListener(new InputListener()
        {   //para que se puede hacer scroll de la barra de terreno con solo pasar el raton por encima:
            @Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            { scrollPane.getStage().setScrollFocus(scrollPane); }

            //para que no haga scroll cuando el raton este fuera de la ventana de terrenos
            @Override public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
            { scrollPane.getStage().setScrollFocus(null); }

            //Añdimos un listener para el scroll, para configurar su velocidad, hay que parar su propagacion con event.stop() para que no salte el que esta programado de base
            @Override public boolean scrolled(InputEvent event, float x, float y, int amount)
            { scrollPane.setScrollY(scrollPane.getScrollY()+ MiscData.TILESIZE*3*amount); event.stop(); return true; }
        });

        crearBarraIconos();
        crearTabla();

        this.setPosition(50,400);
        stage.addActor(this);
    }


    public void crearBarraIconos()
    {
        barraIconos.clear();

        TerrenoDAO terrenoDAO = DAO.terrenoDAOFactory.getTerrenoDAO();

        Iterator<Terreno> iterator = terrenoDAO.getIterator();
        while (iterator.hasNext())
        {
            IconoTerreno icono = new IconoTerreno(iterator.next().getID());
            barraIconos.add(icono);
        }
    }

    public void crearTabla()
    {
        tablaTerrenos.clear();

        int columna = 0;

        for (IconoTerreno icono: barraIconos)
        {
            columna++;
            tablaTerrenos.add(icono.getApariencia()).left().height(icono.getApariencia().getHeight()).width(icono.getApariencia().getWidth());
            if (columna%numColumnas == 0) tablaTerrenos.row();
        }

        scrollPane.setBounds(0,0, numColumnas*MiscData.TILESIZE*2, numFilas*MiscData.TILESIZE*2);

        float esquinaSupIzda = getY() + getHeight();

        this.setWidth(numColumnas*MiscData.TILESIZE*2);
        this.setHeight(numFilas*MiscData.TILESIZE*2);

        setY(esquinaSupIzda - getHeight());

        moverBarra.setPosition(-moverBarra.getWidth()-2,this.getHeight()-moverBarra.getHeight());
        redimensionarBarra.setPosition(this.getWidth(),-redimensionarBarra.getHeight());
    }
}
