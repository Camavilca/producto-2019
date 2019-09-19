package com.camavilca.controllers.producto;

import com.camavilca.model.Categoria;
import com.camavilca.model.Producto;
import java.util.List;

public interface ProductoService {

    void save(Producto producto);

    void delete(Producto producto);

    List<Producto> all();

    Producto findProducto(String nombre);

    void saveCategoria(Categoria categoria);

    List<Categoria> allCategoria();

    void deleteCategoria(Categoria categoria);

}
