//
//  ELrearningModelApp.swift
//  ELrearningModel
//
//  Created by admin on 16/02/25.
//
import SwiftUI

@main
struct StudeApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            LoginView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
