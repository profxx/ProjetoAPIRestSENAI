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

import br.com.senai.commons.ExampleValues;
import br.com.senai.dto.EstudanteDTO;
import br.com.senai.entity.Estudante;
import br.com.senai.service.EstudanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;


@RestController
@RequestMapping("estudantes")
public class EstudanteResource {

	@Autowired
	private EstudanteService estudanteService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<EstudanteDTO>> buscarTodosEstudantes() {
		
		List<Estudante> listaEstudantes = estudanteService.buscarTodosEstudantes();
		
		List<EstudanteDTO> listaEstudanteDTO = listaEstudantes.stream().map(estudante -> mapper.map(estudante, EstudanteDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaEstudanteDTO);
	}

	@GetMapping("/{id}")
	@Operation(description="Retorna o registro do estudante pelo id")
	public ResponseEntity<EstudanteDTO> buscarEstudanteById(@PathVariable @Schema(example=ExampleValues.idEstudante) Integer id) {
		
		Estudante estudante = estudanteService.buscarEstudanteById(id);
		
		EstudanteDTO estudanteDTO = mapper.map(estudante, EstudanteDTO.class);
		
		return ResponseEntity.ok().body(estudanteDTO);
	}
	
	@PostMapping
	@Operation(description="Cadastra novo estudante")
	public ResponseEntity<EstudanteDTO> cadastrarEstudante(
			@Valid
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content=@Content(examples= {@ExampleObject(name="Exemplo de Estudante", value=ExampleValues.exemploEstudante)}))
			@RequestBody EstudanteDTO estudanteDTO){
		
		Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
		
		estudante = estudanteService.salvar(estudante);
		
		EstudanteDTO novoEstudanteDTO = mapper.map(estudante, EstudanteDTO.class);
		
		return ResponseEntity.ok().body(novoEstudanteDTO);
	}
	
	@PutMapping("/{id}")
	@Operation(description="Atualiza o registro do estudante id pelo body")
	public ResponseEntity<EstudanteDTO> atualizarEstudante(
			@PathVariable @Schema(example=ExampleValues.idEstudante) Integer id, 
			@io.swagger.v3.oas.annotations.parameters.RequestBody(
					content=@Content(
							examples= {
									@ExampleObject(name="Exemplo de Estudante", value=ExampleValues.exemploEstudanteAlterar)
									}))
			@RequestBody EstudanteDTO estudanteDTO){
		
		Estudante estudante = mapper.map(estudanteDTO, Estudante.class);
		
		estudante = estudanteService.updateEstudante(id, estudante);
		
		EstudanteDTO novoEstudanteDTO = mapper.map(estudante, EstudanteDTO.class);
		
		return ResponseEntity.ok().body(novoEstudanteDTO);
		
	}
	
	@DeleteMapping("/{id}")
	@Operation(description="Apaga o registro do estudante pelo id")
	public ResponseEntity<Boolean> deleteEstudante(@PathVariable @Schema(example=ExampleValues.idEstudanteADeletar) Integer id){
		
		Boolean flag = estudanteService.deleteEstudante(id);
		
		return ResponseEntity.ok().body(flag);
		
	}
	
	@GetMapping("paginacao")
	@Operation(description="Consulta estudantes por página e ordenados por nome")
	public Page<Estudante> buscarEstudantePorPaginacao(
			@RequestParam @Schema(example=ExampleValues.pagina) Integer pagina, 
			@RequestParam @Schema(example=ExampleValues.itensPorPagina) Integer itensPorPagina, 
			@RequestParam @Schema(example=ExampleValues.ordenacao) String ordenacao, 
			@RequestParam @Schema(example=ExampleValues.tipoOrdenacao) String tipoOrdenacao) {
	
		PageRequest page = PageRequest.of(pagina, itensPorPagina, (tipoOrdenacao.equals("ASC")? Sort.by(ordenacao).ascending(): Sort.by(ordenacao).descending()));
			
		return estudanteService.buscarEstudantePorPaginacao(page);
	
	}
}
