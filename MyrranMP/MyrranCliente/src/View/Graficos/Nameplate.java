package View.Graficos;

import View.Actores.ActorRecursos;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;

// @author Ivan Delgado Huerta
public class Nameplate extends Group
{
    //NAMEPLATES
    protected Sprite barraVidaTotal;                //Imagen que contiene el nameplateTotal de la vida del PlayerModel
    protected Sprite barraVidaActual;               //Imagen que contiene el fondo de la vida del nameplateTotal del PlayerModel
    protected Sprite barraCasteoTotal;
    protected Sprite barraCasteoActual;
        
    public Nameplate()
    {
        barraVidaTotal = new Sprite(ActorRecursos.get().nameplateTotal);
        barraVidaActual = new Sprite(ActorRecursos.get().nameplateActual);
        barraCasteoTotal = new Sprite(ActorRecursos.get().nameplateTotal);
        barraCasteoActual = new Sprite(ActorRecursos.get().nameplateActual);
        barraVidaTotal.setColor(Color.GREEN);
        barraCasteoTotal.setColor(Color.RED);

        int alto = (int)barraVidaTotal.getHeight();
        int ancho = (int)barraVidaTotal.getWidth();
        //colocamos la barra de vida encima de la de casteo, -1, para que los rebordes se fusionen y no quede doble reborde
        barraVidaTotal.setPosition(0, alto-1);
        barraVidaActual.setPosition(ancho-1, alto-1);
        barraCasteoActual.setPosition(ancho-1, 0);
        
        this.setWidth(ancho);
        this.setHeight(alto);
        this.setBounds(0, 0, ancho, alto);
    }

    public void setHPsPercent (float HPsPercent)
    {
        float tamaño = (1 - HPsPercent) * this.getWidth();
        if (tamaño != barraVidaActual.getWidth()) barraVidaActual.setSize(-(int) tamaño, this.getHeight());
    }

    public void setCastingTimePercent (float castingTimePercent)
    {
        float tamaño = (1-castingTimePercent)*this.getWidth();
        if (tamaño != barraCasteoActual.getWidth()) barraCasteoActual.setSize(-(int)tamaño, this.getHeight());
    }
   
    @Override public void act(float delta)
    {
        float alto = this.getHeight();
        float ancho = this.getWidth();
        
        super.act(delta);
        barraVidaTotal.setPosition(this.getX(), this.getY()+alto-1);
        barraVidaActual.setPosition(this.getX()+ancho-1, this.getY()+alto-1);
        barraCasteoTotal.setPosition(this.getX(), this.getY());
        barraCasteoActual.setPosition(this.getX()+ancho-1, this.getY());        
    }
    
    @Override public void draw (Batch batch, float alpha)
    {
        barraVidaTotal.draw(batch, alpha);
        barraVidaActual.draw(batch, alpha);
        barraCasteoTotal.draw(batch, alpha);
        barraCasteoActual.draw(batch, alpha);
    }
}
