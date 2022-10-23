package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepositorio clienteRepo;
    private final EmailServicio emailServicio;
    private final CompraRepositorio compraRepositorio;
    private final FuncionRepositorio funcionRepositorio;
    private final PeliculaRepositorio peliculaRepositorio;
    private final CuponRepositorio cuponRepositorio;
    private final CuponClienteRepository cuponClienteRepositorio;
    private final CompraConfiteriaRepositorio compraConfiteriaRepositorio;
    private final EntradaRepositorio entradaRepo;
    private final ConfiteriaRepositorio confiteriaRepositorio;

    public ClienteServicioImpl(ClienteRepositorio clienteRepo, EmailServicio emailServicio, CompraRepositorio compraRepositorio, FuncionRepositorio funcionRepositorio, PeliculaRepositorio peliculaRepositorio, CuponRepositorio cuponRepositorio, CuponClienteRepository cuponClienteRepositorio, CompraConfiteriaRepositorio compraConfiteriaRepositorio, EntradaRepositorio entradaRepo, ConfiteriaRepositorio confiteriaRepositorio) {
        this.clienteRepo = clienteRepo;
        this.emailServicio = emailServicio;
        this.compraRepositorio = compraRepositorio;
        this.funcionRepositorio = funcionRepositorio;
        this.peliculaRepositorio = peliculaRepositorio;
        this.cuponRepositorio = cuponRepositorio;
        this.cuponClienteRepositorio = cuponClienteRepositorio;
        this.compraConfiteriaRepositorio = compraConfiteriaRepositorio;
        this.entradaRepo = entradaRepo;
        this.confiteriaRepositorio = confiteriaRepositorio;
    }

    @Override
    public Cliente obtenerCliente(Integer cedula) throws Exception {
        Optional<Cliente> guardado = clienteRepo.findById(cedula);

        if(!guardado.isPresent()){
            throw new Exception("El cliente no existe");
        }
        return guardado.get();
    }

    @Override
    public Cliente inciarSesion(String correo, String contraseña) throws Exception {
        Cliente cliente = clienteRepo.findByCorreoAndAndContraseña(correo,contraseña);
        if(cliente == null){
            throw new Exception("Los datos de autenticación son incorrectos");
        }
        return cliente;
    }

    @Override
    public Cliente activarCuenta(Cliente cliente) {

        cliente.setEstado(true);
        clienteRepo.save(cliente);
        return cliente;
    }

    @Override
    public Cliente registrar(Cliente cliente) throws Exception {

        boolean correoExistente = esRepetido(cliente.getCorreo());
        boolean cedulaExistente = cedulaRepetida(cliente.getCedula());

        if(correoExistente){
            throw new Exception("El correo ya se ha registrado anteriormente");
        }
        if(cedulaExistente){
            throw new Exception("La cedula ya se ha registrado anteriormente");
        }
        enviarCuponRegistro(cliente);
        enviarConfirmacion(cliente);

        return clienteRepo.save(cliente);
    }
    private Boolean esRepetido(String correo) {
        return clienteRepo.findByCorreo(correo).orElse(null)!=null;
    }

    private Boolean cedulaRepetida(Integer cedula) {
        return clienteRepo.findById(cedula).orElse(null)!=null;
    }


    private void enviarConfirmacion(Cliente cliente) {
        String mensaje = "Hola " + cliente.getNombre() + "Has creado exitosamente tu cuenta en UniCine " +
                "Este es un mensaje de confirmación,no debes contestarlo";


        emailServicio.enviarEmail("Creación de cuenta unicine", mensaje, cliente.getCorreo());
    }

    private void enviarCuponRegistro(Cliente cliente) {
        String mensaje = "Por haber completado tu registro, te obsequiaremos un cupón de bienvenida " +
                "Este cupón otorga un 15% de descuento en todas tus compras ";

        emailServicio.enviarEmail("Cupon de Bienvenida", mensaje, cliente.getCorreo());
    }
    @Override
    public Cliente actualizar(Cliente cliente) throws Exception{
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cliente.getCedula());

        if (clienteGuardado.isEmpty()) throw new Exception("No se ha encontrado al cliente");
        if(esRepetido(cliente.getCorreo()))throw new Exception("El correo ya se ha registrado antes");
        if(cedulaRepetida(cliente.getCedula()))throw new Exception("La cedula ya se ha registrado antes");

        return clienteRepo.save(cliente);
    }

    @Override
    public void Eliminar(Integer codigoCliente) throws Exception{

        Optional<Cliente> eliminar = clienteRepo.findById(codigoCliente);

        if(eliminar.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        clienteRepo.delete(eliminar.get());

    }

    @Override
    public List<Cliente> listarClientes() {

        return clienteRepo.findAll();
    }

    @Override
    public List<Compra> listarCompras(Integer codigoUsuario) throws Exception {

        Optional<Cliente> cliente = clienteRepo.findById(codigoUsuario);

        if(cliente.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        if(clienteRepo.obtenerCompras(codigoUsuario).isEmpty()){
            throw new Exception("El cliente no ha realizado ninguna compra");
        }

        return clienteRepo.obtenerCompras(codigoUsuario);
    }

    @Override
    public Compra realizarCompra(Integer codigoCliente, Integer codigoFuncion) throws Exception {

        Cliente cliente = clienteRepo.obtenerPorCedula(codigoCliente);
        Funcion funcion = funcionRepositorio.obtenerPorCodigo(codigoFuncion);
        LocalDateTime localDate = LocalDateTime.now();

        if( cliente == null ) {
            throw new Exception("No se ha selecicionado un cliente");
        }
        if( funcion == null ) {
            throw new Exception("Debe seleccionar una funcion");
        }

        Compra compra = Compra.builder().cliente(cliente).funcion(funcion).total(funcion.getPrecio()).fechaCompra(localDate).build();
        compraRepositorio.save(compra);
        return compra;


    }

    @Override
    public Compra escogerNumeroEntradas(Integer codigocompra, Integer numeroEntradas) throws Exception {

        Compra compra = compraRepositorio.obtener(codigocompra);
        if(numeroEntradas<1){
            throw new Exception("Número de entradas no válido");
        }
        compra.setEntradas(generarEntradas(numeroEntradas, compra));
        compraRepositorio.save(compra);
        return compra;
    }

    private List<Entrada> generarEntradas(Integer numeroEntradas, Compra compra) {

        for (int i = 0; i < numeroEntradas; i++){

            Entrada entrada = Entrada.builder().compra(compra).precio(compra.getFuncion().getPrecio()).build();
            entradaRepo.save(entrada);
        }
        return entradaRepo.obtenerFromCompra(compra.getCodigo());
    }


    @Override
    public Entrada escogerSillas(Integer codigoentrada, String fila, String columna) throws Exception {

        Entrada entrada = entradaRepo.obtener(codigoentrada);

        if (entrada == null) throw new Exception("No se ha creado la entrada");

        if(entrada.getFila() != null && entrada.getColumna() != null){
            throw new Exception("La entrada ya tiene su asiento asignado");
        }
        entrada.setFila(fila);
        entrada.setColumna(columna);
        entradaRepo.save(entrada);

        return entrada;
    }



    public List<CompraConfiteria> comprarConfiteria(Integer codigoCompra, Integer codigoConfiteria, Integer cantidad) throws Exception {

        Compra compra = compraRepositorio.obtener(codigoCompra);
        Confiteria confiteria = confiteriaRepositorio.obtener(codigoConfiteria);
        crearCompraConfiteria(cantidad,compra,confiteria);
        if(cantidad<1){
            throw new Exception("Cantidad no válida");
        }
        compra.setCompraConfiterias(compraConfiteriaRepositorio.obtenerFromCompra(codigoCompra));
        compraRepositorio.save(compra);

        return compra.getCompraConfiterias();

    }

    @Override
    public boolean cambiarContrasena(String correoCliente) throws Exception {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String contrasena = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Cliente cliente = clienteRepo.obtener(correoCliente);
        String mensaje = "Ha solicitado cambiar su contraseña"+ "su nueva contraseña temporal es: "+contrasena;
        cliente.setContraseña(contrasena);
        clienteRepo.save(cliente);
        if(cliente == null){
            throw new Exception("Cliente no encontrado");
        }

        return emailServicio.enviarEmail("Cambio de Contraseña", mensaje, correoCliente);
    }

    @Override
    public List<Pelicula> buscarPeliculaNombre(String nombrePelicula) throws Exception {
        if(nombrePelicula.equals("")||nombrePelicula==null){
            throw new Exception("Debe indicar el nombre");
        }
        if(peliculaRepositorio.buscarPeliculaNombre(nombrePelicula).isEmpty()){
            throw new Exception("No existen películas con ese nombre");

        }
        return peliculaRepositorio.buscarPeliculaNombre(nombrePelicula);
    }


    @Override
    public List<Pelicula> buscarPeliculaGenero(Genero generoPelicula) throws Exception {
        if(generoPelicula.equals("")||generoPelicula==null){
            throw new Exception("Debe indicar el género");
        }
        if(peliculaRepositorio.buscarPeliculaGenero(generoPelicula).isEmpty()){
            throw new Exception("No existen películas con dicho género");
        }
        return peliculaRepositorio.buscarPeliculaGenero(generoPelicula);
    }

    @Override
    public List<Pelicula> buscarPeliculaEstado(Boolean estadoPelicula) throws Exception {
        if(estadoPelicula.equals("")||estadoPelicula==null){
            throw new Exception("Debe indicar el estado");
        }
        if(peliculaRepositorio.buscarPeliculaEstado(estadoPelicula).isEmpty()){
            throw new Exception("No existen películas con dicho estado");
        }
        return peliculaRepositorio.buscarPeliculaEstado(estadoPelicula);
    }


    private void crearCompraConfiteria(Integer cantidad, Compra compra, Confiteria confiteria) {

        CompraConfiteria compraConfiteria = CompraConfiteria.builder().confiteria(confiteria).compra(compra).precio(confiteria.getPrecio()*cantidad).unidades(cantidad).build();

        compraConfiteriaRepositorio.save(compraConfiteria);
    }

    @Override
    public Compra redimirCupon(String criterio, Integer codigoCompra)throws Exception {

        CuponCliente cuponRedimir = cuponClienteRepositorio.obtenerPorCriterio(criterio);
        Compra compra = compraRepositorio.obtener(codigoCompra);

        if(!cuponRedimir.isEstado()){
            throw new Exception("El cupon ya se ha gastado anteriromente");

        }
        if(compra.getCuponCliente()!= null){
           throw new Exception("Ya se ha redimido un cupón para esta compra");
        }
        cuponRedimir.setEstado(false);
        cuponRedimir.setCompra(compra);
        cuponClienteRepositorio.save(cuponRedimir);
        compra.setCuponCliente(cuponRedimir);
        compraRepositorio.save(compra);

        return compra;
    }

    @Override
    public Compra elegirMetodoPago(Integer codigoCompra, MedioPago metodoPago) throws Exception {

        if( metodoPago == null ) {
            throw new Exception("No se ha seleccionado metodo de pago");
        }
        Compra compra = compraRepositorio.obtener(codigoCompra);

        if(compraRepositorio.findById(compra.getCodigo()).isEmpty()){
            throw new Exception("No existe la compra");
        }
        compra.setMedioPago(metodoPago);
        compraRepositorio.save(compra);

        return compra;
    }




}