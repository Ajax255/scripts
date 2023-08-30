# Use an official Node.js image based on Alpine Linux
FROM node:14-alpine

# Set the working directory
WORKDIR /app

RUN mkdir data

# Copy the current directory contents into the container at /app
COPY javascript-scripts /app/javascript-scripts

# Run the script when the container launches
CMD ["node", "javascript-scripts/hello-world.js"]