# CPE 342 Lab 04

Standalone UV project for CPE 342.

## Setup

```bash
uv sync
```

Run Python commands with the project environment:

```bash
uv run python
```

## Lab files

- `5_1_classification_tree-based.ipynb` trains and evaluates a decision tree.
- `5_2_ensemble_models.ipynb` compares a decision tree, random forest, and gradient boosting.
- `inputs/MBA.csv` is the source dataset.
- `outputs/` contains the generated prediction CSV files and tree visualization.

Run a notebook from this directory with:

```bash
uv run --with nbconvert jupyter nbconvert --to notebook --execute --inplace \
  --ExecutePreprocessor.timeout=120 notebook.ipynb
```
