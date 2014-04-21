package Controller.Input;
// @author Ivan Delgado Huertaimp

public class PlayerEstado
{
    protected Estado estado;                    //Estado actual, hace que mismos inputs produzcan diferentes resultados
    protected PlayerIO playerI;          //Player al que hace referencia, para poder consultar sus datos facilmente
    protected PlayerIO playerO;

    protected int iDEstado=0;


    //Clase ESTADO: define cada uno de los estados en los que puede estar un player:
    public interface Estado 
    {   public void procesarInput(PlayerEstado playerE);   //reacciona a los diferentes inputs segun el estado en el que estemos
        public void actualizar(PlayerEstado playerE);      //se ejecuta cada ciclo de update del player
    }
    //Constructor:
    public PlayerEstado(PlayerIO playerI, PlayerIO playerO)
    {
        this.playerI = playerI;
        this.playerO = playerO;
        estado = new Quieto(this);
    }
    public void procesarInput ()                { estado.procesarInput(this); }
    public void actualizar ()                   { estado.actualizar(this); }
    
    //Cada estado contiene un constructor con las restricciones iniciales de movimiento y con la animacion que usa ese estado
    //Desde cada estado un mismo Vista.Controller.Input produce efectos diferentes y conduce a estados diferentes tambien
    //cada estado dispone tambien de un metodo de Update, por si hay que actualizar barras de carga, o power ups, etc...
    
    //Estado QUIETO:
    //Cualquier accion que ejecutemos que no sea la de castear implicara un cambio de estado. CastearPC no es un estado en si
    //puesto que no dura nada, es instanteo, no es un estado. Todas las demas acciones duran tanto tiempo como se mantengan
    //el Casteo en cambio no, es una accion instantanea, por eso se decide no hacer de ella un Estado. Para que la animacion
    //del casteo se ejecute hasta el final se flagea como ininterrumpible, y para que no loopee sin parar, se le pone como respaldo
    //la base del estado en el que esta:
    public static class Quieto implements Estado
    {   public Quieto (PlayerEstado playerE)
        {   playerE.playerO.irArriba = false;
            playerE.playerO.irAbajo = false;
            playerE.playerO.irDerecha = false;
            playerE.playerO.irIzquierda = false;

            playerE.playerO.numAnimacion = 5;
            playerE.iDEstado = 5;
        }
        @Override public void procesarInput(PlayerEstado playerE)
        {
            playerE.playerO.castear = playerE.playerI.castear;
            playerE.playerO.stopCastear = playerE.playerI.stopCastear;
            playerE.playerO.screenClick.set(playerE.playerI.screenClick);

            if (playerE.playerI.disparar)       { playerE.estado = new Disparando(playerE); return; }
            if (playerE.playerI.irDerecha)      { playerE.estado = new Este(playerE); return; }
            if (playerE.playerI.irIzquierda)    { playerE.estado = new Oeste(playerE); return; }
            if (playerE.playerI.irArriba)       { playerE.estado = new Norte(playerE); return; }
            if (playerE.playerI.irAbajo)        { playerE.estado = new Sur(playerE); }
        }
        @Override public void actualizar (PlayerEstado playerE) { }
    }
    
    //Estado DISPARANDO:
    //Mantenemos el estado actual mientras se mantenga el boton de Disparar, pero antes de mostrar la animacion
    //de disparar, miramos si casteamos, por que la animacion de casteo tiene preferencia visualmente
    //Si, es así, cargamos primero la de casteo, y luego como respaldo la de disparo, de no castear solo se cargaria
    //la de disparo, que como no tiene fin, no necesita respaldo.
    //En caso de no disparar, hay que cambiar de estado:
    public static class Disparando implements Estado
    {   public Disparando (PlayerEstado playerE)
        {   playerE.playerO.irArriba = false;
            playerE.playerO.irAbajo = false;
            playerE.playerO.irDerecha = false;
            playerE.playerO.irIzquierda = false;

            playerE.playerO.numAnimacion = 6;
            playerE.iDEstado = 6;
        }
        @Override public void procesarInput(PlayerEstado playerE)
        {
            playerE.playerO.castear = playerE.playerI.castear;
            playerE.playerO.stopCastear = playerE.playerI.stopCastear;
            playerE.playerO.screenClick.set(playerE.playerI.screenClick);

            if (playerE.playerI.disparar)
            {
                playerE.playerO.numAnimacion =6;
                playerE.iDEstado = 6;
            }  
            else if (!playerE.playerI.disparar)
            {   //si no disparamos hay que cambiar de estado:
                if (playerE.playerI.irDerecha)      { playerE.estado = new Este(playerE); return; }
                if (playerE.playerI.irIzquierda)    { playerE.estado = new Oeste(playerE); return; }
                if (playerE.playerI.irArriba)       { playerE.estado = new Norte(playerE); return; }
                if (playerE.playerI.irAbajo)        { playerE.estado = new Sur(playerE); return; }
                if (playerE.playerI.estaQuieto())   { playerE.estado = new Quieto(playerE); }
            }
        }   //como mientras disparamos vamos apuntando, hay que actualizar la animacion todo el rato:
        @Override public void actualizar(PlayerEstado playerE)
        {   procesarInput(playerE); }
    }
    
    //Estado ARRIBA:
    //Los estados de movimiento estan separados por punto cardinal, para que ese punto cardinal tenga preferencia cuando
    //luego se altera el segundo eje, para emular la animacion Zelda. Es decir, las diagonal NE por ej, podria tomar la
    //animacion de la animacion Norte o la animacion ESTE, en nuestro caso, manda la primera direccion que pulsemos primero.
    //si pulsamos N y luego E, se vera la animacion N, en cambio si pulsamos E y luego N, se vera la animacion E.
    //esto lo conseguimos con los estados. Mientras domine el de una direccion, alteramos el segundo eje, y si cambia,
    //cambiamos de estado
    public static class Norte implements Estado
    {   public Norte (PlayerEstado playerE)
        {   playerE.playerO.irArriba = playerE.playerI.irArriba;
            playerE.playerO.irAbajo = false;
            playerE.playerO.irDerecha = playerE.playerI.irDerecha;
            playerE.playerO.irIzquierda = playerE.playerI.irIzquierda;

            playerE.playerO.numAnimacion = 2;
            playerE.iDEstado = 2;
        }
        @Override public void procesarInput(PlayerEstado playerE)
        {
            playerE.playerO.castear = playerE.playerI.castear;
            playerE.playerO.stopCastear = playerE.playerI.stopCastear;
            playerE.playerO.screenClick.set(playerE.playerI.screenClick);

            if (playerE.playerI.disparar)       { playerE.estado = new Disparando(playerE); return; }
            if (playerE.playerI.irAbajo)        { playerE.estado = new Sur(playerE); return; }
            if (!playerE.playerI.irArriba)
            {   
                if (playerE.playerI.irDerecha)  { playerE.estado = new Este(playerE); return; }
                if (playerE.playerI.irIzquierda){ playerE.estado = new Oeste(playerE); return; }
            }
            if (playerE.playerI.estaQuieto())   { playerE.estado = new Quieto(playerE); return; }
            if (playerE.playerI.irArriba)
            {   //Si vamos hacia arriba, pero cambia el movimiento lateral, mantenemos el estado y cambiamos el rumbo
                playerE.playerO.irIzquierda = playerE.playerI.irIzquierda;
                playerE.playerO.irDerecha = playerE.playerI.irDerecha;
            }
        }
        @Override public void actualizar(PlayerEstado playerE)  { }
    }
    
    //Estado ABAJO:
    public static class Sur implements Estado
    {   public Sur(PlayerEstado playerE)
        {   playerE.playerO.irArriba = false;
            playerE.playerO.irAbajo = playerE.playerI.irAbajo;
            playerE.playerO.irDerecha = playerE.playerI.irDerecha;
            playerE.playerO.irIzquierda = playerE.playerI.irIzquierda;

            playerE.playerO.numAnimacion = 3;
            playerE.iDEstado = 3;
        }
        @Override public void procesarInput(PlayerEstado playerE)
        {
            playerE.playerO.castear = playerE.playerI.castear;
            playerE.playerO.stopCastear = playerE.playerI.stopCastear;
            playerE.playerO.screenClick.set(playerE.playerI.screenClick);

            if (playerE.playerI.disparar)       { playerE.estado = new Disparando(playerE); return; }
            if (playerE.playerI.irArriba)       { playerE.estado = new Norte(playerE); return; }
            else if (!playerE.playerI.irAbajo)
            {
                if (playerE.playerI.irDerecha)   { playerE.estado = new Este(playerE); return; }
                if (playerE.playerI.irIzquierda) { playerE.estado = new Oeste(playerE); return; }
            }
            if (playerE.playerI.estaQuieto())   { playerE.estado = new Quieto(playerE); return; }
            else if (playerE.playerI.irAbajo)
            {   //Si vamos hacia la direccion del Estado, pero cambia el movimiento lateral, solo cambiamos el rumbo
                playerE.playerO.irIzquierda = playerE.playerI.irIzquierda;
                playerE.playerO.irDerecha = playerE.playerI.irDerecha;
            }
        }
        @Override public void actualizar(PlayerEstado playerE)  { }
    }
    
    //Estado IZQUIERDA:
    public static class Oeste implements Estado
    {   public Oeste (PlayerEstado playerE)
        {   playerE.playerO.irArriba = playerE.playerI.irArriba;
            playerE.playerO.irAbajo = playerE.playerI.irAbajo;
            playerE.playerO.irDerecha = false;
            playerE.playerO.irIzquierda = playerE.playerI.irIzquierda;

            playerE.playerO.numAnimacion = 0;
            playerE.iDEstado = 0;
        }
        @Override public void procesarInput(PlayerEstado playerE)
        {
            playerE.playerO.castear = playerE.playerI.castear;
            playerE.playerO.stopCastear = playerE.playerI.stopCastear;
            playerE.playerO.screenClick.set(playerE.playerI.screenClick);

            if (playerE.playerI.disparar)        { playerE.estado = new Disparando(playerE); return; }
            if (playerE.playerI.irDerecha)       { playerE.estado = new Este(playerE); return; }
            else if (!playerE.playerI.irIzquierda)
            {
                if (playerE.playerI.irArriba)    { playerE.estado = new Norte(playerE); return; }
                if (playerE.playerI.irAbajo)     { playerE.estado = new Sur(playerE); return; }
            }
            if (playerE.playerI.estaQuieto())   { playerE.estado = new Quieto(playerE); }
            else if (playerE.playerI.irIzquierda)
            {   //Si vamos hacia la direccion del Estado, pero cambia el movimiento lateral, solo cambiamos el rumbo
                playerE.playerO.irArriba = playerE.playerI.irArriba;
                playerE.playerO.irAbajo = playerE.playerI.irAbajo;
            }
        }
        @Override public void actualizar(PlayerEstado playerE)  { }
    }
    
    //Estado DERECHA:
    public static class Este implements Estado
    {   public Este (PlayerEstado playerE)
        {   playerE.playerO.irArriba = playerE.playerI.irArriba;
            playerE.playerO.irAbajo = playerE.playerI.irAbajo;
            playerE.playerO.irDerecha = playerE.playerI.irDerecha;
            playerE.playerO.irIzquierda = false;

            playerE.playerO.numAnimacion = 1;
            playerE.iDEstado = 1;
        }
        @Override public void procesarInput(PlayerEstado playerE)
        {
            playerE.playerO.castear = playerE.playerI.castear;
            playerE.playerO.stopCastear = playerE.playerI.stopCastear;
            playerE.playerO.screenClick.set(playerE.playerI.screenClick);

            if (playerE.playerI.disparar)       { playerE.estado = new Disparando(playerE); return; }
            if (playerE.playerI.irIzquierda)    { playerE.estado = new Oeste(playerE); return; }
            else if (!playerE.playerI.irDerecha)
            {
                if (playerE.playerI.irArriba)   { playerE.estado = new Norte(playerE); return; }
                if (playerE.playerI.irAbajo)    { playerE.estado = new Sur(playerE); return; }
            }
            if (playerE.playerI.estaQuieto())   { playerE.estado = new Quieto(playerE); }
            else if (playerE.playerI.irDerecha)
            {   //Si vamos hacia la direccion del Estado, pero cambia el movimiento lateral, solo cambiamos el rumbo
                playerE.playerO.irArriba = playerE.playerI.irArriba;
                playerE.playerO.irAbajo = playerE.playerI.irAbajo;
            }
        }
        
        private float contadorPolvo = 0;
        
        @Override public void actualizar(PlayerEstado playerE)
        {   //para que deje polvo despues de caminar un segundo en la misma direccion
            //contadorPolvo+= Gdx.graphics.getDeltaTime();
            //if (contadorPolvo >= 1)
            //{
                //contadorPolvo = 0;
                //Pixie polvo = new Pixie(RSC.polvoPasos);
                //polvo.setPosition(playerE.player.getX(), playerE.player.getY());
                //polvo.setAnimacion(0, true);
                //playerE.player.getActor().getStage().addActor(polvo);
            //}
        }
    }
}
