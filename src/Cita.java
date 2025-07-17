import java.time.LocalDate;

public class Cita {
    private String idCita;
    private LocalDate fecha;
    private String hora;
    private Mascota mascota;
    private Propietario propietario;
    private Veterinario veterinario;

    //Constructor
    public Cita(String idCita, LocalDate fecha, String hora, Mascota mascota, Propietario propietario, Veterinario veterinario) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.mascota = mascota;
        this.propietario = propietario;
        this.veterinario = veterinario;
    }

    //gettes y setters

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    //mostrar cita
    public void mostrarCita() {
        System.out.println("ID Cita: " + idCita);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Mascota: " + mascota.getNombre());
        System.out.println("Propietario: " + propietario.getNombre());
        System.out.println("Veterinario asignado: " + veterinario.getNombre());
    }
}