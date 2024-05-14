from tkinter import *
mainwindow = Tk()
mainwindow.withdraw()

def stop(w):
    mainwindow.destroy()
    w.destroy()


def page_connection():
    windowCo = Tk()
    windowCo.title('caca')
    windowCo.geometry("800x500+100+200")
    windowCo.configure(background="red")
    labelWelcome= Label(windowCo, text="Site vous permettant d'avoir une Todolist gratuite et pratique !", bg="red",font="100").pack()
    labelCo= Label(windowCo, text="veuillez vous connecter ici : ").pack()
    labelNom = Label(windowCo, text="Nom",anchor="center", font="50").place(x=200, y=120)
    champNom = Entry(windowCo).place(x=200 , y=150)
    buttonExit = Button(windowCo, text="Exit", command=lambda: stop(windowCo)).place(x=700,y=50)

    


page_connection()
mainwindow.mainloop()
