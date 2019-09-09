package com.camavilca.controllers.panamericano;

import com.camavilca.model.Panamericano;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.albatross.zelpers.miscelanea.ExceptionHandler;
import pe.albatross.zelpers.miscelanea.JsonHelper;
import pe.albatross.zelpers.miscelanea.JsonResponse;
import pe.albatross.zelpers.miscelanea.ObjectUtil;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Controller
@RequestMapping("/admin")
public class PanamericanoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String rutaModulo = this.getClass().getAnnotation(RequestMapping.class).value()[0];

    @Autowired
    PanamericanoService service;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute(rutaModulo, rutaModulo);
        return "panamericano/index";
    }

    @RequestMapping("deportes")
    public String deporte(Model model) {
        model.addAttribute(rutaModulo, rutaModulo);
        return "panamericano/deportes";
    }

    @ResponseBody
    @RequestMapping("save")
    public JsonResponse save(@RequestBody Panamericano panamericano) {
        JsonResponse response = new JsonResponse();
        try {
            service.save(panamericano);
            response.setMessage("Deporte Guardado");
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
    public JsonResponse delete(@RequestBody Panamericano panamericano) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(panamericano);
            response.setMessage("Deporte Eliminado");
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
            List<Panamericano> panamericanos = service.all();
            for (Panamericano panamericano : panamericanos) {
                ObjectNode node = JsonHelper.createJson(panamericano, jsonFactory, new String[]{"*"});
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

}
