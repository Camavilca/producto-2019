package com.camavilca.dao;

import com.camavilca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario u WHERE u.correo = ?1 AND u.password = ?2", nativeQuery = true)
    Usuario findUser(String usuario, String password);

}
