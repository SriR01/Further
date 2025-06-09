Further - Self-Improvement App

Overview:
Further is a web app designed to help you set and achieve daily goals using the Power List method. Track your daily, weekly, monthly, and yearly progress toward personal growth.

------------------------------------
Installation Instructions
------------------------------------

Prerequisites:
- Docker installed (https://docs.docker.com/get-docker/)
- Java 17+ installed (if running .jar directly)
- Internet browser (Chrome, Firefox, Edge, etc.)

Option 1: Run Using Docker (Recommended)
1. Build the Docker image (if not already built):
   docker build -t further-app .

2. Run the container:
   docker run -d -p 8080:8080 --name further-app further-app

3. Open your web browser and go to:
   http://localhost:8080

Option 2: Run the Executable JAR
1. Ensure Java 17 or higher is installed.

2. Run the JAR file located at:
   target/further-0.0.1-SNAPSHOT.jar

   Command:
   java -jar target/further-0.0.1-SNAPSHOT.jar

3. Open your browser and navigate to:
   http://localhost:8080

------------------------------------
Usage
------------------------------------
- Register or log in.
- Create your daily Power List of 5 goals.
- Mark goals complete as you go.
- Track your progress on the dashboard.
- Use insights to improve your routine.

------------------------------------
Troubleshooting
------------------------------------
- If port 8080 is busy, stop other services or change port mapping.
- To view Docker logs, run:
  docker logs further-app

------------------------------------
Contact
------------------------------------
For help or questions, contact: [Your Email Here]

Thank you for using Further!
Empower your self-improvement journey.
