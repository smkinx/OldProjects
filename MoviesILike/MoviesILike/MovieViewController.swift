//
//  MovieViewController.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

class MovieViewController: UIViewController {

    var movieDataPassed = [String: AnyObject]()
    
    // Object references to the UI objects
    @IBOutlet var moviePosterImageView: UIImageView!
    @IBOutlet var movieTitleLabel: UILabel!
    @IBOutlet var criticsScoreLabel: UILabel!
    @IBOutlet var audienceScoreLabel: UILabel!
    @IBOutlet var mpaaRatingRuntimeLabel: UILabel!
    @IBOutlet var releaseDateLabel: UILabel!
    @IBOutlet var castTextView: UITextView!
    @IBOutlet var movieInfoTextView: UITextView!
    
    /*
    -----------------------
    MARK: - View Life Cycle
    -----------------------
    */
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = movieDataPassed["title"] as? String
        
        //-----------------------------------
        // Display Movie Poster Profile Image
        //-----------------------------------
        
        let posterUrlsDict = movieDataPassed["posters"] as Dictionary<String, AnyObject>
        let profileImageUrlGiven = posterUrlsDict["profile"] as String
        
        // The Rotten Tomatoes API poster image URLs default to thumbnail smallest image for performance reasons.
        // We need to specifically ask for the Profile poster image, which is larger in file size.
        
        var profileImageUrl = ""
        
        if profileImageUrlGiven.hasSuffix("_pro.jpg") {
            
            profileImageUrl = profileImageUrlGiven
            
        } else if profileImageUrlGiven.hasSuffix("_tmb.jpg") {
            
            profileImageUrl = profileImageUrlGiven.stringByReplacingOccurrencesOfString("_tmb.jpg", withString: "_pro.jpg", options: NSStringCompareOptions.LiteralSearch, range: nil)
        }
        
        // Create an NSURL object from the profile URL string
        var url = NSURL(string: profileImageUrl)
        
        var errorInReadingImageData: NSError?
        
        // Retrieve the movie poster profile image data from the profile URL
        var imageData: NSData? = NSData(contentsOfURL: url!, options: NSDataReadingOptions.DataReadingMappedIfSafe, error: &errorInReadingImageData)
        
        if let moviePosterImage = imageData {
            
            // Movie poster profile image data is successfully retrieved
            moviePosterImageView!.image = UIImage(data: moviePosterImage)
            
        } else {
            
            // Instantiate an alert view object
            var alertView = UIAlertView()
            
            alertView.title = "Unable to Get Movie Profile Image!"
            alertView.message = "Error description: \(errorInReadingImageData!.localizedDescription)"
            alertView.delegate = nil
            alertView.addButtonWithTitle("OK")
            
            alertView.show()
        }
        
        //--------------------
        // Display Movie Title
        //--------------------
        
        movieTitleLabel.text = movieDataPassed["title"] as? String
        
        //-------------------------------------
        // Display Critics and Audience Ratings
        //-------------------------------------
        
        let ratingsDict = movieDataPassed["ratings"] as Dictionary<String, AnyObject>
        
        let criticsScore = ratingsDict["critics_score"] as? Int
        let audienceScore = ratingsDict["audience_score"] as? Int
        
        criticsScoreLabel.text = "\(criticsScore!)%"
        audienceScoreLabel.text = "\(audienceScore!)%"
        
        //--------------------------------
        // Display MPAA Rating and Runtime
        //--------------------------------
        
        var mpaaRating = movieDataPassed["mpaa_rating"] as String
        
        var runtime = ""
        
        var runtimeInMinutes: AnyObject? = movieDataPassed["runtime"]
        
        // Check to see if runtimeInMinutes is of Type Int; if so, assign it to runtimeInMinutesAsInt
        if let runtimeInMinutesAsInt = runtimeInMinutes as? Int {
            
            // Remainder operator % returns the remainder of the division
            var minutes = runtimeInMinutesAsInt % 60
            var hours = Int((runtimeInMinutesAsInt - minutes) / 60)
            
            // Set hour and minute labels
            var hrsLabel = hours > 1 ? "hrs" : "hr"
            var minsLabel = minutes > 1 ? "mins" : "min"
            
            runtime = "\(hours) \(hrsLabel) \(minutes) \(minsLabel)"
            
        } else {
            // Some movies do not have runtime value
            runtime = "No Runtime"
        }
        
        mpaaRatingRuntimeLabel!.text = "\(mpaaRating), \(runtime)"
        
        //---------------------
        // Display Release Date
        //---------------------
        
        var releaseDateInTheaters = ""
        
        var releaseDatesDict = movieDataPassed["release_dates"] as Dictionary<String, String>
        
        // If releaseDatesDict is not empty
        if !releaseDatesDict.isEmpty {
            
            // If a value is available for the "theater" key in the dictionary
            if let releaseDate = releaseDatesDict["theater"] {
                
                releaseDateInTheaters = releaseDate
                
                var date: NSDate?
                
                // NSDateFormatter is used to convert NSDate objects into String representations of
                // dates and times or convert String representations of dates and times into NSDate objects.
                var dateFormatter = NSDateFormatter()
                /*
                We use unicode’s date format patterns here to specify the date styles.
                
                yyyy    --> Year as 4-digit number,     e.g., 2014
                MM      --> Month as 2-digit number,    e.g., 09
                MMM     --> Abbreviated month name,     e.g., Sept
                MMMM    --> Full month name,            e.g., September
                dd      --> Day as 2-digit number,      e.g., 14
                */
                
                // The Date retrieved from the API as String is formatted as "yyyy-MM-dd", e.g., "2014-10-24"
                // Set the date format for the dateFormatter to use
                dateFormatter.dateFormat = "yyyy-MM-dd"
                
                // Ask the dateFormatter to create an NSDate object from the releaseDateInTheaters
                // string date given in the format set above.
                date = dateFormatter.dateFromString(releaseDateInTheaters)
                
                // Specify the new date style desired, e.g., October 24, 2014
                dateFormatter.dateFormat = "MMMM dd, yyyy"
                
                // Ask the dateFormatter to create a String representation of the NSDate object
                // according to the format specified above
                releaseDateInTheaters = dateFormatter.stringFromDate(date!)
            }
        }
        
        releaseDateLabel!.text = releaseDateInTheaters
        
        //------------------------
        // Display Top Movie Stars
        //------------------------
        
        var topArtists = movieDataPassed["abridged_cast"] as Array<AnyObject>
        
        var topStarsOfTheMovie = ""
        let numberOfCastMembers = topArtists.count
        
        for var j=0; j < numberOfCastMembers; j++ {
            
            let movieStar = topArtists[j] as Dictionary<String, AnyObject>
            let movieStarName = movieStar["name"] as String
            
            topStarsOfTheMovie += movieStarName
            
            // Do not place a comma after the last name
            if j != (numberOfCastMembers - 1) {
                topStarsOfTheMovie += ", "
            }
        }
        
        castTextView.text = topStarsOfTheMovie
        
        //-------------------
        // Display Movie Info
        //-------------------
        
        movieInfoTextView.text = movieDataPassed["synopsis"] as? String
        
        /*
        Ask the movieInfoTextView to scroll to the top part of the text.
        Use the following to scroll to the bottom part of the text:
        movieInfoTextView.scrollRangeToVisible(NSMakeRange(0, (movieInfoTextView.text as NSString).length))
        
        */
        movieInfoTextView.scrollRangeToVisible(NSMakeRange(0, 0))
    }
    
}
