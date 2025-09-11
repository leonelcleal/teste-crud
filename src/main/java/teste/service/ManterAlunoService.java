package teste.service;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import teste.model.Aluno;
import teste.model.dao.AlunoDao;

public class ManterAlunoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject	
	private AlunoDao alunoDao;
	
	public void salvar(Aluno aluno) {
		alunoDao.salvar(aluno);
	}
	
	public void excluir(Aluno aluno) {
		this.alunoDao.excluir(aluno);
	}

	public List<Aluno> buscarTodos() {
		
		return alunoDao.buscarTodos();
	}
}
