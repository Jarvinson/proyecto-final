package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;

import java.util.List;

public interface CategoriaServicio {

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer id) throws Exception;

    Categoria registrarCategoria(Categoria c) throws Exception;

    Categoria actualizarCategoria(Categoria c) throws Exception;

    void eliminarCategoria(Integer id) throws Exception;

    List<Categoria> filtrarNombreCategoria(String categoria) throws Exception;


}
