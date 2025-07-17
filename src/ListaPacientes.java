import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaPacientes extends JInternalFrame {

    private CrudMascotas crudMascotas;
    private CrudPropietarios crudPropietarios;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JProgressBar barraCarga;

    public ListaPacientes(CrudMascotas crudMascotas, CrudPropietarios crudPropietarios) {
        super("Lista de pacientes", true, true, true, true);
        this.crudMascotas = crudMascotas;
        this.crudPropietarios = crudPropietarios;

        setSize(600, 400);
        setLayout(new BorderLayout());
        setClosable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        modelo = new DefaultTableModel(new String[]{"Nombre", "Especie", "Edad", "Propietario"}, 0);
        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        barraCarga = new JProgressBar(0, 100);
        barraCarga.setStringPainted(true);
        add(barraCarga, BorderLayout.SOUTH);

        cargarDatosConSimulacion();
    }

    private void cargarDatosConSimulacion() {
        modelo.setRowCount(0); // Limpia la tabla

        Timer timer = new Timer(30, null); // 30 ms por ciclo
        final int[] progreso = {0};
        timer.addActionListener(e -> {
            if (progreso[0] >= 100) {
                ((Timer) e.getSource()).stop();
                llenarTabla();
            } else {
                progreso[0] += 5;
                barraCarga.setValue(progreso[0]);
            }
        });
        timer.start();
    }

    private void llenarTabla() {
        List<Mascota> lista = crudMascotas.getListaMascotas(); // ✅ CORREGIDO

        for (Mascota m : lista) {
            // Buscar propietario asociado a la mascota
            String propietarioNombre = obtenerPropietarioDeMascota(m);
            modelo.addRow(new Object[]{
                    m.getNombre(),
                    m.getEspecie(),
                    m.getEdad(),
                    propietarioNombre
            });
        }

        barraCarga.setValue(100);
        barraCarga.setString("Datos cargados");
    }

    private String obtenerPropietarioDeMascota(Mascota mascota) {
        for (Propietario p : crudPropietarios.getTodos()) {
            if (p != null && p.getNombre() != null) {
                if (p.getMascotas() != null && p.getMascotas().contains(mascota)) {
                    return p.getNombre();
                }
            }
        }
        return "Desconocido";
    }
}