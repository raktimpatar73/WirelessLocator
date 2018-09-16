from flask import Flask, request, render_template, jsonify
# from flask_pymongo import PyMongo
import json
import io
import os 
from track import track

try:
   to_unicode = unicode
except NameError:
   to_unicode = str

# MONGO_URL = os.environ.get('MONGO_URL')
app = Flask(__name__)

# app.config['MONGO_DBNAME'] = 'wifi'
# app.config['MONGO_URI'] = MONGO_URL
# mongo = PyMongo(app)

@app.route("/")
def home():
    return render_template("index.html")

# @app.route("/wifidata", methods=['GET'])
# def get_data():
#     return to_unicode([x for x in mongo.db.wifidata.find()])

# def delete(reading_id):
#     mongo.db.wifidata.find_one_or_404({"_id": reading_id})
#     mongo.db.wifidata.remove({"_id": reading_id})
#     return '', 204

# @app.route("/wifidata", methods=['POST'])
# def post_data():
#     wifidata = mongo.db.wifidata
#     data = request.get_json()
#     print(data)
#     wifidata.insert(data)
#     return "OK"

@app.route("/tracking", methods=['GET','POST'])
def post_track_data():
    tracking_data = request.get_json()
    location = track(tracking_data)
    return location

if __name__ =="__main__":
    app.run(debug=True, host='0.0.0.0')
