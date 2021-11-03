

## Compila cliente y servidor

compila:
	javac servidor/*.java
	cp servidor/Juego.class cliente
	javac -cp cliente/ cliente/*.java
