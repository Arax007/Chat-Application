# Client-Server Chat Application

A Java-based Client-Server Chat Application with a GUI, built using **Swing** for the interface and **Sockets** for communication. This project demonstrates real-time message exchange between a client and a server, supporting **bidirectional communication**.

## ✨ Features
- 📡 **Real-time communication** between client and server.
- 🎨 **User-friendly GUI** built with Java Swing.
- 🔄 **Auto-flushing messages** for reliable delivery.
- 📜 **Multi-threaded reading & writing** to handle asynchronous messaging.
- ❌ **Graceful disconnection handling** when either side terminates the chat.

## 🚀 Technologies Used
- **Java** (Core + Networking)
- **Swing** (for GUI)
- **Sockets** (for Client-Server Communication)

## 📂 Project Structure
```
├── Client.java  # Client-side GUI and message handling
├── Server.java  # Server-side GUI and message handling
└── README.md    # Project documentation
```

## 🛠️ Setup & Usage
### 1️⃣ Clone the repository
```sh
git clone https://github.com/Arax007/Client-Server-Chat.git
cd Client-Server-Chat
```

### 2️⃣ Run the Server
```sh
javac Server.java
java Server
```

### 3️⃣ Run the Client
```sh
javac Client.java
java Client
```

> **Note:** Start the server **before** launching the client.

## 🎯 How It Works
- The **server** listens for incoming connections on a specified port.
- The **client** connects to the server and can send messages using the text input field.
- Messages are **instantly** displayed on both client and server interfaces.
- Type `Abort` in the input field to **close the connection**.

## 📸 Screenshots
🚀 Coming soon!

## 📌 About Me
Hey there! I'm **Aryan Kumar**, a passionate Java Developer who enjoys working on real-world software challenges. I love building projects and learning new technologies. Feel free to check out my GitHub and connect with me!

📧 Contact me: aryanb2039@gmail.com | [LinkedIn](#) | [GitHub](https://github.com/Arax007)

## 🏆 Contributions
Feel free to **fork** this repo, submit **issues**, or **pull requests** to improve this project!


