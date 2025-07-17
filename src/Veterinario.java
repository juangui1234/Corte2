public class Veterinario {
    private String nombre;
    private String especialidad;
    private String nombreVeterinario;
    private boolean disponible;

    public Veterinario(String nombre, String especialidad, boolean disponible) {
        this.disponible = disponible;
        /* Código original:
        this.nombre = nombre;
        this.especialidad = especialidad;
        */
        // ✅ Refactor: validación de argumentos
        setNombre(nombre);
        setEspecialidad(especialidad);

    }

    public void mostrarPerfil() {
        System.out.println("Veterinario: " + nombre + " | Especialidad: " + especialidad);
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del veterinario no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.especialidad = especialidad;
    }
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
