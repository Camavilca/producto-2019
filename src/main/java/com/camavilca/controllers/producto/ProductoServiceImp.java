package com.camavilca.controllers.producto;

import com.camavilca.dao.ProductoDAO;
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

}
