import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    //private ArrayList<Consulta> consultas;
    private List<Vacuna> vacunas = new ArrayList<>();
    private String nombreMascota;
    private Historial historial; //delegar manejo de consultas a historial
    public Mascota(String nombre, String especie, int edad) {
        /* Código original:
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.consultas = new ArrayList<>();
        */
        //usar setters con validación y crear historial
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        this.historial = new Historial();
        this.vacunas = new ArrayList<>(); //lista vacunas
    }

    public void agregarConsulta(Consulta consulta) {
        // consultas.add(consulta); //original
        //delegar a historial
        historial.agregarConsulta(consulta);
    }
    public void agregarVacuna(Vacuna vacuna) {
        vacunas.add(vacuna);
    }

    public void mostrarVacunas() {
        if (vacunas.isEmpty()) {
            System.out.println("No hay vacunas registradas.");
        } else {
            System.out.println("Vacunas aplicadas a " + nombre + ":");
            for (Vacuna v : vacunas) {
                v.mostrarVacuna();
                System.out.println("--------------------------");
            }
        }
    }
    public void mostrarHistorial() {
        System.out.println("📋 Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad + " años");
        System.out.println("Historial de consultas:");
        //metodo de historial
        historial.mostrarConsultas();
    }

        /* Código original:
        if (consultas.isEmpty()) {
            System.out.println("Sin consultas registradas.");
        } else {
            for (Consulta c : consultas) {
                c.mostrarConsulta();
                System.out.println("--------------------------");
            }
        }
        */
    //Getters y setters con validación
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido.");
        }
        this.nombre = nombre;
    }
    public Historial getHistorial() {
        return historial;
    }

    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException("Especie inválida.");
        }
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("Edad no puede ser negativa.");
        }
        this.edad = edad;
            }

    public List<Vacuna> getVacunas() {
        return vacunas;
    }
    @Override
    public String toString() {
        return nombre + " (" + especie + ")";
    }
}
