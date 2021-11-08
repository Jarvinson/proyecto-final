package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {

        this.usuarioRepo = usuarioRepo;
    }

    //Función que permite el registro de usuarios, se crean las posibles excepciones
    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());

        //Condicion con el codigo del usuario
        if ( buscado.isPresent()){
            throw new Exception("El código del usuario ya existe");
        }

        //Condicion con el email del usuario
        buscado = buscarPorEmail(u.getEmail());
        if ( buscado.isPresent()){
            throw new Exception("El email del usuario ya existe");
        }

        //Condicion con el username del usuario
        buscado = usuarioRepo.findByUsername(u.getUsername());
        if ( buscado.isPresent()){
            throw new Exception("El Username del usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    //Funcion que permite la actualización de un usuario por medio de su codigo
    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());

        if (buscado.isEmpty()) {
            throw new Exception("El usuario no existe");
        }
        return usuarioRepo.save(u);
    }

    //Función que busca un usuario por medio de su email
    private Optional<Usuario> buscarPorEmail(String email){

        return usuarioRepo.findByEmail(email);
    }

    //Función que elimina un usuario por medio de su código
    @Override
    public void eliminarUsuario(Integer codigo) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(codigo);

        if ( buscado.isEmpty()){
            throw new Exception("El código del usuario no existe");
        }
        usuarioRepo.deleteById(codigo);
    }

    //Función que lista todos los usuarios
    @Override
    public List<Usuario> listarUsuarios() {

        return usuarioRepo.findAll();
    }

    //Función que lista los productos favoritos de un usuario
    @Override
    public List<Producto> listarFavoritos(String email) throws Exception {
        Optional<Usuario> buscado = buscarPorEmail(email);

        if(buscado.isEmpty()){
            throw new Exception("El email no existe");
        }
        return usuarioRepo.obtenerProductosFavoritos(email);
    }

    //Función que obtiene un usuario por medio de su código
    @Override
    public Usuario obtenerUsuario(Integer codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);

        if ( buscado.isEmpty()){
            throw new Exception("El usuario NO existe");
        }
        return buscado.get();
    }

    //Función que permite el inicio de sesion, teniendo en cuenta el email y el password
    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
      return usuarioRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticación son incorrectos"));

    }
}
