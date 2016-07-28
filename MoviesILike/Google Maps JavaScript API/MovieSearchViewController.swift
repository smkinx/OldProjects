//
//  MovieSearchViewController.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

class MovieSearchViewController: UIViewController {

    @IBOutlet var movieNameToSearch: UITextField!
    var movieName:String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    /*
    ------------------------
    MARK: - IBAction Methods
    ------------------------
    */
    // This method is invoked when the user taps Done on the keyboard
    @IBAction func keyboardDone(sender: UITextField) {
        
        // Deactivate the calling Text Field object and remove the Keyboard
        sender.resignFirstResponder()
    }
    
    // This method is invoked when the user taps anywhere on the background
    @IBAction func backgroundTouch(sender: UIControl) {
        
        // Deactivate the Text Field objects and remove the Keyboard
        movieNameToSearch.resignFirstResponder()
    }
    
    @IBAction func getMovies(sender: UITextField) {
        
        // If no address is entered, alert the user
        if movieNameToSearch.text == ""  {
            
            
            // Instantiate an alert view object
            var alertView = UIAlertView()
            
            alertView.title = "Search left blank!"
            alertView.message = "Please enter movie name!"
            alertView.delegate = nil
            alertView.addButtonWithTitle("OK")
            
            alertView.show()
            
            return
        }
        movieName = movieNameToSearch.text
        movieNameToSearch.text = ""
        performSegueWithIdentifier("Search", sender: self)
    }
    /*
    -------------------------
    MARK: - Prepare for Segue
    -------------------------
    */
    // This method is called by the system whenever you invoke the method performSegueWithIdentifier:sender:
    // You never call this method. It is invoked by the system.
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject!) {
        
        if segue.identifier == "Search" {
            
            // Obtain the object reference of the destination (downstream) view controller
            var searchViewController: MovieTableViewController = segue.destinationViewController as MovieTableViewController
            
            
            /*
            This view controller creates the dataObjectToPass and passes it (by value) to the downstream view controller
            DirectionsMapViewController by copying its content into AddressMapViewController's property dataObjectPassed.
            */
            searchViewController.movieNameToSearch = movieName
        }
    }
}
