package configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NonLazy {
    public int retentionRulesTipsLimit;
    public String maxReferencedTipAge;
    public int maxChildren;
    public int spammerTipsThreshold;

    public int getRetentionRulesTipsLimit() {
        return retentionRulesTipsLimit;
    }

    public void setRetentionRulesTipsLimit(int retentionRulesTipsLimit) {
        this.retentionRulesTipsLimit = retentionRulesTipsLimit;
    }

    public String getMaxReferencedTipAge() {
        return maxReferencedTipAge;
    }

    public void setMaxReferencedTipAge(String maxReferencedTipAge) {
        this.maxReferencedTipAge = maxReferencedTipAge;
    }

    public int getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(int maxChildren) {
        this.maxChildren = maxChildren;
    }

    public int getSpammerTipsThreshold() {
        return spammerTipsThreshold;
    }

    public void setSpammerTipsThreshold(int spammerTipsThreshold) {
        this.spammerTipsThreshold = spammerTipsThreshold;
    }
}
