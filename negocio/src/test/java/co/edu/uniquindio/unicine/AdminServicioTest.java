package co.edu.uniquindio.unicine;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.PeliculaRepositorio;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class AdminServicioTest {

    @Autowired
    private AdminServicio adminServicio;


    @Test
    @Sql("classpath:dataset.sql")
    public void iniciarSesion(){

        try {
            Administrador nuevo = adminServicio.iniciarSesion("santiago@mail.com", "4545k");
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void resgistrarAdminTeatro(){

        AdministradorTeatro administradorTeatro = AdministradorTeatro.builder().nombre("Santiago").cedula(1092850328).contraseña("12345").email("thg@mail.com").numeroTelefonico("30024587").build();

        try {
            AdministradorTeatro nuevo = adminServicio.crearAdministrador(administradorTeatro);
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministrador(){
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
    public void eliminarAdministrador(){

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
    public void listarAdministrador() {
        List<AdministradorTeatro> admins = adminServicio.listarAdministradores();
        Assertions.assertEquals(5, admins.size() );
        admins.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarPelicula(){


        try {
            Pelicula pelicula = Pelicula.builder().nombre("assh").estado(false).genero(Genero.ACCION).sinopsis("sinopsis").reparto("reparto").url_imagen("img").url_trailer("trailer").build();
            Pelicula nuevo = adminServicio.crearPelicula(pelicula);
            System.out.println(nuevo);
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPelicula(){
        try {
            Pelicula actualizar = adminServicio.obtenerPelicula("Taxi Driver");
            actualizar.setNombre("McGregor");
            Pelicula nuevo = adminServicio.actualizarPelicula(actualizar);
            Assertions.assertEquals("McGregor", nuevo.getNombre());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPelicula(){

        try {
            adminServicio.eliminarPelicula("Taxi Driver");
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            Pelicula nuevo = adminServicio.obtenerPelicula("Taxi Driver");
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculas() {
        List<Pelicula> peliculas = adminServicio.listarPeliculas();
        Assertions.assertEquals(5, peliculas.size() );
        peliculas.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarConfiteria(){


        try {
            Confiteria confiteria = Confiteria.builder().nombre("papas").estado("buen estado").precio(1000).descripcion("desc").build();
            Confiteria nuevo = adminServicio.crearComestible(confiteria);
            System.out.println(nuevo);
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarConfiteria(){
        try {
            Confiteria confiteria = adminServicio.obtenerComestible(1);
            confiteria.setNombre("detodito rojo");
            Confiteria nuevo = adminServicio.actualizarComestible(confiteria);
            Assertions.assertEquals("detodito rojo", nuevo.getNombre());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarConfiteria(){

        try {
            adminServicio.eliminarComestible(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            Confiteria confiteria = adminServicio.obtenerComestible(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteria() {
        List<Confiteria> confiterias = adminServicio.listarConfiteria();
        Assertions.assertEquals(5, confiterias.size() );
        confiterias.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarCupon(){


        try {
            Cupon cupon = Cupon.builder().descripcion("cupon regalo").criterio("bienvenida").fechaVencimiento(LocalDateTime.now()).valor(5000).build();
            Cupon nuevo = adminServicio.crearCupon(cupon);
            System.out.println(nuevo);
            Assertions.assertNotNull(nuevo);

        } catch (Exception e) {
            Assertions.assertTrue(false);
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCupon(){
        try {
            Cupon cupon = adminServicio.obtenerCupon(1);
            cupon.setCriterio("detodito rojo");
            Cupon nuevo = adminServicio.actualizarCupon(cupon);
            Assertions.assertEquals("detodito rojo", nuevo.getCriterio());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCupon(){

        try {
            adminServicio.eliminarCupon(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            Cupon cupon = adminServicio.obtenerCupon(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCupon() {
        List<Cupon> cupons = adminServicio.listarCupones();
        Assertions.assertEquals(5, cupons.size() );
        cupons.forEach(System.out::println);
    }



}
