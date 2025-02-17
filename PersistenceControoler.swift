//
//  PersistenceControoler.swift
//  Fitness
//
//  Created by admin on 04/02/25.
//

import Foundation
import CoreData

class PersistenceController {
    static let shared  = PersistenceController()
    let container: NSPersistentContainer
    private init() {
        container = NSPersistentContainer(name: "Fitness")
        container.loadPersistentStores { (storeDescription, error)  in
            if let error = error as NSError? {
                fatalError ("unresolved  error \(error), \(error.userInfo)")
                
            }
            
            }
        
    }
    var context: NSManagedObjectContext {
        return container.viewContext
    }
    func saveContext() {
        let context = container.viewContext
        if context.hasChanges {
            do {
                try context.save()
                
            } catch  {
                let nserror = error as NSError
                fatalError("unresolved error \(nserror.userInfo)")
            }
        }
    }
    
    
}
