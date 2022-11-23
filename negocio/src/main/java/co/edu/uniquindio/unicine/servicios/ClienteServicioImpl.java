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

        boolean correoExistente = esRepetido(cliente.getEmail());
        boolean cedulaExistente = cedulaRepetida(cliente.getCedula());

        if(correoExistente){
            throw new Exception("El correo ya se ha registrado anteriormente");
        }
        if(cedulaExistente){
            throw new Exception("La cedula ya se ha registrado anteriormente");
        }

        Cliente registrado = clienteRepo.save(cliente);
        enviarCuponRegistro(cliente);
        enviarConfirmacion(cliente);
        return registrado;

    }
    private Boolean esRepetido(String correo) {
        return clienteRepo.findByEmail(correo).orElse(null)!=null;
    }

    private Boolean cedulaRepetida(Integer cedula) {
        return clienteRepo.findById(cedula).orElse(null)!=null;
    }


    private void enviarConfirmacion(Cliente cliente) {
        String mensaje = "Hola " + cliente.getNombre() + "Has creado exitosamente tu cuenta en UniCine " +
                "Este es un mensaje de confirmación,no debes contestarlo";


       // emailServicio.enviarEmail("Creación de cuenta unicine", mensaje, cliente.getCorreo());
    }

    private void enviarCuponRegistro(Cliente cliente) {
        String mensaje = "Por haber completado tu registro, te obsequiaremos un cupón de bienvenida " +
                "Este cupón otorga un 15% de descuento en todas tus compras ";

        // emailServicio.enviarEmail("Cupon de Bienvenida", mensaje, cliente.getCorreo());
    }
    @Override
    public Cliente actualizar(Cliente cliente) throws Exception{
        Optional<Cliente> clienteGuardado = clienteRepo.findById(cliente.getCedula());

        if (clienteGuardado.isEmpty()) throw new Exception("No se ha encontrado al cliente");
        if(esRepetido(cliente.getEmail()))throw new Exception("El correo ya se ha registrado antes");
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
        cliente.setContrasena(contrasena);
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

        List<Genero> generosBuscados = null;
        generosBuscados.add(generoPelicula);

        if(generoPelicula.equals("")||generoPelicula==null){
            throw new Exception("Debe indicar el género");
        }
        if(peliculaRepositorio.buscarPeliculaGenero(generosBuscados).isEmpty()){
            throw new Exception("No existen películas con dicho género");
        }
        return peliculaRepositorio.buscarPeliculaGenero(generosBuscados);
    }

    @Override
    public List<Pelicula> buscarPeliculaEstado(EstadoPelicula estadoPelicula) throws Exception {
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

    @Override
    public List<Pelicula> listarPeliculasEstado(Integer codigoCiudad, EstadoPelicula estadoPelicula) throws Exception {
        return funcionRepositorio.listarPeliculasEstado(codigoCiudad,estadoPelicula);
    }

    public Compra finalizarCompra(Compra compra) throws Exception {
        if(compra == null) throw new Exception("No hay compra para registrar");

        compra.setFechaCompra( LocalDateTime.now() );
        compra.calcularValorTotal();

        compraRepositorio.save(compra);
        entradaRepo.saveAll( compra.getEntradas() );
        compraConfiteriaRepositorio.saveAll(compra.getCompraConfiterias() );

        if( primeraCompra(compra.getCliente()) ) enviarCuponPrimeraCompra(compra.getCliente());

        enviarConfirmacionCompra(compra.getCliente(), compra);

        return compra;
    }

    @Override
    public Compra obtenerCompra(Integer idCompra) throws Exception {
            Optional<Compra> guardado = compraRepositorio.findById(idCompra);

            if(!guardado.isPresent()){
                throw new Exception("La compra no existe");
            }
            return guardado.get();

    }

    private boolean primeraCompra(Cliente cliente) {
        return clienteRepo.obtenerCompras(cliente.getCedula()).size() <= 1;
    }

    private void enviarCuponPrimeraCompra(Cliente cliente) {

        String mensaje = "Felicidades por tu primera compra! Para que sigas disfrutando del mejor cine te hemos enviado un cupon de regalo " +
                "con el que puedes obtener un 10% de descuento del valor total de cualquier compra! ";



        emailServicio.enviarEmail("Cupon de Regalo - 10% Descuento", mensaje, cliente.getEmail());
    }

    private void enviarConfirmacionCompra(Cliente cliente, Compra compra) {
        String mensaje = "¡Has realizado una compra!     " +
                " | ID de la compra: " + compra.getCodigo() +
                " | Cantidad de entradas: " + compra.getEntradas().size() +
                " | Subtotal entradas: $" + compra.obtenerTotalEntradas() +
                " | Pelicula: " + compra.getFuncion().getPelicula().getNombre() +
                " | Sala: " + compra.getFuncion().getSala().getNombre() +
                " | Teatro: " + compra.getFuncion().getSala().getTeatro().getNombre() + " " + compra.getFuncion().getSala().getTeatro().getCiudad().getNombre() +
                " | Direccion: " + compra.getFuncion().getSala().getTeatro().getDireccion() +
                " | Subtotal confiteria: $" + compra.obtenerTotalConfiteria() +
                " | Fecha y hora de la funcion: " + compra.getFuncion().getHorario().getFecha().toString() + " " + compra.getFuncion().getHorario().getHora().toString() + "\n" +
                " || VALOR TOTAL: $" + compra.getTotal() + " ||      " +
                " ----------------------- " +
                " Puedes verla en tu historial de compras: https://bit.ly/3s7ETPZ";

        emailServicio.enviarEmail("Confirmacion Compra", mensaje, cliente.getEmail());
    }
    @Override
    public List<Entrada> obtenerEntradasCompradas(Funcion funcion) throws Exception {
        if(funcion == null) throw new Exception("No hay funcion de la cual obtener entradas compradas");
        if( !funcionRepositorio.existsById(funcion.getCodigo()) )
            throw new Exception("La funcion no existe en la base de datos");

        return entradaRepo.obtenerEntradasCompradasFuncion(funcion.getCodigo());
    }





}