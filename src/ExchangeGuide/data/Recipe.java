package ExchangeGuide.data;

import java.util.Map;

public class Recipe implements Comparable<Recipe> {
    final String setName;
    final Map<Material, Integer> requiredMaterials;
    final Reward reward;
    Shop availableAt;

    public Recipe(String setName, Map<Material, Integer> requiredMaterials, Reward reward, Shop availableAt) {
        this.setName = setName;
        this.requiredMaterials = requiredMaterials;
        this.reward = reward;
        this.availableAt = availableAt;
    }

    @Override
    public int compareTo(Recipe o) {
        // first compare by reward's gold, then compare by setName
        return reward.gold.equals(o.reward.gold) ? setName.compareTo(o.setName) : reward.gold - o.reward.gold;
    }
}
