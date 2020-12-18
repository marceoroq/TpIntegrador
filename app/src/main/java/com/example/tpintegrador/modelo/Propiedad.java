package com.example.tpintegrador.modelo;

public class Propiedad {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precioDia;
    private Boolean poseeInternet;
    private Boolean permiteMascotas;
    private TipoAlojamiento tipoPropiedad;
    private Integer capacidadPersonas;
    private float calificacion;
    private float latitud;
    private float longitud;

    public Propiedad() {}

    public Propiedad(String nombre, Double precioDia, Integer capacidadPersonas,
                     Boolean poseeInternet, Boolean permiteMascotas,
                     float latitud, float longitud) {

        this.nombre = nombre;
        this.precioDia = precioDia;
        this.poseeInternet = poseeInternet;
        this.permiteMascotas = permiteMascotas;
        this.capacidadPersonas = capacidadPersonas;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Double precioDia) {
        this.precioDia = precioDia;
    }

    public Boolean getPoseeInternet() {
        return poseeInternet;
    }

    public void setPoseeInternet(Boolean poseeInternet) {
        this.poseeInternet = poseeInternet;
    }

    public Boolean getPermiteMascotas() {
        return permiteMascotas;
    }

    public void setPermiteMascotas(Boolean permiteMascotas) {
        this.permiteMascotas = permiteMascotas;
    }

    public String getTipoPropiedad() {
        return tipoPropiedad.toString();
    }

    public void setTipoPropiedad(TipoAlojamiento tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public Integer getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(Integer capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(long latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(long longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Propiedad{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioDia=" + precioDia +
                ", poseeInternet=" + poseeInternet +
                ", permiteMascotas=" + permiteMascotas +
                ", tipoPropiedad=" + tipoPropiedad +
                ", capacidadPersonas=" + capacidadPersonas +
                ", calificacion=" + calificacion +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
