package DB.Recursos.PixiePCRecursos.DAO;// Created by Hanto on 01/05/2014.

import DB.Recursos.PixiePCRecursos.DTO.PixieRecursos;
import DB.Recursos.PixiePCRecursos.PixiePCRecursosLocalDB;
import View.Classes.Graficos.Pixie;

import java.util.HashMap;

public class PixiePCRecursosLocal implements PixiePCRecursosDAO
{
    public HashMap<String, PixiePCRecursosLocalDB.EquipoPC> listaDePCRazas = PixiePCRecursosLocalDB.get().listaDePCRazas;



    @Override public void salvarRazaPC(String iDRaza)
    {
        PixiePCRecursosLocalDB.EquipoPC equipoPC = new PixiePCRecursosLocalDB.EquipoPC();
        listaDePCRazas.put(iDRaza, equipoPC);
    }

    @Override public void salvarCuerpoPC(String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieCuerpo pixieCuerpo = new PixieRecursos.PixieCuerpo(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeCuerpos.put(iDCuerpo, pixieCuerpo);
    }

    @Override public void salvarCabezaPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieCuerpo = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeCabezas.put(iDCuerpo, pixieCuerpo);
    }

    @Override public void salvarYelmoPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeYelmos.put(iDCuerpo, pixieArmadura);
    }

    @Override public void salvarHombrerasPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeHombreras.put(iDCuerpo, pixieArmadura);
    }

    @Override public void salvarPetoPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDePetos.put(iDCuerpo, pixieArmadura);
    }

    @Override public void salvarPantalonesPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDePantalones.put(iDCuerpo, pixieArmadura);
    }

    @Override public void salvarGuantesPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeGuantes.put(iDCuerpo, pixieArmadura);
    }

    @Override public void salvarBotasPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeBotas.put(iDCuerpo, pixieArmadura);
    }

    @Override public void salvarCapasTraserasPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeCapasTraseras.put(iDCuerpo, pixieArmadura);
    }

    @Override public void salvarCapasFrontalesPC (String iDRaza, String iDCuerpo)
    {
        PixieRecursos.PixieArmadura pixieArmadura = new PixieRecursos.PixieArmadura(iDCuerpo);
        listaDePCRazas.get(iDRaza).listaDeCapasFrontales.put(iDCuerpo, pixieArmadura);
    }

    @Override public Pixie getCuerpoPC (String iDRaza, String iDCuerpo)
    {   return listaDePCRazas.get(iDRaza).listaDeCuerpos.get(iDCuerpo); }

    @Override public Pixie getCabezaPC(String iDRaza, String iDCabeza)
    {   return listaDePCRazas.get(iDRaza).listaDeCabezas.get(iDCabeza); }

    @Override public Pixie getYelmoPC(String iDRaza, String iDYelmo)
    {   return listaDePCRazas.get(iDRaza).listaDeYelmos.get(iDYelmo); }

    @Override public Pixie getHombrerasPC(String iDRaza, String iDHombreras)
    {   return listaDePCRazas.get(iDRaza).listaDeHombreras.get(iDHombreras); }

    @Override public Pixie getPetoPC(String iDRaza, String iDPeto)
    {   return listaDePCRazas.get(iDRaza).listaDePetos.get(iDPeto); }

    @Override public Pixie getPantalonesPC(String iDRaza, String iDPantalones)
    {   return listaDePCRazas.get(iDRaza).listaDePantalones.get(iDPantalones); }

    @Override public Pixie getGuantesPC(String iDRaza, String iDGuantes)
    {   return listaDePCRazas.get(iDRaza).listaDeGuantes.get(iDGuantes); }

    @Override public Pixie getBotasPC(String iDRaza, String iDGuantes)
    {   return listaDePCRazas.get(iDRaza).listaDeBotas.get(iDGuantes); }

    @Override public Pixie getCapaTraseraPC(String iDRaza, String iDCapaTrasera)
    {   return listaDePCRazas.get(iDRaza).listaDeCapasTraseras.get(iDCapaTrasera); }

    @Override public Pixie getCapaFrontalPC(String iDRaza, String iDCapaFrontal)
    {   return listaDePCRazas.get(iDRaza).listaDeCapasFrontales.get(iDCapaFrontal); }
}
