package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Model.Mobiles.Mundo;
import View.Geo.GeoRecursos;
import View.Graficos.Atlas;
import View.Mobiles.MobilesRecursos;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import zMain.LoadGameData;
import zMain.MyrranClient;


public class PantallaLibGDX implements Screen
{
    private MyrranClient myrranCliente;
    private Controlador controlador;

    public String getNombrePantalla()           { return ((Object)this).getClass().getSimpleName(); }

    public PantallaLibGDX(MyrranClient myrranCliente)
    {
        this.myrranCliente = myrranCliente;

        MobilesRecursos.get().setAtlas(Atlas.get().atlas);
        GeoRecursos.get().setAtlas(Atlas.get().atlas);
        LoadGameData.cargarTodo();

        controlador = new Controlador(new Mundo());
    }

    @Override public void show()
    {   Gdx.app.log( myrranCliente.LOG, "SHOW (Inicializando Screen): " + getNombrePantalla()); }

    @Override public void render(float delta)
    {
        controlador.render(delta);
    }

    @Override public void resize(int anchura, int altura)
    {   Gdx.app.log( myrranCliente.LOG, "RESIZE (Redimensionando Screen): "+ getNombrePantalla() +" a: "+anchura+" x "+altura);
        controlador.resize(anchura, altura);
    }

    @Override public void pause()
    {   Gdx.app.log( myrranCliente.LOG, "PAUSE (Pausando pantalla): " + getNombrePantalla()); }

    @Override public void resume()
    {   Gdx.app.log( myrranCliente.LOG, "RESUME (Pantalla reanudada): " + getNombrePantalla()); }

    //El metodo Hide se ejecuta al cerrar la pantalla
    @Override public void hide()
    {   //Despues de cerrar la pantalla es neccesario liberar la memoria de todas las texturas que hayamos usado, por eso llamamos al metodo Dispose
        Gdx.app.log( myrranCliente.LOG, "HIDE (Cerrando pantalla): "+ getNombrePantalla());
        dispose();
    }

    //Es el metodo para liberar los recursos usados
    @Override public void dispose()
    {
        Gdx.app.log( myrranCliente.LOG, "DISPOSE (Liberando memoria): "+ getNombrePantalla());
        controlador.dispose();
    }
}
