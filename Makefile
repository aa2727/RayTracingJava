#!/bin/bash

BIN 	= 	Luncher\

COMPILE	=	javac -d bin\

FILE	=	src/*.java\
			src/fenetre/*.java\
			src/fenetre/Listener/*.java\
			src/object3D/*.java\
			src/ray/*.java\
			src/Shade/light/*.java\
			src/Shade/materials/*.java\
			src/Scene/camera/*.java\
			src/Parser/*.java\
			src/Scene/*.java\

all:
	@echo "All..."
	@make clear
	@make init
	@make docu
	@make compile
	@make run
	@echo "All ended"

compile:
	@echo "Compile..."
	@$(COMPILE) $(FILE)
	@echo "Compile ended"

compile_fenetre:
	@echo "Compile Fenetre..."
	@$(COMPILE) src/fenetre/*.java src/fenetre/Listener/*.java src/Scene/*.java
	@echo "Compile Fenetre ended"

compile_object3D:
	@echo "Compile object3D..."
	@$(COMPILE) src/object3D/*.java src/ray/*.java src/Shade/materials/*.java
	@echo "Compile object3D ended"

compile_ray:
	@echo "Compile ray..."
	@$(COMPILE) src/ray/*.java
	@echo "Compile ray ended"

compile_shade:
	@echo "Compile shade..."
	@$(COMPILE) src/Shade/light/*.java src/Shade/materials/*.java src/ray/*.java src/Shade/camera/*.java src/object3D/*.java
	@echo "Compile shade ended"

test_Ray:
	@(javac -d bin src/Ray/*.java)
compile_parser:
	@echo "Compile parser..."
	@$(COMPILE) src/Parser/*.java src/Shade/light/*.java src/Shade/camera/*.java src/ray/*.java src/Shade/materials/*.java src/object3D/*.java src/Scene/*.java
	@echo "Compile parser ended"
compile_testShade:
	@echo "Compile testShade..."
	@$(COMPILE) src/Shade/light/*.java src/Shade/materials/*.java src/ray/*.java src/Shade/camera/*.java src/object3D/*.java src/test/*.java
	@echo "Compile testShade ended"

compile_testTriangle:
	@echo "Compile testTriangle..."
	@$(COMPILE) src/Shade/light/*.java src/Shade/materials/*.java src/ray/*.java src/Shade/camera/*.java src/object3D/*.java src/test/*.java
	@echo "Compile testTriangle ended"

run_shade:
	@echo "Run Shade"
	@java -cp ./bin/ Shade.camera.Camera

run_testCameraUp:
	@echo "Run TestShade"
	@java -cp ./bin/ test.TestViewCameraUp

run_testCameraFront:
	@echo "Run TestShade"
	@java -cp ./bin/ test.TestViewCameraFront

run_testCameraRight:
	@echo "Run TestShade"
	@java -cp ./bin/ test.TestViewCameraRight

run_testCameraDiagonal:
	@echo "Run TestShade"
	@java -cp ./bin/ test.TestViewCameraDiagonal

run_testTriangle:
	@echo "Run TestTriangle"
	@java -cp ./bin/ test.TestTriangle

run:
	@(java -cp bin Launcher)

runFenetre:
	@(java -cp bin fenetre.Main)
	@echo "Run..."
	@java -cp bin $(BIN)
	@echo "Run ended"

clear:
	@echo "Clear..."
	@rm -rf bin/*
	@echo "Clear ended"

init:
	@echo "Init..."
	@mkdir -p bin
	@mkdir -p lib
	@mkdir -p doc
	@mkdir -p src/
	@mkdir -p src/fenetre/
	@mkdir -p src/object3D/
	@mkdir -p src/ray/
	@mkdir -p src/Shade/
	@mkdir -p src/Shade/light/
	@mkdir -p src/Shade/materials/
	@echo "Init ended"

docu:
	@echo "Docu..."
	@rm -rf doc/*
	@javadoc -d doc -sourcepath $(FILE)
	@echo "Docu ended"
