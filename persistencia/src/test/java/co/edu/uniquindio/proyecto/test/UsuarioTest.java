package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//Esta clase contiene los métodos para realizar las pruebas unitarias al objeto usuario
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired //Etiqueta que permite instanciar automaticamente el objeto creado
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    //Función que permite realizar las pruebas unitarias para la de creación de usuarios
    @Test
    @Sql("classpath:data.sql")
    public void registrarTest (){
        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("Telefono 1", "3007896578");
        telefonos.put("Telefono 2", "3115679809");

        Usuario usuario = new Usuario(1, "Jarvinson Valencia", "jarvinsonvalencia@gmail.com", "jarvinson123", "jvalencia", ciudad, telefonos);
        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

    }

    //Función que permite realizar las pruebas unitarias para la modificacion de usuarios
    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        //Obtenemos el objeto que vamos a modificar mendiante su ID
        Usuario usuarioGuardado = usuarioRepo.findById(1).orElse(null);

        //Seteamos el nuevo valor para el campo que se va a modificar
        usuarioGuardado.setEmail("jvalencia@gmail.com");

        //Guardamos el objeto con el cambio aplicado
        usuarioRepo.save(usuarioGuardado);

        Usuario usuarioBuscado = usuarioRepo.findById(1).orElse(null);
        Assertions.assertEquals("jvalencia@gmail.com", usuarioBuscado.getEmail());
    }

    //Función que permite realizar las pruebas unitarias para el listado de usuarios
    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Usuario> usuarios =usuarioRepo.findAll();
        usuarios.forEach( usuario -> System.out.println(usuario));
    }

    //Función que permite realizar las pruebas unitarias para el borrado de usuarios
    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById(1);
        Usuario usuarioBuscado = usuarioRepo.findById(1).orElse(null);
        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){

        List<Usuario> lista = usuarioRepo.findAllByNombreContains("Daniela");
        lista.forEach(u-> System.out.println(u));
    }

    //Función que permite realizar las pruebas unitarias para el filtrado de email de un usuario
    @Test
    @Sql("classpath:data.sql")
    public void filtrarEmailTest(){

        Optional<Usuario> usuario = usuarioRepo.findByEmail("danielavilsdsdsflegas@gmail.com");

        if (usuario.isPresent()){
            System.out.println(usuario.get());
        }else{
            System.out.println("NO existe este correo");
        }
    }

    //Función que permite realizar las pruebas unitarias para limitar el muestreo de usuarios en una busqueda
    @Test
    @Sql("classpath:data.sql")

    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,2);
        Page<Usuario> lista = usuarioRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));
        //System.out.println(lista);
    }

    //Función que permite realizar las pruebas unitarias para ordenar un listado de usuarios
    @Test
    @Sql("classpath:data.sql")
    public void ordenarListaTest(){
        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre"));

       // System.out.println(lista.stream().collect(Collectors.toList()));
        System.out.println(lista);
    }

}
