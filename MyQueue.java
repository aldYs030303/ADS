import java.util.LinkedList;

public class MyQueue<T> {
    private LinkedList<T> list;

    public MyQueue() {
        list = new LinkedList<>();
    }

    public boolean add(T object) {
        return list.add(object);
    }

    public T element() {
        return list.getFirst();
    }

    public boolean offer(T object) {
        return list.offer(object);
    }

    public T peek() {
        if(list.size() == 0) return null;
        return list.getFirst();
    }

    public T poll() {
        if(list.size() == 0) return null;
        return list.removeFirst();
    }

    public T remove() {
        return list.removeFirst();
    }

    public int size() {
        return list.size();
    }
}