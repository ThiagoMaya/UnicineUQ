package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepositorio extends JpaRepository<Entrada, Integer> {

    @Query("select e from Entrada e where e.compra.codigo= :codigoCompra")
    List<Entrada> obtenerFromCompra(Integer codigoCompra);

    @Query("select e from Entrada e where e.Codigo= :codigo")
    Entrada obtener(Integer codigo);

}
