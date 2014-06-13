package DB.Recursos.PixiePCRecursos.DAO;// Created by Hanto on 01/05/2014.

import View.Classes.Graficos.Pixie;

public interface PixiePCRecursosDAO
{
    public void salvarRazaPC(String iDRaza);
    public void salvarCuerpoPC (String iDRaza, String iDCuerpo);
    public void salvarCabezaPC (String iDRaza, String iDCuerpo);
    public void salvarYelmoPC (String iDRaza, String iDCuerpo);
    public void salvarHombrerasPC (String iDRaza, String iDCuerpo);
    public void salvarPetoPC (String iDRaza, String iDCuerpo);
    public void salvarPantalonesPC (String iDRaza, String iDCuerpo);
    public void salvarGuantesPC (String iDRaza, String iDCuerpo);
    public void salvarBotasPC (String iDRaza, String iDCuerpo);
    public void salvarCapasTraserasPC (String iDRaza, String iDCuerpo);
    public void salvarCapasFrontalesPC (String iDRaza, String iDCuerpo);

    public Pixie getCuerpoPC (String iDRaza, String iDCuerpo);
    public Pixie getCabezaPC (String iDRaza, String iDCabeza);
    public Pixie getYelmoPC (String iDRaza, String iDYelmo);
    public Pixie getHombrerasPC (String iDRaza, String iDHombreras);
    public Pixie getPetoPC (String iDRaza, String iDPeto);
    public Pixie getPantalonesPC (String iDRaza, String iDPantalones);
    public Pixie getGuantesPC (String iDRaza, String iDGuantes);
    public Pixie getBotasPC (String iDRaza, String iDGuantes);
    public Pixie getCapaTraseraPC (String iDRaza, String iDCapaTrasera);
    public Pixie getCapaFrontalPC (String iDRaza, String iDCapaFrontal);

}
