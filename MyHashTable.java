import java.util.ArrayList;

public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private ArrayList<HashNode<K, V>> chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new ArrayList<>(M);
        size = 0;

        for (int i = 0; i < M; i++)
            chainArray.add(null);
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new ArrayList<>(M);
        size = 0;

        for (int i = 0; i < M; i++)
            chainArray.add(null);
    }

    private int hash(K key) {
        return (hashCode(key) & 0x7fffffff) % M;
    }

    private int hashCode(K key) {
        return java.lang.System.identityHashCode(key);
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray.get(index);

        while (head != null) {
            if (head.key.equals(key) && hashCode(head.key) == hashCode(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = chainArray.get(index);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        chainArray.set(index, newNode);

        if ((1.0 * size) / M >= 0.7) {
            ArrayList<HashNode<K, V>> temp = chainArray;
            chainArray = new ArrayList<>();
            M = 2 * M;
            size = 0;
            for (int i = 0; i < M; i++)
                chainArray.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray.get(index);

        while (head != null) {
            if (head.key.equals(key) && hashCode(head.key) == hashCode(key))
                return head.value;
            head = head.next;
        }

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> head = chainArray.get(index);
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key) && hashCode(key) == hashCode(head.key))
                break;

            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;

        size--;

        if (prev != null)
            prev.next = head.next;
        else
            chainArray.set(index, head.next);

        return head.value;
    }

    public boolean contains(V value) {
        for(HashNode<K, V> n : chainArray) {
            for(HashNode<K, V> i = n; i != null; i = i.next) {
                if(i.value == value) {
                    return true;
                }
            }
        }
        return false;
    }

    public K getKey(V value) {
        for(HashNode<K, V> n : chainArray) {
            for(HashNode<K, V> i = n; i != null; i = i.next) {
               if(i.value == value) {
                   return i.key;
               }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        for(HashNode<K, V> n : chainArray) {
            for(HashNode<K, V> i = n; i != null; i = i.next) {
                res += i.toString() + ", ";
            }
        }
        return "{" + res + "}";
    }
}
