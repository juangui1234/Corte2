import java.util.ArrayList;

public class Propietario {
    private String nombre;
    private String documento;
    private String telefono;
    private String direccion;
    private ArrayList<Mascota> mascotas; // Relación 1:N original

    public Propietario(String nombre, String documento, String telefono, String direccion) {
        setNombre(nombre);
        setDocumento(documento);
        setTelefono(telefono);
        setDireccion(direccion);  // nuevo
        this.mascotas = new ArrayList<>();
    }

        /* codigo original:
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.mascotas = new ArrayList<>();
        */
        //setters con validaciones y lista inicializada


    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    /*
     * muestra los datos del propietario.
     */
    public void mostrarInformacion() {
        System.out.println("👤 Propietario: " + nombre);
        System.out.println("🆔 Documento: " + documento);
        System.out.println("📞 Teléfono: " + telefono);
        System.out.println("🏠 Dirección: " + direccion);
    }

    /*
     * muestra el historial de todas las mascotas asociadas.
     */
    public void mostrarMascotas() {
        for (Mascota m : mascotas) {
            System.out.println();
            m.mostrarHistorial();
        }
    }

    /*
     muestra toda la ficha clínica completa.
     */
    /*public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA CLÍNICA =====");
        mostrarInformacion();
        mostrarMascotas();
    }*/

    // Getters y Setters refactorizados

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
    /*public String getNombre() {
        return nombre;
    }*/
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección inválida.");
        }
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        if (documento == null || documento.length() < 5) {
            throw new IllegalArgumentException("Documento inválido.");
        }
        this.documento = documento;
    }
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.length() < 7) {
            throw new IllegalArgumentException("Teléfono inválido.");
        }
        this.telefono = telefono;
    }
    @Override
    public String toString() {
        return nombre + " (" + documento + ")";
    }
    /*public List<Mascota> getMascotas() {
        return new ArrayList<>(mascotas);
    }*/
}