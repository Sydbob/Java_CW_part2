package mainPackage;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
/*
Project Author: Irina Kovalova S170716899
Last updated: 01-Dec-2017
Description: Java coursework - part 2 - sports club application
Created using: IntelliJ IDEA v 2017.2.5, Java 9, Windows 10 operating machine
GitHub repository: https://github.com/Sydbob/Java_CW_part2/tree/master/src/mainPackage
 */

public class Main
{

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in  = new Scanner(System.in);
        Club club = new Club();
        Util.ReadFromFile(club);
        Club.Welcome();
        char menuOpt = 'n';

        while (menuOpt != 'f')
        {
            Club.MainMenu();
            menuOpt = in.nextLine().toLowerCase().charAt(0);
            switch (menuOpt)
            {
                case 'f':
                    //exit application
                    menuOpt = 'f';
                    break;
                case 'e':
                    //display all events
                    out.println("Displaying all registered events:");
                    for (Event e: club.GetEvents())
                        out.println(e);
                    out.println("Going back to main menu");
                    break;
                case 'c':
                    //display all clients
                    out.println("Displaying all registered clients:");
                    for (Client c: club.GetClients())
                        out.println(c);
                    out.println("Going back to main menu");
                    break;
                case 'b':
                    //buy tickets
                    out.print("\nEnter client details [format: name surname e.g. 'Bob Smith'] ");
                    String details = in.nextLine();
                    //check if client exists, if yes get id
                    int clientID = club.ClientID(details);
                    //if client exists
                    if (clientID != -1)
                    {
                        char yesNo = 'y';
                        //check if client has space, if not put out a warning
                        if (!club.GetClients().get(clientID).HasSpace())
                        {
                            out.println("WARNING: This client has reached maximum allowed amount of events.");
                            out.println("WARNING: This client can ONLY purchase additional tickets to EXISTING events.");
                            yesNo = ' ';
                            while (yesNo != 'n' && yesNo!= 'y')
                            {
                                out.print("\nWould you like to continue (y/n) >> ");
                                yesNo = in.nextLine().toLowerCase().charAt(0);
                                switch (yesNo)
                                {
                                    case 'n':
                                        out.println("Transaction cancelled.");
                                        yesNo = 'n';
                                        break;
                                    case 'y':
                                        yesNo = 'y';
                                        break;
                                    default:
                                        out.println("Not a valid option, please enter y/n");
                                        break;
                                }
                            }
                        }
                        while (yesNo != 'n')
                        {
                            if (clientID != -1 && !club.GetClients().get(clientID).HasSpace())
                            {
                                out.println("This client can only purchase tickets to following events:");
                                for (Pair p : club.GetClients().get(clientID).GetChosenEvents())
                                    out.println(p.GetKey());
                            }
                            //continue if client has space available
                            out.print("\nEnter event name: ");
                            String eName = in.nextLine();
                            //check if event exists and hs tickets available
                            int eventID = club.EventID(eName);
                            while (!club.GetClients().get(clientID).ClientHasEvent(eName))
                            {
                                out.println("Client cannot but tickets to new events");
                                out.println("Enter valid event name");
                                eName = in.nextLine();
                                eventID = club.EventID(eName);
                            }
                            if (eventID != -1 && club.GetEvents().get(eventID).GetTickets() > 0)
                            {
                                out.print("\nEnter amount of tickets to buy " + "(" + club.GetEvents().get(eventID).GetTickets() + " ticket(s) available): ");
                                String temp = in.nextLine();
                                //validate amount entered
                                int eTickets = Util.ValidateInt(temp, "That's not a valid amount!", 1, club.GetEvents().get(eventID).GetTickets());
                                //add the amount if valid
                                club.GetClients().get(clientID).AddTicket(club.GetEvents().get(eventID), eTickets);
                                //update amount of available tickets
                                int newAmount = club.GetEvents().get(eventID).GetTickets() - eTickets;
                                club.GetEvents().get(eventID).SetTickets(newAmount);
                                yesNo = 'n';
                            }
                            else
                            {
                                out.println("Sorry, event doesn't exist OR has no tickets available. Cancelling transaction.");
                                yesNo = 'n';
                            }

                        }
                    }
                    //if client exists but has reached max events count
                    else
                    {
                        out.println("Sorry, only registered clients can purchase tickets.");
                        out.println("Client '" + details + "' doesn't exist. Transaction cancelled.");
                    }
                    break;
                case 'r':
                    //refund tickets
                    //prompt for name/surname
                    //check if client exists and bought at least 1 ticket...exit if not
                    //prompt for event name
                    //check if event exists and there are tickets left...exit if not
                    //ask for amount to refund
                    //remove refunded tickets from client
                    //update event details
                    break;
                default:
                    //back to main menu again
                    out.println("#############################################################");
                    out.println("Not a valid option, please select a valid option from bellow:");
                    break;
            }
        }
        out.println("\nThank you for using Sports Club application!");
        out.println("################ END OF APPLICATION ##############");
        in.close();
    }
}


