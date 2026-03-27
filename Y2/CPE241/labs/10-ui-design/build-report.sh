#!/bin/zsh

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
OUTPUT_PDF="${ROOT_DIR}/PS10_YourStudentID.pdf"
INPUT_MD="${ROOT_DIR}/ps10_report.md"

pandoc "${INPUT_MD}" \
  --from=gfm \
  --pdf-engine=pdflatex \
  -V geometry:margin=1in \
  -V fontsize=11pt \
  -o "${OUTPUT_PDF}"

echo "Report generated at ${OUTPUT_PDF}"
