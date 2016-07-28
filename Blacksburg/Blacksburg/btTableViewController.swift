//
//  btTableViewController.swift
//  Blacksburg
//
//  Created by Supratim Baruah on 10/2/14.
//  Copyright (c) 2014 Supratim Baruah. All rights reserved.
//

import UIKit

class btTableViewController: UITableViewController {

    var dict = [String:AnyObject]()
    
    var options = [String]()
    
    var data = [String]()
    
    var websiteURL: String = ""
    var optionName: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        var btFilePath: NSString? = NSBundle.mainBundle().pathForResource("bt", ofType: "plist")
        
        var dictionaryFromFile: NSDictionary? = NSDictionary(contentsOfFile: btFilePath!)
        
        if let dictionaryForPlistFile = dictionaryFromFile {
            
            // Typecast the created dictionary of type NSDictionary as Dictionary type
            dict = dictionaryForPlistFile as Dictionary
            
            /*
            allKeys returns a new array containing the dictionary’s keys as of type AnyObject.
            Therefore, typecast the AnyObject type keys to be of type String.
            */
            options = dictionaryForPlistFile.allKeys as [String]
    
            
        } else {
            
            // Unable to get the file from the server over the network
            showErrorMessageFor("bt.plist")
            return
        }
        
    }
    
    func showErrorMessageFor(fileName: String) {
        
        // Instantiate an alert view object
        var alertView = UIAlertView()
        
        alertView.title = "Unable to Access the File: \(fileName)!"
        alertView.message = "The file does not reside in the application's main bundle (project folder)"
        alertView.delegate = nil
        alertView.addButtonWithTitle("OK")
        
        alertView.show()
    }
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        
        return 1
    }
    
    // Asks the data source to return the number of rows in a section, the number of which is given
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return options.count
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        var rowNumber: Int = indexPath.row    // Identify the row number
        
        var cell: TableViewCell = tableView.dequeueReusableCellWithIdentifier("placesCellType") as TableViewCell
        
        // Obtain the country code of the row
        var option: String = options[rowNumber]
        
        cell.optionLabel.text! = option
        
        
        var optionArray: AnyObject? = dict[option]
        
        data = optionArray! as [String]
        
        
        return cell
    }
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        var rowNumber: Int = indexPath.row    // Identify the row number
        
        // Obtain the country code of the row
        var option: String = options[rowNumber]
        
        var optionArray: AnyObject? = dict[option]
        
        data = optionArray! as [String]
        
        
        optionName = option
        let ScheduleandMaps = "Schedule and Maps"
        //print(optionName)
        websiteURL = data[0]
        //print(websiteURL)
        if(websiteURL == "ScheduleandMaps")
        {
            performSegueWithIdentifier("SandM", sender: self)
        }
        else
        {
            performSegueWithIdentifier("web", sender: self)
        }
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject!) {
        
        if segue.identifier == "web" {
            
            // Obtain the object reference of the destnation view controller
            var webViewController: BTWebViewController = segue.destinationViewController as BTWebViewController
            
            //Pass the data objects to the destination view controller object
            webViewController.linkName = optionName
            webViewController.websiteURL = websiteURL
        }
        else if segue.identifier == "SandM" {
            
            // Obtain the object reference of the destination view controller
            var SandMController: ScheduleandMapsTableViewController = segue.destinationViewController as ScheduleandMapsTableViewController
            
           
        }
    }

}
