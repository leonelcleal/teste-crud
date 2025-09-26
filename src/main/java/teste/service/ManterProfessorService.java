package teste.service;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import teste.model.Professor;
import teste.model.dao.ProfessorDao;

public class ManterProfessorService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject	
	private ProfessorDao ProfessorDao;
	
	public void salvar(Professor Professor) {
		ProfessorDao.salvar(Professor);
	}
	
	public void excluir(Professor Professor) {
		this.ProfessorDao.excluir(Professor);
	}

	public List<Professor> buscarTodos() {
		
		return ProfessorDao.buscarTodos();
	}
}
