package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubastaServicioImpl implements SubastaServicio{

    @Autowired
    private SubastaRepo subastaRepo;

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
}
