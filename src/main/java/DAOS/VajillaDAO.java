/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOS;

import Model.Vajilla;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Puesto19
 */
public class VajillaDAO {

    private final EntityManager entityManager;

    public VajillaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Método para realizar el alta de un nuevo elemento de vajilla
    public static Vajilla altaNuevoElemento(EntityManager entityManager, String nombre, String descripcion, int cantidad) {
        Vajilla nuevoElemento = new Vajilla();
        nuevoElemento.setNombreElemento(nombre);
        nuevoElemento.setDescripcionElemento(descripcion);
        nuevoElemento.setCantidadElemento(cantidad);
        nuevoElemento.setCodigoElemento(); // Método para generar el código

        // Guardar en la base de datos
        entityManager.getTransaction().begin();
        entityManager.persist(nuevoElemento);
        entityManager.getTransaction().commit();

        return nuevoElemento;
    }

    public static void modificarCantidadElemento(EntityManager em, String codigo, int cantidad) {
        em.getTransaction().begin();

        // Recuperar el elemento por el código
        TypedQuery<Vajilla> query = em.createQuery("SELECT v FROM Vajilla v WHERE v.codigoElemento = :codigo", Vajilla.class);
        query.setParameter("codigo", codigo);

        Vajilla vajilla = query.getSingleResult();

        if (vajilla != null) {
            // Modificar la cantidad del elemento
            int nuevaCantidad = vajilla.getCantidadElemento() - cantidad;
            if (nuevaCantidad < 0) {
                nuevaCantidad = 0;
            }

            vajilla.setCantidadElemento(nuevaCantidad);
            em.merge(vajilla);
        } else {
            System.out.println("Elemento no encontrado con el código: " + codigo);
        }

        em.getTransaction().commit();
    }

    // Método para eliminar un elemento de vajilla por su código
    public static void eliminarElemento(EntityManager entityManager, String codigo) {
    try {
        // Mostrar la lista de elementos antes de la eliminación
        System.out.println("Lista de elementos antes de la eliminación:");
        mostrarStock(entityManager);

        // Buscar el elemento por su código
        Vajilla elementoEliminar = entityManager.createQuery("SELECT v FROM Vajilla v WHERE v.codigo = :codigo", Vajilla.class)
                .setParameter("codigo", codigo)
                .getSingleResult();

        // Realizar la eliminación
        entityManager.getTransaction().begin();
        entityManager.remove(elementoEliminar);
        entityManager.getTransaction().commit();

        System.out.println("Eliminación exitosa.");

        // Mostrar la lista de elementos después de la eliminación
        System.out.println("Lista de elementos después de la eliminación:");
        mostrarStock(entityManager);
    } catch (Exception e) {
        System.err.println("Error al eliminar el elemento: " + e.getMessage());
        e.printStackTrace();
    }
}

   
    public static List<Vajilla> mostrarStock(EntityManager entityManager) {
        // Consulta para obtener el stock de elementos
        TypedQuery<Vajilla> query = entityManager.createQuery("SELECT v FROM Vajilla v", Vajilla.class);

        // Ejecutar la consulta
        List<Vajilla> stock = query.getResultList();

        // Mostrar el código, nombre y cantidad de cada elemento
        for (Vajilla elemento : stock) {
            System.out.println("Código: " + elemento.getCodigoElemento()
                    + ", Nombre: " + elemento.getNombreElemento()
                    + ", Cantidad: " + elemento.getCantidadElemento());
        }

        return stock;
    }
}
