package Model.Classes.UIO;// Created by Hanto on 11/04/2014.

import com.badlogic.gdx.Input;

public class PlayerIO
{
    public int screenX;
    public int screenY;

    public int teclaArriba = Input.Keys.W;
    public int teclaAbajo = Input.Keys.S;
    public int teclaDerecha = Input.Keys.D;
    public int teclaIzquierda = Input.Keys.A;

    public boolean irArriba = false;
    public boolean irAbajo = false;
    public boolean irDerecha = false;
    public boolean irIzquierda = false;
    public boolean disparar = false;

    public boolean startCastear = false;
    public boolean stopCastear = false;

    public int numAnimacion = 5;

    public boolean estaQuieto()
    {   return (!irArriba && !irAbajo && !irDerecha && !irIzquierda); }
}
