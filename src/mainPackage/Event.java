package mainPackage;

public class Event implements Comparable<Event>{
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
    public void SetTickets(int t) {m_tickets = t;}

    //an override method for comparison (compare event name vs other event name not obj address)
    public boolean equals(Event event)
    {
        return (this.GetName().equals(event.GetName()));
    }


       public int compareTo(Event e)
    {
        int nCmp = m_name.compareTo(e.m_name);
        return nCmp;
    }

    @Override
    public String toString() {
        return "Event name: " + m_name + " " + "| Tickets available: " + m_tickets;
    }
}
