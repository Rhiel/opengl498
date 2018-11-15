package com.jagex;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.graphics.runetek4.opengl.GLShapes;
import com.jagex.graphics.runetek4.opengl.GLState;

/**
 * Created with IntelliJ IDEA.
 * User: peterbjornx
 * Date: 9-10-14
 * Time: 19:08
 * To change this template use File | Settings | File Templates.
 */
public class DebugConsole {

	public static boolean opened = false;
	public static int line_height;
	public static int input_line_height;

	public static int fade_in_counter = 0;
	public static FileOutputStream output_stream;
	public static String[] output_buffer;
	public static int line_count = 0;
	public static int line_scroll_pos = 0;

	public static String input_buffer = "";
	public static int input_cursor_pos = 0;
	public static int input_history_ptr = 0;

	public static String[] current_script;
	public static int script_pointer = -1;
	public static long script_resume_timestamp;

	public static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

	public static String DEBUG_CONSOLE_INFO = "This is the developer console. To close, press the `~` key on your keyboard.";
	public static KeyEvent[] key_press_events = new KeyEvent[128];
	public static int key_press_count = 0;

	public static boolean is_open() {
		return opened;
	}

	public static boolean is_console_allowed() {
		return GameClient.clientState >= 1;
	}

	public static void open() {
		if (is_console_allowed()) {
			if (output_buffer == null) {
				initialize();
			}
			opened = true;
			fade_in_counter = 0;
		}
	}

	public static void close() {
		opened = false;
		RSInterfaceList.setAllDirty(11980);
		if (GameClient.clientState == 5 || GameClient.clientState == 10 || GameClient.clientState == 20) {
			Rasterizer2D.set_clipping(0, 0, GameShell.frame_width, GameShell.frame_height);
		}
	}

	public static void initialize() {
		input_line_height = 15;
		line_height = 15;
		output_buffer = new String[500];
		for (int line = 0; line < output_buffer.length; line++) {
			output_buffer[line] = "";
		}
		write(DEBUG_CONSOLE_INFO);
	}

	public static void write(String message) {
		if (output_buffer == null) {
			initialize();
		}
		calendar.setTime(new Date(TimeTools.getMillis()));
		int hour = calendar.get(11);
		int minute = calendar.get(12);
		int second = calendar.get(13);
		String timestamp = Integer.toString(hour / 10) + hour % 10 + ":" + minute / 10 + minute % 10 + ":" + second / 10 + second % 10;
		String[] message_lines = split(message, '\n');
		for (String line : message_lines) {
			System.arraycopy(output_buffer, 0, output_buffer, 1, line_count);
			output_buffer[0] = timestamp + ": " + line;
			if (output_stream != null) {
				try {
					output_stream.write(a((byte) -84, output_buffer[0] + "\n"));
				} catch (IOException ioexception) {
					/* empty */
				}
			}
			if (-1 + output_buffer.length > line_count) {
				line_count++;
				if (line_scroll_pos > 0) {
					line_scroll_pos++;
				}
			}
		}

	}

	public static String[] split(String string, char delimiter) {
		int delim_count = char_count(string, delimiter);
		String[] tokens = new String[delim_count + 1];
		int token_ptr = 0;
		int token_start = 0;
		for (int delim_no = 0; delim_no < delim_count; delim_no++) {
			int token_end;
			for (token_end = token_start; string.charAt(token_end) != delimiter; token_end++) {
				;
			}
			tokens[token_ptr++] = string.substring(token_start, token_end);
			token_start = token_end + 1;
		}
		tokens[delim_count] = string.substring(token_start);
		return tokens;
	}

	public static int char_count(String string, char find_char) {
		int count = 0;
		int length = string.length();
		for (int pos = 0; pos < length; pos++) {
			if (string.charAt(pos) == find_char) {
				count++;
			}
		}
		return count;
	}

	public static void execute_command_list(String[] list) {
		if (list.length <= 1) {
			input_buffer += list[0];
			input_cursor_pos += list[0].length();
		} else {
			for (int line_ptr = 0; list.length > line_ptr; line_ptr++) {
				if (list[line_ptr].startsWith("pause")) {
					int pause = 5;
					try {
						pause = Integer.parseInt(list[line_ptr].substring(6));
					} catch (Exception exception) {
						/* empty */
					}
					write("Pausing for " + pause + " seconds...");
					script_pointer = line_ptr + 1;
					current_script = list;
					script_resume_timestamp = TimeTools.getMillis() + pause * 1000;
					return;
				}
				input_buffer = list[line_ptr];
				execute_input(false);
			}
		}
	}

	public static void draw() {
		int x = 0;
		int y = 0;
		if (GLManager.opengl_mode) {
			GLState.set_clipping(x, y, GameShell.window_width + x, 340 + y);
			GLShapes.fill_rectangle(x, y, GameShell.window_width, 340, 0x332277, fade_in_counter);
		} else {
			Rasterizer2D.set_clipping(x, y, GameShell.window_width + x, 340 + y);
			Rasterizer2D.fill_rectangle(x, y, GameShell.window_width, 340, 0x332277, fade_in_counter);
		}
		int i_5_ = 340 / line_height;
		if (line_count > 0) {
			int i_6_ = -line_height + 332;
			int i_7_ = i_5_ * i_6_ / (i_5_ + line_count - 1);
			int i_8_ = 4;
			if (line_count > 1) {
				i_8_ += (i_6_ - i_7_) * (-1 + line_count - line_scroll_pos) / (line_count - 1);
			}
			if (GLManager.opengl_mode) {
				GLShapes.fill_rectangle(-16 + GameShell.window_width + x, y + i_8_, 12, i_7_, 0x332277, fade_in_counter);
			} else {
				Rasterizer2D.fill_rectangle(-16 + GameShell.window_width + x, y + i_8_, 12, i_7_, 0x332277, fade_in_counter);
			}
			for (int i_9_ = line_scroll_pos; line_scroll_pos + i_5_ > i_9_ && line_count > i_9_; i_9_++) {
				String[] strings = split(output_buffer[i_9_], '\010');
				int i_10_ = (GameShell.window_width - 24) / strings.length;
				for (int i_11_ = 0; strings.length > i_11_; i_11_++) {
					int i_12_ = 8 + i_11_ * i_10_;
					if (GLManager.opengl_mode) {
						GLState.set_clipping(x + i_12_, y, i_12_ + x + i_10_ - 8, 340 + y);
					} else {
						Rasterizer2D.set_clipping(x + i_12_, y, i_12_ + x + i_10_ - 8, 340 + y);
					}
					FontCache.b12_full.draw_text(RSString.create(sanitize_for_display(strings[i_11_])), x + i_12_, -2 + -input_line_height + y - (-340 + FontCache.b12_full.glyphs_miny) + -(line_height * (-line_scroll_pos + i_9_)), -1, -16777216);
				}
			}
		}
		FontCache.b12_full.draw_text_right_anchor(RSString.create("Build: 317"), x + GameShell.window_width - 25, 340 + y - 20, -1, -16777216);
		if (GLManager.opengl_mode) {
			GLState.set_clipping(x, y, GameShell.window_width + x, y + 340);
			GLShapes.draw_horizontal_line(x, 322, GameShell.window_width, 0xffffff);
		} else {
			Rasterizer2D.set_clipping(x, y, GameShell.window_width + x, y + 340);
			Rasterizer2D.draw_horizontal_line(x, 322, GameShell.window_width, 0xffffff);
		}
		FontCache.b12_full.draw_text(RSString.create("--> " + sanitize_for_display(input_buffer)), x + 10, -1 + y + 348 - FontCache.b12_full.glyphs_miny, -1, -16777216);
		if (GameShell.isFocusIn()) {
			int colour = -1;
			if (GameClient.timer % 40 < 20) {
				colour = 0xffffff;
				if (GLManager.opengl_mode) {
					GLShapes.draw_vertical_line(FontCache.b12_full.calculate_width(RSString.create("--> " + sanitize_for_display(input_buffer).substring(0, input_cursor_pos))) + 10 + x, 337 + y - FontCache.b12_full.glyphs_miny, 12, colour);
				} else {
					Rasterizer2D.draw_vertical_line(FontCache.b12_full.calculate_width(RSString.create("--> " + sanitize_for_display(input_buffer).substring(0, input_cursor_pos))) + 10 + x, 337 + y - FontCache.b12_full.glyphs_miny, 12, colour);
				}
			}
		}
		if (GLManager.opengl_mode) {
			GLState.reset_clipping();
		} else {
			Rasterizer2D.set_clipping(0, 0, Rasterizer2D.width, Rasterizer2D.height);
		}
	}

	public static String sanitize_for_display(String line) {
		String prompt = null;
		int index_of_prompt = line.indexOf("--> ");
		if (index_of_prompt >= 0) {
			prompt = line.substring(0, 4 + index_of_prompt);
			line = line.substring(4 + index_of_prompt);
		}
		if (line.startsWith("directlogin ")) {
			int start_of_password = line.indexOf(" ", "directlogin ".length());
			if (start_of_password >= 0) {
				int i_6_ = line.length();
				line = line.substring(0, start_of_password) + " ";
				for (int i_7_ = 1 + start_of_password; i_7_ < i_6_; i_7_++) {
					line += "*";
				}
			}
		}
		if (prompt == null) {
			return line;
		}
		return prompt + line;
	}

	public static void do_input_history() {
		if (input_history_ptr > 0) {
			int input_hist_count = 0;
			for (String line : output_buffer) {
				if (line.contains("--> ") && ++input_hist_count == input_history_ptr) {
					input_buffer = line.substring(2 + line.indexOf(">"));
					break;
				}
			}
		} else {
			input_buffer = "";
		}
	}

	public static void execute_input(boolean keep_input) {
		if (input_buffer.length() != 0) {
			write("--> " + input_buffer);
			Class53.processClientCommands(1337, RSString.createString("::" + input_buffer));
			if (!keep_input) {
				input_cursor_pos = 0;
				input_buffer = "";
			}
			input_history_ptr = 0;
		}
	}

	public static void process() {
		if (fade_in_counter < 102) {
			fade_in_counter += 6;
		}
		if (script_pointer != -1 && TimeTools.getMillis() > script_resume_timestamp) {
			for (int slptr = script_pointer; current_script.length > slptr; slptr++) {
				if (current_script[slptr].startsWith("pause")) {
					int pause = 5;
					try {
						pause = Integer.parseInt(current_script[slptr].substring(6));
					} catch (Exception exception) {
						/* empty */
					}
					write("Pausing for " + pause + " seconds...");
					script_pointer = slptr + 1;
					script_resume_timestamp = pause * 1000 + TimeTools.getMillis();
					return;
				}
				input_buffer = current_script[slptr];
				execute_input(false);
			}
			script_pointer = -1;
		}

		if (Class48.anInt749 != 0) {
			line_scroll_pos -= 5 * Class48.anInt749;
			if (line_scroll_pos >= line_count) {
				line_scroll_pos = -1 + line_count;
			}
			Class48.anInt749 = 0;
			if (line_scroll_pos < 0) {
				line_scroll_pos = 0;
			}
		}

		for (int event_ptr = 0; event_ptr < key_press_count; event_ptr++) {
			KeyEvent event = key_press_events[event_ptr];
			int key_code = event.getKeyCode();
			char key_char = event.getKeyChar();
			int key_mods = event.getModifiers();
			if (key_code == 10) {
				execute_input(false);
			}
			if (key_code == 9) {
				execute_input(true);
			} else if (key_mods == 2 && key_code == 67) {
				if (GameClient.system_clipboard != null) {
					String string = "";
					for (int pos = output_buffer.length - 1; pos >= 0; pos--) {
						if (output_buffer[pos] != null && output_buffer[pos].length() > 0) {
							string += output_buffer[pos] + '\n';
						}
					}
					GameClient.system_clipboard.setContents(new StringSelection(string), null);
				}
			} else if (key_mods == 2 && key_code == 86) {
				if (GameClient.system_clipboard != null) {
					try {
						Transferable transferable = GameClient.system_clipboard.getContents(null);
						if (transferable != null) {
							String string = (String) transferable.getTransferData(DataFlavor.stringFlavor);
							if (string != null) {
								String[] strings = split(string, '\n');
								execute_command_list(strings);
							}
						}
					} catch (Exception exception) {
						/* empty */
					}
				}
			} else if (key_code == 8 && input_cursor_pos > 0) {
				input_buffer = input_buffer.substring(0, input_cursor_pos - 1) + input_buffer.substring(input_cursor_pos);
				input_cursor_pos--;
			} else if (key_code == 127 && input_cursor_pos < input_buffer.length()) {
				input_buffer = input_buffer.substring(0, input_cursor_pos) + input_buffer.substring(input_cursor_pos + 1);
			} else if (key_code == 37 && input_cursor_pos > 0) {
				input_cursor_pos--;
			} else if (key_code == 39 && input_cursor_pos < input_buffer.length()) {
				input_cursor_pos++;
			} else if (key_code == 102) {
				input_cursor_pos = 0;
			} else if (key_code == 103) {
				input_cursor_pos = input_buffer.length();
			} else if (key_code == 38 && input_history_ptr < output_buffer.length) {
				input_history_ptr++;
				do_input_history();
				input_cursor_pos = input_buffer.length();
			} else if (key_code == 40 && input_history_ptr > 0) {
				input_history_ptr--;
				do_input_history();
				input_cursor_pos = input_buffer.length();
			} else if (is_alphanum(key_char) || "\\/.:, _-+[]~@".indexOf(key_char) != -1) {
				input_buffer = input_buffer.substring(0, input_cursor_pos) + key_press_events[event_ptr].getKeyChar() + input_buffer.substring(input_cursor_pos);
				input_cursor_pos++;
			}
		}
		key_press_count = 0;
	}

	public static boolean is_alphanum(char c) {
		return c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
	}

	public static final byte[] a(byte i, String string) {
		int i_10_ = string.length();
		byte[] is = new byte[i_10_];
		for (int i_12_ = 0; i_10_ > i_12_; i_12_++) {
			char c = string.charAt(i_12_);
			if ((c <= 0 || c >= '\u0080') && (c < '\u00a0' || c > '\u00ff')) {
				if (c != '\u20ac') {
					if (c == '\u201a') {
						is[i_12_] = (byte) -126;
					} else if (c != '\u0192') {
						if (c != '\u201e') {
							if (c != '\u2026') {
								if (c == '\u2020') {
									is[i_12_] = (byte) -122;
								} else if (c == '\u2021') {
									is[i_12_] = (byte) -121;
								} else if (c != '\u02c6') {
									if (c != '\u2030') {
										if (c == '\u0160') {
											is[i_12_] = (byte) -118;
										} else if (c != '\u2039') {
											if (c != '\u0152') {
												if (c == '\u017d') {
													is[i_12_] = (byte) -114;
												} else if (c == '\u2018') {
													is[i_12_] = (byte) -111;
												} else if (c == '\u2019') {
													is[i_12_] = (byte) -110;
												} else if (c == '\u201c') {
													is[i_12_] = (byte) -109;
												} else if (c == '\u201d') {
													is[i_12_] = (byte) -108;
												} else if (c != '\u2022') {
													if (c == '\u2013') {
														is[i_12_] = (byte) -106;
													} else if (c != '\u2014') {
														if (c != '\u02dc') {
															if (c == '\u2122') {
																is[i_12_] = (byte) -103;
															} else if (c != '\u0161') {
																if (c == '\u203a') {
																	is[i_12_] = (byte) -101;
																} else if (c == '\u0153') {
																	is[i_12_] = (byte) -100;
																} else if (c == '\u017e') {
																	is[i_12_] = (byte) -98;
																} else if (c != '\u0178') {
																	is[i_12_] = (byte) 63;
																} else {
																	is[i_12_] = (byte) -97;
																}
															} else {
																is[i_12_] = (byte) -102;
															}
														} else {
															is[i_12_] = (byte) -104;
														}
													} else {
														is[i_12_] = (byte) -105;
													}
												} else {
													is[i_12_] = (byte) -107;
												}
											} else {
												is[i_12_] = (byte) -116;
											}
										} else {
											is[i_12_] = (byte) -117;
										}
									} else {
										is[i_12_] = (byte) -119;
									}
								} else {
									is[i_12_] = (byte) -120;
								}
							} else {
								is[i_12_] = (byte) -123;
							}
						} else {
							is[i_12_] = (byte) -124;
						}
					} else {
						is[i_12_] = (byte) -125;
					}
				} else {
					is[i_12_] = (byte) -128;
				}
			} else {
				is[i_12_] = (byte) c;
			}
		}
		return is;
	}
}
