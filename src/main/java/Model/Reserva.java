/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Puesto19
 */
@Entity
@Table(name = "Reserva", schema = "esqexados")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", nullable = false)
    private long idReserva;

    @Column(name = "fch_reserva")
    private Date fchReserva;

    @ManyToMany
    @JoinTable(
        name = "Reserva_ElementoVajilla",
        joinColumns = @JoinColumn(name = "id_reserva"),
        inverseJoinColumns = @JoinColumn(name = "id_elemento_vajilla")
    )
    private List<Vajilla> elementosVajilla;

    public Reserva() {
    }

    public Reserva(long idReserva, Date fchReserva, List<Vajilla> elementosVajilla) {
        this.idReserva = idReserva;
        this.fchReserva = fchReserva;
        this.elementosVajilla = elementosVajilla;
    }
    

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFchReserva() {
        return fchReserva;
    }

    public void setFchReserva(Date fchReserva) {
        this.fchReserva = fchReserva;
    }

    public List<Vajilla> getElementosVajilla() {
        return elementosVajilla;
    }

    public void setElementosVajilla(List<Vajilla> elementosVajilla) {
        this.elementosVajilla = elementosVajilla;
    }


   
    
    
    
}
