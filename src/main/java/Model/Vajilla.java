/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Puesto19
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Vajilla", schema = "esqexados")
public class Vajilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_elemento_vajilla", nullable = false)
    private long idElementoVajilla;

    @Column(name = "codigo_elemento", nullable = false)
    private String codigoElemento;

    @Column(name = "nombre_elemento")
    private String nombreElemento;

    @Column(name = "descripcion_elemento")
    private String descripcionElemento;

    @Column(name = "cantidad_elemento", nullable = false)
    private int cantidadElemento;

    @ManyToMany(mappedBy = "elementosVajilla")
    private List<Reserva> reservas;

    // Constructores (puedes agregar otros constructores según tus necesidades)
    public Vajilla() {
    }

    public Vajilla(long idElementoVajilla, String codigoElemento, String nombreElemento, String descripcionElemento, int cantidadElemento, List<Reserva> reservas) {
        this.idElementoVajilla = idElementoVajilla;
        this.codigoElemento = codigoElemento();
        this.nombreElemento = nombreElemento;
        this.descripcionElemento = descripcionElemento;
        this.cantidadElemento = cantidadElemento;
        this.reservas = reservas;
    }
    // Método para generar el código como la concatenación del id con el nombre

    public long getIdElementoVajilla() {
        return idElementoVajilla;
    }

    public void setIdElementoVajilla(long idElementoVajilla) {
        this.idElementoVajilla = idElementoVajilla;
    }

    public String getCodigoElemento() {
        return codigoElemento;
    }

    public void setCodigoElemento() {
        this.codigoElemento = codigoElemento();

    }

    public String getNombreElemento() {
        return nombreElemento;
    }

    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public String getDescripcionElemento() {
        return descripcionElemento;
    }

    public void setDescripcionElemento(String descripcionElemento) {
        this.descripcionElemento = descripcionElemento;
    }

    public int getCantidadElemento() {
        return cantidadElemento;
    }

    public void setCantidadElemento(int cantidadElemento) {
        this.cantidadElemento = cantidadElemento;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    private String codigoElemento() {

        return this.idElementoVajilla + "_" + this.nombreElemento;
    }

   

}
