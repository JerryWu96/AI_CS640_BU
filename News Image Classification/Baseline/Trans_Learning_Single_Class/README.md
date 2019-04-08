# Transfer Learning on Single Class (Protest)

This folder contains our baseline appraoch for applying transfer learning techniques on protesting event classification. 

**Model adapted from:** https://pytorch.org/tutorials/beginner/transfer_learning_tutorial.html

**Original Author:** Sasank Chilamkurthy <https://chsasank.github.io>

**License:** BSD

**Tools Used:** PyTorch, ResNet-18

### Transfer Learning Models:

Two major transfer learning scenarios are examined here:

- **Fine-tuning the Convolutional Neural Network** by replacing random initialization with a pretrained network, carry out training using backpropagation and cost function minimization using Stochastic Gradient Descent.

- **Using CNN as a fixed feature extractor** by freezing the weights for all the network except ones of the final fully connected layer. The weights in the last layer is replaced with new random weights and only this layer is trained.

## Structure

All files are in Jupyter Notebook format that contains both code and result.

- Image and Dataset Pre-processing in: pre-processing.ipynb
- Transfer Learning: transfer_learning_baseline.ipynb

## Run the Code

Launch Jupyter Notebook from directory where these files are contained, run all or sections of code in Jupyter Notebook environment. 

To run the code in GPU environment, machine needs to have Cuda installed. 
