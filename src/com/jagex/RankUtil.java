package com.jagex;

/**
 * @author Walied K. Yassen
 */
public final class RankUtil {

	public static RSString getIcon(int mod_status, int iron_status) {
		if (mod_status == 0 && iron_status == 0) {
			return StaticMethods.empty_string;
		}
		StringBuilder builder = new StringBuilder();
		if (mod_status != 0) {
			builder.append("<img=").append(mod_status - 1).append(">");
			if (iron_status != 0) {
				builder.append(" ");
			}
		}
		if (iron_status != 0) {
			builder.append("<img=").append(iron_status - 1).append(">");
		}
		return RSString.create(builder.toString());
	}
}
