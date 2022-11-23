package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador,Integer> {


    @Query("select c from Administrador c where c.email = ?1")
    Administrador obtener(String correo);

    @Query("select c from Administrador c where c.cedula = ?1")
    Administrador obtenerPorCedula(Integer cedula);

    Optional<Administrador> findByEmail(String email);

    Optional<Administrador> findByCedula(Integer cedula);

    @Query("select c from Administrador c where c.email =:email and c.contrasena =:contrasena")
    Administrador comprobarAutenticacion(String email, String contrasena);
}
