package com.ZJJ.Utility;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

/**
 * 工具类
 * 
 * @author JJDJJ
 *
 */
public class Utility {

	private static SimpleDateFormat simpleDateFormatyyyyMMddHHmmss = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static SimpleDateFormat simpleDateFormatYYYYMMDDHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

	private final static String DEFAULTYMDHMS = "1970-01-01 00:00:00";

	/**
	 * 返回YYYYMMDDHHmmss类型的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateYYYYMMDDHHmmss(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatYYYYMMDDHHmmss.format(date);
	}

	/**
	 * 返回yyyy/MM/dd HH:mm:ss类型的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateyyyyMMddHHmmss(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatyyyyMMddHHmmss.format(date);
	}

	/**
	 * 获取Unix时间戳
	 * 
	 * @return
	 */
	public static String getTimesTamp() {
		Long timestamp = Long.valueOf(System.currentTimeMillis() / 1000L);
		String times = timestamp.toString();
		return times;
	}

	/**
	 * 生成8位数随机编号
	 * 
	 * @return
	 */
	public static String getUUId() {
		String timesTamp = getTimesTamp();
		String idbefore = timesTamp.substring(timesTamp.length() - 4, timesTamp.length());
		int idafter = (int) (Math.random() * 9999) + 1000;
		String unId = (new StringBuilder(String.valueOf(idbefore))).append(idafter).toString();
		return unId;
	}

	/**
	 * 根据年月生成id
	 * 
	 * @return
	 */
	public static String getUUidByYMD() {
		Calendar calendar = Calendar.getInstance();
		String year = (new StringBuilder(String.valueOf(calendar.get(1)))).toString();
		String month = (new StringBuilder(String.valueOf(calendar.get(2) + 1))).toString();
		String day = (new StringBuilder(String.valueOf(calendar.get(5)))).toString();
		String uuidFirst = (new StringBuilder(String.valueOf(year))).append(month).append(day).toString();
		int idlast = (int) (Math.random() * 9999) + 1000;
		String uuid = (new StringBuilder(String.valueOf(uuidFirst))).append(idlast).toString();
		return uuid;
	}
	
	public static String getReplaceDate(String dateString){
		String replace = dateString.replace("-", "");
		String newDate = replace+"000000";
		return newDate;
	}
	
	public static void main(String[] args) {
		String replaceDate = getReplaceDate("2017-10-15");
		System.out.println(replaceDate);
	}
}
