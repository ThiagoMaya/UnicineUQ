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

    @Test
    @Sql("classpath:dataset.sql")
    public void resgistrarTeatro(){

        try {
            AdministradorTeatro adminTeatro = adminTeatroServicio.iniciarSesion("Cristiano@mail.com","78475f");
            Ciudad ciudad = adminTeatroServicio.obtenerCiudad(1);
            Teatro teatro = Teatro.builder().ciudad(ciudad).adminTeatro(adminTeatro).nombre("Teatro prueba")
                    .telefono("7409899").direccion("Centro Comercial Unicentro").build();

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
            Teatro actualizar = adminTeatroServicio.obtenerTeatro(1);
            actualizar.setNombre("Nombre actualizado");
            Teatro nuevo = adminTeatroServicio.actualizarTeatro(actualizar);
            Assertions.assertEquals("Nombre actualizado", nuevo.getNombre());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatro(){

        try {
            adminTeatroServicio.eliminarTeatro(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            Teatro nuevo = adminTeatroServicio.obtenerTeatro(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatros() {
        List<Teatro> teatros = adminTeatroServicio.listarTeatros();
        Assertions.assertEquals(5, teatros.size() );
        teatros.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void resgistrarFunciones() throws Exception {

        Pelicula pelicula = adminTeatroServicio.obtenerPelicula("Taxi driver");
        Sala sala = adminTeatroServicio.obtenerSala(1);
        Horario horario = adminTeatroServicio.obtenerHorario(4);
        Funcion funcion = Funcion.builder().sala(sala).precio(10000).pelicula(pelicula).horario(horario).build();
        funcion.setCodigo(6);
        System.out.println(funcion.getCodigo());
        Funcion nuevo = adminTeatroServicio.crearFuncion(funcion);
        Assertions.assertNotNull(nuevo);



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
    public void eliminarFuncion(){

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
    public void listarFunciones() {
        List<Funcion> admins = adminTeatroServicio.listarFunciones();
        Assertions.assertEquals(5, admins.size() );
        admins.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void registrarSala() throws Exception {

        DistribucionSillas dist = adminTeatroServicio.obtenerDistribucion(1);
        Teatro teatro = adminTeatroServicio.obtenerTeatro(1);
        Sala sala = Sala.builder().nombre("Sala nueva").teatro(teatro).distribucionSillas(dist).build();
        Sala nuevo = adminTeatroServicio.crearSala(sala);
        Assertions.assertNotNull(nuevo);


    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSala(){
        try {
            Sala actualizar = adminTeatroServicio.obtenerSala(1);
            actualizar.setCodigo(17);
            Sala nuevo = adminTeatroServicio.actualizarSala(actualizar);
            Assertions.assertEquals(17, nuevo.getCodigo());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSala(){

        try {
            adminTeatroServicio.eliminarSala(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            Sala nuevo = adminTeatroServicio.obtenerSala(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalas() {
        List<Funcion> admins = adminTeatroServicio.listarFunciones();
        Assertions.assertEquals(5, admins.size() );
        admins.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void registrarDistribucion() throws Exception {


        DistribucionSillas distribucionSillas = DistribucionSillas.builder().total_Sillas(40).esquema("Basico").filas(10).columnas(12).build();
        DistribucionSillas nuevo = adminTeatroServicio.crearDistribucion(distribucionSillas);
        Assertions.assertNotNull(nuevo);


    }


    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarDistribucion(){
        try {
            DistribucionSillas distribucionSillas = adminTeatroServicio.obtenerDistribucion(1);
            distribucionSillas.setCodigo(17);
            DistribucionSillas nuevo = adminTeatroServicio.actualizarDistribucion(distribucionSillas);
            Assertions.assertEquals(17, nuevo.getCodigo());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarDistribucion(){

        try {
            adminTeatroServicio.eliminarDistribucion(1);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
        try{
            DistribucionSillas nuevo = adminTeatroServicio.obtenerDistribucion(1);
        }catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarDistribuciones() {
        List<DistribucionSillas> distribucionSillas = adminTeatroServicio.listarDistribucion();
        Assertions.assertEquals(5, distribucionSillas.size() );
        distribucionSillas.forEach(System.out::println);
    }




}
