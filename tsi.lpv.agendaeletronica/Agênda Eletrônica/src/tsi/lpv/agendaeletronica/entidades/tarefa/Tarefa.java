package tsi.lpv.agendaeletronica.entidades.tarefa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import tsi.lpv.agendaeletronica.persistencia.TarefaDAO;

public class Tarefa extends TarefaDAO implements Comparator<Tarefa> {
	
	private int codigoTarefa;
	private String descricao;
	private Date dataHora;
	
	public Tarefa() {
		super();
	}

	public Tarefa(String descricao, Date dataHora) {
		super();
		this.descricao = descricao;
		this.dataHora = dataHora;
	}
	
	public Tarefa(int codigoTarefa, String descricao, Date dataHora) {
		super();
		this.codigoTarefa = codigoTarefa;
		this.descricao = descricao;
		this.dataHora = dataHora;
	}

	public int getCodigoTarefa() {
		return codigoTarefa;
	}

	public void setCodigoTarefa(int codigoTarefa) {
		this.codigoTarefa = codigoTarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	
	public boolean inserir() {
		return super.inserir(this);
	}
	
	public int proximoValorSequencia() {
		return super.proximoValorSequencia();
	}
	
	public ArrayList<Tarefa> pesquisar() {
		return super.pesquisar();
	}
	
	public boolean alterar() {
		return super.alterar(this);
	}
	
	public boolean excluir() {
		return super.excluir(this);
	}

	@Override
	public int compare(Tarefa t1, Tarefa t2) {
		return t2.getDataHora().compareTo(t1.getDataHora());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Código da Tarefa:").append(codigoTarefa)
				.append("\n Descricão: ").append(descricao)
				.append("\n Data: ").append(new SimpleDateFormat("dd/MM/yyyy").format(dataHora))
				.append("\n Hora: ").append(new SimpleDateFormat("HH:mm:ss").format(dataHora));
		return builder.toString();
	}

} // class Tarefa
