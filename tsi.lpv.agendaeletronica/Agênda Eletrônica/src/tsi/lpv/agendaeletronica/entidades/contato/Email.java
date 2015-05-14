package tsi.lpv.agendaeletronica.entidades.contato;

import java.util.ArrayList;

import tsi.lpv.agendaeletronica.persistencia.EMailDAO;

public class Email extends EMailDAO {
	
	private String email;
	private int codigoPessoa;
	
	public Email(int codigoPessoa) {
		super();
		this.codigoPessoa = codigoPessoa;
	}
	
	public Email(String email, int codigoPessoa) {
		super();
		this.email = email;
		this.codigoPessoa = codigoPessoa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public ArrayList<Email> pesquisar() {
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
		builder.append(" E-mail: ").append(email)
				.append("\n Código da Pessoa: ").append(codigoPessoa);
		return builder.toString();
	}

} // class Email
