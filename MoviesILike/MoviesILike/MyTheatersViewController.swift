//
//  MyTheatersViewController.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

class MyTheatersViewController: UIViewController,  AddTheaterViewControllerProtocol, EditTheaterViewControllerProtocol  {

    
    @IBOutlet var pickerView: UIPickerView!
    @IBOutlet var mapTypeSegmentedControl: UISegmentedControl!
    
    @IBOutlet var location: UITextField!
    
    var theaterNames     = [String]()
    
    // Declare a property to contain the absolute file path for the maps.html file
    var mapsHtmlFilePath: String?
    
    // Obtain the object reference to the App Delegate object
    let applicationDelegate: AppDelegate = UIApplication.sharedApplication().delegate as AppDelegate
    
    /*
    dataObjectToPass is the data object to pass to the downstream view controller (i.e., VTPlaceOnMapViewController)
    Create the array to hold 2 string values as specified at index 0 and 1.
    
    NOTE: To store values in an array using its index, you must create the array with the number of index values to be used.
    */
    var dataObjectToPass: [String] = ["googleMapQuery", "directionsType"]
    
    var theaterSelected = ""
    var addressSelected = ""
    
    var viewShownFirstTime = true
    
    /*
    -----------------------
    MARK: - View Life Cycle
    -----------------------
    */
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.theaterNames = applicationDelegate.dict_Theaters.allKeys as [String]
        
        // Sort the universityNames within itself in alphabetical order
        self.theaterNames.sort { $0 < $1 }
        
        // Obtain the absolute file path to the maps.html file in the main bundle
        mapsHtmlFilePath = NSBundle.mainBundle().pathForResource("maps", ofType: "html")
        
        
        // Set up the Edit button on the left of the navigation bar to enable editing of the table view rows
        let editButton: UIBarButtonItem = UIBarButtonItem(barButtonSystemItem: .Edit, target: self, action: "editTheater:")
        self.navigationItem.leftBarButtonItem = editButton
        
        // Set up the Add button on the right of the navigation bar to call the addCity method when tapped
        let addButton: UIBarButtonItem = UIBarButtonItem(barButtonSystemItem: .Add, target: self, action: "addTheater:")
        self.navigationItem.rightBarButtonItem = addButton
        
        /*
        println("mapsHtmlFilePath = \(mapsHtmlFilePath)")
        
        This prints the following as the *** absolute path *** to the maps.html file.
        
        mapsHtmlFilePath = Optional("/Users/Balci/Library/Developer/CoreSimulator/Devices/230B3A00-3615-486F-A925-D601DB35F0FC/
        data/Containers/Bundle/Application/1BBEB6E1-FA8A-4179-BA6E-E85CC355679F/VTQuest.app/maps.html")
        */
    }
    
    override func viewWillAppear(animated: Bool) {
        
        // Set the segmented control to show no selection before the view appears
        mapTypeSegmentedControl.selectedSegmentIndex = UISegmentedControlNoSegment
        
        
        // Obtain the number of the row in the middle of the VT place names list
        var numberOfTheaters = theaterNames.count
        var numberOfRowToShow = Int(numberOfTheaters / 2)
        
        // Show the picker view of VT place names from the middle
        pickerView.selectRow(numberOfRowToShow, inComponent: 0, animated: false)
        
        // Deselect the earlier selected directions type
        //directionsTypeSegmentedControl.selectedSegmentIndex = UISegmentedControlNoSegment
        
    }
    
    // The addCity method is invoked when the user taps the Add button created in viewDidLoad() above.
    func addTheater(sender: AnyObject) {
        
        // Perform the segue named AddCity
        performSegueWithIdentifier("AddTheater", sender: self)
        
    }
    
    func editTheater(sender: AnyObject) {
        var selectedRowNumber = pickerView.selectedRowInComponent(0)
        
        theaterSelected = theaterNames[selectedRowNumber]
        
        var theaterAddress: AnyObject? = applicationDelegate.dict_Theaters.valueForKey(theaterSelected)
        
        addressSelected = theaterAddress as String
        
        // Perform the segue named AddCity
        performSegueWithIdentifier("EditTheater", sender: self)
        
    }
    
    func addTheaterViewController(controller: AddTheaterViewController, didFinishWithSave save: Bool) {
        
        if save {
            
            // Get the country name entered by the user on the AddCityViewController's UI
            var theaterNameEntered: String = controller.theaterNameTextField.text
            
            // Get the city name entered by the user on the AddCityViewController's UI
            var addressEntered: String = controller.addressTextField.text
            
            if contains(theaterNames, theaterNameEntered) {
                
                applicationDelegate.dict_Theaters.setValue(addressEntered, forKey: theaterNameEntered)
                
            } else {   // The entered country name does not exist in the current dictionary
                
                applicationDelegate.dict_Theaters.setObject(addressEntered, forKey: theaterNameEntered)
            }
            
            //Names = applicationDelegate.dict_Country_Cities.allKeys as [String]
            
            // Sort the country names within itself in alphabetical order
            // countryNames.sort { $0 < $1 }
            
            // Reload the rows and sections of the Table View countryCityTableView
            viewDidLoad()
            viewWillAppear(true)
        }
        
        self.navigationController!.popViewControllerAnimated(true)
    }
    
    func editTheaterViewController(controller: EditTheaterViewController, didFinishWithSave save: Bool) {
        
        if save {
            
            // Get the country name entered by the user on the AddCityViewController's UI
            var theaterNameEntered: String = controller.theaterNameTextField.text
            
            // Get the city name entered by the user on the AddCityViewController's UI
            var addressEntered: String = controller.addressTextField.text
            
            if contains(theaterNames, theaterNameEntered) {
                
                applicationDelegate.dict_Theaters.setValue(addressEntered, forKey: theaterNameEntered)
                
            } else {   //
                
                applicationDelegate.dict_Theaters.setObject(addressEntered, forKey: theaterNameEntered)
            }
            
            //Names = applicationDelegate.dict_Country_Cities.allKeys as [String]
            
            // Sort the country names within itself in alphabetical order
            // countryNames.sort { $0 < $1 }
            
            // Reload the rows and sections of the Table View countryCityTableView
            viewDidLoad()
            viewWillAppear(true)
        }
        
        self.navigationController!.popViewControllerAnimated(true)
    }
    
    func editTheaterDeleteViewController(controller: EditTheaterViewController, delete: Bool) {
        
        if delete {
            
            // Get the country name entered by the user on the AddCityViewController's UI
            var theaterNameEntered: String = controller.theaterNameTextField.text
            
            // Get the city name entered by the user on the AddCityViewController's UI
            var addressEntered: String = controller.addressTextField.text
            
            if contains(theaterNames, theaterNameEntered) {
                
                applicationDelegate.dict_Theaters.removeObjectForKey(theaterNameEntered)
                
            }
            
            pickerView.reloadAllComponents()
            viewDidLoad()
            viewWillAppear(true)
        }
        
        self.navigationController!.popViewControllerAnimated(true)
    }

    /*
    ------------------------
    MARK: - IBAction Methods
    ------------------------
    */
    
    
    @IBAction func getMap(sender: UISegmentedControl) {
        
        
        var selectedRowNumber = pickerView.selectedRowInComponent(0)
        
        theaterSelected = theaterNames[selectedRowNumber]
        
        var theaterAddress: AnyObject? = applicationDelegate.dict_Theaters.valueForKey(theaterSelected)
        
        var address = theaterAddress as String
      
        // A Google map query parameter cannot have spaces. Therefore, replace each space with +
        var placeNameWithNoSpaces = address.stringByReplacingOccurrencesOfString(" ", withString: "+", options: nil, range: nil)
        // Obtain the absolute file path to the maps.html file in the main bundle
        
        var googleMapQuery: String = ""
        
        switch sender.selectedSegmentIndex {
            
        case 0:   // Roadmap map type selected
            googleMapQuery = mapsHtmlFilePath! + "?place=\(placeNameWithNoSpaces)&maptype=ROADMAP&zoom=16"
            
        case 1:   // Satellite map type selected
            googleMapQuery = mapsHtmlFilePath! + "?place=\(placeNameWithNoSpaces)&zoom=16&maptype=SATELLITE"
            
        case 2:   // Hybrid map type selected
            googleMapQuery = mapsHtmlFilePath! + "?place=\(placeNameWithNoSpaces)&zoom=16&maptype=HYBRID"
            
        case 3:   // Terrain map type selected
            googleMapQuery = mapsHtmlFilePath! + "?place=\(placeNameWithNoSpaces)&zoom=16&maptype=TERRAIN"
            
            
        default:
            return
        }
        
        // Prepare the data object to pass to the downstream view controller
        dataObjectToPass[0] = googleMapQuery
        dataObjectToPass[1] = theaterSelected
        //print(placeNameWithNoSpaces);
        // Perform the segue named vtPlaceOnMap
        performSegueWithIdentifier("Theater", sender: self)
    }
    
    @IBAction func getTheatersAndMovies(sender: UITextView) {
        
        // A Google map query parameter cannot have spaces. Therefore, replace each space with +
        var locationEnteredWithNoSpaces = location.text.stringByReplacingOccurrencesOfString(" ", withString: "+", options: nil, range: nil)
        
        // Obtain the absolute file path to the maps.html file in the main bundle
        location.text = ""
        var googleQuery: String = ""
        
        googleQuery = "http://google.com/movies?near=\(locationEnteredWithNoSpaces)"
        
        // Prepare the data object to pass to the downstream view controller
        dataObjectToPass[0] = googleQuery
        dataObjectToPass[1] = "Showtimes"
        // Perform the segue named vtPlaceOnMap
        if location.text == ""
        {
            var alertView = UIAlertView()
            
            alertView.title = "Form not compelted!"
            alertView.message = "Enter Location: City, State"
            alertView.delegate = nil
            alertView.addButtonWithTitle("OK")
            
            alertView.show()
        }
        else
        {
            performSegueWithIdentifier("Theater", sender: self)
        }
    }
    
    /*
    -------------------------
    MARK: - Prepare for Segue
    -------------------------
    */
    // This method is called by the system whenever you invoke the method performSegueWithIdentifier:sender:
    // You never call this method. It is invoked by the system.
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject!) {
        
        if segue.identifier == "Theater" {
            
            // Obtain the object reference of the destination (downstream) view controller
            var theaterWebViewController: TheaterWebViewController = segue.destinationViewController as TheaterWebViewController
            /*
            This view controller creates the dataObjectToPass and passes it (by value) to the downstream view controller
            VTDirectionsMapViewController by copying its content into VTDirectionsMapViewController's property dataObjectPassed.
            */
            theaterWebViewController.dataObjectPassed = dataObjectToPass
        }
        if segue.identifier == "AddTheater" {
            var addTheaterViewController: AddTheaterViewController = segue.destinationViewController as AddTheaterViewController
            
            // Under the Delegation Design Pattern, set the addCityViewController's delegate to be self
            addTheaterViewController.delegate = self
        }
        
        if segue.identifier == "EditTheater" {
            var editTheaterViewController: EditTheaterViewController = segue.destinationViewController as EditTheaterViewController
            
            // Under the Delegation Design Pattern, set the addCityViewController's delegate to be self
            editTheaterViewController.delegate = self
            editTheaterViewController.address = addressSelected
            editTheaterViewController.theaterName = theaterSelected
        }
        
    }
    
    /*
    ----------------------------------------
    MARK: - UIPickerView Data Source Methods
    ----------------------------------------
    */
    
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        
        return 1
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        
        return theaterNames.count
    }
    
    

    /*
    ------------------------------------
    MARK: - UIPickerView Delegate Method
    ------------------------------------
    */
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String! {
        
        return theaterNames[row]
    }
    
}