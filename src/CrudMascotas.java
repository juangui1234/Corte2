import java.util.ArrayList;

public class CrudMascotas {

    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void agregarMascota(Mascota mascota) {
        if (mascota != null) {
            mascotas.add(mascota);
        }
    }

    public boolean eliminarMascotaPorNombre(String nombre) {
        Mascota mascota = buscarPorNombre(nombre);
        if (mascota != null) {
            return mascotas.remove(mascota);
        }
        return false;
    }

    public Mascota buscarPorNombre(String nombre) {
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m;
            }
        }
        return null;
    }

    public boolean eliminarPorNombre(String nombre) {
        Mascota encontrada = buscarPorNombre(nombre);
        if (encontrada != null) {
            mascotas.remove(encontrada);
            return true;
        }
        return false;
    }

    public boolean editarMascota(String nombreOriginal, Mascota nuevaInfo) {
        for (int i = 0; i < mascotas.size(); i++) {
            if (mascotas.get(i).getNombre().equalsIgnoreCase(nombreOriginal)) {
                mascotas.set(i, nuevaInfo);
                return true;
            }
        }
        return false;
    }

    public void limpiarLista() {
        mascotas.clear();
    }
}
