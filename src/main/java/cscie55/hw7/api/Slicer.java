package cscie55.hw7.api;

public interface Slicer<T, R, V> {
    R slice(T t, V v, V vTwo);
}
