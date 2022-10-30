package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad, Integer> {


    @Query("select c from Ciudad c where c.codigo = ?1")
    Ciudad obtenerPorCodigo(Integer codigo);

    @Query("select c from Ciudad c where c.nombre = ?1")
    Ciudad obtenerPorNombre(String nombre);
}
