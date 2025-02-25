# cfdce: Coalition Formation with Dynamically Changing Externalities

**cfdce** is a framework designed for **multi-agent coalition formation** that accounts for **dynamically changing externalities**. It is based on research published in:

> **Coalition Formation with Dynamically Changing Externalities**  
> **Authors**: Youcef Sklab, Samir Aknine, Onn Shehory, Abdelkamel Tari  
> *Engineering Applications of Artificial Intelligence, 91, 103577 (2020)*  
> DOI: [10.1016/j.engappai.2020.103577](https://doi.org/10.1016/j.engappai.2020.103577)  

This project provides a computational solution for forming interdependent coalitions where task execution order impacts the feasibility of future coalitions. It introduces a **Multi-lateral Negotiation Protocol (MNP)** to ensure practical coalition formation in the presence of dynamically changing externalities.

## 📌 Key Features

- **Task Interdependencies Handling**: Models real-world scenarios where an agent's coalition choices affect future coalition possibilities.
- **Multi-Agent System Support**: Enables cooperation between self-interested agents with bounded rationality.
- **Multi-Lateral Negotiation Protocol (MNP)**: Implements a distributed negotiation mechanism for coalition formation.
- **Optimized Coalition Planning**: Agents gradually revise proposals and concessions to maximize their individual expected gains.
- **Real-World Applications**: Useful for smart mobility, logistics, resource allocation, and other collaborative decision-making processes.

## 🚀 Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/YoucefSklab/cfdce.git
   cd cfdce
