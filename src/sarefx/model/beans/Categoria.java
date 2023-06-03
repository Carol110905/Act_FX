package sarefx.model.beans;


public class Categoria {
    private Integer idCategoria;
    private String nombre;
    private String activo;
    private Integer orden;

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombre, String activo, Integer orden) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.activo = activo;
        this.orden = orden;
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
    public String getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(String activo) {
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
