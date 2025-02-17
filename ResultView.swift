//
//  ResultView.swift
//  ELrearningModel
//
//  Created by admin on 17/02/25.
//

 import SwiftUI

struct ResultsView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @FetchRequest(entity: ExamResult.entity(), sortDescriptors: []) private var results: FetchedResults<ExamResult>
    
    var body: some View {
        NavigationView {
            List(results, id: \.self) { result in
                VStack(alignment: .leading) {
                    Text("Subject: \(result.subject?.name ?? "Unknown")")
                    Text("Score: \(result.score)/\(result.totalQuestions)")
                    Text("Date: \(result.date ?? Date(), formatter: dateFormatter)")
                }
            }
            .navigationTitle("Exam Results")
        }
    }
    
    private let dateFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateStyle = .short
        formatter.timeStyle = .short
        return formatter
    }()
}
