package exchangeGuide.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class Recipe implements Comparable<Recipe> {
    private final String setName;
    private final Map<Material, Integer> requiredMaterials;
    private final Reward reward;

    @JsonIgnore
    private Shop availableAt;

    public Recipe(String setName, Map<Material, Integer> requiredMaterials, Reward reward, Shop availableAt) {
        this.setName = setName;
        this.requiredMaterials = requiredMaterials;
        this.reward = reward;
        this.availableAt = availableAt;
    }

    @Override
    public int compareTo(Recipe o) {
        // first compare by reward's gold, then compare by setName
        return reward.getGold().equals(o.reward.getGold()) ? setName.compareTo(o.setName)
                : reward.getGold() - o.reward.getGold();
    }

    public String getSetName() {
        return setName;
    }

    public Map<Material, Integer> getRequiredMaterials() {
        return requiredMaterials;
    }

    public Reward getReward() {
        return reward;
    }

    public Shop getAvailableAt() {
        return availableAt;
    }
}
