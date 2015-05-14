package tsi.lpv.agendaeletronica.eventos.pessoa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.gui.pessoa.IgCadastrarPessoa;

public class TEActionIgCadPessoa implements ActionListener {
	
	private final String MSG_ERRO_NOME = " O campo nome não pode ficar vazio!";
	private final String MSG_ERRO_DATA = " A data de nascimento da pessoa não foi selecionada!";
	
	private IgCadastrarPessoa ig;

	public TEActionIgCadPessoa(IgCadastrarPessoa igCadastrarPessoa) {
		super();
		this.ig = igCadastrarPessoa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getCadastrarButton()) {
			if(ValidarDados.validarVazio(ig.getNomeTextField().getText())) {
				if(ig.getValorDatePicker() != null) {
					String nome = ig.getNomeTextField().getText();
					ig.esconderMsgCamposErrados();
					
					// Verifica se foi possível inserir a pessoa no banco de dados.
					if(new Pessoa(nome, ig.getValorDatePicker()).inserir())
						JOptionPane.showMessageDialog(ig, "Cadastro da pessoa de nome '" + nome + "' realizado com sucesso!", ig.NOME_MODULO, 1);
					else
						JOptionPane.showMessageDialog(ig, "Falha no cadastro da pessoa de nome '" + nome + "' !", ig.NOME_MODULO, 0);
					
					// Libera a janela.
					ig.dispose();
				}
				else ig.exibirMsgCamposErrados(MSG_ERRO_DATA, ig.getLblDataDeNascimento());
			}
			else ig.exibirMsgCamposErrados(MSG_ERRO_NOME, ig.getLblNome());
		}
	}

} // class TEActionIgCadPessoa
