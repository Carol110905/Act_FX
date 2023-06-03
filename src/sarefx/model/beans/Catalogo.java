package sarefx.model.beans;


public class Catalogo {
    private Integer idCatalogo;
    private Integer idCategoria;
    private String nombre;
    private char activo;
    private Integer orden;

    public Catalogo() {
    }

    public Catalogo(Integer idCatalogo, Integer idCategoria, String nombre, char activo, Integer orden) {
        this.idCatalogo = idCatalogo;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.activo = activo;
        this.orden = orden;
    }

    /**
     * @return the idCatalogo
     */
    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    /**
     * @param idCatalogo the idCatalogo to set
     */
    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    /**
     * @return the idCategoria
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the activo
     */
    public char getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(char activo) {
        this.activo = activo;
    }

    /**
     * @return the orden
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    
    
}
