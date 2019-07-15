package exchangeGuide.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.TreeSet;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ShopContent {
    private final Shop shop;
    private TreeSet<Recipe> recipes;

    /**
     * Default constructor with a
     * @param shop
     */
    public ShopContent(Shop shop) {
        this.shop = shop;
        recipes = new TreeSet<>();
    }
    public Shop getShop() {
        return shop;
    }

    public TreeSet<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
