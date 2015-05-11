package com.ping.core.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public abstract class AESUtils {
	public static final String KEY_ALGORITHM = "AES";
	
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	
	/**
	 * ת����Կ
	 * @param key
	 * @return
	 */
	private static Key toKey(byte[] key) {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}
	
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	public static byte[] initKey() throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
		generator.init(128);
		SecretKey secretKey = generator.generateKey();
		return secretKey.getEncoded();
	}
	
	/**
	 * ��ʼ����Կ
	 * @return String Base64������Կ
	 * @throws Exception
	 */
	public static String initKeyString() throws Exception {
		return Base64.encodeBase64String(initKey());
	}
	
	/**
	 * ��ȡ��Կ
	 * @param key
	 * @return
	 */
	public static byte[] getKey(String key) {
		return Base64.decodeBase64(key);
	}
	
	/**
	 * ����
	 * @param data
	 * @param key ��Կ
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, String key) throws Exception {
		return decrypt(data, getKey(key));
	}
	
	/**
	 * ����
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, String key) throws Exception {
		return encrypt(data, getKey(key));
	}
	
	/**��������ת����16���� 
	 * @param buf 
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {  
	        StringBuffer sb = new StringBuffer();  
	        for (int i = 0; i < buf.length; i++) {  
	                String hex = Integer.toHexString(buf[i] & 0xFF);  
	                if (hex.length() == 1) {  
	                        hex = '0' + hex;  
	                }  
	                sb.append(hex.toUpperCase());  
	        }  
	        return sb.toString();  
	}  
	
	/**��16����ת��Ϊ������ 
	 * @param hexStr 
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;  
		}
	    byte[] result = new byte[hexStr.length()/2];  
	    for (int i = 0; i < hexStr.length() / 2; i++) {
	    	int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);  
	        int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);  
	        result[i] = (byte) (high * 16 + low);  
	    }  
	    return result;  
	}  
	
	/**
	 * ժҪ����
	 * @param data ��ժҪ����
	 * @return String ժҪ�ַ���
	 */
	public static String shaHex(byte[] data) {
		return DigestUtils.md5Hex(data);
	}
	
	/**
	 * ��֤
	 * @param data ��ժҪ����
	 * @param messageDigest ժҪ�ַ���
	 * @return boolean
	 */
	public static boolean validate(byte[] data, String messageDigest) {
		return messageDigest.equals(shaHex(data));
	}
	
}
