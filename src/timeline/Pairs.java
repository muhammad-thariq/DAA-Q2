package timeline;
import java.util.Objects;


public class Pairs<T, U> {
    public T first;
    public U second;

    public Pairs(T first, U second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pairs<?, ?> pair = (Pairs<?, ?>) obj;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}