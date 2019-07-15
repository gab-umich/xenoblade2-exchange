package exchangeGuide.data;

import java.util.Map;

public class Reward {
    private final Integer gold;
    private final Map<Booster, Integer> boosters;

    public Reward(Integer gold, Map<Booster, Integer> boosters) {
        this.gold = gold;
        this.boosters = boosters;
    }

    public Integer getGold() {
        return gold;
    }

    public Map<Booster, Integer> getBoosters() {
        return boosters;
    }
}
