package tsi.lpv.agendaeletronica.entidades.contato;

import java.util.ArrayList;

import tsi.lpv.agendaeletronica.persistencia.TelefoneDAO;

public class Telefone extends TelefoneDAO {
	
	private String numero;
	private TipoTel tipo;
	private int codigoPessoa;
	
	public Telefone(int codigoPessoa) {
		super();
		this.codigoPessoa = codigoPessoa;
	}
	
	public Telefone(String numero, TipoTel tipo, int codigoPessoa) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.codigoPessoa = codigoPessoa;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTel getTipo() {
		return tipo;
	}

	public void setTipo(TipoTel tipo) {
		this.tipo = tipo;
	}

	public int getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}
	
	public boolean inserir() {
		return super.inserir(this);
	}
	
	public ArrayList<Telefone> pesquisar() {
		return super.pesquisar(this);
	}
	
	public boolean alterar(String chave) {
		return super.alterar(this, chave);
	}
	
	public boolean excluir() {
		return super.excluir(this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Telefone: ").append(numero)
				.append("\n Tipo: ").append(tipo)
				.append("\n Código da Pessoa: ").append(codigoPessoa);
		return builder.toString();
	}

} // class Telefone
