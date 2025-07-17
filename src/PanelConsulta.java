import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PanelConsulta extends JInternalFrame {

    private CrudMascotas crudMascotas;
    private JTextArea areaResultado;
    private JTextField txtNombreBusqueda;

    // Campos para registrar consulta
    private JTextField txtFecha;
    private JTextField txtVetNombre;
    private JTextField txtVetEspecialidad;
    private JButton btnRegistrarConsulta;

    private Mascota mascotaActual;  // mascota encontrada

    public PanelConsulta(CrudMascotas crudMascotas) {
        super("Consulta y Servicios", true, true, true, true);
        this.crudMascotas = crudMascotas;

        setSize(750, 500);
        setLayout(new BorderLayout());

        crearPanelServicios();     // panel izquierdo con JTree
        crearPanelCentral();       // panel central con búsqueda, info y formulario
    }

    private void crearPanelServicios() {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Servicios");
        raiz.add(new DefaultMutableTreeNode("Medicina general"));
        raiz.add(new DefaultMutableTreeNode("Cirugía"));
        raiz.add(new DefaultMutableTreeNode("Vacunación"));
        raiz.add(new DefaultMutableTreeNode("Peluquería"));
        raiz.add(new DefaultMutableTreeNode("Urgencias"));

        JTree arbolServicios = new JTree(new DefaultTreeModel(raiz));
        JScrollPane scrollArbol = new JScrollPane(arbolServicios);
        scrollArbol.setPreferredSize(new Dimension(200, getHeight()));
        add(scrollArbol, BorderLayout.WEST);
    }

    private void crearPanelCentral() {
        JPanel panelCentral = new JPanel(new BorderLayout());

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel();
        JLabel lblNombre = new JLabel("Nombre de la mascota:");
        txtNombreBusqueda = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");

        panelBusqueda.add(lblNombre);
        panelBusqueda.add(txtNombreBusqueda);
        panelBusqueda.add(btnBuscar);

        // Área de resultados
        areaResultado = new JTextArea(10, 40);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollTexto = new JScrollPane(areaResultado);

        // Panel para agregar consulta
        JPanel panelConsulta = new JPanel(new GridLayout(5, 2, 5, 5));
        panelConsulta.setBorder(BorderFactory.createTitledBorder("Registrar nueva consulta"));

        txtFecha = new JTextField();
        txtVetNombre = new JTextField();
        txtVetEspecialidad = new JTextField();
        btnRegistrarConsulta = new JButton("Registrar consulta");
        btnRegistrarConsulta.setEnabled(false);  // solo se habilita cuando hay mascota

        panelConsulta.add(new JLabel("Fecha (YYYY-MM-DD):"));
        panelConsulta.add(txtFecha);
        panelConsulta.add(new JLabel("Veterinario:"));
        panelConsulta.add(txtVetNombre);
        panelConsulta.add(new JLabel("Especialidad:"));
        panelConsulta.add(txtVetEspecialidad);
        panelConsulta.add(new JLabel()); // vacío
        panelConsulta.add(btnRegistrarConsulta);

        // Acciones
        btnBuscar.addActionListener(e -> buscarMascota());
        btnRegistrarConsulta.addActionListener(e -> registrarConsulta());

        // Integración de paneles
        panelCentral.add(panelBusqueda, BorderLayout.NORTH);
        panelCentral.add(scrollTexto, BorderLayout.CENTER);
        panelCentral.add(panelConsulta, BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);
    }

    private void buscarMascota() {
        String nombre = txtNombreBusqueda.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe el nombre de la mascota.");
            return;
        }

        Mascota encontrada = crudMascotas.buscarPorNombre(nombre);
        if (encontrada != null) {
            mascotaActual = encontrada;
            btnRegistrarConsulta.setEnabled(true);
            mostrarHistorial();
        } else {
            areaResultado.setText("❌ Mascota no encontrada.");
            btnRegistrarConsulta.setEnabled(false);
            mascotaActual = null;
        }
    }

    private void mostrarHistorial() {
        areaResultado.setText(""); // Limpiar
        areaResultado.append("📋 Ficha de la mascota:\n\n");
        areaResultado.append("Nombre: " + mascotaActual.getNombre() + "\n");
        areaResultado.append("Especie: " + mascotaActual.getEspecie() + "\n");
        areaResultado.append("Edad: " + mascotaActual.getEdad() + "\n");
        areaResultado.append("\n📄 Consultas:\n");

        if (mascotaActual.getHistorial().getConsultas().isEmpty()) {
            areaResultado.append("- Sin consultas registradas.\n");
        } else {
            for (Consulta c : mascotaActual.getHistorial().getConsultas()) {
                areaResultado.append("  Código: " + c.getCodigo() + "\n");
                areaResultado.append("  Fecha: " + c.getFecha() + "\n");
                areaResultado.append("  Veterinario: " + c.getVeterinario().getNombre()
                        + " - " + c.getVeterinario().getEspecialidad() + "\n");
                areaResultado.append("-------------------------------\n");
            }
        }
    }

    private void registrarConsulta() {
        if (mascotaActual == null) return;

        String fechaStr = txtFecha.getText().trim();
        String nombreVet = txtVetNombre.getText().trim();
        String especialidad = txtVetEspecialidad.getText().trim();

        if (fechaStr.isEmpty() || nombreVet.isEmpty() || especialidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos de la consulta son obligatorios.");
            return;
        }

        try {
            LocalDate fecha = LocalDate.parse(fechaStr); // ✅ CORREGIDO

            Consulta nueva = new Consulta(
                    IDGenerator.generarCodigoConsulta(),
                    fecha,
                    new Veterinario(nombreVet, especialidad, true)
            );

            mascotaActual.agregarConsulta(nueva);
            JOptionPane.showMessageDialog(this, "Consulta registrada correctamente.");
            mostrarHistorial(); // refresca

            // limpia campos
            txtFecha.setText("");
            txtVetNombre.setText("");
            txtVetEspecialidad.setText("");

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener formato YYYY-MM-DD.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}