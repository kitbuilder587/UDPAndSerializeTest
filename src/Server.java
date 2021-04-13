import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    public static DatagramSocket socket;


    public static void main(String[] args) {
        try {
            socket = new DatagramSocket(2255);
            while(true) {
                byte[] buff = new byte[2560];
                DatagramPacket packet = new DatagramPacket(buff, buff.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new ByteArrayInputStream(packet.getData()));
                SerializeTest test = (SerializeTest) objectInputStream.readObject();
                System.out.println(test.toString());
                objectInputStream.close();
                test = new SerializeTest();
                test.data = "ch, nice";
                test.info = "server";
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
                objectOutputStream.writeObject(test);
                objectOutputStream.flush();
                buff = stream.toByteArray();
                packet = new DatagramPacket(buff, buff.length, address, port);

                socket.send(packet);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
