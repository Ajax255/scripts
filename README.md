# scripts

## Available Scripts

### Json-Scrubber

python

```bash
docker run -v /path/to/your/data:/app/data -e INPUT_FILE=input.json -e OUTPUT_FILE=output.json -e KEEP_IF_MATCHED=keep_this,another_name -it quick-python-scripts python /app/python-scripts/json-scrubber.py
```

javascript

```bash
docker run -v /path/to/your/data:/app/data -e INPUT_FILE=input.json -e OUTPUT_FILE=output.json -e KEEP_IF_MATCHED=keep_this,another_name -it quick-javascript-scripts node /app/javascript-scripts/json-scrubber.py
```

java

```bash
docker run -v //path/to/your/data:/app/data -e INPUT_FILE=input.json -e OUTPUT_FILE=output.json -e KEEP_IF_MATCHED=keep_this,another_name -it quick-java-scripts java -cp /app/java-scripts/out JsonScrubber
```

## Docker

### Python

```bash
docker build . -f docker/dockerfile.python -t quick-python-scripts:latest
```

### Javascript

```bash
docker build . -f docker/dockerfile.javascript -t quick-javascript-scripts:latest
```

### Java

```bash
docker build . -f docker/dockerfile.java_ -t quick-java-scripts:latest
```
