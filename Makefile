all: run

clean:
	rm -f out/Bluck.jar out/BoyerMoore.jar

out/Bluck.jar: out/parcs.jar src/Bluck.java src/Input.java src/Result.java
	@javac -cp out/parcs.jar src/Bluck.java src/Input.java src/Result.java
	@jar cf out/Bluck.jar -C src Bluck.class -C src Input.class -C src Result.class
	@rm -f src/Bluck.class src/Input.class src/Result.class

out/KMP.jar: out/parcs.jar src/KMP.java src/Input.java src/Result.java
	@javac -cp out/parcs.jar src/KMP.java src/Input.java src/Result.java
	@jar cf out/KMP.jar -C src KMP.class -C src Input.class -C src Result.class
	@rm -f src/KMP.class src/Input.class src/Result.class

build: out/Bluck.jar out/BoyerMoore.jar

run: out/Bluck.jar out/KMP.jar
	@cd out && java -cp 'parcs.jar:Bluck.jar' Bluck

