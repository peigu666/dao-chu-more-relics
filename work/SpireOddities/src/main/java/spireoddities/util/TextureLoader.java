package spireoddities.util;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public final class TextureLoader {
    private static final HashMap<String, Texture> TEXTURES = new HashMap<>();

    private TextureLoader() {
    }

    public static Texture getTexture(String path) {
        Texture texture = TEXTURES.get(path);
        if (texture == null) {
            texture = new Texture(path);
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            TEXTURES.put(path, texture);
        }
        return texture;
    }
}
