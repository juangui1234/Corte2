import java.time.LocalDate;

public class Vacuna {
    private String tipo;
    private String lote;
    private LocalDate fechaAplicacion;
    private LocalDate proximaDosis;

    //Constructor
    public Vacuna(String tipo, String lote, LocalDate fechaAplicacion, LocalDate proximaDosis) {
        this.tipo = tipo;
        this.lote = lote;
        this.fechaAplicacion = fechaAplicacion;
        this.proximaDosis = proximaDosis;
    }

    //getters y setters

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(LocalDate fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public LocalDate getProximaDosis() {
        return proximaDosis;
    }

    public void setProximaDosis(LocalDate proximaDosis) {
        this.proximaDosis = proximaDosis;
    }

    //metodos
    public void mostrarVacuna() {
        System.out.println("Tipo de vacuna: " + tipo);
        System.out.println("Lote: " + lote);
        System.out.println("Fecha de aplicación: " + fechaAplicacion);
        System.out.println("Próxima dosis: " + proximaDosis);
    }
}