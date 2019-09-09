package com.camavilca.dao;

import com.camavilca.model.Panamericano;
import com.camavilca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PanamericanoDAO extends JpaRepository<Panamericano, Long> {

    

}
