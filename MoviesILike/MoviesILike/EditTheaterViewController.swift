//
//  EditTheaterViewController.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

protocol EditTheaterViewControllerProtocol {
    
    func editTheaterViewController(controller: EditTheaterViewController, didFinishWithSave save: Bool)
    func editTheaterDeleteViewController(controller: EditTheaterViewController, delete: Bool)
}

class EditTheaterViewController: UIViewController {

    /*
    Whichever object wants to conform to our protocol must store its unique ID into this
    instance variable named "delegate" and provide the implementation of the protocol method.
    */
    var delegate: EditTheaterViewControllerProtocol?
    
    // Obtain the object reference of the country name text field
    @IBOutlet var theaterNameTextField: UITextField!
    
    // Obtain the object reference of the city name text field
    @IBOutlet var addressTextField: UITextField!
    
    var address = ""
    var theaterName = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "Add Theater"
        addressTextField.text = address
        theaterNameTextField.text = theaterName
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
        if theaterNameTextField.isFirstResponder() && touch.view != theaterNameTextField {
            
            // Make countryNameTextField to be no longer the first responder.
            theaterNameTextField.resignFirstResponder()
        }
        
        // If cityNameTextField is first responder and the user did not touch the cityNameTextField
        if addressTextField.isFirstResponder() && touch.view != addressTextField {
            
            // Make cityNameTextField to be no longer the first responder.
            addressTextField.resignFirstResponder()
        }
        
        super.touchesBegan(touches, withEvent:event)
    }
    
    // This method is invoked when the user taps the Save button
    func save(sender: AnyObject) {
        
        if theaterNameTextField.text == "" || addressTextField.text == ""
        {
            var alertView = UIAlertView()
            
            alertView.title = "Form not compelted!"
            alertView.message = "Enter Theater name and Address"
            alertView.delegate = nil
            alertView.addButtonWithTitle("OK")
            
            alertView.show()
        }
        else
        {
        // Ask the delegate object to invoke the protocol method
            delegate!.editTheaterViewController(self, didFinishWithSave:true)
        }
    }
    
    // This method is invoked when the user taps the Done key on the keyboard
    @IBAction func deleteTheater(sender: UIButton) {
        
        if theaterNameTextField.text == "" || addressTextField.text == ""
        {
            var alertView = UIAlertView()
            
            alertView.title = "Form not compelted!"
            alertView.message = "Enter Theater name and Address"
            alertView.delegate = nil
            alertView.addButtonWithTitle("OK")
            
            alertView.show()
        }
        else
        {
        // Once the text field is no longer the first responder, the keyboard is removed
            delegate!.editTheaterDeleteViewController(self, delete: true)
        }
    }
}
