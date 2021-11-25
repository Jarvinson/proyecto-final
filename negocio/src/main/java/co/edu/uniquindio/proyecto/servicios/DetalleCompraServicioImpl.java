package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraServicioImpl implements DetalleCompraServicio{

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Override
    public DetalleCompra registrarDetalleCompra(DetalleCompra dc) throws Exception {
        Optional<DetalleCompra> buscado = detalleCompraRepo.findById(dc.getCodigo());

        if ( buscado.isPresent()){
            throw new Exception("El código del detalle compra ya existe");
        }
        return detalleCompraRepo.save(dc);
    }

    @Override
    public DetalleCompra actualizarDetalleCompra(DetalleCompra dc) throws Exception {
        Optional<DetalleCompra> buscado = detalleCompraRepo.findById(dc.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("El código del detalle compra no existe");
        }
        return detalleCompraRepo.save(dc);
    }

    @Override
    public void eliminarDetalleCompra(Integer id) throws Exception {
        Optional<DetalleCompra> buscado = detalleCompraRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("El código del detalle compra no existe");
        }
         detalleCompraRepo.deleteById(id);
    }

    @Override
    public DetalleCompra obtenerDetalleCompra(Integer id) throws Exception {
        return detalleCompraRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ningun detalle compra"));
    }

    @Override
    public List<DetalleCompra> listarDetalleCompra() {
        return detalleCompraRepo.findAll();
    }
}
