package mainPackage;
import static java.lang.System.*;
//import javafx.util.Pair;

public class Client {
    private String m_name;
    private String m_surname;
    private final int MAX_EVENTS = 3;
    private Pair<Event,Integer>[] m_chosenEvents;

    public Client(String name, String surname, Pair <Event,Integer>[] chosenEvents)
    {
        m_name = name;
        m_surname = surname;
        m_chosenEvents = chosenEvents;
    }

    public Client()
    {
        m_name="";
        m_surname="";
        m_chosenEvents = new Pair[MAX_EVENTS];
    }

    public Pair[] GetChosenEvents() {return m_chosenEvents;}
    public String GetName() {return m_name;}
    public String GetSurname() {return m_surname;}
    public void SetName(String name) {m_name = name;}
    public void SetSurname(String surname) {m_surname = surname;}
    public void SetChosenEvents(Pair<Event,Integer>[] events) {m_chosenEvents = events;}

    //add method to check if there space in the array i.e. if any of the elements are null

    //add ticket method to add bought tickets to client
    public void AddTicket(Event event, int tickets)
    {
        for (int i =0; i < m_chosenEvents.length; i++)
        {
            if (m_chosenEvents[i].GetKey().equals(event))
            {
                //add new tickets to the existing event
                //m_chosenEvents[i].getValue() +=tickets;
                //mutable pair instead maybe?
            }
            else
            {
                //add both the new event and the tickets
                //but where to add it??? --> need a method to find the first null value
            }
        }
        //check if event exists in the array to start with
        //if it exists add tickets to it, if it doesn't add the event and tickets to array
    }

    @Override
    //overridden method to print out client name, surname followed by event name and tickets bought for the event
    public String toString()
    {
        String toReturn = m_name + " " + m_surname;
        for (Pair<Event,Integer> p: m_chosenEvents)
        {
            toReturn += "\n" + p.GetKey().GetName() + " " + p.GetValue();
        }
        return toReturn;
    }



}
