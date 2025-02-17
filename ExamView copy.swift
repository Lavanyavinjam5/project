//
//  ExamView.swift
//  ELrearningModel
//
//  Created by admin on 17/02/25.
//
import SwiftUI

struct ExamView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @ObservedObject var subject: Subject
    
    @State private var currentQuestionIndex = 0
    @State private var selectedAnswer: String?
    @State private var score = 0
    @State private var showResults = false
    
    var body: some View {
        NavigationView {
            VStack {
                if currentQuestionIndex < (subject.questions?.count ?? 0) {
                    let question = (subject.questions?.allObjects as? [Question])?[currentQuestionIndex]
                    
                    Text(question?.text ?? "No Question")
                        .font(.title)
                        .padding()
                    
                    ForEach(question?.options as? [String] ?? [], id: \.self) { option in
                        Button(action: {
                            selectedAnswer = option
                        }) {
                            Text(option)
                                .padding()
                                .frame(maxWidth: .infinity)
                                .background(selectedAnswer == option ? Color.blue : Color.gray)
                                .foregroundColor(.white)
                                .cornerRadius(8)
                        }
                        .padding(.horizontal)
                    }
                    
                    Button("Next") {
                        if selectedAnswer == question?.correctAnswer {
                            score += 1
                        }
                        selectedAnswer = nil
                        currentQuestionIndex += 1
                    }
                    .padding()
                    .disabled(selectedAnswer == nil)
                } else {
                    Text("Exam Completed")
                        .font(.title)
                        .padding()
                    
                    Text("Your score: \(score)/\(subject.questions?.count ?? 0)")
                        .font(.headline)
                        .padding()
                    
                    Button("Save Result") {
                        saveResult()
                    }
                    .padding()
                }
            }
            .navigationTitle("Exam")
        }
    }
    
    private func saveResult() {
        let newResult = ExamResult(context: viewContext)
        newResult.score = Int16(score)
        newResult.totalQuestions = Int16(subject.questions?.count ?? 0)
        newResult.date = Date()
        newResult.subject = subject
        
        do {
            try viewContext.save()
        } catch {
            print("Error saving result: \(error.localizedDescription)")
        }
    }
}
