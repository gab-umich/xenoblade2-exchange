package ExchangeGuide.data;

import java.util.TreeSet;

public class ShopContent {
    final Shop shop;
    TreeSet<Recipe> recipes;

    public ShopContent(Shop shop) {
        this.shop = shop;
        recipes = new TreeSet<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
