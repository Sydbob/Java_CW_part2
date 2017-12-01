package mainPackage;
import static java.lang.System.*;
import java.util.ArrayList;

public class Club {

    private ArrayList<Client> m_clients;
    private ArrayList<Event> m_events;

    Club(){ m_clients = new ArrayList<Client>(); m_events = new ArrayList<Event>();}
    //second constructor needs to be replaces later....
    Club(Client c,Event e) {m_events.add(e); m_clients.add(c);}


    public ArrayList<Client> GetClients() {return m_clients;}
    public ArrayList<Event> GetEvents() {return m_events;}
    public void SetClients(SortedArrayList<Client> clients) {m_clients = clients;}
    public void SetEvents(SortedArrayList<Event> events) {m_events = events;}
    public void AddEvent(Event e) {m_events.add(e);}
    public void AddClient(Client c) {m_clients.add(c);}

    //add client to club
    //add event to club
    public int ClientID(String details)
    {
        Client client = new Client(details);
        int exists = -1;
        for (int i = 0; i < m_clients.size(); i ++)
        {
            if (client.equals(m_clients.get(i)))
                exists = i;
        }
        return exists;
    }

    public int EventID(String name)
    {
        int exists = -1;
        for (int i = 0; i < m_events.size(); i++)
        {
            if (name.equals(m_events.get(i).GetName()))
                exists = i;
        }
        return exists;
    }
    //display menus
    public static void Welcome()
    {
        out.println("#################################");
        out.println("Welcome to Sports Events Software");
        out.println("#################################");
    }

    public static void MainMenu()
    {
        out.println("\t\t##MainMenu##");
        out.println("\nYou're in the main menu. Choose an option: ");
        out.println("'f' - to finish running the program ");
        out.println("'e' - to display information about all the events. ");
        out.println("'c' - to display about all the clients");
        out.println("'b' - to buy tickets [only available to registered clients] ");
        out.println("'r' - to return a tickets [only available to registered clients] ");
        out.print("Your choice >> ");
    }

}
