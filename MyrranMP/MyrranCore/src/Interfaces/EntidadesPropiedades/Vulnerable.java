package Interfaces.EntidadesPropiedades;// Created by Ladrim on 18/04/2014.

public interface Vulnerable
{
    //GET:
    public float getActualHPs();
    public float getMaxHPs();

    //SET:
    public void modificarHPs(float HPs);
    public void setActualHPs(float HPs);
    public void setMaxHPs(float HPs);
}