# Paper_Reproduction

This folder contains our modification based on the project kindly presented by Donghyeon Won.
Here is the original project:
https://github.com/wondonghyeon/protest-detection-violence-estimation

## Objectives

 - Train CNN model based on UCLA_Protest_Image_dataset on GPU
 - Apply the model to test on our dataset 
 - Evaluate the annotation of testset, predict labels 'violence', 'protest', and other visual attributes

## Commands

Training:
python train.py --data_dir UCLA-protest/ --batch_size 32 --lr 0.002 --print_freq 100 --epochs 100 --cuda

Evaluation:
python pred.py --img_dir path/to/some/image/directory/ --output_csvpath result.csv --model model_best.pth.tar --cuda

The best model is too big for uploading. If you want the training model, please contact Rongyu Wang by email.

## Results

After training on GPU for epoch = 100, we get the average accuracy at 90% and above.
The detailed results is included in our final report. Please refer to "Reproducing Baseline Solution from Paper @Rongyu"

## References

Mainly based on the paper below:

Donghyeon Won, Zachary C. Steinert-Threlkeld, Jungseock Joo. 2017. Protest Activity Detection and Perceived Violence Estimation from Social Media Images. In Proceedings of the 25th ACM International Conference on Multimedia 2017.
