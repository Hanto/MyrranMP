package View.Classes.UI.Comun;// Created by Hanto on 16/05/2014.

import Data.Misc.MiscData;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class VentanaScrollListener extends DragListener
{
    private ScrollPane scrollPane;

    public VentanaScrollListener(ScrollPane scrollPane)
    {   this.scrollPane = scrollPane; }

    @Override public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
    { scrollPane.getStage().setScrollFocus(scrollPane); }

    //para que no haga scroll cuando el raton este fuera de la ventana de terrenos
    @Override public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor)
    { if (scrollPane.getStage() != null) scrollPane.getStage().setScrollFocus(null); }

    //Añdimos un listener para el scroll, para configurar su velocidad, hay que parar su propagacion con event.stop() para que no salte el que esta programado de base
    @Override public boolean scrolled(InputEvent event, float x, float y, int amount)
    { scrollPane.setScrollY(scrollPane.getScrollY()+ MiscData.TILESIZE*3*amount); event.stop(); return true; }
}
