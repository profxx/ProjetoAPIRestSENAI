package br.com.senai.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.dto.ProfessorDTO;
import br.com.senai.entity.Professor;
import br.com.senai.service.ProfessorService;

@RestController
@RequestMapping("professores")
public class ProfessorResource {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> buscarTodosProfessores() {

		List<Professor> listaProfessores = professorService.buscarTodosProfessores();

		List<ProfessorDTO> listaProfessoresDTO = listaProfessores.stream()
				.map(professor -> mapper.map(professor, ProfessorDTO.class)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaProfessoresDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProfessorDTO> buscarProfessorById(@PathVariable("id") Integer id) {

		Professor professor = professorService.buscarProfessorById(id);

		ProfessorDTO professorDTO = mapper.map(professor, ProfessorDTO.class);

		return ResponseEntity.ok().body(professorDTO);
	}

	@PostMapping
	public ResponseEntity<ProfessorDTO> cadastrarProfessor(@RequestBody ProfessorDTO professorDTO) {

		Professor professor = mapper.map(professorDTO, Professor.class);

		professor = professorService.salvar(professor);

		ProfessorDTO novoProfessorDTO = mapper.map(professor, ProfessorDTO.class);

		return ResponseEntity.ok().body(novoProfessorDTO);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProfessorDTO> atualizarProfessor(@PathVariable Integer id,
			@RequestBody ProfessorDTO professorDTO) {

		Professor professor = mapper.map(professorDTO, Professor.class);

		professor = professorService.updateProfessor(id, professor);

		ProfessorDTO novoProfessorDTO = mapper.map(professor, ProfessorDTO.class);

		return ResponseEntity.ok().body(novoProfessorDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProfessor(@PathVariable Integer id){
		
		Boolean flag = professorService.deleteProfessor(id);
		
		return ResponseEntity.ok().body(flag);
	}

	@GetMapping("paginacao")
	public Page<Professor> buscarProfessorPorPaginacao(@RequestParam Integer pagina, @RequestParam Integer itensPorPagina, @RequestParam String ordenacao, @RequestParam String tipoOrdenacao){
		
		PageRequest page = PageRequest.of(pagina, itensPorPagina, (tipoOrdenacao.equals("ASC")? Sort.by(ordenacao).ascending(): Sort.by(ordenacao).descending()));
		
		return professorService.buscarProfessorPorPaginacao(page);
	}
	
	
}
