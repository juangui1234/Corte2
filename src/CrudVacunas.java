import java.util.List;

public class CrudVacunas {

    // Registrar vacuna a una mascota existente
    public boolean registrarVacuna(Mascota mascota, Vacuna vacuna) {
        if (mascota != null && vacuna != null) {
            mascota.agregarVacuna(vacuna);
            System.out.println("✅ Vacuna registrada para " + mascota.getNombre());
            return true;
        }
        return false;
    }

    //listar vacunas de una mascota
    public void listarVacunas(Mascota mascota) {
        if (mascota != null) {
            mascota.mostrarVacunas();
        } else {
            System.out.println("❌ Mascota no encontrada.");
        }
    }

    // Buscar vacuna por tipo en una mascota
    public Vacuna buscarVacunaPorTipo(Mascota mascota, String tipo) {
        if (mascota != null && tipo != null) {
            List<Vacuna> vacunas = mascota.getVacunas();
            for (Vacuna v : vacunas) {
                if (v.getTipo().equalsIgnoreCase(tipo)) {
                    return v;
                }
            }
        }
        return null;
    }

    //eliminar vacuna por tipo
    public boolean eliminarVacunaPorTipo(Mascota mascota, String tipo) {
        Vacuna vacuna = buscarVacunaPorTipo(mascota, tipo);
        if (vacuna != null) {
            mascota.getVacunas().remove(vacuna);
            System.out.println("🗑️ Vacuna '" + tipo + "' eliminada de " + mascota.getNombre());
            return true;
        }
        return false;
    }

    //editar vacuna por tipo
    public boolean editarVacunaPorTipo(Mascota mascota, String tipoOriginal, Vacuna nuevaVacuna) {
        List<Vacuna> vacunas = mascota.getVacunas();
        for (int i = 0; i < vacunas.size(); i++) {
            if (vacunas.get(i).getTipo().equalsIgnoreCase(tipoOriginal)) {
                vacunas.set(i, nuevaVacuna);
                System.out.println("✏️ Vacuna actualizada.");
                return true;
            }
        }
        return false;
    }
}