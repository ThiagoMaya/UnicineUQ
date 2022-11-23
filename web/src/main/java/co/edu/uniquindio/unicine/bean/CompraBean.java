package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdminServicio;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
public class CompraBean implements Serializable {

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ClienteServicio clienteServicio;


    @Value("#{param['funcion_id']}")
    private Integer idFuncion;

    @Value(value = "#{seguridadBean.personaActiva}")
    private Persona personaActiva;


    @Getter @Setter
    private Funcion funcion;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private ArrayList<Integer> filas, columnas;

    @Getter @Setter
    private Compra compra;

    @Getter @Setter
    private List<Confiteria> confiteria;

    @Getter @Setter
    private List< List<Entrada> > matriz;

    @Getter @Setter
    private List<Entrada> seleccionadas;

    @Getter @Setter
    private List<Entrada> entradasCompradas;

    @Getter @Setter
    private List<CompraConfiteria> comprasConfiteria;

    @Getter @Setter
    private int unidadesCompradas;

    @Getter @Setter
    private Float valorTotalEntradas;

    @Getter @Setter
    private Float valorTotalConfiteria;

    @Getter @Setter
    private Float valorTotal;

    @Getter @Setter
    private List<MedioPago> metodosPago = List.of( MedioPago.values() );

    @Getter @Setter
    private MedioPago metodoPago;

    @Getter @Setter
    private List<CuponCliente> cuponesCliente;

    @Getter @Setter
    private CuponCliente cupon;

    @Getter @Setter
    private DistribucionSillas distribucionSillas;

    @PostConstruct
    public void init() {

        unidadesCompradas = 0;
        valorTotalConfiteria = 0.0f;
        valorTotalEntradas = 0.0f;
        valorTotal = 0.0f;
        seleccionadas = new ArrayList<>();
        comprasConfiteria = new ArrayList<>();
        confiteria = adminServicio.listarConfiteria();
        matriz = new ArrayList<>();
        compra = new Compra();
        entradasCompradas = new ArrayList<>();

        if(personaActiva!=null) {


            try {
                cliente = (Cliente) personaActiva;
                funcion = adminTeatroServicio.obtenerFuncion(idFuncion);
                int filas = funcion.getSala().getDistribucionSillas().getFilas();
                int columnas = funcion.getSala().getDistribucionSillas().getColumnas();

                for (int i = 1; i <= filas; i++) {
                    List<Entrada> fila = new ArrayList<>();

                    for (int j = 1; j <= columnas; j++) {
                        fila.add(Entrada.builder().fila(i).columna(j).precio(8000).build());
                    }
                    matriz.add(fila);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void guadarSilla(int fila, int columna){
        boolean repetida = false;


        if(seleccionadas.size() > 0) {

            // Con foreach no sirve (???)
            for(int i = 0; i < seleccionadas.size(); i++) {
                if( seleccionadas.get(i).getFila() == fila && seleccionadas.get(i).getColumna() == columna ) {
                    seleccionadas.remove(i);
                    repetida = true;
                }
            }
        }

        for(int i = 0; i < entradasCompradas.size(); i++) {
            if( entradasCompradas.get(i).getFila() == fila && entradasCompradas.get(i).getColumna() == columna ) {
                mostrarError( new Exception("La silla " + entradasCompradas.get(i).getFila() + "-" + entradasCompradas.get(i).getColumna() + " ya esta ocupada") );
                repetida = true;
            }
        }

        Entrada entrada = Entrada.builder().fila(fila).columna(columna).precio(10000).build();
        entrada.setCompra(compra);
        seleccionadas.add(entrada);
        entradasCompradas.add(entrada);
        calcularTotalEntradas();
        calcularTotalCompra();
    }



    public void agregarComestible(Confiteria comestible) {
        obtenerUnidadesCompradas(comestible);

        if(unidadesCompradas == 0) {
            CompraConfiteria compraConfiteria = new CompraConfiteria(comestible.getPrecio(), 1, comestible,compra);
            comprasConfiteria.add(compraConfiteria);
            unidadesCompradas++;
        }
        else {
            for(int i = 0; i < comprasConfiteria.size(); i++) {
                if( comprasConfiteria.get(i).getConfiteria().equals(comestible)  ) {
                    unidadesCompradas = comprasConfiteria.get(i).actualizarUnidadesCompradas('+');
                }
            }
        }

        calcularTotalConfiteria();
        calcularTotalCompra();
    }

    public void quitarComestible(Confiteria comestible) {
        obtenerUnidadesCompradas(comestible);

        if(unidadesCompradas != 0) {

            for(int i = 0; i < comprasConfiteria.size(); i++) {
                if( comprasConfiteria.get(i).getConfiteria().equals(comestible)  ) {

                    if( comprasConfiteria.get(i).getUnidades() == 1 ) {
                        comprasConfiteria.remove(i);
                        unidadesCompradas = 0;
                    }
                    else {
                        unidadesCompradas = comprasConfiteria.get(i).actualizarUnidadesCompradas('-');
                    }
                }
            }
        }
        calcularTotalConfiteria();
        calcularTotalCompra();
    }

    private void obtenerUnidadesCompradas(Confiteria comestible) {
        unidadesCompradas = 0;

        for (CompraConfiteria compraConfiteria : comprasConfiteria) {
            if (compraConfiteria.getConfiteria().equals(comestible)) {
                unidadesCompradas = compraConfiteria.getUnidades();
            }
        }
    }

    public void calcularTotalEntradas() {
        compra.setEntradas(seleccionadas);

        valorTotalEntradas = compra.obtenerTotalEntradas();
    }

    public void calcularTotalConfiteria() {
        compra.setCompraConfiterias(comprasConfiteria);

        valorTotalConfiteria = compra.obtenerTotalConfiteria();
    }

    public void calcularTotalCompra() {
        try {
            compra.setEntradas(seleccionadas);
            compra.setCompraConfiterias(comprasConfiteria);
            compra.setFuncion(funcion);

            valorTotal = compra.calcularValorTotal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void seleccionarMetodoPago() {

        compra.setMedioPago(metodoPago);

    }



    public String finalizarCompra(){

        if(seleccionadas.size() == 0) mostrarError( new Exception("Debes comprar al menos una entrada") );
        if(metodoPago == null) mostrarError( new Exception("Debes seleccionar un metodo de pago") );

        compra.setCliente(cliente);
        compra.setFuncion(funcion);
        compra.setEntradas(seleccionadas);
        compra.setCompraConfiterias(comprasConfiteria);
        compra.setMedioPago(metodoPago);
        compra.setNumeroEntradas(entradasCompradas.size());

        try {
            compra = clienteServicio.finalizarCompra(compra);
            return "/cliente/detalle_compra?faces-redirect=true&amp;b=" + compra.getCodigo();
        } catch (Exception e) {
            mostrarError(e);
        }
        return "";
    }



    private void mostrarError(Exception e) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        PrimeFaces.current().dialog().showMessageDynamic(fm);
    }
}