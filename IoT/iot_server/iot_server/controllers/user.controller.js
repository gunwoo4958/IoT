const mongoose = require('mongoose');
const passport = require('passport');
const _ = require('lodash');

const User = mongoose.model('User');
const Data = mongoose.model('Data');

module.exports.register = (req, res, next) => {
    var user = new User();
    user.fullName = req.body.fullName;
    user.email = req.body.email;
    user.password = req.body.password;
    user.save((err, doc) => {
        console.log("save success");
        if (!err)
            res.send(doc);
        else {
            if (err.code == 11000)
                res.status(422).send(['Duplicate email adrress found.']);
            else
                return next(err);
        }

    });
}
/*
module.exports.authenticate = (req, res, next) => {
    // call for passport authentication
    passport.authenticate('local', (err, user, info) => {       
        // error from passport middleware
        if (err) return res.status(400).json(err);
        // registered user
        else if (user) return res.status(200).json({ "token": user.generateJwt() });
        // unknown user or wrong password
        else return res.status(404).json(info);
    })(req, res);
}
*/

module.exports.Login = (req,res,next) =>{
    User.find((err,user)=>{
        res.json({user:user});
    });
}

module.exports.savegps = (req, res, next) => {

    var data = new Data();
     data.x = req.body.x;
    data.y = req.body.y;

    data.save((err, doc) => {
        console.log("gps data save success");
        if (!err)
            res.send(doc);
        else {
               return next(err);
        }

    })};

module.exports.getdata = (req,res,next) =>{

Data.find((err,docs) => {
   if(!err) {res.send(docs); }
   else { console.log("error")}
});
};

