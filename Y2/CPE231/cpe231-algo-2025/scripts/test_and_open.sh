#!/bin/bash

# Run tests and open HTML report
echo "Running tests..."
./gradlew test

echo "Opening test report..."
open app/build/reports/tests/test/index.html

echo "Done! Test report should be open in your browser."
