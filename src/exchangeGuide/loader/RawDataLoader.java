package exchangeGuide.loader;

import exchangeGuide.data.Shop;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RawDataLoader {
    private static final String DEFAULT_SHOPINFO_FILE_PATH = "ShopInfo.json";
    private static final String NOT_ARRAY_NODE = "Warning: ShopInfo is not formatted as an ArrayNode";
    private ObjectNode objectNode;

    /**
     * Constructor, default to look up all shops specified by a "ShopInfo.json" file.
     */
    public RawDataLoader() {
        this(DEFAULT_SHOPINFO_FILE_PATH);
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
    public List<Shop> buildShopList() {
        ArrayList<Shop> shops = new ArrayList<>();
        String rawDirectory = objectNode.get("rawDirectory").textValue();
        JsonNode shopsNode = objectNode.get("shops");
        if (!shopsNode.isArray()) {
            System.out.println(NOT_ARRAY_NODE);
            return shops;
        }
        for (final JsonNode shopNode : shopsNode) {
            Shop shop = new Shop(
                    shopNode.get("shopName").textValue(),
                    shopNode.get("area").textValue(),
                    shopNode.get("nation").textValue(),
                    rawDirectory + shopNode.get("fileName").textValue()
            );
            shops.add(shop);
        }
        return shops;
    }

}
