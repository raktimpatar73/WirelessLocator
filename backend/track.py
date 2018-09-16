import os
import pandas as pd 
from sklearn.externals import joblib
import dill as pickle
import numpy as np

AP_list = list(pd.read_csv('data/new_wifi.csv').columns)
AP_list =AP_list[:len(AP_list)-3]
AP_list.remove('_id')

def processed(data):
    x_track = pd.DataFrame([data])
    columns = x_track.columns.values.tolist()
    x_track_df = pd.DataFrame()
    for i in AP_list:
        if i in columns:
            x_track_df[i] = x_track[i]
        else:
            x_track_df[i]=pd.Series(100)
    return x_track_df

def track(data):
    data = processed(data)
    print(data)
    rf = 'models/model_rf.pk'
    loaded_model = None
    with open(rf,'rb') as f:
        loaded_model = pickle.load(f)
    predictions = loaded_model.predict(data)
    print(predictions)
    return(str(int(predictions[0])))




    
 
