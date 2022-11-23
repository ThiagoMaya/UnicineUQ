package co.edu.uniquindio.unicine;


import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import co.edu.uniquindio.unicine.servicios.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ClienteServicioTest
{
    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private EmailServicio emailServicio;



    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCliente(){

        Cliente cliente = Cliente.builder().nombre("Santiago").cedula(1092850328).contraseña("12345").correo("pepe@mail.com").build();

        try {
            Cliente nuevo = clienteServicio.registrar(cliente);
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion(){


        try {
            Cliente nuevo = clienteServicio.inciarSesion("bicho@mail.com", "sdgfsdgds");
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void activarCuenta(){


        try {
            Cliente aux = clienteServicio.obtenerCliente(1);
            clienteServicio.activarCuenta(aux);
            System.out.println(aux.isEstado());
            Assertions.assertEquals(true, aux.isEstado());


        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar(){


        try {
            Cliente actualizar = clienteServicio.obtenerCliente(1);
            actualizar.setNombre("McGregor");
            Cliente nuevo = clienteServicio.actualizar(actualizar);
            Assertions.assertEquals("McGregor", nuevo.getNombre());

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminar(){

        try {
            clienteServicio.Eliminar(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            Cliente nuevo = clienteServicio.obtenerCliente(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar() {
        List<Cliente> clientes = clienteServicio.listarClientes();
        Assertions.assertEquals(5, clientes.size() );
        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCompras() {
        try {
            List<Compra> compras = clienteServicio.listarCompras(1);
            Assertions.assertEquals(1, compras.size() );
            compras.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompra() {
        try {
            Compra compra = clienteServicio.realizarCompra(1,1);
            Assertions.assertEquals(1, compra.getCliente().getCedula() );
            System.out.println(compra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void enviarCorreoTest() {
        emailServicio.enviarEmail("Prueba","Mensaje confirmación","santiagomayacc2.com");
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void escogerAsientos() {
        try {
            Entrada entrada = clienteServicio.escogerSillas(1,"1","a");
            System.out.println(entrada);
            Assertions.assertNotNull(entrada);
        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cantidadEntradas() {
        try {
            Compra compra = clienteServicio.escogerNumeroEntradas(1,3);
            Assertions.assertEquals(3, compra.getEntradas().size() );
            compra.getEntradas().forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void CompraConfiteria() {
        try {
            List<CompraConfiteria> confiterias = clienteServicio.comprarConfiteria(1,2,4);
            Assertions.assertEquals(2, confiterias.size() );
            confiterias.forEach(System.out::println);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void RedimirCupon() {
        try {
            Compra compra = clienteServicio.redimirCupon("Bienvenida",1);
            Assertions.assertEquals(false, compra.getCuponCliente().isEstado());
            System.out.println(compra.getCuponCliente());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculasNombre() {
        try {
            List<Pelicula> peliculas = clienteServicio.buscarPeliculaNombre("sapohpta");
            peliculas.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculasGenero() {
        try {
            List<Pelicula> peliculas = clienteServicio.buscarPeliculaGenero(Genero.TERROR);
            peliculas.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculasEstado() {
        try {
            List<Pelicula> peliculas = clienteServicio.buscarPeliculaEstado(EstadoPelicula.CARTELERA);
            peliculas.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void contrasenaOlvidada() {
        try {

            clienteServicio.cambiarContrasena("mariana240312@gmail.com");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void elegirMedioPago() {
        try {

            Compra compra = clienteServicio.elegirMetodoPago(1, MedioPago.VISA);
            Assertions.assertEquals(MedioPago.VISA, compra.getMedioPago());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
