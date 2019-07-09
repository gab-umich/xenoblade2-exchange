package ExchangeGuide.data;

import java.util.Objects;

public class Material {
    final String name;

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
    public int hashCode() {
        return Objects.hash(name);
    }
}
