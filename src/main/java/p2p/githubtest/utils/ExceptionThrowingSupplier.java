package p2p.githubtest.utils;

public interface ExceptionThrowingSupplier<O> {
    O get() throws Throwable;
}
