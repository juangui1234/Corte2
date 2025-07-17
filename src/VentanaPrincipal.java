import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JDesktopPane desktopPane;
    private CrudMascotas crudMascotas;
    private CrudPropietarios crudPropietarios;

    public VentanaPrincipal() {
        setTitle("Sistema de Gestión Clínica Veterinaria");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        crudPropietarios = new CrudPropietarios(); //crud propietario
        crudMascotas = new CrudMascotas(); // Instancia central del CRUD
        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        crearMenu();
        JOptionPane.showMessageDialog(
                this,
                "👋 Bienvenido al Sistema de Gestión Clínica Veterinaria de Mascotas **LA MEJOR** realizado por Juan Guillermo Salazar",
                "Bienvenido",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu menuArchivo = getJMenu();

        //menu vista
        JMenu menuVista = new JMenu("Vista");

        JMenuItem itemPacientes = new JMenuItem("Pacientes");
        itemPacientes.addActionListener(_ -> {
            ListaPacientes lista = new ListaPacientes(crudMascotas, crudPropietarios);
            desktopPane.add(lista);
            lista.setVisible(true);
        });

        JMenuItem itemHistorial = new JMenuItem("Historial de consultas");
        itemHistorial.addActionListener(_ -> {
            PanelHistorialConsultas panel = new PanelHistorialConsultas(crudMascotas);
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        menuVista.add(itemHistorial);menuVista.add(itemHistorial);

        JMenuItem itemConsulta = new JMenuItem("Consulta");
        itemConsulta.addActionListener(_ -> {
            PanelConsulta panel = new PanelConsulta(crudMascotas);
            desktopPane.add(panel);
            panel.setVisible(true);
        });

        JMenuItem itemVacunas = new JMenuItem("Vacunas");
        itemVacunas.addActionListener(e -> {
            PanelVacunas panelVacunas = new PanelVacunas(crudMascotas); // 👈 este debe existir
            desktopPane.add(panelVacunas); // escritorio es tu JDesktopPane
            panelVacunas.setVisible(true);
        });
        menuVista.add(itemVacunas);

        JMenuItem itemGestion = new JMenuItem("Gestión Mascotas");
        itemGestion.addActionListener(_ -> {
            PanelGestionMascotas panel = new PanelGestionMascotas(crudMascotas);
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuVista.add(itemGestion);

        menuVista.add(itemPacientes);
        menuVista.add(itemConsulta);

        menuBar.add(menuArchivo);
        menuBar.add(menuVista);

        setJMenuBar(menuBar);
    }



    private JMenu getJMenu() {
        JMenu menuArchivo = new JMenu("Archivo");

        //menu propietarios
        JMenuItem itemPropietarios = new JMenuItem("Registrar Propietario");
        itemPropietarios.addActionListener(_ -> {
            PanelGestionPropietarios panel = new PanelGestionPropietarios(crudPropietarios);
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuArchivo.add(itemPropietarios);

        JMenuItem itemNuevo = new JMenuItem("Registrar Mascota");
        itemNuevo.addActionListener(_ -> {
            FormularioPaciente form = new FormularioPaciente(crudMascotas, crudPropietarios);
            desktopPane.add(form);
            form.setVisible(true);
        });


        // Registra la consulta
        JMenuItem itemRegistrarConsulta = new JMenuItem("Registrar Consulta");
        itemRegistrarConsulta.addActionListener(_ -> {
            PanelRegistrarConsulta panel = new PanelRegistrarConsulta(crudMascotas);
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuArchivo.add(itemRegistrarConsulta);

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(_ -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estas seguro que deseas salir?",
                    "Confirma tu salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        menuArchivo.add(itemNuevo);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        return menuArchivo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}