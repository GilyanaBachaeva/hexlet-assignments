package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        List<String> result = new ArrayList<>();

        Collections.sort(apartments, (h1, h2) -> Double.compare(h1.getArea(), h2.getArea()));

        for (int i = 0; i < Math.min(n, apartments.size()); i++) {
            result.add(apartments.get(i).toString());
        }

        return result;
    }
}
// END
