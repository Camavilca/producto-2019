package com.camavilca.controllers.producto;

import com.camavilca.dao.CategoriaDAO;
import com.camavilca.dao.ProductoDAO;
import com.camavilca.model.Categoria;
import com.camavilca.model.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class ProductoServiceImp implements ProductoService {

    @Autowired
   ProductoDAO productoDAO;


    @Autowired
    CategoriaDAO categoriaDAO;

    @Override
    @Transactional
    public void save(Producto producto) {
        if (producto.getNombre() == null && producto.getPrecio() == null) {
            throw new PhobosException("Debe completar los campos");
        }
        productoDAO.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDAO.delete(producto);
    }

    @Override
    public List<Producto> all() {
        return productoDAO.findAll();
    }

//    @Override
//    public Producto findProducto(String nombre) {
//        if (nombre == null) {
//            throw new PhobosException("Debe ingresar un nombre o codigo");
//        }
//        Producto productoDB = productoDAO.find(nombre);
//        if (productoDB == null) {
//            throw new PhobosException("No existen producto con esa Descripcion");
//        }
//        return productoDB;
//    }
    @Override
    public Producto findProducto(String nombre) {
        if (nombre == null) {
            throw new PhobosException("Debe ingresar un nombre o codigo");
        }
        Producto productoDB = productoDAO.findProducto(nombre);
        if (productoDB == null) {
            throw new PhobosException("No existen producto con esa Descripcion");
        }
        return productoDB;
    }

    @Override
    @Transactional
    public void saveCategoria(Categoria categoria) {
        System.out.println(categoria.getNombre().isEmpty());
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            throw new PhobosException("Debe ingresar el nombre de la categoria");
        }
        categoriaDAO.save(categoria);
    }

    @Override
    public List<Categoria> allCategoria() {
        return categoriaDAO.findAll();
    }

    @Override
    @Transactional
    public void deleteCategoria(Categoria categoria) {
        categoriaDAO.delete(categoria);
    }

}
