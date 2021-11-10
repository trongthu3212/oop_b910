import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PhanTu<T extends Comparable<T>> {
    private T value;

    public PhanTu(T value) {
        this.value = value;
    }

    public boolean isGreaterThan(PhanTu<T> a) {
        return value.isGreaterThan(a.getValue());
    }

    public T getValue() {
        return value;
    }

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader(args[0]);
        Scanner scanner = new Scanner(reader);

        int count = scanner.nextInt();
        List<PhanTu<Integer>> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(new PhanTu<Integer>(new Integer(scanner.nextInt())));
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).isGreaterThan(list.get(j))) {
                    PhanTu<Integer> temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }

        FileWriter writer = new FileWriter(args[1]);
        for (int i = 0; i < count; i++) {
            writer.write(list.get(i).getValue().toString() + "\n");
        }
    }
}
