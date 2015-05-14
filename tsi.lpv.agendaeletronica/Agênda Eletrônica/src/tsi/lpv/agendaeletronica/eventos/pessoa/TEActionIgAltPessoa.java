package tsi.lpv.agendaeletronica.eventos.pessoa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.contato.Email;
import tsi.lpv.agendaeletronica.entidades.contato.Telefone;
import tsi.lpv.agendaeletronica.entidades.contato.TipoTel;
import tsi.lpv.agendaeletronica.entidades.pessoa.Pessoa;
import tsi.lpv.agendaeletronica.gui.pessoa.IgAlterarPessoa;

public class TEActionIgAltPessoa implements ActionListener {
	
	private final String MSG_ERRO_NOME = " O campo nome não pode ficar vazio!";
	private final String MSG_ERRO_DATA = " A data de nascimento da pessoa não foi selecionada!";
	private final String MSG_ERRO_TEL = " O campo telefone não pode ficar vazio!";
	private final String MSG_ERRO_EMAIL = " O campo e-mail não foi preenchido corretamente!";
	
	private IgAlterarPessoa ig;

	public TEActionIgAltPessoa(IgAlterarPessoa igAlterarPessoa) {
		super();
		this.ig = igAlterarPessoa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getBtnAlterar()) {
			if(ValidarDados.validarVazio(ig.getNovoNomeTextField().getText())) {
				if(ig.getValorDatePicker() != null) {
					int index = ig.getNomesComboBox().getSelectedIndex() - 1;
					int codigoPessoa = ig.getArrayListPessoas().get(index).getCodigoPessoa();
					
					String novoNome = ig.getNovoNomeTextField().getText();
					String antigoNome = ig.getNomesComboBox().getSelectedItem().toString();
					String novoTel = ig.getNovoNumeroFormattedTextField().getText();
					String antigoTel = ig.getTelefonesComboBox().getSelectedItem().toString();
					String novoEmail = ig.getNovoEmailTextField().getText();
					String antigoEmail = ig.getEmailsComboBox().getSelectedItem().toString();
					
					boolean telVazio = ValidarDados.validarVazio(ig.getTelefonesComboBox().getSelectedItem().toString());
					boolean mailVazio = ValidarDados.validarVazio(ig.getEmailsComboBox().getSelectedItem().toString());
					boolean nomeAlterado = ig.getNomesComboBox().getSelectedItem().toString().equals(novoNome);
					boolean dataAlterada = ig.getValorDatePicker().equals(ig.getDataAntiga());
					
					if(!telVazio || (telVazio && ValidarDados.validarVazio(novoTel.replace("(", "").replace(")", "").replace("-", "").trim()))) {
						if(!mailVazio || (mailVazio && ValidarDados.verificarCadeiaDeCaracteres(novoEmail, ValidarDados.EX_REG_EMAIL))) {
							ig.esconderMsgCamposErrados();
							String dadosAlterados = "";
							
							if(telVazio) {
								// Verifica se foi possível alterar o telefone no banco de dados.
								if(new Telefone(novoTel, TipoTel.obterTipo(ig.obterRadioBtnSelecionado()), codigoPessoa).alterar(antigoTel))
									dadosAlterados += "\n---- Novo número: " + novoTel;
								else
									JOptionPane.showMessageDialog(ig, "Falha na alteração do número de telefone: " + antigoTel + " !",
											ig.NOME_MODULO, 0);
							}
							
							if(mailVazio) {
								// Verifica se foi possível alterar o e-mail no banco de dados.
								if(new Email(novoEmail, codigoPessoa).alterar(antigoEmail))
									dadosAlterados += "\n---- Novo e-mail: " + novoEmail;
								else
									JOptionPane.showMessageDialog(ig, "Falha na alteração do e-mail: " + antigoEmail + " !", ig.NOME_MODULO, 0);
							}
							
							if(!nomeAlterado || !dataAlterada) {
								// Verifica se foi possível alterar a pessoa no banco de dados.
								if(new Pessoa(codigoPessoa, novoNome, ig.getValorDatePicker()).alterar()) {
									if(!nomeAlterado) dadosAlterados += "\n---- Novo nome: " + novoNome;
									if(!dataAlterada) dadosAlterados += "\n---- Nova data de nascimento: " +  new SimpleDateFormat("dd/MM/yyyy")
																											.format(ig.getValorDatePicker());
								}
								else
									JOptionPane.showMessageDialog(ig, "Falha na alteração da pessoa de nome '" + antigoNome + "' !",
											ig.NOME_MODULO, 0);
							}
							
							if(!dadosAlterados.equals("")) {
								JOptionPane.showMessageDialog(ig, "Alteração da pessoa de nome '" + antigoNome + "' realizada com sucesso!"
										+ "\nNovos dados:" + dadosAlterados, ig.NOME_MODULO, 1);
								
								// Libera a janela.
								ig.dispose();
							}
							else
								JOptionPane.showMessageDialog(ig, "Nenhum dado foi modificado!\nAlteração não realizada!", ig.NOME_MODULO, 2);
						}
						else ig.exibirMsgCamposErrados(MSG_ERRO_EMAIL, ig.getLblNovoEmail());
					}
					else ig.exibirMsgCamposErrados(MSG_ERRO_TEL, ig.getLblNovoNumero());
				}
				else ig.exibirMsgCamposErrados(MSG_ERRO_DATA, ig.getLblNovaDataDeNascimento());
			}
			else ig.exibirMsgCamposErrados(MSG_ERRO_NOME, ig.getLblNovoNome());
		}
	}

} // class TEActionIgAltPessoa
