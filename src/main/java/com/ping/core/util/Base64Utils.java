package com.ping.core.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public abstract class Base64Utils {
	public final static String ENCODING = "utf-8";
	
	public static String encode(String data) throws UnsupportedEncodingException {
		byte[] encodeByte = Base64.encodeBase64(data.getBytes(ENCODING));
		return new String(encodeByte, ENCODING);
	}
	
	/**
	 * Base64安全编码 遵循RFC2045实现
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String encodeSafe(String data) throws UnsupportedEncodingException {
		byte[] encodeByte = Base64.encodeBase64(data.getBytes(ENCODING), true);
		return new String(encodeByte, ENCODING);
	}
	
	public static String decode(String data) throws UnsupportedEncodingException {
		byte[] decodeByte = Base64.decodeBase64(data.getBytes(ENCODING));
		return new String(decodeByte, ENCODING);
	}
	
	/**
	 * Url Base64编码
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String encodeUrl(String data) throws UnsupportedEncodingException {
		byte[] encodeByte = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
		return new String(encodeByte, ENCODING);
	}
	
	public static String decodeUrl(String data) throws UnsupportedEncodingException {
		return decode(data);
	}
	
}
