# Competitie Programming Coach
A Java desktop application that helps Codeforces users track their progress, analyze their performance, and explore the Codeforces problemset.
## The Problem
Competitive programmers often solve hundreds or even thousands of problems, making it difficult to keep track of their progress and identify areas where they need improvement. Codeforces provides submission history and problem statistics, but users may have to navigate different pages and analyze this information themselves.
I built this project to bring problem exploration, performance tracking, and statistical analysis together in one application, helping competitive programmers better understand their progress.
## How It Works
The application connects to the Codeforces API to retrieve a user's submission history and the available problemset. It processes the returned JSON data and stores it locally for future use.
Users can:
- Analyze their submission history and view performance statistics.
- Track problems solved by difficulty and topic.
- Visualize their progress and activity over time.
- Browse the Codeforces problemset using difficulty and topic filters.
- View problem details, including rating, tags, contest ID, and solved status.
- Open selected problems directly on Codeforces.
- The application provides a graphical interface built with JavaFX.
![System diagram](CPC%20Diagram.jpg)
## Tech Stack
Language: Java
Libraries:
- JavaFX - graphical user interface.
- Gson - JSON parsing and serialization.
- XChart - data visualization.
External Tools:
- Codeforces API - retrieving submission history and problemset data.
- Git and GitHub - version control.
- Local Storage: JSON files.
## Setup
1. Go to the Releases page.
2. Download the installer for your operating system.
3. Run the installer and launch the application.
4. No additional dependencies or configuration are required.
## Usage
1. Launch the application.
2. Enter your Codeforces handle.
3. Allow the application to retrieve your submission history.
4. Explore your performance statistics and visualizations.
5. Browse the Codeforces problemset using difficulty and topic filters.
6. Select a problem to view its details and open it on Codeforces.
## Future Plans
Features planned beyond the initial MVP:
- Personalized Recommendations: Suggest unsolved problems based on the user's performance and weaker topics.
- Additional Platforms: Support other competitive programming platforms, such as AtCoder and CodeChef.
- Improved Visualizations: Introduce additional charts and performance metrics.
- Advanced Progress Tracking: Provide more detailed long-term progress dashboard.

