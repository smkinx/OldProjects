//
//  MapViewController.swift
//  Blacksburg
//
//  Created by Supratim Baruah on 10/2/14.
//  Copyright (c) 2014 Supratim Baruah. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: UIViewController, MKMapViewDelegate {

    @IBOutlet var mapView: MKMapView!
    
    var latitude:   Double = 37.2300
    var longitude:  Double = -80.4178
    var span:       Double = 3000.0000
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        var countryLocation: CLLocation = CLLocation(latitude: latitude, longitude: longitude)
        var mapRegion: MKCoordinateRegion? = MKCoordinateRegionMakeWithDistance(countryLocation.coordinate, span, span)
        self.title = "Blacksburg"
        mapView.setRegion(mapRegion!, animated: true)
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func buttonTapped(sender: UIBarButtonItem) {
        
        // tag is a property of the UIBarButtonItem object inherited from the UIView class
        var tagNumber: Int = sender.tag
        
        switch tagNumber {
        case 0: mapView.mapType = MKMapType.Satellite
            
        case 1: mapView.mapType = MKMapType.Hybrid
            
        default:
            mapView.mapType = MKMapType.Standard
        }

    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue!, sender: AnyObject!) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
