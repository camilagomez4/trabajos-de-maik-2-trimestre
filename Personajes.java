public class Personaje {
    String nombre;
    String raza;
    Integer ki;
    Integer ataque;
    String planeta;

    public Personaje(String nombre, String raza, Integer ki, Integer ataque, String planeta) {
        this.nombre = nombre;
        this.raza = raza;
        this.ki = ki;
        this.ataque = ataque;
        this.planeta = planeta;
    }

    public void mostrarPersonaje(){
        System.out.println("El nombre es: " + nombre);
        System.out.println("La raza es: " + raza);
        System.out.println("El ki es: " + ki);
        System.out.println("El ataque es: " + ataque);
        System.out.println("El planeta es: " + planeta);
    }
    
    

    public static void main(String[] args) {
        Personaje goku = new Personaje("Gokú", "Saiyajin", 1000, 1000, "Vegito");
        Personaje vegetta = new Personaje("Vegetta", "Saiyajin", 999, 999, "Vegito");

        goku.mostrarPersonaje();
        System.out.println();
        vegetta.mostrarPersonaje();
    }
}