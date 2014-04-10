package Controller.Network;// Created by Hanto on 10/04/2014.

public interface NetServer
{
    public void enviarACliente(int connectionID, Object obj);
    public void enviarATodosClientes(Object obj);
    public void enviarATodosClientesMenosUno(int connectionID, Object obj);
}
