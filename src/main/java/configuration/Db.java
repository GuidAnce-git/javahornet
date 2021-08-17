package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Db {
    public String engine;
    public String path;
    public boolean autoRevalidation;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isAutoRevalidation() {
        return autoRevalidation;
    }

    public void setAutoRevalidation(boolean autoRevalidation) {
        this.autoRevalidation = autoRevalidation;
    }
}
