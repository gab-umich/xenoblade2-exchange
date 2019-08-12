package exchangeGuide.indexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import exchangeGuide.data.ShopContent;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Indexer<T> {
    protected static final String OUTPUT_DATA_PATH = "./docs/_data/";
    protected static final String FILE_CREATION_ERROR = "File %s creation not possible!";
    protected final String TARGET_FILE_PATH;
    protected ObjectMapper objectMapper = new ObjectMapper();
    protected List<ShopContent> shopContents;
    protected Set<T> data;
    protected SimpleModule module;

    /**
     * @param shopContents  original Data source, sanitized but without reverse indexing
     * @param target_file_path  output directory
     * @param module  module with custom serializer
     */
    public Indexer(List<ShopContent> shopContents, String target_file_path, SimpleModule module) {
        this.shopContents = shopContents;
        this.module = module;
        TARGET_FILE_PATH = target_file_path;
    }

    private void serialize(Set<T> dataSet, SimpleModule module) {
        objectMapper.registerModule(module);
        try {
            File target = new File(TARGET_FILE_PATH);
            if (target.exists() || target.createNewFile()) {
                objectMapper.writeValue(target, dataSet);
            } else {
                throw new IOException(String.format(Indexer.FILE_CREATION_ERROR, TARGET_FILE_PATH));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serialize(Set<T> dataSet) {
        serialize(dataSet, module);
    }

}
