package mainPackage;
import java.io.*;
import java.util.*;
import static java.lang.System.*;

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
                    int clientID = club.ClientID(details);
                    //if client exists and has space available
                    if (clientID != -1 && club.GetClients().get(clientID).HasSpace())
                    {
                        out.print("\nEnter event name: ");
                        String eName = in.nextLine();
                        //check if event exists and hs tickets available
                        int eventID = club.EventID(eName);
                        if (eventID != -1 && club.GetEvents().get(eventID).GetTickets() > 0)
                        {
                            out.print("\nEnter amount of tickets to buy: ");
                            String temp = in.nextLine();
                            int eTickets = Integer.parseInt(temp);
                            //check if amount is valid
                            //if not askt o enter different amount...or cancel
                        }
                        else
                            out.println("Sorry, event doesn't exist or has no tickets available. Cancelling transaction.");
                    }
                    //if client exists but has reached max events count
                    else if (clientID != -1)
                    {
                        out.println("WARNING: This client has reached maximum allowed amount of events.");
                        out.println("WARNING: This client can ONLY purchase additional tickets to EXISTING events.");
                        char yesNo = 'y';
                        while (yesNo != 'n')
                        {
                            out.print("\nWould you like to continue (y/n) >> ");
                            yesNo = in.nextLine().toLowerCase().charAt(0);
                            switch (yesNo) {
                                case 'n':
                                    out.println("Transaction cancelled.");
                                    break;
                                case 'y':
                                    out.println(club.GetClients().get(clientID).GetChosenEvents());
                                    out.println("Which event would you liek to add tickets to? Enter name");
                                    //add tickets
                                    break;
                                default:
                                    out.println("Not a valid option, enter 'y' or 'n'");
                            }
                        }

                    }
                    else
                    {
                        out.println("Sorry, only registered clients can purchase tickets.");
                        out.println("Client '" + details + "' doesn't exist. Transaction cancelled.");
                    }
                    //ask for amount to add
                    //add new amount to client
                    //update event details
                    //possibly display the new info
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


