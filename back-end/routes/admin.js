const express = require('express')
const router = express.Router()
const mysql = require('mysql')
const connect = require('./connect')
const jwt = require('jsonwebtoken')
const bodyParser = require('body-parser')
const bcrypt = require('bcryptjs')
const fs = require('fs')
router.use(bodyParser.json({limit: '50mb', extended: true}))
router.use(bodyParser.urlencoded({limit: '50mb', extended: true, parameterLimit: 500000}))

//--------------InsertUser------------------
router.post('/energy/api/Admin/users', verifyToken, (req, res) => {
    jwt.verify(req.token, 'test', async (err, authData) => {
        if (err) res.status(401).send('Not authorized')
        else {
            const uname = authData.username
            if (uname !== 'admin') res.status(401).send('Not authorized')
            else {
                const db = connect()
                const username = req.body.username
                const password = req.body.password
                const quota = parseInt(req.body.requestsPerDayQuota, 10);
                const email = req.body.email
                const salt = await bcrypt.genSalt(10)
                const hashPassword = await bcrypt.hash(password, salt)
                const querySt = `INSERT INTO Users (username,password,email,quota,curr_quota) 
                values ('${username}','${hashPassword}','${email}','${quota}','${quota}')`
                db.query(querySt, (err) => {
                    if (err) throw err
                    else res.send({ username: username, email: email, requestsPerDayQuota: quota })
                })
            }
        }
    })
})

//---------------UserInfo--------------------
router.get('/energy/api/Admin/users/:username', verifyToken, (req, res) => {
    jwt.verify(req.token, 'test', async (err, authData) => {
        if (err) res.status(401).send('Not authorized')
        else {
            const uname = authData.username
            if (uname !== 'admin') res.status(401).send('Not authorized')
            else {
                const db = connect()
                const username = req.params.username
                const querySt = `SELECT username,email,curr_quota FROM Users WHERE
      username = ?`;
                db.query(querySt, [username], (err, rows, fields) => {
                    if (err) throw err
                    const result = rows.map((row) => {
                        return {
                            username: row.username, email: row.email, requestsPerDayQuota: row.curr_quota
                        }
                    })
                    res.set('Content-Type', 'application/json')
                    res.send(result)
                })
            }
        }
    })
})

//------------UpdateUserInfo-----------------
router.put('/energy/api/Admin/users/:username', verifyToken, (req, res) => {
    jwt.verify(req.token, 'test', async (err, authData) => {
        if (err) res.status(401).send('Not authorized')
        else {
            const uname = authData.username
            if (uname !== 'admin') res.status(401).send('Not authorized')
            else {
                const db = connect()
                const username = req.params.username
                const quota = req.body.requestsPerDayQuota
                const email = req.body.email
                const querySt = `UPDATE Users SET email = '${email}',quota = '${quota}',curr_quota = '${quota}' 
                WHERE username = '${username}'`;
                db.query(querySt, (err) => {
                    if (err) throw err
                    else res.send({ username: username, email: email, requestsPerDayQuota: quota })
                })
            }
        }
    })

})


router.post('/energy/api/Admin/:dataset', verifyToken,async (req, res) => {
    jwt.verify(req.token, 'test', async (err, authData) => {
        if (err) res.status(401).send('Not authorized')
        else {
            const uname = authData.username
            if (uname !== 'admin') res.status(401).send('Not authorized')
            else {
                const dataset = req.params.dataset
                const file = req.body
                const {Parser} = require('json2csv')
                const json2csvparser = new Parser()
                const file1 = json2csvparser.parse(file)
                fs.writeFileSync(dataset +'.csv',' ')
                fs.writeFileSync(dataset +'.csv',file1,'utf-8')
                const db = connect()
                const querySt = `LOAD DATA LOCAL INFILE '`+ dataset + '.csv'+ `' INTO TABLE ${dataset} 
                FIELDS TERMINATED BY ';'             
                ESCAPED BY ''
                LINES TERMINATED BY '\n' 
                IGNORE 1 ROWS`;
                db.query(querySt, (err,rows) => {
                    if (err) throw err
                    else
                    {
                        const imported = rows.affectedRows
                        var database_counter = database_counter + imported
                        res.status(200).send({
                            totalRecordsInFile : imported,
                            totalRecordsImported : imported,
                            totalRecordsInDatabase : database_counter
                        })

                    }
                })
            }
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