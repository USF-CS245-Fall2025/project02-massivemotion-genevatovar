import org.w3c.dom.Node;

public interface List<T> {

    public void add (int index, T element) throws Exception;
    public boolean add (T element);
    public T get (int index) throws Exception;
    public T remove (int index) throws Exception;
    public int size ();
}