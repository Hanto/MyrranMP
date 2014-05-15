package View.Classes.UI.BarraTerrenos.BarraTerrenosView;// Created by Hanto on 14/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.UI.BarraTerrenos.BarraTerrenos;
import Model.DTO.BarraTerrenosDTO;
import Recursos.DAO.RSC;
import View.Classes.UI.BarraTerrenos.TerrenoIcono.TerrenoIcono;
import View.Classes.UI.Comun.MoverActorListener;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BarraTerrenosView extends Group implements PropertyChangeListener
{
    protected Controlador controlador;
    protected Stage stage;
    protected BarraTerrenos barraTerrenos;

    protected Table tablaTerrenos = new Table().top().left();
    protected ScrollPane scrollPane;
    protected Array<TerrenoIcono> barraIconos = new Array<>();

    protected DragAndDrop dad = new DragAndDrop();

    protected Image moverBarra;
    protected Image redimensionarBarra;

    private float oldX;
    private float oldY;

    protected int numFilas = 4;
    protected int numColumnas =2;

    public BarraTerrenosView (Controlador controlador, Stage stage, BarraTerrenos barraTerrenos)
    {
        this.controlador = controlador;
        this.stage = stage;
        this.barraTerrenos = barraTerrenos;

        scrollPane = new ScrollPane(tablaTerrenos);
        this.addActor(scrollPane);

        moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        moverBarra.addListener(new MoverActorListener(moverBarra, this));
        this.addActor(moverBarra);

        redimensionarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.BARRASPELLS_RebindButtonON));
        redimensionarBarra.addListener(new BTerrenosResizeListener(redimensionarBarra, this, this));
        this.addActor(redimensionarBarra);

        scrollPane.clearListeners();
        scrollPane.addListener(new DragListener()
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

        setPosition(-getWidth()-redimensionarBarra.getWidth(), 500);

        oldX = moverBarra.getWidth();
        oldY = 500;

        barraTerrenos.añadirObservador(this);
    }

    private void crearBarraIconos()
    {
        dad.setDragTime(0);
        barraIconos.clear();

        for (int x=0; x< barraTerrenos.getTamaño(); x++)
        {
            final TerrenoIcono icono = new TerrenoIcono(barraTerrenos, x);
            icono.addDragAndDrop(dad, controlador);

            barraIconos.add(icono);
        }
    }

    public void crearTabla()
    {
        tablaTerrenos.clear();

        int columna = 0;

        for (TerrenoIcono icono: barraIconos)
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

    public void mostrarBarraTerrenos()
    {
        this.clearActions();
        stage.addActor(this);
        this.addAction(Actions.moveTo(oldX, oldY, 0.2f, Interpolation.sine));
    }

    public void ocultarBarraTerrenos()
    {
        if (this.getActions().size == 0)
        {
            oldX = getX();
            oldY = getY();
        }
        else clearActions();
        addAction(Actions.sequence(Actions.moveTo(0-getWidth()-redimensionarBarra.getWidth(), getY(), 0.5f, Interpolation.sine),
                                   Actions.removeActor()));
    }

    public void actualizarApariencia(TerrenoIcono icono)
    {   icono.actualizarApariencia(); }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof BarraTerrenosDTO.SetTerrenoDTO)
        {
            int posX = ((BarraTerrenosDTO.SetTerrenoDTO) evt.getNewValue()).posX;
            actualizarApariencia(barraIconos.get(posX));
        }
    }
}
