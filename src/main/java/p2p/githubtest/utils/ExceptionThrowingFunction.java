package p2p.githubtest.utils;

public interface ExceptionThrowingFunction<I, O> {
    O apply(I value) throws Throwable;
}
