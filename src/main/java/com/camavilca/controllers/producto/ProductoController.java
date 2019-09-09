package com.camavilca.controllers.producto;

import com.camavilca.model.Producto;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.albatross.zelpers.miscelanea.ExceptionHandler;
import pe.albatross.zelpers.miscelanea.JsonHelper;
import pe.albatross.zelpers.miscelanea.JsonResponse;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Controller
@RequestMapping("producto")
public class ProductoController {

    @Autowired
    ProductoService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        return "producto/producto";
    }

    @ResponseBody
    @RequestMapping("save")
    public JsonResponse save(@RequestBody Producto producto) {
        JsonResponse response = new JsonResponse();
        try {
            service.save(producto);
            if (producto.getId() == null) {
                response.setMessage("Producto Guardado");
            }else{
                response.setMessage("Producto Actualizado");
            }
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("delete")
    public JsonResponse delete(@RequestBody Producto producto) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(producto);
            response.setMessage("Producto Eliminado");
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("all")
    public JsonResponse all() {
        JsonResponse response = new JsonResponse();
        JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = new ArrayNode(jsonFactory);
        try {
            List<Producto> productos = service.all();
            for (Producto producto : productos) {
                ObjectNode node = JsonHelper.createJson(producto, jsonFactory, new String[]{"*"});
                arrayNode.add(node);
            }
            response.setData(arrayNode);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("search")
    public JsonResponse search(@RequestParam("nombre") String nombre) {
        JsonResponse response = new JsonResponse();
        JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
        try {
            Producto producto = service.findProducto(nombre);
            ObjectNode node = JsonHelper.createJson(producto, jsonFactory, new String[]{"*"});
            response.setData(node);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

}
