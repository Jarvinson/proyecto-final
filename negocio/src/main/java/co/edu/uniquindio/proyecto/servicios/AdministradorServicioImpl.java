package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    @Autowired
    private AdministradorRepo administradorRepo;

    @Override
    public Administrador registrarAdministrador(Administrador a) throws Exception {
        Optional<Administrador> buscado = administradorRepo.findById(a.getCodigo());

        //Condicion con el codigo del usuario
        if ( buscado.isPresent()){
            throw new Exception("El código del administrador ya existe");
        }

        //Condicion con el email del usuario
        buscado = administradorRepo.findByEmail(a.getEmail());
        if ( buscado.isPresent()){
            throw new Exception("El email del administrador ya existe");
        }

        return administradorRepo.save(a);
    }

    @Override
    public Administrador actualizarAdministrador(Administrador a) throws Exception {
        Optional<Administrador> buscado = administradorRepo.findById(a.getCodigo());

        if (buscado.isEmpty()) {
            throw new Exception("El administrador no existe");
        }
        return administradorRepo.save(a);
    }

    @Override
    public void eliminarAdministrador(Integer id) throws Exception {
        Optional<Administrador> buscado = administradorRepo.findById(id);

        if ( buscado.isEmpty()){
            throw new Exception("El código del administrador no existe");
        }
        administradorRepo.deleteById(id);
    }

    @Override
    public List<Administrador> listarAdmnistradores() {
        return administradorRepo.findAll();
    }

    @Override
    public Administrador obtenerAdministrador(Integer id) throws Exception {
        Optional<Administrador> administrador = administradorRepo.findById(id);

        if(administrador.isEmpty()){
            throw  new Exception("El administrador no existe");
        }
        return administrador.get();
    }

    @Override
    public Optional<Administrador> filtrarAdministradorEmail(String email) throws Exception {
        Optional administrador = administradorRepo.findByEmail(email);

        if(administrador.isEmpty()){
            throw new Exception("El administrador no existe");
        }
        return administradorRepo.findByEmail(email);
    }
}
