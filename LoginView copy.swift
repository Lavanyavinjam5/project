//
//  LoginView.swift
//  ELrearningModel
//
//  Created by admin on 16/02/25.
//

import SwiftUI

struct LoginView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @Environment(\.dismiss) private var dismiss
    
    @State private var email = ""
    @State private var password = ""
    @State private var showRegistration = false
    @State private var loginError = ""
    
    var body: some View {
        NavigationView {
            Form {
                Section(header: Text("Login")) {
                    TextField("Email", text: $email)
                        .keyboardType(.emailAddress)
                        .autocapitalization(.none)
                    SecureField("Password", text: $password)
                }
                
                if !loginError.isEmpty {
                    Text(loginError)
                        .foregroundColor(.red)
                }
                
                Button("Login") {
                    login()
                }
                
                Button("Register") {
                    showRegistration = true
                }
            }
            .navigationTitle("Login")
            .sheet(isPresented: $showRegistration) {
                RegisterView()
            }
        }
    }
    
    private func login() {
        let fetchRequest: NSFetchRequest<User> = User.fetchRequest()
        fetchRequest.predicate = NSPredicate(format: "email == %@ AND password == %@", email, password)
        
        do {
            let users = try viewContext.fetch(fetchRequest)
            if let user = users.first {
                // Successful login
                print("Logged in as \(user.email ?? "")")
                dismiss()
            } else {
                loginError = "Invalid email or password"
            }
        } catch {
            loginError = "Error logging in: \(error.localizedDescription)"
        }
    }
}
