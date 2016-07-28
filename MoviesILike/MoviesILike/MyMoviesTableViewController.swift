//
//  MyMoviesTableViewController.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

class MyMoviesTableViewController: UITableViewController,
    AddMovieViewControllerProtocol {
    
    // Obtain the object reference to the UITableView object
    @IBOutlet var movieTableView: UITableView!
    
    // Obtain the object reference to the App Delegate object
    let applicationDelegate: AppDelegate = UIApplication.sharedApplication().delegate as AppDelegate
    
    //---------- Create and Initialize the Arrays -----------------------
    
    var genreNames = [String]()
    var dict_movies2: NSMutableDictionary = NSMutableDictionary.alloc()
    
    var movieName: String = ""
    var youtubeMovieTrailer: String = ""
    
    // dataObjectToPass is the data object to pass to the downstream view controller
    var dataObjectToPass: [String] = ["", ""]
    
    /*
    -----------------------
    MARK: - View Life Cycle
    -----------------------
    */
    
    override func viewDidLoad() {
    super.viewDidLoad()
    
    // Preserve selection between presentations
    self.clearsSelectionOnViewWillAppear = false
    
    // Set up the Edit button on the left of the navigation bar to enable editing of the table view rows
    self.navigationItem.leftBarButtonItem = self.editButtonItem()
    
    // Set up the Add button on the right of the navigation bar to call the addCity method when tapped
    let addButton: UIBarButtonItem = UIBarButtonItem(barButtonSystemItem: .Add, target: self, action: "addMovie:")
    self.navigationItem.rightBarButtonItem = addButton
    
    /*
    allKeys returns a new array containing the dictionary’s keys as of type AnyObject.
    Therefore, typecast the AnyObject type keys to be of type String.
    The keys in the array are *unordered*; therefore, they need to be sorted.
    */
    genreNames = applicationDelegate.dict_Genre.allKeys as [String]
    
    // Sort the country names within itself in alphabetical order
    genreNames.sort { $0 < $1 }
    }
    
    /*
    -----------------------
    MARK: - Add City Method
    -----------------------
    */
    
    // The addCity method is invoked when the user taps the Add button created in viewDidLoad() above.
    func addMovie(sender: AnyObject) {
    
    // Perform the segue named AddCity
    performSegueWithIdentifier("AddMovie", sender: self)
    
    }
    
    
    /**********************************************
    MARK: - Table View Data Source Protocol Methods
    **********************************************/
    
    // We are implementing a Grouped table view style as we selected in the storyboard file.
    
    /*
    ---------------------------------------
    Return Number of Sections in Table View
    ---------------------------------------
    */
    
    // Each table view section corresponds to a country
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
    
    return genreNames.count
    }
    
    /*
    --------------------------------
    Return Number of Rows in Section
    --------------------------------
    */
    
    // Number of rows in a given country (section) = Number of Cities in the given country (section)
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    
    // Obtain the name of the given country
    var givenGenreName = genreNames[section]
    
    // Obtain the list of cities in the given country as Objective-C array
    var genre: AnyObject? = applicationDelegate.dict_Genre[givenGenreName]
    
    // Convert the Objective-C array to Swift array
    var moviesOfGivenGenre = genre!
    
    
    //var movieNames1 = citiesOfGivenCountry.allKeys as [String]
    
    
    return moviesOfGivenGenre.count
    }
    
    /*
    ----------------------------
    Set Title for Section Header
    ----------------------------
    */
    
    // Set the table view section header to be the country name
    override func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String {
    
        return genreNames[section]
    }
    
    /*
    ----------------------------
    Set Title for Section Footer
    ----------------------------
    */
    
    
    /*
    ------------------------------------
    Prepare and Return a Table View Cell
    ------------------------------------
    */
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
    
    let cell = tableView.dequeueReusableCellWithIdentifier("MovieView", forIndexPath: indexPath) as UITableViewCell
    
        
    var sectionNumber = indexPath.section
    var rowNumber = indexPath.row
    
    // Obtain the name of the given country
    var givenGenreName = genreNames[sectionNumber]
    
    /*
    Note that city names must not be sorted. The order shows how favorite the city is.
    The higher the order the more favorite the city is. The user specifies the ordering
    in the Edit mode by moving a row from one location to another for the same country.
    */
    
    // Obtain the list of cities in the given country as Objective-C array
    var movies: AnyObject? = applicationDelegate.dict_Genre[givenGenreName]
        
    var moviesInfo = movies as NSMutableDictionary
    
    var moviesRank = moviesInfo.allKeys as [String]
    
    moviesRank.sort {$0 < $1}
    
    var movieNum = moviesRank[rowNumber]
    
    //var movieData  = [String]()
    dict_movies2 = movies! as NSMutableDictionary
    
    var movieData = dict_movies2[movieNum] as [String]
    
    
    // Convert the Objective-C array to Swift array
    //var citiesOfGivenCountry = cities! as [String]
    
    // Set the cell title to be the city name
    cell.textLabel.text = movieData[0]
    cell.detailTextLabel?.text = movieData[1]
        
    if(movieData[3] == "PG-13")
    {
        cell.imageView.image = UIImage(named: "PG13.png")
    }
    else if movieData[3] == "PG"
    {
        cell.imageView.image = UIImage(named: "PG.png")
    }
    else if movieData[3] == "R"
    {
        cell.imageView.image = UIImage(named: "R.png")
    }
    else if movieData[3] == "G"
    {
        cell.imageView.image = UIImage(named: "G.png")
    }
    else
    {
        cell.imageView.image = UIImage(named: "NC17.png")
    }
    
    return cell
    }
    
    // Informs the table view delegate that the specified row is selected.
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
    
    var sectionNumber = indexPath.section
    
    var rowNumber: Int = indexPath.row    // Identify the row number
    
    var givenGenreName = genreNames[sectionNumber]
    // Obtain the list of cities in the given country as Objective-C array
    var movies: AnyObject? = applicationDelegate.dict_Genre[givenGenreName]
    
    var moviesRank = movies?.allKeys as [String]
    
    moviesRank.sort {$0 < $1}
    
    var movieNum = moviesRank[rowNumber]
    
    //var movieData  = [String]()
    dict_movies2 = movies! as NSMutableDictionary
    
    var movieData = dict_movies2[movieNum] as [String]
    
    movieName = movieData[0]
    youtubeMovieTrailer = movieData[2]
    performSegueWithIdentifier("Trailer", sender: self)
    }
    /*
    ------------------------------
    Allow Editing of Rows (Cities)
    ------------------------------
    */
    
    // We allow each row (city) of the table view to be editable, i.e., deletable or movable
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
    
    return true
    }
    
    
    func addMovieViewController(controller: AddMovieViewController, didFinishWithSave save: Bool) {
    
    if save {
    
    // Get the country name entered by the user on the AddCityViewController's UI
    var genreNameEntered: String = controller.genreNameTextField.text
    
    // Get the city name entered by the user on the AddCityViewController's UI
    var movieNameEntered: String = controller.movieNameTextField.text
    var topStarsEntered: String = controller.topStarsTextField.text
    var ratingsEntered: String = controller.rating
    var youtubeTrailerEntered: String = controller.movieTrailerTextField.text
        
        var movieInfo: [String] = ["Movie Name", "Actors", "Genre", "Ratings"]
        movieInfo[0] = movieNameEntered
        movieInfo[1] = topStarsEntered
        movieInfo[2] = youtubeTrailerEntered
        movieInfo[3] = ratingsEntered
    if contains(genreNames, genreNameEntered) {
    
    
    var movies: AnyObject? = applicationDelegate.dict_Genre[genreNameEntered]
    
    var moviesRank = movies?.allKeys as [String]
    
    moviesRank.sort {$0 < $1}
    
    moviesRank.count
    //var movieNum = moviesRank[moviesRank.count]
    
    //var movieData  = [String]()
    dict_movies2 = movies! as NSMutableDictionary
    
    //var movieData = dict_movies2[movieNum] as [String]
    
    
    /*
    Note that city names must not be sorted. The order shows how favorite the city is.
    The higher the order the more favorite the city is. The user specifies the ordering
    in the Edit mode by moving a row from one location to another for the same country.
    
    Add the new city name to the end of the list.
    */
    dict_movies2.setValue(movieInfo, forKey: "\(moviesRank.count + 1 )")
    
    // Update the new list of cities for the country in the Objective-C dictionary
    applicationDelegate.dict_Genre.setValue(dict_movies2, forKey: genreNameEntered)
    
    } else {   // The entered country name does not exist in the current dictionary
    

    // Create an array containing cityNameEntered as the only city of the new country
        var newGenreNameEntered = NSMutableDictionary.init()
        // Update the new list of cities for the country in the Objective-C dictionary
    // Note that since the country name key does not exist, we use setObject instead of setValue
        newGenreNameEntered.setObject(movieInfo, forKey: "1")
        applicationDelegate.dict_Genre.setObject(newGenreNameEntered, forKey: genreNameEntered)
        
    }
    
    genreNames = applicationDelegate.dict_Genre.allKeys as [String]
    
    // Sort the country names within itself in alphabetical order
     genreNames.sort { $0 < $1 }
    
    // Reload the rows and sections of the Table View countryCityTableView
        
        viewDidLoad()
        movieTableView.reloadData()
    }
    
    self.navigationController!.popViewControllerAnimated(true)
    }
    
    
    
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
    
    if editingStyle == .Delete {   // Handle the Delete action
    
    // Obtain the name of the country of the city to be deleted
    var genreOfMovieToDelete = genreNames[indexPath.section]
    
    // Obtain the list of cities in the country as Objective-C array
    var movies: AnyObject? = applicationDelegate.dict_Genre[genreOfMovieToDelete]
    
    // Convert the Objective-C array to Swift array
    dict_movies2 = movies! as NSMutableDictionary
    
    var moviesRank = movies?.allKeys as [String]
    
    moviesRank.sort {$0 < $1}
    
    
    // Obtain the name of the city to be deleted
    var movieToDelete = moviesRank[indexPath.row]
    //print(indexPath.row)
    // Delete the identified city in the Objective-C array
    dict_movies2.removeObjectForKey(movieToDelete)
    
    //dict_movies2.
    
    if movies!.count == 0 {
    // If no city remains in the array after deletion, then we need to also delete the country
    // in the Objective-C dictionary
    applicationDelegate.dict_Genre.removeObjectForKey(genreOfMovieToDelete)
    
    // Since the dictionary has been changed, obtain the country names again
    genreNames = applicationDelegate.dict_Genre.allKeys as [String]
    
    // Sort the country names within itself in alphabetical order
    genreNames.sort { $0 < $1 }
    }
    else {
    // At least one more city remains in the array; therefore, the country stays.
    
    // Update the new list of cities for the country in the Objective-C dictionary
    applicationDelegate.dict_Genre.setValue(dict_movies2, forKey: genreOfMovieToDelete)
    }
    
    // Reload the rows and sections of the Table View countryCityTableView
    movieTableView.reloadData()
    }
    }
    
    
    /*******************************************
    MARK: - Table View Delegate Protocol Methods
    *******************************************/
    
    
    
    /*
    This method is invoked to carry out the row (city) movement after the method
    tableView: targetIndexPathForMoveFromRowAtIndexPath: toProposedIndexPath: approved the move.
    */
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {
    
        var genreName = genreNames[fromIndexPath.section]
        
        // Obtain the list of cities in the country as Objective-C array
        var movies: AnyObject? = applicationDelegate.dict_Genre[genreName]
        
        // Convert the Objective-C array to Swift array
        dict_movies2 = movies! as NSMutableDictionary
        
        var moviesRank = movies?.allKeys as [String]
        
        moviesRank.sort {$0 < $1}
        
        // Row number to move FROM
        var rowNumberFrom   = fromIndexPath.row
        
        // Row number to move TO
        var rowNumberTo     = toIndexPath.row
        
        var movieToMove = dict_movies2["\(rowNumberFrom + 1)"] as [String]
        
        if rowNumberFrom > rowNumberTo {
            // Movement is from lower part of the list to the upper part
            var i = 0
            //print(rowNumberTo)
            for(i = rowNumberTo; i < rowNumberFrom + 1; i++)
            {
                
                var temp = dict_movies2["\(i+1)"] as [String]
                dict_movies2.setValue(movieToMove, forKey: "\(i+1)")
                movieToMove = temp
                //print(i)
            }
            
        } else {
            var i = 0
            //print(rowNumberTo)
            for(i = rowNumberTo; i + 1 > rowNumberFrom; i--)
            {
                var temp = dict_movies2["\(i + 1)"] as [String]
                dict_movies2.setValue(movieToMove, forKey: "\(i + 1)")
                movieToMove = temp
                
            }
        }
        
        // Update the new list of cities for the country in the Objective-C dictionary
        //applicationDelegate.dict_Genre.setValue(citiesOfTheCountry, forKey: countryName)
        
        // Update the new list of cities for the country in the Objective-C dictionary
        applicationDelegate.dict_Genre.setValue(dict_movies2, forKey: genreName)
        movieTableView.reloadData()
    
    // No need to reload the table view data since the view is updated automatically
    }
    /*
    -------------------------
    Movement of City Approval
    -------------------------
    */
    
    // This method is invoked when the user attempts to move a row (city)
    override func tableView(tableView: UITableView, targetIndexPathForMoveFromRowAtIndexPath sourceIndexPath: NSIndexPath, toProposedIndexPath proposedDestinationIndexPath: NSIndexPath) -> NSIndexPath {
    
    var countryFrom = genreNames[sourceIndexPath.section]
    var countryTo = genreNames[proposedDestinationIndexPath.section]
    
    if countryFrom != countryTo {
    
    // The user attempts to move a city from one country to another, which is prohibited
    
    // Instantiate an alert view object
    var alertView = UIAlertView()
    
    alertView.title = "Move Not Allowed!"
    alertView.message = "Order movies according to your liking only within the same genre!"
    alertView.delegate = nil
    alertView.addButtonWithTitle("OK")
    
    alertView.show()
    
    return sourceIndexPath  // The row (city) movement is denied
    }
    else {
    return proposedDestinationIndexPath  // The row (city) movement is approved
    }
    
    }
    /*
    -------------------------
    MARK: - Prepare For Segue
    -------------------------
    */
    
    // This method is called by the system whenever you invoke the method performSegueWithIdentifier:sender:
    // You never call this method. It is invoked by the system.
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
    
    if segue.identifier == "AddMovie" {
    var addMovieViewController: AddMovieViewController = segue.destinationViewController as AddMovieViewController
    
    // Under the Delegation Design Pattern, set the addCityViewController's delegate to be self
        addMovieViewController.delegate = self
    }
    else if segue.identifier == "Trailer" {
    
    var trailerView: MovieTrailerViewController = segue.destinationViewController as MovieTrailerViewController
    
    //Pass the data objects to the destination view controller object
    // NFLWebViewController.countryName = countryName
    
    trailerView.movieName = movieName
    trailerView.youtubeMovieTrailer = youtubeMovieTrailer
    }
    }
    
    
}
