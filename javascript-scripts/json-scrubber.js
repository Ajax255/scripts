const fs = require('fs');
const path = require('path');

const inputFilename = path.join('data', process.env.INPUT_FILE || 'data/input.json');
const outputFilename = path.join('data', process.env.OUTPUT_FILE || 'data/output.json');
const keepIfMatched = process.env.KEEP_IF_MATCHED ? process.env.KEEP_IF_MATCHED.split(',') : [];

// Read data from the input file
const inputData = fs.readFileSync(inputFilename, 'utf8');
const data = JSON.parse(inputData);

// Process the data
data._source.metadata = data._source.metadata.filter(item => !item.name || keepIfMatched.includes(item.name));

// Export the modified data to an output file
fs.writeFileSync(outputFilename, JSON.stringify(data, null, 4), 'utf8');

console.log(`Modified data exported to ${outputFilename}`);
