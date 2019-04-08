# Multi-label Classification using Keras

This folder contains our modification based on the project kindly presented by [Adrian Rosebrock](https://www.pyimagesearch.com/author/adrian/) 

Original project:

https://www.pyimagesearch.com/2018/05/07/multi-label-classification-with-keras/

## Objective

- Pull desired obejct images using Bing Image search API.
- Identify fire / protest signs or a combination of fire and protest signs

## Commands

To fetch the images required:

*# python bing_image_search.py --query "DESIRED OBJECT" --output PATH_TO_SAVE_IMAGES*

To train the model:

*# python train.py --dataset PATH_TO_THE_FOLDER_CONTAINS_TRAINING_SETS --model fashion.model --labelbin mlb.pickle*

To run the test:

*# python classify.py --model your_trained_model.model --labelbin mlb.pickle --image examples/example_01.jpg*

## Results



## Credits

Boston University Department of Computer Science, CS640, Professor Betke

This repo was created by:

Xiankang (Jerry) Wu

Maya Shen https://github.com/starrymonkey

Rongyu Wang https://github.com/WangIsabel