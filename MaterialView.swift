//
//  MaterialView.swift
//  ELrearningModel
//
//  Created by admin on 17/02/25.
//

import SwiftUI

struct MaterialView: View {
    @ObservedObject var subject: Subject
    
    var body: some View {
        VStack {
            Text("Materials for \(subject.name ?? "")")
                .font(.title)
                .padding()
            
List(subject.materials?.allObjects as? [Material] ?? [], id: \.self) { material in
                VStack(alignment: .leading) {
                    Text(material.title ?? "Unknown Title")
                    Text(material.url ?? "No URL")
                        .font(.caption)
                        .foregroundColor(.gray)
                }
            }
            
            Spacer()
        }
    }
}
