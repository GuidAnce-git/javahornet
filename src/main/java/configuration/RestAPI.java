package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestAPI {
    public String bindAddress;
    public JwtAuth jwtAuth;
    public boolean excludeHealthCheckFromAuth;
    public List<String> permittedRoutes;
    public List<String> whitelistedAddresses;
    public boolean powEnabled;
    public int powWorkerCount;
    public Limits limits;

    public String getBindAddress() {
        return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
    }

    public JwtAuth getJwtAuth() {
        return jwtAuth;
    }

    public void setJwtAuth(JwtAuth jwtAuth) {
        this.jwtAuth = jwtAuth;
    }

    public boolean isExcludeHealthCheckFromAuth() {
        return excludeHealthCheckFromAuth;
    }

    public void setExcludeHealthCheckFromAuth(boolean excludeHealthCheckFromAuth) {
        this.excludeHealthCheckFromAuth = excludeHealthCheckFromAuth;
    }

    public List<String> getPermittedRoutes() {
        return permittedRoutes;
    }

    public void setPermittedRoutes(List<String> permittedRoutes) {
        this.permittedRoutes = permittedRoutes;
    }

    public List<String> getWhitelistedAddresses() {
        return whitelistedAddresses;
    }

    public void setWhitelistedAddresses(List<String> whitelistedAddresses) {
        this.whitelistedAddresses = whitelistedAddresses;
    }

    public boolean isPowEnabled() {
        return powEnabled;
    }

    public void setPowEnabled(boolean powEnabled) {
        this.powEnabled = powEnabled;
    }

    public int getPowWorkerCount() {
        return powWorkerCount;
    }

    public void setPowWorkerCount(int powWorkerCount) {
        this.powWorkerCount = powWorkerCount;
    }

    public Limits getLimits() {
        return limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }
}
