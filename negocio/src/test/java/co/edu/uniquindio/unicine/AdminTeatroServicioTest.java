package co.edu.uniquindio.unicine;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.TeatroRepositorio;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class AdminTeatroServicioTest {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion(){

        try {
            AdministradorTeatro nuevo = adminTeatroServicio.iniciarSesion("santiago@mail.com", "4545k");
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }
    /*
    @Test
    @Sql("classpath:dataset.sql")
    public void resgistrarTeatro(){

        try {
            AdministradorTeatro admin = adminServicio.obtenerAdministrador(1);

            Teatro teatro = Teatro.builder().ciudad(ciudad).adminTeatro(admin).build();

            Teatro nuevo = adminTeatroServicio.crearTeatro(teatro);
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatro(){
        try {
            AdministradorTeatro actualizar = adminServicio.obtenerAdministrador(1);
            actualizar.setNombre("McGregor");
            AdministradorTeatro nuevo = adminServicio.actualizarAdministrador(actualizar);
            Assertions.assertEquals("McGregor", nuevo.getNombre());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro(){

        try {
            adminServicio.eliminarAdministrador(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            AdministradorTeatro nuevo = adminServicio.obtenerAdministrador(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros() {
        List<AdministradorTeatro> admins = adminServicio.listarAdministradores();
        Assertions.assertEquals(5, admins.size() );
        admins.forEach(System.out::println);
    }

     */
    @Test
    @Sql("classpath:dataset.sql")
    public void resgistrarFunciones(){

        try {
            Pelicula pelicula = adminServicio.obtenerPelicula("Taxi driver");
            Sala sala = adminTeatroServicio.obtenerSala(1);

            Funcion funcion = Funcion.builder().precio(1000).pelicula(pelicula).sala(sala).build();

            Funcion nuevo = adminTeatroServicio.crearFuncion(funcion);
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarFuncion(){
        try {
            Funcion actualizar = adminTeatroServicio.obtenerFuncion(1);
            actualizar.setCodigo(17);
            Funcion nuevo = adminTeatroServicio.actualizarFuncion(actualizar);
            Assertions.assertEquals(17, nuevo.getCodigo());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro(){

        try {
            adminTeatroServicio.eliminarFuncion(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            Funcion nuevo = adminTeatroServicio.obtenerFuncion(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros() {
        List<Funcion> admins = adminTeatroServicio.listarFunciones();
        Assertions.assertEquals(5, admins.size() );
        admins.forEach(System.out::println);
    }



}
