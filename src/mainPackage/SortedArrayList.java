package mainPackage;
import java.util.ArrayList;


public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    private E element;

    public E GetElement() {return  element;}
    public void SetElement(E element) {this.element = element;}

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
    //task 1
    //one method that inserts a new element in a sorted list in the right place not using collections.sort
    //scan list and find right place for the element
    //header is last slide in 41 in notes part 2

    //task2
    //2 types of objects: event objects and client objects
    //suggestions of main characteristics on age 2 of the spec
    //events and clients are to be stored in a sorted arrayList:
    //order provided in the spec for events and clients
    //see sportclub example slide 25 and later for building this program (first set of lecture notes)

    //main method (driver) class
    //peson class
    //event class
    //sorted array list class


}
