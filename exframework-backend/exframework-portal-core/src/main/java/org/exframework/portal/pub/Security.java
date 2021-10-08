package org.exframework.portal.pub;

public class Security {

	public static String encode(String as_name, String as_passwd) {

		int i;

		for (; as_name.length() < 5;) {
			as_name = "0" + as_name;
		}

		String l_name = as_name.toUpperCase();

		if ("".equals(l_name) || l_name == null) {
			return "";
		}
		String ls_passwd = as_passwd.toUpperCase();

		if ("".equals(ls_passwd) || ls_passwd == null) {
			return "";
		}
		int l_shift;

		for (i = 1; i <= l_name.length(); i++) {

			l_shift = (int) l_name.charAt(i - 1);

			if (l_shift < 65 || l_shift > 90) {

				l_shift = 65 + l_shift % 24;

				l_name = l_name.substring(0, i - 1) + (char) l_shift + l_name.substring(i);

			}
		}

		for (i = 1; i <= ls_passwd.length(); i++) {

			l_shift = (int) ls_passwd.charAt(i - 1);

			if (l_shift < 65 || l_shift > 90) {

				l_shift = 65 + l_shift % 24;

				ls_passwd = ls_passwd.substring(0, i - 1) + (char) l_shift + ls_passwd.substring(i);

			}
		}

		int l_offset = (int) l_name.charAt(1);
		int l_root = (int) ls_passwd.charAt(ls_passwd.length() - 1);
		l_shift = (int) l_name.charAt(l_name.length() - 1);

		l_shift = l_shift % 13;

		String l_answer = l_name + ls_passwd;

		int l_position = 1;

		for (i = 1; i <= 30; i++) {

			if (l_answer.length() >= 30) {

				break;
			}

			l_shift = l_shift + l_offset + i;
			if (l_shift > 90) {
				l_shift = l_shift % 24;
				l_shift = l_shift + 65;
			}

			String l_char = String.valueOf((char) l_shift);

			if (l_position == 1) {
				l_answer = l_answer + l_char;
				l_position = 0;
			} else {
				l_answer = l_char + l_answer;
				l_position = 1;
			}

		}

		for (i = 1; i <= 30; i++) {

			l_shift = (int) l_answer.charAt(i - 1);
			l_shift = l_shift + l_root + i;
			if (l_shift > 90) {
				l_shift = l_shift % 24;
				l_shift = l_shift + 65;
			}
			l_answer = l_answer.substring(0, i - 1) + String.valueOf((char) (l_shift)) + l_answer.substring(i);

		}

		return l_answer;

	}

}