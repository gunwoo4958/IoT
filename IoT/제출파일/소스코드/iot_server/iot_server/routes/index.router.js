const express = require('express');
const router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: false }));

const ctrlUser = require('../controllers/user.controller');
const jwtHelper = require('../config/jwtHelper');

router.post('/register', ctrlUser.register);
router.get('/authenticate', ctrlUser.Login);
router.post('/savegps',ctrlUser.savegps);
router.get('/getdata',ctrlUser.getdata);

module.exports = router;



