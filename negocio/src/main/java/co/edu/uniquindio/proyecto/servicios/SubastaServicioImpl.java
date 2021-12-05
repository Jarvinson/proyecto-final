package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SubastaServicioImpl implements SubastaServicio{

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    @Override
    public Subasta registrarSubasta(Subasta s) throws Exception {
        Optional<Subasta> buscado = subastaRepo.findById(s.getCodigo());

        if ( buscado.isPresent()){
            throw new Exception("El código de la subasta ya existe");
        }
        return subastaRepo.save(s);
    }

    @Override
    public Subasta actualizarSubasta(Subasta s) throws Exception {
        Optional<Subasta> buscado = subastaRepo.findById(s.getCodigo());

        if ( buscado.isEmpty()){
            throw new Exception("El código de la subasta no existe");
        }
        return subastaRepo.save(s);
    }

    @Override
    public Subasta obtenerSubasta(Integer id) throws Exception {
        return subastaRepo.findById(id).orElseThrow(() -> new Exception("El id no corresponde a ninguna subasta"));
    }

    @Override
    public void eliminarSubasta(Integer id) throws Exception {
        Optional<Subasta> buscado = subastaRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("El código de la subasta no existe");
        }
        subastaRepo.deleteById(id);
    }

    @Override
    public List<Subasta> listarSubastas() {
        return subastaRepo.findAll();
    }

    @Override
    public Float obtenerValorMasAlto(Integer codigo) {
        return subastaRepo.obtenerValorMasAlto(codigo);
    }

    @Override
    public List<Subasta> listarSubastasDisponibles() {
        return subastaRepo.listarSubastasDisponibles();
    }

    @Override
    public Subasta crearSubasta(LocalDate fechaLimite, Producto producto, Usuario usuario, double valor, LocalDate fechaSubasta) throws Exception {
       try {
           Subasta subasta = new Subasta();
           subasta.setProducto(producto);
           subasta.setFechaLimite(fechaLimite);

           Subasta subastaGuardada = subastaRepo.save(subasta);

           SubastaUsuario su;

                su = new SubastaUsuario();
                su.setUsuario(usuario);
                su.setValor(valor);
                su.setFechaSubasta(fechaSubasta);
                su.setSubasta(subasta);

               subastaUsuarioRepo.save(su);

            return subastaGuardada;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
