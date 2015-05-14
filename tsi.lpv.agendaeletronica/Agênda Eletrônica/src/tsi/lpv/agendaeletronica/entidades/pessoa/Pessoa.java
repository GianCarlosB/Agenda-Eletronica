package tsi.lpv.agendaeletronica.entidades.pessoa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import tsi.lpv.agendaeletronica.persistencia.PessoaDAO;

public class Pessoa extends PessoaDAO implements Comparator<Pessoa> {
	
	private int codigoPessoa;
	private String nome;
	private Date dataDeAniversario;
	
	public Pessoa() {
		super();
	}
	
	public Pessoa(String nome, Date dataDeAniversario) {
		super();
		this.nome = nome;
		this.dataDeAniversario = dataDeAniversario;
	}
	
	public Pessoa(int codigoPessoa, String nome, Date dataDeAniversario) {
		super();
		this.codigoPessoa = codigoPessoa;
		this.nome = nome;
		this.dataDeAniversario = dataDeAniversario;
	}

	public int getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataDeAniversario() {
		return dataDeAniversario;
	}

	public void setDataDeAniversario(Date dataDeAniversario) {
		this.dataDeAniversario = dataDeAniversario;
	}
	
	public boolean inserir() {
		return super.inserir(this);
	}
	
	public ArrayList<Pessoa> pesquisar() {
		return super.pesquisar();
	}
	
	public boolean alterar() {
		return super.alterar(this);
	}
	
	public boolean excluir() {
		return super.excluir(this);
	}

	@Override
	public int compare(Pessoa p1, Pessoa p2) {
		return p1.getNome().compareToIgnoreCase(p2.getNome());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Código da Pessoa: ").append(codigoPessoa)
				.append("\n Nome: ").append(nome)
				.append("\n Data de Aniversário: ").append(new SimpleDateFormat("dd/MM").format(dataDeAniversario));
		return builder.toString();
	}
	
} // class Pessoa
