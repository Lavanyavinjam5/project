//
//  RegisterView.swift
//  ELrearningModel
//
//  Created by admin on 16/02/25.
//

import SwiftUI

struct RegisterView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @Environment(\.dismiss) private var dismiss
    
    @State private var email = ""
    @State private var password = ""
    @State private var confirmPassword = ""
    @State private var registrationError = ""
    
    var body: some View {
        NavigationView {
            Form {
                Section(header: Text("Registration")) {
                    TextField("Email", text: $email)
                        .keyboardType(.emailAddress)
                        .autocapitalization(.none)
                    SecureField("Password", text: $password)
                    SecureField("Confirm Password", text: $confirmPassword)
                }
                
                if !registrationError.isEmpty {
                    Text(registrationError)
                        .foregroundColor(.red)
                }
                
                Button("Register") {
                    register()
                }
            }
            .navigationTitle("Register")
        }
    }
    
    private func register() {
        guard password == confirmPassword else {
            registrationError = "Passwords do not match"
            return
        }
        
        let newUser = User(context: viewContext)
        newUser.email = email
        newUser.password = password
        
        do {
            try viewContext.save()
            dismiss()
        } catch {
            registrationError = "Error registering: \(error.localizedDescription)"
        }
    }
}
