//
//  MainView.swift
//  ELrearningModel
//
//  Created by admin on 17/02/25.
//
import SwiftUI

struct MainView: View {
    @Environment(\.managedObjectContext) private var viewContext
    
    var body: some View {
        TabView {
            SubjectView()
                .tabItem {
                    Label("Subjects", systemImage: "book")
                }
            
            ResultsView()
                .tabItem {
                    Label("Results", systemImage: "chart.bar")
                }
        }
    }
}
