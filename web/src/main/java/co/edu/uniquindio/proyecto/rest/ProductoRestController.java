package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;

    //Lista todos los productos publicados
    @GetMapping
    public List<Producto> listar(){
        return productoServicio.listarTodosProductos();
    }

    //Obtiene un producto por medio de su id
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") Integer idProducto){
        try {
            Producto producto= productoServicio.obtenerProducto(idProducto);
            return ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Crea un producto
    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Producto producto) {
        try {
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("El producto se creó correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Actualiza un producto
    @PutMapping
    public ResponseEntity<Mensaje> actualizar(@RequestBody Producto producto){
        try {
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("El producto se actualizó correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Elimina un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") Integer id){
        try {
            productoServicio.eliminarProducto(id);
            return ResponseEntity.status(200).body(new Mensaje("El producto se eliminó correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Lista los productos correspondientes a una categoria
    @GetMapping("/categoria/{nombre}")
    public ResponseEntity<?> obtenerCategoria(@PathVariable("nombre") String nombre){
        try {
            List<Producto> productos= productoServicio.buscarProductoCategoria(nombre);
            return ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Lista los productos que están en un rango de precio determinado
    @GetMapping("/valor1/{precio1}/valor2/{precio2}")
    public ResponseEntity<?> obtenerPrecio(@PathVariable("precio1") double precio1, @PathVariable("precio2") double precio2){
        try {
            List<Producto> productos= productoServicio.buscarProductosPrecio(precio1, precio2);
            return ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Lista los productos correspondientes a una ciudad
    @GetMapping("/ciudad/{nombre}")
    public ResponseEntity<?> obtenerCiudad(@PathVariable("nombre") String nombre){
        try {
            List<Producto> productos= productoServicio.productosPorCiudad(nombre);
            return ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Lista los productos que tienen un descuento determinado
    @GetMapping("/descuento/{descuento}")
    public ResponseEntity<?> obtenerDescuento(@PathVariable("descuento") double descuento){
        try {
            List<Producto> productos= productoServicio.productosPorDescuento(descuento);
            return ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Lista los productos que están en un rango de precio determinado
    @GetMapping("/favoritos/{idUsuario}")
    public ResponseEntity<?> obtenerFavoritos(@PathVariable("idUsuario") Integer idUsuario){
        try {
            List<Producto> productos= productoServicio.listarProductosFavoritos(idUsuario);
            return ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }





}
