import pymongo
import sys
#sys.argv: name, email, password (RSA encrypted)
myclient = pymongo.MongoClient("mongodb+srv://admin:l0NVhm8aF0KqwJKF@cluster0.tedole4.mongodb.net/test")
users = myclient["test"]["users"]
mydict = { 'isAdmin': False, 'name': sys.argv[1], 'email': sys.argv[2], 'username': sys.argv[1], 'password': sys.argv[3], '__v': 0}
x = users.insert_one(mydict)
