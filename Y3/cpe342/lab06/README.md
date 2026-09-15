# CPE 342 Lab 06

Convolutional neural networks with Keras 3 and TensorFlow.

## Setup

```bash
uv sync
```

Run a notebook from this directory with:

```bash
uv run jupyter nbconvert --to notebook --execute --inplace \
  --ExecutePreprocessor.timeout=300 cnn-in-class.ipynb
```

The homework notebook needs the subject Cat-versus-Dog archive on its first
run. It downloads the archive to `data/` when no extracted images are found.
Set `CPE342_CATS_DOGS_DIR` to an existing extracted dataset to avoid the
download. The archive and generated outputs are ignored by Git.

```bash
CPE342_CATS_DOGS_DIR=/path/to/Cat_Dog_data \
  uv run jupyter nbconvert --to notebook --execute --inplace \
  --ExecutePreprocessor.timeout=300 cnn-homework.ipynb
```

## Notebooks

- `cnn-in-class.ipynb` loads MNIST, trains a CNN, compares augmentation, inspects
  activation maps, and demonstrates frozen-feature transfer learning.
- `cnn-homework.ipynb` builds a Cat-versus-Dog classifier with MobileNetV2,
  fine-tuning, validation-selected precision thresholding, and error analysis.

Both notebooks validate inputs, use fixed random seeds, keep test data isolated
until evaluation, and save generated figures and metrics under `outputs/`.
`cnn-in-class.ipynb` uses 12,000 training images by default for a practical CPU
run; set `MAX_TRAINING_SAMPLES = None` to use all 60,000. `cnn-homework.ipynb`
uses 2,000 images per class by default; set `MAX_IMAGES_PER_CLASS = None` to use
all readable images.
