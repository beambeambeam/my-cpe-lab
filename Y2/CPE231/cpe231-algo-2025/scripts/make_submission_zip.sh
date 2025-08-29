#!/usr/bin/env bash
set -euo pipefail

# make_submission_zip.sh
# Create a zip of the project named <YYYY-MM-DD>-submission.zip by default.
# Usage: ./scripts/make_submission_zip.sh [--name NAME] [--outdir DIR] [--date FORMAT] [--dry-run]

PROGNAME="$(basename "$0")"

print_usage() {
  cat <<EOF
Usage: $PROGNAME [options]

Options:
  --name NAME       Override default filename (without extension). Default: DATE-submission
  --outdir DIR      Directory to write the zip to. Default: current directory
  --date FORMAT     Date format passed to date(1). Default: %F (YYYY-MM-DD)
  --dry-run         Print the zip command instead of running it
  -h, --help        Show this help

Examples:
  $PROGNAME                # creates 2025-08-29-submission.zip in cwd
  $PROGNAME --outdir ../   # write archive to parent directory
  $PROGNAME --name final   # creates final.zip
  $PROGNAME --dry-run      # show what would run
EOF
}

# Default values
DATE_FORMAT="%F"
OUTDIR="."
DRY_RUN=0
CUSTOM_NAME=""

while [[ ${#} -gt 0 ]]; do
  case "$1" in
    --name)
      CUSTOM_NAME="$2"; shift 2;;
    --outdir)
      OUTDIR="$2"; shift 2;;
    --date)
      DATE_FORMAT="$2"; shift 2;;
    --dry-run)
      DRY_RUN=1; shift;;
    -h|--help)
      print_usage; exit 0;;
    --)
      shift; break;;
    *)
      echo "Unknown option: $1" >&2; print_usage; exit 2;;
  esac
done

# Verify we're in a project root (best-effort)
if [[ ! -f "gradlew" && ! -f "settings.gradle" && ! -f "build.gradle" ]]; then
  echo "Warning: this script doesn't detect a Gradle project in the current directory." >&2
  echo "Make sure you run it from the project root where files like gradlew or settings.gradle live." >&2
fi

DATE_STR="$(date +"$DATE_FORMAT")"
if [[ -n "$CUSTOM_NAME" ]]; then
  BASENAME="$CUSTOM_NAME"
else
  BASENAME="$DATE_STR-submission"
fi

OUTDIR_ABS="$OUTDIR"
mkdir -p "$OUTDIR_ABS"
TARGET="$OUTDIR_ABS/${BASENAME}.zip"

# Exclude common build/artifact folders and VCS
EXCLUDES=(
  "*.class"
  "build/*"
  "app/build/*"
  ".gradle/*"
  "gradle/*"
  ".git/*"
  "node_modules/*"
  "venv/*"
  "__pycache__/*"
  "*.DS_Store"
  "*.iml"
)

# Build the -x args for zip
ZIP_EXCLUDE_ARGS=()
for pat in "${EXCLUDES[@]}"; do
  ZIP_EXCLUDE_ARGS+=("-x" "$pat")
done

# Use zip to create a portable archive. -r recurse, -q quiet, -X eXclude extra attributes
ZIP_CMD=(zip -r -q -X "$TARGET" . "${ZIP_EXCLUDE_ARGS[@]}")

if [[ $DRY_RUN -eq 1 ]]; then
  echo "DRY RUN: would run:" >&2
  printf ' %q' "${ZIP_CMD[@]}"; echo; exit 0
fi

echo "Creating submission archive: $TARGET"
"${ZIP_CMD[@]}"

echo "Done."
