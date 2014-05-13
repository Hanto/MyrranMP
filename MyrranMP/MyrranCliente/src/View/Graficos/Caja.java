package View.Graficos;// Created by Hanto on 12/05/2014.

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Caja extends Actor
{
    private Vector2 esquinaSupIzda = new Vector2();
    private Vector2 tamaño = new Vector2();

    public void setEsquinaSupIzda(float x, float y) { esquinaSupIzda.set(x,y); }
    public void setTamaño(float ancho, float alto)  { tamaño.set(ancho, alto); }

    public Vector2 getEsquinaSupIzda()              { return esquinaSupIzda; }
    public Vector2 getTamaño()                      { return tamaño; }

    private ShapeRenderer shape = new ShapeRenderer();


    public void setCaja (float esquinaSupIzdaX, float esquinaSupIzdaY, float ancho, float alto)
    {
        setEsquinaSupIzda(esquinaSupIzdaX, esquinaSupIzdaY);
        setTamaño(ancho, alto);
    }

    @Override public void draw (Batch batch, float parentAlpha)
    {
        batch.end();
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.ORANGE);

        shape.rect((int)esquinaSupIzda.x, (int)esquinaSupIzda.y, (int) tamaño.x, (int) tamaño.y);

        shape.end();
        batch.begin();
    }
}
