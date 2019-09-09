package com.camavilca.controllers.panamericano;

import com.camavilca.model.Panamericano;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camavilca.dao.PanamericanoDAO;
import java.util.List;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class PanamericanoServiceImp implements PanamericanoService {

    @Autowired
    PanamericanoDAO repository;

    @Override
    @Transactional
    public void save(Panamericano panamericano) {
        if (panamericano.getNombre() == null
                || panamericano.getDescripcion() == null
                || panamericano.getHistoria() == null) {
            throw new PhobosException("Debe completar los campos");
        }
        if (panamericano.getId() == null) {
            repository.save(panamericano);
        } else {
            repository.saveAndFlush(panamericano);
        }
    }

    @Override
    public List<Panamericano> all() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(Panamericano panamericano) {
        repository.delete(panamericano);
    }

}
