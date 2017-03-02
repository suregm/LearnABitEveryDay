package bitBeat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by sure GM on 2017/3/2 23:57.
 */
public class IP {

	public static void main(String[] args) {
		easyIP();
		System.out.println(getLocalIPByCMD());
		System.out.println(getLocalIPByJava());
	}

	/**
	 * 最常用方法，但是不推荐，如果开vpn或者添加多网卡后获得的是小网ip或是第一个ip
	 * @return
	 */
	private static String easyIP() {
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String localIP = ia.getHostAddress().toString();    //获得本机IP
		String localName = ia.getHostName().toString();     //获得本机名称
		System.out.println("HostIP: " + localIP);
		System.out.println("HostName: " + localName);
		return localIP;
	}

	/**
	 * 命令行findstr方法，获得多组ip，需要再次筛选
	 * @return
	 */
	// 方法一，使用CMD命令：
	public static String getLocalIPByCMD() {
		StringBuilder sb = new StringBuilder();
		String command = "cmd.exe /c ipconfig | findstr IPv4";
		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.substring(line.lastIndexOf(":") + 2, line.length());
				sb.append(line + ", ");
			}
			br.close();
			p.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 *
	 * @return
	 */
	// 方法二，使用Java方法：
	public static String getLocalIPByJava() {
		StringBuilder sb = new StringBuilder();
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface intf = (NetworkInterface) en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						sb.append(inetAddress.getHostAddress().toString() + ", ");
					}
				}
			}
		} catch (SocketException e) {
		}
		return sb.toString();
	}
}
