package mainPackage;

public class Event {
    private String m_name;
    private int m_tickets;

    Event()
    {
        m_name="";
        m_tickets=0;
    }

    Event(String name, int tickets)
    {
        m_name = name;
        m_tickets = tickets;
    }

    public String GetName() {return m_name;}
    public int GetTickets() {return m_tickets;}

    //will probably need an override method for comparison (compare event name vs other event name not obj address)


    @Override
    public String toString() {
        return "Event name: " + m_name + " " + " Tickets available: " + m_tickets;
    }
}
