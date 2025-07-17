import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PanelRegistrarConsulta extends JInternalFrame {

    private CrudMascotas crudMascotas;

    public PanelRegistrarConsulta(CrudMascotas crudMascotas) {
        super("Registrar Consulta", true, true, true, true);
        this.crudMascotas = crudMascotas;

        setSize(450, 300);
        setLayout(new GridLayout(6, 2, 5, 5));

        JLabel lblMascota = new JLabel("Mascota:");
        JComboBox<Mascota> comboMascotas = new JComboBox<>();
        for (Mascota m : crudMascotas.getMascotas()) {
            comboMascotas.addItem(m);  // usa el toString de Mascota
        }

        JLabel lblVeterinario = new JLabel("Veterinario:");
        JTextField txtVeterinario = new JTextField();

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        JTextField txtEspecialidad = new JTextField();

        JLabel lblDisponible = new JLabel("¿Disponible?");
        JCheckBox chkDisponible = new JCheckBox();

        JButton btnRegistrar = new JButton("Registrar Consulta");

        btnRegistrar.addActionListener(_ -> {
            Mascota mascota = (Mascota) comboMascotas.getSelectedItem();
            String nombreVet = txtVeterinario.getText().trim();
            String especialidad = txtEspecialidad.getText().trim();
            boolean disponible = chkDisponible.isSelected(); // ✅ leer checkbox

            if (mascota == null || nombreVet.isEmpty() || especialidad.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            try {
                String codigo = IDGenerator.generarCodigoConsulta();
                LocalDate fecha = LocalDate.now(); // fecha automática
                Veterinario veterinario = new Veterinario(nombreVet, especialidad, disponible); // ✅ ahora sí

                Consulta consulta = new Consulta(codigo, fecha, veterinario);
                mascota.agregarConsulta(consulta);

                JOptionPane.showMessageDialog(this, "✅ Consulta registrada con éxito.");
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        });

        // Agregar componentes
        add(lblMascota);
        add(comboMascotas);
        add(lblVeterinario);
        add(txtVeterinario);
        add(lblEspecialidad);
        add(txtEspecialidad);
        add(lblDisponible);
        add(chkDisponible);
        add(new JLabel()); // espacio vacío
        add(btnRegistrar);
    }
}