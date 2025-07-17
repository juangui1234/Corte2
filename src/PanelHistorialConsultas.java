import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelHistorialConsultas extends JInternalFrame {

    private CrudMascotas crudMascotas;
    private JComboBox<Mascota> comboMascotas;
    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelHistorialConsultas(CrudMascotas crudMascotas) {
        super("Historial de Consultas", true, true, true, true);
        this.crudMascotas = crudMascotas;

        setSize(700, 400);
        setLayout(new BorderLayout());

        //panel superior con combo y botón
        JPanel panelSuperior = new JPanel(new FlowLayout());

        comboMascotas = new JComboBox<>();
        for (Mascota m : crudMascotas.getMascotas()) {
            comboMascotas.addItem(m);  //usa toString de Mascota
        }

        JButton btnVerHistorial = new JButton("Ver Historial");
        btnVerHistorial.addActionListener(_ -> cargarConsultas());

        panelSuperior.add(new JLabel("Seleccione Mascota:"));
        panelSuperior.add(comboMascotas);
        panelSuperior.add(btnVerHistorial);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"Código", "Fecha", "Veterinario", "Especialidad"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void cargarConsultas() {
        modelo.setRowCount(0); // limpia la tabla
        Mascota mascotaSeleccionada = (Mascota) comboMascotas.getSelectedItem();
        if (mascotaSeleccionada != null) {
            List<Consulta> consultas = mascotaSeleccionada.getHistorial().getConsultas();
            for (Consulta c : consultas) {
                modelo.addRow(new Object[]{
                        c.getCodigo(),
                        c.getFecha(),
                        c.getVeterinario().getNombre(),
                        c.getVeterinario().getEspecialidad()
                });
            }
        }
    }
}