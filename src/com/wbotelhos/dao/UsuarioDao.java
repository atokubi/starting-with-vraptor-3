package com.wbotelhos.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.wbotelhos.model.Usuario;

/**
 * @author Washington Botelho dos Santos
 * @artigo http://wbotelhos.com/2009/12/07/iniciando-com-vraptor-3
 */

@SessionScoped
@Component
public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = -1917047398280846082L;

	private List<Usuario> usuarioList = new ArrayList<Usuario>();
	private Integer id = 1;

	public void salvar(Usuario usuario) {
		usuarioList.add(usuario);
		usuario.setId(id++);
	}

	public List<Usuario> loadAll() {
		return usuarioList;
	}

	public Usuario loadById(Usuario usuario) {
		Usuario usuarioDelete = null;

		for (Usuario item : usuarioList) {
			if (item.getId() == usuario.getId()) {
				usuarioDelete = item;
				break;
			}
		}

		/*
		 * Se o usuário desistir de atualizar depois dos dados retornado na tela obviamente
		 * o usuário já terá sido removido da lista, mas o exemplo é apenas didático.
		 */
		removerItem(usuarioDelete);
		
		return usuarioDelete;
	}

	public void remover(Usuario usuario) {
		Usuario usuarioDelete = null;

		for (Usuario item : usuarioList) {
			if (item.getId() == usuario.getId()) {
				usuarioDelete = item;
				break;
			}
		}
		
		removerItem(usuarioDelete);
	}

	private void removerItem(Usuario usuarioDelete) {
		if (usuarioList.remove(usuarioDelete)) {
			id--;
		}
	}

}