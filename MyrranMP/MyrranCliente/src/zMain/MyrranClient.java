package zMain;// Created by Hanto on 08/04/2014.

import Data.MiscData;
import View.PantallaLibGDX;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MyrranClient extends Game
{
    public String LOG = MyrranClient.class.getSimpleName();
    public enum tipoPantalla { pantallaMenu, pantallaJuego }

    public static void main (String[] arg)
    {
        //PrintStream printStream = new PrintStream(new ConsolaOutputStream(Consolas.get().consolaPrincipal));
        //System.setOut(printStream);
        //System.setErr(printStream);

        System.setProperty("user.name","Myrran");

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Myrran";
        cfg.fullscreen = false;
        cfg.vSyncEnabled = false;
        cfg.foregroundFPS = 5000;
        //cfg.useGL30 = true;
        cfg.fullscreen = false;
        cfg.width = MiscData.GDX_Horizontal_Resolution;
        cfg.height = MiscData.GDX_Vertical_Resolution;

        cfg.addIcon(MiscData.RECURSOS_Atlas_Carpeta_Imagenes_Origen+MiscData.ATLAS_TexturasIconos_LOC+"FireBall.png", Files.FileType.Internal);
        new LwjglApplication(new MyrranClient(), cfg);
    }

    @Override public void create()
    {
        nagevarA(tipoPantalla.pantallaJuego);
    }

    public void nagevarA (tipoPantalla pantalla)
    {
        Screen screen;
        switch (pantalla)
        {
            case pantallaMenu:  screen = new PantallaLibGDX(this); break;
            case pantallaJuego: screen = new PantallaLibGDX(this); break;
            default:            screen = new PantallaLibGDX(this); break;
        }
        setScreen(screen);
    }
}
