package Data;

public class GameDataDTO
{
    public static class TerrenoDTO
    {
        public String nombre;
        public boolean isSolido;
        public String nombreTextura;
        public TerrenoDTO(String nombre, boolean isSolido, String nombreTextura)
        {   this.nombre = nombre; this.isSolido = isSolido; this.nombreTextura = nombreTextura; }
    }
}
