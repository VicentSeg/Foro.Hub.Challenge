package Foro.Hub.Challenge.api.Curso;

public class Curso {
    private Long id;
    private String nombreCurso;
    private String categoria;

    public Curso(DatosRegistroCurso curso){
        this.categoria = curso.categoria();
        this.nombreCurso = curso.nombreCurso();
    }
}
