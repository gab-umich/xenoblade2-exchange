package exchangeGuide.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.AbstractMap;
import java.util.TreeSet;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ShopContent extends AbstractMap.SimpleEntry<Shop, TreeSet<Recipe>> {

    /**
     * Default constructor.
     */
    public ShopContent(Shop shop) {
        super(shop, new TreeSet<>());
    }
    public Shop getShop() {
        return getKey();
    }

    public TreeSet<Recipe> getRecipes() {
        return getValue();
    }

    public void addRecipe(Recipe recipe) {
        this.getValue().add(recipe);
    }
}
