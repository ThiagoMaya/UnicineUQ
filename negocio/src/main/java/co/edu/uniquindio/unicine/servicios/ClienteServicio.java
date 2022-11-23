package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteServicio {

    Cliente obtenerCliente(Integer cedula) throws Exception;

    Cliente inciarSesion(String correo, String contraseña) throws Exception;

    Cliente activarCuenta(Cliente cliente);

    Cliente registrar(Cliente cliente) throws Exception;

    Cliente actualizar(Cliente cliente)throws Exception;

    void Eliminar(Integer codigoCliente)throws Exception;

    List<Cliente> listarClientes();

    List<Compra> listarCompras(Integer codigoUsuario)throws Exception;

    Compra realizarCompra(Integer funcion, Integer cliente)throws Exception;

    Compra escogerNumeroEntradas(Integer codigoCompra, Integer numeroEntradas)throws Exception;

    public Compra redimirCupon(String criterio, Integer codigoCompra)throws Exception;

    List<CompraConfiteria>comprarConfiteria(Integer codigoCompra, Integer codigoConfiteria, Integer cantidad)throws Exception;

    boolean cambiarContrasena(String correoCliente) throws Exception;

    List<Pelicula> buscarPeliculaNombre(String nombrePelicula)throws Exception;

    List<Pelicula> buscarPeliculaGenero(Genero generoPelicula)throws Exception;

    List<Pelicula> buscarPeliculaEstado(EstadoPelicula estadoPelicula)throws Exception;

    Compra elegirMetodoPago(Integer compra, MedioPago metodoPago) throws Exception;

    List<Pelicula> listarPeliculasEstado(Integer codigoCiudad, EstadoPelicula estadoPelicula) throws Exception;

    Compra finalizarCompra(Compra compra) throws Exception ;

    Compra obtenerCompra(Integer idCompra) throws Exception;

    List<Entrada> obtenerEntradasCompradas(Funcion funcion) throws Exception;
}
