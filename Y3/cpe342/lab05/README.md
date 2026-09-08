# CPE 342 Lab 05

Neural-network classification with scikit-learn and Keras 3. The project uses
TensorFlow as its Keras backend and Python 3.12.

## Setup

```bash
uv sync
```

Run Python commands with the project environment:

```bash
uv run python
```

## Lab files

- `input/bank-data.csv` is the 4,521-row UCI Bank Marketing sample used for
  binary classification.
- `keras/1_Intro_to_NN.ipynb` compares a scikit-learn MLP with a Keras binary
  classifier after categorical encoding and feature scaling.
- `keras/2_Keras_Tutorial.ipynb` demonstrates a fully connected MNIST model.
- `keras/3_DNN_Homework.ipynb` completes the DNN exercise, including data
  inspection, validation curves, test evaluation, confusion-matrix analysis,
  and tuning experiments.
- `keras/outputs/` contains generated metric tables and figures.

Execute a notebook from this directory with:

```bash
uv run jupyter nbconvert --to notebook --execute --inplace \
  --ExecutePreprocessor.timeout=300 keras/3_DNN_Homework.ipynb
```

The notebooks set `KERAS_BACKEND=tensorflow` before importing Keras. Keras 3
requires a backend framework; TensorFlow 2.16 and later install/use Keras 3.
See the official [Keras installation guide](https://keras.io/getting_started/)
and [Sequential API](https://keras.io/api/models/sequential/).

## Dataset research

The data comes from the [UCI Bank Marketing dataset](https://archive.ics.uci.edu/dataset/222/bank),
which models whether a client subscribed to a Portuguese bank's term deposit
after a direct-marketing campaign. The local file is the smaller `bank.csv`
variant: 17 columns, 4,521 observations, and no missing values. The positive
class (`y=yes`) is the minority class, so the notebooks report precision,
recall, and F1 in addition to accuracy.
