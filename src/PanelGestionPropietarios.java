import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelGestionPropietarios extends JInternalFrame {

    private CrudPropietarios crudPropietarios;
    private JTable tabla;
    private DefaultTableModel modelo;

    public PanelGestionPropietarios(CrudPropietarios crudPropietarios) {
        super("Gestión de Propietarios", true, true, true, true);
        this.crudPropietarios = crudPropietarios;

        setSize(600, 400);
        setLayout(new BorderLayout());

        // Tabla
        modelo = new DefaultTableModel(new Object[]{"Nombre", "Documento", "Teléfono", "Dirección"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Botones
        JButton btnAgregar = new JButton("Agregar propietario");
        btnAgregar.addActionListener(_ -> {
            PanelRegistrarPropietario panel = new PanelRegistrarPropietario(crudPropietarios, this);
            getParent().add(panel);
            panel.setVisible(true);
        });

        JButton btnEliminar = new JButton("Eliminar propietario");
        btnEliminar.addActionListener(_ -> eliminarSeleccionado());

        JButton btnEditar = new JButton("Editar propietario");
        btnEditar.addActionListener(_ -> editarPropietarioSeleccionado());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);

        add(panelBotones, BorderLayout.SOUTH);

        actualizarTabla();
    }

    public void actualizarTabla() {
        modelo.setRowCount(0); // limpiar
        for (Propietario p : crudPropietarios.getTodos()) {
            modelo.addRow(new Object[]{
                    p.getNombre(),
                    p.getDocumento(),
                    p.getTelefono(),
                    p.getDireccion()
            });
        }
    }

    private void eliminarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un propietario para eliminar.");
            return;
        }

        String documento = (String) modelo.getValueAt(fila, 1); // columna del documento
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar al propietario con documento " + documento + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (crudPropietarios.eliminarPorDocumento(documento)) {
                JOptionPane.showMessageDialog(this, "Propietario eliminado.");
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.");
            }
        }
    }

    private void editarPropietarioSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un propietario de la tabla.");
            return;
        }

        String documento = (String) modelo.getValueAt(fila, 1); // columna 1: documento
        Propietario propietario = crudPropietarios.buscarPorDocumento(documento);
        if (propietario == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el propietario.");
            return;
        }

        JTextField nuevoNombre = new JTextField(propietario.getNombre());
        JTextField nuevoTelefono = new JTextField(propietario.getTelefono());
        JTextField nuevaDireccion = new JTextField(propietario.getDireccion());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(nuevoNombre);
        panel.add(new JLabel("Teléfono:"));
        panel.add(nuevoTelefono);
        panel.add(new JLabel("Dirección:"));
        panel.add(nuevaDireccion);

        int r = JOptionPane.showConfirmDialog(this, panel, "Editar Propietario", JOptionPane.OK_CANCEL_OPTION);
        if (r == JOptionPane.OK_OPTION) {
            try {
                propietario.setNombre(nuevoNombre.getText().trim());
                propietario.setTelefono(nuevoTelefono.getText().trim());
                propietario.setDireccion(nuevaDireccion.getText().trim());

                JOptionPane.showMessageDialog(this, "✅ Propietario editado.");
                actualizarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage());
            }
        }
    }
}