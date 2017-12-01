package mainPackage;
import java.io.*;
import java.util.*;
import static java.lang.System.*;

public class Main
{

    public static void main(String[] args) throws FileNotFoundException {
        //change to relative path later
        Club club = new Club();
        Util.ReadFromFile(club);
        /*
        Event e1 = new Event("Football", 50);
        Event e2 = new Event("Tennis", 100);
        Event e3 = new Event("Volleyball", 40);
        Pair<Event, Integer>[] arr = new Pair[3];
        Pair<Event, Integer> p1 = new Pair(e1, 4);
        Pair<Event, Integer> p3 = new Pair(e3, 10);
        //Pair<Event, Integer> p2 = new Pair(e2, 5);
        arr[0] = p1;
        arr[2] = p3;
        Client c = new Client("Bob", "Williams", arr);
        c.AddTicket(e2, 3);
        c.AddTicket(e2, 4);
        c.AddTicket(e2, 4);
        */

        Club.Welcome();
        Club.MainMenu();

        out.println(club.GetClients().get(0));
        out.println(club.GetEvents().get(0));


    }
}


