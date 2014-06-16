package View.Classes.UI.BarraTerrenos;// Created by Hanto on 14/05/2014.

import Controller.Controlador;
import Data.MiscData;
import Model.Classes.UI.BarraTerrenos.BarraTerrenos;
import Model.DTO.BarraTerrenosDTO;
import DB.RSC;
import View.Classes.Graficos.Texto;
import View.Classes.UI.BarraTerrenos.TerrenoView.TerrenoView;
import View.Classes.UI.Comun.Ventana;
import View.Classes.UI.Comun.VentanaMoverListener;
import View.Classes.UI.Comun.VentanaResizeListener;
import View.Classes.UI.Comun.VentanaScrollListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BarraTerrenosView extends Group implements PropertyChangeListener, Ventana
{
    protected Controlador controlador;
    protected Stage stage;
    protected BarraTerrenos barraTerrenos;

    protected Table tablaTerrenos = new Table().top().left();
    protected ScrollPane scrollPane;
    protected Array<TerrenoView> barraIconos;

    protected DragAndDrop dad;

    private Texto[] botonCapas = new Texto[MiscData.MAPA_Max_Capas_Terreno];
    protected Image moverBarra;
    protected Image redimensionarBarra;
    protected Image botonBorrarTerreno;

    private float oldX;
    private float oldY;

    protected int numFilas = 4;
    protected int numColumnas =2;

    @Override public float getAnchoElemento()           { return MiscData.TILESIZE*2; }
    @Override public float getAltoElemento()            { return MiscData.TILESIZE*2; }

    public BarraTerrenosView (Controlador controlador, Stage stage, final BarraTerrenos barraTerrenos)
    {
        this.controlador = controlador;
        this.stage = stage;
        this.barraTerrenos = barraTerrenos;


        barraTerrenos.añadirObservador(this);

        scrollPane = new ScrollPane(tablaTerrenos);
        this.addActor(scrollPane);

        moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_RebindButtonON));
        moverBarra.addListener(new VentanaMoverListener(moverBarra, this));
        this.addActor(moverBarra);

        redimensionarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_RebindButtonON));
        redimensionarBarra.addListener(new VentanaResizeListener(redimensionarBarra, this, this));
        this.addActor(redimensionarBarra);

        botonBorrarTerreno = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRATERRENOS_Borrar_Terreno));
        botonBorrarTerreno.setPosition(0, -botonBorrarTerreno.getHeight());
        this.addActor(botonBorrarTerreno);
        botonBorrarTerreno.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                barraTerrenos.setParametroTerrenoID((short)-1);
                return true;
            }
        });

        scrollPane.clearListeners();
        scrollPane.addListener(new VentanaScrollListener(scrollPane));

        crearBarraIconos();
        crearBotonCapas();

        setPosition(-getWidth()-redimensionarBarra.getWidth(), 500);
        oldX = moverBarra.getWidth();
        oldY = 500;
    }

    private void crearBarraIconos()
    {
        barraIconos = new Array<>();
        dad = new DragAndDrop();
        dad.setDragTime(0);

        for (int x=0; x< barraTerrenos.getTamaño(); x++)
        {   barraIconos.add(crearIcono(x)); }

        recrearTabla();
    }

    private TerrenoView crearIcono(final int posX)
    {
        TerrenoView icono = new TerrenoView(barraTerrenos, posX);
        icono.addDragAndDrop(dad, controlador);
        return icono;
    }

    private void crearBotonCapas()
    {
        for (int i=0; i< MiscData.MAPA_Max_Capas_Terreno; i++)
        {
            final int numCapa = i;
            botonCapas[i] = new Texto("Capa "+numCapa, RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres), Color.ORANGE, Color.BLACK, 0, 0, Align.left, Align.bottom, 2);
            botonCapas[i].setPosition(4, -MiscData.TILESIZE*2-18-numCapa*17);
            this.addActor(botonCapas[i]);

            botonCapas[i].addListener(new InputListener()
            {
                @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
                {
                    for (int j=0; j<botonCapas.length; j++)
                    {   botonCapas[j].setColorNormal(Color.ORANGE); }
                    botonCapas[numCapa].setColorNormal(Color.GREEN);
                    barraTerrenos.setParametroNumCapa(numCapa);
                    return true;
                }
            });
        }
    }

    public void recrearTabla()
    {   //La tabla es la Vista, lo que se ve, cada vez que se redimensiona el ancho y alto de la zona de visualizacion hay que recrearla
        tablaTerrenos.clear();

        int columna = 0;

        for (TerrenoView icono: barraIconos)
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

    @Override public void eventoVentanaResize(int columnasAdicionales, int filasAdicionales)
    {
        if (this.getStage() != null)
        {
            numColumnas += columnasAdicionales;
            numFilas += filasAdicionales;
            recrearTabla();
        }
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

    public void actualizarApariencia(TerrenoView icono)
    {   icono.actualizarApariencia(); }


    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof BarraTerrenosDTO.SetTerreno)
        {
            int posX = ((BarraTerrenosDTO.SetTerreno) evt.getNewValue()).posX;
            actualizarApariencia(barraIconos.get(posX));
        }

        if (evt.getNewValue() instanceof BarraTerrenosDTO.CrearBarraTerreno)
        {   crearBarraIconos(); }
    }
}
