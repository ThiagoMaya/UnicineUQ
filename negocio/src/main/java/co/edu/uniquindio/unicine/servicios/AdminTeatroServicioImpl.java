package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio{

    private final AdministradorTeatroRepositorio administradorTeatroRepositorio;

    private final TeatroRepositorio teatroRepositorio;

    private final CiudadRepositorio ciudadRepositorio;

    private final FuncionRepositorio funcionRepositorio;

    private final SalaRepositorio salaRepositorio;

    private final PeliculaRepositorio peliculaRepositorio;

    private final HorarioRepositorio horarioRepositorio;

    private final DistribucionSillasRepositorio distribucionSillasRepositorio;


    public AdminTeatroServicioImpl(AdministradorTeatroRepositorio administradorTeatroRepositorio, TeatroRepositorio teatroRepositorio, CiudadRepositorio ciudadRepositorio, FuncionRepositorio funcionRepositorio, SalaRepositorio salaRepositorio, PeliculaRepositorio peliculaRepositorio, HorarioRepositorio horarioRepositorio, DistribucionSillasRepositorio distribucionSillasRepositorio) {
        this.administradorTeatroRepositorio = administradorTeatroRepositorio;
        this.teatroRepositorio = teatroRepositorio;
        this.ciudadRepositorio = ciudadRepositorio;
        this.funcionRepositorio = funcionRepositorio;
        this.salaRepositorio = salaRepositorio;
        this.peliculaRepositorio = peliculaRepositorio;
        this.horarioRepositorio = horarioRepositorio;
        this.distribucionSillasRepositorio = distribucionSillasRepositorio;
    }

    @Override
    public AdministradorTeatro iniciarSesion(String email, String contrasena) throws Exception {
        AdministradorTeatro admin = administradorTeatroRepositorio.comprobarAutenticacion(email,contrasena);
        if(admin == null){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return admin;
    }

    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {

        if(esRepetido(teatro.getNombre())) throw new Exception("Ya se ha registrado un teatro con ese nombre anteriormente");
        return teatroRepositorio.save(teatro);
    }

    private boolean esRepetido(String nombre) {
        return teatroRepositorio.findByNombre(nombre).orElse(null)!=null;

    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
        Optional<Teatro> actualizar = teatroRepositorio.findById(teatro.getCodigo());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado al teatro");

        return teatroRepositorio.save(teatro);
    }

    @Override
    public void eliminarTeatro(Integer idTeatro) throws Exception {

        Optional<Teatro> eliminar = teatroRepositorio.findById(idTeatro);

        if(eliminar.isEmpty()){
            throw new Exception("El teatro  no existe");
        }
        teatroRepositorio.delete(eliminar.get());

    }

    @Override
    public Pelicula obtenerPelicula(String nombrePelicula) throws Exception {
        Optional<Pelicula> guardado = peliculaRepositorio.findByNombre(nombrePelicula);

        if(!guardado.isPresent()){
            throw new Exception("La pelicula no existe");
        }
        return guardado.get();
    }

    @Override
    public Horario obtenerHorario(Integer idHorario) throws Exception {
        Optional<Horario> guardado = horarioRepositorio.findById(idHorario);

        if(!guardado.isPresent()){
            throw new Exception("El teatro no existe");
        }
        return guardado.get();
    }


    @Override
    public Teatro obtenerTeatro(Integer idTeatro) throws Exception {
        Optional<Teatro> guardado = teatroRepositorio.findById(idTeatro);

        if(!guardado.isPresent()){
            throw new Exception("El teatro no existe");
        }
        return guardado.get();
    }

    @Override
    public List<Teatro> listarTeatros() {
        return teatroRepositorio.findAll();
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepositorio.findAll();
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception {

        Optional<Ciudad> guardado = ciudadRepositorio.findById(codigoCiudad);

        if(!guardado.isPresent()){
            throw new Exception("El administrador no existe");
        }
        return guardado.get();
    }
    @Override
    public Sala crearSala(Sala sala) throws Exception {
        boolean salaRepetida = salaRepetida(sala.getCodigo());

        if(salaRepetida){
            throw new Exception("La sala ya se ha registrado anteriormente");
        }
        return salaRepositorio.save(sala);
    }

    private boolean salaRepetida(Integer codigo) {
        return salaRepositorio.findById(codigo).orElse(null)!=null;

    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {
        Optional<Sala> actualizar = salaRepositorio.findById(sala.getCodigo());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado la sala");
        if(salaRepetida(sala.getCodigo()))throw new Exception("La sala ya se ha registrado anteriormente");


        return salaRepositorio.save(sala);
    }

    @Override
    public void eliminarSala(Integer idSala) throws Exception {

        Optional<Sala> eliminar = salaRepositorio.findById(idSala);

        if(eliminar.isEmpty()){
            throw new Exception("La sala no existe");
        }
        salaRepositorio.delete(eliminar.get());
    }

    @Override
    public Sala obtenerSala(Integer idSala) throws Exception {
        Optional<Sala> guardado = salaRepositorio.findById(idSala);

        if(!guardado.isPresent()){
            throw new Exception("La sala no existe");
        }
        return guardado.get();
    }

    @Override
    public List<Sala> listarSalas() {
        return salaRepositorio.findAll();
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {

        verificarFuncion(funcion);

        return funcionRepositorio.save(funcion);
    }

    private void verificarFuncion(Funcion funcion) throws Exception {
        List<Funcion> funciones = funcionRepositorio.obtenerPorSala(funcion.getSala());


        for( Funcion funcionAux :funciones) {
            if(funcionAux.getSala().equals(funcion.getSala())){

                if(funcionAux.getHorario().equals(funcion.getHorario())){
                    throw new Exception("Ya existe una funcion a esa hora en esa sala");
                }
            }
            if(funcionRepetida(funcion.getCodigo())){
                throw new Exception("La funcion ya se ha registrado anteriormente");
            }
        }

    }

    private boolean funcionRepetida(Integer codigo) {
        return funcionRepositorio.findById(codigo).orElse(null)!=null;

    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {
        Optional<Funcion> actualizar = funcionRepositorio.findById(funcion.getCodigo());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado la funcion");
        if(funcionRepetida(funcion.getCodigo()))throw new Exception("La funcion ya se ha registrado anteriormente");
        verificarFuncion(funcion);

        return funcionRepositorio.save(funcion);
    }

    @Override
    public void eliminarFuncion(Integer idFuncion) throws Exception {

        Optional<Funcion> eliminar = funcionRepositorio.findById(idFuncion);

        if(eliminar.isEmpty()){
            throw new Exception("La sala no existe");
        }
        funcionRepositorio.delete(eliminar.get());
    }

    @Override
    public Funcion obtenerFuncion(Integer idFuncion) throws Exception {
        Optional<Funcion> guardado = funcionRepositorio.findById(idFuncion);

        if(!guardado.isPresent()){
            throw new Exception("La funcion no existe");
        }
        return guardado.get();
    }

    @Override
    public List<Funcion> listarFunciones() {
        return funcionRepositorio.findAll();
    }

    @Override
    public DistribucionSillas crearDistribucion(DistribucionSillas distribucionSillas) throws Exception {

        return distribucionSillasRepositorio.save(distribucionSillas);

    }

    @Override
    public DistribucionSillas actualizarDistribucion(DistribucionSillas distribucionSillas) throws Exception {
        Optional<DistribucionSillas> actualizar = distribucionSillasRepositorio.findById(distribucionSillas.getCodigo());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado la distribucion");

        return distribucionSillasRepositorio.save(distribucionSillas);
    }

    @Override
    public void eliminarDistribucion(Integer idDistribucion) throws Exception {

        Optional<DistribucionSillas> eliminar = distribucionSillasRepositorio.findById(idDistribucion);

        if(eliminar.isEmpty()){
            throw new Exception("La distribucion no existe");
        }
        distribucionSillasRepositorio.delete(eliminar.get());
    }

    @Override
    public DistribucionSillas obtenerDistribucion(Integer idFuncion) throws Exception {
        Optional<DistribucionSillas> guardado = distribucionSillasRepositorio.findById(idFuncion);

        if(!guardado.isPresent()){
            throw new Exception("La distribucion no existe");
        }
        return guardado.get();
    }

    public List<Teatro> listarTeatrosCiudad(Integer codigoCiudad) {
        return funcionRepositorio.listarTeatrosCiudad(codigoCiudad);
    }

    @Override
    public List<DistribucionSillas> listarDistribucion() {
        return distribucionSillasRepositorio.findAll();
    }
}
