package learn.TestSocket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by sure GM on 2015/9/17.
 */
public class UDPServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(5678);
		byte buf[] = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		try {
			while (true) {
				ds.receive(dp);
				ByteArrayInputStream bais = new ByteArrayInputStream(dp.getData());
				DataInputStream dis = new DataInputStream(bais);
				System.out.println(dis.readLong());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ds.close();
		}
	}
}
