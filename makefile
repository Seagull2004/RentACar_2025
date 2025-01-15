# Directory di origine e destinazione
SRC_DIR = src
BIN_DIR = $(SRC_DIR)/bin

# Trova tutti i file sorgente Java
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Obiettivi
all: $(BIN_DIR) $(BIN_DIR)/Dashboard.class

# Creazione della directory bin se non esiste
$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Compilazione dei file Java
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	javac -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<

# Avvio del programma
run: all
	java -cp $(BIN_DIR) Dashboard

# Pulizia dei file compilati
clean:
	rm -rf $(BIN_DIR)/*.class

# Phony targets
.PHONY: all run clean

