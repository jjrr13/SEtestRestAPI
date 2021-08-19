package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.entities.Entrega;
import com.test.pruebaGestioLogistica.repository.IEntregaRepository;
import com.test.pruebaGestioLogistica.services.IServiceEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("serviceEntrega")
public class EntregaServiceImpl implements IServiceEntrega {

    @Autowired
    private IEntregaRepository iEntregaRepository;

    @Override
    public List<Entrega> listAll() {
        return (List<Entrega>) iEntregaRepository.findAll();
    }

    @Override
    public Entrega save(Entrega entrega) {
        return iEntregaRepository.save(entrega);
    }

    @Override
    public List<Entrega> obtenerEntregasConNombres() {
        return (List<Entrega>) iEntregaRepository.obtenerEntregasConNombres();
    }

    @Override
    public List<Entrega> listByClient(Long id) {
        return (List<Entrega>) iEntregaRepository.listByClient(id);
    }

    @Override
    public Long insertar(Entrega entrega) {
        return iEntregaRepository.insertar(
                entrega.getCantidad_producto(),
                entrega.getDescuento(),
                entrega.getFecha_entraga(),
                entrega.getFecha_registro(),
                entrega.getIdentificacion_transporte(),
                entrega.getNumero_guia(),
                entrega.getPrecio(),
                entrega.getFk_id_almacen_cliente(),
                entrega.getFk_id_cliente(),
                entrega.getFk_id_producto(),
                entrega.getTipoLogistica().ordinal()
        );
    }

    @Override
    public Integer update(Entrega entrega) {
        return iEntregaRepository.update(
                entrega.getId(),
                entrega.getCantidad_producto(),
                entrega.getDescuento(),
                entrega.getFecha_entraga(),
                entrega.getFecha_registro(),
                entrega.getIdentificacion_transporte(),
                entrega.getNumero_guia(),
                entrega.getPrecio(),
                entrega.getFk_id_almacen_cliente(),
                entrega.getFk_id_cliente(),
                entrega.getFk_id_producto(),
                entrega.getTipoLogistica().ordinal()
        );
    }

    @Override
    public Entrega findById2(Long id) {
        return iEntregaRepository.findById2(id);
    }


    @Override
    public Entrega findById(Long id) {
        return iEntregaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iEntregaRepository.deleteById(id);
    }

    @Override
    public boolean validateIdentificacion(String tipoEntrega, String identificacion) {
        System.out.println(tipoEntrega);

        if ("MARITIMA".equalsIgnoreCase(tipoEntrega)){
            return validateFlota(identificacion);
        }
        else if("TERRESTRE".equalsIgnoreCase(tipoEntrega) ){
            return validatePlaca(identificacion);
        }

        return false;
    }

    @Override
    public Double generateDiscount(String tipoEntrega, Integer cantidad_productos) {

        if (cantidad_productos > 10 ){
            if ("MARITIMA".equalsIgnoreCase(tipoEntrega)){
                return 0.03;
            }
            else if("TERRESTRE".equalsIgnoreCase(tipoEntrega) ){
                return 0.05;
            }
        }

        return 0.0;

    }

    public boolean validatePlaca(String placa) {
        Pattern pat = Pattern.compile("^[A-Za-z]{3}([0-9]{3}$)");
        Matcher mat = pat.matcher(placa);
        System.out.println(mat.matches());
        return mat.matches();
    }

    public boolean validateFlota(String flota) {
        Pattern pat = Pattern.compile("^[A-Za-z]{4}([0-9]{3}$)");
        Matcher mat = pat.matcher(flota);
        System.out.println(mat.matches());
        return mat.matches();
    }
}
