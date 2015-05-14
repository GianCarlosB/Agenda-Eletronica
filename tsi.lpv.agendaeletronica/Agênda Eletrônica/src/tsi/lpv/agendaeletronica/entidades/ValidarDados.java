package tsi.lpv.agendaeletronica.entidades;



/** A classe <code>ValidarDados</code> possui diversas rotinas necessárias na validação de operações da agenda eletrônica
 * 
 * @author Gian Carlos Barros Honório
 */
public class ValidarDados {
	
	/** Expressão regular para a validação de e-mail
	 */
	public static final String EX_REG_EMAIL = "[A-Za-z0-9\\._-]+@[A-Za-z0-9]+(\\.[A-Za-z]+)*";
	
	/** Verifica se uma cadeia de caracteres <code>String</code> está no formato de uma expressão regular
	 *  @param string <code>String</code> com a cadeia de caracteres que serão verificados
	 *  @param expressaoRegular <code>String</code> com a expressão regular
	 *  @return <code>boolean</code> com <code>true</code> caso o valor do objeto String seja igual ao padrão 
	 *  definido pela expressão regular, e <code>false</code> caso contrário
	 */
	public static boolean verificarCadeiaDeCaracteres(String string, String expressaoRegular) {
		// Verifica se o valor do objeto String é igual ao padrão definido pela expressão regular.
		if(string.matches(expressaoRegular))
			return true;
		
		return false;
	}
	
	/** Verifica se uma <code>String</code> está vazia
	 *  @param str <code>String</code> com a cadeia de caracteres que será verificada 
	 *  @return <code>boolean</code> com <code>true</code> caso a cadeia de caracteres da <code>String</code> 
	 *  passada como parâmetro seja vazia, e <code>false</code> caso contrário
	 */
	public static boolean validarVazio(String str) {
		if(str.equals(""))
			return false;
		
		return true;
	}
	
	/** Verifica se uma <code>String</code> pode formar uma hora válida
	 *  @param hora <code>String</code> com a cadeia de caracteres que será verificada 
	 *  @return <code>boolean</code> com <code>true</code> caso a cadeia de caracteres da <code>String</code> 
	 *  passada como parâmetro forme uma hora válida, e <code>false</code> caso contrário
	 */
	public static boolean validarHora(String hora) {
		if(hora.length() != 4)
			return false;
		
		try {
			int horas = Integer.parseInt(hora.substring(0, 2));
			int minutos = Integer.parseInt(hora.substring(2, 4));
			
			if((horas < 0 || horas > 23) || (minutos < 0 || minutos > 59))
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}

} // class ValidarDados
