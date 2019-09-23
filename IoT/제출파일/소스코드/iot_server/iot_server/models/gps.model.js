
const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

var dataSchema = new mongoose.Schema({
    x: {type: String},
    y: {type: String},
 });
mongoose.model('Data', dataSchema);