package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.AdministradorTeatroRepositorio;
import co.edu.uniquindio.unicine.repositorios.FuncionRepositorio;
import co.edu.uniquindio.unicine.repositorios.SalaRepositorio;
import co.edu.uniquindio.unicine.repositorios.TeatroRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminTeatroServicioImpl implements AdminTeatroServicio{

    private final AdministradorTeatroRepositorio administradorTeatroRepositorio;

    private final TeatroRepositorio teatroRepositorio;

    private final FuncionRepositorio funcionRepositorio;

    private final SalaRepositorio salaRepositorio;


    public AdminTeatroServicioImpl(AdministradorTeatroRepositorio administradorTeatroRepositorio, TeatroRepositorio teatroRepositorio, FuncionRepositorio funcionRepositorio, SalaRepositorio salaRepositorio) {
        this.administradorTeatroRepositorio = administradorTeatroRepositorio;
        this.teatroRepositorio = teatroRepositorio;
        this.funcionRepositorio = funcionRepositorio;
        this.salaRepositorio = salaRepositorio;
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

        return teatroRepositorio.save(teatro);
    }

    private boolean esRepetido(Integer codigo) {
        return teatroRepositorio.findById(codigo).orElse(null)!=null;

    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {
        Optional<Teatro> actualizar = teatroRepositorio.findById(teatro.getCodigo());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado al teatro");
        if(esRepetido(teatro.getCodigo()))throw new Exception("El teatro ya se ha registrado anteriormente");


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
        if(esRepetido(sala.getCodigo()))throw new Exception("La sala ya se ha registrado anteriormente");


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
        boolean salaRepetida = funcionRepetida(funcion.getCodigo());

        Funcion funcionAux = funcionRepositorio.obtenerPorSala(funcion.getSala());

         verificarFuncion(funcion);

        return funcionRepositorio.save(funcion);
    }

    private void verificarFuncion(Funcion funcion) throws Exception {
        Funcion funcionAux = funcionRepositorio.obtenerPorSala(funcion.getSala());

        if(funcionAux.getSala().equals(funcion.getSala())){

            Funcion funcionAux2 = funcionRepositorio.obtenerPorHorario(funcion.getHorario());

            if(funcionAux.getHorario().equals(funcion.getHorario())){
                throw new Exception("Ya existe una funcion a esa hora en esa sala");
            }
        }
        if(funcionRepetida(funcion.getCodigo())){
            throw new Exception("La funcion ya se ha registrado anteriormente");
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
            throw new Exception("La sala no existe");
        }
        return guardado.get();
    }

    @Override
    public List<Funcion> listarFunciones() {
        return funcionRepositorio.findAll();
    }
}
