package br.com.senai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.senai.entity.Professor;
import br.com.senai.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepo;
	
	public Professor salvar(Professor professor) {
		return professorRepo.save(professor);
	}
	
	public List<Professor> buscarTodosProfessores(){
		return professorRepo.findAll();
	}
	public Professor buscarProfessorById(Integer id) {
		return professorRepo.findById(id).orElse(null);
	}
	public Boolean deleteProfessor(Integer id) {
		Professor prof = professorRepo.findById(id).orElse(null);
		if (prof != null) {
			professorRepo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}
	public Professor updateProfessor(Integer id, Professor prof) {
		Professor profBD = professorRepo.findById(id).orElse(null);
		if (profBD != null) {
			profBD.setNome(prof.getNome());
			profBD.setSobrenome(prof.getSobrenome());
			profBD.setEmail(prof.getEmail());
			professorRepo.save(profBD);
			return profBD;
		}else{
			return null;
		}
	}
	
	public Page<Professor> buscarProfessorPorPaginacao(PageRequest page){
		
		return professorRepo.findAll(page);
	}
}
