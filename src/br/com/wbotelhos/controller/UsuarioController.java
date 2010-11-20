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
public class UsuarioController {

	private Result result;
	private UsuarioDao usuarioDao;
	
	public UsuarioController(Result result, UsuarioDao usuarioDao) {
		this.result = result;
		this.usuarioDao = usuarioDao;
	}

	@Get
	@Path("/usuario/novo")
	public void novo(Usuario usuario) {
		result.include("usuario", usuario);
	}

	@Post
	@Path("/usuario")
	public void salvar(Usuario usuario) {
		usuarioDao.salvar(usuario);

		result
			.include("usuarioList", usuarioDao.loadAll())
			.forwardTo("WEB-INF/jsp/usuario/listagem.jsp");

		// Poderia ser usado: result.redirectTo(this).listagem();
	}

	@Get
	@Path("/usuario")
	public List<Usuario> listagem() {
		return usuarioDao.loadAll();
	}
	
	@Put
	@Path("/usuario")
	public void editar(Usuario usuario) {
		Usuario entity = usuarioDao.loadById(usuario);
		result.redirectTo(this).novo(entity);
	}
	
	@Delete
	@Path("/usuario")
	public void remover(Usuario usuario) {
		usuarioDao.remover(usuario);
		result.redirectTo(this).listagem();
	}

}