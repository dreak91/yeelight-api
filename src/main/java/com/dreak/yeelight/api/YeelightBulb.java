package com.dreak.yeelight.api;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class YeelightBulb {

    private Socket socket;
    private BufferedReader out;
    private BufferedWriter in;

    public YeelightBulb(InetSocketAddress address) throws IOException {
        socket = new Socket();
        socket.connect(address, 5000);
        socket.setSoTimeout(5000);
        out = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        in = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public static void main(String[] args) throws IOException {
        YeelightBulb bulb = new YeelightBulb(new InetSocketAddress("10.0.0.234", 55443));

        bulb.send("{\"id\":3,\"method\":\"set_rgb\",\"params\":[255, \"smooth\", 500]}\r\n");
    }

    public void send(String request) throws IOException {
        in.write(request);
        in.flush();

        String response = out.readLine();
        YeelightResponse.of(response);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        out.close();
        in.close();
        socket.close();
        System.out.println("closed");
    }
}
