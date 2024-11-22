import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrabajoGrado implements Serializable {
    private String id;
    private String fecha;
    private String titulo;
    private String descripcion;
    private List<String> autoresIds;

    public TrabajoGrado(String id, String fecha, String titulo, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autoresIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getAutoresIds() {
        return autoresIds;
    }

    public void addAutorId(String autorId) {
        autoresIds.add(autorId);
    }

    @Override
    public String toString() {
        return fecha + " - " + titulo;
    }
}
