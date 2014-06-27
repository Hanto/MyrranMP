package View.Classes.Graficos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * @author Ivan Delgado Huerta
 */

//clase para el tratamiento grafico de textos en pantalla:
public class Texto extends Actor
{
    private LabelStyle estiloNormal;        //Fuente y Color del texto normal
    private LabelStyle estiloSombra;        //Fuente y Color del texto de la sombra

    public Label textoNormal;              //Resultado final del Texto final
    public Label textoSombra;              //Resultado final del Texto sombra

    private int centradoHorizontal;
    private int centradoVertical;

    private int offSetX = 0;
    private int offSetY = 0;
    private int relieveSombra;

    //CONSTRUCTOR Metodo para crear texto con un formato determinado y encapsularlo todo en un grupo:
    public Texto (String texto, BitmapFont fuente, Color colorNormal, Color colorSombra, int centradoHorizontal, int centradoVertical, int relieve )
    {
        this.centradoHorizontal = centradoHorizontal;
        this.centradoVertical = centradoVertical;
        this.relieveSombra = relieve;

        //Creamos el estilo para los dos tipos de texto, el de la sombra y el del texto normal:
        estiloNormal = new LabelStyle(fuente, colorNormal);
        estiloSombra = new LabelStyle(fuente, colorSombra);
        //Creamos el texto segun segun los dos estilo (el normal y el sombra):
        textoNormal = new Label(texto, estiloNormal);
        textoSombra = new Label(texto, estiloSombra);

        switch (centradoHorizontal)
        {//Segun el tipo de centradoHorizontal ajustamos el eje de coordenadas X:
            case Align.right:   { offSetX = - (int)textoNormal.getWidth(); break; }
            case Align.center:  { offSetX = - (int)textoNormal.getWidth()/2; ;break;}
            case Align.left:    { break; }
            default:            { break; }
        }
        switch (centradoVertical)
        {//Segun el tipo de centradoVertical ajustamos el eje de coordenadas Y:
            case Align.top:     { offSetY = - (int)textoNormal.getHeight(); break; }
            case Align.center:  { offSetY = - (int)textoNormal.getHeight()/2; break; }
            case Align.bottom:  { break; }
            default:            { break; }
        }
        this.setHeight(textoNormal.getHeight()+relieve);
        this.setWidth(textoNormal.getWidth()+relieve);
    }

    public void setCentrado ( int centradoHorizontal, int centradoVertical)
    {
        switch (centradoHorizontal)
        {//Segun el tipo de centradoHorizontal ajustamos el eje de coordenadas X:
            case Align.right:   { offSetX = - (int)textoNormal.getWidth(); break; }
            case Align.center:  { offSetX = - (int)textoNormal.getWidth()/2; break; }
            case Align.left:    { break; }
            default:            { break; }
        }

        switch (centradoVertical)
        {//Segun el tipo de centradoVertical ajustamos el eje de coordenadas Y:
            case Align.top:     { offSetY = - (int)textoNormal.getHeight(); break; }
            case Align.center:  { offSetY = - (int)textoNormal.getHeight()/2; break; }
            case Align.bottom:  { break; }
            default:            { break; }
        }
        //Situamos el texto normal y el texto sombra en las coordenadas generadas segun el tipo de centrado, y añadimos ambos textos al grupo grupoTexto:
    }

    public void setTexto ( String texto )
    {
        textoNormal.setText(texto);
        textoSombra.setText(texto);
        //por algun motivo si cambiamos el texto su tamaño no se actualiza, así que tenemos que generar un label getTipoSpellDAO con ese texto para calcular el tamaño
        Label ltexto = new Label(texto, estiloNormal);
        textoNormal.setWidth(ltexto.getWidth() +relieveSombra);
        textoSombra.setWidth(ltexto.getWidth() +relieveSombra);
        this.setWidth(ltexto.getWidth() +relieveSombra);
        this.setHeight(ltexto.getHeight() +relieveSombra);
        setCentrado (this.centradoHorizontal, this.centradoVertical);
    }

    public void setFuente ( BitmapFont fuente )
    {   //no se puede hacer un estiloNormal = new LabelStyle () con los nuevos parametros, hay que acceder a los campos y modificarlos. Si se hace un new, se crean nuevas referencias y los actores
        //no se actualizan.
        estiloNormal.font = fuente;
        estiloSombra.font = fuente;

        textoNormal.setStyle(estiloNormal);
        textoSombra.setStyle(estiloSombra);
    }

    public void setColorNormal ( Color color )
    {
        estiloNormal.fontColor = color;
        textoNormal.setStyle(estiloNormal);
    }

    public void setColorSombra ( Color color)
    {
        estiloSombra.fontColor = color;
        textoSombra.setStyle(estiloSombra);
    }

    public void scrollingCombatText (Stage stage, float duracion)
    {
        stage.addActor(this);
        this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0);
        this.addAction(Actions.sequence(Actions.fadeIn(duracion / 4), Actions.delay(duracion / 4 * 2), Actions.fadeOut(duracion / 4)));
        this.addAction(Actions.sequence(Actions.moveBy(0f, 40f, duracion, Interpolation.linear), Actions.removeActor()));
    }

    public void scrollingCombatText (Group group, float duracion)
    {
        this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0);
        this.addAction(Actions.sequence(Actions.fadeIn(duracion / 4), Actions.delay(duracion / 4 * 2), Actions.fadeOut(duracion / 4)));
        this.addAction(Actions.sequence(Actions.moveBy(0f, 40f, duracion, Interpolation.sine), Actions.removeActor()));
        group.addActor(this);
    }

    //Para cuando no queremos crear la entidad para su posterior modificabilidad, si no que queremos crearla rapido:
    public static void printTexto (String texto, BitmapFont fuente, Color colorNormal, Color colorSombra, float posX, float posY, int centradoHorizontal, int centradoVertical, int relieve, Group group)
    {   //Creamos el estilo para los dos tipos de texto, el de la sombra y el del texto normal:
        LabelStyle estiloNormal = new LabelStyle(fuente, colorNormal);
        LabelStyle estiloSombra = new LabelStyle(fuente, colorSombra);
        //Creamos el texto segun segun los dos estilo (el normal y el sombra):
        Label textoNormal = new Label(texto, estiloNormal);
        Label textoSombra = new Label(texto, estiloSombra);

        switch (centradoHorizontal)
        {//Segun el tipo de centradoHorizontal ajustamos el eje de coordenadas X:
            case Align.right:   { posX = posX - (int)textoNormal.getWidth(); break; }
            case Align.center:  { posX = posX - (int)textoNormal.getWidth()/2; break; }
            case Align.left:    { break; }
            default:            { break; }
        }
        switch (centradoVertical)
        {//Segun el tipo de centradoVertical ajustamos el eje de coordenadas Y:
            case Align.top:     { posY = posY -(int)textoNormal.getHeight(); break; }
            case Align.center:  { posY = posY -(int)textoNormal.getHeight()/2; break; }
            case Align.bottom:  { break; }
            default:            { break; }
        }
        //Situamos el texto normal y el texto sombra en las coordenadas generadas segun el tipo de centrado, y añadimos ambos textos al grupo grupoTexto:
        textoSombra.setPosition(posX + relieve, posY - relieve);
        textoNormal.setPosition(posX, posY);
        group.addActor(textoSombra);
        group.addActor(textoNormal);
    }

    @Override public void draw (Batch batch, float alpha)
    {
        //Posicion Elementos:
        textoNormal.setPosition(getX() + offSetX, getY() + offSetY);
        textoSombra.setPosition(getX() + offSetX + relieveSombra, getY() + offSetY - relieveSombra);

        //Dibujado Elementos:
        textoSombra.draw(batch, this.getColor().a);
        textoNormal.draw(batch, this.getColor().a);
    }
}
