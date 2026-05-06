/**
 * Collection.java
 *
 * KIT107 Assignment 2 -- Collection Implementation
 *
 * @author Mehedi Hasan Bhuya, 773058
 * @version 04/05/2026
 */
/*
 * Design decision for Collection:
 * The collection of football teams is modelled as a list because teams must be
 * stored alphabetically, searched by team name, traversed for reports, and added
 * as new teams are encountered in the data file.
 *
 * A linked list is used as the underlying data structure because the number of
 * teams is treated as unknown from the design information and ordered insertion
 * can be performed without shifting array elements.
 */


public class Collection implements CollectionInterface // this class stores all teams (each team is a cluster)
{
    protected Node firstTeam; // this is the first node of the linked list (start of teams list)


   
    public Collection() // constructor
    {
        firstTeam = null; // at the beginning there are no teams
    }

    public boolean isEmpty() // checks if the collection is empty
    {
        return firstTeam == null; // if firstTeam is null, no teams exist
    }

    public void addPlayerToCollection(Player p) // adds a player into the correct team
    {
        Node current; // used to move through the team list
        Node previous; // keeps track of previous node
        Node newNode; // new node to insert
        Cluster currentCluster; // current team cluster
        Cluster newCluster; // new team cluster if needed
        String currentTeam; // name of current team
        int comparison; // result of comparing team names
        boolean added; // flag to check if player is added

        current = firstTeam; // start from first team
        previous = null; // no previous at start
        added = false; // player not added yet

        while ((current != null) && (!added)) // loop through teams
        {
            currentCluster = (Cluster) current.getData(); // get current cluster
            currentTeam = currentCluster.getFirstPlayer().getTeam(); // get team name
            comparison = p.getTeam().compareTo(currentTeam); // compare team names

            if (comparison == 0) // if team already exists
            {
                currentCluster.addPlayerToCluster(p); // add player to that team
                added = true; // mark as added
            }
            else
            {
                if (comparison < 0) // correct position found (alphabetical order)
                {
                    newCluster = new Cluster(); // create new team
                    newCluster.addPlayerToCluster(p); // add player to new team
                    newNode = new Node(newCluster); // create node for new team

                    if (previous == null) // insert at beginning
                    {
                        newNode.setNext(firstTeam); // link to old first
                        firstTeam = newNode; // update first team
                    }
                    else // insert in middle
                    {
                        newNode.setNext(current); // link to current
                        previous.setNext(newNode); // link previous to new node
                    }

                    added = true; // mark as added
                }
                else
                {
                    previous = current; // move previous forward
                    current = current.getNext(); // move current forward
                }
            }
        }

        if (!added) // if team not found, add at end
        {
            newCluster = new Cluster(); // create new cluster
            newCluster.addPlayerToCluster(p); // add player
            newNode = new Node(newCluster); // create node

            if (previous == null) // if list was empty
            {
                firstTeam = newNode; // set as first team
            }
            else
            {
                previous.setNext(newNode); // add to end
            }
        }
    }

    public void showPlayerHistogram() // shows number of players per team
    {
        Node current; // traversal pointer
        Cluster currentCluster; // current team cluster
        Player firstPlayer; // first player in cluster
        String teamName; // team name
        String stars; // stars for histogram
        int count; // number of players
        int i; // loop counter

        System.out.println("Count of players per team:"); // heading

        if (isEmpty()) // if no data
        {
            System.out.println("No data!"); // print message
        }
        else
        {
            current = firstTeam; // start from first team

            while (current != null) // go through all teams
            {
                currentCluster = (Cluster) current.getData(); // get cluster
                firstPlayer = currentCluster.getFirstPlayer(); // get first player
                teamName = firstPlayer.getTeam(); // get team name
                count = currentCluster.countPlayers(); // count players
                stars = ""; // start with empty stars

                for (i = 0; i < count; i++) // loop to create stars
                {
                    stars = stars + "*"; // add one star each time
                }

                System.out.println(String.format("%22s | %s %d", teamName, stars, count)); // print result

                current = current.getNext(); // move to next team
            }
        }
    }

    public String most(char x) // finds player with highest stat across all teams
    {
        Node current; // traversal pointer
        Cluster currentCluster; // current cluster
        Player currentPlayer; // current player
        Player resultPlayer; // best player found
        String result; // result string
        int currentValue; // current stat value
        int maximumValue; // highest value

        current = firstTeam; // start from first team
        resultPlayer = null; // no best player yet
        result = ""; // empty result
        maximumValue = 0; // start max at 0

        if (isEmpty()) // if no data
        {
            result = "No data!"; // return message
        }
        else
        {
            while (current != null) // go through all teams
            {
                currentCluster = (Cluster) current.getData(); // get cluster
                currentPlayer = currentCluster.most(x); // get best player in cluster
                currentValue = 0; // reset value

                // check which stat is required
                if (x == 'a')
                    currentValue = currentPlayer.getFreesAgainst();
                else if (x == 'c')
                    currentValue = currentPlayer.getClangers();
                else if (x == 'd')
                    currentValue = currentPlayer.getDisposals();
                else if (x == 'g')
                    currentValue = currentPlayer.getGoals();
                else if (x == 'm')
                    currentValue = currentPlayer.getGames();

                // update best player if needed
                if ((resultPlayer == null) || (currentValue >= maximumValue))
                {
                    resultPlayer = currentPlayer;
                    maximumValue = currentValue;
                }

                current = current.getNext(); // move to next team
            }

            result = resultPlayer.toString(); // convert player to string
        }

        return result; // return result
    }

    public void summarise(String t) // prints summary for a specific team
    {
        Node current; // traversal pointer
        Cluster currentCluster; // current cluster
        Player firstPlayer; // first player of cluster
        boolean found; // checks if team found

        current = firstTeam; // start from first team
        found = false; // not found yet

        if (isEmpty()) // if no data
        {
            System.out.println("No data!"); // print message
        }
        else
        {
            while ((current != null) && (!found)) // search for team
            {
                currentCluster = (Cluster) current.getData(); // get cluster
                firstPlayer = currentCluster.getFirstPlayer(); // get first player

                if (firstPlayer.getTeam().equalsIgnoreCase(t)) // match team name
                {
                    System.out.println(currentCluster.summary()); // print summary
                    found = true; // mark found
                }
                else
                {
                    current = current.getNext(); // move to next
                }
            }

            if (!found) // if team not found
            {
                System.out.println("Team (" + t + ") not found!"); // print message
            }
        }
    }

    public String toString() // prints all players in all teams
    {
        Node current; // traversal pointer
        String result; // final string

        current = firstTeam; // start from first
        result = ""; // empty string

        while (current != null) // loop through teams
        {
            result = result + ((Cluster) current.getData()).toString(); // add cluster data
            current = current.getNext(); // move to next team
        }

        return result; // return full string
    }
}