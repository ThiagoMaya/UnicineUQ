package co.edu.uniquindio.unicine;


import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.repositorios.FuncionRepositorio;
import co.edu.uniquindio.unicine.repositorios.HorarioRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FuncionTest {


    @Autowired
    private FuncionRepositorio funcionRepositorio;



    @Test
    @Sql("classpath:dataset.sql")
    public void obteberPeliculaFuncion(){

        String nombrePelicula = funcionRepositorio.nombrePelicula(3);

        Assertions.assertEquals("Star Wars", nombrePelicula);

        System.out.println(nombrePelicula);
    }



}
