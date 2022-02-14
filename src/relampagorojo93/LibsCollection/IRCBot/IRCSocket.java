package relampagorojo93.LibsCollection.IRCBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.net.SocketFactory;

public class IRCSocket {
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	public IRCSocket(SocketFactory socketfactory, String host, int port) throws Exception {
		socket = socketfactory.createSocket();
		socket.connect(new InetSocketAddress(host, port), 3000);
		if (!isConnected()) throw new Exception("Not able to connect to the IRC server!");
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	public boolean isConnected() {
		return socket != null && !socket.isClosed() && socket.isConnected();
	}
	public void close() {
		try {
			if (socket != null && !socket.isClosed()) socket.close();
		} catch (IOException e) {}
	}
	public BufferedReader getReader() {
		return in;
	}
	public BufferedWriter getWriter() {
		return out;
	}
}
