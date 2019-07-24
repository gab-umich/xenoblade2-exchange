package exchangeGuide.data;

import com.fasterxml.jackson.annotation.JsonValue;
import exchangeGuide.algorithm.SingularInflector;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Material implements Comparable<Material> {
    public final String name;
    private static SingularInflector toSingularConverter = SingularInflector.getInstance();

    public Material(String name) {
        // for each word, convert to singular form if is a noun.
        this.name = Arrays.stream(name.split(" ")).map(toSingularConverter::toSingular)
                .collect(Collectors.joining(" "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return name.equals(material.name);
    }

    @Override
    public int compareTo(Material o) {
        return name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    /* This annotation ensures that the Jackson Serializer uses this.name as its output */
    @JsonValue
    public String toString() {
        return name;
    }
}
