package com.jagex;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.jagex.game.runetek4.clientoptions.ClientOptions;
import com.jagex.game.runetek4.clientoptions.GamePreferences;
import com.jagex.graphics.runetek4.media.Font;
import com.jagex.graphics.runetek4.opengl.GLManager;
import com.jagex.launcher.Configurations;
import com.jagex.window.VideoMode;
import com.jagex.window.WindowMode;
import com.rs2.client.components.worldmap.WorldMap;
import com.rs2.client.components.worldmap.WorldMapArea;
import com.rs2.client.scene.Scene;
import com.rs2.client.scene.environment.SceneEnvironment;

/**
 * Created by Chris on 4/28/2017.
 */
public class CS2Runtime {
	public static int[] int_stack = new int[1000];
	static RSString[] str_stack = new RSString[1000];
	static long[] long_stack = new long[1000];
	public static int call_stack_ptr;
	public static int[] int_local_vars;
	public static RSString[] str_local_vars;
	public static long[] long_local_vars;
	public static CS2CallFrame[] call_stack = new CS2CallFrame[50];
	public static int[] int_global_vars = new int[2000];
	public static int[] int_array_size;
	public static int[][] int_arrays = new int[5][5000];
	public static int loginOpcode = -2;

	static {
		int_array_size = new int[5];
		call_stack_ptr = 0;
	}

	public static final void parseCS2Script(int max_instrs, CS2Event event, boolean dummy) {
		Object[] objects = event.scriptArguments;// spell listener
		int scriptId = ((Integer) objects[0]).intValue();
		CS2ScriptDefinition script = CS2ScriptDefinition.getCS2ScriptDefinition(scriptId, -17384);
		boolean debug = scriptId == 1369;
		if (debug) {
			System.out.println("=======================================================================");
		}
		if (script == null) {
			// NOOP
		} else {
			call_stack_ptr = 0;
			int int_stack_ptr = 0;
			int str_stack_ptr = 0;
			int long_stack_ptr = 0;
			int[] opcodes = script.opcodes;
			int[] int_operands = script.int_operands;
			int opcode = -1;
			int ip = -1;
			try {
				int_local_vars = new int[script.int_var_count];
				str_local_vars = new RSString[script.string_var_count];
				long_local_vars = new long[script.long_var_count];
				int int_v_ptr = 0;
				int str_v_ptr = 0;
				int long_v_ptr = 0;
				for (int index = 1; index < objects.length; index++) {
					if (objects[index] instanceof Integer) {
						int int_val = ((Integer) objects[index]).intValue();
						if (int_val == -2147483647) {
							int_val = event.mouseX;
						}
						if (int_val == -2147483646) {
							int_val = event.mouseY;
						}
						if (int_val == -2147483645) {
							int_val = event.component == null ? -1 : event.component.uid;
						}
						if (int_val == -2147483644) {
							int_val = event.anInt2270;
						}
						if (int_val == -2147483643) {
							int_val = event.component == null ? -1 : event.component.componentIndex;
						}
						if (int_val == -2147483642) {
							int_val = event.aClass64_2255 != null ? event.aClass64_2255.uid : -1;
						}
						if (int_val == -2147483641) {
							int_val = event.aClass64_2255 == null ? -1 : event.aClass64_2255.componentIndex;
						}
						if (int_val == -2147483640) {
							int_val = event.keyCode;
						}
						if (int_val == -2147483639) {
							int_val = event.keyChar;
						}
						int_local_vars[int_v_ptr++] = int_val;
					} else if (objects[index] instanceof RSString) {
						RSString string = (RSString) objects[index];
						if (string.equals(Class91.aClass16_1558)) {
							string = event.opbase;
						}
						str_local_vars[str_v_ptr++] = string;
					} else if (objects[index] instanceof Long) {
						long l = (Long) objects[index];
						long_local_vars[long_v_ptr++] = l;
					}
				}
				int executed_instr_count = 0;
				for (;;) {
					if (++executed_instr_count > max_instrs) {
						throw new RuntimeException("slow");
					}
					opcode = opcodes[++ip];// 1141 2433
					if (debug) {
						System.out.println(opcode);
					}
					if (opcode >= 6000) {
						if (opcode < 6100) {
							if (opcode == 6001) {
								int level = int_stack[--int_stack_ptr];
								if (-2 < ~level) {
									level = 1;
								}
								if (-5 > ~level) {
									level = 4;
								}
								ClientOptions.clientoption_brightness = level;
								if (!GLManager.opengl_loaded || !ClientOptions.clientoption_highdetails_lighting) {
									if (ClientOptions.clientoption_brightness == 1) {
										GraphicTools.change_brightness(0.9F);
									}
									if (ClientOptions.clientoption_brightness == 2) {
										GraphicTools.change_brightness(0.8F);
									}
									if (ClientOptions.clientoption_brightness == 3) {
										GraphicTools.change_brightness(0.7F);
									}
									if (ClientOptions.clientoption_brightness == 4) {
										GraphicTools.change_brightness(0.6F);
									}
								}
								if (GLManager.opengl_mode) {
									SceneEnvironment.update_brightness();
									if (!ClientOptions.clientoption_highdetails_lighting) {
										// TODO: OpenGL code here.
									}
								}
								// TODO: detail opcodes needs apply changes etc..
								StaticMethods.method890((byte) 127);
								ClientOptions.save(GameClient.gameSignlink);
								ClientOptions.clientoptions_updated = false;
								continue;
							} else if (opcode == 6002) {
								ClientOptions.set_removeroofs(1 == int_stack[--int_stack_ptr]);
								// LocTypeList.method1211(66); DK WHY THIS IS HERE, maybe 530 used to do it differently, only god knows
								ClientOptions.apply_changes();
								RoofCulling.update_culler_type();
								ClientOptions.save(GameClient.gameSignlink);
								ClientOptions.clientoptions_updated = false;
							} else if (opcode == 6004) {
								ClientOptions.clientoption_removeroofs_selective = ~int_stack[--int_stack_ptr] == -2;
								RoofCulling.update_culler_type();
								ClientOptions.save(GameClient.gameSignlink);
								ClientOptions.clientoptions_updated = false;
							} else if (opcode == 6009) {
								ClientOptions.clientoption_textures = int_stack[--int_stack_ptr] == 1;
								continue;
							} else if (opcode == 6017) {
								boolean highDetailAudio = 1 == int_stack[--int_stack_ptr];
								continue;
							} else if (opcode == 6018) {
								int i_182_ = int_stack[--int_stack_ptr];
								if (0 > i_182_) {
									i_182_ = 0;
								}
								if (-128 > (i_182_ ^ 0xffffffff)) {
									i_182_ = 127;
								}
								StaticMethods.soundPreference3 = i_182_;
								continue;
							} else if (opcode == 6019) {
								int i_183_ = int_stack[--int_stack_ptr];
								if (i_183_ < 0) {
									i_183_ = 0;
								}
								if (-256 > (i_183_ ^ 0xffffffff)) {
									i_183_ = 255;
								}
								if (i_183_ != Class21.anInt342) {
									if (Class21.anInt342 == 0 && WallObject.musicId != -1) {
										MusicPlayer.updateCurrentMusic(WallObject.musicId, CacheConstants.musicCacheIdx, 1, 0, i_183_, false);
										Js5.aBoolean1806 = false;
									} else if ((i_183_ ^ 0xffffffff) != -1) {
										Class65.method1229(i_183_, 110);
									} else {
										MusicPlayer.stopMusic(false);
										Js5.aBoolean1806 = false;
									}
									Class21.anInt342 = i_183_;
								}
								continue;
							} else if (opcode == 6020) {
								int i_184_ = int_stack[--int_stack_ptr];
								if (0 > i_184_) {
									i_184_ = 0;
								}
								if (127 < i_184_) {
									i_184_ = 127;
								}
								TimeTools.soundPreference1 = i_184_;
								continue;
							} else if (opcode == 6021) {
								ClientOptions.clientoption_removeroofs_override = int_stack[--int_stack_ptr] == 1;
								RoofCulling.update_culler_type();
							} else if (opcode == 6031) {
								// TODO:
								int i_155_ = int_stack[--int_stack_ptr];
								if (i_155_ < 0 || i_155_ > 5) {
									i_155_ = 2;
									// MovedStatics9.a(false, false, i_155_);
								}
								continue;
							} else if (opcode == 6037) {
								int i_184_ = int_stack[--int_stack_ptr];
								if (0 > i_184_) {
									i_184_ = 0;
								}
								if (127 < i_184_) {
									i_184_ = 127;
								}
								TimeTools.soundPreference2 = i_184_;
								continue;
							}
						} else if (opcode < 6200) {
							if (opcode == 6101) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_brightness;
								continue;
							} else if (opcode == 6102) {
								int_stack[int_stack_ptr++] = ClientOptions.is_removeroofs() ? 1 : 0;
								continue;
							} else if (opcode == 6103) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_removeroofs_selective ? 1 : 0;
								continue;
							} else if (opcode == 6105) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_grounddecor ? 1 : 0;
								continue;
							} else if (opcode == 6106) {
								// TODO:
								int_stack[int_stack_ptr++] = 0;
								continue;
							} else if (opcode == 6107) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_idleanims_many ? 1 : 0;
								continue;
							} else if (opcode == 6108) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_flickering_on ? 1 : 0;
								continue;
							} else if (opcode == 6109) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_textures ? 1 : 0;
								continue;
							} else if (opcode == 6110) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_spotshadows ? 1 : 0;
								continue;
							} else if (opcode == 6111) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_hardshadows;
								continue;
							} else if (opcode == 6112) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_highdetails_lighting ? 1 : 0;
								continue;
							} else if (opcode == 6114) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_highdetails_water ? 1 : 0;
								continue;
							} else if (opcode == 6115) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_enable_fog ? 1 : 0;
								continue;
							} else if (6116 == opcode) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_antialiasing;
								continue;
							} else if (opcode == 6117) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_stereo ? 1 : 0;
								continue;
							} else if (opcode == 6118) {
								int_stack[int_stack_ptr++] = StaticMethods.soundPreference3;
								continue;
							} else if (6120 == opcode) {
								int_stack[int_stack_ptr++] = TimeTools.soundPreference1;
								continue;
							} else if (6121 == opcode) {
								if (GLManager.opengl_mode) {
									int_stack[int_stack_ptr++] = GLManager.multisampling_supported ? 1 : 0;
								} else {
									int_stack[int_stack_ptr++] = 0;
								}
								continue;
							} else if (opcode == 6122) {
								int_stack[int_stack_ptr++] = !Js5.aBoolean1806 ? 0 : 1;
								continue;
							} else if (opcode == 6124) {
								int_stack[int_stack_ptr++] = ClientOptions.clientoption_antialiasing_default;
								return;
							} else if (opcode == 6129) {
								// TODO:
								int_stack[int_stack_ptr++] = 0;
								return;
							} else if (opcode == 6130) {// ground blending
								// TODO:
								int_stack[int_stack_ptr++] = 0;
								continue;
							} else if (opcode == 6131) {
								int_stack[int_stack_ptr++] = GLManager.opengl_mode ? 0 : 1;
								continue;
							} else if (opcode == 6135) {
								// TODO:
								int_stack[int_stack_ptr++] = 0;
								continue;
							} else if (opcode == 6142) {
								int_stack[int_stack_ptr++] = TimeTools.soundPreference2;
								continue;
							} else if (opcode != 6119) {
								break;
							}
							int_stack[int_stack_ptr++] = Class21.anInt342;
							continue;
						} else if (opcode < 6300) {
							if (opcode == 6200) {
								int_stack_ptr -= 2;
								Scene.fovx = (short) int_stack[int_stack_ptr];
								if (Scene.fovx <= 0) {
									Scene.fovx = (short) 256;
								}
								Scene.fovy = (short) int_stack[int_stack_ptr + 1];
								if (Scene.fovy <= 0) {
									Scene.fovy = (short) 205;
								}
								continue;
							} else if (opcode == 6201) {
								int_stack_ptr -= 2;
								Scene.zoomx = (short) int_stack[int_stack_ptr];
								if (Scene.zoomx <= 0) {
									Scene.zoomx = (short) 256;
								}
								Scene.zoomy = (short) int_stack[int_stack_ptr + 1];
								if (Scene.zoomy <= 0) {
									Scene.zoomy = (short) 320;
								}
								continue;
							} else if (opcode == 6202) {
								int_stack_ptr -= 4;
								Scene.fov_minx = (short) int_stack[int_stack_ptr];
								if (Scene.fov_minx <= 0) {
									Scene.fov_minx = (short) 1;
								}
								Scene.fov_maxx = (short) int_stack[int_stack_ptr + 1];
								if (Scene.fov_maxx <= 0) {
									Scene.fov_maxx = (short) 32767;
								} else if (Scene.fov_maxx < Scene.fov_minx) {
									Scene.fov_maxx = Scene.fov_minx;
								}
								Scene.fov_miny = (short) int_stack[int_stack_ptr + 2];
								if (Scene.fov_miny <= 0) {
									Scene.fov_miny = (short) 1;
								}
								Scene.fov_maxy = (short) int_stack[int_stack_ptr + 3];
								if (Scene.fov_maxy <= 0) {
									Scene.fov_maxy = (short) 32767;
								} else {
									if (Scene.fov_maxy < Scene.fov_miny) {
										Scene.fov_maxy = Scene.fov_miny;
									}
								}
								continue;
							} else if (opcode == 6203) {
								Scene.calculate_effective_size(0, 0, Scene.scene_component.layout_width, Scene.scene_component.layout_height, false);
								int_stack[int_stack_ptr++] = Scene.effective_width;
								int_stack[int_stack_ptr++] = Scene.effective_height;
								continue;
							} else if (opcode == 6204) {
								int_stack[int_stack_ptr++] = Scene.zoomx;
								int_stack[int_stack_ptr++] = Scene.zoomy;
								continue;
							} else if (opcode == 6205) {
								int_stack[int_stack_ptr++] = Scene.fovx;
								int_stack[int_stack_ptr++] = Scene.fovy;
								continue;
							} else {
								System.out.println("missing opcode: " + opcode);
								break;
							}
						} else if (opcode >= 6500 && opcode < 6600) {
							if (opcode == 6506) {
								int j37 = int_stack[--int_stack_ptr];
								LobbyWorld world = null;
								if (world != null) {

								} else {
									int_stack[int_stack_ptr++] = -1;
									str_stack[str_stack_ptr++] = RSString.create("");
									int_stack[int_stack_ptr++] = 0;
									str_stack[str_stack_ptr++] = RSString.create("");
									int_stack[int_stack_ptr++] = 0;
									int_stack[int_stack_ptr++] = 0;
									str_stack[str_stack_ptr++] = RSString.create("");
								}
								continue;
							} else {
								if (opcode != 6510) {
									break;
								}
								int_stack[int_stack_ptr++] = 0;
								continue;
							}
						} else if (opcode >= 6900 & opcode < 7000) {
							if (opcode == 6910) {
								int_stack[int_stack_ptr++] = 0;
								continue;
							} else if (opcode == 6900) {
								int_stack[int_stack_ptr++] = 0;
								continue;
							} else {
								throw new RuntimeException("missing opcode: " + opcode);
							}
						} else {
							throw new RuntimeException("missing opcode: " + opcode);
						}
					} else if (opcode < 100) {
						if (opcode == 0) {
							int_stack[int_stack_ptr++] = int_operands[ip];
							continue;
						}
						if (opcode == 1) {
							int i_11_ = int_operands[ip];
							int_stack[int_stack_ptr++] = GameClient.configs[i_11_];
							continue;
						}
						if (opcode == 2) {
							int i_12_ = int_operands[ip];
							GameClient.configs[i_12_] = int_stack[--int_stack_ptr];
							continue;
						}
						if (opcode == 3) {
							str_stack[str_stack_ptr++] = script.str_operands[ip];
							continue;
						}
						if (opcode == 6) {
							ip += int_operands[ip];
							continue;
						}
						if (opcode == 7) {
							int_stack_ptr -= 2;
							if (int_stack[int_stack_ptr] != int_stack[int_stack_ptr + 1]) {
								ip += int_operands[ip];
							}
							continue;
						}
						if (opcode == 8) {
							int_stack_ptr -= 2;
							if (int_stack[int_stack_ptr] == int_stack[int_stack_ptr + 1]) {
								ip += int_operands[ip];
							}
							continue;
						}
						if (opcode == 9) {
							int_stack_ptr -= 2;
							if (int_stack[int_stack_ptr] < int_stack[int_stack_ptr + 1]) {
								ip += int_operands[ip];
							}
							continue;
						}
						if (opcode == 10) {
							int_stack_ptr -= 2;
							if (int_stack[1 + int_stack_ptr] < int_stack[int_stack_ptr]) {
								ip += int_operands[ip];
							}
							continue;
						}
						if (opcode == 21) {
							if (call_stack_ptr != 0) {
								CS2CallFrame frame = call_stack[--call_stack_ptr];
								str_local_vars = frame.str_local_vars;
								int_local_vars = frame.int_local_vars;
								long_local_vars = frame.long_local_vars;
								ip = frame.ip;
								script = frame.cs2Script;
								opcodes = script.opcodes;
								int_operands = script.int_operands;
								continue;
							}
							return;
						}
						if (opcode == 25) {
							int i_13_ = int_operands[ip];
							int_stack[int_stack_ptr++] = Varbit.getConfigFileValue(i_13_);
							continue;
						}
						if (opcode == 27) {
							int i_14_ = int_operands[ip];
							Varbit.setConfigFileValue(0, i_14_, int_stack[--int_stack_ptr]);
							continue;
						}
						if (opcode == 31) {
							int_stack_ptr -= 2;
							if (int_stack[int_stack_ptr] <= int_stack[int_stack_ptr + 1]) {
								ip += int_operands[ip];
							}
							continue;
						}
						if (opcode == 32) {
							int_stack_ptr -= 2;
							if (int_stack[int_stack_ptr] >= int_stack[int_stack_ptr + 1]) {
								ip += int_operands[ip];
							}
							continue;
						}
						if (opcode == 33) {
							int_stack[int_stack_ptr++] = int_local_vars[int_operands[ip]];
							continue;
						}
						if (opcode == 34) {
							int_local_vars[int_operands[ip]] = int_stack[--int_stack_ptr];
							continue;
						}
						if (opcode == 35) {
							str_stack[str_stack_ptr++] = str_local_vars[int_operands[ip]];
							continue;
						}
						if (opcode == 36) {
							str_local_vars[int_operands[ip]] = str_stack[--str_stack_ptr];
							continue;
						}
						if (opcode == 37) {
							int i_15_ = int_operands[ip];
							str_stack_ptr -= i_15_;
							RSString class16 = RSString.joinRsStrings(str_stack, -32768, str_stack_ptr, i_15_);
							str_stack[str_stack_ptr++] = class16;
							continue;
						}
						if (opcode == 38) {
							int_stack_ptr--;
							continue;
						}
						if (opcode == 39) {
							str_stack_ptr--;
							continue;
						}
						if (opcode == 40) {
							int script_name = int_operands[ip];
							if (debug) {
								System.out.println("Calling script: " + script_name);
							}
							CS2ScriptDefinition target = CS2ScriptDefinition.getCS2ScriptDefinition(script_name, -17384);
							if (target == null) {
								throw new RuntimeException("Could not find script: " + script_name);
							}
							RSString[] ss = new RSString[target.string_var_count];
							int[] is = new int[target.int_var_count];
							long[] ls = new long[target.long_var_count];
							for (int index = 0; (target.int_arg_count ^ 0xffffffff) < (index ^ 0xffffffff); index++) {
								is[index] = int_stack[-target.int_arg_count + int_stack_ptr + index];
							}
							for (int index = 0; (target.string_arg_count ^ 0xffffffff) < (index ^ 0xffffffff); index++) {
								ss[index] = str_stack[index + str_stack_ptr - target.string_arg_count];
							}
							for (int index = 0; (target.long_arg_count ^ 0xffffffff) < (index ^ 0xffffffff); index++) {
								ls[index] = long_stack[index + long_stack_ptr - target.long_arg_count];
							}
							int_stack_ptr -= target.int_arg_count;
							str_stack_ptr -= target.string_arg_count;
							long_stack_ptr -= target.long_arg_count;
							CS2CallFrame class50 = new CS2CallFrame();
							class50.str_local_vars = str_local_vars;
							class50.int_local_vars = int_local_vars;
							class50.long_local_vars = long_local_vars;
							class50.cs2Script = script;
							class50.ip = ip;
							if (call_stack_ptr >= call_stack.length) {
								throw new RuntimeException("here141234");
							}
							call_stack[call_stack_ptr++] = class50;
							str_local_vars = ss;
							int_local_vars = is;
							long_local_vars = ls;
							ip = -1;
							script = target;
							int_operands = script.int_operands;
							opcodes = script.opcodes;
							continue;
						}
						if (opcode == 42) {
							int_stack[int_stack_ptr++] = int_global_vars[int_operands[ip]];
							continue;
						}
						if (opcode == 43) {
							int i_554_ = int_operands[ip];
							int_global_vars[i_554_] = int_stack[--int_stack_ptr];
							InterfaceUpdateQueue.insertGlobalHash(i_554_, 8807);
							continue;
						}
						if (opcode == 44) {
							int i_21_ = int_operands[ip] >> 16;
							int i_22_ = int_operands[ip] & 0xffff;
							int i_23_ = int_stack[--int_stack_ptr];
							if ((i_23_ ^ 0xffffffff) > -1 || i_23_ > 5000) {
								throw new RuntimeException("here423526");
							}
							int_array_size[i_21_] = i_23_;
							int i_24_ = -1;
							if (i_22_ == 105) {
								i_24_ = 0;
							}
							for (int i_25_ = 0; (i_25_ ^ 0xffffffff) > (i_23_ ^ 0xffffffff); i_25_++) {
								int_arrays[i_21_][i_25_] = i_24_;
							}
							continue;
						}
						if (opcode == 45) {
							int i_26_ = int_operands[ip];
							int i_27_ = int_stack[--int_stack_ptr];
							if (i_27_ < 0 || int_array_size[i_26_] <= i_27_) {
								throw new RuntimeException("here8535");
							}
							int_stack[int_stack_ptr++] = int_arrays[i_26_][i_27_];
							continue;
						}
						if (opcode == 46) {
							int_stack_ptr -= 2;
							int i_28_ = int_operands[ip];
							int i_29_ = int_stack[int_stack_ptr];
							if (i_29_ < 0 || (int_array_size[i_28_] ^ 0xffffffff) >= (i_29_ ^ 0xffffffff)) {
								throw new RuntimeException("here1942");
							}
							int_arrays[i_28_][i_29_] = int_stack[int_stack_ptr + 1];
							continue;
						}
						if (opcode == 47) {
							RSString class16 = StaticMethods.str_global_vars[int_operands[ip]];
							if (class16 == null) {
								class16 = FaceNormal.aClass16_629;
							}
							str_stack[str_stack_ptr++] = class16;
							continue;
						}
						if (opcode == 48) {
							int i_554_ = int_operands[ip];
							StaticMethods.str_global_vars[i_554_] = str_stack[--str_stack_ptr];
							InterfaceUpdateQueue.insertGlobalStringHash(19416, i_554_);
							continue;
						}
						if (opcode == 51) {
							HashTable var_oa = script.jump_tables[int_operands[ip]];
							IntegerNode integerNode = (IntegerNode) var_oa.get(int_stack[--int_stack_ptr]);
							if (integerNode != null) {
								ip += integerNode.value;
							}
							continue;
						}
						if (opcode == 86) {
							if (int_stack[--int_stack_ptr] == 1) {
								ip += int_operands[ip];
							}
							continue;
						}
						if (opcode == 87) {
							if (int_stack[--int_stack_ptr] == 0) {
								ip += int_operands[ip];
							}
							continue;
						}
					}
					boolean bool_30_;
					if (int_operands[ip] == 1) {
						bool_30_ = true;
					} else {
						bool_30_ = false;
					}
					if (opcode < 300) {
						if (opcode == 100 || opcode == 150) {
							// sub components support?
							int_stack_ptr -= 3;
							int i_31_ = int_stack[int_stack_ptr - -1];
							int i_32_ = int_stack[2 + int_stack_ptr];
							int i_33_ = int_stack[int_stack_ptr];
							if ((i_31_ ^ 0xffffffff) == -1) {
								throw new RuntimeException("her5212");
							}
							RSInterface class64 = RSInterface.getInterface(i_33_);
							if (class64.dynamic_components == null) {
								class64.dynamic_components = new RSInterface[1 + i_32_];
							}
							if ((class64.dynamic_components.length ^ 0xffffffff) >= (i_32_ ^ 0xffffffff)) {
								RSInterface[] class64s = new RSInterface[i_32_ - -1];
								for (int i_34_ = 0; (class64.dynamic_components.length ^ 0xffffffff) < (i_34_ ^ 0xffffffff); i_34_++) {
									class64s[i_34_] = class64.dynamic_components[i_34_];
								}
								class64.dynamic_components = class64s;
							}
							if ((i_32_ ^ 0xffffffff) < -1 && class64.dynamic_components[-1 + i_32_] == null) {
								throw new RuntimeException("Gap at:" + (i_32_ + -1));
							}
							RSInterface class64_35_ = new RSInterface();
							class64_35_.newer_interface = true;
							class64_35_.componentIndex = i_32_;
							class64_35_.parentId = class64_35_.uid = class64.uid;
							class64_35_.type = i_31_;
							class64_35_.version = class64.version;
							class64.dynamic_components[i_32_] = class64_35_;
							if (!bool_30_) {
								Class61.aClass64_959 = class64_35_;
							} else {
								StaticMethods.aClass64_2965 = class64_35_;
							}
							RSInterfaceList.setDirty(class64);
						} else if (opcode == 101 || opcode == 151) {
							// sub components support?
							RSInterface class64 = bool_30_ ? StaticMethods.aClass64_2965 : Class61.aClass64_959;
							if (class64.componentIndex == -1) {
								if (!bool_30_) {
									throw new RuntimeException("Tried to cc_delete static active-component!");
								}
								throw new RuntimeException("Tried to .cc_delete static .active-component!");
							}
							RSInterface class64_36_ = RSInterface.getInterface(class64.uid);
							class64_36_.dynamic_components[class64.componentIndex] = null;
							RSInterfaceList.setDirty(class64_36_);
						} else if (opcode == 102 || opcode == 152) {
							// sub components support?
							int interfaceHash = int_stack[--int_stack_ptr];
							RSInterface class64 = RSInterface.getInterface(interfaceHash);
							class64.dynamic_components = null;
							RSInterfaceList.setDirty(class64);
						} else if (opcode == 200) {
							int_stack_ptr -= 2;
							int dword_id = int_stack[int_stack_ptr];
							int idx = int_stack[1 + int_stack_ptr];
							RSInterface class64 = RSInterfaceList.get_dynamic_component(dword_id, idx, (byte) -127);
							if (class64 == null || (idx ^ 0xffffffff) == 0) {
								int_stack[int_stack_ptr++] = 0;
							} else {
								int_stack[int_stack_ptr++] = 1;
								if (bool_30_) {
									StaticMethods.aClass64_2965 = class64;
								} else {
									Class61.aClass64_959 = class64;
								}
							}
						} else {
							if (opcode != 201) {
								throw new RuntimeException("missing opcode: " + opcode);
							}
							int i_39_ = int_stack[--int_stack_ptr];
							RSInterface class64 = RSInterface.getInterface(i_39_);
							if (class64 == null) {
								int_stack[int_stack_ptr++] = 0;
							} else {
								int_stack[int_stack_ptr++] = 1;
								if (bool_30_) {
									StaticMethods.aClass64_2965 = class64;
								} else {
									Class61.aClass64_959 = class64;
								}
							}
						}
					} else if (opcode < 500) {
						if (opcode == 403) {
							int_stack_ptr -= 2;
							int i_40_ = int_stack[1 + int_stack_ptr];
							int i_41_ = int_stack[int_stack_ptr];
							if (i_41_ >= 7) {
								i_41_ -= 7;
							}
							GameClient.currentPlayer.appearance.updateLooks(i_40_, -1, i_41_);
						} else if (opcode == 404) {
							int_stack_ptr -= 2;
							int colorPart = int_stack[int_stack_ptr];
							int color = int_stack[int_stack_ptr - -1];
							GameClient.currentPlayer.appearance.updateAppearanceColor(colorPart, color);
						} else {
							if (opcode != 410) {
								throw new RuntimeException("missing opcode: " + opcode);
							}
							boolean isFemale = int_stack[--int_stack_ptr] != 0;
							GameClient.currentPlayer.appearance.updateGender(isFemale);
						}
					} else if ((opcode < 1000 || opcode >= 1100) && (opcode < 2000 || opcode >= 2100)) {
						if ((opcode < 1100 || opcode >= 1200) && (opcode < 2100 || opcode >= 2200)) {
							if ((opcode < 1200 || opcode >= 1300) && (opcode < 2200 || opcode >= 2300)) {
								if ((opcode < 1300 || opcode >= 1400) && (opcode < 2300 || opcode >= 2400)) {
									if (opcode >= 1400 && opcode < 1500 || opcode >= 2400 && opcode < 2500) {
										RSInterface class64;
										if (opcode >= 2000) {
											class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
											opcode -= 1000;
										} else {
											class64 = bool_30_ ? StaticMethods.aClass64_2965 : Class61.aClass64_959;
										}
										int[] is_45_ = null;
										RSString class16 = str_stack[--str_stack_ptr];
										if ((class16.length() ^ 0xffffffff) < -1 && class16.charAt(class16.length() + -1) == 89) {
											int i_46_ = int_stack[--int_stack_ptr];
											if (i_46_ > 0) {
												is_45_ = new int[i_46_];
												while ((i_46_-- ^ 0xffffffff) < -1) {
													is_45_[i_46_] = int_stack[--int_stack_ptr];
												}
											}
											class16 = class16.substring(-1 + class16.length(), 0);
										}
										Object[] objects_47_ = new Object[1 + class16.length()];
										for (int i_48_ = objects_47_.length + -1; i_48_ >= 1; i_48_--) {
											if (class16.charAt(i_48_ + -1) != 115) {
												objects_47_[i_48_] = new Integer(int_stack[--int_stack_ptr]);
											} else {
												objects_47_[i_48_] = str_stack[--str_stack_ptr];
											}
										}
										int i_49_ = int_stack[--int_stack_ptr];
										if (i_49_ != -1) {
											objects_47_[0] = new Integer(i_49_);
										} else {
											objects_47_ = null;
										}
										if (opcode == 1423) {
											class64.attachment_update_handler = objects_47_;
										}
										if (opcode == 1400) {
											class64.mouse_pressed_handler = objects_47_;
										}
										if (1427 == opcode) {
											class64.anObjectArray1604 = objects_47_;
										}
										if (1428 == opcode) {
											class64.anObjectArray161 = objects_47_;
											class64.anIntArray211 = is_45_;
										}
										if (1429 == opcode) {
											class64.anObjectArray162 = objects_47_;
											class64.anIntArray212 = is_45_;
										}
										if (opcode == 1402) {
											class64.mouse_released_handler = objects_47_;
										}
										if (opcode == 1418) {
											class64.chatbox_update_handler = objects_47_;
										}
										if (opcode == 1416) {
											class64.spellUsedOnItemListener = objects_47_;
										}
										if (opcode == 1407) {
											class64.varpUpdateListener = objects_47_;
											class64.varpListenerValues = is_45_;
										}
										if (opcode == 1419) {
											class64.key_press_handler = objects_47_;
										}
										if (opcode == 1420) {
											class64.anObjectArray1104 = objects_47_;
										}
										if (opcode == 1404) {
											class64.mouse_exit_handler = objects_47_;
										}
										if (opcode == 1403) {
											class64.mouse_enter_handler = objects_47_;
										}
										if (opcode == 1408) {
											class64.mainLoopListener = objects_47_;
										}
										if (opcode == 1424) {
											class64.anObjectArray1014 = objects_47_;
										}
										if (opcode == 1406) {
											class64.spellUsedListener = objects_47_;
										}
										if (opcode == 1425) {
											class64.anObjectArray1092 = objects_47_;
										}
										if (opcode == 1410) {
											class64.anObjectArray1066 = objects_47_;
										}
										if (opcode == 1405) {
											class64.anObjectArray1053 = objects_47_;
										}
										if (opcode == 1409) {
											class64.anObjectArray1116 = objects_47_;
										}
										if (opcode == 1411) {
											class64.mouse_dragged_handler = objects_47_;
										}
										if (opcode == 1401) {
											class64.mouse_drag_pass_handler = objects_47_;
										}
										class64.has_hooks = true;
										if (opcode == 1421) {
											class64.anObjectArray1137 = objects_47_;
										}
										if (opcode == 1422) {
											class64.update_timer_change_handler = objects_47_;
										}
										if (opcode == 1412) {
											class64.onMouseRepeatHook = objects_47_;
										}
										if (opcode == 1414) {
											class64.inventoryUpdateListener = objects_47_;
											class64.inventoryListenerValues = is_45_;
										}
										if (opcode == 1415) {
											class64.skillUpdateListener = objects_47_;
											class64.statTransmitList = is_45_;
										}
										if (opcode == 1417) {
											class64.mouseWheelListener = objects_47_;
										}
									} else if (opcode < 1600) {
										RSInterface class64 = bool_30_ ? StaticMethods.aClass64_2965 : Class61.aClass64_959;
										if (opcode == 1500) {
											int_stack[int_stack_ptr++] = class64.layout_x;
											continue;
										} else if (opcode == 1501) {
											int_stack[int_stack_ptr++] = class64.layout_y;
											continue;
										} else if (opcode == 1502) {
											int_stack[int_stack_ptr++] = class64.layout_width;
											continue;
										} else if (opcode == 1503) {
											int_stack[int_stack_ptr++] = class64.layout_height;
											continue;
										} else if (opcode == 1504) {
											int_stack[int_stack_ptr++] = class64.hidden ? 1 : 0;
											continue;
										} else if (opcode == 1505) {
											int_stack[int_stack_ptr++] = class64.parentId;
											continue;
										} else if (opcode == 1506) {
											RSInterface var_rtInterface_316_ = RSInterfaceList.get_parent(class64);
											int_stack[int_stack_ptr++] = var_rtInterface_316_ == null ? -1 : var_rtInterface_316_.uid;
											continue;
										} else if (opcode == 1507) {
											int_stack[int_stack_ptr++] = class64.color;
											continue;
										}
										throw new RuntimeException("missing opcode: " + opcode);
									} else if (opcode >= 1700) {
										if (opcode >= 1800) {
											if (opcode >= 1900) {
												if (opcode >= 2600) {
													if (opcode >= 2700) {
														if (opcode >= 2800) {
															if (opcode < 2900) {
																RSInterface class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
																if (opcode == 2800) {
																	int_stack[int_stack_ptr++] = Class47.getOptionMask(class64, 1).method101();
																} else if (opcode == 2801) {
																	int i_50_ = int_stack[--int_stack_ptr];
																	i_50_--;
																	if (class64.ops != null && class64.ops.length > i_50_ && class64.ops[i_50_] != null) {
																		str_stack[str_stack_ptr++] = class64.ops[i_50_];
																	} else {
																		str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																	}
																} else {
																	if (opcode != 2802) {
																		break;
																	}
																	if (class64.opBase != null) {
																		str_stack[str_stack_ptr++] = class64.opBase;
																	} else {
																		str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																	}
																}
															} else if (opcode < 3200) {
																if (opcode == 3100) {
																	RSString class16 = str_stack[--str_stack_ptr];
																	Class95.sendGameMessage(0, -1, class16, TimeTools.aClass16_1600);
																} else if (opcode == 3101) {
																	int_stack_ptr -= 2;
																	Player.setAnimation(0, int_stack[int_stack_ptr + 1], int_stack[int_stack_ptr], GameClient.currentPlayer);
																} else if (opcode == 3103) {
																	Packet.method444();
																} else if (opcode == 3104) {
																	int i_51_ = 0;
																	RSString class16 = str_stack[--str_stack_ptr];
																	if (class16.isInteger()) {
																		i_51_ = class16.toInteger();
																	}
																	GameClient.outBuffer.putOpcode(200);
																	GameClient.outBuffer.p4(i_51_);
																} else if (opcode == 3105) {
																	RSString class16 = str_stack[--str_stack_ptr];
																	GameClient.outBuffer.putOpcode(179);
																	GameClient.outBuffer.putLong(class16.toUsernameLong(), (byte) -108);
																} else if (opcode == 3106) {// long input text
																	RSString class16 = str_stack[--str_stack_ptr];
																	GameClient.outBuffer.putOpcode(106);
																	GameClient.outBuffer.p1(class16.length() + 1);
																	GameClient.outBuffer.putString(class16, -104);
																} else if (opcode == 3107) {
																	int i_52_ = int_stack[--int_stack_ptr];
																	RSString class16 = str_stack[--str_stack_ptr];
																	VertexNormal.method1457(i_52_, class16);
																} else if (opcode == 3108) {
																	int_stack_ptr -= 3;
																	int i_53_ = int_stack[int_stack_ptr - -1];
																	int i_54_ = int_stack[int_stack_ptr];
																	int i_55_ = int_stack[int_stack_ptr - -2];
																	RSInterface class64 = RSInterface.getInterface(i_55_);
																	StaticMethods.method273(class64, (byte) -114, i_53_, i_54_);
																} else if (opcode == 3109) {
																	RSInterface class64 = bool_30_ ? StaticMethods.aClass64_2965 : Class61.aClass64_959;
																	int_stack_ptr -= 2;
																	int i_56_ = int_stack[int_stack_ptr];
																	int i_57_ = int_stack[int_stack_ptr - -1];
																	StaticMethods.method273(class64, (byte) -105, i_57_, i_56_);
																} else if (opcode == 3111) {
																	int_stack_ptr -= 2;
																	int i_334_ = int_stack[int_stack_ptr];
																	int i_335_ = int_stack[int_stack_ptr + 1];
																	InterfaceNode var_dba = (InterfaceNode) Class36.anOa565.get(i_334_);
																	if (var_dba != null) {
																		GameShell.method27(var_dba.interfaceId != i_335_, var_dba, true);
																	}
																	HashTable.get(3, i_335_, i_334_, true);
																} else if (opcode == 3112) {
																	int_stack_ptr--;
																	int i_336_ = int_stack[int_stack_ptr];
																	InterfaceNode var_dba = (InterfaceNode) Class36.anOa565.get(i_336_);
																	if (var_dba != null && var_dba.walkable == 3) {
																		GameShell.method27(true, var_dba, true);
																	}
																} else if (opcode == 3116) {
																	int i_339_ = int_stack[--int_stack_ptr];
																	// TODO: Implement this server side, HSLDIALOG packet, the value is the HSL color in RuneScape format
																	GameClient.outBuffer.putOpcode(146);
																	GameClient.outBuffer.putShort(i_339_);
																} else {
																	if (opcode != 3110) {
																		break;
																	}
																	int i_58_ = int_stack[--int_stack_ptr];
																	GameClient.outBuffer.putOpcode(112);
																	GameClient.outBuffer.putShort(i_58_);
																}
															} else if (opcode >= 3300) {
																if (opcode >= 3400) {
																	if (opcode >= 3500) {
																		if (opcode >= 3700) {
																			if (opcode < 4000) {
																				if (opcode == 3903) {
																					int i_59_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = GrandExchangeOffer.offers[i_59_].method1443(-26124);
																				} else if (opcode == 3904) {
																					int i_60_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = GrandExchangeOffer.offers[i_60_].anInt1528;
																				} else if (opcode == 3905) {
																					int i_61_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = GrandExchangeOffer.offers[i_61_].anInt1537;
																				} else if (opcode == 3906) {
																					int i_62_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = GrandExchangeOffer.offers[i_62_].anInt1547;
																				} else if (opcode == 3907) {
																					int i_63_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = GrandExchangeOffer.offers[i_63_].anInt1531;
																				} else if (opcode == 3908) {
																					int i_64_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = GrandExchangeOffer.offers[i_64_].anInt1534;
																				} else if (opcode == 3910) {
																					int i_65_ = int_stack[--int_stack_ptr];
																					int i_66_ = GrandExchangeOffer.offers[i_65_].method1445(224);
																					int_stack[int_stack_ptr++] = (i_66_ ^ 0xffffffff) != -1 ? 0 : 1;
																				} else if (opcode == 3911) {
																					int i_67_ = int_stack[--int_stack_ptr];
																					int i_68_ = GrandExchangeOffer.offers[i_67_].method1445(224);
																					int_stack[int_stack_ptr++] = i_68_ == 2 ? 1 : 0;
																				} else if (opcode == 3912) {
																					int i_69_ = int_stack[--int_stack_ptr];
																					int i_70_ = GrandExchangeOffer.offers[i_69_].method1445(224);
																					int_stack[int_stack_ptr++] = i_70_ != 5 ? 0 : 1;
																				} else {
																					if (opcode != 3913) {
																						break;
																					}
																					int i_71_ = int_stack[--int_stack_ptr];
																					int i_72_ = GrandExchangeOffer.offers[i_71_].method1445(224);
																					int_stack[int_stack_ptr++] = i_72_ != 1 ? 0 : 1;
																				}
																			} else if (opcode >= 4100) {
																				if (opcode >= 4200) {
																					if (opcode >= 4300) {
																						if (opcode >= 4500 && opcode < 4600) {
																							if (opcode != 4500) {
																								break;
																							}
																							int_stack_ptr -= 2;
																							int i_95_ = int_stack[int_stack_ptr + 1];
																							int i_96_ = int_stack[int_stack_ptr];
																							ParamType paramType = ParamType.getParamType(i_95_);
																							if (!paramType.isString()) {
																								int_stack[int_stack_ptr++] = StructType.getStructType(i_96_).getIntValue(i_95_, paramType.defaultint);
																							} else {
																								str_stack[str_stack_ptr++] = StructType.getStructType(i_96_).getStringValue(i_95_, paramType.defaultstr);
																							}
																							continue;
																						} else if (opcode >= 5100) {
																							if (opcode < 5200) {
																								if (opcode == 5100) {
																									if (StaticMethods2.keysPressed[86]) {
																										int_stack[int_stack_ptr++] = 1;
																									} else {
																										int_stack[int_stack_ptr++] = 0;
																									}
																								} else if (opcode == 5101) {
																									if (!StaticMethods2.keysPressed[82]) {
																										int_stack[int_stack_ptr++] = 0;
																									} else {
																										int_stack[int_stack_ptr++] = 1;
																									}
																								} else {
																									if (opcode != 5102) {
																										break;
																									}
																									if (!StaticMethods2.keysPressed[81]) {
																										int_stack[int_stack_ptr++] = 0;
																									} else {
																										int_stack[int_stack_ptr++] = 1;
																									}
																								}
																							} else if (opcode >= 5300) {
																								if (opcode < 5400) {
																									if (opcode == 5300) {
																										int_stack_ptr -= 2;
																										int i_95_ = int_stack[int_stack_ptr];
																										int i_96_ = int_stack[int_stack_ptr + 1];
																										WindowMode.set_wm(3, false, i_95_, i_96_);
																										int_stack[int_stack_ptr++] = 0;
																										continue;
																									} else if (opcode == 5301) {
																										if (WindowMode.full_screen_frame != null) {
																											WindowMode.set_wm(GamePreferences.windowMode, false, -1, -1);
																										}
																										continue;
																									} else if (opcode == 5302) {
																										VideoMode[] var_videoModes = VideoMode.getVideoModes();
																										int_stack[int_stack_ptr++] = var_videoModes.length;
																										continue;
																									} else if (opcode == 5303) {
																										int i_97_ = int_stack[--int_stack_ptr];
																										VideoMode[] var_videoModes = VideoMode.getVideoModes();
																										int_stack[int_stack_ptr++] = var_videoModes[i_97_].width;
																										int_stack[int_stack_ptr++] = var_videoModes[i_97_].height;
																										continue;
																									} else if (opcode == 5305) {
																										int i_98_ = WindowMode.full_screen_width;
																										int i_99_ = WindowMode.full_screen_height;
																										int i_100_ = -1;
																										VideoMode[] var_videoModes = VideoMode.getVideoModes();
																										for (int i_101_ = 0; i_101_ < var_videoModes.length; i_101_++) {
																											VideoMode var_videoMode = var_videoModes[i_101_];
																											if (var_videoMode.width == i_98_ && var_videoMode.height == i_99_) {
																												i_100_ = i_101_;
																												break;
																											}
																										}
																										int_stack[int_stack_ptr++] = i_100_;
																										continue;
																									} else if (opcode == 5306) {
																										int_stack[int_stack_ptr++] = WindowMode.get_wm();
																										continue;
																									} else if (opcode == 5307) {
																										int i_102_ = int_stack[--int_stack_ptr];
																										if (i_102_ >= 1 && i_102_ <= 2) {
																											WindowMode.set_wm(i_102_, false, -1, -1);
																										}
																										continue;
																									} else if (opcode == 5308) {
																										int_stack[int_stack_ptr++] = 2;
																										continue;
																									} else if (opcode == 5309) {
																										int i_103_ = int_stack[--int_stack_ptr];
																										if (i_103_ >= 1 && i_103_ <= 2) {
																											GamePreferences.windowMode = i_103_;
																										}
																										continue;
																									} else if (opcode != 5304) {
																										break;
																									}
																									int_stack[int_stack_ptr++] = 0;
																									continue;
																								} else if (opcode < 5500) {
																									if (opcode == 5400) {
																										str_stack_ptr -= 2;
																										RSString class16 = str_stack[str_stack_ptr - -1];
																										int i_73_ = int_stack[--int_stack_ptr];
																										RSString class16_74_ = str_stack[str_stack_ptr];
																										GameClient.outBuffer.putOpcode(240);
																										GameClient.outBuffer.p1(1 + LobbyWorld.method1372(class16_74_) - -LobbyWorld.method1372(class16));
																										GameClient.outBuffer.putString(class16_74_, -126);
																										GameClient.outBuffer.putString(class16, -91);
																										GameClient.outBuffer.p1(i_73_);
																									} else if (opcode == 5408) {
																										int_stack[int_stack_ptr++] = 1;
																									} else if (opcode == 5419) {
																										RSString text = StaticMethods.empty_string;
																										if (ObjType.aRequest_3944 != null) {
																											text = StaticMethods2.method1354(12445 ^ 0x3062, ObjType.aRequest_3944.priority);
																											if (ObjType.aRequest_3944.result != null) {
																												byte abyte[] = null;
																												try {
																													abyte = ((String) ObjType.aRequest_3944.result).getBytes("ISO-8859-1");
																													;
																												} catch (UnsupportedEncodingException e) {
																													e.printStackTrace();
																												}
																												text = Packet.bufferToString(abyte, 0, abyte.length, 0);
																											}
																										}
																										str_stack[str_stack_ptr++] = text;
																									} else if (opcode == 5420) {
																										int_stack[int_stack_ptr++] = GameClient.gameSignlink.is_signed ? 0 : 1;
																									} else if (opcode == 5421) {
																										RSString string = str_stack[--str_stack_ptr];
																										boolean bool = int_stack[--int_stack_ptr] == 1;
																										// TODO open webpage
																									} else if (opcode == 5424) {
																										int_stack_ptr -= 11;
																									} else if (opcode == 5428) {
																										int_stack_ptr -= 2;
																										int i_120_ = int_stack[int_stack_ptr];
																										int i_121_ = int_stack[int_stack_ptr + 1];
																										int_stack[int_stack_ptr++] = 0;
																										continue;
																									} else {
																										if (opcode != 5401) {
																											break;
																										}
																										int_stack_ptr -= 2;
																										BZIPContext.aShortArray1339[int_stack[int_stack_ptr]] = (short) FloType.rgb2hsl_inner(int_stack[1 + int_stack_ptr]);
																										ObjType.clearItemModelCache();
																										StaticMethods.method890((byte) 117);
																										StringNode.method910(dummy);
																										UpdateServerThread.method94(0);
																										RSInterfaceList.setAllDirty(11980);
																									}
																								} else if (opcode < 5600) {
																									if (opcode == 5504) {
																										int_stack_ptr -= 2;
																									} else if (opcode != 5505) {
																										break;
																									}
																									int_stack[int_stack_ptr++] = (int) 1024.0F >> 3;
																								} else if (opcode < 5700) {
																									if (opcode != 5602) {
																										break;
																									}
																									if (LoginHandler.loginConnectionState == 0) {
																										loginOpcode = -2;
																									}
																								} else {
																									break;
																								}
																							} else if (opcode < 5300) {
																								if (opcode == 5200) {
																									WorldMap.method1479(int_stack[--int_stack_ptr], (byte) 56);
																									continue;
																								}
																								if (opcode == 5201) {
																									int_stack[int_stack_ptr++] = WorldMap.method571(-109);
																									continue;
																								}
																								if (opcode == 5202) {
																									WorldMap.method503((byte) -53, int_stack[--int_stack_ptr]);
																									continue;
																								}
																								if (opcode == 5203) {
																									WorldMap.method84(str_stack[--str_stack_ptr], -801);
																									continue;
																								}
																								if (opcode == 5204) {
																									str_stack[str_stack_ptr - 1] = WorldMap.method27(str_stack[str_stack_ptr - 1], true);
																									continue;
																								}
																								if (opcode == 5205) {
																									WorldMap.method138(str_stack[--str_stack_ptr], 0);
																									continue;
																								}
																								if (opcode == 5206) {
																									int i30 = int_stack[--int_stack_ptr];
																									WorldMapArea class3_sub28_sub3_4 = WorldMap.get_selectable_area(0x3fff & i30 >> 0x36628f6e, 0x3fff & i30);
																									if (class3_sub28_sub3_4 != null) {
																										str_stack[str_stack_ptr++] = class3_sub28_sub3_4.filename;
																									} else {
																										str_stack[str_stack_ptr++] = StaticMethods.empty_string;
																									}
																									continue;
																								}
																								if (opcode == 5207) {
																									WorldMapArea class3_sub28_sub3 = WorldMap.get_area_by_filename(str_stack[--str_stack_ptr]);
																									if (null != class3_sub28_sub3 && class3_sub28_sub3.areaname != null) {
																										str_stack[str_stack_ptr++] = class3_sub28_sub3.areaname;
																									} else {
																										str_stack[str_stack_ptr++] = StaticMethods.empty_string;
																									}
																									continue;
																								}
																								if (opcode == 5208) {
																									int_stack[int_stack_ptr++] = WorldMap.anInt817;
																									int_stack[int_stack_ptr++] = WorldMap.anInt410;
																									continue;
																								}
																								if (opcode == 5209) {
																									int_stack[int_stack_ptr++] = WorldMap.anInt3256 + WorldMap.anInt3536;
																									int_stack[int_stack_ptr++] = WorldMap.anInt65 + -WorldMap.anInt2251 + -1 + WorldMap.anInt1460;
																									continue;
																								}
																								if (opcode == 5210) {
																									WorldMapArea class3_sub28_sub3_1 = WorldMap.get_current_area();
																									if (class3_sub28_sub3_1 == null) {
																										int_stack[int_stack_ptr++] = 0;
																										int_stack[int_stack_ptr++] = 0;
																									} else {
																										int_stack[int_stack_ptr++] = class3_sub28_sub3_1.origin_x * 64;
																										int_stack[int_stack_ptr++] = 64 * class3_sub28_sub3_1.origin_y;
																									}
																									continue;
																								}
																								if (opcode == 5211) {
																									WorldMapArea class3_sub28_sub3_2 = WorldMap.get_current_area();
																									if (class3_sub28_sub3_2 != null) {
																										int_stack[int_stack_ptr++] = class3_sub28_sub3_2.maxx - class3_sub28_sub3_2.minx;
																										int_stack[int_stack_ptr++] = -class3_sub28_sub3_2.miny + class3_sub28_sub3_2.maxy;
																									} else {
																										int_stack[int_stack_ptr++] = 0;
																										int_stack[int_stack_ptr++] = 0;
																									}
																									continue;
																								}
																								if (opcode == 5212) {
																									int j30 = WorldMap.method1258((byte) -53);
																									int k70 = 0;
																									RSString class94_51;
																									if (~j30 != 0) {
																										class94_51 = WorldMap.current_labels.label_text[j30];
																										k70 = WorldMap.current_labels.method1791(j30);
																									} else {
																										class94_51 = StaticMethods.empty_string;
																									}
																									class94_51 = class94_51.replace(StaticMethods.aClass94_2765, StaticMethods.aClass94_2168);
																									str_stack[str_stack_ptr++] = class94_51;
																									int_stack[int_stack_ptr++] = k70;
																									continue;
																								}
																								if (opcode == 5213) {
																									int i71 = 0;
																									int k30 = WorldMap.method251(-1);
																									RSString class94_52;
																									if (0 != ~k30) {
																										class94_52 = WorldMap.current_labels.label_text[k30];
																										i71 = WorldMap.current_labels.method1791(k30);
																									} else {
																										class94_52 = StaticMethods.empty_string;
																									}
																									class94_52 = class94_52.replace(StaticMethods.aClass94_2765, StaticMethods.aClass94_2168);
																									str_stack[str_stack_ptr++] = class94_52;
																									int_stack[int_stack_ptr++] = i71;
																									continue;
																								}
																								if (opcode == 5214) {
																									int l30 = int_stack[--int_stack_ptr];
																									WorldMap.method565(0x3fff & l30 >> 0xa1b3276e, 0x3fff & l30);
																									continue;
																								}
																								if (opcode == 5215) {
																									int i31 = int_stack[--int_stack_ptr];
																									RSString class94_53 = str_stack[--str_stack_ptr];
																									boolean flag10 = false;
																									Queue class13 = WorldMap.get_selectable_areas(0x3fff & i31 >> 0xa4e408ae, 0x3fff & i31);
																									WorldMapArea class3_sub28_sub3_5 = (WorldMapArea) class13.get_first();
																									do {
																										if (class3_sub28_sub3_5 == null) {
																											break;
																										}
																										if (class3_sub28_sub3_5.filename.equalsIgnoreCase(class94_53)) {
																											flag10 = true;
																											break;
																										}
																										class3_sub28_sub3_5 = (WorldMapArea) class13.get_next();
																									} while (true);
																									if (!flag10) {
																										int_stack[int_stack_ptr++] = 0;
																									} else {
																										int_stack[int_stack_ptr++] = 1;
																									}
																									continue;
																								}
																								if (opcode == 5216) {
																									int j31 = int_stack[--int_stack_ptr];
																									WorldMap.switch_mapfunction_displayable(j31, 4);
																									continue;
																								}
																								if (opcode == 5217) {
																									int k31 = int_stack[--int_stack_ptr];
																									if (!WorldMap.is_mapfunction_displayable(k31, 20)) {
																										int_stack[int_stack_ptr++] = 0;
																									} else {
																										int_stack[int_stack_ptr++] = 1;
																									}
																									continue;
																								}
																								if (opcode == 5218) {
																									WorldMapArea class3_sub28_sub3_3 = WorldMap.get_current_area();
																									if (null != class3_sub28_sub3_3) {
																										int_stack[int_stack_ptr++] = class3_sub28_sub3_3.defaultzoom;
																									} else {
																										int_stack[int_stack_ptr++] = -1;
																									}
																									continue;
																								}
																								if (opcode == 5219) {
																									WorldMap.method915(str_stack[--str_stack_ptr], -1);
																									continue;
																								}
																								if (opcode == 5220) {
																									int_stack[int_stack_ptr++] = ~WorldMap.loading_percentage != -101 ? 0 : 1;
																									continue;
																								}
																								throw new RuntimeException("missing opcode: " + opcode);
																							}
																						} else if (opcode == 5000) {
																							int_stack[int_stack_ptr++] = LocResult.anInt3722;
																							continue;
																						} else if (opcode == 5001) {
																							int_stack_ptr -= 3;
																							LocResult.anInt3722 = int_stack[int_stack_ptr];
																							Class87_Sub3.anInt2820 = int_stack[1 + int_stack_ptr];
																							NPC.anInt4368 = int_stack[2 + int_stack_ptr];
																							GameClient.outBuffer.putOpcode(54);
																							GameClient.outBuffer.p1(LocResult.anInt3722);
																							GameClient.outBuffer.p1(Class87_Sub3.anInt2820);
																							GameClient.outBuffer.p1(NPC.anInt4368);
																						} else if (opcode == 5002) {
																							int_stack_ptr -= 2;
																							RSString class16 = str_stack[--str_stack_ptr];
																							int i_75_ = int_stack[int_stack_ptr];
																							int i_76_ = int_stack[1 + int_stack_ptr];
																							GameClient.outBuffer.putOpcode(206);
																							GameClient.outBuffer.putLong(class16.toUsernameLong(), (byte) -128);
																							GameClient.outBuffer.p1(-1 + i_75_);
																							GameClient.outBuffer.p1(i_76_);
																						} else if (opcode == 5003) {
																							int i_77_ = int_stack[--int_stack_ptr];
																							RSString class16 = null;
																							if (i_77_ < 100) {
																								class16 = CollisionMap.chatMessages[i_77_];
																							}
																							if (class16 == null) {
																								class16 = TimeTools.aClass16_1600;
																							}
																							str_stack[str_stack_ptr++] = class16;
																						} else if (opcode == 5004) {
																							int i_78_ = int_stack[--int_stack_ptr];
																							int i_79_ = -1;
																							if (i_78_ < 100 && CollisionMap.chatMessages[i_78_] != null) {
																								i_79_ = Queue.chatTypes[i_78_];
																							}
																							int_stack[int_stack_ptr++] = i_79_;
																						} else if (opcode == 5005) {
																							int_stack[int_stack_ptr++] = Class87_Sub3.anInt2820;
																						} else if (opcode == 5006) {
																							int i_10_ = int_stack[--int_stack_ptr];
																						} else if (opcode == 5008) {
																							RSString class16 = str_stack[--str_stack_ptr];
																							if (class16.startsWith(GroundObjEntity.aClass16_714)) {
																								Class53.processClientCommands(126, class16);
																							} else if (GameClient.rights != 0 || Class67.anInt1176 != 1 && StaticMethods.anInt3075 != 1) {
																								RSString class16_80_ = class16.toLowerCase();
																								int i_81_ = 0;
																								int i_82_ = 0;
																								if (!class16_80_.startsWith(StaticMethods.aClass16_2993)) {
																									if (!class16_80_.startsWith(Class31.aClass16_492)) {
																										if (!class16_80_.startsWith(Class4.aClass16_90)) {
																											if (class16_80_.startsWith(Js5.aClass16_1803)) {
																												i_82_ = 3;
																												class16 = class16.substring(Js5.aClass16_1803.length());
																											} else if (!class16_80_.startsWith(ModelList.aClass16_1430)) {
																												if (class16_80_.startsWith(StaticMethods.aClass16_3047)) {
																													class16 = class16.substring(StaticMethods.aClass16_3047.length());
																													i_82_ = 5;
																												} else if (!class16_80_.startsWith(StaticMethods.aClass16_3416)) {
																													if (class16_80_.startsWith(StaticMethods.aClass16_3253)) {
																														class16 = class16.substring(StaticMethods.aClass16_3253.length());
																														i_82_ = 7;
																													} else if (class16_80_.startsWith(StaticMethods2.aClass16_1421)) {
																														i_82_ = 8;
																														class16 = class16.substring(StaticMethods2.aClass16_1421.length());
																													} else if (!class16_80_.startsWith(StaticMethods.aClass16_1858)) {
																														if (class16_80_.startsWith(StaticMethods.aClass16_4483)) {
																															i_82_ = 10;
																															class16 = class16.substring(StaticMethods.aClass16_4483.length());
																														} else if (!class16_80_.startsWith(SoundEffects.aClass16_2059)) {
																															if (GameClient.getLanguage() != 0) {
																																if (class16_80_.startsWith(StaticMethods.aClass16_2982)) {
																																	class16 = class16.substring(StaticMethods.aClass16_2982.length());
																																	i_82_ = 0;
																																} else if (class16_80_.startsWith(Class31.aClass16_493)) {
																																	class16 = class16.substring(Class31.aClass16_493.length());
																																	i_82_ = 1;
																																} else if (!class16_80_.startsWith(Class4.aClass16_99)) {
																																	if (!class16_80_.startsWith(Js5.aClass16_1809)) {
																																		if (class16_80_.startsWith(ModelList.aClass16_1436)) {
																																			class16 = class16.substring(ModelList.aClass16_1436.length());
																																			i_82_ = 4;
																																		} else if (class16_80_.startsWith(StaticMethods.aClass16_3057)) {
																																			i_82_ = 5;
																																			class16 = class16.substring(StaticMethods.aClass16_3057.length());
																																		} else if (class16_80_.startsWith(StaticMethods.aClass16_3419)) {
																																			i_82_ = 6;
																																			class16 = class16.substring(StaticMethods.aClass16_3419.length());
																																		} else if (class16_80_.startsWith(StaticMethods.aClass16_3244)) {
																																			i_82_ = 7;
																																			class16 = class16.substring(StaticMethods.aClass16_3244.length());
																																		} else if (class16_80_.startsWith(StaticMethods2.aClass16_1423)) {
																																			i_82_ = 8;
																																			class16 = class16.substring(StaticMethods2.aClass16_1423.length());
																																		} else if (class16_80_.startsWith(StaticMethods.aClass16_1862)) {
																																			i_82_ = 9;
																																			class16 = class16.substring(StaticMethods.aClass16_1862.length());
																																		} else if (class16_80_.startsWith(StaticMethods.aClass16_4486)) {
																																			i_82_ = 10;
																																			class16 = class16.substring(StaticMethods.aClass16_4486.length());
																																		} else if (class16_80_.startsWith(SoundEffects.aClass16_2043)) {
																																			i_82_ = 11;
																																			class16 = class16.substring(SoundEffects.aClass16_2043.length());
																																		}
																																	} else {
																																		class16 = class16.substring(Js5.aClass16_1809.length());
																																		i_82_ = 3;
																																	}
																																} else {
																																	class16 = class16.substring(Class4.aClass16_99.length());
																																	i_82_ = 2;
																																}
																															}
																														} else {
																															i_82_ = 11;
																															class16 = class16.substring(SoundEffects.aClass16_2059.length());
																														}
																													} else {
																														i_82_ = 9;
																														class16 = class16.substring(StaticMethods.aClass16_1858.length());
																													}
																												} else {
																													i_82_ = 6;
																													class16 = class16.substring(StaticMethods.aClass16_3416.length());
																												}
																											} else {
																												i_82_ = 4;
																												class16 = class16.substring(ModelList.aClass16_1430.length());
																											}
																										} else {
																											i_82_ = 2;
																											class16 = class16.substring(Class4.aClass16_90.length());
																										}
																									} else {
																										i_82_ = 1;
																										class16 = class16.substring(Class31.aClass16_492.length());
																									}
																								} else {
																									i_82_ = 0;
																									class16 = class16.substring(StaticMethods.aClass16_2993.length());
																								}
																								class16_80_ = class16.toLowerCase();
																								if (!class16_80_.startsWith(StaticMethods.aClass16_3437)) {
																									if (class16_80_.startsWith(Class107.aClass16_1834)) {
																										i_81_ = 2;
																										class16 = class16.substring(Class107.aClass16_1834.length());
																									} else if (class16_80_.startsWith(Class48.aClass16_745)) {
																										class16 = class16.substring(Class48.aClass16_745.length());
																										i_81_ = 3;
																									} else if (!class16_80_.startsWith(FileSystem.aClass16_261)) {
																										if (!class16_80_.startsWith(Class42.aClass16_659)) {
																											if ((GameClient.getLanguage() ^ 0xffffffff) != -1) {
																												if (!class16_80_.startsWith(StaticMethods.aClass16_3440)) {
																													if (class16_80_.startsWith(Class107.aClass16_1839)) {
																														class16 = class16.substring(Class107.aClass16_1839.length());
																														i_81_ = 2;
																													} else if (!class16_80_.startsWith(Class48.aClass16_746)) {
																														if (class16_80_.startsWith(FileSystem.aClass16_251)) {
																															class16 = class16.substring(FileSystem.aClass16_251.length());
																															i_81_ = 4;
																														} else if (class16_80_.startsWith(Class42.aClass16_653)) {
																															i_81_ = 5;
																															class16 = class16.substring(Class42.aClass16_653.length());
																														}
																													} else {
																														class16 = class16.substring(Class48.aClass16_746.length());
																														i_81_ = 3;
																													}
																												} else {
																													i_81_ = 1;
																													class16 = class16.substring(StaticMethods.aClass16_3440.length());
																												}
																											}
																										} else {
																											class16 = class16.substring(Class42.aClass16_659.length());
																											i_81_ = 5;
																										}
																									} else {
																										i_81_ = 4;
																										class16 = class16.substring(FileSystem.aClass16_261.length());
																									}
																								} else {
																									i_81_ = 1;
																									class16 = class16.substring(StaticMethods.aClass16_3437.length());
																								}
																								GameClient.outBuffer.putOpcode(104);
																								GameClient.outBuffer.p1(0);
																								int i_83_ = GameClient.outBuffer.index;
																								GameClient.outBuffer.p1(i_82_);
																								GameClient.outBuffer.p1(i_81_);
																								Packet.method321(GameClient.outBuffer, class16, 0);
																								GameClient.outBuffer.putIndex((byte) 21, GameClient.outBuffer.index - i_83_);
																							}
																						} else if (opcode == 5009) {
																							str_stack_ptr -= 2;
																							RSString class16 = str_stack[str_stack_ptr];
																							RSString class16_84_ = str_stack[str_stack_ptr - -1];
																							if ((GameClient.rights ^ 0xffffffff) != -1 || Class67.anInt1176 != 1 && StaticMethods.anInt3075 != 1) {
																								GameClient.outBuffer.putOpcode(69);// send pm message.
																								GameClient.outBuffer.p1(0);
																								int i_85_ = GameClient.outBuffer.index;
																								GameClient.outBuffer.putLong(class16.toUsernameLong(), (byte) -112);
																								Packet.method321(GameClient.outBuffer, class16_84_, 0);
																								GameClient.outBuffer.putIndex((byte) 21, -i_85_ + GameClient.outBuffer.index);
																							}
																						} else if (opcode == 5010) {
																							int i_86_ = int_stack[--int_stack_ptr];
																							RSString class16 = null;
																							if (i_86_ < 100) {
																								class16 = UpdateServerNode.chatUsers[i_86_];
																							}
																							if (class16 == null) {
																								class16 = TimeTools.aClass16_1600;
																							}
																							str_stack[str_stack_ptr++] = class16;
																						} else if (opcode == 5011) {
																							int i_87_ = int_stack[--int_stack_ptr];
																							RSString class16 = null;
																							if (i_87_ < 100) {
																								class16 = Class23_Sub16.aClass16Array2357[i_87_];
																							}
																							if (class16 == null) {
																								class16 = TimeTools.aClass16_1600;
																							}
																							str_stack[str_stack_ptr++] = class16;
																						} else if (opcode == 5012) {
																							int i_88_ = int_stack[--int_stack_ptr];
																							int i_89_ = -1;
																							if (i_88_ < 100) {
																								i_89_ = StaticMethods2.chatIDS[i_88_];
																							}
																							int_stack[int_stack_ptr++] = i_89_;
																						} else if (opcode == 5015) {
																							RSString userDisplay;
																							if (GameClient.currentPlayer == null || GameClient.currentPlayer.username == null) {
																								userDisplay = LoginHandler.usernameField;
																							} else {
																								userDisplay = GameClient.currentPlayer.username;
																								if (GameClient.currentPlayer.titleUsername != null) {
																									userDisplay = GameClient.currentPlayer.titleUsername;
																								}
																							}
																							str_stack[str_stack_ptr++] = userDisplay;
																						} else if (opcode == 5016) {
																							int_stack[int_stack_ptr++] = NPC.anInt4368;
																						} else if (opcode == 5017) {
																							int_stack[int_stack_ptr++] = Class56.chatMessageCount;
																						} else if (opcode == 5019) {
																							int i_19_ = int_stack[--int_stack_ptr];
																							RSString string = RSString.create("");
																							if (Class23_Sub16.aClass16Array2357[i_19_] != null) {
																								string = Class23_Sub16.aClass16Array2357[i_19_];
																							}
																							str_stack[str_stack_ptr++] = string;
																						} else if (opcode == 5024) {
																							int i_22_ = int_stack[--int_stack_ptr];
																							int i_23_ = StringNode.anInt2473;
																							int_stack[int_stack_ptr++] = i_23_;
																						} else if (opcode == 5050) {
																							int quickchatid = int_stack[--int_stack_ptr];
																							str_stack[str_stack_ptr++] = QuickChatDefinition.getQuickChat(quickchatid).message;
																						} else if (opcode == 5051) {
																							int quickChatID = int_stack[--int_stack_ptr];
																							QuickChatDefinition quickChatDefinition = QuickChatDefinition.getQuickChat(quickChatID);
																							if (quickChatDefinition.options == null) {
																								int_stack[int_stack_ptr++] = 0;
																							} else {
																								int_stack[int_stack_ptr++] = quickChatDefinition.options.length;
																							}
																						} else if (opcode == 5052) {
																							int_stack_ptr -= 2;
																							int i_92_ = int_stack[int_stack_ptr];
																							int i_93_ = int_stack[1 + int_stack_ptr];
																							QuickChatDefinition quickChatDefinition = QuickChatDefinition.getQuickChat(i_92_);
																							int i_94_ = quickChatDefinition.options[i_93_];
																							int_stack[int_stack_ptr++] = i_94_;
																						} else if (opcode == 5053) {
																							int i_95_ = int_stack[--int_stack_ptr];
																							QuickChatDefinition quickChatDefinition = QuickChatDefinition.getQuickChat(i_95_);
																							if (quickChatDefinition.anIntArray4029 == null) {
																								int_stack[int_stack_ptr++] = 0;
																							} else {
																								int_stack[int_stack_ptr++] = quickChatDefinition.anIntArray4029.length;
																							}
																						} else if (opcode == 5054) {
																							int_stack_ptr -= 2;
																							int i_96_ = int_stack[int_stack_ptr];
																							int i_97_ = int_stack[int_stack_ptr - -1];
																							int_stack[int_stack_ptr++] = QuickChatDefinition.getQuickChat(i_96_).anIntArray4029[i_97_];
																						} else if (opcode == 5055) {
																							int i_98_ = int_stack[--int_stack_ptr];
																							str_stack[str_stack_ptr++] = NPC.getOtherQuickChat(i_98_, 1).method749((byte) 67);
																						} else if (opcode == 5056) {
																							int i_99_ = int_stack[--int_stack_ptr];
																							Class23_Sub13_Sub12 class23_sub13_sub12 = NPC.getOtherQuickChat(i_99_, 1);
																							if (class23_sub13_sub12.anIntArray3966 != null) {
																								int_stack[int_stack_ptr++] = class23_sub13_sub12.anIntArray3966.length;
																							} else {
																								int_stack[int_stack_ptr++] = 0;
																							}
																						} else if (opcode == 5057) {
																							int_stack_ptr -= 2;
																							int i_100_ = int_stack[int_stack_ptr];
																							int i_101_ = int_stack[int_stack_ptr - -1];
																							int_stack[int_stack_ptr++] = NPC.getOtherQuickChat(i_100_, 1).anIntArray3966[i_101_];
																						} else if (opcode == 5058) {
																							GroundObjEntity.aClass42_712 = new Class42();
																							GroundObjEntity.aClass42_712.chatFileID = int_stack[--int_stack_ptr];
																							GroundObjEntity.aClass42_712.aClass23_Sub13_Sub12_646 = NPC.getOtherQuickChat(GroundObjEntity.aClass42_712.chatFileID, 1);
																							GroundObjEntity.aClass42_712.anIntArray648 = new int[GroundObjEntity.aClass42_712.aClass23_Sub13_Sub12_646.method753(0)];
																						} else if (opcode == 5059) {
																							GameClient.outBuffer.putOpcode(246);
																							GameClient.outBuffer.p1(0);
																							int i_102_ = GameClient.outBuffer.index;
																							GameClient.outBuffer.p1(0);
																							GameClient.outBuffer.putShort(GroundObjEntity.aClass42_712.chatFileID);
																							GroundObjEntity.aClass42_712.aClass23_Sub13_Sub12_646.method750(GameClient.outBuffer, 4056, GroundObjEntity.aClass42_712.anIntArray648);
																							GameClient.outBuffer.putIndex((byte) 21, GameClient.outBuffer.index + -i_102_);
																						} else if (opcode == 5060) {
																							RSString class16 = str_stack[--str_stack_ptr];
																							GameClient.outBuffer.putOpcode(227);
																							GameClient.outBuffer.p1(0);
																							int i_103_ = GameClient.outBuffer.index;
																							GameClient.outBuffer.putLong(class16.toUsernameLong(), (byte) -123);
																							GameClient.outBuffer.putShort(GroundObjEntity.aClass42_712.chatFileID);
																							GroundObjEntity.aClass42_712.aClass23_Sub13_Sub12_646.method750(GameClient.outBuffer, 4056, GroundObjEntity.aClass42_712.anIntArray648);
																							GameClient.outBuffer.putIndex((byte) 21, -i_103_ + GameClient.outBuffer.index);
																						} else if (opcode == 5061) {
																							GameClient.outBuffer.putOpcode(246);
																							GameClient.outBuffer.p1(0);
																							int i_104_ = GameClient.outBuffer.index;
																							GameClient.outBuffer.p1(1);
																							GameClient.outBuffer.putShort(GroundObjEntity.aClass42_712.chatFileID);
																							GroundObjEntity.aClass42_712.aClass23_Sub13_Sub12_646.method750(GameClient.outBuffer, 4056, GroundObjEntity.aClass42_712.anIntArray648);
																							GameClient.outBuffer.putIndex((byte) 21, GameClient.outBuffer.index - i_104_);
																						} else if (opcode == 5062) {
																							int_stack_ptr -= 2;
																							int i_105_ = int_stack[1 + int_stack_ptr];
																							int i_106_ = int_stack[int_stack_ptr];
																							int_stack[int_stack_ptr++] = QuickChatDefinition.getQuickChat(i_106_).anIntArray4026[i_105_];
																						} else if (opcode == 5063) {
																							int_stack_ptr -= 2;
																							int i_107_ = int_stack[int_stack_ptr];
																							int i_108_ = int_stack[1 + int_stack_ptr];
																							int_stack[int_stack_ptr++] = QuickChatDefinition.getQuickChat(i_107_).anIntArray4017[i_108_];
																						} else if (opcode == 5064) {
																							int_stack_ptr -= 2;
																							int i_109_ = int_stack[int_stack_ptr];
																							int i_110_ = int_stack[int_stack_ptr + 1];
																							if (i_110_ != -1) {
																								int_stack[int_stack_ptr++] = QuickChatDefinition.getQuickChat(i_109_).method774(i_110_, (byte) -125);
																							} else {
																								int_stack[int_stack_ptr++] = -1;
																							}
																						} else if (opcode == 5065) {
																							int_stack_ptr -= 2;
																							int i_111_ = int_stack[int_stack_ptr];
																							int i_112_ = int_stack[int_stack_ptr - -1];
																							if ((i_112_ ^ 0xffffffff) != 0) {
																								int_stack[int_stack_ptr++] = QuickChatDefinition.getQuickChat(i_111_).method770(i_112_);
																							} else {
																								int_stack[int_stack_ptr++] = -1;
																							}
																						} else if (opcode == 5066) {
																							int i_113_ = int_stack[--int_stack_ptr];
																							int_stack[int_stack_ptr++] = NPC.getOtherQuickChat(i_113_, 1).method753(0);
																						} else if (opcode == 5067) {
																							int_stack_ptr -= 2;
																							int i_114_ = int_stack[1 + int_stack_ptr];
																							int i_115_ = int_stack[int_stack_ptr];
																							int i_116_ = NPC.getOtherQuickChat(i_115_, 1).method751(0, i_114_);
																							int_stack[int_stack_ptr++] = i_116_;
																						} else if (opcode == 5068) {
																							int_stack_ptr -= 2;
																							int i_117_ = int_stack[int_stack_ptr];
																							int i_118_ = int_stack[int_stack_ptr - -1];
																							GroundObjEntity.aClass42_712.anIntArray648[i_117_] = i_118_;
																						} else if (opcode == 5069) {
																							int_stack_ptr -= 2;
																							int i_119_ = int_stack[int_stack_ptr];
																							int i_120_ = int_stack[1 + int_stack_ptr];
																							GroundObjEntity.aClass42_712.anIntArray648[i_119_] = i_120_;
																						} else {
																							if (opcode != 5070) {
																								break;
																							}
																							int_stack_ptr -= 3;
																							int i_121_ = int_stack[int_stack_ptr];
																							int i_122_ = int_stack[1 + int_stack_ptr];
																							int i_123_ = int_stack[2 + int_stack_ptr];
																							Class23_Sub13_Sub12 class23_sub13_sub12 = NPC.getOtherQuickChat(i_121_, 1);
																							if (class23_sub13_sub12.method751(0, i_122_) != 0) {
																								throw new RuntimeException("bad command");
																							}
																							int_stack[int_stack_ptr++] = class23_sub13_sub12.method745(i_122_, -121, i_123_);
																						}
																					} else if (opcode == 4200) {
																						int i_124_ = int_stack[--int_stack_ptr];
																						str_stack[str_stack_ptr++] = ObjTypeList.list(i_124_).name;
																					} else if (opcode == 4201) {
																						int_stack_ptr -= 2;
																						int i_125_ = int_stack[int_stack_ptr];
																						int i_126_ = int_stack[1 + int_stack_ptr];
																						ObjType class23_sub13_sub11 = ObjTypeList.list(i_125_);
																						if (i_126_ < 1 || i_126_ > 5 || class23_sub13_sub11.groundActions[i_126_ - 1] == null) {
																							str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																						} else {
																							str_stack[str_stack_ptr++] = class23_sub13_sub11.groundActions[-1 + i_126_];
																						}
																					} else if (opcode == 4202) {
																						int_stack_ptr -= 2;
																						int i_127_ = int_stack[1 + int_stack_ptr];
																						int i_128_ = int_stack[int_stack_ptr];
																						ObjType itemDef = ObjTypeList.list(i_128_);
																						if (i_127_ < 1 || i_127_ > 5 || itemDef.actions[i_127_ + -1] == null) {
																							str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																						} else {
																							str_stack[str_stack_ptr++] = itemDef.actions[-1 + i_127_];
																						}
																					} else if (opcode == 4203) {
																						int i_129_ = int_stack[--int_stack_ptr];
																						int_stack[int_stack_ptr++] = ObjTypeList.list(i_129_).value;
																					} else if (opcode == 4204) {
																						int i_130_ = int_stack[--int_stack_ptr];
																						int_stack[int_stack_ptr++] = ObjTypeList.list(i_130_).stackable == 1 ? 1 : 0;
																					} else if (opcode == 4205) {
																						int i_131_ = int_stack[--int_stack_ptr];
																						ObjType class23_sub13_sub11 = ObjTypeList.list(i_131_);
																						if (class23_sub13_sub11.notedTemplateId == -1 && class23_sub13_sub11.noteInfoId >= 0) {
																							int_stack[int_stack_ptr++] = class23_sub13_sub11.noteInfoId;
																						} else {
																							int_stack[int_stack_ptr++] = i_131_;
																						}
																					} else if (opcode == 4206) {
																						int i_132_ = int_stack[--int_stack_ptr];
																						ObjType class23_sub13_sub11 = ObjTypeList.list(i_132_);
																						if (class23_sub13_sub11.notedTemplateId < 0 || (class23_sub13_sub11.noteInfoId ^ 0xffffffff) > -1) {
																							int_stack[int_stack_ptr++] = i_132_;
																						} else {
																							int_stack[int_stack_ptr++] = class23_sub13_sub11.noteInfoId;
																						}
																					} else if (opcode == 4207) {
																						int i_133_ = int_stack[--int_stack_ptr];
																						int_stack[int_stack_ptr++] = ObjTypeList.list(i_133_).membersItem ? 1 : 0;
																					} else if (opcode == 4208) {
																						int_stack_ptr -= 2;
																						int i_249_ = int_stack[int_stack_ptr];
																						int i_250_ = int_stack[int_stack_ptr + 1];
																						ParamType class335 = ParamType.getParamType(i_250_);
																						if (!class335.isString()) {
																							int_stack[int_stack_ptr++] = ObjTypeList.list(i_249_).getParam(i_250_, class335.defaultint);
																						} else {
																							str_stack[str_stack_ptr++] = ObjTypeList.list(i_249_).method1004(i_250_, class335.defaultstr);
																						}
																					} else if (opcode == 4209) {
																						int_stack_ptr -= 2;
																						// TODO:
																						int obj_id = int_stack[int_stack_ptr];
																						int op_idx = int_stack[int_stack_ptr + 1] - 1;
																						int_stack[int_stack_ptr++] = -1;
																					} else if (opcode == 4210) {
																						RSString class16 = str_stack[--str_stack_ptr];
																						int i_134_ = int_stack[--int_stack_ptr];
																						ObjType.method704(i_134_ == 1, class16, (byte) 115);
																						int_stack[int_stack_ptr++] = StaticMethods.result_buffer_size;
																					} else if (opcode == 4211) {
																						if (StaticMethods2.result_id_buffer != null && StaticMethods.result_buffer_size > NameHashTable.result_buffer_ptr) {
																							int_stack[int_stack_ptr++] = MathUtils.bitAnd(65535, StaticMethods2.result_id_buffer[NameHashTable.result_buffer_ptr++]);
																						} else {
																							int_stack[int_stack_ptr++] = -1;
																						}
																					} else if (opcode == 4212) {
																						NameHashTable.result_buffer_ptr = 0;
																					} else {
																						break;
																					}
																				} else if (opcode == 4100) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					int i_135_ = int_stack[--int_stack_ptr];
																					str_stack[str_stack_ptr++] = RSString.joinRsStrings(new RSString[] { class16, RSString.valueOf(i_135_) });
																				} else if (opcode == 4101) {
																					str_stack_ptr -= 2;
																					RSString class16 = str_stack[str_stack_ptr];
																					RSString class16_136_ = str_stack[str_stack_ptr + 1];
																					str_stack[str_stack_ptr++] = RSString.joinRsStrings(new RSString[] { class16, class16_136_ });
																				} else if (opcode == 4102) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					int i_137_ = int_stack[--int_stack_ptr];
																					str_stack[str_stack_ptr++] = RSString.joinRsStrings(new RSString[] { class16, RSString.method292(i_137_, true) });
																				} else if (opcode == 4103) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					str_stack[str_stack_ptr++] = class16.toLowerCase();
																				} else if (opcode == 4104) {
																					int i_138_ = int_stack[--int_stack_ptr];
																					long l = 86400000L * (i_138_ + 11745L);
																					Class31.aCalendar485.setTime(new Date(l));
																					int i_139_ = Class31.aCalendar485.get(5);
																					int i_140_ = Class31.aCalendar485.get(2);
																					int i_141_ = Class31.aCalendar485.get(1);
																					str_stack[str_stack_ptr++] = RSString.joinRsStrings(new RSString[] { RSString.valueOf(i_139_), StaticMethods2.aClass16_3888, Entity.aClass16Array2654[i_140_], StaticMethods2.aClass16_3888, RSString.valueOf(i_141_) });
																				} else if (opcode == 4105) {
																					str_stack_ptr -= 2;
																					RSString class16 = str_stack[str_stack_ptr - -1];
																					RSString class16_142_ = str_stack[str_stack_ptr];
																					if (GameClient.currentPlayer.appearance == null || !GameClient.currentPlayer.appearance.female) {
																						str_stack[str_stack_ptr++] = class16_142_;
																					} else {
																						str_stack[str_stack_ptr++] = class16;
																					}
																				} else if (opcode == 4106) {
																					int i_143_ = int_stack[--int_stack_ptr];
																					str_stack[str_stack_ptr++] = RSString.valueOf(i_143_);
																				} else if (opcode == 4107) {
																					str_stack_ptr -= 2;
																					int_stack[int_stack_ptr++] = str_stack[str_stack_ptr].method167(str_stack[1 + str_stack_ptr]);
																				} else if (opcode == 4108) {
																					int_stack_ptr -= 2;
																					RSString class16 = str_stack[--str_stack_ptr];
																					int i_144_ = int_stack[int_stack_ptr];
																					int font_id = int_stack[int_stack_ptr + 1];
																					int_stack[int_stack_ptr++] = FontCache.load_metrics(font_id).perform_word_warp(class16, i_144_);
																				} else if (opcode == 4109) {
																					int_stack_ptr -= 2;
																					int font_id = int_stack[int_stack_ptr - -1];
																					int i_147_ = int_stack[int_stack_ptr];
																					RSString class16 = str_stack[--str_stack_ptr];
																					int_stack[int_stack_ptr++] = FontCache.load_metrics(font_id).get_paragraph_width(class16, i_147_);
																				} else if (opcode == 4110) {
																					str_stack_ptr -= 2;
																					RSString class16 = str_stack[1 + str_stack_ptr];
																					RSString class16_148_ = str_stack[str_stack_ptr];
																					if (int_stack[--int_stack_ptr] == 1) {
																						str_stack[str_stack_ptr++] = class16_148_;
																					} else {
																						str_stack[str_stack_ptr++] = class16;
																					}
																				} else if (opcode == 4111) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					str_stack[str_stack_ptr++] = Font.escape_tags(class16);
																				} else if (opcode == 4112) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					int i_149_ = int_stack[--int_stack_ptr];
																					if (i_149_ == -1) {
																						throw new RuntimeException("null char");
																					}
																					str_stack[str_stack_ptr++] = class16.method165(i_149_);
																				} else if (opcode == 4113) {
																					int i_150_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = a((char) i_150_);
																				} else if (opcode == 4114) {
																					int i_151_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = !Keyboard.method966(i_151_, 122) ? 0 : 1;
																				} else if (opcode == 4115) {
																					int i_152_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = !StaticMethods2.method1524(i_152_, 0) ? 0 : 1;
																				} else if (opcode == 4116) {
																					int i_153_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = !StaticMethods2.method232(i_153_, 107) ? 0 : 1;
																				} else if (opcode == 4117) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					if (class16 == null) {
																						int_stack[int_stack_ptr++] = 0;
																					} else {
																						int_stack[int_stack_ptr++] = class16.length();
																					}
																				} else if (opcode == 4118) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					int_stack_ptr -= 2;
																					int i_154_ = int_stack[int_stack_ptr];
																					int i_155_ = int_stack[1 + int_stack_ptr];
																					str_stack[str_stack_ptr++] = class16.substring(i_155_, i_154_);
																				} else if (opcode == 4119) {
																					RSString class16 = str_stack[--str_stack_ptr];
																					RSString class16_156_ = RSString.create(class16.length(), 0);
																					boolean bool_157_ = false;
																					for (int i_158_ = 0; (class16.length() ^ 0xffffffff) < (i_158_ ^ 0xffffffff); i_158_++) {
																						int i_159_ = class16.charAt(i_158_);
																						if (i_159_ == 60) {
																							bool_157_ = true;
																						} else if (i_159_ != 62) {
																							if (!bool_157_) {
																								class16_156_.append(i_159_);
																							}
																						} else {
																							bool_157_ = false;
																						}
																					}
																					class16_156_.method178();
																					str_stack[str_stack_ptr++] = class16_156_;
																				} else if (opcode == 4120) {
																					int_stack_ptr -= 2;
																					RSString class16 = str_stack[--str_stack_ptr];
																					int i_160_ = int_stack[int_stack_ptr + 1];
																					int i_161_ = int_stack[int_stack_ptr];
																					int_stack[int_stack_ptr++] = class16.indexOf((byte) 127, i_160_, i_161_);
																				} else if (opcode == 4121) {
																					str_stack_ptr -= 2;
																					RSString class16 = str_stack[str_stack_ptr];
																					RSString class16_162_ = str_stack[1 + str_stack_ptr];
																					int i_163_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = class16.indexOf(i_163_, class16_162_);
																				} else if (opcode == 4122) {
																					int i_164_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = CS2ScriptDefinition.toLowerCase(i_164_, 192);
																				} else if (opcode == 4124) {
																					boolean bool_503_ = int_stack[--int_stack_ptr] != 0;
																					int i_504_ = int_stack[--int_stack_ptr];
																					str_stack[str_stack_ptr++] = RSString.hashToString(bool_503_, 0, 0, i_504_);
																				} else if (opcode == 4125) {
																					int font_id = int_stack[--int_stack_ptr];
																					RSString class16 = str_stack[--str_stack_ptr];
																					int_stack[int_stack_ptr++] = FontCache.load_metrics(font_id).calculate_width(class16);
																				} else {
																					if (opcode != 4123) {
																						break;
																					}
																					int i_165_ = int_stack[--int_stack_ptr];
																					int_stack[int_stack_ptr++] = GrandExchangeOffer.toUpperCase(i_165_, 6);
																				}
																			} else if (opcode == 4000) {
																				int_stack_ptr -= 2;
																				int i_166_ = int_stack[int_stack_ptr];
																				int i_167_ = int_stack[int_stack_ptr - -1];
																				int_stack[int_stack_ptr++] = i_166_ - -i_167_;
																			} else if (opcode == 4001) {
																				int_stack_ptr -= 2;
																				int i_168_ = int_stack[int_stack_ptr];
																				int i_169_ = int_stack[1 + int_stack_ptr];
																				int_stack[int_stack_ptr++] = i_168_ - i_169_;
																			} else if (opcode == 4002) {
																				int_stack_ptr -= 2;
																				int i_170_ = int_stack[int_stack_ptr + 1];
																				int i_171_ = int_stack[int_stack_ptr];
																				int_stack[int_stack_ptr++] = i_170_ * i_171_;
																			} else if (opcode == 4003) {
																				int_stack_ptr -= 2;
																				int i_172_ = int_stack[int_stack_ptr + 1];
																				int i_173_ = int_stack[int_stack_ptr];
																				int_stack[int_stack_ptr++] = i_173_ / i_172_;
																			} else if (opcode == 4004) {
																				int i_174_ = int_stack[--int_stack_ptr];
																				int_stack[int_stack_ptr++] = (int) (Math.random() * i_174_);
																			} else if (opcode == 4005) {
																				int i_175_ = int_stack[--int_stack_ptr];
																				int_stack[int_stack_ptr++] = (int) ((i_175_ - -1) * Math.random());
																			} else if (opcode == 4006) {
																				int_stack_ptr -= 5;
																				int i_176_ = int_stack[int_stack_ptr];
																				int i_177_ = int_stack[1 + int_stack_ptr];
																				int i_178_ = int_stack[int_stack_ptr + 4];
																				int i_179_ = int_stack[3 + int_stack_ptr];
																				int i_180_ = int_stack[int_stack_ptr - -2];
																				int_stack[int_stack_ptr++] = i_176_ - -((i_178_ - i_180_) * (i_177_ - i_176_) / (i_179_ + -i_180_));
																			} else if (opcode == 4007) {
																				int_stack_ptr -= 2;
																				long l = int_stack[1 + int_stack_ptr];
																				long l_181_ = int_stack[int_stack_ptr];
																				int_stack[int_stack_ptr++] = (int) (l * l_181_ / 100L + l_181_);
																			} else if (opcode == 4008) {
																				int_stack_ptr -= 2;
																				int i_182_ = int_stack[int_stack_ptr];
																				int i_183_ = int_stack[int_stack_ptr + 1];
																				int_stack[int_stack_ptr++] = MathUtils.doBitwiseOr(i_182_, 1 << i_183_);
																			} else if (opcode == 4009) {
																				int_stack_ptr -= 2;
																				int i_184_ = int_stack[int_stack_ptr + 1];
																				int i_185_ = int_stack[int_stack_ptr];
																				int_stack[int_stack_ptr++] = MathUtils.bitAnd(-1 - (1 << i_184_), i_185_);
																			} else if (opcode == 4010) {
																				int_stack_ptr -= 2;
																				int i_186_ = int_stack[int_stack_ptr];
																				int i_187_ = int_stack[1 + int_stack_ptr];
																				int_stack[int_stack_ptr++] = (MathUtils.bitAnd(i_186_, 1 << i_187_) ^ 0xffffffff) != -1 ? 1 : 0;
																			} else if (opcode == 4011) {
																				int_stack_ptr -= 2;
																				int i_188_ = int_stack[1 + int_stack_ptr];
																				int i_189_ = int_stack[int_stack_ptr];
																				int_stack[int_stack_ptr++] = i_189_ % i_188_;
																			} else if (opcode == 4012) {
																				int_stack_ptr -= 2;
																				int i_190_ = int_stack[int_stack_ptr];
																				int i_191_ = int_stack[1 + int_stack_ptr];
																				if ((i_190_ ^ 0xffffffff) == -1) {
																					int_stack[int_stack_ptr++] = 0;
																				} else {
																					int_stack[int_stack_ptr++] = (int) Math.pow(i_190_, i_191_);
																				}
																			} else if (opcode == 4013) {
																				int_stack_ptr -= 2;
																				int i_192_ = int_stack[int_stack_ptr];
																				int i_193_ = int_stack[1 + int_stack_ptr];
																				if (i_192_ == 0) {
																					int_stack[int_stack_ptr++] = 0;
																				} else if ((i_193_ ^ 0xffffffff) == -1) {
																					int_stack[int_stack_ptr++] = 2147483647;
																				} else {
																					int_stack[int_stack_ptr++] = (int) Math.pow(i_192_, 1.0 / i_193_);
																				}
																			} else if (opcode == 4014) {
																				int_stack_ptr -= 2;
																				int i_194_ = int_stack[1 + int_stack_ptr];
																				int i_195_ = int_stack[int_stack_ptr];
																				int_stack[int_stack_ptr++] = MathUtils.bitAnd(i_194_, i_195_);
																			} else if (opcode == 4015) {
																				int_stack_ptr -= 2;
																				int i_196_ = int_stack[int_stack_ptr];
																				int i_197_ = int_stack[int_stack_ptr + 1];
																				int_stack[int_stack_ptr++] = MathUtils.doBitwiseOr(i_197_, i_196_);
																			} else if (opcode == 4016) {
																				int_stack_ptr -= 2;
																				int i_198_ = int_stack[int_stack_ptr];
																				int i_199_ = int_stack[1 + int_stack_ptr];
																				int_stack[int_stack_ptr++] = (i_199_ ^ 0xffffffff) < (i_198_ ^ 0xffffffff) ? i_198_ : i_199_;
																			} else if (opcode == 4017) {
																				int_stack_ptr -= 2;
																				int i_200_ = int_stack[int_stack_ptr];
																				int i_201_ = int_stack[1 + int_stack_ptr];
																				int_stack[int_stack_ptr++] = (i_201_ ^ 0xffffffff) <= (i_200_ ^ 0xffffffff) ? i_201_ : i_200_;
																			} else if (opcode == 4020) {
																				int color = int_stack[--int_stack_ptr];
																				int_stack[int_stack_ptr++] = ColourUtil.hsvToRgbTable[ColourUtil.hsl_to_hsv(color) & 0xffff];
																			} else {
																				if (opcode != 4018) {
																					break;
																				}
																				int_stack_ptr -= 3;
																				long l = int_stack[int_stack_ptr];
																				long l_202_ = int_stack[int_stack_ptr - -1];
																				long l_203_ = int_stack[int_stack_ptr + 2];
																				int_stack[int_stack_ptr++] = (int) (l * l_203_ / l_202_);
																			}
																		} else if (opcode == 3600) {
																			if ((StaticMethods.anInt1357 ^ 0xffffffff) == -1) {
																				int_stack[int_stack_ptr++] = -2;
																			} else if (StaticMethods.anInt1357 != 1) {
																				int_stack[int_stack_ptr++] = Class45.friends_count;
																			} else {
																				int_stack[int_stack_ptr++] = -1;
																			}
																		} else if (opcode == 3601) {
																			int i_204_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.anInt1357 == 2 && i_204_ < Class45.friends_count) {
																				str_stack[str_stack_ptr++] = StaticMethods.friends_name[i_204_];
																				str_stack[str_stack_ptr++] = RSString.create("");
																			} else {
																				str_stack[str_stack_ptr++] = RSString.create("");
																				str_stack[str_stack_ptr++] = RSString.create("");
																			}
																		} else if (opcode == 3602) {
																			int i_205_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.anInt1357 != 2 || i_205_ >= Class45.friends_count) {
																				int_stack[int_stack_ptr++] = 0;
																			} else {
																				int_stack[int_stack_ptr++] = Class23_Sub10_Sub3.friends_worldid[i_205_];
																			}
																		} else if (opcode == 3603) {
																			int i_206_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.anInt1357 != 2 || (i_206_ ^ 0xffffffff) <= (Class45.friends_count ^ 0xffffffff)) {
																				int_stack[int_stack_ptr++] = 0;
																			} else {
																				int_stack[int_stack_ptr++] = Mouse.friends_rank[i_206_];
																			}
																		} else if (opcode == 3604) {
																			RSString class16 = str_stack[--str_stack_ptr];
																			int i_207_ = int_stack[--int_stack_ptr];
																			Class61.method1199(i_207_, false, class16);
																		} else if (opcode == 3605) {
																			RSString userDisplay = str_stack[--str_stack_ptr];
																			if (GameClient.currentPlayer != null && GameClient.currentPlayer.title != null && userDisplay.toString().contains(" ")) {
																				String[] tokens = userDisplay.toString().split(" ");
																				String s = "";
																				if (tokens.length > 1) {
																					for (int i = 1; i < tokens.length; i++) {
																						s += tokens[i];
																					}
																				}
																				userDisplay = RSString.create(s);
																			}
																			Class45.addFriend(userDisplay.toUsernameLong(), 32767);
																		} else if (opcode == 3606) {
																			RSString class16 = str_stack[--str_stack_ptr];
																			PlayerRelations.removeFriend(class16.toUsernameLong(), (byte) -111);
																		} else if (opcode == 3607) {
																			RSString userDisplay = str_stack[--str_stack_ptr];
																			if (GameClient.currentPlayer != null && GameClient.currentPlayer.title != null && userDisplay.toString().contains(" ")) {
																				String[] tokens = userDisplay.toString().split(" ");
																				String s = "";
																				if (tokens.length > 1) {
																					for (int i = 1; i < tokens.length; i++) {
																						s += tokens[i];
																					}
																				}
																				userDisplay = RSString.create(s);
																			}
																			ReflectionAntiCheat.addToIgnore(0, userDisplay.toUsernameLong());
																		} else if (opcode == 3608) {
																			RSString class16 = str_stack[--str_stack_ptr];
																			PlayerRelations.removeFromIgnore(-118, class16.toUsernameLong());
																		} else if (opcode == 3609) {
																			RSString class16 = str_stack[--str_stack_ptr];
																			if (class16.startsWith(GrandExchangeOffer.imgIcon0) || class16.startsWith(StaticMethods.imgIcon)) {
																				class16 = class16.substring(7);
																			}
																			int_stack[int_stack_ptr++] = !Class42.method1116(class16) ? 0 : 1;
																		} else if (opcode == 3610) {
																			int i_208_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.anInt1357 != 2 || Class45.friends_count <= i_208_) {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			} else {
																				str_stack[str_stack_ptr++] = Class87_Sub3.friends_worldname[i_208_];
																			}
																		} else if (opcode == 3611) {
																			if (StaticMethods.clanChatName != null) {
																				str_stack[str_stack_ptr++] = StaticMethods.clanChatName.method154();
																			} else {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			}
																		} else if (opcode == 3612) {
																			if (StaticMethods.clanChatName == null) {
																				int_stack[int_stack_ptr++] = 0;
																			} else {
																				int_stack[int_stack_ptr++] = DataBuffer.clanChatSize;
																			}
																		} else if (opcode == 3613) {
																			int i_209_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.clanChatName != null && (DataBuffer.clanChatSize ^ 0xffffffff) < (i_209_ ^ 0xffffffff)) {
																				str_stack[str_stack_ptr++] = NameHashTable.currentClanChatUsers[i_209_].username.method154();
																			} else {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			}
																		} else if (opcode == 3614) {
																			int i_210_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.clanChatName != null && i_210_ < DataBuffer.clanChatSize) {
																				int_stack[int_stack_ptr++] = NameHashTable.currentClanChatUsers[i_210_].userWorld;
																			} else {
																				int_stack[int_stack_ptr++] = 0;
																			}
																		} else if (opcode == 3615) {
																			int i_211_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.clanChatName != null && (i_211_ ^ 0xffffffff) > (DataBuffer.clanChatSize ^ 0xffffffff)) {
																				int_stack[int_stack_ptr++] = NameHashTable.currentClanChatUsers[i_211_].clanRights;
																			} else {
																				int_stack[int_stack_ptr++] = 0;
																			}
																		} else if (opcode == 3616) {
																			int_stack[int_stack_ptr++] = StaticMethods2.chatKickRights;
																		} else if (opcode == 3617) {
																			RSString class16 = str_stack[--str_stack_ptr];
																			PlayerRelations.kickUser(class16, !dummy);
																		} else if (opcode == 3618) {
																			int_stack[int_stack_ptr++] = StaticMethods.currentUserClanRights;
																		} else if (opcode == 3619) {
																			RSString class16 = str_stack[--str_stack_ptr];
																			InvType.method828((byte) 92, class16.toUsernameLong());
																		} else if (opcode == 3620) {
																			MonochromeImageCacheSlot.method869(-2415);
																		} else if (opcode == 3621) {
																			if ((StaticMethods.anInt1357 ^ 0xffffffff) == -1) {
																				int_stack[int_stack_ptr++] = -1;
																			} else {
																				int_stack[int_stack_ptr++] = PlayerRelations.ignoreListSize;
																			}
																		} else if (opcode == 3622) {
																			int i_212_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.anInt1357 != 0 && PlayerRelations.ignoreListSize > i_212_) {
																				str_stack[str_stack_ptr++] = WallObject.getStringFromLong(-1, PlayerRelations.ignoreList[i_212_]).method154();
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			} else {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			}
																		} else if (opcode == 3623) {
																			RSString class16 = str_stack[--str_stack_ptr];
																			if (class16.startsWith(GrandExchangeOffer.imgIcon0) || class16.startsWith(StaticMethods.imgIcon)) {
																				class16 = class16.substring(7);
																			}
																			int_stack[int_stack_ptr++] = FileSystem.method124(127, class16) ? 1 : 0;
																		} else if (opcode == 3624) {
																			int i_213_ = int_stack[--int_stack_ptr];
																			if (NameHashTable.currentClanChatUsers == null || i_213_ >= DataBuffer.clanChatSize || !NameHashTable.currentClanChatUsers[i_213_].username.equalsIgnoreCase(GameClient.currentPlayer.username)) {
																				int_stack[int_stack_ptr++] = 0;
																			} else {
																				int_stack[int_stack_ptr++] = 1;
																			}
																		} else if (opcode == 3625) {
																			if (StaticMethods.chatOwnerName != null) {
																				str_stack[str_stack_ptr++] = StaticMethods.chatOwnerName.method154();
																			} else {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			}
																		} else if (opcode == 3626) {
																			int i_214_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.clanChatName == null || i_214_ >= DataBuffer.clanChatSize) {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			} else {
																				str_stack[str_stack_ptr++] = NameHashTable.currentClanChatUsers[i_214_].worldString;
																			}
																		} else if (opcode == 3628) {
																			RSString class124 = str_stack[--str_stack_ptr];
																			if (class124.startsWith(GrandExchangeOffer.imgIcon0) || class124.startsWith(StaticMethods.imgIcon)) {
																				class124 = class124.substring(7);
																			}
																			int_stack[int_stack_ptr++] = EntityUpdating.method826(class124, 6);
																		} else if (opcode == 3632) {
																			int i_214_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.clanChatName != null && i_214_ < DataBuffer.clanChatSize) {
																				str_stack[str_stack_ptr++] = NameHashTable.currentClanChatUsers[i_214_].username;
																			} else {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			}
																		} else if (opcode == 3633) {
																			int i_214_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.anInt1357 != 0 && i_214_ < PlayerRelations.ignoreListSize) {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			} else {
																				str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
																			}
																		} else {
																			if (opcode != 3627) {
																				break;
																			}
																			int i_215_ = int_stack[--int_stack_ptr];
																			if (StaticMethods.anInt1357 == 2 && (i_215_ ^ 0xffffffff) > (Class45.friends_count ^ 0xffffffff)) {
																				int_stack[int_stack_ptr++] = StaticMethods2.aBooleanArray1741[i_215_] ? 1 : 0;
																			} else {
																				int_stack[int_stack_ptr++] = 0;
																			}
																		}
																	} else if (opcode == 3400) {// enum_string
																		int_stack_ptr -= 2;
																		int i_216_ = int_stack[int_stack_ptr - -1];
																		int i_217_ = int_stack[int_stack_ptr];
																		EnumType class23_sub13_sub13 = EnumType.list(i_217_);
																		str_stack[str_stack_ptr++] = class23_sub13_sub13.getString(i_216_);
																	} else if (opcode == 3408) {// enum_value
																		int_stack_ptr -= 4;
																		int key_type = int_stack[int_stack_ptr];
																		int const_file = int_stack[int_stack_ptr + 2];
																		int val_type = int_stack[int_stack_ptr + 1];
																		int key = int_stack[3 + int_stack_ptr];
																		EnumType _enum = EnumType.list(const_file);
																		if ((key_type ^ 0xffffffff) != (_enum.keyType ^ 0xffffffff) || _enum.valueType != val_type) {
																			throw new RuntimeException("Unmatching enum types: enum: " + const_file + ",, key_type: " + (int) _enum.keyType + ", expected: " + key_type + ", value_type: " + (+_enum.valueType) + ", expected: " + val_type);
																		}
																		if (val_type != 115) {
																			int_stack[int_stack_ptr++] = _enum.getInteger(key);
																		} else {
																			str_stack[str_stack_ptr++] = _enum.getString(key);
																		}
																	} else if (opcode == 3409) {// enum_hasoutput
																		int_stack_ptr -= 3;
																		int l49 = int_stack[int_stack_ptr - -1];
																		int key = int_stack[int_stack_ptr + 2];
																		int i13 = int_stack[int_stack_ptr];
																		if (l49 == -1) {
																			throw new RuntimeException("wdoihowhoiad");
																		}
																		EnumType class3_sub28_sub13_3 = EnumType.list(l49);
																		if (class3_sub28_sub13_3.valueType != i13) {
																			throw new RuntimeException("C3409-1");
																		}
																		int_stack[int_stack_ptr++] = class3_sub28_sub13_3.hasOutput(Integer.valueOf(key)) ? 1 : 0;
																	} else if (opcode == 3410) {// enum_hasoutput
																		int j13 = int_stack[--int_stack_ptr];
																		RSString string = str_stack[--str_stack_ptr];
																		if (j13 == -1) {
																			throw new RuntimeException("C3410-2");
																		}
																		EnumType class3_sub28_sub13_2 = EnumType.list(j13);
																		if (-116 != ~class3_sub28_sub13_2.valueType) {
																			throw new RuntimeException("C3410-1");
																		}
																		int_stack[int_stack_ptr++] = class3_sub28_sub13_2.hasOutput(string) ? 1 : 0;
																	} else if (opcode == 3411) {// enum_getoutputcount
																		int k13 = int_stack[--int_stack_ptr];
																		EnumType class3_sub28_sub13 = EnumType.list(k13);
																		int_stack[int_stack_ptr++] = class3_sub28_sub13.getSize();
																	} else {
																		break;
																	}
																} else if (opcode == 3300) {
																	int_stack[int_stack_ptr++] = GameClient.timer;
																} else if (opcode == 3301) {
																	int_stack_ptr -= 2;
																	int i_222_ = int_stack[int_stack_ptr];
																	int i_223_ = int_stack[int_stack_ptr - -1];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_slot_obj(i_223_, -1, i_222_);
																} else if (opcode == 3302) {
																	int_stack_ptr -= 2;
																	int i_224_ = int_stack[int_stack_ptr + 1];
																	int i_225_ = int_stack[int_stack_ptr];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_slot_total((byte) -18, i_224_, i_225_);
																} else if (opcode == 3303) {
																	int_stack_ptr -= 2;
																	int i_226_ = int_stack[1 + int_stack_ptr];
																	int i_227_ = int_stack[int_stack_ptr];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_item_count((byte) 99, i_227_, i_226_);
																} else if (opcode == 3304) {
																	int id = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = InvType.getInvType(id).size;
																} else if (opcode == 3305) {
																	int i_229_ = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = CacheFileWorker.skillLevels[i_229_];
																} else if (opcode == 3306) {
																	int i_230_ = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = ReflectionRequest.anIntArray2482[i_230_];
																} else if (opcode == 3307) {
																	int i_231_ = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = PlayerAppearance.skillExperience[i_231_];
																} else if (opcode == 3308) {
																	int i_232_ = ObjType.localHeight;
																	int i_233_ = (GameClient.currentPlayer.bound_extents_x >> 7) - -MapLoader.region_aboslute_z;
																	int i_234_ = (GameClient.currentPlayer.bound_extents_z >> 7) + MapLoader.region_aboslute_x;
																	int_stack[int_stack_ptr++] = (i_233_ << 14) + (i_232_ << 28) - -i_234_;
																} else if (opcode == 3309) {
																	int i_235_ = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = MathUtils.bitAnd(268433290, i_235_) >> 14;
																} else if (opcode == 3310) {
																	int i_236_ = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = i_236_ >> 28;
																} else if (opcode == 3311) {
																	int i_237_ = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = MathUtils.bitAnd(i_237_, 16383);
																} else if (opcode == 3312) {
																	int_stack[int_stack_ptr++] = !GameClient.isMembers() ? 0 : 1;
																} else if (opcode == 3313) {
																	int_stack_ptr -= 2;
																	int i_238_ = int_stack[int_stack_ptr];
																	int i_239_ = int_stack[1 + int_stack_ptr];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_slot_obj(i_239_, -1, i_238_);
																} else if (opcode == 3314) {
																	int_stack_ptr -= 2;
																	int i_240_ = int_stack[int_stack_ptr];
																	int i_241_ = int_stack[int_stack_ptr - -1];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_slot_total((byte) -18, i_241_, i_240_);
																} else if (opcode == 3315) {
																	int_stack_ptr -= 2;
																	int i_242_ = int_stack[int_stack_ptr - -1];
																	int i_243_ = int_stack[int_stack_ptr];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_item_count((byte) 47, i_243_, i_242_);
																} else if (opcode == 3316) {
																	if (GameClient.rights < 2) {
																		int_stack[int_stack_ptr++] = 0;
																	} else {
																		int_stack[int_stack_ptr++] = GameClient.rights;
																	}
																} else if (opcode == 3317) {
																	int_stack[int_stack_ptr++] = Queuable.systemUpdateTime;
																} else if (opcode == 3318) {
																	int_stack[int_stack_ptr++] = GameClient.getWorldId();
																} else if (opcode == 3321) {
																	int_stack[int_stack_ptr++] = SpotType.runEnergy;
																} else if (opcode == 3322) {
																	int_stack[int_stack_ptr++] = Class36.anInt569;
																} else if (opcode == 3323) {
																	if (StaticMethods.anInt3470 < 5 || StaticMethods.anInt3470 > 9) {
																		int_stack[int_stack_ptr++] = 0;
																	} else {
																		int_stack[int_stack_ptr++] = 1;
																	}
																} else if (opcode == 3324) {
																	if (StaticMethods.anInt3470 < 5 || StaticMethods.anInt3470 > 9) {
																		int_stack[int_stack_ptr++] = 0;
																	} else {
																		int_stack[int_stack_ptr++] = StaticMethods.anInt3470;
																	}
																} else if (opcode == 3325) {
																	if ((Class30.anInt478 ^ 0xffffffff) < -1) {
																		int_stack[int_stack_ptr++] = 1;
																	} else {
																		int_stack[int_stack_ptr++] = 0;
																	}
																} else if (opcode == 3326) {
																	int_stack[int_stack_ptr++] = GameClient.currentPlayer.combatLevel;
																} else if (opcode == 3327) {
																	int_stack[int_stack_ptr++] = !GameClient.currentPlayer.appearance.female ? 0 : 1;
																} else if (opcode == 3328) {
																	int_stack[int_stack_ptr++] = Class67.anInt1176;
																} else if (opcode == 3330) {
																	int i_66_ = int_stack[--int_stack_ptr];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_free_slots(i_66_, false);
																} else if (opcode == 3331) {
																	int_stack_ptr -= 2;
																	int i_67_ = int_stack[int_stack_ptr];
																	int i_68_ = int_stack[1 + int_stack_ptr];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_total_param(i_67_, false, i_68_, false);
																} else if (opcode == 3332) {
																	int_stack_ptr -= 2;
																	int i_67_ = int_stack[int_stack_ptr];
																	int i_68_ = int_stack[1 + int_stack_ptr];
																	int_stack[int_stack_ptr++] = ClientInventoryList.get_total_param(i_67_, false, i_68_, true);
																} else if (opcode == 3333) {
																	int_stack[int_stack_ptr++] = Configurations.SUB_BUILD;
																} else if (3335 == opcode) {
																	int_stack[int_stack_ptr++] = GameClient.language;
																} else if (3336 == opcode) {
																	int_stack_ptr -= 4;
																	int i49 = int_stack[int_stack_ptr - -1];
																	int j12 = int_stack[int_stack_ptr];
																	j12 += i49 << 14;
																	int k76 = int_stack[3 + int_stack_ptr];
																	int j68 = int_stack[2 + int_stack_ptr];
																	j12 += j68 << 28;
																	j12 += k76;
																	int_stack[int_stack_ptr++] = j12;
																} else if (3337 == opcode) {
																	int_stack[int_stack_ptr++] = GameClient.affId;
																} else if (3339 == opcode) {
																	int_stack[int_stack_ptr++] = 0;// TODO: profile cpu
																} else if (3340 == opcode) {
																	int_stack[int_stack_ptr++] = GameShell.isFocusIn() ? 1 : 0;
																} else {
																	if (opcode != 3329) {
																		break;
																	}
																	int_stack[int_stack_ptr++] = StaticMethods.anInt3075;
																}
															} else if (opcode == 3200) {
																int_stack_ptr -= 3;
																Class21.addSoundStore1(-121, int_stack[int_stack_ptr], 256, 255, int_stack[int_stack_ptr + 1], int_stack[int_stack_ptr + 2]);
															} else if (opcode == 3201) {
																Class23_Sub13_Sub2.playMusic(int_stack[--int_stack_ptr], (byte) 110);
															} else if (opcode == 3202) {
																int_stack_ptr -= 2;
																Class36.method990((byte) 110, int_stack[1 + int_stack_ptr], int_stack[int_stack_ptr]);
															} else if (opcode == 3203) {
																int_stack_ptr -= 4;
																Class21.addSoundStore1(-102, int_stack[int_stack_ptr], 256, int_stack[int_stack_ptr + 3], int_stack[int_stack_ptr + 1], int_stack[int_stack_ptr + 2]);
															} else if (opcode == 3206) {
																int_stack_ptr -= 4;
																Class21.addSoundStore2(int_stack[int_stack_ptr + 3], false, 256, int_stack[int_stack_ptr + 2], int_stack[int_stack_ptr], 112, int_stack[int_stack_ptr + 1]);
															} else if (opcode == 3207) {
																int_stack_ptr -= 4;
																Class21.addSoundStore2(int_stack[int_stack_ptr + 3], true, 256, int_stack[int_stack_ptr + 2], int_stack[int_stack_ptr], 85, int_stack[int_stack_ptr + 1]);
															} else if (opcode == 3208) {
																int_stack_ptr -= 5;
																Class21.addSoundStore1(-80, int_stack[int_stack_ptr], int_stack[int_stack_ptr + 4], int_stack[int_stack_ptr + 3], int_stack[int_stack_ptr + 1], int_stack[int_stack_ptr + 2]);
															} else {
																if (opcode != 3209) {
																	break;
																}
																int_stack_ptr -= 5;
																Class21.addSoundStore2(int_stack[int_stack_ptr + 3], false, int_stack[int_stack_ptr + 4], int_stack[int_stack_ptr + 2], int_stack[int_stack_ptr], 106, int_stack[int_stack_ptr + 1]);
															}
														} else if (opcode == 2700) {
															RSInterface class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
															int_stack[int_stack_ptr++] = class64.objid;
														} else if (opcode == 2701) {
															RSInterface class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
															if ((class64.objid ^ 0xffffffff) != 0) {
																int_stack[int_stack_ptr++] = class64.shownItemAmount;
															} else {
																int_stack[int_stack_ptr++] = 0;
															}
														} else if (opcode == 2702) {
															int i_244_ = int_stack[--int_stack_ptr];
															InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(i_244_);
															if (class23_sub25 == null) {
																int_stack[int_stack_ptr++] = 0;
															} else {
																int_stack[int_stack_ptr++] = 1;
															}
														} else if (opcode == 2703) {
															RSInterface class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
															if (class64.dynamic_components == null) {
																int_stack[int_stack_ptr++] = 0;
															} else {
																int i_245_ = class64.dynamic_components.length;
																for (int i_246_ = 0; (i_246_ ^ 0xffffffff) > (class64.dynamic_components.length ^ 0xffffffff); i_246_++) {
																	if (class64.dynamic_components[i_246_] == null) {
																		i_245_ = i_246_;
																		break;
																	}
																}
																int_stack[int_stack_ptr++] = i_245_;
															}
														} else {
															if (opcode != 2704 && opcode != 2705) {
																break;
															}
															int_stack_ptr -= 2;
															int i_247_ = int_stack[int_stack_ptr];
															int i_248_ = int_stack[int_stack_ptr - -1];
															InterfaceNode class23_sub25 = (InterfaceNode) Class36.anOa565.get(i_247_);
															if (class23_sub25 != null && (i_248_ ^ 0xffffffff) == (class23_sub25.interfaceId ^ 0xffffffff)) {
																int_stack[int_stack_ptr++] = 1;
															} else {
																int_stack[int_stack_ptr++] = 0;
															}
														}
													} else {
														RSInterface class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
														if (opcode == 2600) {
															int_stack[int_stack_ptr++] = class64.scroll_x;
														} else if (opcode == 2601) {
															int_stack[int_stack_ptr++] = class64.scroll_y;
														} else if (opcode == 2602) {
															str_stack[str_stack_ptr++] = class64.default_text;
														} else if (opcode == 2603) {
															int_stack[int_stack_ptr++] = class64.scroll_width;
														} else if (opcode == 2604) {
															int_stack[int_stack_ptr++] = class64.scroll_height;
														} else if (opcode == 2605) {
															int_stack[int_stack_ptr++] = class64.media_zoom;
														} else if (opcode == 2606) {
															int_stack[int_stack_ptr++] = class64.media_xangle;
														} else if (opcode == 2607) {
															int_stack[int_stack_ptr++] = class64.media_zangle;
														} else if (opcode == 2608) {
															int_stack[int_stack_ptr++] = class64.media_yangle;
														} else if (opcode == 2610) {
															int_stack[int_stack_ptr++] = class64.anInt258;
														} else if (opcode == 2611) {
															int_stack[int_stack_ptr++] = class64.anInt264;
														} else if (opcode == 2612) {
															int_stack[int_stack_ptr++] = class64.graphicid;
														} else if (opcode == 2613) {
															int_stack[int_stack_ptr++] = class64.angle2d;
														} else if (opcode == 2614) {
															int_stack[int_stack_ptr++] = class64.media_type == 1 ? class64.media_id : -1;
															;
														} else {
															if (opcode != 2609) {
																break;
															}
															int_stack[int_stack_ptr++] = class64.transparency;
														}
													}
												} else {
													RSInterface class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
													if (opcode == 2500) {
														int_stack[int_stack_ptr++] = class64.layout_x;
													} else if (opcode == 2501) {
														int_stack[int_stack_ptr++] = class64.layout_y;
													} else if (opcode == 2502) {
														int_stack[int_stack_ptr++] = class64.layout_width;
													} else if (opcode == 2503) {
														int_stack[int_stack_ptr++] = class64.layout_height;
													} else if (opcode == 2504) {
														int_stack[int_stack_ptr++] = class64.hidden ? 1 : 0;
													} else {
														if (opcode != 2505) {
															break;
														}
														int_stack[int_stack_ptr++] = class64.parentId;
													}
												}
											} else {
												RSInterface class64 = !bool_30_ ? Class61.aClass64_959 : StaticMethods.aClass64_2965;
												if (opcode == 1800) {
													int_stack[int_stack_ptr++] = Class47.getOptionMask(class64, 1).method101(); // BZIPContext.method1313(121, Class47.getOptionMask(class64, 120).anInt2452);
												} else if (opcode == 1801) {
													int i_249_ = int_stack[--int_stack_ptr];
													i_249_--;
													if (class64.ops != null && class64.ops.length > i_249_ && class64.ops[i_249_] != null) {
														str_stack[str_stack_ptr++] = class64.ops[i_249_];
													} else {
														str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
													}
												} else {
													if (opcode != 1802) {
														break;
													}
													if (class64.opBase == null) {
														str_stack[str_stack_ptr++] = TimeTools.aClass16_1600;
													} else {
														str_stack[str_stack_ptr++] = class64.opBase;
													}
												}
											}
										} else {
											RSInterface class64 = bool_30_ ? StaticMethods.aClass64_2965 : Class61.aClass64_959;
											if (opcode == 1700) {
												int_stack[int_stack_ptr++] = class64.objid;
											} else if (opcode == 1701) {
												if ((class64.objid ^ 0xffffffff) != 0) {
													int_stack[int_stack_ptr++] = class64.shownItemAmount;
												} else {
													int_stack[int_stack_ptr++] = 0;
												}
											} else {
												if (opcode != 1702) {
													break;
												}
												int_stack[int_stack_ptr++] = class64.componentIndex;
											}
										}
									} else {
										RSInterface class64 = !bool_30_ ? Class61.aClass64_959 : StaticMethods.aClass64_2965;
										if (opcode == 1600) {
											int_stack[int_stack_ptr++] = class64.scroll_x;
										} else if (opcode == 1601) {
											int_stack[int_stack_ptr++] = class64.scroll_y;
										} else if (opcode == 1602) {
											str_stack[str_stack_ptr++] = class64.default_text;
										} else if (opcode == 1603) {
											int_stack[int_stack_ptr++] = class64.scroll_width;
										} else if (opcode == 1604) {
											int_stack[int_stack_ptr++] = class64.scroll_height;
										} else if (opcode == 1605) {
											int_stack[int_stack_ptr++] = class64.media_zoom;
										} else if (opcode == 1606) {
											int_stack[int_stack_ptr++] = class64.media_xangle;
										} else if (opcode == 1607) {
											int_stack[int_stack_ptr++] = class64.media_zangle;
										} else if (opcode == 1608) {
											int_stack[int_stack_ptr++] = class64.media_yangle;
										} else if (opcode == 1610) {
											int_stack[int_stack_ptr++] = class64.anInt258;
										} else if (opcode == 1611) {
											int_stack[int_stack_ptr++] = class64.anInt264;
											;
										} else if (opcode == 1612) {
											int_stack[int_stack_ptr++] = class64.graphicid;
										} else if (opcode == 1613) {
											int id = int_stack[--int_stack_ptr];
											ParamType var_paramType = ParamType.getParamType(id);
											if (var_paramType.isString()) {
												str_stack[str_stack_ptr++] = class64.get_param(id, var_paramType.defaultstr);
											} else {
												int_stack[int_stack_ptr++] = class64.get_param(id, var_paramType.defaultint);
												return;
											}
											return;
										} else if (opcode == 1614) {
											int_stack[int_stack_ptr++] = class64.angle2d;
										} else {
											if (opcode != 1609) {
												break;
											}
											int_stack[int_stack_ptr++] = class64.transparency;
										}
									}
								} else {
									RSInterface class64;
									if (opcode < 2000) {
										class64 = bool_30_ ? StaticMethods.aClass64_2965 : Class61.aClass64_959;
									} else {
										class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
										opcode -= 1000;
									}
									if (opcode == 1300) {
										int i_250_ = -1 + int_stack[--int_stack_ptr];
										if (i_250_ < 0 || i_250_ > 9) {
											str_stack_ptr--;
										} else {
											class64.set_menu_action(i_250_, 0, str_stack[--str_stack_ptr]);
										}
									} else if (opcode == 1301) {
										int_stack_ptr -= 2;
										int i_251_ = int_stack[int_stack_ptr];
										int i_252_ = int_stack[1 + int_stack_ptr];
										if (i_251_ == -1 && i_252_ == -1) {
											class64.aClass64_1121 = null;
										} else {
											class64.aClass64_1121 = RSInterfaceList.get_dynamic_component(i_251_, i_252_, (byte) -64);
										}
									} else if (opcode == 1302) {
										class64.dragRenderBehaviour = int_stack[--int_stack_ptr] == 1;
									} else if (opcode == 1303) {
										class64.dragDeadZone = int_stack[--int_stack_ptr];
									} else if (opcode == 1304) {
										class64.dragDeadTime = int_stack[--int_stack_ptr];
									} else if (opcode == 1305) {
										class64.opBase = str_stack[--str_stack_ptr];
									} else if (opcode == 1306) {
										class64.targetVerb = str_stack[--str_stack_ptr];
									} else if (opcode == 1308) {
										int_stack_ptr -= 2;
									} else if (opcode == 1309) {
										int_stack_ptr -= 2;
									} else if (opcode == 1310) {
										str_stack_ptr -= 1;
									} else if (opcode == 1311) {
										int_stack_ptr -= 1;
									} else if (opcode == 1314) {
										int_stack_ptr -= 1;
									} else {
										if (opcode != 1307) {
											break;
										}
										class64.ops = null;
									}
								}
							} else {
								RSInterface class64;
								if (opcode >= 2000) {
									class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
									opcode -= 1000;
								} else {
									class64 = !bool_30_ ? Class61.aClass64_959 : StaticMethods.aClass64_2965;
								}
								RSInterfaceList.setDirty(class64);
								if (opcode == 1200 || opcode == 1205 || opcode == 1208 || opcode == 1209 || opcode == 1212 || opcode == 1213) {
									int_stack_ptr -= 2;
									int i_253_ = int_stack[int_stack_ptr];
									int i_254_ = int_stack[1 + int_stack_ptr];
									if (class64.componentIndex == -1) {
										InterfaceUpdateQueue.insertMediaHash(class64.uid, -120);
										InterfaceUpdateQueue.insertHash1(true, class64.uid);
										InterfaceUpdateQueue.insertModelHash(class64.uid, true);
									}
									if ((i_253_ ^ 0xffffffff) == 0) {
										class64.media_id = -1;
										class64.media_type = 1;
										class64.objid = -1;
									} else {
										class64.objid = i_253_;
										class64.shownItemAmount = i_254_;
										ObjType class23_sub13_sub11 = ObjTypeList.list(i_253_);
										if (opcode == 1208 || opcode == 1209) {
											class64.aBoolean1054 = true;
										} else {
											class64.aBoolean1054 = false;
										}
										if (opcode == 1205 || opcode == 1209) {
											class64.obj_stack_mode = 0;
										} else if (opcode == 1212 || opcode == 1213) {
											class64.obj_stack_mode = 1;
										} else {
											class64.obj_stack_mode = 2;
										}
										class64.media_yangle = class23_sub13_sub11.modelRotation2;
										class64.anInt258 = class23_sub13_sub11.modelOffset1;
										class64.media_xangle = class23_sub13_sub11.modelRotation1;
										class64.media_zangle = class23_sub13_sub11.modelRotationZ;
										class64.anInt264 = class23_sub13_sub11.modelOffset2;
										class64.media_zoom = class23_sub13_sub11.modelZoom;
										if ((class64.media_viewport_width ^ 0xffffffff) >= -1) {
											if (class64.width > 0) {
												class64.media_zoom = 32 * class64.media_zoom / class64.width;
											}
										} else {
											class64.media_zoom = class64.media_zoom * 32 / class64.media_viewport_width;
										}
									}
								} else if (opcode == 1201) {
									class64.media_type = 2;
									class64.media_id = int_stack[--int_stack_ptr];
									if (class64.componentIndex == -1) {
										RSInterfaceManager.a(4, class64.uid);
									}
								} else if (opcode == 1202) {
									class64.media_type = 3;
									class64.media_id = GameClient.currentPlayer.appearance.getMediaID();
									if (class64.componentIndex == -1) {
										RSInterfaceManager.a(4, class64.uid);
									}
								} else if (opcode == 1203) {
									class64.media_type = 6;
									class64.media_id = int_stack[--int_stack_ptr];
									if (class64.componentIndex == -1) {
										RSInterfaceManager.a(4, class64.uid);
									}
								} else if (opcode == 1206) {
									int_stack_ptr -= 4;
									RSInterfaceList.setDirty(class64);
								} else if (opcode == 1207) {
									int_stack_ptr -= 2;
									RSInterfaceList.setDirty(class64);
								} else if (opcode == 1210) {
									int_stack_ptr -= 4;
									class64.media_id = int_stack[int_stack_ptr];
									// class64.anInt2457 = int_stack[int_stack_ptr + 1];
									if (int_stack[int_stack_ptr + 2] == 1) {
										class64.media_type = 9;
									} else {
										class64.media_type = 8;
									}
									if (class64.componentIndex == -1) {
										RSInterfaceManager.a(4, class64.uid);
									}
								} else if (opcode == 1211) {
									class64.media_type = 5;
									class64.media_id = StaticMethods.thisPlayerIndex;
									if (class64.componentIndex == -1) {
										RSInterfaceManager.a(4, class64.uid);
									}
								} else {
									if (opcode != 1204) {
										break;
									}
									class64.media_type = 5;
									class64.media_id = int_stack[--int_stack_ptr];
									if (class64.componentIndex == -1) {
										RSInterfaceManager.a(4, class64.uid);
									}
								}
							}
						} else {
							RSInterface class64;
							if (opcode >= 2000) {
								class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
								opcode -= 1000;
							} else {
								class64 = bool_30_ ? StaticMethods.aClass64_2965 : Class61.aClass64_959;// false
							}
							if (opcode == 1100) {
								int_stack_ptr -= 2;
								class64.scroll_x = int_stack[int_stack_ptr];
								if (class64.scroll_width - class64.layout_width < class64.scroll_x) {
									class64.scroll_x = class64.scroll_width - class64.layout_width;
								}
								if (class64.scroll_x < 0) {
									class64.scroll_x = 0;
								}
								class64.scroll_y = int_stack[1 + int_stack_ptr];
								if ((class64.scroll_y ^ 0xffffffff) < (-class64.layout_height + class64.scroll_height ^ 0xffffffff)) {
									class64.scroll_y = -class64.layout_height + class64.scroll_height;
								}
								if ((class64.scroll_y ^ 0xffffffff) > -1) {
									class64.scroll_y = 0;
								}
								RSInterfaceList.setDirty(class64);
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertScollHash(12, class64.uid);
								}
							} else if (opcode == 1101) {
								class64.color = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertColorHash(class64.uid, 0);
								}
							} else if (opcode == 1102) {
								class64.filled = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1103) {
								class64.transparency = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1104) {
								class64.line_width = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1105) {
								int sprite = int_stack[--int_stack_ptr];
								if (class64.graphicid != sprite) {
									class64.graphicid = sprite;
									RSInterfaceList.setDirty(class64);
								}
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertSpriteHash(class64.uid, -1);
								}

							} else if (opcode == 1106) {
								class64.angle2d = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1107) {
								class64.repeat = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1108) {
								class64.media_type = 1;
								class64.media_id = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
								if (class64.componentIndex == -1) {
									RSInterfaceManager.a(4, class64.uid);
								}
							} else if (opcode == 1109) {
								int_stack_ptr -= 6;
								class64.anInt258 = int_stack[int_stack_ptr];
								class64.anInt264 = int_stack[int_stack_ptr + 1];
								class64.media_xangle = int_stack[2 + int_stack_ptr];
								class64.media_yangle = int_stack[int_stack_ptr + 3];
								class64.media_zangle = int_stack[4 + int_stack_ptr];
								class64.media_zoom = int_stack[5 + int_stack_ptr];
								RSInterfaceList.setDirty(class64);
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertHash1(true, class64.uid);
									InterfaceUpdateQueue.insertModelHash(class64.uid, true);
								}
							} else if (opcode == 1110) {
								int i_255_ = int_stack[--int_stack_ptr];
								if ((i_255_ ^ 0xffffffff) != (class64.media_animid ^ 0xffffffff)) {
									class64.media_animid = i_255_;
									class64.media_tween_tick = 0;
									class64.media_current_frameid = 0;
									class64.media_next_frameid = 1;
									RSInterfaceList.setDirty(class64);
								}
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertHash3(class64.uid, 1);
								}
							} else if (opcode == 1111) {
								class64.media_orthographic = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1112) {
								RSString class16 = str_stack[--str_stack_ptr];
								if (!class16.equals(class64.default_text)) {
									class64.default_text = class16;
									RSInterfaceList.setDirty(class64);
								}
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertHash2(class64.uid, 1);
								}
							} else if (opcode == 1113) {
								class64.font_id = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertFontHash(class64.uid, 100);
								}
							} else if (opcode == 1114) {
								int_stack_ptr -= 3;
								class64.halignment = int_stack[int_stack_ptr];
								class64.v_text_align = int_stack[1 + int_stack_ptr];
								class64.line_height = int_stack[int_stack_ptr + 2];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1115) {
								class64.shaded = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1116) {
								class64.outline = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1117) {
								class64.graphic_shadow = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1118) {
								class64.flip_v = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1119) {
								class64.flip_h = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1122) {
								class64.tiling = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1121) {
								int_stack_ptr -= 2;
								class64.media_depth_near = (short) int_stack[int_stack_ptr];
								class64.media_depth_scale = (short) int_stack[int_stack_ptr + 1];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1123) {
								class64.media_zoom = int_stack[--int_stack_ptr];
								RSInterfaceList.setDirty(class64);
								if (class64.componentIndex == -1) {
									InterfaceUpdateQueue.insertHash1(true, class64.uid);
								}
							} else if (opcode == 1124) {
								class64.line_mirrored = int_stack[--int_stack_ptr] == 1;
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1125) {
								int_stack_ptr -= 2;
								class64.media_origin_x = int_stack[int_stack_ptr];
								class64.media_origin_y = int_stack[int_stack_ptr + 1];
								RSInterfaceList.setDirty(class64);
							} else if (opcode == 1127) {
								int_stack_ptr -= 2;
								int param_id = int_stack[int_stack_ptr];
								int param_val = int_stack[int_stack_ptr + 1];
								ParamType paramtype = ParamType.getParamType(param_id);
								if (param_val != paramtype.defaultint) {
									class64.set_param(param_id, param_val);
								} else {
									class64.remove_param(param_id);
								}
							} else if (opcode == 1128) {
								int param_id = int_stack[--int_stack_ptr];
								RSString string = str_stack[--str_stack_ptr];
								ParamType paramtype = ParamType.getParamType(param_id);
								if (!paramtype.defaultstr.equals(string)) {
									class64.set_param(param_id, string);
								} else {
									class64.remove_param(param_id);
								}
							} else {
								if (opcode != 1120) {
									break;
								}
								int_stack_ptr -= 2;
								class64.scroll_width = int_stack[int_stack_ptr];
								class64.scroll_height = int_stack[1 + int_stack_ptr];
								RSInterfaceList.setDirty(class64);
								if (class64.type == 0) {
									RSInterfaceLayout.calc_layout(class64, dummy);
								}
							}
						}
					} else {
						RSInterface class64;
						if (opcode >= 2000) {
							opcode -= 1000;
							class64 = RSInterface.getInterface(int_stack[--int_stack_ptr]);
						} else {
							class64 = !bool_30_ ? Class61.aClass64_959 : StaticMethods.aClass64_2965;
						}
						if (opcode == 1000) {
							int_stack_ptr -= 4;
							class64.positionX = int_stack[int_stack_ptr];
							class64.positionY = int_stack[int_stack_ptr + 1];
							int h_pos_mode = int_stack[int_stack_ptr + 2];
							if (h_pos_mode < 0) {
								h_pos_mode = 0;
							} else if (h_pos_mode > 5) {
								h_pos_mode = 5;
							}
							int v_pos_mode = int_stack[int_stack_ptr + 3];
							if (v_pos_mode < 0) {
								v_pos_mode = 0;
							} else if (v_pos_mode > 5) {
								v_pos_mode = 5;
							}
							class64.h_pos_mode = (byte) h_pos_mode;
							class64.v_pos_mode = (byte) v_pos_mode;
							RSInterfaceList.setDirty(class64);
							RSInterfaceLayout.update_layout(class64);
							if (class64.componentIndex == -1) {
								InterfaceUpdateQueue.insertPositionHash(class64.uid, true);
							}
						} else if (opcode == 1001) {
							int_stack_ptr -= 4;
							class64.width = int_stack[int_stack_ptr];
							class64.height = int_stack[int_stack_ptr + 1];
							class64.media_viewport_width = 0;
							class64.media_viewport_height = 0;
							int h_size_mode = int_stack[int_stack_ptr + 2];
							if (h_size_mode < 0) {
								h_size_mode = 0;
							} else if (h_size_mode > 4) {
								h_size_mode = 4;
							}
							int v_size_mode = int_stack[int_stack_ptr + 3];
							if (v_size_mode < 0) {
								v_size_mode = 0;
							} else if (v_size_mode > 4) {
								v_size_mode = 4;
							}
							class64.h_size_mode = (byte) h_size_mode;
							class64.v_size_mode = (byte) v_size_mode;
							RSInterfaceList.setDirty(class64);
							RSInterfaceLayout.update_layout(class64);
							if ((class64.type ^ 0xffffffff) == -1) {
								RSInterfaceLayout.calc_layout(class64, dummy);
							}
						} else if (1004 == opcode) {
							int_stack_ptr -= 2;
							class64.aspect_width = int_stack[int_stack_ptr];
							class64.aspect_height = int_stack[1 + int_stack_ptr];
							RSInterfaceList.setDirty(class64);
							RSInterfaceLayout.update_layout(class64);
							if ((class64.type ^ 0xffffffff) == -1) {
								RSInterfaceLayout.calc_layout(class64, dummy);
							}
						} else if (opcode == 1005) {
							class64.noClickThrough = int_stack[--int_stack_ptr] == 1;
						} else {
							if (opcode != 1003) {
								break;
							}
							boolean bool_256_ = int_stack[--int_stack_ptr] == 1;
							if (bool_256_ != class64.hidden) {
								class64.hidden = bool_256_;
								RSInterfaceList.setDirty(class64);
							}
							if (class64.componentIndex == -1) {
								InterfaceUpdateQueue.insertHiddenHash(7, class64.uid);
							}
						}
					}
				}
				throw new IllegalStateException("op: " + opcode);
			} catch (Exception exception) {
				if (script.name == null) {
					if ((GameClient.getGameType() ^ 0xffffffff) != -1) {
						Class95.sendGameMessage(0, -1, PlayerIdentityKit.aClass16_4093, TimeTools.aClass16_1600);
					}
					RSString class16 = RSString.create(30, 0);
					class16.append(HashTable.aClass16_1253).append(RSString.valueOf(script.scriptid));
					for (int i_257_ = call_stack_ptr + -1; (i_257_ ^ 0xffffffff) <= -1; i_257_--) {
						class16.append(Class21.aClass16_341).append(RSString.valueOf(call_stack[i_257_].cs2Script.scriptid));
					}
					if (opcode == 40) {
						int i_258_ = int_operands[ip];
						class16.append(StringConstants.aClass16_1975).append(RSString.valueOf(i_258_));
					}
					if ((GameClient.getGameType() ^ 0xffffffff) != -1) {
						Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3132, RSString.valueOf(script.scriptid) }), TimeTools.aClass16_1600);
					}
					ForceMovement.sendError(95, exception, "CS2 - scr:" + script.uid + " ops:" + opcode + new String(class16.getBytes()));
				} else {
					RSString class16 = RSString.create(30, 0);
					class16.append(HashTable.aClass16_1253).append(script.name);
					for (int i_257_ = call_stack_ptr + -1; (i_257_ ^ 0xffffffff) <= -1; i_257_--) {
						class16.append(Class21.aClass16_341).append(call_stack[i_257_].cs2Script.name);
					}
					if (opcode == 40) {
						int i_258_ = int_operands[ip];
						class16.append(StringConstants.aClass16_1975).append(RSString.valueOf(i_258_));
					}
					if ((GameClient.getGameType() ^ 0xffffffff) != -1) {
						Class95.sendGameMessage(0, -1, RSString.joinRsStrings(new RSString[] { StaticMethods.aClass16_3132, script.name }), TimeTools.aClass16_1600);
					}
					ForceMovement.sendError(95, exception, "CS2 - scr:" + script.uid + " ops:" + opcode + new String(class16.getBytes()));
				}
			}
		}
	}

	public static int a(char c) {
		if (a(c, -110)) {
			return 1;
		}
		return 0;
	}

	public static final boolean a(char c, int i) {
		if (c >= ' ' && c <= '~') {
			return true;
		}
		if (c >= '\u00a0' && c <= '\u00ff') {
			return true;
		}
		if (c == '\u20ac' || c == '\u0152' || c == '\u2014' || c == '\u0153' || c == '\u0178') {
			return true;
		}
		return false;
	}
}
