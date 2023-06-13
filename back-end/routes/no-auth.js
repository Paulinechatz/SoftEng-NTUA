const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const connect = require('./connect')
const jwt = require('jsonwebtoken')
const bodyParser = require('body-parser')
const bcrypt = require('bcryptjs')
router.use(bodyParser.urlencoded({ extended: false }))
router.use(bodyParser.json())

//--------------InsertUser------------------
router.post('/energy/api/Admin/users', async (req, res) => {
                const db = connect()
                const username = req.body.username
                const password = req.body.password
                const quota = req.body.quota
                const email = req.body.email
                const salt = await bcrypt.genSalt(10)
                const hashPassword = await bcrypt.hash(password, salt)
                const querySt = `INSERT INTO Users (username,password,email,quota,curr_quota) 
                values ('${username}','${hashPassword}','${email}','${quota}','${quota}')`
                db.query(querySt, (err) => {
                    if (err) throw err
                    else res.send("User inserted successfully")
                })
            })

module.exports = router