# 🧩 Laboratorio 1 — Desarrollo Orientado por Objetos (2026-2)

Escuela Colombiana de Ingeniería Julio Garavito

Laboratorio 1 de 6 del curso **Construcción — DOPO**. Se trabaja con BlueJ, el paquete `shapes` y se construye una mini-aplicación inspirada en **Karel the Robot**: `RobotMaze`.

## 🎯 Objetivo

Apropiar un paquete existente (diagrama de clases, documentación y código), crear y extender clases, entender el manejo básico de memoria en OO, explorar el API de Java y practicar XP (*Planning* y *Coding en parejas*) usando **BlueJ**.

## 📦 Contenido del laboratorio

### Parte I — Shapes
Exploración del paquete `shapes` (diagrama de clases, documentación, código fuente):
- Inspección de objetos, atributos y comportamiento de la clase `Triangle`.
- Análisis de código existente (predicción vs. ejecución real).
- Extensión de `Triangle` con nuevos métodos:
  - `area()`
  - `equilateral()`
  - `walk(times: int)`
  - Nuevo constructor `Triangle(color, ancho, alto)`
  - Método propio adicional propuesto por el equipo.

### Parte II — De Python a Java
Evaluación de recursos de apoyo (video y prompts) para la transición Python → Java.

### Parte III — RobotMaze 🤖
Mini-juego de laberinto inspirado en Karel:
- El robot inicia con **10 puntos de vida**, ubicado en la entrada del laberinto.
- Se mueve casilla por casilla en 4 direcciones (`N`, `S`, `E`, `W`).
- Pierde 1 punto de vida al chocar contra una pared o un borde.
- El juego termina al llegar a la salida o al quedarse sin vida.

**Requisitos funcionales:** crear laberinto, agregar paredes, iniciar juego, mover el robot, consultar vida, detectar fin de juego, terminar juego.

**Requisitos de interfaz:** entrada/salida diferenciadas, dirección visible, paredes golpeadas cambian de color, robot distinto sin vida, mensajes de error vía `JOptionPane`.

**Bono:** movimiento automático inteligente (la máquina decide) + función deshacer último movimiento.

Se reutilizan la clase `Robot` (construida sobre `shapes`) y el paquete `shapes` como base gráfica.

## 🛠️ Tecnologías

- Java
- BlueJ
- Paquete `shapes` (recurso de BlueJ)
- `JOptionPane` para mensajes de interfaz

## 📁 Estructura sugerida del repositorio

```
📦 ApellidoA-ApellidoB
 ┣ 📂 shapes/          # Paquete base + extensiones a Triangle
 ┣ 📂 robot/            # Clase Robot
 ┣ 📂 robotmaze/         # Mini-aplicación RobotMaze
 ┣ 📄 lab01.doc          # Respuestas y capturas de pantalla
 ┗ 📄 README.md
```

## 👥 Autores

- *(Tu nombre)*
- *(Nombre de tu compañero)*

---
Curso: Desarrollo Orientado por Objetos — 2026-2 · Escuela Colombiana de Ingeniería Julio Garavito
