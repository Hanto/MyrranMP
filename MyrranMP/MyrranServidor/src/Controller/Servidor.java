package Controller;// Created by Hanto on 07/04/2014.

import DTO.NetDTO;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class Servidor extends Server
{
    public Controlador controlador;

    public Servidor (Controlador control)
    {
        super(512*1024, 4*1024);
        this.controlador = control;

        NetDTO.register(this);
        this.start();

        //Para activar el log completo de mensajes:
        //Log.TRACE();

        this.addListener(new Listener.ThreadedListener(new Listener()
        {
            public void connected (Connection con)              { controlador.añadirPC(con.getID(),0f,0f); }
            public void disconnected (Connection con)           { controlador.eliminarPC(con.getID()); }
            public void received (Connection con, Object obj)   { procesarMensajeCliente(con, obj); }
        }));

        try { this.bind(NetDTO.puerto); }
        catch (Exception e) { System.out.println("ERROR: Inicio Servidor: "+e); }
    }

    private void procesarMensajeCliente(Connection con, Object obj)
    {
        if (obj instanceof NetDTO.PosicionPPC)
        {
            int conID = con.getID();
            float x = ((NetDTO.PosicionPPC) obj).x;
            float y = ((NetDTO.PosicionPPC) obj).y;

            controlador.moverPC(conID, x, y);
        }

        if (obj instanceof NetDTO.AnimacionPPC)
        {
            int conID = ((NetDTO.AnimacionPPC) obj).connectionID;
            int numAnimacion = ((NetDTO.AnimacionPPC) obj).numAnimacion;
            controlador.cambiarAnimacionPC(conID, numAnimacion);
        }

        if (obj instanceof NetDTO.CastearPPC)
        {
            int conID = con.getID();
            boolean castear = ((NetDTO.CastearPPC) obj).castear;
            int targetX = ((NetDTO.CastearPPC) obj).targetX;
            int targetY = ((NetDTO.CastearPPC) obj).targetY;
            controlador.castear(conID, castear, targetX, targetY);
        }

        if (obj instanceof NetDTO.SetSpellIDSeleccionado)
        {
            String spellID = ((NetDTO.SetSpellIDSeleccionado) obj).spellID;
            Object parametros = ((NetDTO.SetSpellIDSeleccionado) obj).parametrosSpell;
            controlador.cambiarSpellSeleccionado(con.getID(), spellID, parametros);
        }

        if (obj instanceof NetDTO.SetParametrosSpell)
        {
            Object parametros = ((NetDTO.SetParametrosSpell) obj).parametrosSpell;
            controlador.cambiarParametrosSpell(con.getID(), parametros);
        }

        if (obj instanceof NetDTO.EnviarModificarSkillTalentoPPC)
        {
            int conID = con.getID();
            String skillID = ((NetDTO.EnviarModificarSkillTalentoPPC) obj).skillID;
            int statID = ((NetDTO.EnviarModificarSkillTalentoPPC) obj).statID;
            int valor = ((NetDTO.EnviarModificarSkillTalentoPPC) obj).valor;
            controlador.modificarSkillTalentoPC(conID, skillID, statID, valor);
        }
    }

    public void enviarACliente(int connectionID, Object obj)
    {   this.sendToTCP(connectionID, obj); }

    public void enviarATodosClientes(Object obj)
    {   this.sendToAllTCP(obj); }

    public void enviarATodosClientesMenosUno(int connectionID, Object obj)
    {   this.sendToAllExceptTCP(connectionID, obj);}
}
