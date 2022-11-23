package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServicio {


    Administrador iniciarSesion(String email, String contrasena) throws Exception;

    AdministradorTeatro crearAdministrador(AdministradorTeatro administradorTeatro) throws Exception;

    AdministradorTeatro actualizarAdministrador(AdministradorTeatro administradorTeatro) throws Exception;

    void eliminarAdministrador(Integer cedulaAdministrador) throws Exception;

    AdministradorTeatro obtenerAdministrador(Integer cedulaAdministrador) throws Exception;


    List<AdministradorTeatro> listarAdministradores();

    Ciudad obtenerCiudad(Integer codigoCiudad)throws  Exception;


    Pelicula crearPelicula(Pelicula pelicula) throws Exception;

    Pelicula actualizarPelicula(Pelicula pelicula) throws Exception;

    void eliminarPelicula(String nombrePelicula) throws Exception;


    public List<Funcion> obtenerFuncionesPelicula(Integer peliculaCodigo) throws Exception;

    Pelicula obtenerPelicula(Integer idPelicula) throws Exception;

    List<Pelicula> listarPeliculas();


    Confiteria crearComestible(Confiteria comestible) throws Exception;

    Confiteria actualizarComestible(Confiteria comestible) throws Exception;

    void eliminarComestible(Integer idComestible) throws Exception;

    Confiteria obtenerComestible(Integer idComestible) throws Exception;

    List<Confiteria> listarConfiteria();


    Cupon crearCupon(Cupon cupon) throws Exception;

    Cupon actualizarCupon(Cupon cupon) throws Exception;

    void eliminarCupon(Integer idCupon) throws Exception;

    Cupon obtenerCupon(Integer idCupon) throws Exception;

    List<Cupon> listarCupones();


}

