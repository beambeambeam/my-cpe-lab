# CPE 342 Labs

Each lab is an independent UV project. Generate a new lab from `template/`, then
sync its environment with UV.

## Generate a lab

Install Copier once from `Y3/cpe342`:

```bash
mise install
```

Alternative:

```bash
uv tool install copier
```

Create a destination directory, render the template from inside it, and create
its lockfile and virtual environment:

```bash
mkdir lab02
cd lab02

copier copy --defaults \
  --data lab_number=2 \
  ../template .

uv sync
```

The template generates project metadata, Mise configuration, Python version
pinning, a README, and ignore rules. `uv.lock` and `.venv/` are generated per
lab and should not be copied between labs. `.copier-answers.yml` is generated
for future template updates; do not edit it manually.

Run `copier update --defaults` from inside a lab when the template changes and
the repository is clean.
