package zMain;// Created by Hanto on 11/04/2014.

import View.Actores.ActorRecursos;

public class LoadActores
{
    public static void cargarRecursos ()
    {
        ActorRecursos.get().añadirRazaPC("Golem");
        ActorRecursos.get().salvarCuerpoPC("Golem", "Golem");

        ActorRecursos.get().salvarYelmoPC("Golem", "Desnudo");
        ActorRecursos.get().salvarCabezaPC("Golem", "Desnudo");
        ActorRecursos.get().salvarBotasPC("Golem", "Desnudo");
        ActorRecursos.get().salvarGuantesPC("Golem", "Desnudo");
        ActorRecursos.get().salvarHombrerasPC("Golem", "Desnudo");
        ActorRecursos.get().salvarPantalonesPC("Golem", "Desnudo");
        ActorRecursos.get().salvarPetoPC("Golem", "Desnudo");
        ActorRecursos.get().salvarCapasFrontalesPC("Golem", "Desnudo");
        ActorRecursos.get().salvarCapasTraserasPC("Golem", "Desnudo");
    }
}
