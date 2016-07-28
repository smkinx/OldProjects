//
//  TableViewCell.swift
//  Blacksburg
//
//  Created by Supratim Baruah on 10/1/14.
//  Copyright (c) 2014 Supratim Baruah. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell {

    @IBOutlet var optionLabel: UILabel!

   
    @IBOutlet var flagImage: UIImageView?
    @IBOutlet var btLabel: UILabel!
    @IBOutlet var optionLabele: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
