package Foro.Hub.Challenge.api.Usuario;

import java.util.Set;

public class Usuario {
    private Long id;
    private String nombre;
    private String mail;
    private String contrasena;
    private Set<Perfil>  perfil;

    public Usuario(DatosRegistroUsuario usuario) {
        this.nombre = usuario.nombre();
        this.mail =  usuario.mail();
        this.contrasena  = usuario.contrasena();
       // this.perfil = new Perfil(usuario.perfil());
    }
}
