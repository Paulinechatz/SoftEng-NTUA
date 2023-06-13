const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const conn = require('./connect')

router.get('/energy/api/HealthCheck', (req, res) => {
    
const db = conn()
db.connect((err) => {
    if (err) {
         res.send(err)
    }
    else {
        var result = {status : "OK"}
        res.json(result)
    }
})
})
module.exports = router
