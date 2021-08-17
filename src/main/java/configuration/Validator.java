package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Validator {
    public boolean validate;
    public boolean ignoreSoftErrors;
    public Api api;
    public Coordinator coordinator;

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public boolean isIgnoreSoftErrors() {
        return ignoreSoftErrors;
    }

    public void setIgnoreSoftErrors(boolean ignoreSoftErrors) {
        this.ignoreSoftErrors = ignoreSoftErrors;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
