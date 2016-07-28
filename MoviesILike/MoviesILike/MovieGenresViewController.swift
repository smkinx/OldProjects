//
//  MovieGenresViewController.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

class MovieGenresViewController: UIViewController, UIScrollViewDelegate, UITableViewDataSource, UITableViewDelegate {
    
    
    // Obtain object references to the UI objects
    @IBOutlet var leftArrowWhite: UIImageView!
    @IBOutlet var rightArrowWhite: UIImageView!
    @IBOutlet var scrollMenu: UIScrollView!
    @IBOutlet var autoTableView: UITableView!
    
    var movieName: String = ""
    var youtubeMovieTrailer: String = ""
    
    let applicationDelegate: AppDelegate = UIApplication.sharedApplication().delegate as AppDelegate
    
    var dict_movies2: NSMutableDictionary = NSMutableDictionary.alloc()
    // Create and initialize the dictionary to store the input data
    var dict_AutoMakers_AutoModels = [String: AnyObject]()
    
    // Create and initialize the array to store auto manufacturer names
    var genreNames = [String]()
    
    // Other properties (instance variables) and their initializations
    let kScrollMenuHeight: CGFloat = 30.0
    var selectedGenre = ""
    var previousButton = UIButton(frame: CGRectMake(0, 0, 0, 0))
    
    /*
    -----------------------
    MARK: - View Life Cycle
    -----------------------
    */
    override func viewDidLoad() {
        super.viewDidLoad()
        
        /*******************************************
        * Read in the Automobiles.plist file content
        *******************************************/
        
        /*
        allKeys returns a new array containing the dictionary’s keys as of type AnyObject.
        Therefore, typecast the AnyObject type keys to be of type String.
        The keys in the array are *unordered*; therefore, they need to be sorted.
        */
        genreNames = applicationDelegate.dict_Genre.allKeys as [String]
        
        // Sort the country names within itself in alphabetical order
        genreNames.sort { $0 < $1 }
        
        /**********************
        * Set Background Colors
        **********************/
        
        self.view.backgroundColor = UIColor.blackColor()
        leftArrowWhite.backgroundColor = UIColor.blackColor()
        rightArrowWhite.backgroundColor = UIColor.blackColor()
        scrollMenu.backgroundColor = UIColor.blackColor()
        
        /***********************************************************************
        * Instantiate and setup the buttons for the horizontally scrollable menu
        ***********************************************************************/
        
        // Instantiate a mutable array to hold the menu buttons to be created
        var listOfMenuButtons = [UIButton]()
        
        for var i = 0; i < genreNames.count; i++ {
            
            // Instantiate a button to be placed within the horizontally scrollable menu
            var scrollMenuButton: UIButton = UIButton.buttonWithType(UIButtonType.Custom) as UIButton
            
            // Obtain the title (i.e., auto manufacturer name) to be displayed on the button
            var buttonTitle = genreNames[i]
            
            // The button width and height in points will depend on its font style and size
            var buttonTitleFont = UIFont(name: "Helvetica-Bold", size: 17.0)
            
            // Set the font of the button title label text
            scrollMenuButton.titleLabel?.font = buttonTitleFont
            
            // Compute the size of the button title in points
            var buttonTitleSize: CGSize = (buttonTitle as NSString).sizeWithAttributes([NSFontAttributeName:buttonTitleFont!])
            
            // Add 20 points to the width to leave 10 points on each side.
            // Set the button frame with width=buttonWidth height=kScrollMenuHeight points with origin at (x, y) = (0, 0)
            scrollMenuButton.frame = CGRectMake(0.0, 0.0, buttonTitleSize.width + 20.0, kScrollMenuHeight)
            
            // Set the background color of the button to black
            scrollMenuButton.backgroundColor = UIColor.blackColor()
            
            // Set the button title to the automobile manufacturer's name
            scrollMenuButton.setTitle(buttonTitle, forState: UIControlState.Normal)
            
            // Set the button title color to white
            scrollMenuButton.setTitleColor(UIColor.whiteColor(), forState: UIControlState.Normal)
            
            // Set the button title color to red when the button is selected
            scrollMenuButton.setTitleColor(UIColor.redColor(), forState: UIControlState.Selected)
            
            // Set the button to invoke the buttonPressed: method when the user taps it
            scrollMenuButton.addTarget(self, action: "buttonPressed:", forControlEvents: .TouchUpInside)
            
            // Add the constructed button to the list of buttons
            listOfMenuButtons.append(scrollMenuButton)
        }
        
        /*********************************************************************************************
        * Compute the sumOfButtonWidths = sum of the widths of all buttons to be displayed in the menu
        *********************************************************************************************/
        
        var sumOfButtonWidths: CGFloat = 0.0
        
        for var j = 0; j < listOfMenuButtons.count; j++ {
            
            // Obtain the obj ref to the jth button in the listOfMenuButtons array
            var button: UIButton = listOfMenuButtons[j]
            
            // Set the button's frame to buttonRect
            var buttonRect: CGRect = button.frame
            
            // Set the buttonRect's x coordinate value to sumOfButtonWidths
            buttonRect.origin.x = sumOfButtonWidths
            
            // Set the button's frame to the newly specified buttonRect
            button.frame = buttonRect
            
            // Add the button to the horizontally scrollable menu
            scrollMenu.addSubview(button)
            
            // Add the width of the button to the total width
            sumOfButtonWidths += button.frame.size.width
        }
        
        // Horizontally scrollable menu's content width size = the sum of the widths of all of the buttons
        // Horizontally scrollable menu's content height size = kScrollMenuHeight points
        scrollMenu.contentSize = CGSizeMake(sumOfButtonWidths, kScrollMenuHeight)
        
        /*******************************************************
        * Select and show the default auto maker upon app launch
        *******************************************************/
        
        // Hide left arrow
        leftArrowWhite.hidden = true
        
        // The first auto maker on the list is the default one to display
        var defaultButton: UIButton = listOfMenuButtons[0]
        
        // Indicate that the button is selected
        defaultButton.selected = true
        
        previousButton = defaultButton
        selectedGenre = genreNames[0]
        
        // Display the table view object's content for the selected auto maker
        autoTableView.reloadData()
    }
    
    /*
    -----------------------------------
    MARK: - Method to Handle Button Tap
    -----------------------------------
    */
    // This method is invoked when the user taps a button in the horizontally scrollable menu
    func buttonPressed(sender: UIButton) {
        
        var selectedButton: UIButton = sender
        
        selectedButton.selected = true
        
        if previousButton != selectedButton {
            // Selecting the selected button again should not change its title color
            previousButton.selected = false
        }
        
        previousButton = selectedButton
        
        selectedGenre = selectedButton.titleForState(UIControlState.Normal)!
        
        // Redisplay the table view object's content for the selected auto maker
        autoTableView.reloadData()
    }
    
    /*
    -----------------------------------
    MARK: - Scroll View Delegate Method
    -----------------------------------
    */
    
    // Tells the delegate when the user scrolls the content view within the receiver
    
    func scrollViewDidScroll(scrollView: UIScrollView) {
        
        // The autoTableView object scrolling also invokes this method, in the case of which no action
        // should be taken since this method is created to handle only the scrollMenu object's scrolling.
        if scrollView == autoTableView {
            return
        }
        
        /*
        Content        = concatenated list of buttons
        Content Width  = sum of all button widths, sumOfButtonWidths
        Content Height = kScrollMenuHeight points
        Origin         = (x, y) values of the bottom left corner of the scroll view or content
        Sx             = Scroll View's origin x value
        Cx             = Content's origin x value
        contentOffset  = Sx - Cx
        
        Interpretation of the Arrows:
        
        IF scrolled all the way to the RIGHT THEN show only RIGHT arrow: indicating that the data (content) is
        on the right hand side and therefore, the user must *** scroll to the left *** to see the content.
        
        IF scrolled all the way to the LEFT THEN show only LEFT arrow: indicating that the data (content) is
        on the left hand side and therefore, the user must *** scroll to the right *** to see the content.
        
        5 pixels used as padding
        */
        if scrollView.contentOffset.x <= 5 {
            // Scrolling is done all the way to the RIGHT
            leftArrowWhite.hidden   = true      // Hide left arrow
            rightArrowWhite.hidden  = false     // Show right arrow
        }
        else if scrollView.contentOffset.x >= (scrollView.contentSize.width - scrollView.frame.size.width) - 5 {
            // Scrolling is done all the way to the LEFT
            leftArrowWhite.hidden   = false     // Show left arrow
            rightArrowWhite.hidden  = true      // Hide right arrow
        }
        else {
            // Scrolling is in between. Scrolling can be done in either direction.
            leftArrowWhite.hidden   = false     // Show left arrow
            rightArrowWhite.hidden  = false     // Show right arrow
        }
    }
    
    /*
    --------------------------------------
    MARK: - Table View Data Source Methods
    --------------------------------------
    */
    
    // Asks the data source to return the number of rows in a section
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        // Obtain the array of auto model names for the selected auto maker
        var modelsForSelectedMaker: AnyObject? = applicationDelegate.dict_Genre[selectedGenre]
        
        // Convert the array to be a Swift array
        var listOfMovies = modelsForSelectedMaker!
        
        // Return the number of auto model names for the selected auto maker.
        // We subtract 1 because the first item is the name of the logo image file.
        return listOfMovies.count
    }
    
    // Asks the data source to return a cell to insert in a particular table view location
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        var cell: UITableViewCell = tableView.dequeueReusableCellWithIdentifier("MovieViewCell") as UITableViewCell
        
        var rowNumber = indexPath.row
        
        
        
        /*
        Note that city names must not be sorted. The order shows how favorite the city is.
        The higher the order the more favorite the city is. The user specifies the ordering
        in the Edit mode by moving a row from one location to another for the same country.
        */
        
        // Obtain the list of cities in the given country as Objective-C array
        var movies: AnyObject? = applicationDelegate.dict_Genre[selectedGenre]
        
        var moviesRank = movies?.allKeys as [String]
        
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
    
    /*
    ----------------------------------
    MARK: - Table View Delegate Method
    ----------------------------------
    */
    
    // This method is invoked when the user taps a table view row
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        print("clicked")
        // Identify the row number
        var rowNumber = indexPath.row
        
        var movies: AnyObject? = applicationDelegate.dict_Genre[selectedGenre]
        
        var moviesRank = movies?.allKeys as [String]
        
        moviesRank.sort {$0 < $1}
        
        var movieNum = moviesRank[rowNumber]
        
        //var movieData  = [String]()
        dict_movies2 = movies! as NSMutableDictionary
        
        var movieData = dict_movies2[movieNum] as [String]
        
        movieName = movieData[0]
        youtubeMovieTrailer = movieData[2]
        performSegueWithIdentifier("GenreTrailer", sender: self)
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
    }
    /*
    -------------------------
    MARK: - Prepare For Segue
    -------------------------
    */
    
    // This method is called by the system whenever you invoke the method performSegueWithIdentifier:sender:
    // You never call this method. It is invoked by the system.
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        
        if segue.identifier == "GenreTrailer" {
            
            var trailerView: GenreTrailerViewController = segue.destinationViewController as GenreTrailerViewController
            
            //Pass the data objects to the destination view controller object
            // NFLWebViewController.countryName = countryName
            
            trailerView.movieName = movieName
            trailerView.youtubeMovieTrailer = youtubeMovieTrailer
        }
    }
}
