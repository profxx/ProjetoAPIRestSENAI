package br.com.senai.inicializacao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senai.entity.Estudante;
import br.com.senai.entity.Professor;
import br.com.senai.repository.EstudanteRepository;
import br.com.senai.repository.ProfessorRepository;
import br.com.senai.service.EstudanteService;
import br.com.senai.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private EstudanteService estudanteService;
	
	@Autowired
	private EstudanteRepository repo;
	
	@Autowired
	private ProfessorRepository repoProf;
	
	@Autowired
	private ProfessorService professorService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Estudante estudante1 = new Estudante();
		estudante1.setNome("Alexandre");
		estudante1.setSobrenome("Pimentel");
		estudante1.setEmail("acpimentel@gmail.com");

		Estudante estudante2 = new Estudante();
		estudante2.setNome("Maria");
		estudante2.setSobrenome("Silva");
		estudante2.setEmail("mariasilva@gmail.com");

		Estudante estudante3 = new Estudante();
		estudante3.setNome("João");
		estudante3.setSobrenome("Souza");
		estudante3.setEmail("joaosouza@gmail.com");

		Estudante estudante4 = new Estudante();
		estudante4.setNome("Ana");
		estudante4.setSobrenome("Santos");
		estudante4.setEmail("anasantos@gmail.com");

		Estudante estudante5 = new Estudante();
		estudante5.setNome("Pedro");
		estudante5.setSobrenome("Ferreira");
		estudante5.setEmail("pedroferreira@gmail.com");

		Estudante estudante6 = new Estudante();
		estudante6.setNome("Mariana");
		estudante6.setSobrenome("Oliveira");
		estudante6.setEmail("marianaoliveira@gmail.com");

		Estudante estudante7 = new Estudante();
		estudante7.setNome("Lucas");
		estudante7.setSobrenome("Ribeiro");
		estudante7.setEmail("lucasribeiro@gmail.com");

		Estudante estudante8 = new Estudante();
		estudante8.setNome("Carolina");
		estudante8.setSobrenome("Almeida");
		estudante8.setEmail("carolinaalmeida@gmail.com");
		
		repo.saveAll(Arrays.asList(estudante1, estudante2, estudante3, estudante4, estudante5, estudante6, estudante7, estudante8));
		
		//buscar todos os estudantes
		//List<Estudante> listaEstudante = estudanteService.buscarTodosEstudantes();
		//listaEstudante.forEach(estudante -> System.out.println(estudante.toString()));
		
		//buscar um estudante pelo id
		//System.out.println(estudanteService.buscarEstudanteById(5).toString());
		
		//deletar estudante pelo id
		//Boolean flag = estudanteService.deleteEstudante(2);
		//System.out.println(flag);
		
		//alterar estudante pelo id
		//Estudante estudante9 = new Estudante();
		//estudante9.setNome("Kadu");
		//estudante9.setSobrenome("Fonseca");
		//estudante9.setEmail("kadutheway@gmail.com");
		//estudanteService.updateEstudante(1, estudante9);
		//estudanteService.buscarTodosEstudantes().forEach(estudante -> System.out.println(estudante.toString()));
		
		Professor prof1 = new Professor();
		prof1.setNome("Marcelo");
		prof1.setSobrenome("Estruc");
		prof1.setEmail("marc@gmail.com");
		
		Professor prof2 = new Professor();
		prof2.setNome("Luiz");
		prof2.setSobrenome("Neves");
		prof2.setEmail("lfneves@firjan.com.br");
		
		Professor prof3 = new Professor();
		prof3.setNome("Carlos Eduardo");
		prof3.setSobrenome("da Silva");
		prof3.setEmail("caredsilva@firjan.com.br");
		
		repoProf.saveAll(Arrays.asList(prof1, prof2, prof3));
		
		// Buscar todos os professores
		//professorService.buscarTodosProfessores().forEach(prof -> System.out.println(prof.toString()));
		
		// Buscar professor pelo id
		//System.out.println(professorService.buscarProfessorById(1));
		
		// Deletar professor pelo id
		//Boolean flag = professorService.deleteProfessor(1);
		//System.out.println("Professor deletado: " + flag);
		
		// Alterar professor pelo id
		Professor prof4 = new Professor();
		prof4.setNome("Ronald");
		prof4.setSobrenome("Anchite");
		prof4.setEmail("ranchite@firjan.com.br");
		
		professorService.updateProfessor(2, prof4);
		professorService.buscarTodosProfessores().forEach(prof -> System.out.println(prof.toString()));
	}
}
