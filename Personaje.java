public class Personaje {
    private String nombre;
    private int ki;

    public Personaje(String nombre, int ki) {
        this.nombre = nombre;
        this.ki = ki;
    }

    public void curar(Personaje objetivo, int cantidad) {
        int kiRecuperado = Math.min(cantidad, 1000 - objetivo.ki);
        objetivo.ki += kiRecuperado;

        System.out.println(this.nombre + " curó a " + objetivo.nombre +
         " y recuperó " + kiRecuperado + " puntos de ki.");
    }

    public String getNombre() {
        return nombre;
    }

    public int getKi() {
        return ki;
    }

    public void setKi(int ki) {
        this.ki = ki;
    }

    // Método main dentro de la clase Personaje
    public static void main(String[] args) {
        Personaje goku = new Personaje("Goku", 1000);
        Personaje vegeta = new Personaje("Vegeta", 1000);

        vegeta.setKi(920); // Vegeta pierde ki

        goku.curar(vegeta, 100); // Goku cura a Vegeta

        System.out.println("Ki actual de " + vegeta.getNombre() + ": " + vegeta.getKi());
    }
}