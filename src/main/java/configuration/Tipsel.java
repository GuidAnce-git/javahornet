package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tipsel {
    public int maxDeltaMsgYoungestConeRootIndexToCMI;
    public int maxDeltaMsgOldestConeRootIndexToCMI;
    public int belowMaxDepth;
    public NonLazy nonLazy;
    public SemiLazy semiLazy;

    public int getMaxDeltaMsgYoungestConeRootIndexToCMI() {
        return maxDeltaMsgYoungestConeRootIndexToCMI;
    }

    public void setMaxDeltaMsgYoungestConeRootIndexToCMI(int maxDeltaMsgYoungestConeRootIndexToCMI) {
        this.maxDeltaMsgYoungestConeRootIndexToCMI = maxDeltaMsgYoungestConeRootIndexToCMI;
    }

    public int getMaxDeltaMsgOldestConeRootIndexToCMI() {
        return maxDeltaMsgOldestConeRootIndexToCMI;
    }

    public void setMaxDeltaMsgOldestConeRootIndexToCMI(int maxDeltaMsgOldestConeRootIndexToCMI) {
        this.maxDeltaMsgOldestConeRootIndexToCMI = maxDeltaMsgOldestConeRootIndexToCMI;
    }

    public int getBelowMaxDepth() {
        return belowMaxDepth;
    }

    public void setBelowMaxDepth(int belowMaxDepth) {
        this.belowMaxDepth = belowMaxDepth;
    }

    public NonLazy getNonLazy() {
        return nonLazy;
    }

    public void setNonLazy(NonLazy nonLazy) {
        this.nonLazy = nonLazy;
    }

    public SemiLazy getSemiLazy() {
        return semiLazy;
    }

    public void setSemiLazy(SemiLazy semiLazy) {
        this.semiLazy = semiLazy;
    }
}
