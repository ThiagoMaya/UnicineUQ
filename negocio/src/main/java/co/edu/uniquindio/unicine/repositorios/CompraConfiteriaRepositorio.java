package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraConfiteriaRepositorio extends JpaRepository<CompraConfiteria, Integer> {

    @Query("select c from CompraConfiteria c where c.compra.codigo= :codigoCompra")
    List<CompraConfiteria> obtenerFromCompra(Integer codigoCompra);
}
