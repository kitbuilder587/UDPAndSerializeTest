import java.io.Serializable;

public class SerializeTest implements Serializable {
    public String data;
    public String info;

    @Override
    public String toString() {
        return "SerializeTest{" +
                "data='" + data + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
