import javax.swing.*;
import java.awt.*;

public class PanelRegistrarPropietario extends JInternalFrame {

    private CrudPropietarios crudPropietarios;
    private PanelGestionPropietarios panelPadre;

    public PanelRegistrarPropietario(CrudPropietarios crudPropietarios, PanelGestionPropietarios panelPadre) {
        super("Registrar Propietario", true, true, true, true);
        this.crudPropietarios = crudPropietarios;
        this.panelPadre = panelPadre;

        setSize(300, 300); // aumentamos el tamaño vertical
        setLayout(new GridLayout(5, 2, 5, 5)); // ahora 5 filas

        JTextField txtNombre = new JTextField();
        JTextField txtDocumento = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtDireccion = new JTextField();  // 👉 nuevo campo
        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(_ -> {
            String nombre = txtNombre.getText().trim();
            String documento = txtDocumento.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim(); // ✅ capturamos dirección

            if (nombre.isEmpty() || documento.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            try {
                // ✅ usamos el constructor correcto con 4 datos
                Propietario nuevo = new Propietario(nombre, documento, telefono, direccion);
                crudPropietarios.agregarPropietario(nuevo);
                panelPadre.actualizarTabla();
                JOptionPane.showMessageDialog(this, "Propietario registrado.");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        });

        // Agregar componentes
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Documento:"));
        add(txtDocumento);
        add(new JLabel("Teléfono:"));
        add(txtTelefono);
        add(new JLabel("Dirección:")); // 👈 nuevo campo visual
        add(txtDireccion);
        add(new JLabel());
        add(btnGuardar);
    }
}
