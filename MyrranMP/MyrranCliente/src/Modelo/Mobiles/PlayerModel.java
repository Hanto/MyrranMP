package Modelo.Mobiles;// Created by Hanto on 10/04/2014.

import Controller.Input.PlayerIO;
import Models.PCModel;

public interface PlayerModel extends PCModel
{
    public void setInput (PlayerIO playerInput);
    public void actualizar (float delta);
}
