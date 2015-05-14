package tsi.lpv.agendaeletronica.eventos.tarefa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import tsi.lpv.agendaeletronica.entidades.ValidarDados;
import tsi.lpv.agendaeletronica.entidades.tarefa.Compromisso;
import tsi.lpv.agendaeletronica.entidades.tarefa.Tarefa;
import tsi.lpv.agendaeletronica.gui.tarefa.IgAlterarTarefa;

public class TEActionIgAltTarefa implements ActionListener {
	
	private final String MSG_ERRO_DESCRICAO = " O campo descrição não pode ficar vazio!";
	private final String MSG_ERRO_DATA = " A data da tarefa não foi selecionada!";
	private final String MSG_ERRO_HORA = " O campo hora deve receber hora(s) e minuto(s) válido(s)!";
	
	private IgAlterarTarefa ig;

	public TEActionIgAltTarefa(IgAlterarTarefa igAlterarTarefa) {
		super();
		this.ig = igAlterarTarefa;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ig.getBtnAlterar()) {
			if(ValidarDados.validarVazio(ig.getDescricaoTextField().getText())) {
				if(ig.getValorDatePicker() != null) {
					if(ValidarDados.validarHora(ig.getHoraFormattedTextField().getText().replace(":", "").trim())) {
						Calendar cal = Calendar.getInstance();
						ig.esconderMsgCamposErrados();
						
						cal.setTime(ig.getValorDatePicker());
						cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ig.getHoraFormattedTextField().getText().replace(":", "").substring(0, 2)));
						cal.set(Calendar.MINUTE, Integer.parseInt(ig.getHoraFormattedTextField().getText().replace(":", "").substring(2, 4)));
						cal.set(Calendar.SECOND, 00);
						cal.set(Calendar.MILLISECOND, 00);
						
						int index = ig.getTarefasComboBox().getSelectedIndex() - 1;
						int codigoTarefa = ig.getArrayListTarefas().get(index).getCodigoTarefa();
						
						String novaDescricao = ig.getDescricaoTextField().getText();
						String antigaDescricao = ig.getDescricaoAntiga();
						String novaData = new SimpleDateFormat("dd/MM/yyyy").format(ig.getValorDatePicker());
						String antigaData = new SimpleDateFormat("dd/MM/yyyy").format(ig.getDataHoraAntiga());
						String novaHora = ig.getHoraFormattedTextField().getText();
						String antigaHora = new SimpleDateFormat("HH:mm").format(ig.getDataHoraAntiga());

						boolean descricaoAlterada = antigaDescricao.equals(novaDescricao);
						boolean dataAlterada = antigaData.equals(novaData);
						boolean horaAlterada = antigaHora.equals(novaHora);
						
						int itensInSelec[] = ig.getInserirPessoasList().getSelectedIndices();
						int itensReSelec[] = ig.getRemoverPessoasList().getSelectedIndices();
						String compromissosInseridos = "";
						String compromissosRemovidos = "";
						String dadosAlterados = "";
						
						for(int i = 0; i < itensInSelec.length; i++) {
							// Verifica se foi possível inserir o compromisso no banco de dados.
							if(new Compromisso(ig.getArrayListPessoasIn().get(itensInSelec[i]).getCodigoPessoa(), codigoTarefa).inserir())
								compromissosInseridos += "\n---- " + ig.getArrayListPessoasIn().get(itensInSelec[i]).getNome();
							else
								JOptionPane.showMessageDialog(ig, "Falha no cadastro do compromisso da pessoa de nome '"
										+ ig.getArrayListPessoasIn().get(itensInSelec[i]).getNome() + "' !", ig.NOME_MODULO, 0);
						}
						
						for(int i = 0; i < itensReSelec.length; i++) {
							// Verifica se foi possível excluir o compromisso no banco de dados.
							if(new Compromisso(ig.getArrayListPessoasRe().get(itensReSelec[i]).getCodigoPessoa(), codigoTarefa).excluir())
								compromissosRemovidos += "\n---- " + ig.getArrayListPessoasRe().get(itensReSelec[i]).getNome();
							else
								JOptionPane.showMessageDialog(ig, "Falha na exclusão do compromisso da pessoa de nome '"
										+ ig.getArrayListPessoasIn().get(itensInSelec[i]).getNome() + "' !", ig.NOME_MODULO, 0);
						}
						
						if(!descricaoAlterada || !dataAlterada || !horaAlterada) {
							// Verifica se foi possível alterar a tarefa no banco de dados.
							if(new Tarefa(codigoTarefa, novaDescricao, cal.getTime()).alterar()) {
								if(!descricaoAlterada) dadosAlterados += "\n---- Nova descrição: " + novaDescricao;
								if(!dataAlterada) dadosAlterados += "\n---- Nova data: " + novaData;
								if(!horaAlterada) dadosAlterados += "\n---- Nova hora: " + novaHora;
							}
							else
								JOptionPane.showMessageDialog(ig, "Falha na alteração da tarefa de descrição '" + antigaDescricao + "' !",
										ig.NOME_MODULO, 0);
						}
						
						if(!dadosAlterados.equals("") || !compromissosInseridos.equals("") || !compromissosRemovidos.equals("")) {
							if(!compromissosInseridos.equals(""))
								dadosAlterados += "\nNovas pessoas que realizarão a tarefa:" + compromissosInseridos;
							if(!compromissosRemovidos.equals(""))
								dadosAlterados += "\nPessoas que não mais realizarão a tarefa:" + compromissosRemovidos;
								
							JOptionPane.showMessageDialog(ig, "Alteração da tarefa de descrição '" + antigaDescricao +
									"' realizada com sucesso!" + "\nNovos dados:" + dadosAlterados, ig.NOME_MODULO, 1);
							
							// Libera a janela.
							ig.dispose();
						}
						else
							JOptionPane.showMessageDialog(ig, "Nenhum dado foi modificado!\nAlteração não realizada!", ig.NOME_MODULO, 2);
					}
					else ig.exibirMsgCamposErrados(MSG_ERRO_HORA, ig.getLblHora());
				}
				else ig.exibirMsgCamposErrados(MSG_ERRO_DATA, ig.getLblData());
			}
			else ig.exibirMsgCamposErrados(MSG_ERRO_DESCRICAO, ig.getLblDescricao());
		}
	}

} // class TEActionIgAltPessoa
