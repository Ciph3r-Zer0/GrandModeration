package ir.ciph3r.grandmoderation.storage.toml.model;

import com.moandjiezana.toml.Toml;
import lombok.Getter;
import net.kyori.adventure.text.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Model {
    @Getter
    private Toml configuration;
    private final Path path;
    private final Class<?> clazz;
    private final String fileName;

    public Model(Path path, Class<?> clazz, String fileName) {
        this.path = path;
        this.clazz = clazz;
        this.fileName = fileName;
    }

    public void load() {
        File folder = path.toFile();
        File file = new File(folder, fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try (InputStream input = clazz.getResourceAsStream("/" + file.getName())) {
                if (input != null) {
                    Files.copy(input, file.toPath());
                } else {
                    file.createNewFile();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        configuration = new Toml().read(file);
        init();
    }

    public abstract void init();
}
