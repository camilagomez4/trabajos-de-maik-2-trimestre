
    
import java.util.ArrayList;

class Vehiculo {
    // Encapsulamiento esto me servira para poner mis atributos privados
    private String marca;
    private String modelo;
    private int velocidadMaxima;

    
    public Vehiculo(String marca, String modelo, int velocidadMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
    }

    
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    
    public void mostrarInfo() {
        System.out.println("Marca: " + marca + ", Modelo: " + modelo + 
                           ", Velocidad Máxima: " + velocidadMaxima + " km/h");
    }

    
    public void acelerar() {
        System.out.println("El vehículo está acelerando...");
    }
}

//  Herencia esto le permite a una clase heredar atributos 
class Auto extends Vehiculo {
    public Auto(String marca, String modelo, int velocidadMaxima) {
        super(marca, modelo, velocidadMaxima);
    }

    @Override
    public void acelerar() { // 🔹 3. Polimorfismo
        System.out.println("El auto acelera suavemente 🚗💨");
    }
}

// Otra subclase: Moto
class Moto extends Vehiculo {
    public Moto(String marca, String modelo, int velocidadMaxima) {
        super(marca, modelo, velocidadMaxima);
    }

    @Override
    public void acelerar() { // Polimorfismo permite quye un metodo se comporte de4 diferentes maneras , segun para que se use 
        System.out.println("La moto acelera con mucha rapidez 🏍💨");
    }
}


public class SistemaVehiculos {
    public static void main(String[] args) {
    
        Vehiculo v1 = new Vehiculo("Genérico", "Base", 120);
        Vehiculo v2 = new Auto("Toyota", "Corolla", 180);
        Vehiculo v3 = new Moto("Yamaha", "R3", 220);

        
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        listaVehiculos.add(v1);
        listaVehiculos.add(v2);
        listaVehiculos.add(v3);

        
        for (Vehiculo v : listaVehiculos) {
            v.mostrarInfo();
            v.acelerar();
            System.out.println("---------------");
        }
    }
}
