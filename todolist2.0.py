import tkinter as tk
from tkinter import messagebox
import mysql.connector

class ToDoListApp:
    def __init__(self, master):
        self.master = master
        self.master.title("To-Do List")

        self.logged_in = False
        self.current_user = None
        self.current_user_id = None

        # Connexion à la base de données
        self.db = mysql.connector.connect(
            host="localhost",
            user="root",
            password="",
            database="todolist"
        )

        # Création du curseur
        self.cursor = self.db.cursor()

        # Création de la table users si elle n'existe pas
        self.cursor.execute("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255), password VARCHAR(255))")

        # Création de la table tasks si elle n'existe pas
        self.cursor.execute("CREATE TABLE IF NOT EXISTS tasks (id INT AUTO_INCREMENT PRIMARY KEY, user_id INT, task VARCHAR(255))")

        self.tasks = []

        # Interface utilisateur
        self.task_entry = tk.Entry(master, width=50, state=tk.DISABLED)
        self.task_entry.grid(row=0, column=0, padx=5, pady=5)

        self.add_button = tk.Button(master, text="Add Task", command=self.add_task, state=tk.DISABLED)
        self.add_button.grid(row=0, column=1, padx=5, pady=5)

        self.task_list = tk.Listbox(master, width=50)
        self.task_list.grid(row=1, column=0, columnspan=2, padx=5, pady=5)

        self.delete_button = tk.Button(master, text="Delete Task", command=self.delete_task, state=tk.DISABLED)
        self.delete_button.grid(row=2, column=0, columnspan=2, padx=5, pady=5)

        self.login_button = tk.Button(master, text="Login", command=self.show_login_dialog)
        self.login_button.grid(row=3, column=0, padx=5, pady=5)

        self.register_button = tk.Button(master, text="Register", command=self.show_register_dialog)
        self.register_button.grid(row=3, column=1, padx=5, pady=5)

        self.logout_button = tk.Button(master, text="Logout", command=self.logout, state=tk.DISABLED)
        self.logout_button.grid(row=4, column=0, columnspan=2, padx=5, pady=5)

    def show_login_dialog(self):
        login_dialog = LoginDialog(self.master, self.login)
        self.master.wait_window(login_dialog.top)

    def show_register_dialog(self):
        register_dialog = RegisterDialog(self.master, self.register)
        self.master.wait_window(register_dialog.top)

    def login(self, email, password):
        self.cursor.execute("SELECT * FROM users WHERE email = %s AND password = %s", (email, password))
        user = self.cursor.fetchone()
        if user:
            self.current_user_id = user[0]
            self.current_user = email
            self.logged_in = True
            self.task_entry.config(state=tk.NORMAL)
            self.add_button.config(state=tk.NORMAL)
            self.delete_button.config(state=tk.NORMAL)
            self.login_button.config(state=tk.DISABLED)
            self.register_button.config(state=tk.DISABLED)
            self.logout_button.config(state=tk.NORMAL)
            messagebox.showinfo("Login", "Successfully logged in as {}".format(email))
            self.load_tasks()
        else:
            messagebox.showerror("Login Error", "Invalid email or password")


    def load_tasks(self):
        self.tasks.clear()
        self.task_list.delete(0, tk.END)
        self.cursor.execute("SELECT * FROM tasks WHERE user_id = %s", (self.current_user_id,))
        tasks = self.cursor.fetchall()
        for task in tasks:
            self.tasks.append(task)
            self.task_list.insert(tk.END, task[2])

    def register(self, email, password):
        self.cursor.execute("SELECT * FROM users WHERE email = %s", (email,))
        user = self.cursor.fetchone()
        if not user:
            self.cursor.execute("INSERT INTO users (email, password) VALUES (%s, %s)", (email, password))
            self.db.commit()
            self.current_user_id = self.cursor.lastrowid
            self.current_user = email
            self.logged_in = True
            self.task_entry.config(state=tk.NORMAL)
            self.add_button.config(state=tk.NORMAL)
            self.delete_button.config(state=tk.NORMAL)
            self.login_button.config(state=tk.DISABLED)
            self.register_button.config(state=tk.DISABLED)
            self.logout_button.config(state=tk.NORMAL)
            messagebox.showinfo("Registration", "Successfully registered as {}".format(email))
        else:
            messagebox.showerror("Registration Error", "Email already exists")

    def logout(self):
        self.logged_in = False
        self.current_user = None
        self.current_user_id = None
        self.task_entry.config(state=tk.DISABLED)
        self.add_button.config(state=tk.DISABLED)
        self.delete_button.config(state=tk.DISABLED)
        self.login_button.config(state=tk.NORMAL)
        self.register_button.config(state=tk.NORMAL)
        self.logout_button.config(state=tk.DISABLED)
        self.task_list.delete(0, tk.END)
        messagebox.showinfo("Logout", "Successfully logged out")

    def add_task(self):
        task = self.task_entry.get()
        if task:
            self.tasks.append(task)
            self.task_list.insert(tk.END, task)
            self.cursor.execute("INSERT INTO tasks (user_id, task) VALUES (%s, %s)", (self.current_user_id, task))
            self.db.commit()
            self.task_entry.delete(0, tk.END)

    def delete_task(self):
        try:
            index = self.task_list.curselection()[0]
            task_id = self.tasks[index][0]
            del self.tasks[index]
            self.task_list.delete(index)
            self.cursor.execute("DELETE FROM tasks WHERE id = %s", (task_id,))
            self.db.commit()
        except IndexError:
            pass

class LoginDialog:
    def __init__(self, parent, callback):
        self.top = tk.Toplevel(parent)
        self.top.title("Login")

        self.label_email = tk.Label(self.top, text="Email:")
        self.label_email.grid(row=0, column=0, padx=5, pady=5)
        self.entry_email = tk.Entry(self.top)
        self.entry_email.grid(row=0, column=1, padx=5, pady=5)

        self.label_password = tk.Label(self.top, text="Password:")
        self.label_password.grid(row=1, column=0, padx=5, pady=5)
        self.entry_password = tk.Entry(self.top, show="*")
        self.entry_password.grid(row=1, column=1, padx=5, pady=5)

        self.login_button = tk.Button(self.top, text="Login", command=self.login)
        self.login_button.grid(row=2, columnspan=2, padx=5, pady=5)

        self.callback = callback

    def login(self):
        email = self.entry_email.get()
        password = self.entry_password.get()
        self.callback(email, password)
        self.top.destroy()

class RegisterDialog:
    def __init__(self, parent, callback):
        self.top = tk.Toplevel(parent)
        self.top.title("Register")

        self.label_email = tk.Label(self.top, text="Email:")
        self.label_email.grid(row=0, column=0, padx=5, pady=5)
        self.entry_email = tk.Entry(self.top)
        self.entry_email.grid(row=0, column=1, padx=5, pady=5)

        self.label_password = tk.Label(self.top, text="Password:")
        self.label_password.grid(row=1, column=0, padx=5, pady=5)
        self.entry_password = tk.Entry(self.top, show="*")
        self.entry_password.grid(row=1, column=1, padx=5, pady=5)

        self.register_button = tk.Button(self.top, text="Register", command=self.register)
        self.register_button.grid(row=2, columnspan=2, padx=5, pady=5)

        self.callback = callback

    def register(self):
        email = self.entry_email.get()
        password = self.entry_password.get()
        self.callback(email, password)
        self.top.destroy()

def main():
    root = tk.Tk()
    app = ToDoListApp(root)
    root.mainloop()

if __name__ == "__main__":
    main()