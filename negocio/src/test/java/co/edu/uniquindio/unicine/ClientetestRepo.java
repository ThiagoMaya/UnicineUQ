package co.edu.uniquindio.unicine;


import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.repositorios.ClienteRepositorio;
import co.edu.uniquindio.unicine.entidades.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientetestRepo {
/*
    @Autowired
    private ClienteRepositorio clienterepository;



    @Test
    public void registrar(){

        ArrayList<String> telefono = new ArrayList<String>();
        telefono.add("32229392399");
        telefono.add("322293927799");

        Cliente cliente = new Cliente(1092850328,"santi","santiago@gmail.com",telefono,"asdsa");

        Cliente guardado = clienterepository.save(cliente);

        Assertions.assertNotNull(guardado);
    }
    @Test
    public void actualizar(){

        ArrayList<String> telefono = new ArrayList<String>();
        telefono.add("32229392399");
        telefono.add("322293927799");

        Cliente cliente = new Cliente(1092850328,"santi","santiago@gmail.com",telefono,"asdsa");
        cliente.setCedula(1);

        Cliente guardado = clienterepository.save(cliente);

        guardado.setNombre("Thiaginio");

        Cliente nuevo = clienterepository.save(guardado);

        Assertions.assertEquals("Thiaginio", nuevo.getNombre());
    }

    @Test
    public void eliminar(){

        ArrayList<String> telefono = new ArrayList<String>();
        telefono.add("32229392399");
        telefono.add("322293927799");

        Cliente cliente = new Cliente(1092850328,"santi","santiago@gmail.com",telefono,"asdsa");
        cliente.setCedula(1);

        Cliente guardado = clienterepository.save(cliente);

        clienterepository.delete(guardado);

        Optional<Cliente> buscado = clienterepository.findById(1);

        Assertions.assertNull(buscado.orElse(null));


    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){


        List<Cliente> lista = clienterepository.findAll();

        System.out.println(lista);
    }
    @Test
    public void obtener(){

        ArrayList<String> telefono = new ArrayList<String>();
        telefono.add("32229392399");
        telefono.add("322293927799");

        Cliente cliente = new Cliente(1092850328,"santi","santiago@gmail.com",telefono,"asdsa");

       clienterepository.save(cliente);

        Optional<Cliente> buscado = clienterepository.findById(1);

        System.out.println(buscado.orElse(null));

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorCorreo(){

        Cliente cliente = clienterepository.findByEmail("pepe@mail.com").orElse(null);
        Assertions.assertNotNull(cliente);


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutenticacion(){

        Cliente cliente  = clienterepository.findByCorreoAndAndContraseña("pepe@mail.com","Sdsadsa");
        Assertions.assertNotNull(cliente);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginador(){

        List<Cliente> lista  = clienterepository.obtenerPorEstado(true, PageRequest.of(0,2));

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarRegistros(){

        List<Cliente> lista  = clienterepository.findAll(PageRequest.of(0,3, Sort.by("nombre")) ).toList();

        lista.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompras(){

        List<Compra> compras  = clienterepository.obtenerCompras(1);

        compras.forEach(System.out::println);
    }


 */
}

