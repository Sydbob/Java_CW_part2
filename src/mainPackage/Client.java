package mainPackage;
import static java.lang.System.*;
//import javafx.util.Pair;

/**
 * Class for club clients
 */
public class Client implements Comparable<Client> {
    private String m_name;
    private String m_surname;
    private static final int MAX_EVENTS = 3;
    private Pair<Event,Integer>[] m_chosenEvents;

    public Client(String name, String surname, Pair <Event,Integer>[] chosenEvents)
    {
        m_name = name;
        m_surname = surname;
        m_chosenEvents = chosenEvents;
    }


    public Client(String s)
    {
        String [] arr = s.split("\\W+");
        m_name = arr[0];
        m_surname = arr[1];
        m_chosenEvents = new Pair[MAX_EVENTS];
    }

    public Client()
    {
        m_name="";
        m_surname="";
        m_chosenEvents = new Pair[MAX_EVENTS];
    }

    public Pair<Event,Integer>[] GetChosenEvents() {return m_chosenEvents;}
    public String GetName() {return m_name;}
    public String GetSurname() {return m_surname;}
    public void SetName(String name) {m_name = name;}
    public void SetSurname(String surname) {m_surname = surname;}
    public void SetChosenEvents(Pair<Event,Integer>[] events) {m_chosenEvents = events;}


    //method to check if there space in the array i.e. if any of the elements are null
    public boolean HasSpace()
    {
        for (int i=0; i < m_chosenEvents.length; i++)
        {
            if (m_chosenEvents[i] == null)
            {
                return true;
            }
        }
        return false;
    }


     // method that checks if m_chosenEvents is empty
    public boolean IsEmpty()
    {
        boolean empty = true;
        for (int i = 0; i < m_chosenEvents.length; i ++)
        {
            if (m_chosenEvents[i] != null)
            {
                empty = false;
                break;
            }
        }
        return empty;
    }


    //add a new pair to the fixed size array
    public void AddPair(Pair[] arr, Pair<Event,Integer> pair)
    {
        for (int i = 0; i < arr.length; i++)
        {
            //find next available spot
            if (arr[i] == null)
            {
                //add the pair here
                arr[i] = pair;
                break;
            }
        }
    }

    //method for finding event id in m_chosenEvents
    public int EventID(Event event)
    {
        int id = -1;
        for (int i = 0; i < m_chosenEvents.length; i++)
        {
            if (m_chosenEvents[i] != null && m_chosenEvents[i].GetKey().equals(event))
            {
                //if event is found set id to it's position in the array and exit loop
                id = i;
                break;
            }
            else
            {
                //otherwise set it to -1 and keep looking
                id = -1;
            }
        }
        return id;
    }

    //method that checks if client bought any tickets
    public boolean HasTickets()
    {
        boolean b = false;
        for (Pair p : m_chosenEvents)
        {
            if (p != null)
            {
                b = true;
                break;
            }
        }
        return b;
    }

    //method that checks if client already bought tickets for an event
    public boolean ClientHasEvent(String n)
    {
        boolean b = false;
        if ( IsEmpty() )
        {
            b = true;
        }
        else
        {

            for (int i = 0; i < m_chosenEvents.length; i++) {
                if (m_chosenEvents[i] != null && m_chosenEvents[i].GetKey().GetName().equals(n)) {
                    b = true;
                    break;
                } else {
                    b = false;
                }
            }
        }
        return b;
    }

    //method to add bought tickets to client
    public void AddTicket(Event event, int tickets)
    {
        //check if event exists, set id to -1 if it doesnt
        int id = EventID(event);
            //if event exists i.e id is not -1
            if (id != -1)
            {
                //add new tickets to existing ones
                int temp = m_chosenEvents[id].GetValue() + tickets;
                //update the value in the pair
                m_chosenEvents[id].SetValue(temp);
            }
            else
            {
                //create a new pair and add it
                Pair<Event,Integer> pair = new Pair<>();
                pair.SetKey(event);
                pair.SetValue(tickets);
                AddPair(m_chosenEvents, pair);
            }

    }

    //method that removes tickets and event if it was fully refunded
    public void RemoveTicket(Event event, int tickets)
    {
        //check if event exists, set id to -1 if it doesn't
        int id = EventID(event);
        //if event exists i.e id is not -1
        if (id != -1)
        {
            //remove tickets to refund
            int temp = m_chosenEvents[id].GetValue() - tickets;
            //update the value in the pair
            m_chosenEvents[id].SetValue(temp);
        }
        //check for events that were completely refunded
        if (m_chosenEvents[id].GetValue() <= 0)
        {
            //remove the event entry if customer refunded all tickets
            m_chosenEvents[id] = null;
        }
    }
    //an override method for comparison (compare client name/surname vs other client name/surname)
    public boolean equals(Client client)
    {
        return (this.GetName().equals(client.GetName()) && this.GetSurname().equals(client.GetSurname()));
    }

    public int compareTo(Client c)
    {
        int lnCmp = m_surname.compareTo(c.m_surname);
        if (lnCmp !=0) return lnCmp;
        int fnCmp = m_name.compareTo(c.m_name);
        return fnCmp;
    }
    @Override
    //overridden method to print out client name, surname followed by event name and tickets bought for the event
    public String toString()
    {
        String events ="";
        String client =  "\nClient:" + " " + m_name + " " + m_surname + "\nEvents:";
        String toReturn;
        for (Pair<Event,Integer> p: m_chosenEvents)
        {
            if (p != null)
                events += "\n\t" + p.GetKey().GetName() + " | " + p.GetValue();
        }
        if (events.isEmpty())
            events = "No events to display";
        toReturn = client + events;
        return toReturn;
    }
}
