from sklearn.ensemble.forest import RandomForestRegressor
from sklearn.tree import DecisionTreeRegressor
from sklearn.externals import joblib
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.svm import SVC, SVR
import numpy as  np
import pandas as pd
import dill as pickle 

train_data_path = 'data/new_wifi.csv'
pd.set_option('display.precision',3)
filename = 'models/model_rf.pk'
filename2 = 'models/model_dt.pk'

def load(train_file_name):
    if train_file_name == None:
        print('File does not exist')
        exit()

    data_frame = pd.read_csv(train_file_name)
    data_frame.drop('_id' ,axis=1, inplace=True)
    x_data = data_frame.T[:data_frame.shape[1]-3].T
    y_data = data_frame['cellid_']
    
    # x_data = normalizeX(x_data)
    # y_data = normalizeY(y_data)
    
    x_train, x_test, y_train, y_test = train_test_split(x_data, y_data, test_size=0.2, random_state=0)
    return x_train,x_test,y_train,y_test, x_data, y_data


# def normalizeX(arr):
#     res = np.copy(arr).astype(np.float)
#     for i in range(np.shape(res)[0]):
#         for j in range(np.shape(res)[1]):
#             if res[i][j] == 100:
#                 res[i][j] = 0
#             else:
#                 res[i][j] = -0.01 * res[i][j]
#     return res

# def normalizeY(arr):
#     arr=arr/100
#     return arr

if __name__ == '__main__':

    train_x, test_x, train_y, test_y, x_data, y_data = load(train_data_path)

    rf_model = RandomForestRegressor() 
    rf_model.fit(x_data, y_data)
    with open(filename, 'wb') as file:
	    pickle.dump(rf_model, file)
    rf_train_score = rf_model.score(x_data, y_data)
    rf_test_score = rf_model.score(test_x, test_y)
    print("RF train score:",rf_train_score)
    print("RF test score:",rf_test_score)

    dt_model =  DecisionTreeRegressor() 
    dt_model.fit(x_data, y_data)
    with open(filename2, 'wb') as file:
	    pickle.dump(dt_model, file)
    dt_train_score = dt_model.score(x_data, y_data)
    dt_test_score = dt_model.score(test_x, test_y)
    print("DT train score:",dt_train_score)
    print("DT test score:",dt_test_score)