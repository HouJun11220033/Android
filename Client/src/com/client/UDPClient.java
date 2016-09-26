package com.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
	public static void main(String[] args) throws IOException {
		try {
			DatagramSocket socket = new DatagramSocket(4567);
			InetAddress serverAddress = InetAddress.getByName("169.254.149.125");
			String str = "hello";
			byte data[] = str.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 4567);
			socket.send(packet);
		} catch (SocketException e) {

			e.printStackTrace();
		}
	}

}
