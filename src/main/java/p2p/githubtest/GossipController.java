package p2p.githubtest;

import java.util.concurrent.CompletableFuture;

public interface GossipController {
    CompletableFuture<String> chat(String msg);

    CompletableFuture<String> send(String msg);
}


