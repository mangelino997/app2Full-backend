package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.IUsuarioDAO;
import ar.com.wecode.jitws.model.Usuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author blas
 */

@Service
public class UsuarioService {

    @Autowired
    IUsuarioDAO usuarioDAO;

    public Usuario save(Usuario usuario) {
        return usuarioDAO.saveAndFlush(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    public Usuario find(String username) {
        return usuarioDAO.findOneByUsername(username);
    }

    public Optional<Usuario> find(int id) {
        return usuarioDAO.findById(id);
    }

}
