package com.quiz.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordUtils {
	private static final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
	
	/**
	 * Enkriptuje prosledjeni String
	 * @param data String - string koji želimo da ekriptujemo
	 * @return String - enkriptovani string
	 */
	public static String encryptPassword(String data) {
		return passwordEncryptor.encryptPassword(data);
	}
	
	/**
	 * Checks an unencrypted (plain) password against an encrypted one (a digest) to see if they match. 
	 * Uporedjuje enkriptovanu i ne-enkriptovanu šifru, i proverava da li se slažu.
	 * @param plainText - plain text šifra
	 * @param encryptedPassword - enkriptovana šifra
	 * @return true ako se šifre slažu, false ako se ne slažu.
	 */
	public static boolean checkPassword(String plainText, String encryptedPassword) {
		return passwordEncryptor.checkPassword(plainText, encryptedPassword);
	}
}
