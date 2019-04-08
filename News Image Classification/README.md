# New Image Classification

This folder contains our second project for BU CS640 Artificial Intelligence. 

## Problem definition

Nowadays journalists are not the first ones to record an emergency event but any civilians with phones, and they send to social media almost immediately when such event takes place in their proximity. Such examples can be found as Twitter posts during the Paris 2015 Bataclan event, 2013 Boston Marathon Bombing etc.; the use of hashtags make these instances searchable.

Here, emergency event is defined as a sudden, urgent, usually unexpected incident or occurrence that requires an immediate reaction or assistance for emergency situations faced by the recipients of public assistance in order to bring the situation under control and to restore normality. 

For this project, we would like to build a system that looks for such event in a pool of images originated from social media feeds. The system consists of two parts: first a deep neural network trained for object detection, secondly, an expert system that yields an emergency level score based on the object or an event indicated by object(s) detected from images. 

To reach our goal, we decided to try three different approaches for building the deep neural network, two using transfer learning techniques and one from reproducing the work from the original paper where Topic 2 is based on. We aim at comparing the approaches, as well as gaining a deeper level of understanding on Convolutional Neural Network structure that’s heavily used in solving Computer Vision problems. 

## Run the code

We used **Python** and related machine learning libraries, for example. **PyTorch**.

Please check out each folder for details.

## Credits

Boston University Department of Computer Science, CS640, Professor Betke

This repo was created by:

Xiankang (Jerry) Wu

Maya Shen https://github.com/starrymonkey

Rongyu Wang https://github.com/WangIsabel
