import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PanelVacunas extends JInternalFrame {

    private CrudMascotas crudMascotas;
    private CrudVacunas crudVacunas;

    private JComboBox<String> comboMascotas;
    private JTextField txtTipo, txtLote, txtFechaAplicacion, txtProximaDosis;
    private JTable tablaVacunas;
    private DefaultTableModel modeloTabla;

    public PanelVacunas(CrudMascotas crudMascotas) {
        this.crudMascotas = crudMascotas;
        this.crudVacunas = new CrudVacunas();

        setTitle("Gestión de Vacunas");
        setClosable(true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        //formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));

        comboMascotas = new JComboBox<>();
        cargarMascotas();

        txtTipo = new JTextField();
        txtLote = new JTextField();
        txtFechaAplicacion = new JTextField("2025-07-16");
        txtProximaDosis = new JTextField("2026-07-16");

        panelFormulario.add(new JLabel("Mascota:"));
        panelFormulario.add(comboMascotas);
        panelFormulario.add(new JLabel("Tipo vacuna:"));
        panelFormulario.add(txtTipo);
        panelFormulario.add(new JLabel("Lote:"));
        panelFormulario.add(txtLote);
        panelFormulario.add(new JLabel("Fecha aplicación (AAAA-MM-DD):"));
        panelFormulario.add(txtFechaAplicacion);
        panelFormulario.add(new JLabel("Próxima dosis (AAAA-MM-DD):"));
        panelFormulario.add(txtProximaDosis);

        JButton btnRegistrar = new JButton("Registrar vacuna");
        JButton btnListar = new JButton("Listar vacunas");
        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnListar);

        add(panelFormulario, BorderLayout.NORTH);

        //tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"Tipo", "Lote", "Aplicación", "Próxima dosis"});

        tablaVacunas = new JTable(modeloTabla);
        add(new JScrollPane(tablaVacunas), BorderLayout.CENTER);

        //botón Registrar
        btnRegistrar.addActionListener(e -> registrarVacuna());

        //botón Listar
        btnListar.addActionListener(e -> listarVacunas());
    }

    private void cargarMascotas() {
        comboMascotas.removeAllItems();
        for (Mascota m : crudMascotas.getMascotas()) {
            comboMascotas.addItem(m.getNombre());
        }
    }

    private void registrarVacuna() {
        String nombreMascota = (String) comboMascotas.getSelectedItem();
        Mascota mascota = crudMascotas.buscarPorNombre(nombreMascota);

        try {
            String tipo = txtTipo.getText();
            String lote = txtLote.getText();
            LocalDate fechaAplicacion = LocalDate.parse(txtFechaAplicacion.getText());
            LocalDate proximaDosis = LocalDate.parse(txtProximaDosis.getText());

            Vacunacion vacunacion = new Vacunacion(tipo, lote, fechaAplicacion, proximaDosis);
            crudVacunas.registrarVacuna(mascota, vacunacion);
            JOptionPane.showMessageDialog(this, "✅ Vacunacion registrada correctamente.");
            listarVacunas();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Error al registrar: " + ex.getMessage());
        }
    }

    private void listarVacunas() {
        modeloTabla.setRowCount(0); //limpiar tabla
        String nombreMascota = (String) comboMascotas.getSelectedItem();
        Mascota mascota = crudMascotas.buscarPorNombre(nombreMascota);
        if (mascota != null) {
            List<Vacunacion> vacunacions = mascota.getVacunas();
            for (Vacunacion v : vacunacions) {
                modeloTabla.addRow(new Object[]{
                        v.getTipo(),
                        v.getLote(),
                        v.getFechaAplicacion(),
                        v.getProximaDosis()
                });
            }
        }
    }
}