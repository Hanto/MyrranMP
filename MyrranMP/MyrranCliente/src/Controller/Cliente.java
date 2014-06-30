package Controller;// Created by Hanto on 08/04/2014.

import DTO.NetDTO;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Cliente extends Client
{
    public Controlador controlador;
    public String host;

    public Cliente (Controlador controlador)
    {
        super(16*1024, 128*1024);
        this.controlador = controlador;

        NetDTO.register(this);
        this.start();

        //Para activar el log completo de mensajes:
        //Log.TRACE();

        this.addListener
            (new Listener.QueuedListener
                (new Listener()
                {
                     @Override public void connected (Connection con)   { }
                     @Override public void received (Connection con, Object obj) { procesarMensajeServidor(con, obj);}
                     @Override public void disconnected (Connection con) { }
                })
            {
                @Override protected void queue(Runnable runnable)
                {   Gdx.app.postRunnable(runnable); }
            });

        host = "localhost"; //(String) JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE, null, null, "localhost");

        try { this.connect(NetDTO.timeout, host, NetDTO.puerto); }
        catch (Exception IOException) { System.out.println("ERROR: Imposible conectar cliente: "+IOException); }
    }

    private void procesarMensajeServidor (Connection con, Object obj)
    {
        if (obj instanceof NetDTO.ActualizarPPC)
        {   controlador.actualizarPPC((NetDTO.ActualizarPPC) obj); }

        if (obj instanceof NetDTO.PosicionPPC)
        {
            int conID = ((NetDTO.PosicionPPC) obj).connectionID;
            float x = ((NetDTO.PosicionPPC) obj).x;
            float y = ((NetDTO.PosicionPPC) obj).y;
            controlador.moverPPC(conID, x, y);
        }

        if (obj instanceof NetDTO.AnimacionPPC)
        {
            int conID = ((NetDTO.AnimacionPPC) obj).connectionID;
            int numAnimacion = ((NetDTO.AnimacionPPC) obj).numAnimacion;
            controlador.cambiarAnimacionPPC(conID, numAnimacion);
        }

        if (obj instanceof NetDTO.ModificarHPsPPC)
        {
            int conID = ((NetDTO.ModificarHPsPPC) obj).connectionID;
            float modHPs = ((NetDTO.ModificarHPsPPC) obj).HPs;
            controlador.modificarHPsPPC(conID, modHPs);
        }

        if (obj instanceof NetDTO.EliminarPPC)
        {
            int conID = ((NetDTO.EliminarPPC) obj).connectionID;
            controlador.eliminarPPC(conID);
        }

        if (obj instanceof NetDTO.SetTerreno)
        {
            int celdaX = ((NetDTO.SetTerreno) obj).celdaX;
            int celdaY = ((NetDTO.SetTerreno) obj).celdaY;
            int numCapa = ((NetDTO.SetTerreno) obj).numCapa;
            short iDTerreno = ((NetDTO.SetTerreno) obj).iDTerreno;
            controlador.setTerreno(celdaX, celdaY, numCapa, iDTerreno);
        }

        if (obj instanceof NetDTO.ActualizarMapa)
        {   controlador.actualizarMapa((NetDTO.ActualizarMapa)obj); }

        if (obj instanceof NetDTO.MapTilesAdyacentesEnCliente)
        {   controlador.actualizarMapTilesCargados((NetDTO.MapTilesAdyacentesEnCliente)obj); }

        if (obj instanceof NetDTO.AñadirSpellPersonalizadoPPC)
        {
            String spellID = ((NetDTO.AñadirSpellPersonalizadoPPC) obj).spellID;
            controlador.añadirSkillPersonalizadoPPC(con.getID(), spellID);
        }

        if (obj instanceof NetDTO.ModificarNumTalentosSkillPersonalizadoPPC)
        {
            String skillID = ((NetDTO.ModificarNumTalentosSkillPersonalizadoPPC) obj).skillID;
            int statID = ((NetDTO.ModificarNumTalentosSkillPersonalizadoPPC) obj).statID;
            int valor = ((NetDTO.ModificarNumTalentosSkillPersonalizadoPPC) obj).valor;
            controlador.modificarnumTalentosSkillPersonalizadoCC(con.getID(), skillID, statID, valor);
        }
    }

    public void enviarAServidor(Object obj)
    {   this.sendTCP(obj); }
}
