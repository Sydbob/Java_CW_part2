package mainPackage;
import static java.lang.System.*;

public class Club {

    private SortedArrayList<Client> m_clients;
    private SortedArrayList<Event> m_events;

    Club(){}
    //second constructor needs to be replaces later....
    Club(Client c,Event e) {m_events.add(e); m_clients.add(c);}


    public SortedArrayList<Client> GetClients() {return m_clients;}
    public SortedArrayList<Event> GetEvents() {return m_events;}
    public void SetClients(SortedArrayList<Client> clients) {m_clients = clients;}
    public void SetEvents(SortedArrayList<Event> events) {m_events = events;}

    //add client to club
    //add event to club

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
        out.println("Your choice > ");
    }

}
