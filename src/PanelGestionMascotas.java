import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelGestionMascotas extends JInternalFrame {

    private CrudMascotas crudMascotas;
    private JTable tablaMascotas;
    private DefaultTableModel modelo;
    private JTextField txtBuscar;

    public PanelGestionMascotas(CrudMascotas crudMascotas) {
        super("Gestión de Mascotas", true, true, true, true);
        this.crudMascotas = crudMascotas;

        setSize(700, 400);
        setLayout(new BorderLayout());

        // Panel superior con campo de búsqueda
        JPanel panelSuperior = new JPanel(new FlowLayout());
        txtBuscar = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        panelSuperior.add(new JLabel("Nombre:"));
        panelSuperior.add(txtBuscar);
        panelSuperior.add(btnBuscar);
        panelSuperior.add(btnEditar);
        panelSuperior.add(btnEliminar);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla para mostrar las mascotas
        modelo = new DefaultTableModel(new String[]{"Nombre", "Especie", "Edad"}, 0);
        tablaMascotas = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaMascotas);
        add(scroll, BorderLayout.CENTER);

        cargarTabla(crudMascotas.getMascotas());  // inicial

        // Acción buscar
        btnBuscar.addActionListener(e -> {
            String nombre = txtBuscar.getText().trim();
            if (nombre.isEmpty()) {
                cargarTabla(crudMascotas.getMascotas()); // recarga completa
            } else {
                Mascota encontrada = crudMascotas.buscarPorNombre(nombre);
                if (encontrada != null) {
                    cargarTabla(List.of(encontrada));
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la mascota.");
                }
            }
        });

        // Acción editar
        btnEditar.addActionListener(e -> {
            int fila = tablaMascotas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una mascota para editar.");
                return;
            }

            String nombreActual = modelo.getValueAt(fila, 0).toString();
            Mascota mascota = crudMascotas.buscarPorNombre(nombreActual);

            if (mascota == null) {
                JOptionPane.showMessageDialog(this, "Mascota no encontrada.");
                return;
            }

            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", mascota.getNombre());
            String nuevaEspecie = JOptionPane.showInputDialog(this, "Nueva especie:", mascota.getEspecie());
            String nuevaEdadStr = JOptionPane.showInputDialog(this, "Nueva edad:", mascota.getEdad());

            if (nuevoNombre != null && nuevaEspecie != null && nuevaEdadStr != null) {
                try {
                    int nuevaEdad = Integer.parseInt(nuevaEdadStr);
                    mascota.setNombre(nuevoNombre);
                    mascota.setEspecie(nuevaEspecie);
                    mascota.setEdad(nuevaEdad);
                    cargarTabla(crudMascotas.getMascotas());
                    JOptionPane.showMessageDialog(this, "Mascota actualizada.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Datos inválidos.");
                }
            }
        });

        // Acción eliminar
        btnEliminar.addActionListener(e -> {
            int fila = tablaMascotas.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una mascota para eliminar.");
                return;
            }

            String nombre = modelo.getValueAt(fila, 0).toString();
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar a " + nombre + "?", "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminada = crudMascotas.eliminarMascotaPorNombre(nombre);
                if (eliminada) {
                    cargarTabla(crudMascotas.getMascotas());
                    JOptionPane.showMessageDialog(this, "Mascota eliminada.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
                }
            }
        });
    }

    private void cargarTabla(List<Mascota> lista) {
        modelo.setRowCount(0);
        for (Mascota m : lista) {
            modelo.addRow(new Object[]{m.getNombre(), m.getEspecie(), m.getEdad()});
        }
    }
}