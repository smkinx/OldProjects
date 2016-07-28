//
//  AddMovieViewController.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

protocol AddMovieViewControllerProtocol {
    
    func addMovieViewController(controller: AddMovieViewController, didFinishWithSave save: Bool)
}

class AddMovieViewController: UIViewController, UIScrollViewDelegate, UITextFieldDelegate {

    
    // Object reference to the UIScrollView object in the UI
    @IBOutlet var scrollView: UIScrollView!
    var activeTextField: UITextField?
    /*
    Whichever object wants to conform to our protocol must store its unique ID into this
    instance variable named "delegate" and provide the implementation of the protocol method.
    */
    var delegate: AddMovieViewControllerProtocol?
    
    // Obtain the object reference of the country name text field
    @IBOutlet var genreNameTextField: UITextField!
    
    // Obtain the object reference of the city name text field
    @IBOutlet var movieNameTextField: UITextField!
    
    @IBOutlet var movieTrailerTextField: UITextField!
    
    @IBOutlet var topStarsTextField: UITextField!
    
    @IBOutlet var ratingSegment: UISegmentedControl!
    var rating = ""
    
    //var ratingSelected = false;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        registerForKeyboardNotifications()
        self.title = "Add Movie"
        ratingSegment.selectedSegmentIndex = UISegmentedControlNoSegment
        // Create a Save button on the right of the navigation bar to call the "save:" method when tapped
        let saveButton = UIBarButtonItem(barButtonSystemItem: .Save, target: self, action: "save:")
        self.navigationItem.rightBarButtonItem = saveButton
    }
    
    
    // This method is invoked when the user taps the Done key on the keyboard
    @IBAction func keyboardDone(sender: UITextField) {
        
        // Once the text field is no longer the first responder, the keyboard is removed
        sender.resignFirstResponder()
    }
    
    // This method removes the keyboard when the user taps anywhere on the background
    override func touchesBegan(touches: NSSet, withEvent event: UIEvent) {
        /*
        "A UITouch object represents the presence or movement of a finger on the screen for a particular event." [Apple]
        We store the UITouch object's unique ID into the local variable touch.
        */
        var touch: UITouch = event.allTouches()?.anyObject()? as UITouch
        
        /*
        When the user taps within a text field, that text field becomes the first responder.
        When a text field becomes the first responder, the system automatically displays the keyboard.
        */
        
        // If countryNameTextField is first responder and the user did not touch the countryNameTextField
        if genreNameTextField.isFirstResponder() && touch.view != genreNameTextField {
            
            // Make countryNameTextField to be no longer the first responder.
            genreNameTextField.resignFirstResponder()
        }
        
        // If cityNameTextField is first responder and the user did not touch the cityNameTextField
        if movieNameTextField.isFirstResponder() && touch.view != movieNameTextField {
            
            // Make cityNameTextField to be no longer the first responder.
            movieNameTextField.resignFirstResponder()
        }
        
        super.touchesBegan(touches, withEvent:event)
    }
    
    
    // This method is invoked when the user taps the Save button
    func save(sender: AnyObject) {
        
        // Ask the delegate object to invoke the protocol method
        if movieNameTextField.text == "" || genreNameTextField.text == "" || topStarsTextField.text == "" || movieTrailerTextField.text == "" || rating == ""
        {
            var alertView = UIAlertView()
            
            alertView.title = "Form not compelted!"
            alertView.message = "Please fill out every textbox and select MAPP ratings"
            alertView.delegate = nil
            alertView.addButtonWithTitle("OK")
            
            alertView.show()
        }
        else
        {
            delegate!.addMovieViewController(self, didFinishWithSave:true)
        }
    }
    
    @IBAction func getRating(sender: UISegmentedControl) {
        
        
        switch sender.selectedSegmentIndex {
            
        case 0:   // Roadmap map type selected
           rating = "G"
        case 1:   // Satellite map type selected
           rating = "PG"
        case 2:   // Hybrid map type selected
           rating = "PG-13"
            
        case 3:   // Terrain map type selected
           rating = "R"
        case 4:
            rating = "NC-17"
            
            
        default:
            return
        }
        //ratingSelected = true;
    }
    
    /*
    ---------------------------------------
    MARK: - Handling Keyboard Notifications
    ---------------------------------------
    */
    
    // This method is called in viewDidLoad() to register self for keyboard notifications
    func registerForKeyboardNotifications() {
        
        // "An NSNotificationCenter object (or simply, notification center) provides a
        // mechanism for broadcasting information within a program." [Apple]
        let notificationCenter = NSNotificationCenter.defaultCenter()
        
        notificationCenter.addObserver(self,
            selector:   "keyboardWillShow:",    // <-- Call this method upon Keyboard Will SHOW Notification
            name:       UIKeyboardWillShowNotification,
            object:     nil)
        
        notificationCenter.addObserver(self,
            selector:   "keyboardWillHide:",    //  <-- Call this method upon Keyboard Will HIDE Notification
            name:       UIKeyboardWillHideNotification,
            object:     nil)
    }
    
    
    // This method is called upon Keyboard Will SHOW Notification
    func keyboardWillShow(sender: NSNotification) {
        
        // "userInfo, the user information dictionary stores any additional
        // objects that objects receiving the notification might use." [Apple]
        let info: NSDictionary = sender.userInfo!
        
        /*
        Key     = UIKeyboardFrameBeginUserInfoKey
        Value   = an NSValue object containing a CGRect that identifies the start frame of the keyboard in screen coordinates.
        */
        let value: NSValue = info.valueForKey(UIKeyboardFrameBeginUserInfoKey) as NSValue
        
        // Obtain the size of the keyboard
        let keyboardSize: CGSize = value.CGRectValue().size
        
        // Create Edge Insets for the view.
        let contentInsets: UIEdgeInsets = UIEdgeInsetsMake(0.0, 0.0, keyboardSize.height, 0.0)
        
        // Set the distance that the content view is inset from the enclosing scroll view.
        scrollView.contentInset = contentInsets
        
        // Set the distance the scroll indicators are inset from the edge of the scroll view.
        scrollView.scrollIndicatorInsets = contentInsets
        
        //-----------------------------------------------------------------------------------
        // If active text field is hidden by keyboard, scroll the content up so it is visible
        //-----------------------------------------------------------------------------------
        
        // Obtain the frame size of the View
        var selfViewFrameSize: CGRect = self.view.frame
        
        // Subtract the keyboard height from the self's view height
        // and set it as the new height of the self's view
        selfViewFrameSize.size.height -= keyboardSize.height
        
        // Obtain the size of the active UITextField object
        let activeTextFieldRect: CGRect? = movieTrailerTextField!.frame
        
        // Obtain the active UITextField object's origin (x, y) coordinate
        let activeTextFieldOrigin: CGPoint? = activeTextFieldRect?.origin
        
        
        if (!CGRectContainsPoint(selfViewFrameSize, activeTextFieldOrigin!)) {
            
            // If active UITextField object's origin is not contained within self's View Frame,
            // then scroll the content up so that the active UITextField object is visible
            scrollView.scrollRectToVisible(activeTextFieldRect!, animated:true)
        }
    }
    
    // This method is called upon Keyboard Will HIDE Notification
    func keyboardWillHide(sender: NSNotification) {
        
        // Set contentInsets to top=0, left=0, bottom=0, and right=0
        let contentInsets: UIEdgeInsets = UIEdgeInsetsZero
        
        // Set scrollView's contentInsets to top=0, left=0, bottom=0, and right=0
        scrollView.contentInset = contentInsets
        
        // Set scrollView's scrollIndicatorInsets to top=0, left=0, bottom=0, and right=0
        scrollView.scrollIndicatorInsets = contentInsets
    }
    

    
    // This method is called when the user taps Return on the keyboard
    func textFieldShouldReturn(textField: UITextField!) -> Bool {
        
        // Deactivate the text field and remove the keyboard
        textField.resignFirstResponder()
        
        return true
    }
    

    
    
}

