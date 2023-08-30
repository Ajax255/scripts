import os
import json

# Read data from a file
input_filename =  "data/" + os.environ.get("INPUT_FILE", "data/input.json")
output_filename = "data/" + os.environ.get("OUTPUT_FILE", "data/output.json")
keepIfMatched = os.environ.get("KEEP_IF_MATCHED", "").split(",")

# Read data from the input file
with open(input_filename, "r") as input_file:
    data = json.load(input_file)

# Process the data
data["_source"]["metadata"] = [
    item for item in data["_source"]["metadata"]
    if "name" not in item or item["name"] in keepIfMatched
]

# Export the modified data to an output file
with open(output_filename, "w") as output_file:
    json.dump(data, output_file, indent=4)

print("Modified data exported to", output_filename)