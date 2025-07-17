import javax.swing.*;
import java.awt.*;

public class FormularioPaciente extends JInternalFrame {

    private CrudMascotas crudMascotas;
    private CrudPropietarios crudPropietarios;

    public FormularioPaciente(CrudMascotas crudMascotas, CrudPropietarios crudPropietarios) {
        super("Nuevo Paciente", true, true, true, true);
        this.crudMascotas = crudMascotas;
        this.crudPropietarios = crudPropietarios;

        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblEspecie = new JLabel("Especie:");
        JComboBox<String> cbEspecie = new JComboBox<>(new String[]{"Perro", "Gato", "Ave", "Otro"});

        JLabel lblEdad = new JLabel("Edad:");
        JSpinner spEdad = new JSpinner(new SpinnerNumberModel(1, 0, 50, 1));

        JLabel lblPropietario = new JLabel("Propietario:");
        JComboBox<Propietario> comboPropietarios = new JComboBox<>();
        for (Propietario p : crudPropietarios.getTodos()) {
            comboPropietarios.addItem(p);  // usa el toString del propietario
        }

        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(_ -> {
            String nombre = txtNombre.getText().trim();
            String especie = cbEspecie.getSelectedItem().toString();
            int edad = (int) spEdad.getValue();
            Propietario propietarioSeleccionado = (Propietario) comboPropietarios.getSelectedItem();

            if (nombre.isEmpty() || propietarioSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Nombre y propietario son obligatorios.");
                return;
            }

            try {
                Mascota mascota = new Mascota(nombre, especie, edad);
                propietarioSeleccionado.agregarMascota(mascota); // asigna al propietario
                crudMascotas.agregarMascota(mascota);                   // guarda en la lista global

                JOptionPane.showMessageDialog(this, "Mascota registrada con éxito.");
                dispose();  // Cierra el formulario
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // Agregar componentes al formulario
        add(lblNombre);
        add(txtNombre);
        add(lblEspecie);
        add(cbEspecie);
        add(lblEdad);
        add(spEdad);
        add(lblPropietario);
        add(comboPropietarios);
        add(new JLabel());  // espacio vacío
        add(btnRegistrar);
    }
}