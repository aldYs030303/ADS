import java.util.ArrayList;

public class MyHeap<T extends Comparable<T>> {
    private ArrayList<T> list;

    public MyHeap() {
        list = new ArrayList<>();
    }

    public void add(T item){
        list.add(item);
    }

    public void heapify(int index) {
        int size = list.size();
        int largest = index;
        int leftChild = 2*index+1;
        int rightChild = 2*index+2;
        if(leftChild < size && list.get(leftChild).compareTo(list.get(largest)) > 0)
            largest = leftChild;
        if(rightChild < size && list.get(rightChild).compareTo(list.get(largest)) > 0)
            largest = rightChild;

        if(largest != index) {
            T temp = list.get(largest);
            list.set(largest, list.get(index));
            list.set(index, temp);

            heapify(largest);
        }
    }

    private int parent(int k) {
        return (k)/2;
    }

    private int leftChild(int k) {
        return ((k*2));
    }

    private int rightChild(int k) {
        return (k*2) + 1;
    }

    public boolean remove(T item) {
        if(list.contains(item)) {
            list.remove(item);
            return true;
        }
        return false;
    }

    public void print() {
        for (int i=0; i<list.size(); i++) {
            System.out.println(list.get(i)+" ");
        }
    }
}