package teste.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import teste.model.Professor;
import teste.service.ManterProfessorService;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class ManterProfessorBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ManterProfessorService manterProfessorService;
	private Professor Professor = new Professor();
	private List<Professor> Professors = new ArrayList<Professor>();

	
	@PostConstruct
	public void inicializar() {
		log.debug("init pesquisa"); 
		this.setProfessors(manterProfessorService.buscarTodos());
		limpar();
	}
	
	public void salvar() {
		log.info(Professor.toString());
		manterProfessorService.salvar(Professor);
		
		FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
        		"O Professor foi gravado com sucesso!", 
        		Professor.toString()));
		
		log.info("Professor: " + Professor.toString());
	}
	
	public void excluir() {
		try {
			manterProfessorService.excluir(Professor);
			this.Professors = manterProfessorService.buscarTodos();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Professor " + Professor.getNome() + " excluído com sucesso.", null));
			log.info("Professor excluido = " + Professor.getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um problema", null));
		}
	}
		
	public void limpar() {

		this.Professor = new Professor();
	}
	
}
