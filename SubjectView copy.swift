//
//  SubjectView.swift
//  ELrearningModel
//
//  Created by admin on 17/02/25.
//

import SwiftUI

struct SubjectView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @FetchRequest(entity: Subject.entity(), sortDescriptors: []) private var subjects: FetchedResults<Subject>
    
    var body: some View {
        NavigationView {
            List(subjects, id: \.self) { subject in
                NavigationLink(destination: MaterialView(subject: subject)) {
                    Text(subject.name ?? "Unknown Subject")
                }
            }
            .navigationTitle("Subjects")
        }
    }
}
