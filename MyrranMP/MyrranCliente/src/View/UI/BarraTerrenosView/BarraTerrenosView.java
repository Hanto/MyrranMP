package View.UI.BarraTerrenosView;// Created by Hanto on 14/05/2014.

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class BarraTerrenosView extends Group
{
    protected Stage stage;

    protected Table tablaTerrenos;
    protected ScrollPane scrollPane;
    protected Array<IconoTerreno> barraIconos = new Array<>();

    public BarraTerrenosView (Stage stage)
    {
        this.stage = stage;

    }

}
