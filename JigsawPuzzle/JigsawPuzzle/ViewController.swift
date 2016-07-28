//
//  ViewController.swift
//  JigsawPuzzle
//
//  Created by Supratim Baruah on 11/3/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {

    @IBOutlet var instructionLabel: UILabel!
    @IBOutlet weak var timerLabel: UILabel!
    var timer = NSTimer()
    var startTime = NSTimeInterval()
    
    @IBOutlet var endingLabel: UILabel!
    @IBOutlet var congratulations: UIImageView!
    @IBOutlet var rocket1: UIImageView!
    @IBOutlet var rocket2: UIImageView!
    

    var animationStartLocation1 = CGPointMake(0.0, 0.0)
    var animationEndLocation1: CGPoint = CGPointMake(0.0, 0.0)
    var animationStartLocation2 = CGPointMake(0.0, 0.0)
    var animationEndLocation2: CGPoint = CGPointMake(0.0, 0.0)
    let animationDistance: CGFloat = 500.0
    
    @IBOutlet var puzzle1: UIImageView!
    @IBOutlet var puzzle2: UIImageView!
    @IBOutlet var puzzle3: UIImageView!
    @IBOutlet var puzzle4: UIImageView!
    @IBOutlet var puzzle5: UIImageView!
    @IBOutlet var puzzle6: UIImageView!
    @IBOutlet var puzzle7: UIImageView!
    @IBOutlet var puzzle8: UIImageView!
    @IBOutlet var puzzle9: UIImageView!
    @IBOutlet var puzzle10: UIImageView!
    @IBOutlet var puzzle11: UIImageView!
    @IBOutlet var puzzle12: UIImageView!
    
    
    var lastTranslation1 = CGPointMake(0.0, 0.0)
    var lastTranslation2 = CGPointMake(0.0, 0.0)
    var lastTranslation3 = CGPointMake(0.0, 0.0)
    var lastTranslation4 = CGPointMake(0.0, 0.0)
    var lastTranslation5 = CGPointMake(0.0, 0.0)
    var lastTranslation6 = CGPointMake(0.0, 0.0)
    var lastTranslation7 = CGPointMake(0.0, 0.0)
    var lastTranslation8 = CGPointMake(0.0, 0.0)
    var lastTranslation9 = CGPointMake(0.0, 0.0)
    var lastTranslation10 = CGPointMake(0.0, 0.0)
    var lastTranslation11 = CGPointMake(0.0, 0.0)
    var lastTranslation12 = CGPointMake(0.0, 0.0)
    
    
    /*
    ---------------------
    Is Puzzle at the right locatoin
    ---------------------
    */
    var isPuzzle1: Bool = false
    var isPuzzle2: Bool = false
    var isPuzzle3: Bool = false
    var isPuzzle4: Bool = false
    var isPuzzle5: Bool = false;
    var isPuzzle6: Bool = false;
    var isPuzzle7: Bool = false;
    var isPuzzle8: Bool = false;
    var isPuzzle9: Bool = false;
    var isPuzzle10: Bool = false;
    var isPuzzle11: Bool = false;
    var isPuzzle12: Bool = false;
    
    var hours: UInt8 = 0
    var minutes: UInt8 = 0
    var seconds: UInt8 = 0
    var fraction: UInt8 = 0
    
    var dict = [String:AnyObject]()
    
    var options = [String]()
    
    var data = [String]()
    
    @IBOutlet var backgroundRect: UIImageView!
    var clickPlayer: AVAudioPlayer? = nil
    var applaudPlayer: AVAudioPlayer? = nil

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        var btFilePath: NSString? = NSBundle.mainBundle().pathForResource("puzzlePieceCenterCoordinates", ofType: "plist")
        
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
            showErrorMessageFor("puzzlePieceCenterCoordinates.plist")
            return
        }
        
        //------------------------------ Click player --------------------------------------

        var clickFileURL: NSURL? = NSBundle.mainBundle().URLForResource("clickSound", withExtension: "wav")
        
        /*
        Create an AVAudioPlayer object for playing the techTriumph.wav audio file.
        If the object creation is successful, the Optional local variable newAudioPlayer
        will have a value; otherwise, it will be nil implying that it has no value.
        */
        var newAudioPlayer1: AVAudioPlayer? = AVAudioPlayer(contentsOfURL: clickFileURL!, error: nil)
        
        // Unwrap the Optional newAudioPlayer. If it has a value, assign it to createdAudioPlayer.
        // Otherwise, assign nil implying that it has no value.
        if let createdAudioPlayer1 = newAudioPlayer1 {
            
            // An AVAudioPlayer object is successfully created. Assign its obj ref value to audioPlayer
            clickPlayer = createdAudioPlayer1
            
        } else {
            // AVAudioPlayer object creation failed!
            
            println("AVAudioPlayer object creation failed!")
        }
        
        //------------------------------ Applaud player --------------------------------------

        var applaudFileURL: NSURL? = NSBundle.mainBundle().URLForResource("applaudSound", withExtension: "wav")

        var newAudioPlayer2: AVAudioPlayer? = AVAudioPlayer(contentsOfURL: applaudFileURL!, error: nil)
        
        if let createdAudioPlayer2 = newAudioPlayer2 {
            
            // An AVAudioPlayer object is successfully created. Assign its obj ref value to audioPlayer
            applaudPlayer = createdAudioPlayer2
            
        } else {
            
            println("AVAudioPlayer object creation failed!")
        }


        //------------------------------ Image View 1 --------------------------------------
        // Create Panning (Dragging) Gesture Recognizer for Image View 1
        var panRecognizer1 = UIPanGestureRecognizer(target: self, action: "handlePanning1:")
        
        // Add Panning (Dragging) Gesture Recognizer to Image View 1
        puzzle1.addGestureRecognizer(panRecognizer1)
        puzzle1.hidden = true
        
        //------------------------------ Image View 2 --------------------------------------
        // Create Panning (Dragging) Gesture Recognizer for Image View 2
        var panRecognizer2 = UIPanGestureRecognizer(target: self, action: "handlePanning2:")
        
        // Add Panning (Dragging) Gesture Recognizer to Image View 2
        puzzle2.addGestureRecognizer(panRecognizer2)
        puzzle2.hidden = true
        
        //------------------------------ Image View 3 --------------------------------------
        // Create Panning (Dragging) Gesture Recognizer for Image View 3
        var panRecognizer3 = UIPanGestureRecognizer(target: self, action: "handlePanning3:")
        
        // Add Panning (Dragging) Gesture Recognizer to Image View 3
        puzzle3.addGestureRecognizer(panRecognizer3)
        puzzle3.hidden = true
        
        //------------------------------ Image View 4 --------------------------------------
        // Create Panning (Dragging) Gesture Recognizer for Image View 4
        var panRecognizer4 = UIPanGestureRecognizer(target: self, action: "handlePanning4:")
        
        // Add Panning (Dragging) Gesture Recognizer to Image View 4
        puzzle4.addGestureRecognizer(panRecognizer4)
        puzzle4.hidden = true
        
        //------------------------------ Image View 5 --------------------------------------
        
        var panRecognizer5 = UIPanGestureRecognizer(target: self, action: "handlePanning5:")
        
        puzzle5.addGestureRecognizer(panRecognizer5)
        puzzle5.hidden = true
        //------------------------------ Image View 6 --------------------------------------
        
        var panRecognizer6 = UIPanGestureRecognizer(target: self, action: "handlePanning6:")
        
        puzzle6.addGestureRecognizer(panRecognizer6)
        puzzle6.hidden = true
        //------------------------------ Image View 7 --------------------------------------
        
        var panRecognizer7 = UIPanGestureRecognizer(target: self, action: "handlePanning7:")
        
        puzzle7.addGestureRecognizer(panRecognizer7)
        puzzle7.hidden = true
        //------------------------------ Image View 8 --------------------------------------
        
        var panRecognizer8 = UIPanGestureRecognizer(target: self, action: "handlePanning8:")
        
        puzzle8.addGestureRecognizer(panRecognizer8)
        puzzle8.hidden = true
        //------------------------------ Image View 9 --------------------------------------
        
        var panRecognizer9 = UIPanGestureRecognizer(target: self, action: "handlePanning9:")
        
        puzzle9.addGestureRecognizer(panRecognizer9)
        puzzle9.hidden = true
        //------------------------------ Image View 10 --------------------------------------
        
        var panRecognizer10 = UIPanGestureRecognizer(target: self, action: "handlePanning10:")
        
        puzzle10.addGestureRecognizer(panRecognizer10)
        puzzle10.hidden = true
        //------------------------------ Image View 11 --------------------------------------
        
        var panRecognizer11 = UIPanGestureRecognizer(target: self, action: "handlePanning11:")
        
        puzzle11.addGestureRecognizer(panRecognizer11)
        puzzle11.hidden = true
        //------------------------------ Image View 12 --------------------------------------
        
        var panRecognizer12 = UIPanGestureRecognizer(target: self, action: "handlePanning12:")
        
        puzzle12.addGestureRecognizer(panRecognizer12)
        puzzle12.hidden = true

        
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

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func newGame(sender: UIButton)
    {

        drawRectangle();
        timer.invalidate();
        if (!timer.valid) {
            let aSelector : Selector = "updateTime"
            timer = NSTimer.scheduledTimerWithTimeInterval(0.01, target: self, selector: aSelector, userInfo: nil, repeats: true)
            startTime = NSDate.timeIntervalSinceReferenceDate()
        }
        lastTranslation1.x = CGFloat(arc4random_uniform(750))
        lastTranslation1.y = CGFloat(arc4random_uniform(600))
        puzzle1.transform = CGAffineTransformMakeTranslation(lastTranslation1.x,  lastTranslation1.y)
        puzzle1.image = UIImage(named: "puzzleImage1.jpg");
        
        lastTranslation2.x = CGFloat(arc4random_uniform(750))
        lastTranslation2.y = CGFloat(arc4random_uniform(600))
        puzzle2.transform = CGAffineTransformMakeTranslation(lastTranslation2.x,  lastTranslation2.y)
        puzzle2.image = UIImage(named: "puzzleImage2.jpg");
        
        lastTranslation3.x = CGFloat(arc4random_uniform(750))
        lastTranslation3.y = CGFloat(arc4random_uniform(600))
        puzzle3.transform = CGAffineTransformMakeTranslation(lastTranslation3.x,  lastTranslation3.y)
        puzzle3.image = UIImage(named: "puzzleImage3.jpg");
        
        lastTranslation4.x = CGFloat(arc4random_uniform(750))
        lastTranslation4.y = CGFloat(arc4random_uniform(600))
        puzzle4.transform = CGAffineTransformMakeTranslation(lastTranslation4.x,  lastTranslation4.y)
        puzzle4.image = UIImage(named: "puzzleImage4.jpg");
        
        lastTranslation5.x = CGFloat(arc4random_uniform(750))
        lastTranslation5.y = CGFloat(arc4random_uniform(600))
        puzzle5.transform = CGAffineTransformMakeTranslation(lastTranslation5.x,  lastTranslation5.y)
        puzzle5.image = UIImage(named: "puzzleImage5.jpg");
        
        lastTranslation6.x = CGFloat(arc4random_uniform(750))
        lastTranslation6.y = CGFloat(arc4random_uniform(600))
        puzzle6.transform = CGAffineTransformMakeTranslation(lastTranslation6.x,  lastTranslation6.y)
        puzzle6.image = UIImage(named: "puzzleImage6.jpg");
        
        lastTranslation7.x = CGFloat(arc4random_uniform(750))
        lastTranslation7.y = CGFloat(arc4random_uniform(600))
        puzzle7.transform = CGAffineTransformMakeTranslation(lastTranslation7.x,  lastTranslation7.y)
        puzzle7.image = UIImage(named: "puzzleImage7.jpg");
        
        lastTranslation8.x = CGFloat(arc4random_uniform(750))
        lastTranslation8.y = CGFloat(arc4random_uniform(600))
        puzzle8.transform = CGAffineTransformMakeTranslation(lastTranslation8.x,  lastTranslation8.y)
        puzzle8.image = UIImage(named: "puzzleImage8.jpg");
        
        lastTranslation9.x = CGFloat(arc4random_uniform(750))
        lastTranslation9.y = CGFloat(arc4random_uniform(600))
        puzzle9.transform = CGAffineTransformMakeTranslation(lastTranslation9.x,  lastTranslation9.y)
        puzzle9.image = UIImage(named: "puzzleImage9.jpg");
        
        lastTranslation10.x = CGFloat(arc4random_uniform(750))
        lastTranslation10.y = CGFloat(arc4random_uniform(600))
        puzzle10.transform = CGAffineTransformMakeTranslation(lastTranslation10.x,  lastTranslation10.y)
        puzzle10.image = UIImage(named: "puzzleImage10.jpg");
        
        lastTranslation11.x = CGFloat(arc4random_uniform(750))
        lastTranslation11.y = CGFloat(arc4random_uniform(600))
        puzzle11.transform = CGAffineTransformMakeTranslation(lastTranslation11.x,  lastTranslation11.y)
        puzzle11.image = UIImage(named: "puzzleImage11.jpg");
        
        lastTranslation12.x = CGFloat(arc4random_uniform(750))
        lastTranslation12.y = CGFloat(arc4random_uniform(600))
        puzzle12.transform = CGAffineTransformMakeTranslation(lastTranslation12.x,  lastTranslation12.y)
        puzzle12.image = UIImage(named: "puzzleImage12.jpg");
        
        endingLabel.text = " "
        instructionLabel.text = " "
        congratulations.image = nil
        puzzle1.hidden = false
        puzzle2.hidden = false
        puzzle3.hidden = false
        puzzle4.hidden = false
        puzzle5.hidden = false
        puzzle6.hidden = false
        puzzle7.hidden = false
        puzzle8.hidden = false
        puzzle9.hidden = false
        puzzle10.hidden = false
        puzzle11.hidden = false
        puzzle12.hidden = false
        isPuzzle1 = false
        isPuzzle2 = false
        isPuzzle3 = false
        isPuzzle4 = false
        isPuzzle5 = false;
        isPuzzle6 = false;
        isPuzzle7 = false;
        isPuzzle8 = false;
        isPuzzle9 = false;
        isPuzzle10 = false;
        isPuzzle11 = false;
        isPuzzle12 = false;

    }
    /*
    ---------------------
    MARK: - Create Canvas
    ---------------------
    */
    
    // This method creates a rectangular canvas centered on the screen to hold the puzzle
    // pieces when they are all put together (i.e., when the puzzle is solved).
    
     func drawRectangle() {
        
        // Set the size of the canvas to be created to width=603 and height=453
        let canvasSize = CGSizeMake(603, 453)
        
        // Set the properties of the graphics context to be created
        let bounds = CGRect(origin: CGPoint.zeroPoint, size: canvasSize)
        let opaque = false
        let scale: CGFloat = 0
        
        // Create a bitmap-based graphics context with the specified properties
        UIGraphicsBeginImageContextWithOptions(canvasSize, opaque, scale)
        
        // Set the current graphics context returned to context
        let context = UIGraphicsGetCurrentContext()
        
        // Set the current stroke color in the graphics context, using a Quartz color
        CGContextSetStrokeColorWithColor(context, UIColor.blackColor().CGColor)
        
        // Set the line width for the graphics context to 3 points
        CGContextSetLineWidth(context, 3.0)
        
        // Paint a rectangular path
        CGContextStrokeRect(context, bounds)
        
        CGContextBeginPath(context)
        CGContextMoveToPoint(context, CGRectGetMinX(bounds), CGRectGetMinY(bounds))
        CGContextMoveToPoint(context, CGRectGetMaxX(bounds), CGRectGetMinY(bounds))
        CGContextStrokePath(context)
        
        // Return an image based on the contents of the current bitmap-based graphics context
        let contextImage = UIGraphicsGetImageFromCurrentImageContext()
        
        // Remove the current bitmap-based graphics context from the top of the stack
        UIGraphicsEndImageContext()
        
        /*
        Create a UIImageView object in Storyboard with x=210, y=158, width=603, height=453
        Create an outlet for it as: @IBOutlet var backgroundRect: UIImageView!
        
        Set the UIImageView object's image to the image created based on the contents of
        the current bitmap-based graphics context.
        */
        backgroundRect.image = contextImage
        
        
    }
    
    /*
    ---------------------
    MARK: - Handlers for the puzzle peices
    ---------------------
    */
    
    func handlePanning1(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation1.x + newTranslation.x, lastTranslation1.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle1 = false
            lastTranslation1.x += newTranslation.x
            lastTranslation1.y += newTranslation.y
            var widthHalf: CGFloat = 165 / 2
            var heightHalf: CGFloat = 170 / 2
            var xCenter: CGFloat = 291
            var yCenter: CGFloat = 240
            var x: CGFloat = lastTranslation1.x
            var y: CGFloat = lastTranslation1.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation1.x = xCenter - widthHalf
                lastTranslation1.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation1.x,  lastTranslation1.y);
                isPuzzle1 = true
                clickPlayer!.play()
                finished()
            }

        }
        
        
    }
    func handlePanning2(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation2.x + newTranslation.x, lastTranslation2.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle2 = false

            lastTranslation2.x += newTranslation.x
            lastTranslation2.y += newTranslation.y
            var widthHalf: CGFloat = 228 / 2
            var heightHalf: CGFloat = 179 / 2
            var xCenter: CGFloat = 442
            var yCenter: CGFloat = 245
            var x: CGFloat = lastTranslation2.x
            var y: CGFloat = lastTranslation2.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation2.x = xCenter - widthHalf
                lastTranslation2.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation2.x,  lastTranslation2.y);
                isPuzzle2 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    
    func handlePanning3(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation3.x + newTranslation.x, lastTranslation3.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle3 = false

            lastTranslation3.x += newTranslation.x
            lastTranslation3.y += newTranslation.y
            var widthHalf: CGFloat = 170 / 2
            var heightHalf: CGFloat = 171 / 2
            var xCenter: CGFloat = 570
            var yCenter: CGFloat = 241
            var x: CGFloat = lastTranslation3.x
            var y: CGFloat = lastTranslation3.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation3.x = xCenter - widthHalf
                lastTranslation3.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation3.x,  lastTranslation3.y);
                isPuzzle3 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    
    func handlePanning4(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation4.x + newTranslation.x, lastTranslation4.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle4 = false

            lastTranslation4.x += newTranslation.x
            lastTranslation4.y += newTranslation.y
            var widthHalf: CGFloat = 174 / 2
            var heightHalf: CGFloat = 176 / 2
            var xCenter: CGFloat = 727
            var yCenter: CGFloat = 244
            var x: CGFloat = lastTranslation4.x
            var y: CGFloat = lastTranslation4.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation4.x = xCenter - widthHalf
                lastTranslation4.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation4.x,  lastTranslation4.y);
                isPuzzle4 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning5(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation5.x + newTranslation.x, lastTranslation5.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle5 = false

            lastTranslation5.x += newTranslation.x
            lastTranslation5.y += newTranslation.y
            var widthHalf: CGFloat = 158 / 2
            var heightHalf: CGFloat = 187 / 2
            var xCenter: CGFloat = 287
            var yCenter: CGFloat = 382
            var x: CGFloat = lastTranslation5.x
            var y: CGFloat = lastTranslation5.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation5.x = xCenter - widthHalf
                lastTranslation5.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation5.x,  lastTranslation5.y);
                isPuzzle5 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning6(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation6.x + newTranslation.x, lastTranslation6.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle6 = false

            lastTranslation6.x += newTranslation.x
            lastTranslation6.y += newTranslation.y
            var widthHalf: CGFloat = 172 / 2
            var heightHalf: CGFloat = 189 / 2
            var xCenter: CGFloat = 445
            var yCenter: CGFloat = 394
            var x: CGFloat = lastTranslation6.x
            var y: CGFloat = lastTranslation6.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation6.x = xCenter - widthHalf
                lastTranslation6.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation6.x,  lastTranslation6.y);
                isPuzzle6 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning7(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation7.x + newTranslation.x, lastTranslation7.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle7 = false

            lastTranslation7.x += newTranslation.x
            lastTranslation7.y += newTranslation.y
            var widthHalf: CGFloat = 188 / 2
            var heightHalf: CGFloat = 187 / 2
            var xCenter: CGFloat = 586
            var yCenter: CGFloat = 395
            var x: CGFloat = lastTranslation7.x
            var y: CGFloat = lastTranslation7.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation7.x = xCenter - widthHalf
                lastTranslation7.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation7.x,  lastTranslation7.y);
                isPuzzle7 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning8(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation8.x + newTranslation.x, lastTranslation8.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle8 = false

            lastTranslation8.x += newTranslation.x
            lastTranslation8.y += newTranslation.y
            var widthHalf: CGFloat = 176 / 2
            var heightHalf: CGFloat = 164 / 2
            var xCenter: CGFloat = 726
            var yCenter: CGFloat = 388
            var x: CGFloat = lastTranslation8.x
            var y: CGFloat = lastTranslation8.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation8.x = xCenter - widthHalf
                lastTranslation8.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation8.x,  lastTranslation8.y);
                isPuzzle8 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning9(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation9.x + newTranslation.x, lastTranslation9.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle9 = false

            lastTranslation9.x += newTranslation.x
            lastTranslation9.y += newTranslation.y
            var widthHalf: CGFloat = 168 / 2
            var heightHalf: CGFloat = 153 / 2
            var xCenter: CGFloat = 292
            var yCenter: CGFloat = 533
            var x: CGFloat = lastTranslation9.x
            var y: CGFloat = lastTranslation9.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation9.x = xCenter - widthHalf
                lastTranslation9.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation9.x,  lastTranslation9.y);
                isPuzzle9 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning10(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation10.x + newTranslation.x, lastTranslation10.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle10 = false

            lastTranslation10.x += newTranslation.x
            lastTranslation10.y += newTranslation.y
            var widthHalf: CGFloat = 189 / 2
            var heightHalf: CGFloat = 153 / 2
            var xCenter: CGFloat = 452
            var yCenter: CGFloat = 533
            var x: CGFloat = lastTranslation10.x
            var y: CGFloat = lastTranslation10.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation10.x = xCenter - widthHalf
                lastTranslation10.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation10.x,  lastTranslation10.y);
                isPuzzle10 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning11(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation11.x + newTranslation.x, lastTranslation11.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle11 = false

            lastTranslation11.x += newTranslation.x
            lastTranslation11.y += newTranslation.y
            var widthHalf: CGFloat = 187 / 2
            var heightHalf: CGFloat = 154 / 2
            var xCenter: CGFloat = 582
            var yCenter: CGFloat = 532
            var x: CGFloat = lastTranslation11.x
            var y: CGFloat = lastTranslation11.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation11.x = xCenter - widthHalf
                lastTranslation11.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation11.x,  lastTranslation11.y);
                isPuzzle11 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    func handlePanning12(recognizer: UIPanGestureRecognizer) {
        
        var newTranslation: CGPoint = recognizer.translationInView(recognizer.view!)
        
        recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation12.x + newTranslation.x, lastTranslation12.y + newTranslation.y)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            isPuzzle12 = false

            lastTranslation12.x += newTranslation.x
            lastTranslation12.y += newTranslation.y
            var widthHalf: CGFloat = 180 / 2
            var heightHalf: CGFloat = 156 / 2
            var xCenter: CGFloat = 723
            var yCenter: CGFloat = 532
            var x: CGFloat = lastTranslation12.x
            var y: CGFloat = lastTranslation12.y
            if(widthHalf  + x <= xCenter + 20 && widthHalf + x >= xCenter - 20 && heightHalf + y <= yCenter + 20 && heightHalf + y >= yCenter - 20)
            {
                lastTranslation12.x = xCenter - widthHalf
                lastTranslation12.y = yCenter - heightHalf
                recognizer.view?.transform = CGAffineTransformMakeTranslation(lastTranslation12.x,  lastTranslation12.y);
                isPuzzle12 = true
                clickPlayer!.play()
                finished()
            }
        }
    }
    
    
    /*
    ---------------------
    Timer
    ---------------------
    */
    func updateTime() {
        var currentTime = NSDate.timeIntervalSinceReferenceDate()
        
        //Find the difference between current time and start time.
        var elapsedTime: NSTimeInterval = currentTime - startTime
        
        hours = UInt8(elapsedTime / 360.0)
        elapsedTime -= (NSTimeInterval(hours) * 360)
        
        //calculate the minutes in elapsed time.
        minutes = UInt8(elapsedTime / 60.0)
        elapsedTime -= (NSTimeInterval(minutes) * 60)
        
        //calculate the seconds in elapsed time.
        seconds = UInt8(elapsedTime)
        elapsedTime -= NSTimeInterval(seconds)
        
        //find out the fraction of milliseconds to be displayed.
        let fraction = UInt8(elapsedTime * 100)
      
        //add the leading zero
        let strHours = hours > 9 ? String(hours):"0" + String(hours)
        let strMinutes = minutes > 9 ? String(minutes):"0" + String(minutes)
        let strSeconds = seconds > 9 ? String(seconds):"0" + String(seconds)
        let strFraction = fraction > 9 ? String(fraction):"0" + String(fraction)
        
        //concatenate minuets, seconds and milliseconds as assign it to the UILabel
        timerLabel.text = "\(strHours):\(strMinutes):\(strSeconds).\(strFraction)"
    }
    
    /*
    ---------------------
    Checks if you finished the puzzle
    ---------------------
    */
    func finished(){
        if(isPuzzle1 == true && isPuzzle2 == true && isPuzzle3 == true && isPuzzle4 == true)
        {
            if(isPuzzle5 == true && isPuzzle6 == true && isPuzzle7 == true && isPuzzle8 == true)
            {
                if(isPuzzle9 == true && isPuzzle10 == true && isPuzzle11 == true && isPuzzle12 == true)
                {
                    applaudPlayer!.play()
                    timer.invalidate();
                    congratulations.image = UIImage(named: "congrats.jpg");
                    instructionLabel.text = "Tap New Game to Play Again!"
                    //------------------------------ Lable --------------------------------------
                    if(seconds < 20 && hours == 0 && minutes == 0 && fraction <= 0)
                    {
                        endingLabel.text = "You did Outstanding"
                    }
                    else if(seconds >= 20 && seconds <= 40 && hours == 0 && minutes == 0 && fraction <= 0)
                    {
                        endingLabel.text = "You did Excellent"
                    }
                    else if(hours == 0 && minutes < 2)
                    {
                        endingLabel.text = "You did Good"

                    }
                    else if(minutes >= 3 && seconds > 0)
                    {
                        endingLabel.text = "You did Slow"
                    }
                    else
                    {
                        endingLabel.text = "You did Satisfactory"
                    }
                    
                    //------------------------------ Rocket Animation --------------------------------------
                    rocket1.image = UIImage(named: "rocketFlyingUp.png")
                    animationEndLocation1.x = animationStartLocation1.x
                    animationEndLocation1.y = animationStartLocation1.y - animationDistance
                    // Set the image view center to the animation start location
                    rocket1.center = animationStartLocation1
                    
                    rocket2.image = UIImage(named: "rocketFlyingUp.png")
                    animationEndLocation2.x = animationStartLocation2.x
                    animationEndLocation2.y = animationStartLocation2.y - animationDistance
                    // Set the image view center to the animation start location
                    rocket2.center = animationStartLocation2
                    // Make the image entirely visible at the start of animation
                    rocket1.alpha = 5.0
                    rocket2.alpha = 5.0
                    // Marks the beginning of a begin/commit animation block
                    UIView.beginAnimations("", context: nil)
                    
                    // Sets the duration (in seconds) of the animation in the animation block
                    UIView.setAnimationDuration(1.50)
                    
                    // Set the image view center to the animation end location
                    rocket1.center = animationEndLocation1
                    rocket2.center = animationEndLocation2
                    
                    // Make the image entirely invisible (fade out) at the end of the animation
                    rocket1.alpha = 0.0
                    rocket2.alpha = 0.0
                    // Marks the end of a begin/commit animation block and schedules the animations for execution.
                    UIView.commitAnimations()

                    
                }
            }
        }

    }
}

