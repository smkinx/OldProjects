//
//  ViewController.swift
//  Calculator
//
//  Created by Supratim Baruah on 9/16/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
                            
    @IBOutlet var ClearButton: UIButton!

   
    @IBOutlet var message: UILabel!
    var num1:Float = -1
    var num2:Float = -1
    
    var op = -1;
    
    var periodCount:Int = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func buttonTapped(sender: UIButton) {
        
        // tag is a property of the UIButton object inherited from the UIView class
        var tagNumber: Int = sender.tag
        
        switch tagNumber {
        case 0: message.text = message.text + "0"
            
        case 1: message.text = message.text + "1"
            
        case 2: message.text = message.text + "2"

        case 3: message.text = message.text + "3"

        case 4: message.text = message.text + "4"
            
        case 5: message.text = message.text + "5"

        case 6: message.text = message.text + "6"
        
        case 7: message.text = message.text + "7"
        
        case 8: message.text = message.text + "8"
            
        case 9: message.text = message.text + "9"
         
        //clear
        case 10: message.text = ""
            num1 = -1
            num2 = -1
            op = -1
            periodCount = 0
        
        //divide
        case 11: op = 1
            num1 = (message.text as NSString).floatValue
            message.text = ""
            periodCount = 0
            
        //multiply
        case 12: op = 2
            num1 = (message.text as NSString).floatValue
            message.text = ""
            periodCount = 0
            
        //subtract
        case 13: op = 3
            num1 = (message.text as NSString).floatValue
            message.text = ""
            periodCount = 0
        
        //add
        case 14: op = 4
            num1 = (message.text as NSString).floatValue
            message.text = ""
            periodCount = 0
            
        case 15:
            if (op == -1)
            {
                displayErrorMessage("Please enter number1 (÷ x - or +) number2 then tap =")
            }
            else
            {
                num2 = (message.text as NSString).floatValue
                message.text = ""
                periodCount = 1
                switch op {
                case 1: message.text = NSString(format: "%.3f", num1 / num2)
                
                case 2: message.text = NSString(format: "%.3f", num1 * num2)
                    
                case 3: message.text = NSString(format: "%.3f", num1 - num2)
                
                case 4: message.text = NSString(format: "%.3f", num1 + num2)
                
                default:
                    println("op number is out of range!")
                }
            }
            
        case 16:
            if(periodCount == 0)
            {
                message.text = message.text + "."
                periodCount++;
            }

            
        default:
            println("Tag number is out of range!")
        }
    }
    
    func displayErrorMessage(messageText: String) {
        
        
        var alertView = UIAlertView()
        
        alertView.title = "Unable to Compute!"
        alertView.message = messageText
        alertView.delegate = nil
        alertView.addButtonWithTitle("OK")
        
        alertView.show()
    }
    

}

