package exchangeGuide.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import exchangeGuide.data.Recipe;
import exchangeGuide.data.Reward;
import exchangeGuide.data.Shop;

import java.io.IOException;

import static java.lang.System.exit;

public class RecipeSerializer extends StdSerializer<Recipe> {
    private Boolean showLocation;
    public RecipeSerializer(Boolean showLocation) {
        this((Class<Recipe>) null);
        this.showLocation = showLocation;
    }

    public RecipeSerializer(Class<Recipe> t) {
        super(t);
    }

    @Override
    public void serialize(
            Recipe recipe,
            JsonGenerator jgen,
            SerializerProvider provider
    ) throws IOException
    {
        jgen.writeStartObject();
        jgen.writeStringField("setName", recipe.getSetName());
        jgen.writeArrayFieldStart("requiredMaterials");
        recipe.getRequiredMaterials().forEach(
                (material, integer) -> {
                    try {
                        jgen.writeStartObject();
                        jgen.writeStringField("materialName", material.toString());
                        jgen.writeNumberField("count", integer);
                        jgen.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                        exit(-1);
                    }
                }
        );
        jgen.writeEndArray();
        if(showLocation) {
            Shop fromShop = recipe.getAvailableAt();
            jgen.writeStringField("shopNation", fromShop.nation);
            jgen.writeStringField("shopArea", fromShop.area);
            jgen.writeStringField("shopName", fromShop.shopName);
        }
        jgen.writeObjectField("reward", recipe.getReward());
        jgen.writeEndObject();
    }
}
