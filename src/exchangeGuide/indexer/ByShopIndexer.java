package exchangeGuide.indexer;

import com.fasterxml.jackson.databind.module.SimpleModule;
import exchangeGuide.data.Recipe;
import exchangeGuide.data.ShopContent;
import exchangeGuide.serializer.RecipeSerializer;
import exchangeGuide.serializer.ShopContentSerializer;

import java.util.List;
import java.util.TreeSet;

public class ByShopIndexer extends Indexer<ShopContent> {

    public ByShopIndexer(List<ShopContent> shopContents) {
        super(shopContents, OUTPUT_DATA_PATH + "byShop.json", new SimpleModule());
        // create the module to register the custom serializer with
        module.addSerializer(ShopContent.class, new ShopContentSerializer());
        module.addSerializer(Recipe.class, new RecipeSerializer(false));
        // build data map as a Shop -> TreeSet<Recipe> map.
        data = new TreeSet<>(shopContents);
    }
}
