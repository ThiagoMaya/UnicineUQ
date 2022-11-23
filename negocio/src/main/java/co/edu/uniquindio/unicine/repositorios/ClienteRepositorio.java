package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositorio  extends JpaRepository<Cliente, Integer> {
    @Query("select c from Cliente c where c.email = ?1")
    Cliente obtener(String correo);

    @Query("select c from Cliente c where c.cedula = ?1")
    Cliente obtenerPorCedula(Integer cedula);
    Optional<Cliente> findByEmail(String correo);

    Optional<Cliente> findByCedula(Integer cedula);

    @Query("select c from Cliente c where c.email =:correo and c.contrasena =:contrasena")
    Cliente comprobarAutenticacion(String correo, String contrasena);

    @Query("select c from Cliente c where c.email =:correo and c.contrasena =:contrasena")
    Cliente findByCorreoAndAndContraseña(String correo, String contrasena);

    @Query("select c from Cliente c where c.estado =:estado")
    List<Cliente> obtenerPorEstado(Boolean estado, Pageable paginador);

    @Query("Select compra from Cliente c, in (c.compras) compra where c.cedula = :cedula")
    List<Compra> obtenerCompras(Integer cedula);

    @Query("Select c from Compra c where c.cliente.email= :correo")
    List<Compra> obtenerComprasV2(String correo);

}