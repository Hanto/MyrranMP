package View.Classes.Mobiles;// Created by Hanto on 20/05/2014.

import Controller.Controlador;
import DTO.NetDTO;
import Data.MiscData;
import Model.Classes.Mobiles.PC;
import Model.GameState.Mundo;

public class MapaView
{
    private PC PC;
    private Mundo mundo;
    private Controlador controlador;

    private int mapTileCentroX;
    private int mapTileCentroY;
    private int posicionHoritontal = 0;
    private int posicionVertical = 0;

    private boolean[][] mapaEnviado = new boolean[3][3];

    private int numTilesX;
    private int numTilesY;

    private final int reborde = 1;
    private final int posHorNeg = MiscData.MAPTILE_Horizontal_Resolution /4;
    private final int posHorPos = MiscData.MAPTILE_Horizontal_Resolution - MiscData.MAPTILE_Horizontal_Resolution /4;
    private final int posVerNeg = MiscData.MAPTILE_Vertical_Resolution /4;
    private final int posVerPos = MiscData.MAPTILE_Vertical_Resolution - MiscData.MAPTILE_Vertical_Resolution /4;


    public MapaView (PC pc, Mundo mundo, Controlador controlador)
    {
        this.PC = pc;
        this.mundo = mundo;
        this.controlador = controlador;

        this.numTilesX = (int)Math.ceil((double)MiscData.MAPTILE_Horizontal_Resolution /(double)MiscData.TILESIZE);
        this.numTilesY = (int)Math.ceil((double)MiscData.MAPTILE_Vertical_Resolution /(double)MiscData.TILESIZE);
    }

    private void init ()
    {
        for (boolean[] fila : mapaEnviado)
        {   for (int i=0; i<fila.length; i++)
                fila[i] = false;
        }
        mapTileCentroX = getMapTileX();
        mapTileCentroY = getMapTileY();
    }

    private int getMapTileX()                                       { return (int)((PC.getX() / (float)(numTilesX * MiscData.TILESIZE))); }
    private int getMapTileY()                                       { return (int)((PC.getY() / (float)(numTilesY * MiscData.TILESIZE))); }
    private boolean getMapaEnviado(int offSetX, int offSetY)        { return mapaEnviado[offSetX+1][-offSetY+1]; }
    private void setMapaEnviado(int offSetX, int offSetY, boolean b){ mapaEnviado[offSetX+1][-offSetY+1] = b; }

    private void enviarMapTilesAdyancentes()
    {
        NetDTO.MapTilesAdyacentesEnCliente maptilesAdyacentes = new NetDTO.MapTilesAdyacentesEnCliente(mapaEnviado);
        controlador.enviarACliente(PC.getConnectionID(), maptilesAdyacentes);
    }

    public void comprobarVistaMapa ()
    {
        if (Math.abs(getMapTileX()-mapTileCentroX) >1 || Math.abs(getMapTileY()-mapTileCentroY) > 1)  { init(); return; }

        int distX = (int)PC.getX() -mapTileCentroX*numTilesX*MiscData.TILESIZE;
        int distY = (int)PC.getY() -mapTileCentroY*numTilesY*MiscData.TILESIZE;

        if (distX < posHorNeg)      { posicionHoritontal = -1; }
        else if (distX > posHorPos) { posicionHoritontal = +1; }
        else                        { posicionHoritontal =  0; }

        if (distY < posVerNeg)      { posicionVertical = -1; }
        else if (distY > posVerPos) { posicionVertical = +1; }
        else                        { posicionVertical =  0; }

        if (posicionVertical != 0 && posicionHoritontal != 0)
        {   //Si estamso en una esquina, mandamos las adyacencias de la esquina:
            actualizarMapa(0, 0);
            actualizarMapa(posicionHoritontal, 0);
            actualizarMapa(0, posicionVertical);
            actualizarMapa(posicionHoritontal, posicionVertical);
        }
        if (posicionVertical == 0 && posicionHoritontal != 0)
        {   //Si estamos en el lateral, enviamos las dos esquina de ese lado
            actualizarMapa(0,  0);
            actualizarMapa(0, +1);
            actualizarMapa(0, -1);
            actualizarMapa(posicionHoritontal, +1);
            actualizarMapa(posicionHoritontal,  0);
            actualizarMapa(posicionHoritontal, -1);
        }
        if (posicionHoritontal == 0 && posicionVertical != 0)
        {   //Si estamos en el Lateral superior o inferior, enviarmos las dos esquinas de ese lado
            actualizarMapa( 0, 0);
            actualizarMapa(+1, 0);
            actualizarMapa(-1, 0);
            actualizarMapa(+1, posicionVertical);
            actualizarMapa( 0, posicionVertical);
            actualizarMapa(-1, posicionVertical);
        }
        if (posicionHoritontal == 0 && posicionVertical == 0)
        {   //Si estamos en el centro enviamos todas las esquinas
            actualizarMapa( 0,  0);
            actualizarMapa(+1, +1);
            actualizarMapa(+1,  0);
            actualizarMapa(+1, -1);
            actualizarMapa( 0, +1);
            actualizarMapa( 0, -1);
            actualizarMapa(-1, +1);
            actualizarMapa(-1,  0);
            actualizarMapa(-1, -1);
        }

        if      (getMapTileX() > mapTileCentroX)   { incrementarMapTile(1, 0); }
        else if (getMapTileX() < mapTileCentroX)   { incrementarMapTile(-1, 0); }
        else if (getMapTileY() > mapTileCentroY)   { incrementarMapTile(0, 1);  }
        else if (getMapTileY() < mapTileCentroY)   { incrementarMapTile(0, -1); }
    }

    private void actualizarMapa(int x, int y)
    {   //Convierte las referencias relativas al centro 0,0 en coordenadas de acceso a la matriz donde estan los datos
        if (!getMapaEnviado(x, y))
        {
            setMapaEnviado(x, y, true);
            enviarMapa(mapTileCentroX + x, mapTileCentroY + y);
            enviarMapTilesAdyancentes();
        }
    }

    private void incrementarMapTile (int incX, int incY)
    {
        desplazarArray(incX, incY);

        if (incX >0)
        {
            mapaEnviado[2][0] = false;
            mapaEnviado[2][1] = false;
            mapaEnviado[2][2] = false;
        }
        if (incX <0)
        {
            mapaEnviado[0][0] = false;
            mapaEnviado[0][1] = false;
            mapaEnviado[0][2] = false;
        }
        if (incY >0)
        {
            mapaEnviado[0][0] = false;
            mapaEnviado[1][0] = false;
            mapaEnviado[2][0] = false;
        }
        if (incY <0)
        {
            mapaEnviado[0][2] = false;
            mapaEnviado[1][2] = false;
            mapaEnviado[2][2] = false;
        }

        mapTileCentroX += incX;
        mapTileCentroY += incY;

        //System.out.println("MAPTILE: ["+mapTileCentroX+" "+mapTileCentroY+"]");
        enviarMapTilesAdyancentes();
    }

    private void desplazarArray (int incX, int incY)
    {
        boolean[] temp;
        boolean tempo;

        if (incX > 0)
        {
            temp = mapaEnviado[0];
            System.arraycopy(mapaEnviado, 1, mapaEnviado, 0, mapaEnviado.length-1);
            mapaEnviado[mapaEnviado.length-1] = temp;

        }
        if (incX < 0)
        {
            temp = mapaEnviado[mapaEnviado.length-1];
            System.arraycopy(mapaEnviado, 0, mapaEnviado, 1, mapaEnviado.length-1);
            mapaEnviado[0] = temp;
        }
        if (incY < 0)
        {
            for (int i=0; i< mapaEnviado.length; i++)
            {
                tempo = mapaEnviado[i][0];
                System.arraycopy(mapaEnviado[i], 1, mapaEnviado[i], 0, mapaEnviado[i].length - 1);
                mapaEnviado[i][mapaEnviado.length-1] = tempo;
            }
        }
        if (incY > 0)
        {
            for (int i=0; i< mapaEnviado.length; i++)
            {
                tempo = mapaEnviado[i][mapaEnviado.length-1];
                System.arraycopy(mapaEnviado[i], 0, mapaEnviado[i], 1, mapaEnviado[i].length - 1);
                mapaEnviado[i][0] = tempo;
            }
        }
    }

    private void enviarMapa(int mapTileInicialX, int mapTileInicialY)
    {
        System.out.println("enviar: ["+mapTileInicialX+" "+mapTileInicialY+"]");
        if (mapTileInicialX <0 || mapTileInicialY < 0) { return; }

        int ancho = numTilesX+reborde*2;
        int alto = numTilesY+reborde*2;

        int esquinaInfIzdaX = mapTileInicialX*numTilesX-reborde;
        int esquinaInfIzdaY = mapTileInicialY*numTilesY-reborde;

        NetDTO.ActualizarMapa actualizarMapa = new NetDTO.ActualizarMapa(esquinaInfIzdaX, esquinaInfIzdaY, ancho, alto);
        for (int x=0; x< ancho; x++)
        {
            for (int y = 0; y< alto; y++)
            {
                for (int i=0; i<MiscData.MAPA_Max_Capas_Terreno; i++)
                {   actualizarMapa.mapa[x][y].celda[i] = mundo.getMapa().getTerrenoID(x +esquinaInfIzdaX, y +esquinaInfIzdaY, i); }
            }
        }
        controlador.enviarACliente(PC.getConnectionID(), actualizarMapa);
    }

    public void cambioTerreno (int tileX, int tileY, int numCapa, short iDTerreno)
    {   //Solo se notifican por cambios los terrenos adyacentes que han sido enviados:
        int offsetX = tileX/MiscData.MAPTILE_NumTilesX - mapTileCentroX;
        int offsetY = tileY/MiscData.MAPTILE_NumTilesY - mapTileCentroY;

        if ( Math.abs(offsetX) <= 1 && Math.abs(offsetY) <= 1)
        {
            if (getMapaEnviado(offsetX, offsetY))
            {
                NetDTO.SetTerreno setTerreno = new NetDTO.SetTerreno(tileX,tileY,numCapa,iDTerreno);
                controlador.enviarACliente(PC.getConnectionID(), setTerreno);
                System.out.println("Editando SetTerreno: ["+tileX+"]["+tileY+"]");
            }
        }
    }
}
