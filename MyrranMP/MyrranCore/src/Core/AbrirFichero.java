package Core;// Created by Hanto on 12/06/2014.

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class AbrirFichero
{
    public static InputStream abrirFichero(String rutaYNombreFichero)
    {   //probamos a Acceder al fichero directamente, en caso de poder acerlo lo transformamos en un InputStream
        try { return new FileInputStream(new File(rutaYNombreFichero)); }
        //En el caso de dar error por que el fichero no exista, probamos a acceder al recurso
        catch (Exception e) { return AbrirFichero.class.getClassLoader().getResourceAsStream(rutaYNombreFichero);}
    }
}
