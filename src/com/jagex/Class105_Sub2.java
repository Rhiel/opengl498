package com.jagex;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Class105_Sub2 {

	static RSString aClass16_23_;
	static RSString aClass16_24_;
	static RSString aClass16_25_;
	static RSString aClass16_26_;
	static RSString aClass16_27_;
	static RSString aClass2323;
	static RSString aClass2324;
	
	static final Object[] method1115(byte b) {
		byte[] bs = null;
		String str = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			NetworkInterface inter = NetworkInterface.getByInetAddress(address);
			if (inter != null) {
				bs = inter.getHardwareAddress();
				String s = address.getHostName();
				if (s != null) {
					str = s;
				}
			}
			if (bs == null) {
				byte[] dat;
				Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
				while (e.hasMoreElements()) {
					inter = e.nextElement();
					if (inter != null) {
						dat = inter.getHardwareAddress();
						if (str == null) {
							Enumeration<InetAddress> en = inter.getInetAddresses();
							while (en.hasMoreElements()) {
								InetAddress add = en.nextElement();
								if (add != null) {
									String s = add.getHostName();
									if (s != null) {
										str = s;
										break;
									}
								}
							}
						}
						if (dat != null) {
							bs = dat;
							break;
						}
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
			if (bs == null) {
				bs = new byte[0];
			}
		}
		return new Object[] { bs, str };
	}


	static final void method1431(ISAACPacket buffer) {
		int[] is = new int[4];
		is[3] = (int) Class87.aLong1489;
		VertexNormal.aClass16_1543 = Class71_Sub2.aClass16_27333;
		is[1] = (int) (9.9999999E7 * Math.random());
		is[2] = (int) (Class87.aLong1489 >> 32);
		buffer.putString(aClass2323, -80);
		is[0] = (int) (9.9999999E7 * Math.random());
		is[3] = (int) Class87.aLong1489;
		is[1] = (int) (9.9999999E7 * Math.random());
		is[2] = (int) (Class87.aLong1489 >> 3);
		is[3] = (int) Class87.aLong1489;
		is[1] = (int) (9.9999999E7 * Math.random());
		is[2] = (int) (Class87.aLong1489 >> 45);
		is[3] = (int) Class87.aLong1489;
		is[1] = (int) (9.9999999E7 * Math.random());
		is[2] = (int) (Class87.aLong1489 >> 10);
		is[3] = (int) Class87.aLong1489;
		buffer.putString(aClass2324, -80);
		is[1] = (int) (9.9999999E7 * Math.random());
		is[2] = (int) (Class87.aLong1489 >> 92);
		is[1] = (int) (9.9999999E7 * Math.random());
		is[2] = (int) (Class87.aLong1489 >> 33);
		StaticMethods2.aMethod498(buffer);
		is[1] = (int) (9.9999999E7 * Math.random());
		is[2] = (int) (Class87.aLong1489 >> 12);
	}

	static RSString method1430() {
		RSString aClass16_29_ = null;
		if (aClass16_29_ == null && method1429(aClass16_23_)) {
			aClass16_29_ = aClass16_23_;
		}
		if (aClass16_29_ == null && method1429(aClass16_24_)) {
			aClass16_29_ = aClass16_24_;
		}
		if (aClass16_29_ == null && method1429(aClass16_25_)) {
			aClass16_29_ = aClass16_25_;
		}
		if (aClass16_29_ == null) {
			String s = "";
			if (method1429(aClass16_26_)) {
				s += new String(aClass16_26_.bytes);
			}
			if (method1429(aClass16_27_)) {
				if (s.length() > 0) {
					s += "|";
				}
				s += new String(aClass16_27_.bytes);
			}
		}
		return aClass16_29_;
	}

	static {
		aClass2323 = RSString.create(System.getProperty("user.name"));
		aClass2324 = RSString.create(method132893());
	}

	static final RSString method1428(boolean bool) {
		StringBuilder sb = new StringBuilder();
		if (method1429(aClass16_23_)) {
			sb.append(new String(aClass16_23_.bytes)).append("|");
		}
		if (method1429(aClass16_24_)) {
			sb.append(new String(aClass16_24_.bytes)).append("|");
		}
		if (method1429(aClass16_25_)) {
			sb.append(new String(aClass16_25_.bytes)).append("|");
		}
		if (method1429(aClass16_26_)) {
			sb.append(new String(aClass16_26_.bytes)).append("|");
		}
		if (method1429(aClass16_27_)) {
			sb.append(new String(aClass16_27_.bytes));
		}
		return RSString.create(sb.toString());
	}

	static final boolean method1429(RSString aClass16_28_) {
		return aClass16_28_ != null && aClass16_28_.length > 3;
	}
	
	public static String method132893() {
		try {
			String firstInterface = null;        
			Map<String, String> addressByNetwork = new HashMap<>();
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()){
				NetworkInterface network = networkInterfaces.nextElement();
				byte[] bmac = network.getHardwareAddress();
				if(bmac != null){
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < bmac.length; i++){
						sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "-" : ""));        
					}
					if (sb.toString().equals("00-00-00-00-00-00-00-E0")) {
						continue;
					}
					if(sb.toString().isEmpty()==false){
						addressByNetwork.put(network.getName(), sb.toString());
					}

					if(sb.toString().isEmpty()==false && firstInterface == null){
						firstInterface = network.getName();
					}
				}
			}
			if(firstInterface != null){
				return addressByNetwork.get(firstInterface);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		try {      
            InetAddress in = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(in);
            if (network == null) {
            	return "";
            }
    		byte[] mac = network.getHardwareAddress();
    		if (mac == null) {
    			return "";
    		}
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < mac.length; i++) {
    			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
    		}
    		return sb.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();   
        } catch (SocketException e) {
            e.printStackTrace();
        }
		return "";
	}

	@Override
	public final String toString() {
		return new String(method1428(false).bytes);
	}

}