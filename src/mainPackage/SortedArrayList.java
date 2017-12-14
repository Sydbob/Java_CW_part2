package mainPackage;
import java.util.ArrayList;

//custom class to store elements in a sorted array list
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    private E element;

    public E GetElement() {return  element;}
    public void SetElement(E element) {this.element = element;}

    //add method that finds the right place for each new element based on insertion sorting algorithm
    public void AddE(E element)
    {
        this.add(element);
        if (this.size() != 1)
        {
            int i;
            int j;
            for (i = 0; i < this.size(); i++) {
                E value = this.get(i);
                for (j = i; j > 0; j--) {
                    if (this.get(j - 1).compareTo(value) < 0) {
                        break;
                    } else {
                        this.set(j, this.get(j - 1));
                    }
                }
                this.set(j, value);
            }
        }
    }
}
