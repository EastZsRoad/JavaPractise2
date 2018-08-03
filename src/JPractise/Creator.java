package JPractise;

public  abstract class  Creator {
    public abstract <T extends Pattern> T createPattern(Class<T> c);
}
