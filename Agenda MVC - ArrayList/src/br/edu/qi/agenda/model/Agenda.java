package br.edu.qi.agenda.model;

import java.util.ArrayList;
import java.util.List;

import br.qi.edu.agenda.view.Tela;

public class Agenda {

	// Métodos
	public void iniciarAgenda() {
		// Cria o objeto da classe Tela
		Tela tela = new Tela();

		// Monta o Menu
		String menu = ":: Agenda de Contatos ::\n\n" + "1. Cadastrar\n" + "2. Buscar\n" + "3. Editar\n"
				+ "4. Excluir\n\n";

		// Mantém o Menu ativo
		boolean isAtivo = true;

		// Loop Principal do Sistema
		while (isAtivo) {
			String opcao = tela.informar(menu, "Informe uma das opções", -1);

			switch (opcao) {
			case "1":
				cadastrarContato(tela);
				break;

			case "2":
				buscarContato(tela);
				break;

			case "3":
				editarContato(tela);
				break;

			case "4":
				excluirContato(tela);
				break;

			default:
				int sair = tela.confirmar("Deseja sair?", "Encerrar Sistema", 3);

				// Sim == 0 | Não == 1
				if (sair == 0) {
					isAtivo = false;
					tela.mostrar("Encerrando o Sistema...", "Encerrar Sistema", 1);
				}
			} // fecha o switch
		} // fecha o while
	} // fecha o método iniciarAgenda()

	private void cadastrarContato(Tela tela) {

		// Usuário informa os dados de cadastro
		String nome = tela.informar("Informe o Nome", "Nome", 1);
		String email = tela.informar("Informe o E-mail", "E-mail", 1);
		String fone = tela.informar("Informe o Telefone", "Fone", 1);

		// Cria o objeto da classe Contato
		Contato contato = new Contato(nome, email, fone);

		// Adiciona o Contato na Lista de Contatos da Agenda
		Lista.getInstance().add(contato);

	} // fecha o método cadastrarContato()

	private void buscarContato(Tela tela) {
		// Tamanho da lista (quantidade de registros)
		int numeroRegistros = Lista.getInstance().size();

		if (numeroRegistros > 0) {
			String listaContatos = "";

			// Percorre a Lista de Contatos
			for (int i = 0; i < numeroRegistros; i++) {
				listaContatos += "ID: " + (i + 1) + "\nNome: " + Lista.getInstance().get(i).getNome() + "\nE-mail: "
						+ Lista.getInstance().get(i).getEmail() + "\nFone: " + Lista.getInstance().get(i).getFone()
						+ "\n\n";
			}

			// Mostra o resultado da busca
			tela.mostrar(listaContatos, "Contatos", -1);

		} else {
			tela.mostrar("Nenhum contato registrado", "Contatos", 1);
		}
	}

	private void editarContato(Tela tela) {
		buscarContato(tela);
		
				
		int id = 0;
		
		try {
			id = Integer.parseInt(tela.informar("Informe o ID para editar", "editar contato", 1));
		}
		catch (Exception e) {
			tela.mostrar("Informe um número numérico", "ID inválido", 2);
		}
		
		boolean isAtivo = true;
		
		String menu = ":: O que deseja editar? ::\n\n" + "1. Nome\n" + "2. E-mail\n" + "3. Telefone\n\n";
		
		while (isAtivo) {
			String opcao = tela.informar(menu, "Informe uma das opções", -1);
			switch (opcao) {
			case "1":
				String nome = tela.informar("Informe o novo Nome", "Nome", 1);
				Lista.getInstance().get(id - 1).setNome(nome);
				break;

			case "2":
				String email = tela.informar("Informe o novo E-mail", "E-mail", 1);
				Lista.getInstance().get(id - 1).setEmail(email);
				break;

			case "3":
				String fone = tela.informar("Informe o Telefone", "Fone", 1);
				Lista.getInstance().get(id - 1).setFone(fone);
				break;
			default:
				isAtivo = false;
				break;
				}
			}
	}
	
	private void excluirContato(Tela tela) {
		buscarContato(tela);
		
		
		int id = 0;
		
		try {
			id = Integer.parseInt(tela.informar("Informe o ID para excluir", "ecluir contato", 1));
		}
		catch (Exception e) {
			tela.mostrar("Informe um número numérico", "ID inválido", 2);
		}
		Lista.getInstance().remove(id - 1);
		
		
	}
}
