/**
 * Cluster.java
 * 
 * KIT107 Assignment 2 -- Cluster Implementation
 * 
 * @author <<Mehedi Hasan Bhuya, ID number 773058>>
 * @version	<<1/05/2026>>
 */
/*
 * Answers to design questions (a) and (b):
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

    {
COMPLETE ME!
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
COMPLETE ME! // to get past the compiler, use: return null;
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
COMPLETE ME! // to get past the compiler, use: return 0;
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
COMPLETE ME! // to get past the compiler, use: return null;
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
COMPLETE ME! // to get past the compiler, use: return "";
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
COMPLETE ME! // to get past the compiler, use: return "";
    }
}