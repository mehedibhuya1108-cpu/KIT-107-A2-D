echo "# KIT-107-A2-D" >> README.md
/**
# KIT107 Assignment 2 – AFL Player Statistics

## Student Details

Name: Mehedi Hasan Bhuya
Student ID: 773058

## Description

This program reads AFL player statistics from a CSV file and analyses the data for a selected year. It stores the data using linked lists and produces outputs such as player statistics, histograms, and team summaries.

## How to Compile

Open terminal in the project folder and run:
javac *.java

## How to Run

After compiling, run:
java AssigTwo126

## Files Included

* AssigTwo126.java (main program)
* Collection.java (stores teams)
* Cluster.java (stores players per team)
* Node.java (linked list structure)
* Player.java (player data)
* FileHandler.java (reads CSV file)
* Interfaces (.java files)
* stats.csv (dataset)

## Design Decisions

* Linked lists are used because the number of teams and players is unknown.
* Data is stored in alphabetical order for easy searching.
* Clusters represent teams, and each cluster contains players.

## Assumptions

* The CSV file format is correct.
* User inputs a valid year and team name.
* Data contains no missing values.

## Notes

* The program ignores data outside the selected year.
* Case-insensitive search is used for team names.
*/