"""
A very simple expert system demo
Input: output from deep neural network, a .csv file containing predicted labels for all categories (ex: protest, violence, fire etc.)
Output: A emergency level from Low, Medium, High
Method: Here, the emergency level is solely depended on violence score (a column in .csv file)
Possible Improvement given more time:
    1. Involving more input measure for generating emergency level, such as presence of fire, number of people involved etc.
    2. Have more detailed categorization for emergency level

To run:
 - supply input image to pred.py ()
 - pred.py() outputs a result file result.csv
 - run this file with: python simple_expert_sys.py "path_to_result.csv"
"""
import pandas as pd
import sys

# read in input .csv
path = sys.argv[1]
df = pd.read_csv(path+'/result.csv')

# locate violence column entry
var = df.loc[df.violence]

# generate simple responses
if (var < 0) or (var > 1) or (var is None):
    print("Invalid Violence Ratio")
elif var>=0 or var<= 0.3:
    print("Low Emergency Level")
elif var>0.3 or var < 0.7:
    print("Medium Emergency Level")
else:
    print("High Emergency Level")
