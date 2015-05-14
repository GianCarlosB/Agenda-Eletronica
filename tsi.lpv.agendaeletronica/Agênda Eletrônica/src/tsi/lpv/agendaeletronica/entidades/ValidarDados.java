package tsi.lpv.agendaeletronica.entidades;



/** A classe <code>ValidarDados</code> possui diversas rotinas necess�rias na valida��o de opera��es da agenda eletr�nica
 * 
 * @author Gian Carlos Barros Hon�rio
 */
public class ValidarDados {
	
	/** Express�o regular para a valida��o de e-mail
	 */
	public static final String EX_REG_EMAIL = "[A-Za-z0-9\\._-]+@[A-Za-z0-9]+(\\.[A-Za-z]+)*";
	
	/** Verifica se uma cadeia de caracteres <code>String</code> est� no formato de uma express�o regular
	 *  @param string <code>String</code> com a cadeia de caracteres que ser�o verificados
	 *  @param expressaoRegular <code>String</code> com a express�o regular
	 *  @return <code>boolean</code> com <code>true</code> caso o valor do objeto String seja igual ao padr�o 
	 *  definido pela express�o regular, e <code>false</code> caso contr�rio
	 */
	public static boolean verificarCadeiaDeCaracteres(String string, String expressaoRegular) {
		// Verifica se o valor do objeto String � igual ao padr�o definido pela express�o regular.
		if(string.matches(expressaoRegular))
			return true;
		
		return false;
	}
	
	/** Verifica se uma <code>String</code> est� vazia
	 *  @param str <code>String</code> com a cadeia de caracteres que ser� verificada 
	 *  @return <code>boolean</code> com <code>true</code> caso a cadeia de caracteres da <code>String</code> 
	 *  passada como par�metro seja vazia, e <code>false</code> caso contr�rio
	 */
	public static boolean validarVazio(String str) {
		if(str.equals(""))
			return false;
		
		return true;
	}
	
	/** Verifica se uma <code>String</code> pode formar uma hora v�lida
	 *  @param hora <code>String</code> com a cadeia de caracteres que ser� verificada 
	 *  @return <code>boolean</code> com <code>true</code> caso a cadeia de caracteres da <code>String</code> 
	 *  passada como par�metro forme uma hora v�lida, e <code>false</code> caso contr�rio
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
