/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import DAOS.VajillaDAO;
import Model.Vajilla;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Puesto19
 */
public class Controlador {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        try {
            while (true) {
                System.out.println("Menú Principal");
                System.out.println("1. Alta de nuevo elemento");
                System.out.println("2. Eliminar un elemento");
                System.out.println("3. Modificar cierta cantidad de un elemento");
                System.out.println("4. Mostrar stock");
                System.out.println("5. Salir");

                Scanner scanner = new Scanner(System.in);
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        altaNuevoElemento(em);
                        break;
                    case 2:
                        System.out.println("Lista de elementos antes de la eliminación:");
                        mostrarStock(em);
                        eliminarElemento(em);
                        break;
                    case 3:
                        mostrarStock(em);
                        modificarCantidadElemento(em);
                        break;
                    case 4:
                        mostrarStock(em);
                        break;

                    case 5:
                        System.out.println("Saliendo del programa.");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            }
        } finally {
            em.close();
        }
    }

    private static void altaNuevoElemento(EntityManager em) {
        System.out.println("Ingrese el nombre del elemento:");
        String nombre = new Scanner(System.in).nextLine();

        System.out.println("Ingrese la descripción del elemento:");
        String descripcion = new Scanner(System.in).nextLine();

        System.out.println("Ingrese la cantidad del elemento:");
        int cantidad = new Scanner(System.in).nextInt();

        // Llamada a tu método DAO para dar de alta un nuevo elemento
        VajillaDAO.altaNuevoElemento(em, nombre, descripcion, cantidad);
    }

    private static void eliminarElemento(EntityManager em) {
        System.out.println("Ingrese el código del elemento a eliminar:");
        String codigo = new Scanner(System.in).nextLine();

        // Llamada a tu método DAO para eliminar un elemento
        VajillaDAO.eliminarElemento(em, codigo);
    }

    private static void modificarCantidadElemento(EntityManager em) {
        System.out.println("Ingrese el código del elemento a modificar:");
        String codigo = new Scanner(System.in).nextLine();

        System.out.println("Ingrese la cantidad a disminuir:");
        int cantidad = new Scanner(System.in).nextInt();

        // Llamada a tu método DAO para modificar la cantidad de un elemento
        VajillaDAO.modificarCantidadElemento(em, codigo, cantidad);
    }

    private static void mostrarStock(EntityManager em) {
        // Llamada a tu método DAO para mostrar el stock
        List<Vajilla> stock = VajillaDAO.mostrarStock(em);
        System.out.println("Stock de elementos:");
        for (Vajilla vajilla : stock) {
            System.out.println("Código: " + vajilla.getCodigoElemento()
                    + ", Nombre: " + vajilla.getNombreElemento()
                    + ", Cantidad: " + vajilla.getCantidadElemento());
        }
    }

}
