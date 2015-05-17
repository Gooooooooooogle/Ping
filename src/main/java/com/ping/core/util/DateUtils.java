package com.ping.core.util;

import com.ping.core.Constant;

import java.sql.Timestamp;

/**
 * ����ʱ����صĹ�����
 * @author tianym
 * @version 0.1
 */
public abstract class DateUtils {
	private static long day = 1000 * 60 * 60 * 24;
	private static long hour = 1000 * 60 * 60;
	private static long minute = 1000 * 60;
	
	/**
	 * ת��long���ͺ���Ϊ��λ��ʱ�䵽���ֱ�ʾ
	 * @param time
	 * @return
	 */
	public static String convertToTimeText(long time) {
		StringBuffer sb = new StringBuffer();
		long d = time / day;
		if (d > 0) {
			sb.append(d + "��");
		} 
		long h = (time - d * day) / hour;
		if (h > 0) {
			sb.append(h + "Сʱ");
		}
		long m = (time - d * day - h * hour) / minute;
		if (m > 0) {
			return m + "����";
		}
		
		return sb.toString();
	}
	
	public static String convertToTimeText(Timestamp time) {
		return convertToTimeText(time.getTime());
	}

	public static Timestamp convertLongToTime(long currentTimeMillis) {
		return new Timestamp(currentTimeMillis);
	}

	/**
	 * ������Timestamp��ʾ�ĵ�ǰʱ��
	 * @return
	 */
	public static Timestamp getNowByTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * �Ƚ�ʱ�����Timestamp��true��ʾgenerateTime���ڵ���nowByTimestamp
	 * @param generateTime
	 * @param nowByTimestamp
	 * @return
	 */
	public static boolean compareTime(Timestamp generateTime, Timestamp nowByTimestamp) {
		return generateTime.compareTo(nowByTimestamp) < 0 ? true : false;
	}
	
	/**
	 * �����ʼ���֤����ʱ�����Чʱ���ж��Ƿ���֤����ڣ�����true��ʾδ����
	 * @param generateTime
	 * @return
	 */
	public static boolean compareTimeForAvailableToCheck(Timestamp generateTime) {
		Timestamp nowByTimestamp = new Timestamp(Constant.CHECKCODE_AVAILABLE + System.currentTimeMillis());
		return compareTime(generateTime, nowByTimestamp);
	}
}
