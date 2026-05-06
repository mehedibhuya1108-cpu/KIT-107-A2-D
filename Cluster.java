/**
 * Cluster.java
 *
 * KIT107 Assignment 2 -- Cluster Implementation
 *
 * @author Mehedi Hasan Bhuya, 773058
 * @version 30/04/2026
 */
/*
 * Design decision for Cluster:
 * The cluster of players within one football team is modelled as a list because
 * players must be stored in alphabetical order, searched, traversed, counted,
 * and updated when another game record for the same player is found.
 *
 * A linked list is used as the underlying data structure because the number of
 * players is not known in advance and ordered insertion can be done by changing
 * node links without shifting array elements.
 */


public class Cluster implements ClusterInterface // class implementing the interface
{
    protected Node firstPlayer; // reference to the first node (head of linked list)

    public Cluster() // constructor
    {
        firstPlayer = null; // initially the list is empty
    }

    public boolean isEmpty() // check if list is empty
    {
        return firstPlayer == null; // return true if no first node
    }

    public void addPlayerToCluster(Player p) // add player into linked list
    {
        Node current; // pointer to current node during traversal
        Node previous; // pointer to previous node
        Node newNode; // new node to insert
        Player currentPlayer; // current player object from node
        int comparison; // result of comparing names
        boolean finished; // flag to stop loop

        current = firstPlayer; // start from first node
        previous = null; // no previous at beginning
        finished = false; // loop not finished yet

        while ((current != null) && (!finished)) // traverse list
        {
            currentPlayer = (Player) current.getData(); // get player from node
            comparison = p.getName().compareTo(currentPlayer.getName()); // compare names

            if (comparison == 0) // same player found
            {
                currentPlayer.update(p); // update stats instead of adding new node
                finished = true; // stop loop
            }
            else
            {
                if (comparison < 0) // correct position found (alphabetically)
                {
                    finished = true; // stop loop
                }
                else
                {
                    previous = current; // move previous forward
                    current = current.getNext(); // move current forward
                }
            }
        }

        // if player not already updated → insert new node
        if ((current == null) || (p.getName().compareTo(((Player) current.getData()).getName()) != 0))
        {
            newNode = new Node(p); // create new node

            if (previous == null) // insert at beginning
            {
                newNode.setNext(firstPlayer); // link new node to old first
                firstPlayer = newNode; // update head
            }
            else // insert in middle or end
            {
                newNode.setNext(current); // link new node to current
                previous.setNext(newNode); // link previous to new node
            }
        }
    }

    public Player getFirstPlayer() // return first player
    {
        Player result;

        if (isEmpty()) // if list empty
        {
            result = null; // return null
        }
        else
        {
            result = (Player) firstPlayer.getData(); // get first player
        }

        return result; // return result
    }

    public int countPlayers() // count number of players
    {
        Node current; // traversal pointer
        int count; // counter

        current = firstPlayer; // start at head
        count = 0; // initialise counter

        while (current != null) // traverse list
        {
            count = count + 1; // increment count
            current = current.getNext(); // move to next node
        }

        return count; // return total count
    }

    public Player most(char x) // find player with max value
    {
        Node current; // traversal pointer
        Player currentPlayer; // current player
        Player result; // best player found
        int currentValue; // current stat value
        int maximumValue; // max stat value

        current = firstPlayer; // start from first
        result = null; // no result yet
        maximumValue = 0; // initialise max

        while (current != null) // traverse all players
        {
            currentPlayer = (Player) current.getData(); // get player
            currentValue = 0; // reset value

            // select stat based on input
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

            // check if this player has higher value
            if ((result == null) || (currentValue >= maximumValue))
            {
                result = currentPlayer; // update result
                maximumValue = currentValue; // update max value
            }

            current = current.getNext(); // move to next node
        }

        return result; // return best player
    }

    public String summary() // calculate team summary
    {
        Node current; // traversal pointer
        Player currentPlayer; // current player
        String result; // result string

        // variables to accumulate stats
        int disposals = 0;
        int marks = 0;
        int kicks = 0;
        int handballs = 0;
        int hitouts = 0;
        int tackles = 0;
        int clangers = 0;
        int freesFor = 0;
        int freesAgainst = 0;
        int goals = 0;
        int behinds = 0;
        int points = 0;

        current = firstPlayer; // start traversal
        result = ""; // initialise result

        while (current != null) // loop through players
        {
            currentPlayer = (Player) current.getData(); // get player

            // accumulate all stats
            disposals += currentPlayer.getDisposals();
            marks += currentPlayer.getMarks();
            kicks += currentPlayer.getKicks();
            handballs += currentPlayer.getHandballs();
            hitouts += currentPlayer.getHitouts();
            tackles += currentPlayer.getTackles();
            clangers += currentPlayer.getClangers();
            freesFor += currentPlayer.getFreesFor();
            freesAgainst += currentPlayer.getFreesAgainst();
            goals += currentPlayer.getGoals();
            behinds += currentPlayer.getBehinds();

            current = current.getNext(); // move to next
        }

        if (!isEmpty()) // if data exists
        {
            points = goals * 6 + behinds; // calculate total score

            // build result string
            result = "\tThere were: " + disposals + " disposals (Marks: " + marks
                    + "; kicks: " + kicks + "; handballs: " + handballs
                    + "; hitouts: " + hitouts + ")\n"
                    + "\tTackles: " + tackles + " Clangers: " + clangers + "\n"
                    + "\tFree kicks: " + freesFor + " for and " + freesAgainst + " against\n"
                    + "\tScoring: " + goals + "." + behinds + " for a total of "
                    + points + " points.";
        }

        return result; // return summary
    }

    public String toString() // convert cluster to string
    {
        Node current; // traversal pointer
        String result; // result string

        current = firstPlayer; // start from head
        result = ""; // initialise string

        while (current != null) // loop through list
        {
            result = result + ((Player) current.getData()).toString(); // append player string
            current = current.getNext(); // move to next node
        }

        return result; // return full string
    }
}