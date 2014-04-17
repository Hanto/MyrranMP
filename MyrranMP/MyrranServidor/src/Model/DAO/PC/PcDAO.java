package Model.DAO.PC;// Created by Hanto on 13/04/2014.

import Model.Mobiles.PC;

public interface PcDAO
{   //C.R.U.D: Create Retrieve Update Delete
    public void añadirPC (PC pc);
    public PC getPC (int iD);
    public void salvarPC();
    public void eliminarPC (PC pc);
}
