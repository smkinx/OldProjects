//
//  AppDelegate.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    
    //--------- Create and Initialize the NSMutableDictionary ----------------
    var dict_Genre: NSMutableDictionary = NSMutableDictionary.alloc()
    var dict_Theaters: NSMutableDictionary = NSMutableDictionary.alloc()
    
    
    func application(application: UIApplication!, didFinishLaunchingWithOptions launchOptions: NSDictionary!) -> Bool {
        /*
        All application-specific and user data must be written to files that reside in the iOS device's
        Documents directory. Nothing can be written into application's main bundle (project folder) because
        it is locked for writing after your app is published.
        
        The contents of the iOS device's Documents directory are backed up by iTunes during backup of an iOS device.
        Therefore, the user can recover the data written by your app from an earlier device backup.
        
        The Documents directory path on an iOS device is different from the one used for iOS Simulator.
        
        To obtain the Documents directory path, you use the NSSearchPathForDirectoriesInDomains function.
        However, this function was created originally for Mac OS X, where multiple such directories could exist.
        Therefore, it returns an array of paths rather than a single path.
        
        For iOS, the resulting array's first element (index=0) contains the path to the Documents directory.
        */
        
        let paths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true)
        let documentDirectoryPath = paths[0] as String
        
        let plistFilePathInDocumentDirectory = documentDirectoryPath + "/MyFavoriteMovies.plist"
        
        // NSMutableDictionary manages an *unordered* collection of mutable (changeable) key-value pairs.
        // Instantiate an NSMutableDictionary object and initialize it with the contents of the CountryCities.plist file.
        var dictionaryFromFile: NSMutableDictionary? = NSMutableDictionary(contentsOfFile: plistFilePathInDocumentDirectory)
        
        /*
        If the optional variable dictionaryFromFile has a value, then
        CountryCities.plist exists in the Document directory and the dictionary is successfully created
        else read CountryCities.plist from the application's main bundle.
        */
        if let dictionaryFromFileInDocumentDirectory = dictionaryFromFile {
            
            // CountryCities.plist exists in the Document directory
            dict_Genre = dictionaryFromFileInDocumentDirectory
            
        } else {
            
            // CountryCities.plist does not exist in the Document directory; Read it from the main bundle.
            
            // Obtain the file path to the plist file in the mainBundle (project folder)
            var plistFilePathInMainBundle = NSBundle.mainBundle().pathForResource("MyFavoriteMovies", ofType: "plist")
            
            // Instantiate an NSDictionary object and initialize it with the contents of the CountryCities.plist file.
            var dictionaryFromFileInMainBundle: NSMutableDictionary? = NSMutableDictionary(contentsOfFile: plistFilePathInMainBundle!)
            
            // Typecast the created NSDictionary as Dictionary type and assign it to the property
            dict_Genre = dictionaryFromFileInMainBundle!
            
        }
        
        let plistFile2PathInDocumentDirectory = documentDirectoryPath + "/MyFavoriteTheaters.plist"
        
        // NSMutableDictionary manages an *unordered* collection of mutable (changeable) key-value pairs.
        // Instantiate an NSMutableDictionary object and initialize it with the contents of the CountryCities.plist file.
        var dictionary2FromFile: NSMutableDictionary? = NSMutableDictionary(contentsOfFile: plistFile2PathInDocumentDirectory)
        
        /*
        If the optional variable dictionaryFromFile has a value, then
        CountryCities.plist exists in the Document directory and the dictionary is successfully created
        else read CountryCities.plist from the application's main bundle.
        */
        if let dictionary2FromFileInDocumentDirectory = dictionary2FromFile {
            
            // CountryCities.plist exists in the Document directory
            dict_Theaters = dictionary2FromFileInDocumentDirectory
            
        } else {
            
            // CountryCities.plist does not exist in the Document directory; Read it from the main bundle.
            
            // Obtain the file path to the plist file in the mainBundle (project folder)
            var plistFile2PathInMainBundle = NSBundle.mainBundle().pathForResource("MyFavoriteTheaters", ofType: "plist")
            
            // Instantiate an NSDictionary object and initialize it with the contents of the CountryCities.plist file.
            var dictionary2FromFileInMainBundle: NSMutableDictionary? = NSMutableDictionary(contentsOfFile: plistFile2PathInMainBundle!)
            
            // Typecast the created NSDictionary as Dictionary type and assign it to the property
            dict_Theaters = dictionary2FromFileInMainBundle!
            
        }
        
        return true
    }
    
    func applicationWillResignActive(application: UIApplication!) {
        /*
        "UIApplicationWillResignActiveNotification is posted when the app is no longer active and loses focus.
        An app is active when it is receiving events. An active app can be said to have focus.
        It gains focus after being launched, loses focus when an overlay window pops up or when the device is
        locked, and gains focus when the device is unlocked." [Apple]
        */
        
        // Define the file path to the CountryCities.plist file in the Document directory
        let paths = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true)
        let documentDirectoryPath = paths[0] as String
        let plistFilePathInDocumentDirectory = documentDirectoryPath + "/MyFavoriteMovies.plist"
        
        // Write the dictionary to the CountryCities.plist file in the Document directory
        dict_Genre.writeToFile(plistFilePathInDocumentDirectory, atomically: true)
        
        let paths2 = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true)
        let document2DirectoryPath = paths[0] as String
        let plistFile2PathInDocumentDirectory = documentDirectoryPath + "/MyFavoriteTheaters.plist"
        
        // Write the dictionary to the CountryCities.plist file in the Document directory
        dict_Theaters.writeToFile(plistFile2PathInDocumentDirectory, atomically: true)
        /*
        The flag "atomically" specifies whether the file should be written atomically or not.
        
        If flag is TRUE, the dictionary is first written to an auxiliary file, and
        then the auxiliary file is renamed to path plistFilePathInDocumentDirectory.
        
        If flag is FALSE, the dictionary is written directly to path plistFilePathInDocumentDirectory.
        This is a bad idea since the file can be corrupted if the system crashes during writing.
        
        The TRUE option guarantees that the file will not be corrupted even if the system crashes during writing.
        */
    }

}

