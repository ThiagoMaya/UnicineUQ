package co.edu.uniquindio.unicine.test.repositorios;

import co.edu.uniquindio.unicine.test.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio  extends JpaRepository<Cliente, String> {

}
