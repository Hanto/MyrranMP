package Interfaces.UI.BarraAcciones;// Created by Hanto on 13/05/2014.

import Interfaces.EntidadesPropiedades.CasterConTalentos;
import Interfaces.UI.Acciones.AccionI;

public interface ListaAccionesI
{
    public AccionI getAccion(int posX, int posY);
    public CasterConTalentos getCaster();
}
