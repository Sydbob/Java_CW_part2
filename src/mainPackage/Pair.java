package mainPackage;

public class Pair <K,V>
{
    private K key;
    private V value;

    public Pair() {}
    public Pair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K GetKey(){return key;}
    public V GetValue() {return value;}
    public void SetKey(K key) {this.key = key;}
    public void SetValue(V value) {this.value = value;}

    public String toString()
    {
        return key + " " + value;
    }
}
