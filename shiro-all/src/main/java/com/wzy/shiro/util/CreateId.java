package com.wzy.shiro.util;

public class CreateId {

	/**
	 * 
	 * @param p_head
	 *            id头信�?
	 * @param id
	 *            自动生成的id
	 * @param length
	 *            id长度
	 * @return
	 */
	public static String createId(String p_head, Integer id, Integer length) {
		int a = length - String.valueOf(id).length();

		StringBuffer m_str = new StringBuffer();
		m_str.append("");
		if (a >= 0) {
			for (int i = 0; i < a; i++) {
				m_str.append("0");
			}
			return p_head + m_str.toString() + String.valueOf(id);
		} else {
			return p_head + String.valueOf(id);
		}

	}
}
