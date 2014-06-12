package View.Classes.UI.BarraAcciones;// Created by Hanto on 06/05/2014.

import Interfaces.UI.BarraAcciones.ControladorBarraAccionI;
import Data.Misc.MiscData;
import Model.Classes.UI.BarraAcciones.BarraAcciones;
import Model.DTO.BarraAccionesDTO;
import Datos.RSC;
import View.Classes.Graficos.Texto;
import View.Classes.UI.BarraAcciones.AccionIcono.AccionIcono;
import View.Classes.UI.Comun.Ventana;
import View.Classes.UI.Comun.VentanaMoverListener;
import View.Classes.UI.Comun.VentanaResizeListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BarraAccionesView extends Table implements PropertyChangeListener, Ventana
{
    private BarraAcciones barraModel;
    private Stage stage;
    private DragAndDrop dad;
    private ConjuntoBarraAccionesView conjuntoBarraAccionesView;
    private ControladorBarraAccionI controlador;

    private Image moverBarra;
    private Image redimensionarBarra;
    private Image eliminarBarra;

    private Array<Array<AccionIcono>> barraIconos = new Array<>();

    public float getEsquinaSupIzdaX()                       { return this.getX(); }
    public float getEsquinaSupIzdaY()                       { return this.getY() + this.getHeight(); }
    @Override public float getAnchoElemento()               { return MiscData.BARRASPELLS_Ancho_Casilla; }
    @Override public float getAltoElemento()                { return MiscData.BARRASPELLS_Alto_Casilla; }

    public BarraAccionesView(BarraAcciones barraAcciones, ConjuntoBarraAccionesView conjuntoBarraAccionesView, Stage stage, ControladorBarraAccionI controller)
    {
        this.barraModel = barraAcciones;
        this.stage = stage;
        this.controlador = controller;
        this.conjuntoBarraAccionesView = conjuntoBarraAccionesView;
        this.dad = conjuntoBarraAccionesView.getDadAcciones();

        barraAcciones.añadirObservador(this);

        this.setWidth(barraModel.getNumColumnas()*(MiscData.BARRASPELLS_Ancho_Casilla));
        this.setHeight(barraModel.getNumFilas()*(MiscData.BARRASPELLS_Ancho_Casilla));

        this.bottom().left();
        this.setPosition(500,0);

        moverBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_RebindButtonON));
        moverBarra.addListener(new VentanaMoverListener(moverBarra, this));
        this.addActor(moverBarra);

        redimensionarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_RebindButtonON));
        redimensionarBarra.addListener(new VentanaResizeListener(redimensionarBarra, this, this));
        this.addActor(redimensionarBarra);

        eliminarBarra = new Image(RSC.miscRecusosDAO.getMiscRecursosDAO().cargarTextura(MiscData.RECURSO_BARRASPELLS_RebindButtonOFF));
        this.addActor(eliminarBarra);
        eliminarBarra.addListener(new InputListener()
        {
            @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {   eliminarBarraAcciones();
                return true;
            }
        });

        crearBarraIconos();

        this.stage.addActor(this);
    }

    private void crearBarraIconos()
    {
        for (int y=0; y< barraModel.getNumFilas(); y++)
        {   añadirFila(); }
        recrearTabla();
    }

    private AccionIcono crearIcono (int posX, int posY)
    {
        AccionIcono icono = new AccionIcono(barraModel, posX, posY);
        icono.addDragAndDrop(dad, controlador);
        icono.getApariencia().addListener(new BAccionesRebindListener(icono, conjuntoBarraAccionesView, controlador));
        return icono;
    }

    public void recrearTabla ()
    {   //La tabla es la Vista, lo que se ve, cada vez que se añaden o quitan columnas, o filas hay que recrearla
        this.clear();

        float esquinaSupIzda = getEsquinaSupIzdaY();

        this.setWidth(barraModel.getNumColumnas()*(MiscData.BARRASPELLS_Ancho_Casilla));
        this.setHeight(barraModel.getNumFilas()*(MiscData.BARRASPELLS_Ancho_Casilla));

        this.setY(esquinaSupIzda-getHeight());

        for (int y=0; y< barraIconos.size; y++)
        {
            for (int x = 0; x < barraIconos.get(y).size; x++)
            {
                AccionIcono icono = barraIconos.get(y).get(x);
                this.add(icono.getApariencia()).left().height(MiscData.BARRASPELLS_Alto_Casilla).width(MiscData.BARRASPELLS_Ancho_Casilla);
            }
            this.row();
        }

        moverBarra.setPosition(-moverBarra.getWidth()-2,this.getHeight()-moverBarra.getHeight());
        this.addActor(moverBarra);
        redimensionarBarra.setPosition(this.getWidth(),-redimensionarBarra.getHeight());
        this.addActor(redimensionarBarra);
        eliminarBarra.setPosition(-eliminarBarra.getWidth()-2, this.getHeight()-moverBarra.getHeight()-eliminarBarra.getHeight());
        this.addActor(eliminarBarra);
    }

    @Override public void eventoVentanaResize (int columnasAdicionales, int filasAdicionales)
    {
        if (columnasAdicionales >0)
              { controlador.barraAñadirColumna(barraModel, columnasAdicionales); }
        else  { controlador.barraEliminarColumna(barraModel, Math.abs(columnasAdicionales)); }
        if (filasAdicionales >0)
              { controlador.barraAñadirFila(barraModel, filasAdicionales); }
        else  { controlador.barraEliminarFila(barraModel, Math.abs(filasAdicionales)); }
    }

    private void añadirFila (int numFilas)
    {
        for (int i=0; i<numFilas; i++)
            añadirFila();
        recrearTabla();
    }

    private void añadirColumna(int numColumnas)
    {
        for (int i=0; i<numColumnas; i++)
            añadirColumna();
        recrearTabla();
    }

    private void eliminarFila (int numFilas)
    {
        for (int i=0; i<numFilas; i++)
            eliminarFila();
        recrearTabla();
    }

    private void eliminarColumna(int numColumnas)
    {
        for (int i=0; i<numColumnas; i++)
            eliminarColumna();
        recrearTabla();
    }

    private void añadirFila()
    {
        int y = barraIconos.size;
        Array<AccionIcono>array = new Array<>();

        for (int x = 0; x< barraModel.getNumColumnas(); x++)
        {
            AccionIcono icono = crearIcono(x, y);
            array.add(icono);
        }
        barraIconos.add(array);
    }

    private void añadirColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            int x = barraIconos.get(y).size;
            AccionIcono icono = crearIcono(x, y);
            barraIconos.get(y).add(icono);
        }
    }

    private void eliminarFila ()
    {
        Array<AccionIcono> array = barraIconos.peek();
        for (int i=0; i< array.size; i++)
        {   array.get(i).eliminarIcono(dad); }
        barraIconos.removeIndex(barraIconos.size - 1);
    }

    private void eliminarColumna()
    {
        for (int y=0; y< barraIconos.size; y++)
        {
            AccionIcono icono = barraIconos.get(y).pop();
            icono.eliminarIcono(dad);
        }
    }

    public void eliminarBarraAcciones()
    {
        controlador.eliminarBarraAcciones(barraModel);
        conjuntoBarraAccionesView.eliminarBarraAccionesView(this);
        barraModel.eliminarObservador(this);
        stage.getRoot().removeActor(this);
    }


    private void actualizarApariencia(AccionIcono icono)
    {
        icono.actualizarApariencia();
        if (barraModel.getKeybind(icono.getPosX(), icono.getPosY()) != null)
        {
            Texto.printTexto(String.valueOf(barraModel.getKeybind(icono.getPosX(), icono.getPosY())), RSC.fuenteRecursosDAO.getFuentesRecursosDAO().getFuente(MiscData.FUENTE_Nombres),
                             Color.ORANGE, Color.BLACK, 0, 20, Align.left, Align.bottom, 2, icono.getApariencia());
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getNewValue() instanceof BarraAccionesDTO.EliminarAccionDTO)
        {
            int posX = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.EliminarAccionDTO) evt.getNewValue()).posY;
            actualizarApariencia(barraIconos.get(posY).get(posX));
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.SetAccionDTO)
        {
            int posX = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posX;
            int posY = ((BarraAccionesDTO.SetAccionDTO) evt.getNewValue()).posY;
            actualizarApariencia(barraIconos.get(posY).get(posX));
        }

        if  (evt.getNewValue() instanceof BarraAccionesDTO.EliminarFilaDTO)
        {
            int numFilas = ((BarraAccionesDTO.EliminarFilaDTO) evt.getNewValue()).numFilas;
            eliminarFila(numFilas);
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.AñadirFilaDTO)
        {
            int numFilas = ((BarraAccionesDTO.AñadirFilaDTO) evt.getNewValue()).numFilas;
            añadirFila(numFilas);
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.EliminarColumnaDTO)
        {
            int numColumnas = ((BarraAccionesDTO.EliminarColumnaDTO) evt.getNewValue()).numColumnas;
            eliminarColumna(numColumnas);
        }

        if (evt.getNewValue() instanceof BarraAccionesDTO.AñadirColumnaDTO)
        {
            int numColumnas = ((BarraAccionesDTO.AñadirColumnaDTO) evt.getNewValue()).numColumnas;
            añadirColumna(numColumnas);
        }
    }
}
