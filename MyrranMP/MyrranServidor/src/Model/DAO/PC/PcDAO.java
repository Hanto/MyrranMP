package Model.DAO.PC;// Created by Hanto on 13/04/2014.

import Model.Mobiles.PcModel;

public interface PcDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public void añadirPC (PcModel pc);
    public PcModel getPC (int iD);
    public void salvarPC();
    public void eliminarPC (PcModel pc);
}
