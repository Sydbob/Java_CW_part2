package mainPackage;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
/*
Project Author: Irina Kovalova S170716899
Last updated: 05-Dec-2017
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
                            //if client has reached max available events
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
                            while (!club.GetClients().get(clientID).ClientHasEvent(eName) && !club.GetClients().get(clientID).HasSpace())
                            {
                                out.println("Client cannot buy tickets to new events");
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
                    out.print("\nEnter client details [format: name surname e.g. 'Bob Smith'] ");
                    details = in.nextLine();
                    //check if client exists, if yes get id
                    clientID = club.ClientID(details);
                    //check if client has any tickets
                    if (clientID != -1 && club.GetClients().get(clientID).HasTickets())
                    {
                        out.println("Which event would you like to refund tickets to:");
                        for (Pair<Event,Integer> p : club.GetClients().get(clientID).GetChosenEvents())
                        {
                            if (p != null)
                                out.println(p.GetKey().GetName() + " " + p.GetValue());
                        }
                        out.print("Enter event name: ");
                        String refundEvent = in.nextLine();
                        //check if valid event
                        int eventID = club.EventID(refundEvent);
                        //also check if client has the event
                        boolean clientHasEvent = club.GetClients().get(clientID).ClientHasEvent(refundEvent);
                        if (eventID == -1 || !clientHasEvent)
                        {
                            out.println("Event either doesn't exist or client bought no tickets for it. Transaction cancelled.");
                            break;
                        }
                        else
                        {
                            out.println("How many tickets would you like to refund?");
                            String refund = in.nextLine();
                            //validate amount entered
                            int clientEventID = club.GetClients().get(clientID).EventID(club.GetEvents().get(eventID));
                            int max = club.GetClients().get(clientID).GetChosenEvents()[clientEventID].GetValue();
                            int rTickets = Util.ValidateInt(refund, "That's not a valid amount!", 1, max);
                            //subtract the amount from client tickets if valid
                            club.GetClients().get(clientID).RemoveTicket(club.GetEvents().get(eventID), rTickets);
                            int newAmount = club.GetEvents().get(eventID).GetTickets() + rTickets;
                            club.GetEvents().get(eventID).SetTickets(newAmount);
                        }
                    }
                    else
                        out.println("Sorry the client is either not a valid client or has no tickets purchased");
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


