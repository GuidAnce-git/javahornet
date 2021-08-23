package p2p.githubtest.utils;

import java.util.concurrent.CompletionStage;

public interface ExceptionThrowingFutureSupplier<O> {
    CompletionStage<O> get() throws Throwable;
}
