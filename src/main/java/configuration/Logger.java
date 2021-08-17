package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Logger {
    public String level;
    public boolean disableCaller;
    public String encoding;
    public List<String> outputPaths;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isDisableCaller() {
        return disableCaller;
    }

    public void setDisableCaller(boolean disableCaller) {
        this.disableCaller = disableCaller;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<String> getOutputPaths() {
        return outputPaths;
    }

    public void setOutputPaths(List<String> outputPaths) {
        this.outputPaths = outputPaths;
    }
}
