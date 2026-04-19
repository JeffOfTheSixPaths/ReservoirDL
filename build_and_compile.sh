#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 || $# -gt 2 ]]; then
    echo "Usage: $0 <input.rc> [output.py]" >&2
    exit 1
fi

INPUT_FILE="$1"
OUTPUT_FILE="${2:-generated_lsm.py}"

if [[ ! -f "$INPUT_FILE" ]]; then
    echo "Input file not found: $INPUT_FILE" >&2
    exit 1
fi

ANTLR_CMD="$(command -v antlr4)"
CP=$(grep '^CLASSPATH=' "$ANTLR_CMD" | cut -d= -f2-):.

antlr4 -Dlanguage=Java -visitor RDL.g4
javac -cp "$CP" *.java
java -cp "$CP" CompileRDL "$INPUT_FILE" "$OUTPUT_FILE"

echo "Generated $OUTPUT_FILE from $INPUT_FILE"
