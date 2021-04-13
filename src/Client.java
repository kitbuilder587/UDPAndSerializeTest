import java.io.*;
import java.net.*;

public class Client {

    public static DatagramSocket socket;
    public static InetAddress adress;

    public static void main(String[] args) {

        try {
            socket = new DatagramSocket();
            SerializeTest test = new SerializeTest();
            test.data = "Hello World!";
            test.info = "client";
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
            objectOutputStream.writeObject(test);
            objectOutputStream.flush();
            byte[] buff  = stream.toByteArray();
            adress = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buff,buff.length,adress,2255);
            socket.send(packet);
            packet = new DatagramPacket(buff,buff.length);
            socket.receive(packet);
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new ByteArrayInputStream(packet.getData()));
            test = (SerializeTest) objectInputStream.readObject();
            System.out.println(test.toString());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
