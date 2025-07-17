import java.util.ArrayList;

public class Propietario extends Persona {
    private String telefono;
    private String direccion;
    private ArrayList<Mascota> mascotas;

    public Propietario(String nombre, String documento, String telefono, String direccion) {
        super(nombre, documento, telefono); // llamada al constructor de Persona
        setTelefono(telefono);
        setDireccion(direccion);
        this.mascotas = new ArrayList<>();
    }

    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public void mostrarInformacion() {
        System.out.println("👤 Propietario: " + getNombre());
        System.out.println("🆔 Documento: " + getDocumento());
        System.out.println("📞 Teléfono: " + telefono);
        System.out.println("🏠 Dirección: " + direccion);
    }

    public void mostrarMascotas() {
        for (Mascota m : mascotas) {
            System.out.println();
            m.mostrarHistorial();
        }
    }

    public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA CLÍNICA =====");
        mostrarInformacion();
        mostrarMascotas();
    }

    // Getters y Setters
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección inválida.");
        }
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return getNombre() + " (" + getDocumento() + ")";
    }
}