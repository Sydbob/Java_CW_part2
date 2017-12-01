package mainPackage;
import static java.lang.System.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.System.*;

public class Util {



    public static void ReadFromFile(Club club) throws FileNotFoundException
    {
        Scanner inFile = new Scanner(new FileReader("D:\\Java Projects\\Java_CW_part2\\input.txt"));
        PrintWriter outFile = new PrintWriter("D:\\Java Projects\\Java_CW_part2\\output.txt");
        int numOfEvents = 0;
        int numOfClients =0;
        while(inFile.hasNextLine())
        {
            //read as a line to avoid leftover empty string chars, parse to int
            numOfEvents = Integer.parseInt(inFile.nextLine());
            //read event details, make new Event obj, add it to club's list of events
            for (int i =0; i < numOfEvents; i++)
            {
                String eventName = inFile.nextLine();
                int eventTickets = Integer.parseInt(inFile.nextLine());
                Event event = new Event(eventName, eventTickets);
                club.AddEvent(event);
            }
            //done with events now onto reading clients
            //first clearing the remaining empty string character
            String clear = inFile.nextLine();
            numOfClients = Integer.parseInt(inFile.nextLine());
            for (int i = 0; i < numOfClients; i++)
            {
                String info = inFile.nextLine();
                Client client = new Client(info);
                club.AddClient(client);
            }
        }
        //close the inFile reader once done
        inFile.close();
    }

}
