package Model.GameState;// Created by Hanto on 26/06/2014.

import Core.Consola;

public class Consolas
{
    private static class Singleton      { private static final Consolas get = new Consolas(); }
    public static Consolas get()        { return Singleton.get; }

    public Consola consolaPrincipal;

    private Consolas()
    {   consolaPrincipal = new Consola(); }
}
