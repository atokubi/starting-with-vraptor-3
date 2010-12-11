package br.com.wbotelhos.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.dao.UsuarioDao;
import br.com.wbotelhos.model.Usuario;

/**
 * @author Washington Botelho dos Santos
 * @artigo http://wbotelhos.com.br/2009/12/07/iniciando-com-vraptor-3
 */

@Resource
public class UsersController {

	private Result result;
	private UsuarioDao usuarioDao;
	
	public UsersController(Result result, UsuarioDao usuarioDao) {
		this.result = result;
		this.usuarioDao = usuarioDao;
	}

	@Get
	@Path("/users/new")
	public void novo(Usuario usuario) {
		result.include("usuario", usuario);
	}

	@Post
	@Path("/users")
	public void salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);

		result
			.include("usuarioList", usuarioDao.loadAll())
			.forwardTo("WEB-INF/jsp/users/listagem.jsp");
	}

	@Get
	@Path("/users")
	public List<Usuario> listagem() {
		return usuarioDao.loadAll();
	}
	
	@Put
	@Path("/users")
	public void editar(Usuario usuario) {
		Usuario entity = usuarioDao.loadById(usuario);
		result.redirectTo(this).novo(entity);
	}
	
	@Delete
	@Path("/users")
	public void remover(Usuario usuario) {
		usuarioDao.remover(usuario);
		result.redirectTo(this).listagem();
	}

}