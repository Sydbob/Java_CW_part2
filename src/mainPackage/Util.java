package mainPackage;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.System.*;

/**
 * Class containing various static 'utility' methods
 */
public class Util {


    public static int ValidateInt (String value, String errorText, int minRange, int maxRange)
    {
        Scanner in = new Scanner(System.in);
        int parsedValue = 0;
        boolean valid = false;
        boolean parsed = false;
        //main loop that will run both int and range test
        while (!valid)
        {
            //loop that will run parse test while it keeps failing
            while(!parsed)
            {
                try
                {
                    Integer.parseInt(value);
                    parsed = true; //exit the loop if it parsed successfully
                }
                catch (NumberFormatException e)
                {
                    System.out.print(errorText + "(valid amounts is "+ minRange + "-" + maxRange + "): ");
                    value = in.nextLine();
                    parsed= false;
                }
            }
            //set a new value to the result of the parse to be able to compare it to a range
            parsedValue = Integer.parseInt(value);
            if (parsedValue < minRange || parsedValue > maxRange)
            {
                System.out.print(errorText + "(valid amount is "+ minRange + "-" + maxRange + "): ");
                value = in.nextLine();
                //back to the start of the main loop if range check failed
                valid = false;
                parsed = false;
            }
            else
            {
                //exit the main loop if successful
                valid = true;
            }
        }
        return parsedValue;
    }


    public static void ReadFromFile(Club club) throws FileNotFoundException
    {
        Scanner inFile = new Scanner(new FileReader("D:\\Java Projects\\Java_CW_part2\\input.txt"));
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

    public static void PrintLetter(Client client, String eName) throws FileNotFoundException
    {
        PrintWriter outFile = new PrintWriter("D:\\Java Projects\\Java_CW_part2\\output.txt");
        outFile.println(" Dear " + client.GetName() + " " + client.GetSurname());
        outFile.println("");
        outFile.println("Unfortunately your request for tickets for " + eName + " event was not complete.");
        outFile.println("The event has either sold out or isn't being organised by our club. We apologise for any inconvenience caused. ");
        outFile.println("");
        outFile.println("Kind regards,");
        outFile.println("SportsEvents Club.");
        outFile.close();
    }
}
