import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class MyInteger implements GreaterComparable {
    int value;

    public MyInteger(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean isGreaterThan(GreaterComparable value) {
        if (value instanceof MyInteger) {
            if (this.value > ((MyInteger) value).value) {
                return true;
            }
        }

        return false;
    }
}

public class PhanTu<T extends GreaterComparable> {
    private T value;

    public PhanTu(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isGreaterThan(PhanTu other) {
        return value.isGreaterThan(other.value);
    }

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);

        int count = scanner.nextInt();
        List<PhanTu<MyInteger>> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(new PhanTu<MyInteger>(new MyInteger(scanner.nextInt())));
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).isGreaterThan(list.get(j))) {
                    PhanTu<MyInteger> temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        FileWriter writer = new FileWriter(args[1]);
        for (int i = 0; i < count; i++) {
            writer.write(list.get(i).getValue().toString() + "\n");
        }
        writer.close();
    }
}
