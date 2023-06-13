const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const connect = require('./connect')
const jwt = require('jsonwebtoken')
const bodyParser = require('body-parser')
const bcrypt = require('bcryptjs')
router.use(bodyParser.urlencoded({ extended: false }))
router.use(bodyParser.json())


//--------------Login--------------------
router.post('/energy/api/Login', async (req, res) => {
    const db = connect()
    const username = req.body.username
    const password = req.body.password

    const querySt = `SELECT username,password FROM Users WHERE username = ?`;
    db.query(querySt, [username], async (err, rows) => {
        if (err) throw (err)
        if (!rows.length) res.status(400).send("Invalid Username")
        else {
            const validPass = await bcrypt.compare(password, rows[0].password)
            if (!validPass) res.status(400).send("Invalid Password")
            else
                jwt.sign({ username }, 'test', (err, token) => {
                    if (err) throw err
                    res.json({ "token": token })
                    test=token
                })
        }
    })
})

//--------------Logout-------------------
router.post('/energy/api/Logout', verifyToken, (req, res) => {
    const db = connect()
    jwt.verify(req.token, 'test', (err, authData) => {
        if (err) res.status(401).send('Not Logged in yet')
        else {
            res.sendStatus(200)
        }
    })
})

function verifyToken(req, res, next) {
    const authToken = req.headers['x-observatory-auth']
    if (typeof authToken !== 'undefined') {
        req.token = authToken
        next()
    }
    else res.status(401).send('Not authorized')
}
module.exports = router
