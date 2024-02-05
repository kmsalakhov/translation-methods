#!/bin/bash

# Проверяем, передан ли аргумент
if [ $# -eq 0 ]; then
    echo "Usage: $0 <input_file>"
    exit 1
fi

# Имя входного файла
input_file="$1"

# Проверяем, существует ли файл
if [ ! -f "$input_file" ]; then
    echo "File not found: $input_file"
    exit 1
fi

# Компилируем и запускаем Java-программу
javac Main.java
java Main "$input_file" "output.dot"

# Проверяем успешность выполнения Java-программы
if [ $? -ne 0 ]; then
    echo "Java program execution failed"
    exit 1
fi

# Генерируем SVG из DOT-файла
dot -Tsvg output.dot > output.svg

# Проверяем успешность выполнения команды dot
if [ $? -ne 0 ]; then
    echo "dot command execution failed"
    exit 1
fi

# Удаляем сгенерированные .class файлы
find . -type f -name "*.class" -exec rm -f {} \;

echo "Conversion successful. SVG file generated: output.svg"
