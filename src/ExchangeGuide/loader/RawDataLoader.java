package ExchangeGuide.loader;
import ExchangeGuide.data.Shop;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RawDataLoader {

    ObjectNode objectNode;

    /**
     * Constructor, default to look up all shops specified by a "ShopInfo.json" file.
     */
    public RawDataLoader() {
        this("ShopInfo.json");
    }

    /**
     * Constructor.
     */
    public RawDataLoader(String fileName) {
        try {
            JsonNode node = new ObjectMapper().reader()
                    .readTree(this.getClass().getClassLoader().getResourceAsStream(fileName));
            objectNode = (ObjectNode) node;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("cannot read raw data: " + fileName);
        }
    }

    /**
     * Read the shop information and parse into respective Shop objects
     */
    public List<Shop> getShopList(){
        ArrayList<Shop> shops = new ArrayList<>();
        String rawDirectory = objectNode.get("rawDirectory").textValue();
        JsonNode shopsNode = objectNode.get("shops");
        if (shopsNode.isArray()) {
            for (final JsonNode shopNode : shopsNode) {
                Shop shop = new Shop(
                        shopNode.get("shopName").textValue(),
                        shopNode.get("area").textValue(),
                        shopNode.get("nation").textValue(),
                        rawDirectory + shopNode.get("fileName").textValue()
                );
                shops.add(shop);
            }
            // shops.forEach();
        }
        return shops;
    }
}
