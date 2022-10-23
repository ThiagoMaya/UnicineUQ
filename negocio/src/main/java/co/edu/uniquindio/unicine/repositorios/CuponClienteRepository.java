package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponClienteRepository extends JpaRepository<CuponCliente, Integer> {

    @Query("select c from CuponCliente c where c.cupon.criterio= :criterio")
    CuponCliente obtenerPorCriterio(String criterio);


}
