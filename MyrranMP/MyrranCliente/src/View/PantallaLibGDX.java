package View;// Created by Hanto on 08/04/2014.

import Controller.Controlador;
import DB.Datos.BDebuff.BDebuffLocalDB;
import DB.Datos.Spell.SpellLocalDB;
import DB.Datos.Terreno.TerrenoLocalDB;
import DB.Datos.TipoBdDebuff.TipoBDebuffLocalDB;
import DB.Datos.TipoSpell.TipoSpellLocalDB;
import DB.Recursos.AccionRecursos.AccionRecursosLocalDB;
import DB.Recursos.AtlasRecursos.AtlasRecursosLocalDB;
import DB.Recursos.FuentesRecursos.FuentesRecursosLocalDB;
import DB.Recursos.MiscRecursos.MiscRecursosLocalDB;
import DB.Recursos.PixiePCRecursos.PixiePCRecursosLocalDB;
import DB.Recursos.SkillRecursos.SkillRecursosLocalDB;
import DB.Recursos.TerrenoRecursos.TerrenoRecursosLocalDB;
import Model.GameState.Mundo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import zMain.MyrranClient;


public class PantallaLibGDX implements Screen
{
    private MyrranClient myrranCliente;
    private Controlador controlador;

    public String getNombrePantalla()           { return ((Object)this).getClass().getSimpleName(); }

    public PantallaLibGDX(MyrranClient myrranCliente)
    {
        this.myrranCliente = myrranCliente;

        TipoBDebuffLocalDB.get();
        BDebuffLocalDB.get();
        TipoSpellLocalDB.get();
        SpellLocalDB.get();
        TerrenoLocalDB.get();

        AtlasRecursosLocalDB.get();
        MiscRecursosLocalDB.get();
        FuentesRecursosLocalDB.get();
        TerrenoRecursosLocalDB.get();
        AccionRecursosLocalDB.get();
        SkillRecursosLocalDB.get();
        PixiePCRecursosLocalDB.get();

        Mundo mundo = new Mundo();
        controlador = new Controlador(mundo);
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
