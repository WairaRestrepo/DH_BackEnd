public class Usuario {

    private Integer id;
    private TipoUser tipoUsuario;

    public Usuario(Integer id, TipoUser tipoUsuario) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoUser getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUser tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
