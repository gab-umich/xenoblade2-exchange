package exchangeGuide.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.AbstractMap;
import java.util.TreeSet;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MaterialUsageLibrary extends AbstractMap.SimpleEntry<Material, TreeSet<Recipe>>
        implements Comparable<MaterialUsageLibrary> {

    /**
     * Default constructor.
     */
    public MaterialUsageLibrary(Material material) {
        super(material, new TreeSet<>());
    }
    public Material getMaterial() {
        return getKey();
    }

    public TreeSet<Recipe> getRecipes() {
        return getValue();
    }

    public void addRecipe(Recipe recipe) {
        this.getValue().add(recipe);
    }

    @Override
    public int compareTo(MaterialUsageLibrary o) {
        return this.getMaterial().compareTo(o.getMaterial());
    }
}
