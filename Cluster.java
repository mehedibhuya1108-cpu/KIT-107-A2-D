



/**
 * Cluster.java
 *
 * KIT107 Assignment 2 -- Cluster Implementation
 *
 *  * @author Mehedi Hasan Bhuya, 773058
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


public class Cluster implements ClusterInterface
{
    protected Node firstPlayer;




    /**
     * Constructor
     *
     * Precondition: None
     * Postcondition: The new instance will have its instance variable(s)
     *                  initialised.
     * Informally: Initialise the cluster of players.
     */
        public Cluster()
    {
        firstPlayer = null;
    }


    /**
     * isEmpty()
     *
     * @return boolean -- whether the cluster is empty
     *
     * Precondition: None
     * Postcondition: True is returned if the Cluster is empty; false is
     *                  returned otherwise.
     * Informally: Check whether the Cluster is empty.
     */
        public boolean isEmpty()
    {
        return firstPlayer == null;
    }


    /**
     * addPlayerToCluster()
     *
     * @param p Player -- the player to add to this cluster
     *
     * Precondition: The given Player parameter has been constructed.
     * Postcondition: The given Player has been added to the Cluster of
     *                  players preserving the alphabetical order and
     *                  secondarily ordering by games played.
     * Informally: Add a player to the Cluster.
     */
        public void addPlayerToCluster(Player p)
    {
        Node current;
        Node previous;
        Node newNode;
        Player currentPlayer;
        int comparison;
        boolean finished;


        current = firstPlayer;
        previous = null;
        finished = false;


        while ((current != null) && (!finished))
        {
            currentPlayer = (Player) current.getData();
            comparison = p.getName().compareTo(currentPlayer.getName());


            if (comparison == 0)
            {
                currentPlayer.update(p);
                finished = true;
            }
            else
            {
                if (comparison < 0)
                {
                    finished = true;
                }
                else
                {
                    previous = current;
                    current = current.getNext();
                }
            }
        }


        if ((current == null) || (p.getName().compareTo(((Player) current.getData()).getName()) != 0))
        {
            newNode = new Node(p);


            if (previous == null)
            {
                newNode.setNext(firstPlayer);
                firstPlayer = newNode;
            }
            else
            {
                newNode.setNext(current);
                previous.setNext(newNode);
            }
        }
    }


    /**
     * getFirstPlayer()
     *
     * @return Player -- the first player in the cluster
     *
     * Precondition: None
     * Postcondition: the first player in the cluster is returned if the
     *                  cluster is non-empty; null is returned otherwise.
     * Informally: Get the first player in thge cluster.
     */
        public Player getFirstPlayer()
    {
        Player result;


        if (isEmpty())
        {
            result = null;
        }
        else
        {
            result = (Player) firstPlayer.getData();
        }


        return result;
    }


   /**
     * countPlayers()
     *
     * @return int -- the number of players in the cluster
     *
     * Precondition: None
     * Postcondition: The number of players in the Cluster has been counted and
     *                  returned.
     * Informally: Produce a count of players within the current Cluster.
     */
        public int countPlayers()
    {
        Node current;
        int count;


        current = firstPlayer;
        count = 0;


        while (current != null)
        {
            count = count + 1;
            current = current.getNext();
        }


        return count;
    }


    /**
     * most()
     *
     * @param x char -- the category to search ('g'oals, 'd'isposals, 'c'langers,
     *                      frees 'a'gainst, or ga'm'es)
     * @return Player -- the player with the highest value in the specified
     *                      category
     *
     * Precondition: None.
     * Postcondition: All players in the Cluster are searched for the given maximum
     *                  in the category indicated (x) and the player with the
     *                  highest is returned.  If there are multiple players then the
     *                  last found is returned; if there are no data then null is
     *                  returned.
     * Informally: Find the player in the Cluster with the maximum value in the
     *                  given category.
     */
        public Player most(char x)
    {
        Node current;
        Player currentPlayer;
        Player result;
        int currentValue;
        int maximumValue;


        current = firstPlayer;
        result = null;
        maximumValue = 0;


        while (current != null)
        {
            currentPlayer = (Player) current.getData();
            currentValue = 0;


            if (x == 'a')
            {
                currentValue = currentPlayer.getFreesAgainst();
            }
            else if (x == 'c')
            {
                currentValue = currentPlayer.getClangers();
            }
            else if (x == 'd')
            {
                currentValue = currentPlayer.getDisposals();
            }
            else if (x == 'g')
            {
                currentValue = currentPlayer.getGoals();
            }
            else if (x == 'm')
            {
                currentValue = currentPlayer.getGames();
            }


            if ((result == null) || (currentValue >= maximumValue))
            {
                result = currentPlayer;
                maximumValue = currentValue;
            }


            current = current.getNext();
        }


        return result;
    }


    /**
     * summary()
     *
     * @return String -- the summary of statistics for the current Cluster (i.e. team)
     *
     * Precondition: None
     * Postcondition: A String has been returned which is the summary of the current
     *                  team's statistics, or "" if the cluster is empty.
     * Informally: Produce a summary of the current Cluster.
     */
        public String summary()
    {
        Node current;
        Player currentPlayer;
        String result;
        int disposals;
        int marks;
        int kicks;
        int handballs;
        int hitouts;
        int tackles;
        int clangers;
        int freesFor;
        int freesAgainst;
        int goals;
        int behinds;
        int points;


        current = firstPlayer;
        result = "";
        disposals = 0;
        marks = 0;
        kicks = 0;
        handballs = 0;
        hitouts = 0;
        tackles = 0;
        clangers = 0;
        freesFor = 0;
        freesAgainst = 0;
        goals = 0;
        behinds = 0;
        points = 0;


        while (current != null)
        {
            currentPlayer = (Player) current.getData();


            disposals = disposals + currentPlayer.getDisposals();
            marks = marks + currentPlayer.getMarks();
            kicks = kicks + currentPlayer.getKicks();
            handballs = handballs + currentPlayer.getHandballs();
            hitouts = hitouts + currentPlayer.getHitouts();
            tackles = tackles + currentPlayer.getTackles();
            clangers = clangers + currentPlayer.getClangers();
            freesFor = freesFor + currentPlayer.getFreesFor();
            freesAgainst = freesAgainst + currentPlayer.getFreesAgainst();
            goals = goals + currentPlayer.getGoals();
            behinds = behinds + currentPlayer.getBehinds();


            current = current.getNext();
        }


        if (!isEmpty())
        {
            points = goals * 6 + behinds;


            result = "\tThere were: " + disposals + " disposals (Marks: " + marks
                    + "; kicks: " + kicks + "; handballs: " + handballs
                    + "; hitouts: " + hitouts + ")\n"
                    + "\tTackles: " + tackles + " Clangers: " + clangers + "\n"
                    + "\tFree kicks: " + freesFor + " for and " + freesAgainst + " against\n"
                    + "\tScoring: " + goals + "." + behinds + " for a total of "
                    + points + " points.";
        }


        return result;
    }


    /**
     * toString()
     *
     * @return String -- printable form of the Cluster of players
     *
     * Precondition: None
     * Postcondition: A printable (String) form of the players data is
     *                  returned, one player per line.  If there are no
     *                  players then "" is returned.
     * Informally: Convert the Cluster of players data to a multi-line
     *                  String.
     */
        public String toString()
    {
        Node current;
        String result;


        current = firstPlayer;
        result = "";


        while (current != null) // loop through the linked list of players
        {
            result = result + ((Player) current.getData()).toString(); //
            current = current.getNext();
        }


        return result;
    }
}

