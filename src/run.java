import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class run {
    public static void main(String[] args) {
        Instant start = Instant.now();
        List<String> Lout = library.perString();
        String[] str = new String[Lout.size()];
        for (int i = 0; i < Lout.size(); i++) {
            str[i] = Lout.get(i);
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        library.writePerLine("../Word Search/file/output.txt", str, timeElapsed);
    }
}
