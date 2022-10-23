package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServicioImpl implements AdminServicio{

     private final AdministradorRepositorio administradorRepositorio;

    private final PeliculaRepositorio peliculaRepositorio;

    private final ConfiteriaRepositorio confiteriaRepositorio;

    private final CuponRepositorio cuponRepositorio;


    private final AdministradorTeatroRepositorio administradorTeatroRepositorio;

    public AdminServicioImpl(AdministradorRepositorio administradorRepositorio, PeliculaRepositorio peliculaRepositorio, ConfiteriaRepositorio confiteriaRepositorio, CuponRepositorio cuponRepositorio, AdministradorTeatroRepositorio administradorTeatroRepositorio) {
        this.administradorRepositorio = administradorRepositorio;
        this.peliculaRepositorio = peliculaRepositorio;
        this.confiteriaRepositorio = confiteriaRepositorio;
        this.cuponRepositorio = cuponRepositorio;
        this.administradorTeatroRepositorio = administradorTeatroRepositorio;
    }

    @Override
    public Administrador iniciarSesion(String email, String contrasena) throws Exception {

        Administrador admin = administradorRepositorio.comprobarAutenticacion(email,contrasena);
        if(admin == null){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return admin;
    }

    @Override
    public AdministradorTeatro crearAdministrador(AdministradorTeatro administradorTeatro) throws Exception {

        boolean correoExistente = esRepetido(administradorTeatro.getEmail());
        boolean cedulaExistente = cedulaRepetida(administradorTeatro.getCedula());

        if(correoExistente){
            throw new Exception("El correo ya se ha registrado anteriormente");
        }
        if(cedulaExistente){
            throw new Exception("La cedula ya se ha registrado anteriormente");
        }

        return administradorTeatroRepositorio.save(administradorTeatro);
    }

    private Boolean esRepetido(String correo) {
        return administradorTeatroRepositorio.findByEmail(correo).orElse(null)!=null;
    }

    private Boolean cedulaRepetida(Integer cedula) {
        return administradorTeatroRepositorio.findById(cedula).orElse(null)!=null;
    }


    @Override
    public AdministradorTeatro actualizarAdministrador(AdministradorTeatro administradorTeatro) throws Exception {

        Optional<AdministradorTeatro> adminTeatro = administradorTeatroRepositorio.findById(administradorTeatro.getCedula());

        if (adminTeatro.isEmpty()) throw new Exception("No se ha encontrado al admin");
        //if(esRepetido(administradorTeatro.getEmail()))throw new Exception("El correo ya se ha registrado antes");
        //if(cedulaRepetida(administradorTeatro.getCedula()))throw new Exception("La cedula ya se ha registrado antes");

        return administradorTeatroRepositorio.save(administradorTeatro);
    }

    @Override
    public void eliminarAdministrador(Integer cedulaAdministrador) throws Exception {


        Optional<AdministradorTeatro> eliminar = administradorTeatroRepositorio.findById(cedulaAdministrador);

        if(eliminar.isEmpty()){
            throw new Exception("El administrador del teatro no existe");
        }
        administradorTeatroRepositorio.delete(eliminar.get());

    }

    @Override
    public AdministradorTeatro obtenerAdministrador(Integer cedulaAdministrador) throws Exception {

        Optional<AdministradorTeatro> guardado = administradorTeatroRepositorio.findById(cedulaAdministrador);

        if(!guardado.isPresent()){
            throw new Exception("El administrador no existe");
        }
        return guardado.get();
    }

    @Override
    public List<AdministradorTeatro> listarAdministradores() {
        return administradorTeatroRepositorio.findAll();
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {

        boolean nombreRepetido = nombreRepetido(pelicula.getNombre());


        if(nombreRepetido){
            throw new Exception("Ya existe una pelicula con ese nombte");
        }

        return peliculaRepositorio.save(pelicula);
    }

    private boolean nombreRepetido(String nombre) {
        return peliculaRepositorio.findByNombre(nombre).orElse(null)!=null;

    }

    private boolean peliculaExistente(Integer codigo) {
        return peliculaRepositorio.findById(codigo).orElse(null)!=null;
    }



    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {

        Optional<Pelicula> actualizar = peliculaRepositorio.findById(pelicula.getCodigo());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado la pelicula");
       // if(peliculaExistente(pelicula.getCodigo()))throw new Exception("La pelicula ya se ha registrado antes");
        //if(nombreRepetido(pelicula.getNombre()))throw new Exception("La pelicula ya se ha registrado antes");

        return peliculaRepositorio.save(pelicula);
    }


    @Override
    public void eliminarPelicula(String nombrePelicula) throws Exception {

        Optional<Pelicula> eliminar = peliculaRepositorio.findByNombre(nombrePelicula);

        if(eliminar.isEmpty()){
            throw new Exception("La pelicula no existe");
        }
        peliculaRepositorio.delete(eliminar.get());

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
    public List<Pelicula> listarPeliculas() {
        return peliculaRepositorio.findAll();
    }

    @Override
    public Confiteria crearComestible(Confiteria comestible) throws Exception {

        return confiteriaRepositorio.save(comestible);
    }

    private boolean confiteriaExistente(Integer idProducto) {
        return confiteriaRepositorio.findById(idProducto).orElse(null)!=null;

    }

    @Override
    public Confiteria actualizarComestible(Confiteria comestible) throws Exception {
        Optional<Confiteria> actualizar = confiteriaRepositorio.findById(comestible.getIdProducto());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado el comestible");
        //if(confiteriaExistente(comestible.getIdProducto()))throw new Exception("El comestible ya se ha registrado antes");

        return confiteriaRepositorio.save(comestible);
    }

    @Override
    public void eliminarComestible(Integer idComestible) throws Exception {
        Optional<Confiteria> eliminar = confiteriaRepositorio.findById(idComestible);

        if(eliminar.isEmpty()){
            throw new Exception("El comestible no existe");
        }
        confiteriaRepositorio.delete(eliminar.get());
    }

    @Override
    public Confiteria obtenerComestible(Integer idComestible) throws Exception {
        Optional<Confiteria> guardado = confiteriaRepositorio.findById(idComestible);

        if(!guardado.isPresent()){
            throw new Exception("La pelicula no existe");
        }
        return guardado.get();
    }

    @Override
    public List<Confiteria> listarConfiteria() {
        return confiteriaRepositorio.findAll();
    }

    @Override
    public Cupon crearCupon(Cupon cupon) throws Exception {

        return cuponRepositorio.save(cupon);

    }

    private boolean cuponExistente(Integer codigo) {
        return cuponRepositorio.findById(codigo).orElse(null)!=null;

    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {
        Optional<Cupon> actualizar = cuponRepositorio.findById(cupon.getCodigo());

        if (actualizar.isEmpty()) throw new Exception("No se ha encontrado el comestible");
        //if(cuponExistente(cupon.getCodigo()))throw new Exception("El comestible ya se ha registrado antes");

        return cuponRepositorio.save(cupon);
    }

    @Override
    public void eliminarCupon(Integer idCupon) throws Exception {
        Optional<Cupon> eliminar = cuponRepositorio.findById(idCupon);

        if(eliminar.isEmpty()){
            throw new Exception("El comestible no existe");
        }
        cuponRepositorio.delete(eliminar.get());
    }

    @Override
    public Cupon obtenerCupon(Integer idCupon) throws Exception {
        Optional<Cupon> guardado = cuponRepositorio.findById(idCupon);

        if(!guardado.isPresent()){
            throw new Exception("La pelicula no existe");
        }
        return guardado.get();
    }

    @Override
    public List<Cupon> listarCupones() {
        return cuponRepositorio.findAll();
    }
}
