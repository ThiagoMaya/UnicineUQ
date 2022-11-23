package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminTeatroServicio {


    AdministradorTeatro iniciarSesion(String email, String contrasena) throws Exception;

    Teatro crearTeatro(Teatro teatro) throws Exception;

    Teatro actualizarTeatro(Teatro teatro) throws Exception;

    void eliminarTeatro(Integer idTeatro) throws Exception;

    Teatro obtenerTeatro(Integer idTeatro) throws Exception;

    List<Teatro> listarTeatros();

    List<Ciudad> listarCiudades();

    Pelicula obtenerPelicula(String nombrePelicula) throws Exception;

    Horario obtenerHorario(Integer idHorario) throws Exception;

    Ciudad obtenerCiudad(Integer codigoCiudad)throws  Exception;

    Sala crearSala(Sala sala) throws Exception;

    Sala actualizarSala(Sala sala) throws Exception;

    void eliminarSala(Integer idSala) throws Exception;

    Sala obtenerSala(Integer idSala) throws Exception;

    List<Sala> listarSalas();


    Funcion crearFuncion(Funcion funcion) throws Exception;

    Funcion actualizarFuncion(Funcion funcion) throws Exception;

    void eliminarFuncion(Integer idFuncion) throws Exception;

    Funcion obtenerFuncion(Integer idFuncion) throws Exception;

    List<Funcion> listarFunciones();

    DistribucionSillas crearDistribucion(DistribucionSillas distribucionSillas) throws Exception;

    DistribucionSillas actualizarDistribucion(DistribucionSillas distribucionSillas) throws Exception;

    void eliminarDistribucion(Integer idDistribucion) throws Exception;

    DistribucionSillas obtenerDistribucion(Integer idDistribucion) throws Exception;

    List<DistribucionSillas> listarDistribucion();

    List<Teatro> listarTeatrosCiudad(Integer codigoCiudad);


}
