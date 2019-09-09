package com.camavilca.controllers.panamericano;

import com.camavilca.model.Panamericano;
import java.util.List;

public interface PanamericanoService {

    void save(Panamericano panamericano);

    List<Panamericano> all();

    void delete(Panamericano panamericano);

}
