package tsi.lpv.agendaeletronica.entidades.tarefa;

import java.util.ArrayList;

import tsi.lpv.agendaeletronica.persistencia.CompromissoDAO;

public class Compromisso extends CompromissoDAO {
	
	private int codigoPessoa;
	private int codigoTarefa;
	
	public Compromisso() {
		super();
	}

	public Compromisso(int codigoPessoa, int codigoTarefa) {
		super();
		this.codigoPessoa = codigoPessoa;
		this.codigoTarefa = codigoTarefa;
	}

	public int getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public int getCodigoTarefa() {
		return codigoTarefa;
	}

	public void setCodigoTarefa(int codigoTarefa) {
		this.codigoTarefa = codigoTarefa;
	}
	
	public boolean inserir() {
		return super.inserir(this);
	}
	
	public ArrayList<Compromisso> pesquisarTarefa() {
		return super.pesquisarTarefa(this);
	}
	
	public ArrayList<Compromisso> pesquisarPessoa() {
		return super.pesquisarPessoa(this);
	}
	
	public boolean excluir() {
		return super.excluir(this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Código da Pessoa: ").append(codigoPessoa)
				.append("\n Código da Tarefa:").append(codigoTarefa);
		return builder.toString();
	}
	
} // class Compromisso
