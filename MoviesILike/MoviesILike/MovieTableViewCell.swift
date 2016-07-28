//
//  MovieTableViewCell.swift
//  MoviesILike
//
//  Created by Supratim Baruah on 11/20/14.
//  Copyright (c) 2014 SupratimBaruah. All rights reserved.
//

import UIKit

class MovieTableViewCell: UITableViewCell {

    @IBOutlet var moviePosterImageView: UIImageView!
    @IBOutlet var movieTitleLabel: UILabel!
    @IBOutlet var audienceScoreLabel: UILabel!
    @IBOutlet var movieStarsLabel: UILabel!
    @IBOutlet var mpaaRatingRuntimeDateLabel: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
}