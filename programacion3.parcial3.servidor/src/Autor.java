import java.io.Serializable;

public class Autor implements Serializable {
    private String id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String programa;
    private String tituloProfesional;

    public Autor(String id, String nombre, String apellidos, String cedula, String programa, String tituloProfesional) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.programa = programa;
        this.tituloProfesional = tituloProfesional;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getPrograma() {
        return programa;
    }

    public String getTituloProfesional() {
        return tituloProfesional;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + programa;
    }
}
