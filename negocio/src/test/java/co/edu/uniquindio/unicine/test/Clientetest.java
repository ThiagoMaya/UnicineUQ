package co.edu.uniquindio.unicine.test;


import co.edu.uniquindio.unicine.test.entidades.Cliente;
import co.edu.uniquindio.unicine.test.repositorios.ClienteRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Clientetest {

    @Autowired
    private ClienteRepositorio clienterepo;

    @Test
    public void registrar(){

        ArrayList<String> telefono = new ArrayList<String>();
        telefono.add("32229392399");
        telefono.add("322293927799");

        Cliente cliente = new Cliente("1213131","pepito","santiago@gmail.com",telefono,"asdsa");

        Cliente guardado = clienterepo.save(cliente);

        Assertions.assertNotNull(guardado);
    }
    @Test
    public void actualizar(){

        ArrayList<String> telefono = new ArrayList<String>();
        telefono.add("32229392399");
        telefono.add("322293927799");

        Cliente cliente = new Cliente("1213131","pepito","santiago@gmail.com",telefono,"asdsa");

        Cliente guardado = clienterepo.save(cliente);

        guardado.setNombre("Thiaginio");

        Cliente nuevo = clienterepo.save(guardado);

        Assertions.assertEquals("Thiaginio", nuevo.getNombre());
    }
    public void eliminar(){

        ArrayList<String> telefono = new ArrayList<String>();
        telefono.add("32229392399");
        telefono.add("322293927799");

        Cliente cliente = new Cliente("1213131","pepito","santiago@gmail.com",telefono,"asdsa");
        cliente.setCedula("44444");

        Cliente guardado = clienterepo.save(cliente);

        clienterepo.delete(guardado);

        //Cliente buscado = clienterepo.findBy();

    }
    public void buscar(){

    }
}
