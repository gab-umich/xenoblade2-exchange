package exchangeGuide.indexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import exchangeGuide.data.ShopContent;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Indexer<K, V> {
    protected static final String OUTPUT_DATA_PATH = "./docs/_data/";
    protected final String TARGET_FILE_PATH;
    protected static final String FILE_CREATION_ERROR = "File %s creation not possible!";
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected List<ShopContent> shopContents;
    protected Map<K, V> dataMap;

    public Indexer(List<ShopContent> shopContents, String target_file_path) {
        this.shopContents = shopContents;
        TARGET_FILE_PATH = target_file_path;
    }

    protected void serialize(Map<K, V> data) {
        try {
            File target = new File(TARGET_FILE_PATH);
            if (target.exists() || target.createNewFile()) {
                objectMapper.writeValue(target, data);
            } else {
                throw new IOException(String.format(Indexer.FILE_CREATION_ERROR, TARGET_FILE_PATH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialize() {
        serialize(dataMap);
    }
}
