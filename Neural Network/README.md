# Neural Network

This folder contains my first programming project during my AI course. It utilizes back propagation algorithm to train the neural network, and is capable of  classifying nodes with different colors and recognizing 10 digits (0 - 9).

## Objective

We used **python** to implement this project. Please check *neural network* folder. 

There are four objectives that I achieved:

- Understand how neural networks work 
- Implement a simple neural network 
- Apply backpropagation algorithm to train a neural network 
- Understand the role of different parameters of a neural network, such as epoch and learning rate.

## Run the code

We used jupyter notebook to build my project. It is iteractive and can illustrate the core process of building a neural net from scratch.

To install jupyter notebook: 

https://jupyter.org

## Algorithm

The key idea behind training is to use training data to compare the output of the current neural network with its expected output in the data, and adjust the weights within the neural network accordingly. 

There are many approaches to do this. For this project, we chose forward propagation combined with backward propagation.

Back propagation wiki: 

https://en.wikipedia.org/wiki/Backpropagation

Every time we use the test set (in the DATA folder) to train our neural network, we count a forward propagation and a backward propagation as an **epoch**. We selected epoch = 1,000.

## Results

Please checkout the *Result* folder for details.



## Collaborator

Rongyu Wang @WangIsabel