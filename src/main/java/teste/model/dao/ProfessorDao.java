package teste.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import teste.model.Professor;
import teste.util.jpa.Transactional;

public class ProfessorDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorDao.class);
	
	@Transactional
	public Professor salvar(Professor Professor) throws PersistenceException {
		
		LOGGER.info("salvar DAO... Professor = " + Professor);
		
		try {
			return manager.merge(Professor);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Transactional
	public void excluir(Professor Professor) throws PersistenceException {

		try {
			Professor a = manager.find(Professor.class, Professor.getId());
			manager.remove(a);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	public Professor buscarPeloCodigo(Long id) {
		return manager.find(Professor.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Professor> buscarTodos() {
		
		String query="select a from Professor a";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
	}	
}
