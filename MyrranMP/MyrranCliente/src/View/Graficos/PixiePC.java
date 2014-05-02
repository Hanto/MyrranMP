package View.Graficos;

import Recursos.DAO.PixiePCRecursos.PixiePCRecursosDAO;
import Recursos.DAO.RSC;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


//* @author Ivan Delgado Huerta
public class PixiePC extends Actor
{
    //DATOS Animacion:
    protected String iDRaza;                        //Raza a la que pertecenen las animaciones del jugador (cuerpos y equipo)
    protected int numAnimacion;                     //Animacion que se esta ejecutando en este momento
    //PIXIES PARTES DEL CUERPO:
    protected Pixie cuerpo;                         //Pixie que contiene las animaciones del cuerpo;
    protected Pixie cabeza;                         //Pixie que contiene las animaciones de la cabeza;
    protected Pixie yelmo;                          //Pixie que contiene las animaciones de la armadura: Yelmo
    protected Pixie hombreras;                      //Pixie que contiene las animaciones de la armadura: Hombreras
    protected Pixie peto;                           //Pixie que contiene las animaciones de la armadura: Peto
    protected Pixie pantalones;                     //Pixie que contiene las animaciones de la armadura: Pantalones
    protected Pixie guantes;                        //Pixie que contiene las animaciones de la armadura: Guantes
    protected Pixie botas;                          //Pixie que contiene las animaciones de la armadura: Botas
    protected Pixie capaTrasera;                    //Pixie que contiene las animaciones de la armadura: Capa Trasera
    protected Pixie capaFrontal;                    //Pixie que contiene las animaciones de la armadura: Capa Frontal
    //SPRITES ESPECIALES:
    //protected Sprite sombra;                        //Imagen que Contiene la sombra del personaje
    
    public int getNumAnimacion()                    { return numAnimacion; }

    
    //CONSTRUCTOR: inicializa y crea la animacion base, con el cuerpo de humano y el set desnudo
    public PixiePC(String iDRaza)
    {
        this.iDRaza = iDRaza;

        PixiePCRecursosDAO pixiePCDAO = RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO();

        cuerpo = new Pixie(pixiePCDAO.getCuerpoPC(iDRaza, iDRaza));
        cabeza = new Pixie(pixiePCDAO.getCabezaPC(iDRaza, "Desnudo"));
        yelmo = new Pixie(pixiePCDAO.getYelmoPC(iDRaza, "Desnudo")); yelmo.setOffset(-24, -24);

        peto = new Pixie(pixiePCDAO.getPetoPC(iDRaza, "Desnudo"));
        pantalones = new Pixie(pixiePCDAO.getPantalonesPC(iDRaza, "Desnudo"));
        guantes = new Pixie(pixiePCDAO.getGuantesPC(iDRaza, "Desnudo"));
        botas = new Pixie(pixiePCDAO.getBotasPC(iDRaza, "Desnudo"));
        hombreras = new Pixie(pixiePCDAO.getHombrerasPC(iDRaza, "Desnudo"));
        capaTrasera = new Pixie(pixiePCDAO.getCapaTraseraPC(iDRaza, "Desnudo")); capaTrasera.setOffset(-24, -24);
        capaFrontal = new Pixie(pixiePCDAO.getCapaFrontalPC(iDRaza, "Desnudo")); capaFrontal.setOffset(-24, -24);
        
        //sombra = new Sprite(RSC.sombraPlayer);
                
        this.setHeight(cuerpo.getHeight());
        this.setWidth(cuerpo.getWidth());
    }
    
    public void setAnimacion (int numAnimacion, boolean forzarAnimacion)
    {   //SELECCIONAR ANIMACION: Metodo para sincronizar todas las animaciones de todos los slots de armadura a la animacion que toque ejecutar segun el movimiento principal que ejecute el personaje
        if (this.numAnimacion == numAnimacion && !forzarAnimacion) return;  //Si la animacion no cambia, no hay que hacer nada, que todo se siga animando como siempre, asi se evita malgastar potencia de calculo
        this.numAnimacion = numAnimacion;                                   //En caso negativo, salvamos el valor de la nueva animacion para poderlo comprobar en un futuro, y aplicamos la nueva animacion a todas las partes
        
        cuerpo.setAnimacion(numAnimacion, forzarAnimacion);
        cabeza.setAnimacion(numAnimacion, forzarAnimacion);
        yelmo.setAnimacion(numAnimacion, forzarAnimacion);
        peto.setAnimacion(numAnimacion, forzarAnimacion);
        pantalones.setAnimacion(numAnimacion, forzarAnimacion);
        guantes.setAnimacion(numAnimacion, forzarAnimacion);
        botas.setAnimacion(numAnimacion, forzarAnimacion);
        hombreras.setAnimacion(numAnimacion, forzarAnimacion);
        capaTrasera.setAnimacion(numAnimacion, forzarAnimacion);
        capaFrontal.setAnimacion(numAnimacion, forzarAnimacion);
    }
    
    //VESTIR SLOT ARMADURA: Metodos para vestirse un slot de armadura, carga esa armadura de la lista de armadura de Mundo, creando una copia de la pieza que deseamos
    //Luego la movemos a la posicion donde esta el personaje, le damos los datos del offset y se tercia, y si debe usar las coordenadas de la camara para el caso que sea un Pixie seguido por esta
    //para finalmente activar la animacion que esta ejecutando todo el cuerpo, con booleano de forzarSincronizacion, para que reinicien todas las animaciones, y asi todas empiecen por el mismo frame
    public void setCuerpo (String iDSlot)           //CUERPO:
    {   cuerpo = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getCuerpoPC(iDRaza,iDSlot));
        cuerpo.setPosition(getX(), getY());
        setAnimacion(numAnimacion, true);
    }
    public void setCabeza (String iDSlot)           //CABEZA:
    {   cabeza = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getCabezaPC(iDRaza,iDSlot));
        cabeza.setPosition(getX(), getY());
        setAnimacion(numAnimacion, true);
    }
    public void setYelmo (String iDSlot)          //YELMO:
    {   yelmo = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getYelmoPC(iDRaza,iDSlot));
        yelmo.setPosition(getX(), getY());
        yelmo.setOffset(-24, -24);
        setAnimacion(numAnimacion, true);
    }
    public void setPeto (String iDSlot)           //PETO:
    {   peto = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getPetoPC(iDRaza,iDSlot));
        peto.setPosition(getX(), getY()); 
        setAnimacion(numAnimacion, true); 
    }
    public void setPantalones (String iDSlot)     //PANTALONES:
    {   pantalones = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getPantalonesPC(iDRaza,iDSlot));
        pantalones.setPosition(getX(), getY()); 
        setAnimacion(numAnimacion, true);
    }
    public void setGuantes (String iDSlot)        //GUANTES:
    {   guantes = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getGuantesPC(iDRaza,iDSlot));
        guantes.setPosition(getX(), getY()); 
        setAnimacion(numAnimacion, true);
    }
    public void setBotas (String iDSlot)          //BOTAS:
    {   botas = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getBotasPC(iDRaza,iDSlot));
        botas.setPosition(getX(), getY()); 
        setAnimacion(numAnimacion, true);
    }
    public void setHombreras (String iDSlot)      //HOMBRERAS:
    {   hombreras = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getHombrerasPC(iDRaza,iDSlot));
        hombreras.setPosition(getX(), getY());
        setAnimacion(numAnimacion, true);
    }
    public void setCapaTrasera (String iDSlot)    //CAPA TRASERA:
    {   capaTrasera = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getCapaTraseraPC(iDRaza,iDSlot));
        capaTrasera.setPosition(getX(), getY());
        capaTrasera.setOffset(-24, -24);
        setAnimacion(numAnimacion, true);
    }
    public void setCapaFrontal (String iDSlot)    //CAPA FRONTAL:
    {   capaFrontal = new Pixie(RSC.pixiePCRecursosDAO.getPixiePCRecursosDAO().getCapaFrontalPC(iDRaza,iDSlot));
        capaFrontal.setPosition(getX(), getY());
        capaFrontal.setOffset(-24, -24);
        setAnimacion(numAnimacion, true);
    }
       
    @Override
    public void act (float delta)
    {
        super.act(delta);
        //sombra.setPosition(this.getMapTileX()+8, this.getMapTileY()-5);
    }
    
    @Override
    public void draw (Batch batch, float alpha)
    {   //El orden de dibujado cambia segun la direccion en la que te muevas, ya que las hombreras por ejemplo deben ser tapadas por la capa al moverse en las diagonales superiores:
        if (numAnimacion == 0 || numAnimacion == 1 || numAnimacion == 2 || numAnimacion == 10 )
        {   
            //sombra.draw(batch, alpha);
            capaFrontal.draw(batch, alpha);
            cuerpo.draw(batch, alpha);
            cabeza.draw(batch, alpha);
            yelmo.draw(batch, alpha);
            peto.draw(batch, alpha);
            pantalones.draw(batch, alpha);
            guantes.draw(batch, alpha);
            hombreras.draw(batch, alpha);
            botas.draw(batch, alpha);
            capaTrasera.draw(batch, alpha);
        }
        else
        {   
            //sombra.draw(batch, alpha);
            capaFrontal.draw(batch, alpha);
            capaTrasera.draw(batch, alpha);
            cuerpo.draw(batch, alpha);
            cabeza.draw(batch, alpha);
            yelmo.draw(batch, alpha);
            peto.draw(batch, alpha);
            pantalones.draw(batch, alpha);
            guantes.draw(batch, alpha);
            hombreras.draw(batch, alpha);
            botas.draw(batch, alpha);
        }
    }
}
