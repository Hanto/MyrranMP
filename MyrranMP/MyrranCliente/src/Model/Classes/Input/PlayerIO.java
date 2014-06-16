package Model.Classes.Input;// Created by Hanto on 11/04/2014.

import Interfaces.Input.PlayerIOI;

public class PlayerIO implements PlayerIOI
{
    public int screenX;
    public int screenY;

    public boolean irArriba = false;
    public boolean irAbajo = false;
    public boolean irDerecha = false;
    public boolean irIzquierda = false;
    public boolean disparar = false;
    public boolean startCastear = false;
    public boolean stopCastear = false;
    public int numAnimacion = 5;

    @Override public void setScreenX(int screenX)           { this.screenX = screenX; }
    @Override public void setScreenY(int screenY)           { this.screenY = screenY; }
    @Override public void setIrArriba(boolean b)            { irArriba = b; }
    @Override public void setIrAbajo(boolean b)             { irAbajo = b; }
    @Override public void setirDerecha(boolean b)           { irDerecha = b; }
    @Override public void setIrIzquierda(boolean b)         { irIzquierda = b; }
    @Override public void setDisparar(boolean b)            { disparar = b; }
    @Override public void setStartCastear(boolean b)        { startCastear = b; }
    @Override public void setStopCastear(boolean b)         { stopCastear = b; }
    @Override public void setNumAnimacion(int numAnimacion) { this.numAnimacion = numAnimacion; }

    @Override public int getScreenX()                       { return screenX; }
    @Override public int getScreenY()                       { return screenY; }
    @Override public boolean getIrArriba()                  { return irArriba; }
    @Override public boolean getIrAbajo()                   { return irAbajo; }
    @Override public boolean getIrDerecha()                 { return irDerecha; }
    @Override public boolean getirIzquierda()               { return irIzquierda; }
    @Override public boolean getDisparar()                  { return disparar; }
    @Override public boolean getStartCastear()              { return startCastear; }
    @Override public boolean getStopCastear()               { return stopCastear; }
    @Override public int getNumAnimacion()                  { return numAnimacion; }

    @Override public boolean estaQuieto()
    {   return (!irArriba && !irAbajo && !irDerecha && !irIzquierda); }
}
