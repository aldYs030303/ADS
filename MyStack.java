import java.util.ArrayList;

public class MyStack<T> {
    private ArrayList<T> list;

    public MyStack() {
        list = new ArrayList<>();
    }

    public T push(T object) {
        list.add(object);
        return object;
    }

    public T pop() {
        T object = list.get(list.size()-1);
        list.remove(list.size()-1);
        return object;
    }

    public T peek() {
        return list.get(list.size()-1);
    }

    public boolean  isEmpty() {
        if(list.size() == 0) return true;
        return false;
    }

    public int size() {
        return list.size();
    }
}