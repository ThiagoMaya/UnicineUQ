package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorTeatroRepositorio extends JpaRepository<AdministradorTeatro,Integer> {

    @Query("select c from Administrador c where c.email = ?1")
    Administrador obtener(String correo);

    @Query("select c from Administrador c where c.cedula = ?1")
    Administrador obtenerPorCedula(Integer cedula);

    Optional<Administrador> findByEmail(String correo);

    Optional<Administrador> findByCedula(Integer cedula);

    @Query("select c from AdministradorTeatro c where c.email =:correo and c.contrasena =:contrasena")
    AdministradorTeatro comprobarAutenticacion(String correo, String contrasena);
}
