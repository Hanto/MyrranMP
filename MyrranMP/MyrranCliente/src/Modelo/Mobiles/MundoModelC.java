package Modelo.Mobiles;// Created by Hanto on 11/04/2014.

public interface MundoModelC extends MundoModel
{
    //GET:
    public PlayerModel getPlayer();
    //SET:
    public void añadirPlayer(int connectionID);
    public void eliminarPlayer();
    public void moverPlayer(float x, float y);
}
