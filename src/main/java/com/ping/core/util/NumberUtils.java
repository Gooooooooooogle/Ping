package com.ping.core.util;

import java.util.UUID;

/**
 * ����������UUID/������صĹ�����
 * @author tianym
 * @version 0.1
 */
public abstract class NumberUtils {

	/**
	 * ���س�ȥ'-'���UUID
	 * @return String
	 */
	public static String generateUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) 
				+ uuid.substring(19, 23) + uuid.substring(24);
		return uuid;
	}
	
	/**
	 * ����ָ��������UUID
	 * @param sum
	 * @return String[] ���������ʾ��UUID
	 */
	public static String[] generateUUID(int sum) {
		if (sum <= 0) {
			return null;
		}
		String[] guuid = new String[sum];
		for (int i = 0; i < sum; i++) {
			guuid[i] = generateUUID();
		}
		return guuid;
	} 
	
}
