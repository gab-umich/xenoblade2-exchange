package exchangeGuide.indexer;

import exchangeGuide.data.ShopContent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ByShopIndexer {
    private final String TARGET_FILE_PATH = "./serialized/byShop.json";
    private final String FILE_CREATION_ERROR = "File %s creation not possible!";
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<ShopContent> shopContents;

    public ByShopIndexer(List<ShopContent> shopContents) {
        this.shopContents = shopContents;
    }

    public void serialize() {
        try {
            File target = new File(TARGET_FILE_PATH);
            if (target.exists() || target.createNewFile()) {
                objectMapper.writeValue(target, shopContents);
            } else {
                throw new IOException(String.format(FILE_CREATION_ERROR, TARGET_FILE_PATH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
