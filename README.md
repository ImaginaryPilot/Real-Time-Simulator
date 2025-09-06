# RTS Battle Simulator

A **Lord of the Rings-themed battle simulator** where users create maps, place armies, and simulate epic battles.  

Maps are represented as **undirected graphs**:
- **Nodes** = locations  
- **Edges** = routes  

Armies move across the map, encounter events, and battle enemy forces.

---

## Features

- **Interactive Map Editor**  
  - Add/remove nodes and edges  
  - Select and move nodes  
  - Highlight selected nodes/edges  
  - Pan and zoom the map  

- **Simulation**  
  - Armies with units belonging to different factions (`Men`, `Elves`, `Dwarves`, `Mordor`, `Isengard`)  
  - Randomized movement and battles  
  - Events that affect armies (e.g., reinforcements, natural disasters, hidden weaponry)  
  - Step-by-step simulation control  

- **UI**  
  - Java Swing interface following **MVC** design  
  - Options panel for nodes/edges: view details, rename, add/remove armies or events  

- **Persistence**  
  - Save simulation state to **JSON** for later review  

- **Extras**  
  - Customizable army units and events  
  - Undo/Redo functionality  

---

## Technologies

- **Java 17**  
- **Java Swing** (GUI)  
- **MVC pattern**  
- File I/O (JSON)  

---

## Screenshot

![Example](screenshots/example.png)

---

## Usage

1. Clone the repository  
   ```bash
   git clone https://github.com/yourusername/rts-battle-simulator.git```
2. Build and run with Maven:
```
mvn clean compile exec:java
```
3. Use the GUI to create your map, add armies, and start simulations.

## License
MIT License - see LICENSE file
