package fc.awesome.phaseBot.discord.utils;


/**
 * Makes writing lambdas with expressions simple 1-liners
 * @param <T> The type to return
 * @param <E> The exception this thing can throw
 */
@FunctionalInterface
public interface ThrowableSupplier <T, E extends Exception>{
    public T get() throws E;
}
