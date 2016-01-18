/*
 * Copyright (c) 2016
 * Lokesh Lavangale All Rights Reserved 
 * This is Demonstration Code only is provided AS IS IN basis without any support 
 * 
 * Use of this code is only for demonstration only and can be re-used with prior permission
 * 
 */
package com.lokesh.note.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.lokesh.note.exception.DesException;

/**
 * @author Lokesh Lavangale
 * 
 *         DesLib.java Standard 3DES String encryption/decryption routine
 */

public class DesLib {

	private DesLib() {
	}

	private final static String passwordKey = "mario123";

	private static int ITERATIONS = 20;

	// Salt simple is a Byte Array
	private final static byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8,
			(byte) 0xee, (byte) 0x99 };

	/**
	 * This Method encrypts the plain text (String) using standard DES
	 * algorithm.
	 * 
	 * @param plaintext
	 *            String to be encrypted
	 * @return Encrypted string
	 * @exception DesException
	 *                throws generic DesException.
	 */

	public static byte[] encrypt(String plaintext) throws DesException {
		String self = "DesLib.encrypt: ";
		try {
			PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, ITERATIONS);

			// Create a secret key.
			SecretKey symmetricKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(new PBEKeySpec(passwordKey.toCharArray()));
			// Initialize the cipher.

			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

			pbeCipher.init(Cipher.ENCRYPT_MODE, symmetricKey, pbeParamSpec);

			byte[] encryptedText = pbeCipher.doFinal(plaintext.getBytes());

			return encryptedText;

		} catch (NoSuchAlgorithmException ex) {
			throw new DesException("Algorithm not supported");
		} catch (InvalidKeySpecException ex) {
			throw new DesException("Invalid encryption key specification");
		} catch (IllegalBlockSizeException ex) {
			throw new DesException("Illegal block size");
		} catch (NoSuchPaddingException ex) {
			throw new DesException("Padding not supported");
		} catch (InvalidKeyException ex) {
			throw new DesException("Invalid encryption key");
		} catch (BadPaddingException ex) {
			throw new DesException(ex.getMessage());
		} catch (InvalidAlgorithmParameterException ex) {
			throw new DesException("Invalid algorithm parameter");
		}

		catch (SecurityException ex) {
			throw new DesException("Policy files are not updated. "
					+ "\n VAM data can not be saved, Please contact system administrator. ");
		}
	}

	/**
	 * This Method decrypts the encrypted (String) using standard DES algorithm.
	 * 
	 * @param encryptedString
	 *            String to be decrypted
	 * @return plain text decrypted string
	 * @exception DesException
	 *                throws generic DesException.
	 */

	public static String decrypt(byte[] encryptedBytes) throws DesException {
		String self = "DesLib.decrypt: ";
		try {
			PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, ITERATIONS);

			SecretKey symmetricKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(new PBEKeySpec(passwordKey.toCharArray()));
			// Initialize the cipher.

			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

			pbeCipher.init(Cipher.DECRYPT_MODE, symmetricKey, pbeParamSpec);

			byte[] decryptedBytes = pbeCipher.doFinal(encryptedBytes);

			return new String(decryptedBytes);

		} catch (NoSuchAlgorithmException ex) {
			throw new DesException("Algorithm not supported");
		} catch (InvalidKeySpecException ex) {
			throw new DesException(" Invalid encryption key specification");
		} catch (IllegalBlockSizeException ex) {
			throw new DesException(" Illegal block size");
		} catch (NoSuchPaddingException ex) {
			throw new DesException(" Padding not supported");
		} catch (InvalidKeyException ex) {
			throw new DesException(" Invalid encryption key");
		} catch (BadPaddingException ex) {
			throw new DesException(" Bad Padding");
		} catch (InvalidAlgorithmParameterException ex) {
			throw new DesException(" Invalid algorithm parameter");
		} catch (SecurityException ex) {
			throw new DesException("Policy files are not updated. " + "\n Please contact system administrator. ");
		}

	}

	/**
	 * Test driver for DESApp. This method is used to handle command line
	 * invocation of this class
	 * 
	 * @param args
	 *            The command line arguments
	 * @exception Exception
	 *                Description of the Exception
	 */

	public static void main(String[] args) throws Exception {
		// now encrypt it
		// DesLib.printCipherOnYourSystem();

		String codedStr = new String(DesLib.encrypt("itsgrand3"));

		System.out.println(codedStr);
		// now decrypt it
		String str = DesLib.decrypt(codedStr.getBytes());
		System.out.println(str);
	}
}
