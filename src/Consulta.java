import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Consulta {
    private String codigo;
    private LocalDate fecha;
    private Veterinario veterinario;

    private String diagnostico;
    private String tratamiento;
    private List<String> medicamentos;

    //Constructor
    public Consulta(String codigo, LocalDate fecha, Veterinario veterinario, String diagnostico, String tratamiento, List<String> medicamentos) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.veterinario = veterinario;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medicamentos = medicamentos;
    }

    // Constructor Antiguo
    public Consulta(String codigo, LocalDate fecha, Veterinario veterinario) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.veterinario = veterinario;
        this.diagnostico = "";
        this.tratamiento = "";
        this.medicamentos = new ArrayList<>();
    }

    //metodo get y set

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public List<String> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<String> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void agregarMedicamento(String medicamento) {
        this.medicamentos.add(medicamento);
    }

    public void mostrarConsulta() {
        System.out.println("Código: " + codigo);
        System.out.println("Fecha: " + fecha);
        System.out.println("Veterinario: " + veterinario.getNombre());
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("Tratamiento: " + tratamiento);
        System.out.println("Medicamentos recetados: " + medicamentos);
    }
}