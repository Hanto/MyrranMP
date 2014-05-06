package Model.Classes.Acciones.TiposAccion;// Created by Hanto on 05/05/2014.

import Model.Classes.Acciones.Accion;
import Model.Classes.Acciones.IconoInterface;
import Controller.Input.PlayerEstado;
import Model.Classes.Mobiles.Player;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class IrNorte extends Accion implements IconoInterface
{
    public IrNorte()
    {   iD = getClass().getSimpleName(); }

    @Override public void ejecutarAccion(Player player, PlayerEstado playerE)
    {   playerE.getPlayerI().irArriba = true;
        playerE.procesarInput();
        player.setInput(playerE.getPlayerO());
    }

    @Override public TextureRegion getImagen()
    {   return null; }

    @Override public String getID()
    {   return "ir Norte"; }
}
