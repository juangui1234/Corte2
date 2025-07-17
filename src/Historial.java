import java.util.ArrayList;
import java.util.List;


public class Historial {
    private List<Consulta> consultas;

    public Historial() {
        this.consultas = new ArrayList<>();
    }

    public void agregarConsulta(Consulta consulta) {
        if (consulta != null) {
            consultas.add(consulta);
        }
    }

    public void mostrarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Sin consultas registradas.");
        } else {
            for (Consulta c : consultas) {
                c.mostrarConsulta();
                System.out.println("--------------------------");
            }
        }
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<>(consultas);
    }
}
