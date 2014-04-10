package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import Modelo.Mobiles.Mundo;
import View.Graficos.Recursos;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import zMain.MyrranClient;


public class PantallaJuego implements Screen
{
    private MyrranClient myrranCliente;
    private Controlador controlador;

    public static final Texture texture = new Texture(Gdx.files.internal("Images/Spell Icons/FireBall.png"));

    public String getNombrePantalla()           { return ((Object)this).getClass().getSimpleName(); }

    public PantallaJuego (MyrranClient myrranCliente)
    {
        this.myrranCliente = myrranCliente;
        controlador = new Controlador(new Mundo());

        Recursos.get().crearAtlas();
    }

    @Override public void show()
    {   Gdx.app.log( myrranCliente.LOG, "SHOW (Inicializando Screen): " + getNombrePantalla()); }

    @Override
    public void render(float delta)
    {
        controlador.render(delta);
    }

    @Override
    public void resize(int anchura, int altura)
    {   Gdx.app.log( myrranCliente.LOG, "RESIZE (Redimensionando Screen): "+ getNombrePantalla() +" a: "+anchura+" x "+altura); }

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
        //Antes de liberar los recursos nos aseguramos que esten llenos, si no, da error
    }
}
