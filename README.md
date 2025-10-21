[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion
CS 245 Project 02

Celestial object simulation demonstrating understanding of List data structures and utilizing lists and polymorphism to code an API.

**Author:** Geneva Tovar
**Course:** CS 245 Fall 2025 

## Testing: 
- I wrote a lot of print methods throughout the program, some were deleted and some were not when testing. I mainly ran into errors when working with Java Swing GUI because it's a bit new to me and I needed a refresher. I have provided citations at the bottom that I used to help refresh my memory on writing specific code. I also found it difficult to determine how I should write out equations. For example, I had a difficult time figuring out how I should implement an equation of motion, but using sources online I was able to figure it out. I tested each List implementation by changing the configuration and I could have made this possibly easier instead of manually switching, but I ran out of time. Overall, I feel that I excelled in utilizing object oriented programming and polymorphism throughout the code and I feel that I have a better understanding of list data structures now.

## How to run:
- Use default configuration: 
javac MassiveMotion.java
java MassiveMotion

- Use custom config file:
javac MassiveMotion.java
java MassiveMotion myConfig.txt

## Configuration File
Edit "MassiveMotion.txt" to change the following:
- Animation speed
- List type being used (arraylist, single, double, dummyhead)
- Star and random comets properties

## Concepts Demonstrated
- Java Swing GUI (JFrame, JPanel, Graphics)
- Painting with paintComponent()
- Event animation using javax.swing.Timer
- File I/O and property loading with java.util.properties
- Object Oriented Programming with nested classes and encapsulation and an interface

## Demo Link
https://youtu.be/rwHIZ77bRks

## Citations
- Property file reading: https://kodejava.org/how-do-i-read-a-configuration-file-using-java-util-properties/
- Switch expressions: https://nipafx.dev/java-switch/
- ArrayList implementation: https://usfca.instructure.com/courses/1629342/files/74046395?module_item_id=18708732
- LinkedList implementation: https://usfca.instructure.com/courses/1629342/files/74046396?module_item_id=18708737
- DoublyLinkedList implementation: https://usfca.instructure.com/courses/1629342/files/74046396?module_item_id=18708737 and https://www.youtube.com/watch?v=cYAZZt8GyUs
- DummyHeadLinkedList implementation: https://usfca.instructure.com/courses/1629342/files/74046396?module_item_id=18708737 and  https://www.youtube.com/watch?v=-Cjgt5I0YvM&pp=ygUgd3JpdGluZyBhICBkdW1teSBoZWFkIGxpbmtlZGxpc3Q%3D and https://www.youtube.com/watch?v=aloIxnZ4EvY
- JPanel: https://www.youtube.com/watch?v=4YhrmAGpVtI
- JavaTimer and ActionListener: https://docs.oracle.com/javase/tutorial/uiswing/misc/timer.html
- Nested and Inner Classes: https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
- Java 2D Graphics Basics: https://docs.oracle.com/javase/tutorial/2d/basic2d/