package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServicioImpl implements CompraServicio{
    @Autowired
    private CompraRepo compraRepo;

    @Override
    public Compra registrarCompra(Compra c) throws Exception {
        Optional<Compra> buscado = compraRepo.findById(c.getCodigo());

        if ( buscado.isPresent()){
            throw new Exception("El código de la compra ya existe");
        }
        return compraRepo.save(c);
    }

    @Override
    public Compra actualizarCompra(Compra c) throws Exception {
        Optional<Compra> buscado = compraRepo.findById(c.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("El código de la compra no existe");
        }
        return compraRepo.save(c);
    }

    @Override
    public void eliminarCompra(Integer id) throws Exception {
        Optional<Compra> buscado = compraRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("El código de la compra no existe");
        }
        compraRepo.deleteById(id);
    }

    @Override
    public Compra obtenerCompra(Integer id) throws Exception {
        return compraRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna compra"));
    }

    @Override
    public List<Compra> listaCompras() {
        return compraRepo.findAll();
    }

    @Override
    public List<Compra> findAllByMedioPago(String nombre) {
        return compraRepo.findAllByMedioPago(nombre);
    }

    @Override
    public List<DetalleCompra> listarComprasUsuario(Integer codigo){
        return compraRepo.listarComprasUsuario(codigo);
    }

    @Override
    public  Long obtenerTotalProductosCompradosUsuario(Integer codigo) {
        return compraRepo.obtenerTotalProductosComprados(codigo);
    }

    @Override
    public Long calcularTotalVentas(Integer codigo) {
        return compraRepo.calcularTotalVentas(codigo);
    }

    @Override
    public Long calcularTotalCompras(Integer codigo) {
        return compraRepo.calcularTotalCompras(codigo);
    }


}
