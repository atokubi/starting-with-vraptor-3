package br.com.wbotelhos.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

/**
 * @author Washington Botelho dos Santos
 * @artigo http://wbotelhos.com.br/2009/12/07/iniciando-com-vraptor-3
 */

@Resource
public class IndexController {

	@Path("/")
	public void index() { }

}