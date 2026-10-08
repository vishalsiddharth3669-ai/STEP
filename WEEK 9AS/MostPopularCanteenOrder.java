import java.util.HashMap;
import java.util.Map;

public class MostPopularCanteenOrder {

    public static String[] mostPopular(String[] orders) {

        HashMap<String, Integer> countMap = new HashMap<>();

        // Count every item
        for (String item : orders) {
            countMap.put(
                    item,
                    countMap.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int bestCount = countMap.get(bestItem);

        // Scan original order to handle ties
        for (String item : orders) {

            int count = countMap.get(item);

            if (count > bestCount) {
                bestItem = item;
                bestCount = count;
            }
        }

        return new String[] {
                bestItem,
                String.valueOf(bestCount)
        };
    }

    public static void main(String[] args) {

        String[] orders = {
                "dosa",
                "idli",
                "vada",
                "dosa",
                "idli",
                "dosa",
                "tea"
        };

        String[] result = mostPopular(orders);

        System.out.println(
                "(\"" + result[0] + "\", " + result[1] + ")");
    }
}
