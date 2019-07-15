package exchangeGuide.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public class Material implements Comparable<Material> {
    public final String name;

    public Material(String name) {
        this.name = name;
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
