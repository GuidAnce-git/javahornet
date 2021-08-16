package node;

import java.util.List;

/**
 * InitPlugin is the module initializing configuration of the node.
 * A Node can only have one of such modules.
 */
public class InitPlugin extends Pluggable{

    public InitConfig InitPlugin(List<String> params, List<String> maskedKeys) {


        return new InitConfig();
    }
}
